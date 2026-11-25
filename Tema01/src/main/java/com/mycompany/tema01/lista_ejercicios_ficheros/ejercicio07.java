package com.mycompany.tema01.lista_ejercicios_ficheros;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ejercicio07 {

    public static void main(String[] args) {
        // Mismos arrays del ejercicio 3: nombres y edades de los 10 alumnos
        String[] nombres = {
            "Anabo", "Carlos", "David", "Elena", "Javier",
            "Laura", "Marcos", "Nuria", "Pablo", "Sara"
        };
        int[] edades = { 19, 21, 20, 18, 22, 19, 23, 20, 21, 18 };

        // Tamaño fijo por registro para poder calcular la posición exacta en bytes
        final int LONGITUD_NOMBRE = 20; // 20 caracteres * 2 bytes cada char = 40 bytes
        final int TAMANO_REGISTRO = (LONGITUD_NOMBRE * 2) + Integer.BYTES; // 40 + 4 = 44 bytes

        File fichero = new File("src/main/java/com/mycompany/tema01/lista_ejercicios_ficheros/alumnos_aleatorio.bin");

        try {
            // Abre el fichero en modo acceso directo lectura/escritura ("rw")
            RandomAccessFile raf = new RandomAccessFile(fichero, "rw");

            // Escribe cada alumno en su posición exacta calculada con el índice (i * 44)
            for (int i = 0; i < nombres.length; i++) {
                long posicion = (long) i * TAMANO_REGISTRO;
                raf.seek(posicion); // Sitúa el puntero en el byte inicial del registro i

                // Ajusta el nombre exactamente a 20 caracteres (rellena con '\0' si es más corto)
                StringBuffer sb = new StringBuffer(nombres[i]);
                sb.setLength(LONGITUD_NOMBRE);
                raf.writeChars(sb.toString()); // Escribe los 40 bytes del nombre
                raf.writeInt(edades[i]);       // Escribe los 4 bytes de la edad
            }

            System.out.println("Alumnos guardados correctamente en " + fichero.getName());
            System.out.println("Leyendo alumnos por acceso aleatorio:\n");

            // Vuelve a recorrer los índices para leer cada registro directamente desde su posición
            for (int i = 0; i < nombres.length; i++) {
                long posicion = (long) i * TAMANO_REGISTRO;
                raf.seek(posicion); // Salta al byte de inicio del alumno i

                // Lee los 20 caracteres (2 bytes cada uno) y elimina el relleno '\0'
                char[] aux = new char[LONGITUD_NOMBRE];
                for (int j = 0; j < LONGITUD_NOMBRE; j++) {
                    aux[j] = raf.readChar();
                }
                String nombre = new String(aux).replace('\0', ' ').trim();
                int edad = raf.readInt(); // Lee los 4 bytes siguientes correspondientes a la edad

                System.out.println((i + 1) + ". [Posición " + posicion + "] Alumno: " + nombre + "  Edad: " + edad);
            }

            // Cierra el fichero de acceso aleatorio
            raf.close();

        } catch (IOException e) {
            // Error de lectura o escritura en el fichero
            System.err.println("Error en el acceso aleatorio al fichero: " + e.getMessage());
        }
    }
}
