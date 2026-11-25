package com.mycompany.tema01.lista_ejercicios_ficheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ejercicio02 {

    public static void main(String[] args) {
        // Array origen con las 8 provincias y array destino para guardar lo leído
        String[] provincias = { "Almería", "Cádiz", "Córdoba", "Granada", "Huelva", "Jaén", "Málaga", "Sevilla" };
        String[] provinciasLeidas = new String[8];

        // Ruta del fichero de texto donde se guardarán las provincias
        File fichero = new File("src/main/java/com/mycompany/tema01/lista_ejercicios_ficheros/provincias_buffer.txt");

        try {
            // Envuelve FileWriter en BufferedWriter para escribir por líneas de forma más eficiente
            FileWriter fw = new FileWriter(fichero);
            BufferedWriter bw = new BufferedWriter(fw);

            // Escribe cada provincia seguida de un salto de línea con newLine()
            for (String provincia : provincias) {
                bw.write(provincia);
                bw.newLine();
            }

            // Cierra primero el buffer (vuelca el contenido pendiente) y luego el FileWriter
            bw.close();
            fw.close();
            System.out.println("Provincias escritas correctamente en el fichero " + fichero.getName());

            // Envuelve FileReader en BufferedReader para leer línea a línea con readLine()
            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);

            String linea;
            int contador = 0;

            // readLine() devuelve null cuando llega al final del fichero
            while ((linea = br.readLine()) != null && contador < provinciasLeidas.length) {
                provinciasLeidas[contador] = linea;
                contador++;
            }

            // Cierra el BufferedReader y el FileReader
            br.close();
            fr.close();

            // Muestra las provincias almacenadas en el array provinciasLeidas
            System.out.println("Provincias leídas correctamente del fichero:");
            for (int i = 0; i < provinciasLeidas.length; i++) {
                System.out.println((i + 1) + ". " + provinciasLeidas[i]);
            }

        } catch (IOException e) {
            // Captura cualquier error de escritura o lectura
            System.err.println("Error al trabajar con el fichero: " + e.getMessage());
        }
    }
}
