/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejercicio3_jesusmelendezoteros;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author nocturno
 */
public class Ejercicio3_JesusMelendezOteros {

    private final String conecta = "jdbc:mysql://localhost:3306";
    private final String conectaCole = "jdbc:mysql://localhost:3306/colegio";
    private final String consultasql = "select * from alumnos;";
    private final String usuario = "root";
    private final String clave = "root";

    public static void main(String[] args) {

        Ejercicio3_JesusMelendezOteros r = new Ejercicio3_JesusMelendezOteros();

        r.conecta();

    }

    public void conecta() {
        Connection conexion = null;
        DatabaseMetaData meta = null;

        try {
            conexion = DriverManager.getConnection(conecta, usuario, clave);
            meta = conexion.getMetaData();
            conectaIES();

        } catch (java.sql.SQLException e) {
            e.printStackTrace();

        } finally {
            if (conexion != null)
            try {
                conexion.close();
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void conectaIES() {
        try {
            final Connection conexion = DriverManager.getConnection(conectaCole, usuario, clave);
            final String sentenciaSql = consultasql;
            final PreparedStatement ps = conexion.prepareStatement(sentenciaSql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            final ResultSet rs = ps.executeQuery();
            int cont = 0;
            float notaMayor1 = 0;
            float notaMenor1 = 10;
            String localidadMayor1;
            String localidadMenor1;
            
            float notaMayor2 = 0;
            float notaMenor2 = 10;
            String localidadMayor2;
            String localidadMenor2;
            
            while (rs.next()) {     
                
                if (rs.getString(4).equals("1")) {
                    if (Float.parseFloat(rs.getString(3)) > notaMayor1) {
                        notaMayor1 = Float.parseFloat(rs.getString(3));
                        localidadMayor1 = rs.getString(5);
                    }
                    if (Float.parseFloat(rs.getString(3)) < notaMenor1) {
                        notaMenor1 = Float.parseFloat(rs.getString(3));
                        localidadMenor1 = rs.getString(5);
                    }
                }else if (rs.getString(4).equals("2")) {
                    if (Float.parseFloat(rs.getString(3)) > notaMayor2) {
                        notaMayor2 = Float.parseFloat(rs.getString(3));
                        localidadMayor2 = rs.getString(5);
                    }
                    if (Float.parseFloat(rs.getString(3)) < notaMenor2) {
                        notaMenor2 = Float.parseFloat(rs.getString(3));
                        localidadMenor2 = rs.getString(5);
                    }
                }
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
                System.out.println(String.valueOf(rs.getFloat(3)));
                System.out.println(rs.getString(4));
                System.out.println(rs.getString(5));
                cont++;
            }
            
            System.out.println("Nota mayor grupo 1: " + notaMayor1);
            System.out.println("Nota mayor grupo 2: " + notaMayor2);
            System.out.println("Nota menor grupo 1: " + notaMenor1);
            System.out.println("Nota menor grupo 2: " + notaMenor2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
}
