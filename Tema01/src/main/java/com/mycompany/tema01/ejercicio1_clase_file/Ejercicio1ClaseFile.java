package com.mycompany.tema01.ejercicio1_clase_file;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Ejercicio1ClaseFile {

    private static File directorioActual = obtenerDirectorioInicial();
    private static File elementoSeleccionado = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            mostrarMenu();
            String entrada = scanner.nextLine().trim();

            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduce un número válido del 1 al 7.");
                continue;
            }

            switch (opcion) {
                case 1:
                    solicitarNombre(scanner);
                    break;
                case 2:
                    mostrarInformacion();
                    break;
                case 3:
                    crearDirectorio(scanner);
                    break;
                case 4:
                    crearFichero(scanner);
                    break;
                case 5:
                    accederDirectorio(scanner);
                    break;
                case 6:
                    mostrarContenidoDirectorio(scanner);
                    break;
                case 7:
                    System.out.println("Fin del programa.");
                    break;
                default:
                    System.out.println("Opción no válida. Elige una opción entre 1 y 7.");
            }
        } while (opcion != 7);

        scanner.close();
    }

    private static File obtenerDirectorioInicial() {
        File dir = new File(System.getProperty("user.dir"));
        try {
            return dir.getCanonicalFile();
        } catch (IOException e) {
            return dir.getAbsoluteFile();
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n==========================================");
        System.out.println("Directorio actual: " + directorioActual.getAbsolutePath());
        System.out.println("Elemento seleccionado: "
                + (elementoSeleccionado != null ? elementoSeleccionado.getAbsolutePath() : "(ninguno)"));
        System.out.println("==========================================");
        System.out.println("1. Solicitar el nombre de un fichero y/o directorio");
        System.out.println("2. Mostrar toda la información posible del fichero y/o directorio");
        System.out.println("3. Crear un directorio");
        System.out.println("4. Crear un fichero");
        System.out.println("5. Acceder a un directorio");
        System.out.println("6. Mostrar todos los archivos/subdirectorios de un directorio");
        System.out.println("7. Fin");
        System.out.print("Seleccione una opción: ");
    }

    private static File resolverRuta(String ruta) {
        File f = new File(ruta);
        if (!f.isAbsolute()) {
            f = new File(directorioActual, ruta);
        }
        try {
            return f.getCanonicalFile();
        } catch (IOException e) {
            return f.getAbsoluteFile();
        }
    }

    private static void solicitarNombre(Scanner scanner) {
        System.out.print("Introduce el nombre o ruta del fichero/directorio: ");
        String ruta = scanner.nextLine().trim();

        if (ruta.isEmpty()) {
            System.out.println("No se ha introducido ningún nombre.");
            return;
        }

        elementoSeleccionado = resolverRuta(ruta);
        System.out.println("Elemento seleccionado: " + elementoSeleccionado.getAbsolutePath());
        if (elementoSeleccionado.exists()) {
            System.out.println("Estado: existe (" + (elementoSeleccionado.isDirectory() ? "directorio" : "fichero") + ").");
        } else {
            System.out.println("Estado: no existe todavía.");
        }
    }

    private static void mostrarInformacion() {
        if (elementoSeleccionado == null) {
            System.out.println("Primero debes seleccionar un fichero o directorio en la opción 1.");
            return;
        }

        System.out.println("\n--- Información del elemento ---");
        System.out.println("Nombre: " + elementoSeleccionado.getName());
        System.out.println("Ruta: " + elementoSeleccionado.getPath());
        System.out.println("Ruta absoluta: " + elementoSeleccionado.getAbsolutePath());
        try {
            System.out.println("Ruta canónica: " + elementoSeleccionado.getCanonicalPath());
        } catch (IOException e) {
            System.out.println("Ruta canónica: no disponible (" + e.getMessage() + ")");
        }
        System.out.println("Directorio padre: " + elementoSeleccionado.getParent());
        System.out.println("¿Existe?: " + elementoSeleccionado.exists());

        if (!elementoSeleccionado.exists()) {
            System.out.println("El fichero o directorio no existe en el sistema de archivos.");
            return;
        }

        System.out.println("¿Es directorio?: " + elementoSeleccionado.isDirectory());
        System.out.println("¿Es fichero?: " + elementoSeleccionado.isFile());
        System.out.println("¿Está oculto?: " + elementoSeleccionado.isHidden());
        System.out.println("¿Se puede leer?: " + elementoSeleccionado.canRead());
        System.out.println("¿Se puede escribir?: " + elementoSeleccionado.canWrite());
        System.out.println("¿Se puede ejecutar?: " + elementoSeleccionado.canExecute());
        System.out.println("Tamaño: " + elementoSeleccionado.length() + " bytes");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println("Última modificación: " + sdf.format(new Date(elementoSeleccionado.lastModified())));

        System.out.println("Espacio total del volumen: " + elementoSeleccionado.getTotalSpace() + " bytes");
        System.out.println("Espacio libre del volumen: " + elementoSeleccionado.getFreeSpace() + " bytes");
        System.out.println("Espacio utilizable: " + elementoSeleccionado.getUsableSpace() + " bytes");

        if (elementoSeleccionado.isDirectory()) {
            String[] elementos = elementoSeleccionado.list();
            System.out.println("Número de elementos dentro: " + (elementos != null ? elementos.length : "no accesible"));
        }
    }

    private static void crearDirectorio(Scanner scanner) {
        System.out.print("Introduce el nombre o ruta del directorio a crear: ");
        String ruta = scanner.nextLine().trim();

        if (ruta.isEmpty()) {
            System.out.println("Nombre de directorio vacío.");
            return;
        }

        File nuevoDir = resolverRuta(ruta);
        if (nuevoDir.exists()) {
            System.out.println("Ya existe un elemento con esa ruta: " + nuevoDir.getAbsolutePath());
        } else if (nuevoDir.mkdirs()) {
            System.out.println("Directorio creado correctamente: " + nuevoDir.getAbsolutePath());
            elementoSeleccionado = nuevoDir;
        } else {
            System.out.println("No se pudo crear el directorio.");
        }
    }

    private static void crearFichero(Scanner scanner) {
        System.out.print("Introduce el nombre o ruta del fichero a crear: ");
        String ruta = scanner.nextLine().trim();

        if (ruta.isEmpty()) {
            System.out.println("Nombre de fichero vacío.");
            return;
        }

        File nuevoFichero = resolverRuta(ruta);
        try {
            File padre = nuevoFichero.getParentFile();
            if (padre != null && !padre.exists()) {
                padre.mkdirs();
            }

            if (nuevoFichero.createNewFile()) {
                System.out.println("Fichero creado correctamente: " + nuevoFichero.getAbsolutePath());
                elementoSeleccionado = nuevoFichero;
            } else {
                System.out.println("El fichero ya existe: " + nuevoFichero.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("Error al crear el fichero: " + e.getMessage());
        }
    }

    private static void accederDirectorio(Scanner scanner) {
        System.out.print("Introduce la ruta del directorio al que acceder (usa '..' para subir): ");
        String ruta = scanner.nextLine().trim();

        if (ruta.isEmpty()) {
            System.out.println("Ruta vacía.");
            return;
        }

        File destino = resolverRuta(ruta);
        if (destino.exists() && destino.isDirectory()) {
            directorioActual = destino;
            System.out.println("Has accedido a: " + directorioActual.getAbsolutePath());
        } else {
            System.out.println("La ruta indicada no existe o no es un directorio.");
        }
    }

    private static void mostrarContenidoDirectorio(Scanner scanner) {
        System.out.print("Introduce la ruta del directorio (pulsa Enter para usar el directorio actual): ");
        String ruta = scanner.nextLine().trim();

        File dir = ruta.isEmpty() ? directorioActual : resolverRuta(ruta);

        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("El directorio no existe o no es válido: " + dir.getAbsolutePath());
            return;
        }

        File[] contenido = dir.listFiles();
        if (contenido == null) {
            System.out.println("No se pudo leer el contenido del directorio.");
            return;
        }

        System.out.println("\n--- Contenido de " + dir.getAbsolutePath() + " ---");
        if (contenido.length == 0) {
            System.out.println("(Directorio vacío)");
            return;
        }

        for (File item : contenido) {
            if (item.isDirectory()) {
                System.out.println("[DIR]  " + item.getName());
            } else {
                System.out.println("[FILE] " + item.getName() + " (" + item.length() + " bytes)");
            }
        }
    }
}
