package com.mycompany.tema01.examen30102024;

import java.io.File;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Apartado 2 del examen: añadir un disco a musica.xml usando DOM y mostrar todos los discos.
 *
 * @author Usuario
 */
public class Ej02 {
    
    private Document doc;

    /**
     * Método principal que abre musica.xml, añade el disco de rock, muestra los discos y guarda los cambios.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        
        Ej02 ejercicio = new Ej02();

        // Abre musica.xml y lo carga en el árbol DOM en memoria
        File fichero = new File("src/main/java/com/mycompany/tema01/examen30102024/musica.xml");
        System.out.println("Abriendo archivo: " + fichero.getAbsolutePath());
        int resultadoAbrir = ejercicio.abrir_XML_DOM(fichero);
        if (resultadoAbrir == 0) {

            // Datos del nuevo disco que pide el apartado 2
            String genero = "rock";
            String autor = "Rolling Stone";
            String titulo = "It's Only Rock 'n' Roll";
            String anyo = "1974";
            String discografica = "Rolling Stones Records";
            
            // Añade el nuevo <Disco> al DOM en memoria
            ejercicio.crearDisco(genero, autor, titulo, anyo, discografica);
            
            // Recorre todos los discos del DOM y los muestra por consola
            String salida = ejercicio.recorrerDOMyMostrar(ejercicio.doc);
            System.out.println(salida);
            
            // Guarda el árbol DOM actualizado en musica.xml
            ejercicio.serializarDOM(fichero);

        }

    }
    
    /**
     * Serializa el árbol DOM actualizado por pantalla y sobrescribe el fichero XML.
     *
     * @param fichero Fichero XML de destino.
     */
    private void serializarDOM(File fichero) {
        try {
            DOMSource domSource = new DOMSource(doc);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            
            // OPCIONES DE CONFIGURAXION PARA LA SALIDA XML
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            // SERIALIZACION EN UN STRING MEDIANTE STRINGWRITER
            StringWriter sw = new StringWriter();
            StreamResult sr = new StreamResult(sw);
            transformer.transform(domSource, sr);

            // MOSTRAR EL XML RESULTANTE POR LA PANTALLA
            System.out.println(sw.toString());

            // SOBREESCRIBIBR EL ARCHIVO CON LOS NUEVOS DATOS
            StreamResult srArchivo = new StreamResult(fichero);
            transformer.transform(domSource, srArchivo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * Extrae el atributo género y los elementos hijo de un nodo {@code <Disco>}.
     *
     * @param n Nodo {@code <Disco>} a procesar.
     * @return Array de Strings con género, autor, título, año y discográfica.
     */
    private String[] procesarDisco(Node n) {
        String datos[] = new String[5];
        Node ntemp = null;
        int contador = 1;
        // Obtiene el valor del atributo genero del nodo <Disco>
        datos[0] = n.getAttributes().item(0).getNodeValue();
        // Obtiene los nodos hijo de <Disco> (Autor, Titulo, Anyo, Discografica)
        NodeList nodos = n.getChildNodes();
        for (int i = 0; i < nodos.getLength(); i++) {
            ntemp = nodos.item(i);
            if (ntemp.getNodeType() == Node.ELEMENT_NODE) {
                // Accede al nodo TEXT hijo de cada etiqueta para sacar su valor
                datos[contador] = ntemp.getChildNodes().item(0).getNodeValue();
                contador++;
            }
        }
        return datos;
    }
    
    /**
     * Recorre el documento DOM y construye una cadena con la información de todos los discos.
     *
     * @param doc Documento DOM cargado en memoria.
     * @return Cadena formateada con todos los discos.
     */
    private String recorrerDOMyMostrar(Document doc) {
        String datos_nodo[] = null;
        String salida = "";
        Node node;
        // Obtiene el nodo raíz <Musica>
        Node raiz = doc.getFirstChild();
        // Obtiene todos los nodos hijo dentro de <Musica>
        NodeList nodelist = raiz.getChildNodes();
        // Recorre los hijos filtrando solo las etiquetas (ignorando saltos de línea)
        for (int i = 0; i < nodelist.getLength(); i++) {
            node = nodelist.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                // Extrae los 5 campos del <Disco> y los formatea en la salida
                datos_nodo = procesarDisco(node);
                salida = salida + "\n " + "Género: " + datos_nodo[0];
                salida = salida + "\n " + "Autor: " + datos_nodo[1];
                salida = salida + "\n " + "Título: " + datos_nodo[2];
                salida = salida + "\n " + "Anyo: " + datos_nodo[3];
                salida = salida + "\n " + "Discografía: " + datos_nodo[4];
                salida = salida + "\n";
            }
        }
        return salida;
    }
    
    /**
     * Crea un nuevo nodo {@code <Disco>} y lo añade al elemento raíz {@code <Musica>}.
     *
     * @param genero Género del disco.
     * @param autor Autor del disco.
     * @param titulo Título del disco.
     * @param anyo Año del disco.
     * @param discografica Discográfica del disco.
     */
    public void crearDisco(String genero, String autor, String titulo, String anyo, String discografica){
        // CREAR UN NUEVO ELEMENTO DISCO EN EL XML Y AÑADIRLE EL ATRIBUTO GENERO
            Element nuevoDisco = doc.createElement("Disco");
            nuevoDisco.setAttribute("genero", genero);

            // CREAR UN NUEVO ELEMENTO AUTOR Y DARLE TEXTO
            Element nuevoAutor = doc.createElement("Autor");
            nuevoAutor.appendChild(doc.createTextNode(autor));
            
            // CREAR UN NUEVO ELEMENTO TITULO Y DARLE TEXTO
            Element nuevoTitulo = doc.createElement("Titulo");
            nuevoTitulo.appendChild(doc.createTextNode(titulo));

            // CREAR UN NUEVO ELEMENTO ANYO Y DARLE TEXTO
            Element nuevoAnyo = doc.createElement("Anyo");
            nuevoAnyo.appendChild(doc.createTextNode(anyo));
            
            // CREAR UN NUEVO ELEMENTO DISCOGRAFICA Y DARLE TEXTO
            Element nuevaDiscografica = doc.createElement("Discografica");
            nuevaDiscografica.appendChild(doc.createTextNode(discografica));            

            // AÑADIR LOS NUEVOS ELEMENTOS AL NUEVO DISCO (en el mismo orden que el XML)
            nuevoDisco.appendChild(nuevoAutor);
            nuevoDisco.appendChild(nuevoTitulo);
            nuevoDisco.appendChild(nuevoAnyo);
            nuevoDisco.appendChild(nuevaDiscografica);

            // AÑADIR NUEVO DISCO A DISCOS
            Node discos = doc.getElementsByTagName("Musica").item(0);
            discos.appendChild(nuevoDisco);
    }

    /**
     * Carga un fichero XML en memoria utilizando el parser DOM.
     *
     * @param fichero Fichero XML a abrir.
     * @return 0 si se abre correctamente, -1 si ocurre algún error.
     */
    private int abrir_XML_DOM(File fichero) {
        doc = null; // Representa al árbol DOM
        try {
            // Se crea un objeto DocumentBuilderFactory.
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Indica que el modelo DOM no debe contemplar los comentarios que tenga el XML.
            factory.setIgnoringComments(true);
            // Ignora los espacios en blanco que tenga el documento
            factory.setIgnoringElementContentWhitespace(true);
            // Se crea un objeto DocumentBuilder para cargar en él la estructura de árbol
            // DOM a partir del XML seleccionado.
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Interpreta (parsear) el documento XML (file) y genera el DOM equivalente.
            doc = builder.parse(fichero);
            // Ahora doc apunta al árbol DOM listo para ser recorrido.
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
