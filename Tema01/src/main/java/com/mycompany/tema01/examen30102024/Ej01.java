package com.mycompany.tema01.examen30102024;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Apartado 1 del examen: creación del fichero musica.xml con DOM.
 *
 * @author Usuario
 */
public class Ej01 {

    private Document doc;

    /**
     * Método principal que crea el árbol DOM con los discos iniciales y lo guarda en musica.xml.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {

        Ej01 ejercicio = new Ej01();
        // Ruta del fichero XML que se va a generar
        File fichero = new File("src/main/java/com/mycompany/tema01/examen30102024/musica.xml");

        // Crea el documento DOM vacío con la raíz <Musica> y añade los 6 discos
        if (ejercicio.crearDocumentoXML() == 0) {
            ejercicio.agregarDisco("flamenco", "Camarón de la Isla", "La leyenda del tiempo", "1979", "PolyGram Ibérica, S.A.");
            ejercicio.agregarDisco("flamenco", "José Mercé", "Aire", "2000", "Virgin Records España S.A.");
            ejercicio.agregarDisco("clasica", "AntonioVivaldi", "Concerti", "2003", "Musica ad Rhenum");
            ejercicio.agregarDisco("clasica", "Johann Johannsson", "Orphee", "2016", "Deutsche Grammophon");
            ejercicio.agregarDisco("pop", "The Beatles", "Abbey Road", "1969", " Apple Records");
            ejercicio.agregarDisco("pop", "El Ultimo de la fila", "Insurreccion", "1986", "PDI");

            // Vuelca el árbol DOM en disco como archivo XML
            ejercicio.guardarXML(fichero);
        }
    }

    /**
     * Inicializa el documento DOM y crea el nodo raíz {@code <Musica>}.
     *
     * @return 0 si se crea correctamente, -1 en caso de error.
     */
    private int crearDocumentoXML() {
        try {
            // Crea la fábrica y el constructor de documentos DOM en memoria
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.newDocument();

            // Crea la etiqueta raíz <Musica> y la cuelga del documento
            Element raiz = doc.createElement("Musica");
            doc.appendChild(raiz);
            return 0;
        } catch (ParserConfigurationException e) {
            System.err.println("Error al configurar el parser DOM: " + e.getMessage());
            return -1;
        }
    }

    /**
     * Crea un elemento {@code <Disco>} con sus nodos hijo y lo añade al nodo raíz {@code <Musica>}.
     *
     * @param genero Género musical del disco.
     * @param autor Autor o grupo del disco.
     * @param titulo Título del disco.
     * @param anyo Año de publicación.
     * @param discografica Nombre de la discográfica.
     */
    private void agregarDisco(String genero, String autor, String titulo, String anyo, String discografica) {
        // Crea <Disco> y le asigna el atributo genero="..."
        Element disco = doc.createElement("Disco");
        disco.setAttribute("genero", genero);

        // Crea cada etiqueta hija con su nodo de texto correspondiente
        Element elAutor = doc.createElement("Autor");
        elAutor.appendChild(doc.createTextNode(autor));

        Element elTitulo = doc.createElement("Titulo");
        elTitulo.appendChild(doc.createTextNode(titulo));

        Element elAnyo = doc.createElement("Anyo");
        elAnyo.appendChild(doc.createTextNode(anyo));

        Element elDiscografica = doc.createElement("Discografica");
        elDiscografica.appendChild(doc.createTextNode(discografica));

        // Engancha las etiquetas hijas dentro de <Disco>
        disco.appendChild(elAutor);
        disco.appendChild(elTitulo);
        disco.appendChild(elAnyo);
        disco.appendChild(elDiscografica);

        // Añade el <Disco> completo al nodo raíz <Musica>
        doc.getDocumentElement().appendChild(disco);
    }

    /**
     * Serializa el árbol DOM al fichero XML indicado.
     *
     * @param fichero Fichero de destino donde guardar el XML.
     */
    private void guardarXML(File fichero) {
        try {
            // Crea las carpetas padre si no existen todavía
            if (fichero.getParentFile() != null && !fichero.getParentFile().exists()) {
                fichero.getParentFile().mkdirs();
            }

            // Prepara la fuente DOM y el transformador para escribir el XML
            DOMSource domSource = new DOMSource(doc);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            // Configura formato XML, codificación UTF-8 y sangría de 4 espacios
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            // Escribe el resultado en el fichero de destino
            StreamResult srArchivo = new StreamResult(fichero);
            transformer.transform(domSource, srArchivo);

            System.out.println("Fichero creado correctamente en: " + fichero.getAbsolutePath());
        } catch (TransformerException e) {
            System.err.println("Error al guardar el fichero XML: " + e.getMessage());
        }
    }
}
