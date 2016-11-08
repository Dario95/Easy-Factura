/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioneslibres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author root
 */
public class Conexion {
    
    public String consultar(String tabla) {
               String cc = "jdbc:postgresql://127.0.0.1/facturas";
               String n="";
              try {
                  Class.forName("org.postgresql.Driver");
                  Connection conexion = DriverManager.getConnection(cc,"aplicaciones","postgres01");
                  Statement comando = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
                  if(tabla.equals("facturas")){                                    
                  String sql =
                     "SELECT count(*) FROM facturas;";
                  ResultSet resultado = comando.executeQuery(sql);
                 
                  while(resultado.next()) {
                      n = resultado.getString("count");
                  }
                  resultado.close();
                  }else{
                  String sql =
                     "SELECT count(*) FROM detalles;";
                  ResultSet resultado = comando.executeQuery(sql);
                 
                  while(resultado.next()) {
                      n = resultado.getString("count");
                  }
                  resultado.close();
                  }
                  
                  
                  
                  comando.close();
                  conexion.close();
              } catch(Exception e) {
                  System.out.println(e.getMessage());
              }
              return n;
          }
    
      public void insertar(String tabla,String parametros) {
               String cc = "jdbc:postgresql://127.0.0.1/facturas";
              try {
                  Class.forName("org.postgresql.Driver");
                  Connection conexion = DriverManager.getConnection(cc,"aplicaciones","postgres01");
                  Statement comando = conexion.createStatement();
                  String sql;
                  if(tabla=="facturas"){
                  sql = "INSERT INTO facturas (idFactura,fecha, nombreCliente,direccion) "
               + "VALUES ("+parametros+"');";
                  System.out.println(sql);
                  }
                  else
                  {
                      sql = "INSERT INTO detalles (idDetalle,idFactura,codigoItem,descripcion,precio,cantidad,descuento) "
               + "VALUES ("+parametros+");";
                  System.out.println(sql);
                  
                  }
                  comando.executeUpdate(sql);
                 
                  comando.close();
                  conexion.close();
              } catch(Exception e) {
                  System.out.println(e.getMessage());
              }
          }
    
    
}
