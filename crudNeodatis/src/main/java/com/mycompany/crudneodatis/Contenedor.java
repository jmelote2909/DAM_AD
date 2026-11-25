/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crudneodatis;

public class Contenedor<T> {

    private T valor;

    public Contenedor() {
        this.valor = null;
    }

    public T obtenerValor() {
        return valor;
    }

    public void ponerValor(T valor) {
        this.valor = valor;
    }
}
