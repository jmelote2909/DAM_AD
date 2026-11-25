/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crudneodatis2;

import com.mycompany.crudneodatis.*;

public class Grupo {

    private String nombre;
    private int codGrupo;

    public Grupo(String nombre, int cod) {
        this.nombre = nombre;
        this.codGrupo = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCodGrupo() {
        return codGrupo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodGrupo(int codGrupo) {
        this.codGrupo = codGrupo;
    }

    @Override
    public String toString() {
        return "Grupo{" + "nombre=" + nombre + ", codGrupo=" + codGrupo + '}';
    }

}
