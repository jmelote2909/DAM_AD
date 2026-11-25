/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.ejercicio2_jesusmelendezoteros;

import java.io.File;
import java.io.StringWriter;
import java.util.Scanner;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.stream.StreamResult;

/**
 *
 * @author nocturno
 */
public class Ejercicio2_JesusMelendezOteros {

    public static void main(String[] args) {
        try {
            File fichero = new File("src/main/java/com/mycompany/ejercicio2_jesusmelendezoteros/tienda.xml");
            
            Scanner sc = new Scanner(System.in);
            
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();
            doc.setXmlVersion("1.0");
            
            Element elTienda = doc.createElement("tienda");
            doc.appendChild(elTienda);
            
            System.out.println("Cuantos articulos quieres añadir a tu tienda?");
            String cantidad = sc.nextLine();
            
            for (int i = 0; i < Integer.parseInt(cantidad); i++) {
                System.out.println("\nProducto: " + i);
                
                Element elArticulo = doc.createElement("articulo");
                
                System.out.println("Tipo:");
                String tipo = sc.nextLine();
                elArticulo.setAttribute("tipo", tipo);
                
                Element elId = doc.createElement("id");
                elId.appendChild(doc.createTextNode(String.valueOf(i)));
                elArticulo.appendChild(elId);
                
                System.out.println("\nNombre:");
                String nombre = sc.nextLine();
                Element elNombre = doc.createElement("nombre");
                elNombre.appendChild(doc.createTextNode(nombre));
                elArticulo.appendChild(elNombre);
                
                System.out.println("\nDescripcion:");
                String descripcion = sc.nextLine();
                Element elDescripcion = doc.createElement("descripcion");
                elDescripcion.appendChild(doc.createTextNode(descripcion));
                elArticulo.appendChild(elDescripcion);
                
                System.out.println("\nprecio:");
                String precio = sc.nextLine();
                Element elPrecio = doc.createElement("precio");
                elPrecio.appendChild(doc.createTextNode(precio));
                elArticulo.appendChild(elPrecio);
                
                System.out.println("\nstock:");
                String stock = sc.nextLine();
                Element elStock = doc.createElement("stock");
                elStock.appendChild(doc.createTextNode(stock));
                elArticulo.appendChild(elStock);
                
                elTienda.appendChild(elArticulo);
            }
            
            DOMSource domSource = new DOMSource(doc);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apacher.org/xslt}indent-amount", "4");
            
            StringWriter sw = new StringWriter();
            StreamResult sr = new StreamResult(sw);
            
            
            transformer.transform(domSource, sr);
            
            
            System.out.println(sw.toString());
            
            StreamResult srArchivo = new StreamResult(fichero);
            transformer.transform(domSource, srArchivo);
            
           
        } catch (ParserConfigurationException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}