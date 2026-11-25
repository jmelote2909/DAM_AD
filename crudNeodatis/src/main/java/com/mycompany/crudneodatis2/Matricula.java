/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crudneodatis2;

import java.util.List;

/**
 *
 * @author Usuario
 */
public class Matricula {
    private int codMatricula;
    private String nombre;
    private List<Modulo> modulos;

    public Matricula() {
    }

    public Matricula(int codMatricula, List<Modulo> modulos, String nombre) {
        this.codMatricula = codMatricula;
        this.modulos = modulos;
        this.nombre = nombre;
    }

    public int getCodMatricula() {
        return codMatricula;
    }

    public void setCodMatricula(int codMatricula) {
        this.codMatricula = codMatricula;
    }

    public List<Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(List<Modulo> modulos) {
        this.modulos = modulos;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Matricula{" + "codMatricula=" + codMatricula + ", nombre=" + nombre + ", modulos=" + modulos + '}';
    }
    
}
