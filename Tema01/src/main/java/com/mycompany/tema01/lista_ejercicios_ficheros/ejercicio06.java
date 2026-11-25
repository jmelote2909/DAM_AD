package com.mycompany.tema01.lista_ejercicios_ficheros;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ejercicio06 {

    public static void main(String[] args) {

        // Fichero binario donde se guardaron los objetos Alumno en el ejercicio 5
        File fichero = new File("src/main/java/com/mycompany/tema01/lista_ejercicios_ficheros/ObjetoAlumno.bin");

        // Comprueba que el fichero exista antes de intentar abrirlo
        if (!fichero.exists()) {
            System.err.println("Error: El fichero " + fichero.getName() + " no existe.");
            return;
        }

        try {
            // ObjectInputStream permite leer objetos Java completos desde un flujo binario
            FileInputStream fis = new FileInputStream(fichero);
            ObjectInputStream ois = new ObjectInputStream(fis);

            System.out.println("Alumnos deserializados de " + fichero.getName() + ":");
            int contador = 1;

            // Lee objetos en bucle hasta que salta EOFException al llegar al final del archivo
            try {
                while (true) {
                    // Deserializa el siguiente objeto y lo convierte (cast) a Alumno
                    Alumno a = (Alumno) ois.readObject();
                    System.out.println(contador + ". " + a.toString());
                    contador++;
                }
            } catch (EOFException eof) {
                // Fin del fichero: muestra cuántos alumnos se han recuperado
                System.out.println("\nTotal alumnos deserializados: " + (contador - 1));
            }

            // Cierra los flujos de entrada
            ois.close();
            fis.close();

        } catch (IOException e) {
            // Error de entrada/salida al leer el fichero
            System.err.println("Error al leer el fichero de objetos: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            // Error si la clase Alumno no coincide o no está disponible
            System.err.println("Error: Clase no encontrada: " + e.getMessage());
        }
    }
}
