package com.mycompany.tema01.examen30102024;

import java.io.File;

/**
 * Apartado 4 del examen: comprueba si el parámetro recibido es un archivo o un directorio
 * y muestra sus permisos y tamaño, o la lista de archivos que contiene.
 *
 * @author Usuario
 */
public class ej04 {

    /**
     * Método principal que recibe la ruta por argumento y muestra la información correspondiente.
     *
     * @param args Argumentos de la línea de comandos (args[0] es la ruta del fichero o directorio).
     */
    public static void main(String[] args) {

        // Comprueba que se haya pasado al menos un argumento por consola
        if (args.length > 0) {
            try {
                // Crea el objeto File apuntando a la ruta recibida en args[0]
                File archivo = new File(args[0]);

                if (!archivo.exists()) {
                    // La ruta indicada no existe en disco
                    System.out.println("Error: El fichero o directorio no existe.");
                } else if (archivo.isFile()) {
                    // Si es un archivo: muestra nombre, permisos (R/W/X) y tamaño en bytes
                    System.out.println("Es un archivo: " + archivo.getName());
                    System.out.println("Permiso de lectura: " + archivo.canRead());
                    System.out.println("Permiso de escritura: " + archivo.canWrite());
                    System.out.println("Permiso de ejecución: " + archivo.canExecute());
                    System.out.println("Tamaño del archivo: " + archivo.length() + " bytes");
                } else if (archivo.isDirectory()) {
                    // Si es un directorio: obtiene la lista de nombres que contiene
                    System.out.println("Es un directorio: " + archivo.getName());
                    String[] archivos = archivo.list();
                    if (archivos != null && archivos.length > 0) {
                        System.out.println("Archivos que contiene el directorio:");
                        for (String nombre : archivos) {
                            System.out.println("- " + nombre);
                        }
                    } else {
                        System.out.println("El directorio está vacío o no se pudo leer.");
                    }
                }
            } catch (SecurityException se) {
                // Error si el sistema deniega el acceso por permisos
                System.err.println("Error de permisos al acceder al fichero o directorio: " + se.getMessage());
            } catch (Exception e) {
                // Captura cualquier otro error inesperado
                System.err.println("Error al leer el fichero o directorio: " + e.getMessage());
            }
        } else {
            // Aviso si se ejecutó el programa sin pasar parámetro
            System.out.println("Error: No se ha proporcionado el nombre del fichero o directorio.");
        }

    }

}
