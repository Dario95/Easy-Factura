/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioneslibres;

import conexionBDD.Conexion;
import conexionBDD.Crear;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author root
 */
public class CargaXml {

    public void cargarXml(String name) {
        //Se crea un SAXBuilder para poder parsear el archivo
        SAXBuilder builder = new SAXBuilder();

        File xmlFile = new File(name);
        try {
            //Se crea el documento a traves del archivo
            Document document = (Document) builder.build(xmlFile);
            Crear cr = new Crear();
            Connection con = cr.crearConexion();
            Conexion cp = new Conexion();

            //Se obtiene la raiz 'tables'
            Element rootNode = document.getRootElement();

            // Datos sin cabecera
            Element est = (Element) rootNode.getChild("estado");
            String estado = "";
            if (est != null) {
                estado = est.getTextTrim();
            }

            Element amb = rootNode.getChild("ambiente");
            String ambiente = "";
            if (amb != null) {
                ambiente = amb.getTextTrim();
            }

            //Se obtiene la lista de hijos de la raiz 'comprobante'
            List list = rootNode.getChildren();
            //Se recorre la lista de hijos de 'tables'
            //Se obtiene el elemento 'factura'
            Element tabla = rootNode.getChild("comprobante").getChild("factura");
            //String aux = rootNode.getChild("comprobante").getTextTrim();
            //System.out.println(aux);
            //Element tabla = (Element) list.get(0);
            //System.out.println(tabla.getName());

            List lista_campos = tabla.getChildren();
            Element campo;

            Element tributaria = (Element) lista_campos.get(0);

            // Info Tributaria
            String nombreEst = tributaria.getChildTextTrim("razonSocial");
            String dirMatriz = tributaria.getChildTextTrim("dirMatriz");
            String ruc = tributaria.getChildTextTrim("ruc");

            String establecimiento = "INSERT INTO ESTABLECIMIENTO (id_establecimiento,nombre_establecimiento,direccion_establecimiento)"
                    + "VALUES ('" + ruc + "','" + nombreEst + "','" + dirMatriz + "')";
            cp.insertar(con, establecimiento);

            //Se obtiene la raiz de la factura
            Element factura = (Element) lista_campos.get(1);

            // Info Factura
            String fecha = factura.getChildTextTrim("fechaEmision");
            String nombreCli = factura.getChildTextTrim("razonSocialComprador");
            String cedulaCli = factura.getChildTextTrim("identificacionComprador");
            String totalSinImp = factura.getChildTextTrim("totalSinImpuestos");

            List totalConImp = factura.getChild("totalConImpuestos").getChildren();
            Element totalImp = (Element) totalConImp.get(0);
            String totalConImps = totalImp.getChildTextTrim("valor");

            Element adicional = (Element) lista_campos.get(3);

            // Info Adicional
            List campoAdi = adicional.getChildren();
            Pattern pat;
            Matcher mat;
            String emailCli = "";
            String dirCli = "";

            for (int i = 0; i < campoAdi.size(); i++) {
                Element attr = (Element) campoAdi.get(i);
                String actual = attr.getAttributeValue("nombre");

                pat = Pattern.compile("mail");
                mat = pat.matcher(actual);
                if (mat.find()) {
                    emailCli = attr.getTextTrim();
                    break;
                }

                pat = Pattern.compile("ireccion");
                mat = pat.matcher(actual);
                if (mat.find()) {
                    dirCli = attr.getTextTrim();
                    break;
                }
            }

            String cliente = "INSERT INTO CLIENTE (id_cliente,nombre_cliente,direccion_cliente,email_cliente)"
                    + "VALUES ('" + cedulaCli + "','" + nombreCli + "','" + dirCli + "','" + emailCli + "')";
            cp.insertar(con, cliente);

            /*if (fecha != null && razonSocial != null && direccionEst != null) {
                
                String parametro = idFactura + ",'" + fecha + "','" + razonSocial + "','" + direccionEst;
                System.out.println(parametro);
                cp.insertar("facturas", parametro);
            }*/
            //Cabecera: IDFactura,NombreCliente,FechaEmision,Direccion
            //Detalles: Codigo Producto, Descripcion, Cantidad,precio unitario,descuento
            int idFactura;
            if (cp.consultar(con, "FACTURA") == "") {
                idFactura = 0;
            } else {
                idFactura = Integer.parseInt(cp.consultar(con, "FACTURA"));
                idFactura++;
            }

            String facturaQ = "INSERT INTO FACTURA (id_factura,id_cliente,id_establecimiento,fecha_emision,estado_factura,ambiente_factura,total_sin_iva,total_con_iva)"
                    + "VALUES (" + idFactura + ",'" + cedulaCli + "','" + ruc + "','" + fecha + "','" + estado + "','" + ambiente + "','" + totalSinImp + "','" + totalConImps + "')";
            cp.insertar(con, facturaQ);

            Element detalles = (Element) lista_campos.get(2);
            List detalle = detalles.getChildren();

            for (int j = 0; j < detalle.size(); j++) {

                campo = (Element) detalle.get(j);

                // Detalle
                String descripcion = campo.getChildTextTrim("descripcion");
                Double cantidad = Double.parseDouble(campo.getChildTextTrim("cantidad"));
                Double precioUnitario = Double.parseDouble(campo.getChildTextTrim("precioUnitario"));
                Double total = Double.parseDouble(campo.getChildTextTrim("precioTotalSinImpuesto"));

                int idDetalle;
                if (cp.consultar(con, "DETALLE") == "") {
                    idDetalle = 0;
                } else {
                    idDetalle = Integer.parseInt(cp.consultar(con, "DETALLE"));
                    idDetalle++;
                }

                int idProducto;
                if (cp.consultar(con, "PRODUCTO") == "") {
                    idProducto = 0;
                } else {
                    idProducto = Integer.parseInt(cp.consultar(con, "PRODUCTO"));
                    idProducto++;
                }

                String producto = "INSERT INTO PRODUCTO (id_producto,descripcion_producto,precio_unitario)"
                        + "VALUES (" + idProducto + ",'" + descripcion + "'," + precioUnitario + ")";
                cp.insertar(con, producto);

                String detalleQ = "INSERT INTO DETALLE (id_detalle,id_producto,id_factura,total,cantidad)"
                        + "VALUES (" + idDetalle + "," + idProducto + "," + idFactura + "," + total + "," + cantidad + ")";
                cp.insertar(con, detalleQ);
                /*int idFactura = Integer.parseInt(cp.consultar("facturas"));
                String parametro = idDetalle + "," + idFactura + ",'" + codigoPrincipal + "','" + descripcion + "'," + precioUnitario + "," + cantidad + "," + descuento;
                System.out.println(parametro);
                cp.insertar("detalles", parametro);*/
            }

            //System.out.println("Email: " + emailCli);
        } catch (IOException | JDOMException io) {
            System.out.println(io.getMessage());
        }
    }

    /*public static void main(String args[]) {
        CargaXml car = new CargaXml();
        car.cargarXml("aldo.xml");
    }*/
}
