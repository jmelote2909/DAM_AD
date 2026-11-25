/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crudneodatis2;

public class Localidad {

    private String nombre;
    private String provincia;
    private int codPostal;

    public Localidad(String nombre, String provincia, int codPostal) {
        this.nombre = nombre;
        this.provincia = provincia;
        this.codPostal = codPostal;
    }

    public String getNombre() {
        return nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public int getCodPostal() {
        return codPostal;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setCodPostal(int codPostal) {
        this.codPostal = codPostal;
    }

    @Override
    public String toString() {
        return "Localidad{" + "nombre=" + nombre + ", provincia=" + provincia + ", codPostal="
                + codPostal + '}';
    }
}
