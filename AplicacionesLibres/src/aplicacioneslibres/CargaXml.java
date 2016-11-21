/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioneslibres;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author root
 */
public class CargaXml {
    public void cargarXml(String name)
{
    //Se crea un SAXBuilder para poder parsear el archivo
    SAXBuilder builder = new SAXBuilder();
    
    File xmlFile = new File( name);
    try
    {
        //Se crea el documento a traves del archivo
        Document document = (Document) builder.build( xmlFile );
        Conexion cp = new Conexion();
              
        //Se obtiene la raiz 'tables'
        Element rootNode = document.getRootElement();
        System.out.println(rootNode.getChildren("comprobante"));
        //Se obtiene la lista de hijos de la raiz 'comprobante'
        List list = rootNode.getChildren( "comprobante" );
 
        //Se recorre la lista de hijos de 'tables'
        
            //Se obtiene el elemento 'factura'
            Element tabla = (Element) list.get(0);
 
            //Se obtiene el atributo 'nombre' que esta en el tag 'tabla'
           String nombreTabla = tabla.getAttributeValue("id");
 
           System.out.println(nombreTabla);
            //Se obtiene la lista de hijos del tag 'tabla'
            List lista_campos = tabla.getChildren();
 
            System.out.println( "\tNombre\t\tfecha\t\tValor"+lista_campos.size() );
            Element campo = (Element)lista_campos.get(0 );
                lista_campos=campo.getChildren();
        
                //Se obtiene la raiz de la factura
                Element cabecera = (Element)lista_campos.get( 1 );
                
                //Se obtiene los valores de la cabecera
                String fecha = cabecera.getChildTextTrim("fechaEmision");
                String direccionEst = cabecera.getChildTextTrim("dirEstablecimiento");
                String razonSocial = cabecera.getChildTextTrim("razonSocialComprador");
                String cedula = cabecera.getChildTextTrim("identificacionComprador");
                
                if(fecha!=null && razonSocial!=null && direccionEst!=null){
                int idFactura;
                    if(cp.consultar("facturas")==""){
                    idFactura=0;
                }else{
                 idFactura= Integer.parseInt(cp.consultar("facturas"));
                idFactura++;
                }
                String parametro=idFactura+",'"+fecha+"','"+razonSocial+"','"+direccionEst;
                System.out.println(parametro);
                cp.insertar("facturas", parametro);
                }
                //Cabecera: IDFactura,NombreCliente,FechaEmision,Direccion
                //Detalles: Codigo Producto, Descripcion, Cantidad,precio unitario,descuento
                
                Element detalles = (Element)lista_campos.get( 2 );
                List detalle=detalles.getChildren();
                //Se obtienen los valores que estan entre los tags '<campo></campo>'
                //Se obtiene el valor que esta entre los tags '<nombre></nombre>'
                for(int j=0;j<detalle.size();j++){
                //Se obtiene el valor que esta entre los tags '<tipo></tipo>'
                    campo = (Element)detalle.get( j );
                String codigoPrincipal = campo.getChildTextTrim("codigoPrincipal");
                String descripcion = campo.getChildTextTrim("descripcion");
                String cantidad = campo.getChildTextTrim("cantidad");
                String precioUnitario = campo.getChildTextTrim("precioUnitario");
                String descuento = campo.getChildTextTrim("descuento");
                int idDetalle;
                if(cp.consultar("detalles")==""){
                    idDetalle=0;
                }else{
                idDetalle= Integer.parseInt(cp.consultar("detalles"));
                idDetalle++;
                }
                int idFactura= Integer.parseInt(cp.consultar("facturas"));
                String parametro=idDetalle+","+idFactura+",'"+codigoPrincipal+"','"+descripcion+"',"+precioUnitario+","+cantidad+","+descuento;
                System.out.println( parametro);
                cp.insertar("detalles", parametro);
                }
                JOptionPane.showMessageDialog(null, "Ingreso correcto");
                  
        
    }catch ( IOException io ) {
        System.out.println( io.getMessage() );
    }catch ( JDOMException jdomex ) {
        System.out.println( jdomex.getMessage() );
    }
}
}
