/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioneslibres;

import conexionBDD.Conexion;
import conexionBDD.Conexionn;
import conexionBDD.Crear;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author root
 */
public class CargaXml {

    public void cargarXml(String name, String archivo) {
        //Se crea un SAXBuilder para poder parsear el archivo
        SAXBuilder builder = new SAXBuilder();

        File xmlFile = new File(name);
        try {
            //Se crea el documento a traves del archivo
            Document document = (Document) builder.build(xmlFile);
            Conexionn cp = new Conexionn();

            // Abre la plantilla
            String elemento;
            String elementos[] = new String[16];
            FileReader f = new FileReader(archivo);
            BufferedReader b = new BufferedReader(f);

            int cont = 0;
            while ((elemento = b.readLine()) != null) {
                elementos[cont++] = elemento;
            }

            //Se obtiene la raiz 'tables'
            Element rootNode = document.getRootElement();

            // Datos sin cabecera
            Element est = (Element) rootNode.getChild(elementos[0]);
            String estado = "";
            if (est != null) {
                estado = est.getTextTrim();
            }

            Element amb = rootNode.getChild(elementos[1]);
            String ambiente = "";
            if (amb != null) {
                ambiente = amb.getTextTrim();
            }

            Element tabla = rootNode.getChild("comprobante");

            if (tabla != null) {
                String ex = tabla.getText();

                InputStream stream = new ByteArrayInputStream(ex.getBytes("UTF-8"));
                Document parse = builder.build(stream);

                tabla = parse.getRootElement();
            }
            else {
                tabla = rootNode; 
            }

            List lista_campos = tabla.getChildren();
            Element campo;

            Element tributaria = (Element) lista_campos.get(0);

            // Info Tributaria
            String nombreEst = tributaria.getChildTextTrim(elementos[2]);
            String dirMatriz = tributaria.getChildTextTrim(elementos[3]);
            String ruc = tributaria.getChildTextTrim(elementos[4]);

            String establecimiento = "INSERT INTO ESTABLECIMIENTO (id_establecimiento,nombre_establecimiento,direccion_establecimiento)"
                    + "VALUES ('" + ruc + "','" + nombreEst + "','" + dirMatriz + "')";
            cp.insertar(establecimiento);

            //Se obtiene la raiz de la factura
            Element factura = (Element) lista_campos.get(1);

            // Info Factura
            String fecha = factura.getChildTextTrim(elementos[5]);
            String nombreCli = factura.getChildTextTrim(elementos[6]);
            String cedulaCli = factura.getChildTextTrim(elementos[7]);
            Double totalSinImp = Double.parseDouble(factura.getChildTextTrim(elementos[8]));

            List totalConImp = factura.getChild("totalConImpuestos").getChildren();
            Element totalImp = (Element) totalConImp.get(0);
            Double Imps = Double.parseDouble(totalImp.getChildTextTrim(elementos[9]));

            Double totalConImps = totalSinImp + Imps;

            Element adicional = (Element) lista_campos.get(3);

            // Info Adicional
            List campoAdi = adicional.getChildren();
            Pattern pat;
            Matcher mat;
            String emailCli = "";
            String dirCli = "";

            for (int i = 0; i < campoAdi.size(); i++) {
                Element attr = (Element) campoAdi.get(i);

                pat = Pattern.compile("mail");
                mat = pat.matcher(elementos[10]);
                if (mat.find()) {
                    emailCli = attr.getTextTrim();
                    break;
                }

                pat = Pattern.compile("ireccion");
                mat = pat.matcher(elementos[10]);
                if (mat.find()) {
                    dirCli = attr.getTextTrim();
                    break;
                }
            }

            String cliente = "INSERT INTO CLIENTE (id_cliente,nombre_cliente,direccion_cliente,email_cliente)"
                    + "VALUES ('" + cedulaCli + "','" + nombreCli + "','" + dirCli + "','" + emailCli + "')";
            cp.insertar(cliente);

            int idFactura;
            if (cp.consultar("FACTURA").equals("")) {
                idFactura = 0;
            } else {
                idFactura = Integer.parseInt(cp.consultar("FACTURA"));
                idFactura++;
            }

            String facturaQ = "INSERT INTO FACTURA (id_factura,id_cliente,id_establecimiento,fecha_emision,estado_factura,ambiente_factura,total_sin_iva,iva,total_con_iva)"
                    + "VALUES (" + idFactura + ",'" + cedulaCli + "','" + ruc + "','" + fecha + "','" + estado + "','" + ambiente + "'," + totalSinImp + "," + Imps + "," + totalConImps + ")";
            cp.insertar(facturaQ);

            Element detalles = (Element) lista_campos.get(2);
            List detalle = detalles.getChildren();

            for (int j = 0; j < detalle.size(); j++) {

                campo = (Element) detalle.get(j);

                // Detalle
                String descripcion = campo.getChildTextTrim(elementos[12]);
                Double cantidad = Double.parseDouble(campo.getChildTextTrim(elementos[13]));
                Double precioUnitario = Double.parseDouble(campo.getChildTextTrim(elementos[14]));
                Double total = Double.parseDouble(campo.getChildTextTrim(elementos[15]));

                int idProducto;
                if (cp.consultar("PRODUCTO").equals("")) {
                    idProducto = 0;
                } else {
                    idProducto = Integer.parseInt(cp.consultar("PRODUCTO"));
                    idProducto++;
                }

                String producto = "INSERT INTO PRODUCTO (id_producto,descripcion_producto)"
                        + "VALUES (" + idProducto + ",'" + descripcion + "')";
                cp.insertar(producto);

                String detalleQ = "INSERT INTO DETALLE (id_producto,id_factura,total,cantidad,precio_unitario)"
                        + "VALUES (" + idProducto + "," + idFactura + "," + total + "," + cantidad + "," + precioUnitario + ")";
                cp.insertar(detalleQ);
            }

        } catch (IOException | JDOMException io) {
            System.out.println(io.getMessage());
        }
    }

    public static void main(String args[]) {
        CargaXml car = new CargaXml();
        car.cargarXml("test.xml", "test.txt");
    }
}
