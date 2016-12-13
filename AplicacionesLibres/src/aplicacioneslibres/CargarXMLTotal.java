/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioneslibres;

import conexionBDD.Conexion;
import conexionBDD.Crear;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author andreu
 */
public class CargarXMLTotal {
    
    private ArrayList elementosTotales = new ArrayList();

    public ArrayList cargarTodo(String name) {
        SAXBuilder builder = new SAXBuilder();
        

        File xmlFile = new File(name);
        try {
            //Se crea el documento a traves del archivo
            Document document = (Document) builder.build(xmlFile);
            
            //Se obtiene la raiz 'tables'
            Element rootNode = document.getRootElement();

            List hijosRoot = rootNode.getChildren();
            for (int i = 0; i < hijosRoot.size() - 2; i++) {
                Element hijoR = (Element) hijosRoot.get(i);
                String aux = hijoR.getName() + "-" + hijoR.getTextTrim();
                elementosTotales.add(aux);
            }

            Element tabla = rootNode.getChild("comprobante");

            String ex = tabla.getText();

            InputStream stream = new ByteArrayInputStream(ex.getBytes("UTF-8"));
            Document parse = builder.build(stream);

            tabla = parse.getRootElement();

            List lista_campos = tabla.getChildren();
            recorrerHijos(lista_campos);

        } catch (IOException | JDOMException io) {
            System.out.println(io.getMessage());
        }
        
        return elementosTotales;
    }

    public void recorrerHijos(List lista) {
        for (int i = 0; i < lista.size(); i++) {
            Element hijoC = (Element) lista.get(i);

            String extra = hijoC.getNamespacePrefix();
            if (!extra.equals("ds") && !extra.equals("etsi")) {
                List lista_hijo = hijoC.getChildren();
                if (lista_hijo.isEmpty()) {
                    String nombre = hijoC.getName();
                    if(nombre.equals("campoAdicional"))
                        nombre = hijoC.getAttributeValue("nombre");
                    String aux2 = nombre + "-" + hijoC.getTextTrim();
                    elementosTotales.add(aux2);
                } else {
                    recorrerHijos(lista_hijo);
                }
            }
        }
    }

    /*public static void main(String args[]) {
        CargarXMLTotal car = new CargarXMLTotal();
        car.cargarTodo("aldo.xml");
    }*/
}
