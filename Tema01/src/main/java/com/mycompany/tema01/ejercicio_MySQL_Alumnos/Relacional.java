/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tema01.ejercicio_MySQL_Alumnos;

import java.sql.*;
import java.awt.event.*;

/**
 *
 * @author Usuario
 */
public class Relacional {

    private final String conecta = "jdbc:mysql://localhost:3306";
    private final String conectaIES = "jdbc:mysql://localhost:3306/instituto";
    private final String consultasql = "select * from alumnos;";
    private final String usuario = "root";
    private final String clave = "root";

    public static void main(String[] args) {

        Relacional r = new Relacional();

        r.conecta();

    }

    public void conecta() {
        Connection conexion = null;
        DatabaseMetaData meta = null;

        MuestraMeta muestraMeta = new MuestraMeta();
        try {
            conexion = DriverManager.getConnection(conecta, usuario, clave);
            meta = conexion.getMetaData();
            muestraMeta.setVisible(true);
            muestraMeta.setTitle("Visualización de Metadatos");
            muestraMeta.setNombreSGBD(meta.getDatabaseProductName());
            muestraMeta.setNombreDriver(meta.getDriverName());
            muestraMeta.setVersionSGBD(meta.getDatabaseProductVersion());
            muestraMeta.setVersionDriver(meta.getDriverVersion());
            muestraMeta.setNombreUsuario(meta.getUserName());
            muestraMeta.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    conectaIES();
                }
            });

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
            final Connection conexion = DriverManager.getConnection(conectaIES, usuario, clave);
            final String sentenciaSql = "select * from alumnos;";
            final PreparedStatement ps = conexion.prepareStatement(sentenciaSql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            final ResultSet rs = ps.executeQuery();
            MuestraAlumnos muestraAlumnos = new MuestraAlumnos();
            muestraAlumnos.setResultSet(rs);
            muestraAlumnos.setTitle("Visor de Alumnos");
            muestraAlumnos.setVisible(true);
            if (rs.first()) {
                muestraAlumnos.mostrarRegistro(rs);
            }
            muestraAlumnos.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    try {
                        rs.close();
                        ps.close();
                        conexion.close();
                        System.out.println("Conexión cerrada correctamente.");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
