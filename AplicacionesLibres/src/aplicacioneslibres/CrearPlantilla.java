/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioneslibres;

import Interfaces.Producto;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author andreu
 */
public class CrearPlantilla {

    private final CargarXMLTotal xmlTotal = new CargarXMLTotal();
    private ArrayList arrayTotal;
    private final ArrayList arrayElem = new ArrayList();

    public void generarPlantilla(String factura, String nomFactura) {
        cargarElementos();
        arrayTotal = xmlTotal.cargarTodo(factura);

        File archivo = new File("src/Plantillas/" + nomFactura + ".txt");
        File facturas = new File("src/Plantillas/tipoFacturas.txt");

        StringTokenizer tk;
        String aux;
        String act;

        Pattern pat;
        Matcher mat;

        try {
            FileWriter writeFac = new FileWriter(facturas, true);
            BufferedWriter bw = new BufferedWriter(writeFac);
            bw.write(nomFactura);
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            FileWriter escribir = new FileWriter(archivo);

            for (Object elem : arrayElem) {
                for (Object total : arrayTotal) {
                    tk = new StringTokenizer(total.toString(), "-");
                    aux = tk.nextToken();
                    act = elem.toString();

                    if (act.equals("mail") || act.equals("ireccion")) {
                        pat = Pattern.compile(act);
                        mat = pat.matcher(aux);
                        if (mat.find()) {
                            escribir.write(aux + "\n");
                            break;
                        }
                    }
                    else if (aux.equals(elem.toString())) {
                        escribir.write(aux + "\n");
                        break;
                    }
                }
            }

            escribir.close();
            //System.out.println("Ya se guardo");
        } catch (IOException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarElementos() {
        arrayElem.add("estado");
        arrayElem.add("ambiente");
        arrayElem.add("razonSocial");
        arrayElem.add("dirMatriz");
        arrayElem.add("ruc");
        arrayElem.add("secuencial");
        arrayElem.add("fechaEmision");
        arrayElem.add("razonSocialComprador");
        arrayElem.add("identificacionComprador");
        arrayElem.add("totalSinImpuestos");
        arrayElem.add("valor");
        arrayElem.add("ireccion");
        arrayElem.add("mail");
        arrayElem.add("descripcion");
        arrayElem.add("cantidad");
        arrayElem.add("precioUnitario");
        arrayElem.add("precioTotalSinImpuesto");
    }

}
