package com.mycompany.crudneodatis2;


class Alumno {

    private String nombre;
    private String apellidos;
    private int codAlumnno;
    private Localidad localidad;
    private Grupo grupo;
    private int edad;
    private Matricula matricula;

    public Alumno(String nombre, String apellidos, int codAlumnno, Localidad localidad, Grupo grupo, int edad, Matricula matricula) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.codAlumnno = codAlumnno;
        this.localidad = localidad;
        this.grupo = grupo;
        this.edad= edad;
        this.matricula = matricula;
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
    
    public int getEdad() {
        return edad;
    }
    
    public Matricula getMatricula() {
        return matricula;
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
    
    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "Alumno{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", codAlumnno="
                + codAlumnno + ", localidad=" + localidad + ", grupo=" + grupo + ", matricula=" + matricula + ", edad=" + edad + '}';
    }

}