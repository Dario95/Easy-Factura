/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioneslibres;

import Interfaces.SeleccionarTipoGasto;
import conexionBDD.Conexionn;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author root
 */
public class CargaXml {

    public void cargarXml(String name, String archivo, String cedulaCli) {
        //Se crea un SAXBuilder para poder parsear el archivo
        SAXBuilder builder = new SAXBuilder();

        File xmlFile = new File(name);
        try {
            //Se crea el documento a traves del archivo
            Document document = (Document) builder.build(xmlFile);
            Conexionn cp = new Conexionn();

            // Abre la plantilla
            String elemento;
                        
            ArrayList elementos = new ArrayList();
            
            FileReader f = new FileReader(archivo);
            BufferedReader b = new BufferedReader(f);

            int cont;
            while ((elemento = b.readLine()) != null) {
                elementos.add(elemento);
            }

            //Se obtiene la raiz 'tables'
            Element rootNode = document.getRootElement();

            // Datos sin cabecera
            cont = elementos.indexOf("estado");
            String estado = "";
            if(cont != -1) {
                Element est = (Element) rootNode.getChild(elementos.get(cont).toString());
                if (est != null) {
                    estado = est.getTextTrim();
                }
            }

            cont = elementos.indexOf("ambiente");
            String ambiente = "";
            if(cont != -1) {
                Element amb = (Element) rootNode.getChild(elementos.get(cont).toString());
                if (amb != null) {
                    ambiente = amb.getTextTrim();
                }
            }

            Element tabla = rootNode.getChild("comprobante");

            if (tabla != null) {
                String ex = tabla.getText();

                InputStream stream = new ByteArrayInputStream(ex.getBytes("UTF-8"));
                Document parse = builder.build(stream);

                tabla = parse.getRootElement();
            } else {
                tabla = rootNode;
            }

            List lista_campos = tabla.getChildren();
            Element campo;

            Element tributaria = (Element) lista_campos.get(0);

            // Info Tributaria
            cont = elementos.indexOf("razonSocial");
            String nombreEst = "";
            if(cont != -1) {
                nombreEst = tributaria.getChildTextTrim(elementos.get(cont).toString());
            }
            
            cont = elementos.indexOf("dirMatriz");
            String dirMatriz = "";
            if(cont != -1) {
                dirMatriz = tributaria.getChildTextTrim(elementos.get(cont).toString());
            }
            
            cont = elementos.indexOf("ruc");
            String ruc = "";
            if(cont != -1) {
                ruc = tributaria.getChildTextTrim(elementos.get(cont).toString());
            }
            
            cont = elementos.indexOf("estab");
            String estab = "";
            if(cont != -1) {
                estab = tributaria.getChildTextTrim(elementos.get(cont).toString());
            }
            
            cont = elementos.indexOf("ptoEmi");
            String emision = "";
            if(cont != -1) {
                emision = tributaria.getChildTextTrim(elementos.get(cont).toString());
            }
            
            cont = elementos.indexOf("secuencial");
            String secuencial = "";
            if(cont != -1) {
                secuencial = tributaria.getChildTextTrim(elementos.get(cont).toString());
            }

            String numFact = estab + "-" + emision + "-" + secuencial;

            if (!cp.verificar_usuario("SELECT * FROM ESTABLECIMIENTO WHERE id_establecimiento='" + ruc + "'")) {
                String establecimiento = "INSERT INTO ESTABLECIMIENTO (id_establecimiento,nombre_establecimiento,direccion_establecimiento)"
                        + "VALUES ('" + ruc + "','" + nombreEst + "','" + dirMatriz + "')";
                cp.insertar(establecimiento);
            }

            //Se obtiene la raiz de la factura
            Element factura = (Element) lista_campos.get(1);

            // Info Factura
            cont = elementos.indexOf("fechaEmision");
            String fecha = "";
            if(cont != -1) {
                fecha = factura.getChildTextTrim(elementos.get(cont).toString());
            }
            // String nombreCli = factura.getChildTextTrim(elementos[7]);
            // String cedulaClip = factura.getChildTextTrim(elementos[8]);
            cont = elementos.indexOf("totalSinImpuestos");
            Double totalSinImp = 0.0;
            if(cont != -1) {
                totalSinImp = Double.parseDouble(factura.getChildTextTrim(elementos.get(cont).toString()));
            }

            List totalConImp = factura.getChild("totalConImpuestos").getChildren();
            Element totalImp = (Element) totalConImp.get(0);
            
            cont = elementos.indexOf("valor");
            Double Imps = 0.0;
            if(cont != -1) {
                Imps = Double.parseDouble(totalImp.getChildTextTrim(elementos.get(cont).toString()));
            }

            Double totalConImps = totalSinImp + Imps;

            // Element adicional = (Element) lista_campos.get(3);
            // Info Adicional
            /*List campoAdi = adicional.getChildren();
            Pattern pat;
            Matcher mat;
            String emailCli = "";
            String dirCli = "";

            for (int i = 0; i < campoAdi.size(); i++) {
                Element attr = (Element) campoAdi.get(i);

                pat = Pattern.compile("mail");
                mat = pat.matcher(elementos[11]);
                if (mat.find()) {
                    emailCli = attr.getTextTrim();
                    break;
                }

                pat = Pattern.compile("ireccion");
                mat = pat.matcher(elementos[12]);
                if (mat.find()) {
                    dirCli = attr.getTextTrim();
                    break;
                }
            }*/

 /*String cliente = "INSERT INTO CLIENTE (id_cliente,nombre_cliente)"
                    + "VALUES ('" + cedulaClip + "','" + nombreCli + "')";
            cp.insertar(cliente);*/
            if (!cp.verificar_usuario("SELECT * FROM FACTURA WHERE id_factura='" + numFact + "'")) {
                String facturaQ = "INSERT INTO FACTURA (id_factura,id_cliente,id_establecimiento,fecha_emision,estado_factura,ambiente_factura,total_sin_iva,iva,total_con_iva)"
                        + "VALUES ('" + numFact + "','" + cedulaCli + "','" + ruc + "','" + fecha + "','" + estado + "','" + ambiente + "'," + totalSinImp + "," + Imps + "," + totalConImps + ")";
                cp.insertar(facturaQ);

                Element detalles = (Element) lista_campos.get(2);
                List detalle = detalles.getChildren();

                Object datosProducto[][] = new Object[detalle.size()][3];

                for (int j = 0; j < detalle.size(); j++) {

                    campo = (Element) detalle.get(j);

                    // Detalle
                    cont = elementos.indexOf("descripcion");
                    String descripcion = "";
                    if(cont != -1) {
                        descripcion = campo.getChildTextTrim(elementos.get(cont).toString());
                    }
                    // Double cantidad = Double.parseDouble(campo.getChildTextTrim(elementos[14]));
                    // Double precioUnitario = Double.parseDouble(campo.getChildTextTrim(elementos[15]));
                    cont = elementos.indexOf("precioTotalSinImpuesto");
                    Double total = 0.0;
                    if(cont != -1) {
                        total = Double.parseDouble(campo.getChildTextTrim(elementos.get(cont).toString()));
                    }

                    if (!descripcion.equals("")) {
                        datosProducto[j][0] = descripcion;
                        datosProducto[j][1] = total;
                        datosProducto[j][2] = "";
                    }

                    /*int idProducto;
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
                        + "VALUES (" + idProducto + ",'" + numFact + "'," + total + "," + cantidad + "," + precioUnitario + ")";
                cp.insertar(detalleQ);*/
                }

                if(datosProducto.length != 0) {
                    SeleccionarTipoGasto seleccionar = new SeleccionarTipoGasto(cp, datosProducto, numFact);
                    seleccionar.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Esta factura ya fue ingresada");
            }

        } catch (IOException | JDOMException io) {
            System.out.println(io.getMessage());
        }
    }

    /*public static void main(String args[]) {
        CargaXml car = new CargaXml();
        car.cargarXml("test.xml", "test.txt");
    }*/
}
