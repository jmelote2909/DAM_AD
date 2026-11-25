package com.mycompany.crudneodatis;

class Alumno {

    private String nombre;
    private String apellidos;
    private int codAlumnno;
    private Localidad localidad;
    private Grupo grupo;

    public Alumno(String nombre, String apellidos, int codAlumnno, Localidad localidad, Grupo grupo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.codAlumnno = codAlumnno;
        this.localidad = localidad;
        this.grupo = grupo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public int getCodAlumnno() {
        return codAlumnno;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setNombre(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public void setCodAlumnno(int codAlumnno) {
        this.codAlumnno = codAlumnno;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    @Override
    public String toString() {
        return "Alumno{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", codAlumnno="
                + codAlumnno + ", localidad=" + localidad + ", grupo=" + grupo + '}';
    }

}
