package com.mycompany.tema01.lista_ejercicios_ficheros;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ejercicio04 {

    public static void main(String[] args) {

        // Ruta del fichero binario creado en el ejercicio 3
        File fichero = new File("src/main/java/com/mycompany/tema01/lista_ejercicios_ficheros/alumnos.bin");

        // Comprueba antes de leer que el fichero exista en disco
        if (!fichero.exists()) {
            System.err.println("Error: El fichero " + fichero.getName() + " no existe.");
            return;
        }

        try {

            // FileInputStream lee bytes y DataInputStream los interpreta como tipos Java (UTF e int)
            FileInputStream fis = new FileInputStream(fichero);
            DataInputStream dis = new DataInputStream(fis);
            
            //DataInputStream dis = new DataInputStream(new FileInputStream("alumnos.bin"))

            System.out.println("Alumnos recuperados de " + fichero.getName());
            int contador = 1;


            // Bucle infinito hasta que saltar EOFException indique que se llegó al final del fichero
            try {
                while (true) {
                    // Lee exactamente en el mismo orden en que se escribió (primero String UTF, luego int)
                    String nombre = dis.readUTF();
                    int edad = dis.readInt();
                    System.out.println(contador + ". Alumno: " + nombre + "  Edad: " + edad);
                    contador++;
                }
            } catch (EOFException eof) {
                // End Of File: final normal de lectura del fichero binario
                System.out.println("\nTotal alumnos recuperados: " + (contador - 1));
            }

            // Cierra el flujo de entrada
            dis.close();

        } catch (IOException e) {
            // Captura otros errores de lectura del fichero binario
            System.err.println("Error al leer el fichero binario: " + e.getMessage());
        }
    }
}
