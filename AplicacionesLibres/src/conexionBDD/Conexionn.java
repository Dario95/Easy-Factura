/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
                    "appfacturacion", "facturacion01");
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

    public ArrayList cargarAnios() {
        ArrayList n = new ArrayList();
        try {
            Statement comando = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultado = comando.executeQuery("SELECT * FROM gastosanualespersonales");
            while (resultado.next()) {
                n.add(resultado.getString("anio_gastos"));
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
            ResultSet resultado = comando.executeQuery("SELECT id_establecimiento, direccion_establecimiento,telefono_establecimiento "
                    + "FROM establecimiento WHERE nombre_establecimiento='" + est + "'");
            while (resultado.next()) {
                n.add(resultado.getString("id_establecimiento"));
                n.add(resultado.getString("direccion_establecimiento"));
                if (resultado.getString("telefono_establecimiento") != null) {
                    n.add(resultado.getString("telefono_establecimiento"));
                }else{
                    n.add("");
                }
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

    public ArrayList ddl(String sql) {
        ArrayList salida = new ArrayList();
        try {
            Statement comando = conexion.createStatement();
            ResultSet resultado = comando.executeQuery(sql);
            ResultSetMetaData mt = resultado.getMetaData();

            if (resultado.next()) {
                for (int i = 1; i <= mt.getColumnCount(); i++) {
                    salida.add(resultado.getString(i));
                }
            }
            resultado.close();
            comando.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return salida;
    }

    public Connection getConn() {
        return conexion;
    }

}
