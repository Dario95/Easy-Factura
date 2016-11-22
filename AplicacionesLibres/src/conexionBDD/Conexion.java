/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionBDD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author root
 */
public class Conexion {

    public String consultar(Connection con, String tabla) {

        String n = "";
        try {
            Statement comando = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

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

    public void insertar(Connection con, String sql) {
        try {
            Statement comando = con.createStatement();
            //sql = "INSERT INTO facturas (idFactura,fecha, nombreCliente,direccion) "
            //+ "VALUES (" + parametros + "');";
            comando.executeUpdate(sql);

            comando.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
