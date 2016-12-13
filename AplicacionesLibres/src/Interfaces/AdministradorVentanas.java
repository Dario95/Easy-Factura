package Interfaces;

import aplicacioneslibres.FacturaElectronica;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;

/**
 * importare
 * @author El APRENDIZ www.elaprendiz.net63.net
 */
public class AdministradorVentanas {
    
    private static FacturaElectronica ftc;
    private static FacturaManual ftcM;
    private static RegistroProveedor nuevoProveedor;
   

    public static void mostrarVentanaFactura(JDesktopPane dp) throws IOException
    {
        
        if(ftc != null && !ftc.isShowing())
       {
           ftc.show();
           dp.remove(ftc);
            try{
                dp.add(ftc, JLayeredPane.DEFAULT_LAYER); 
            }catch(IllegalArgumentException ex){               
                dp.add(ftc, JLayeredPane.DEFAULT_LAYER);                
            }    
       }
        
        if(ftc == null)
        {
          ftc = new FacturaElectronica();
          dp.add(ftc, JLayeredPane.DEFAULT_LAYER);
        } 
    }
    
    
    
    public static void mostrarProveedorRegistro(JDesktopPane dp,boolean esNuevo)
    {
         if(nuevoProveedor != null && !nuevoProveedor.isShowing())
       {
           nuevoProveedor.show();
           dp.remove(nuevoProveedor);
            try{
                dp.add(nuevoProveedor, JLayeredPane.DEFAULT_LAYER); 
            }catch(IllegalArgumentException ex){
                dp.add(nuevoProveedor, JLayeredPane.DEFAULT_LAYER);                
            }
           
       }else if(nuevoProveedor != null && nuevoProveedor.isShowing())
       {
       }
         
         
        
        if(nuevoProveedor == null)
        {
          dp.add(nuevoProveedor, JLayeredPane.DEFAULT_LAYER);
        }
        
       
        
    } 
    private static void activarVentana(JDesktopPane dp,JInternalFrame vnt)
    {
        try {
            vnt.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(AdministradorVentanas.class.getName()).log(Level.SEVERE, null, ex);
        }
        dp.setPosition(vnt, 0);
    }
    
    
}
