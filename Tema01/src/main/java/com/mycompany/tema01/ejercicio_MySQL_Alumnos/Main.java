/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tema01.ejercicio_MySQL_Alumnos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Usuario
 */
public class Main {
    
    public static void main(String[] args) {

        // Parámetros de conexión a la base de datos MySQL
        String url = "jdbc:mysql://localhost:3306/instituto";
        String usuario = "root";
        String password = "root";
        String sql = "SELECT * FROM alumnos";
        
        try {
            // Creación de la conexión a la base de datos
            Connection conexion = DriverManager.getConnection(url, usuario, password);
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            boolean hayAlumnos = false;
            
            // Recorremos fila a fila
            while (rs.next()) {
                hayAlumnos = true;

                // NOTA: Ajusta los nombres de las columnas a los que tengas en tu tabla
                int id = rs.getInt("id_alumno");
                String nombre = rs.getString("nombre_alumno");
                int nota = rs.getInt("nota_media");
                int id_grupo = rs.getInt("id_grupo");

                System.out.println("ID: " + id + " | Nombre: " + nombre + " | Nota: " + nota + " | Id_grupo: " + id_grupo);
            }
            
            if (!hayAlumnos) {
                System.out.println("La tabla 'alumnos' está vacía o no tiene registros.");
            }
            
            conexion.close();
            stmt.close();
            rs.close();
            
        } catch(Exception ex){
            System.err.println(ex.getMessage());
        }
    }
    
}
