/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crudneodatis2;

/**
 *
 * @author Usuario
 */
public class Modulo {
    private int codModulo;
    private String nombre;

    public Modulo() {
    }

    public Modulo(int codModulo, String nombre) {
        this.codModulo = codModulo;
        this.nombre = nombre;
    }

    public int getCodModulo() {
        return codModulo;
    }

    public void setCodModulo(int codModulo) {
        this.codModulo = codModulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Modulo{" + "codModulo=" + codModulo + ", nombre=" + nombre + '}';
    }
}
