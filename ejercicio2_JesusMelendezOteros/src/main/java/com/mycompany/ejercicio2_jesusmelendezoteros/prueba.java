/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio2_jesusmelendezoteros;

import java.io.StringWriter;
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
public class prueba {

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();
            doc.setXmlVersion("1.0");
            
            Element elClientes = doc.createElement("clientes");
            doc.appendChild(elClientes);
            
            Element elCliente = doc.createElement("cliente");
            elCliente.setAttribute("DNI", "11444555D");
            
            Element elApell = doc.createElement("apellidos");
            elApell.appendChild(doc.createTextNode("Lozano"));
            elCliente.appendChild(elApell);
            
            Element elValidez = doc.createElement("validez");
            elValidez.setAttribute("estado", "borrado");
            elValidez.setAttribute("timestamp", "456342133");
            elCliente.appendChild(elValidez);
            
            elClientes.appendChild(elCliente);
            
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
        } catch (ParserConfigurationException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
