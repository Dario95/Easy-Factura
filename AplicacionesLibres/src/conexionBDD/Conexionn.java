/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author vengatus
 */
public class Conexionn {

    Connection conexion;

    public Conexionn() {
        //conexion=null;
        try {

            conexion = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/facturas",
                    "aplicaciones", "postgres01");

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }

    }

    public ArrayList cargarEstablecimiento() {
        ArrayList n = new ArrayList();
        try {
            Statement comando = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultado = comando.executeQuery("SELECT nombre_establecimiento FROM establecimiento");
            while (resultado.next()) {
                n.add(resultado.getString("nombre_establecimiento"));
            }
            resultado.close();
            comando.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return n;
    }

    public ArrayList cambiarDatosEstablecimiento(String est) {
        ArrayList n = new ArrayList();
        try {
            Statement comando = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultado = comando.executeQuery("SELECT id_establecimiento, direccion_establecimiento "
                    + "FROM establecimiento WHERE nombre_establecimiento='" + est + "'");
            while (resultado.next()) {
                n.add(resultado.getString("id_establecimiento"));
                n.add(resultado.getString("direccion_establecimiento"));
            }
            resultado.close();
            comando.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return n;
    }
    
    public ArrayList obtenerHistorial(String cedula, int anio) {
        ArrayList n = new ArrayList();
        try {
            Statement comando = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultado = comando.executeQuery("SELECT total_alimentacion,"
                    + "total_salud, total_vivienda, total_educacion, total_vestimenta,"
                    + "total_negocios, total_otros FROM historial_pagos "
                    + "WHERE anio_historial="+anio+" AND id_cliente='"+cedula+"'");
            while (resultado.next()) {
                n.add(transformar(resultado.getString("total_alimentacion")));
                n.add(transformar(resultado.getString("total_salud")));
                n.add(transformar(resultado.getString("total_vivienda")));
                n.add(transformar(resultado.getString("total_educacion")));
                n.add(transformar(resultado.getString("total_vestimenta")));
                n.add(transformar(resultado.getString("total_negocios")));
                n.add(transformar(resultado.getString("total_otros")));
            }
            resultado.close();
            comando.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return n;
    }

    public String consultar(String tabla) {
        String n = "";
        try {
            Statement comando = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT count(*) FROM " + tabla + ";";
            ResultSet resultado = comando.executeQuery(sql);
            while (resultado.next()) {
                n = resultado.getString("count");
            }
            resultado.close();
            comando.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return n;
    }

    public void insertar(String sql) {
        try {
            Statement comando = conexion.createStatement();
            comando.executeUpdate(sql);
            comando.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean verificar_usuario(String sql) {
        boolean val = false;
        try {
            Statement comando = conexion.createStatement();
            ResultSet resultado = comando.executeQuery(sql);
            val = resultado.next();
            resultado.close();
            comando.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return val;
    }
    
    public String transformar (String num) {
        StringTokenizer token = new StringTokenizer(num, ",");
        String aux;
        
        aux = token.nextToken() + "." + token.nextToken();
        return aux;
    }

}
