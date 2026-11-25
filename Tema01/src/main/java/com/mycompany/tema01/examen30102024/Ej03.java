package com.mycompany.tema01.examen30102024;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Apartado 3 del examen: lectura de musica.xml mediante SAX para mostrar género
 * y autor de cada disco.
 *
 * @author Usuario
 */
public class Ej03 {

    /**
     * Método principal que configura el parser SAX y recorre musica.xml.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        try {
            // Crea la fábrica y la instancia del parser SAX
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            // Manejador de eventos SAX que reacciona al abrir/cerrar etiquetas y leer texto
            DefaultHandler handler = new DefaultHandler() {
                // Bandera para saber cuándo estamos dentro de la etiqueta <Autor>
                boolean bAutor = false;

                /*
                 * Se ejecuta al encontrar una etiqueta de apertura (<Etiqueta>).
                 * - uri: URI del namespace (espacio de nombres) o "" si no se usan namespaces.
                 * - localName: nombre de la etiqueta sin prefijo ("" si el parser no tiene namespaces activados).
                 * - qName: nombre completo de la etiqueta tal cual sale en el XML (ej. "Disco" o "prefijo:Disco").
                 * - attributes: objeto con los atributos de la etiqueta (ej. genero="flamenco").
                 */
                public void startElement(String uri, String localName, String qName, Attributes attributes)
                        throws SAXException {
                    // Si empieza un <Disco>, lee y muestra su atributo genero
                    if (qName.equalsIgnoreCase("disco")) {
                        String genero = attributes.getValue("genero");
                        System.out.println("genero: " + genero);
                    }
                    // Si empieza <Autor>, activa la bandera para leer su texto en characters()
                    if (qName.equalsIgnoreCase("autor")) {
                        bAutor = true;
                    }
                }

                /*
                 * Se ejecuta al encontrar una etiqueta de cierre (</Etiqueta>).
                 * - uri, localName, qName: significan lo mismo que en startElement, pero para la etiqueta de cierre.
                 */
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    // Al cerrar </Disco>, imprime una línea en blanco para separar cada disco
                    if (qName.equalsIgnoreCase("disco")) {
                        System.out.println("");
                    }
                }

                /*
                 * Se ejecuta al leer el texto contenido entre apertura y cierre de una etiqueta.
                 * - ch[]: array (buffer) con caracteres del documento leído por el parser.
                 * - start: índice dentro de ch[] donde empieza exactamente el texto de esta etiqueta.
                 * - length: cantidad de caracteres que ocupa ese texto desde la posición start.
                 * (Por eso se usa new String(ch, start, length) y no todo el array ch[]).
                 */
                public void characters(char ch[], int start, int length) throws SAXException {
                    if (bAutor) {
                        System.out.println("autor: " + new String(ch, start, length));
                        bAutor = false; // Desactiva la bandera tras leer el autor
                    }
                }

                /*
                 * Se ejecuta una sola vez al finalizar la lectura completa del documento XML.
                 * No recibe parámetros (suele usarse para mostrar totales o resúmenes finales).
                 */
                public void endDocument() throws SAXException {

                }
            };

            // Busca musica.xml en la carpeta del paquete o en la raíz del proyecto
            File file = new File("src/main/java/com/mycompany/tema01/examen30102024/musica.xml");
            if (!file.exists()) {
                file = new File("musica.xml");
            }
            // Abre el archivo forzando codificación UTF-8 para tildes y caracteres especiales
            InputStream inputStream = new FileInputStream(file);
            Reader reader = new InputStreamReader(inputStream, "UTF-8");
            InputSource is = new InputSource(reader);
            is.setEncoding("UTF-8");

            // Lanza el análisis SAX recorriendo el XML con el manejador definido
            saxParser.parse(is, handler);
        } catch (Exception e) {
            // Muestra la traza si ocurre cualquier error de E/S o de parseo XML
            e.printStackTrace();
        }

    }
}