package com.mycompany.tema01.lista_ejercicios_ficheros;

import java.io.Serializable;

// Implementa Serializable para poder guardar y leer objetos Alumno en ficheros binarios
public class Alumno implements Serializable{
    
    // Atributos del alumno
    private String nombre;
    private int edad;
    
    // Constructor vacío (requerido por varias librerías y para crear objetos sin datos iniciales)
    public Alumno() {
    }

    // Constructor con parámetros para inicializar nombre y edad
    public Alumno(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    // Devuelve el nombre del alumno
    public String getNombre() {
        return nombre;
    }

    // Cambia el nombre del alumno
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Devuelve la edad del alumno
    public int getEdad() {
        return edad;
    }

    // Cambia la edad del alumno
    public void setEdad(int edad) {
        this.edad = edad;
    }

    // Define cómo se muestra el objeto Alumno al convertirlo a texto (por ejemplo, en println)
    @Override
    public String toString() {
        return "Alumno: " + nombre + "  Edad: " + edad;
    }
    
}