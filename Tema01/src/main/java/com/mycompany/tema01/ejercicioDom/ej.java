package com.mycompany.tema01.ejercicioDom;

import java.io.File;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Lectura de un archivo XML con DOM
 */
public class ej {

    private Document doc = null;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ej ejercicio = new ej();
        
        File fichero = new File("src/main/java/com/mycompany/tema01/ejercicioDom/libros.xml");
        System.out.println("Abriendo archivo: " + fichero.getAbsolutePath());
        int resultadoAbrir = ejercicio.abrir_XML_DOM(fichero);

        if (resultadoAbrir == 0) {
            System.out.println("Documento cargado correctamente.");

            System.out.println("Deseas crear un nuevo libro?");
            System.out.println("0. Si");
            System.out.println("1. No");

            int respuesta = Integer.parseInt(sc.nextLine());
            if (respuesta == 0) {

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

            String salida = ejercicio.recorrerDOMyMostrar(ejercicio.doc);
            System.out.println(salida);
        } else {
            System.err.println("Error al abrir el documento XML.");
        }

    }

    /**
     *
     * @param titulo
     * @param autor
     * @param anio
     */
    private void crear_libro(String titulo, String autor, String publicado, String editorial) {

        // CREAR UN NUEVO ELEMENTO LIBRO EN EL XML Y AÑADIRLE EL ATRIBUTO PUBLICADO_EN
        Element nuevoLibro = doc.createElement("Libro");
        nuevoLibro.setAttribute("publicado_en", publicado);

        // CREAR UN NUEVO ELEMENTO TITULO Y DARLE TEXTO
        Element nuevoTitulo = doc.createElement("Titulo");
        nuevoTitulo.appendChild(doc.createTextNode(titulo));

        // CREAR UN NUEVO ELEMENTO AUTOR Y DARLE TEXTO
        Element nuevoAutor = doc.createElement("Autor");
        nuevoAutor.appendChild(doc.createTextNode(autor));

        // CREAR UN NUEVO ELEMENTO EDITORIAL Y DARLE TEXTO
        Element nuevaEditorial = doc.createElement("Editorial");
        nuevaEditorial.appendChild(doc.createTextNode(editorial));

        // AÑADIR LOS NUEVOS ELEMENTOS AL NUEVO LIBRO
        nuevoLibro.appendChild(nuevoTitulo);
        nuevoLibro.appendChild(nuevoAutor);
        nuevoLibro.appendChild(nuevaEditorial);

        // AÑADIR NUEVO LIBRO A LIBROS
        Node libros = doc.getElementsByTagName("Libros").item(0);
        libros.appendChild(nuevoLibro);

    }
    
    /**
     *
     * @param fichero
     * @return
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
     *
     * @param doc
     * @return
     */
    private String recorrerDOMyMostrar(Document doc) {
        String datos_nodo[] = null;
        String salida = "";
        Node node;
        // Obtiene el primero nodo del DOM (primer hijo)
        Node raiz = doc.getFirstChild();
        // Obtiene una lista de nodos con todos los nodos hijo del raíz.
        NodeList nodelist = raiz.getChildNodes();
        // Procesa los nodos hijo
        for (int i = 0; i < nodelist.getLength(); i++) {
            node = nodelist.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                // Es un nodo libro
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
     *
     * @param n
     * @return
     */
    private String[] procesarLibro(Node n) {
        String datos[] = new String[4];
        Node ntemp = null;
        int contador = 1;
        // Obtiene el valor del primer atributo del nodo (uno en este ejemplo)
        datos[0] = n.getAttributes().item(0).getNodeValue();
        // Obtiene los hijos del Libro (titulo y autor)
        NodeList nodos = n.getChildNodes();
        for (int i = 0; i < nodos.getLength(); i++) {
            ntemp = nodos.item(i);
            if (ntemp.getNodeType() == Node.ELEMENT_NODE) {
                // IMPORTANTE: para obtener el texto con el título y autor se accede al nodo
                // TEXT hijo de ntemp y se saca su valor.
                datos[contador] = ntemp.getChildNodes().item(0).getNodeValue();
                contador++;
            }
        }
        return datos;
    }

}