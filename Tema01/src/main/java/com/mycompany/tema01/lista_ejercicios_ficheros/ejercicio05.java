package com.mycompany.tema01.lista_ejercicios_ficheros;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ejercicio05 {
    
    public static void main(String[] args) {
        // Arrays con los nombres y edades para construir los 10 objetos Alumno
        String[] nombres = {"Ruben", "Antonio", "Jesus", "Alvaro", "Ismael", "Mario", "Candela", "Gabriel", "Natanael", "Matilde"};
        int[] edades = {21, 21, 20, 20, 23, 21, 20, 22, 19, 32};
        try {
            // Abre ObjectOutputStream sobre el fichero binario para serializar objetos completos
            File fichero = new File("src/main/java/com/mycompany/tema01/lista_ejercicios_ficheros/ObjetoAlumno.bin");
            FileOutputStream fos = new FileOutputStream(fichero);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            Alumno a;
            // Crea cada Alumno y lo guarda serializado en el fichero
            for (int i = 0; i < 10; i++) {
                a = new Alumno(nombres[i], edades[i]);
                oos.writeObject(a);
            }
            // Cierra los flujos de salida
            oos.close();
            fos.close();

            // Abre ObjectInputStream para leer y reconstruir los objetos Alumno guardados
            FileInputStream fis = new FileInputStream(fichero);
            ObjectInputStream ois = new ObjectInputStream(fis);
            for (int i = 0; i < 10; i++) {
                // readObject() devuelve Object, por eso se hace casting a (Alumno)
                a = (Alumno)ois.readObject();
                System.out.println(a.toString());
            }

            // Cierra los flujos de entrada
            ois.close();
            fis.close();

        } catch (IOException ioe) {
            // Error de entrada/salida con el fichero
            System.err.println(ioe.getMessage());
        } 
        catch (ClassNotFoundException e) {
            // Error si Java no encuentra la clase Alumno al deserializar
            System.err.println(e.getMessage());
        }
        
    }
    
}
