package com.mycompany.tema01.ejercicioSerializacionDOM;

import java.io.File;
import java.io.StringWriter;
import java.util.Scanner;

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
 * Lectura y serialización de un archivo XML con DOM
 */
public class ej {

    private Document doc = null;

    public static void main(String[] args) {

        // Scanner para leer las respuestas del usuario por teclado
        Scanner sc = new Scanner(System.in);

        // Crea una instancia de la clase y apunta al XML de libros
        ej ejercicio = new ej();
        File fichero = new File("src/main/java/com/mycompany/tema01/ejercicioSerializacionDOM/libros.xml");
        System.out.println("Abriendo archivo: " + fichero.getAbsolutePath());

        // Carga el XML en memoria como árbol DOM (devuelve 0 si todo va bien)
        int resultadoAbrir = ejercicio.abrir_XML_DOM(fichero);

        if (resultadoAbrir == 0) {
            System.out.println("Documento cargado correctamente.");

            // Pregunta si se quiere añadir un libro nuevo antes de mostrar y guardar
            System.out.println("Deseas crear un nuevo libro?");
            System.out.println("0. Si");
            System.out.println("1. No");

            int respuesta = Integer.parseInt(sc.nextLine());
            if (respuesta == 0) {
                // Pide los datos del libro y crea el nuevo nodo <Libro> en el DOM
                System.out.println("Ingrese el titulo del libro: ");
                String titulo = sc.nextLine();
                System.out.println("Ingrese el autor del libro: ");
                String autor = sc.nextLine();
                System.out.println("Ingrese el año de publicacion: ");
                String anio = sc.nextLine();
                System.out.println("Ingrese la editorial: ");
                String editorial = sc.nextLine();
                ejercicio.crear_libro(titulo, autor, anio, editorial);
            }

            // 1. Recorre el árbol DOM y muestra los libros por consola
            System.out.println("\nCONTENIDO DEL DOM");
            String salida = ejercicio.recorrerDOMyMostrar(ejercicio.doc);
            System.out.println(salida);

            // 2. Serializa el árbol DOM a texto XML y actualiza el archivo en disco
            System.out.println("DOCUMENTO XML SERIALIZADO");
            ejercicio.serializarDOM(fichero);

        } else {
            System.err.println("Error al abrir el documento XML.");
        }
        
        // Cierra el Scanner al terminar
        sc.close();
    }

    /**
     * Serializa el árbol DOM actual a formato texto XML por consola (mediante StringWriter)
     * y sobrescribe el archivo XML en disco.
     * 
     * @param fichero Archivo XML de destino donde guardar los cambios.
     */
    private void serializarDOM(File fichero) {
        try {
            // Prepara el origen (el árbol DOM) y el transformador que lo convierte a XML
            DOMSource domSource = new DOMSource(doc);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            
            // Configura formato XML, codificación UTF-8 y sangría de 4 espacios
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            // Serializa el DOM en memoria dentro de un StringWriter para imprimirlo en consola
            StringWriter sw = new StringWriter();
            StreamResult sr = new StreamResult(sw);
            transformer.transform(domSource, sr);

            // Muestra el XML resultante por pantalla
            System.out.println(sw.toString());

            // Vuelca el mismo árbol DOM sobre el fichero en disco (sobrescribiéndolo)
            StreamResult srArchivo = new StreamResult(fichero);
            transformer.transform(domSource, srArchivo);

        } catch (Exception e) {
            // Muestra la traza si falla la transformación o la escritura del archivo
            e.printStackTrace();
        }
    }

    /**
     * Crea un nuevo nodo Libro en el árbol DOM.
     *
     * @param titulo Título del libro.
     * @param autor Autor del libro.
     * @param publicado Año de publicación (atributo {@code publicado_en}).
     * @param editorial Nombre de la editorial.
     */
    private void crear_libro(String titulo, String autor, String publicado, String editorial) {

        // Crea la etiqueta <Libro> y le añade el atributo publicado_en="..."
        Element nuevoLibro = doc.createElement("Libro");
        nuevoLibro.setAttribute("publicado_en", publicado);

        // Crea <Titulo> con su nodo de texto interior
        Element nuevoTitulo = doc.createElement("Titulo");
        nuevoTitulo.appendChild(doc.createTextNode(titulo));

        // Crea <Autor> con su nodo de texto interior
        Element nuevoAutor = doc.createElement("Autor");
        nuevoAutor.appendChild(doc.createTextNode(autor));

        // Crea <Editorial> con su nodo de texto interior
        Element nuevaEditorial = doc.createElement("Editorial");
        nuevaEditorial.appendChild(doc.createTextNode(editorial));

        // Mete <Titulo>, <Autor> y <Editorial> dentro de <Libro>
        nuevoLibro.appendChild(nuevoTitulo);
        nuevoLibro.appendChild(nuevoAutor);
        nuevoLibro.appendChild(nuevaEditorial);

        // Busca la etiqueta raíz <Libros> y cuelga el nuevo <Libro> al final
        Node libros = doc.getElementsByTagName("Libros").item(0);
        libros.appendChild(nuevoLibro);
    }

    /**
     * Abre el fichero XML y genera el árbol DOM.
     *
     * @param fichero
     * @return 0 si todo es correcto, -1 si hay error.
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

    /**
     * Recorre el árbol DOM y devuelve una cadena con la información de los libros.
     *
     * @param doc Documento DOM cargado en memoria.
     * @return Texto formateado con los datos de todos los libros.
     */
    private String recorrerDOMyMostrar(Document doc) {
        String datos_nodo[] = null;
        String salida = "";
        Node node;
        // Obtiene el nodo raíz del documento (<Libros>)
        Node raiz = doc.getFirstChild();
        // Obtiene todos los hijos de <Libros> (etiquetas <Libro> y saltos de línea)
        NodeList nodelist = raiz.getChildNodes();
        // Recorre los hijos y procesa únicamente los nodos de tipo etiqueta (ELEMENT_NODE)
        for (int i = 0; i < nodelist.getLength(); i++) {
            node = nodelist.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                // Extrae los 4 datos de <Libro> y los añade al texto de salida
                datos_nodo = procesarLibro(node);
                salida = salida + "\n " + "Publicado en: " + datos_nodo[0];
                salida = salida + "\n " + "El autor es: " + datos_nodo[2];
                salida = salida + "\n " + "El título es: " + datos_nodo[1];
                salida = salida + "\n " + "La editorial es: " + datos_nodo[3];
                salida = salida + "\n";
            }
        }
        return salida;
    }

    /**
     * Extrae el atributo y los elementos hijo de un nodo Libro.
     *
     * @param n Nodo {@code <Libro>} a leer.
     * @return Array de Strings: [0]=publicado_en, [1]=Título, [2]=Autor, [3]=Editorial.
     */
    private String[] procesarLibro(Node n) {
        String datos[] = new String[4];
        Node ntemp = null;
        int contador = 1;
        // Posición 0: lee el primer atributo de <Libro> (publicado_en)
        datos[0] = n.getAttributes().item(0).getNodeValue();
        // Posiciones 1..3: recorre los hijos de <Libro> (Titulo, Autor, Editorial)
        NodeList nodos = n.getChildNodes();
        for (int i = 0; i < nodos.getLength(); i++) {
            ntemp = nodos.item(i);
            if (ntemp.getNodeType() == Node.ELEMENT_NODE) {
                // Accede al primer hijo de cada etiqueta (su nodo TEXT) para extraer el valor
                datos[contador] = ntemp.getChildNodes().item(0).getNodeValue();
                contador++;
            }
        }
        return datos;
    }
}