/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioneslibres;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author andreu
 */
public class CrearPlantilla {

    private final CargarXMLTotal xmlTotal = new CargarXMLTotal();
    private ArrayList arrayTotal;
    private final ArrayList arrayElem = new ArrayList();

    public void generarPlantilla(String factura, String nomFactura) throws FileNotFoundException, IOException {
        cargarElementos();
        arrayTotal = xmlTotal.cargarTodo(factura);

        File archivo = new File("src/Plantillas/" + nomFactura + ".txt");
        File facturas = new File("src/Plantillas/tipoFacturas.txt");
        
        JOptionPane.showMessageDialog(null, archivo.getAbsolutePath());

        StringTokenizer tk;
        String aux, elemento;

        FileReader f = new FileReader(facturas);
        BufferedReader b = new BufferedReader(f);

        while ((elemento = b.readLine()) != null) {
            if(elemento.equals(nomFactura)) {
                JOptionPane.showMessageDialog(null, "Esta plantilla ya fue creada");
                return;
            }
        }

        try {
            FileWriter writeFac = new FileWriter(facturas, true);
            BufferedWriter bw = new BufferedWriter(writeFac);
            bw.write(nomFactura + "\n");
            bw.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }

        try {
            FileWriter escribir = new FileWriter(archivo);

            for (Object elem : arrayElem) {
                for (Object total : arrayTotal) {
                    tk = new StringTokenizer(total.toString(), "-");
                    aux = tk.nextToken();

                    if (aux.equals(elem.toString())) {
                        escribir.write(aux + "\n");
                        break;
                    }
                }
            }

            escribir.close();
            JOptionPane.showMessageDialog(null, "Plantilla Creada Exitosamente");
            //System.out.println("Ya se guardo");
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public void cargarElementos() {
        arrayElem.add("estado");
        arrayElem.add("ambiente");
        arrayElem.add("razonSocial");
        arrayElem.add("dirMatriz");
        arrayElem.add("ruc");
        arrayElem.add("estab");
        arrayElem.add("ptoEmi");
        arrayElem.add("secuencial");
        arrayElem.add("fechaEmision");
        arrayElem.add("totalSinImpuestos");
        arrayElem.add("valor");
        arrayElem.add("descripcion");
        arrayElem.add("precioTotalSinImpuesto");
    }

}
