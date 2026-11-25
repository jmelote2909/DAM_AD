package com.mycompany.tema01.lista_ejercicios_ficheros;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ejercicio03 {

    public static void main(String[] args) {
        // Arrays paralelos de igual longitud con los nombres y edades de los 10 alumnos
        String[] nombres = {
            "Anabo", "Carlos", "David", "Elena", "Javier",
            "Laura", "Marcos", "Nuria", "Pablo", "Sara"
        };
        int[] edades = { 19, 21, 20, 18, 22, 19, 23, 20, 21, 18 };
        
        // Ruta del fichero binario de salida
        File fichero = new File("src/main/java/com/mycompany/tema01/lista_ejercicios_ficheros/alumnos.bin");

        try {
            // FileOutputStream abre el fichero en modo bytes y DataOutputStream permite escribir tipos Java
            FileOutputStream fos = new FileOutputStream(fichero);
            DataOutputStream dos = new DataOutputStream(fos);
            
            //DataOutputStream dos = new DataOutputStream(new FileOutputStream("alumnos.bin"));
            
            // Escribe cada alumno en binario: primero el nombre en UTF-8 y luego la edad como int
            for (int i = 0; i < nombres.length; i++) {
                dos.writeUTF(nombres[i]);
                dos.writeInt(edades[i]);
            }

            // Cierra el flujo de datos (también cierra el fos subyacente)
            dos.close();

            System.out.println("Alumnos guardados correctamente en el fichero " + fichero.getName());

        } catch (IOException e) {
            // Captura errores al crear o escribir en el fichero binario
            System.err.println("Error al escribir en el fichero binario: " + e.getMessage());
        }
    }
}
