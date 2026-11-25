package com.mycompany.tema01.lista_ejercicios_ficheros;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ejercicio01 {

    // CMD: java .\ejercicio01.java provincias
    public static void main(String[] args) {
        // Array con las 8 provincias andaluzas a guardar y array donde se recuperarán después
        String[] provincias = { "Almería", "Cádiz", "Córdoba", "Granada", "Huelva", "Jaén", "Málaga", "Sevilla" };
        String[] provinciasLeidas = new String[8];
        
        // Comprueba que se haya pasado el nombre del fichero como argumento
        if (args.length > 0) {

            try {
                // Crea el flujo FileWriter para escribir texto en el fichero indicado en args[0]
                File fichero = new File(args[0]);
                FileWriter filewriter = new FileWriter(fichero);

                // Escribe cada provincia seguida de un salto de línea del sistema
                for (String provincia : provincias) {
                    filewriter.write(provincia + System.lineSeparator());
                }

                // Cierra el escritor para asegurar que todos los datos se vuelcan al disco
                filewriter.close();

                System.out.println("Provincias escrita correctamente en el fichero");

                // Abre el mismo fichero con FileReader para leerlo carácter a carácter
                FileReader fr = new FileReader(fichero);
                int caracter;
                int contador = 0;
                String provincia = "";

                // read() devuelve el código numérico del carácter leído o -1 al llegar al final
                while ((caracter = fr.read()) != -1) {
                    if (caracter == '\n') {
                        // Al encontrar salto de línea, guarda la provincia acumulada y reinicia
                        provinciasLeidas[contador] = provincia;
                        contador++;
                        provincia = "";
                    } else if (caracter != '\r') {
                        // Ignora el retorno de carro (\r en Windows) y concatena el carácter
                        provincia += (char) caracter;
                    }
                }

                // Cierra el lector
                fr.close();

                // Muestra el contenido recuperado en el array provinciasLeidas
                System.out.println("Provincias leídas correctamente del fichero:");
                for (int i = 0; i < provinciasLeidas.length; i++) {
                    System.out.println((i + 1) + ". " + provinciasLeidas[i]);
                }

            } catch (IOException ioe) {
                // Captura errores de entrada/salida al abrir, escribir o leer el fichero
                System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                System.err.println("Error al escribir en el fichero: " + ioe.getMessage());
            }

        } else {
            // Aviso si se ejecutó sin pasar el nombre del fichero en args[0]
            System.out.println("Error: No se ha proporcionado el nombre del fichero");
        }
    }
}
