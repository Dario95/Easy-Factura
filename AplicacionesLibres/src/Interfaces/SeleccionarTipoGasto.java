/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import conexionBDD.Conexionn;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author andreu
 */
public class SeleccionarTipoGasto extends javax.swing.JFrame {

    final JComboBox comboBox;
    JTable tablaProductos;
    String tipoEstado[];

    String evtTipo = "";
    int filaTipo = -1;

    Conexionn conTipo;
    String numFac;
    int anio;
    String cedula;

    /**
     * Creates new form SeleccionarTipoGasto
     *
     * @param conn
     * @param tipos
     * @param factura
     * @param anio
     * @param cedula
     */
    public SeleccionarTipoGasto(Conexionn conn, Object[][] tipos, String factura, int anio, String cedula) {
        initComponents();
        this.conTipo = conn;
        this.numFac = factura;
        this.anio = anio;
        this.cedula = cedula;

        String nombreCabeceras[] = {"Descripcion", "Precio Total", "Tipo de Gasto"};
        
        tipoEstado = new String[tipos.length];
        for (int i=0; i<tipos.length; i++) {
            tipoEstado[i] = "";
        }

        tablaProductos = new JTable(tipos, nombreCabeceras) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
        };
        jScrollPane1.setViewportView(tablaProductos);

        comboBox = new JComboBox();
        comboBox.addItem("");
        comboBox.addItem("Vivienda");
        comboBox.addItem("Salud");
        comboBox.addItem("Educacion");
        comboBox.addItem("Alimentacion");
        comboBox.addItem("Vestimenta");
        comboBox.addItem("Negocio");
        comboBox.addItem("Otro");

        DefaultTableCellRenderer alinearDerecha = new DefaultTableCellRenderer();
        alinearDerecha.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
        tablaProductos.getColumnModel().getColumn(1).setCellRenderer(alinearDerecha);

        tablaProductos.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(comboBox));

        tablaProductos.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent tme) {
                int row = tme.getFirstRow();
                int column = tme.getColumn();

                TableModel model = (TableModel) tme.getSource();
                Object data = model.getValueAt(row, column);

                if (!data.equals("") && column == 2) {
                    //int opc = comboBox.getSelectedIndex();
                    //System.out.println(row);
                    double total;

                    if (!tipoEstado[row].equals("")) {
                        if (tipoEstado[row].equals("Vivienda")) {
                            total = Double.parseDouble(jTextField1.getText());
                            total -= (Double) tablaProductos.getValueAt(row, 1);
                            total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();
                            jTextField1.setText(String.valueOf(total));
                        }
                        if (tipoEstado[row].equals("Salud")) {
                            total = Double.parseDouble(jTextField2.getText());
                            total -= (Double) tablaProductos.getValueAt(row, 1);
                            total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();
                            jTextField2.setText(String.valueOf(total));
                        }
                        if (tipoEstado[row].equals("Educacion")) {
                            total = Double.parseDouble(jTextField3.getText());
                            total -= (Double) tablaProductos.getValueAt(row, 1);
                            total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();
                            jTextField3.setText(String.valueOf(total));
                        }
                        if (tipoEstado[row].equals("Alimentacion")) {
                            total = Double.parseDouble(jTextField4.getText());
                            total -= (Double) tablaProductos.getValueAt(row, 1);
                            total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();
                            jTextField4.setText(String.valueOf(total));
                        }
                        if (tipoEstado[row].equals("Vestimenta")) {
                            total = Double.parseDouble(jTextField5.getText());
                            total -= (Double) tablaProductos.getValueAt(row, 1);
                            total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();
                            jTextField5.setText(String.valueOf(total));
                        }
                        if (tipoEstado[row].equals("Negocio")) {
                            total = Double.parseDouble(jTextField6.getText());
                            total -= (Double) tablaProductos.getValueAt(row, 1);
                            total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();
                            jTextField6.setText(String.valueOf(total));
                        }
                        if (tipoEstado[row].equals("Otro")) {
                            total = Double.parseDouble(jTextField7.getText());
                            total -= (Double) tablaProductos.getValueAt(row, 1);
                            total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();
                            jTextField7.setText(String.valueOf(total));
                        }
                    }

                    if (data.equals("Vivienda")) {
                        total = Double.parseDouble(jTextField1.getText());
                        total += (Double) tablaProductos.getValueAt(row, 1);
                        total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();
                        jTextField1.setText(String.valueOf(total));
                        tipoEstado[row] = "Vivienda";
                    }
                    if (data.equals("Salud")) {
                        total = Double.parseDouble(jTextField2.getText());
                        total += (Double) tablaProductos.getValueAt(row, 1);
                        total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();
                        jTextField2.setText(String.valueOf(total));
                        tipoEstado[row] = "Salud";
                    }
                    if (data.equals("Educacion")) {
                        total = Double.parseDouble(jTextField3.getText());
                        total += (Double) tablaProductos.getValueAt(row, 1);
                        total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();
                        jTextField3.setText(String.valueOf(total));
                        tipoEstado[row] = "Educacion";
                    }
                    if (data.equals("Alimentacion")) {
                        total = Double.parseDouble(jTextField4.getText());
                        total += (Double) tablaProductos.getValueAt(row, 1);
                        total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();
                        jTextField4.setText(String.valueOf(total));
                        tipoEstado[row] = "Alimentacion";
                    }
                    if (data.equals("Vestimenta")) {
                        total = Double.parseDouble(jTextField5.getText());
                        total += (Double) tablaProductos.getValueAt(row, 1);
                        total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();
                        jTextField5.setText(String.valueOf(total));
                        tipoEstado[row] = "Vestimenta";
                    }
                    if (data.equals("Negocio")) {
                        total = Double.parseDouble(jTextField6.getText());
                        total += (Double) tablaProductos.getValueAt(row, 1);
                        total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();
                        jTextField6.setText(String.valueOf(total));
                        tipoEstado[row] = "Negocio";
                    }
                    if (data.equals("Otro")) {
                        total = Double.parseDouble(jTextField7.getText());
                        total += (Double) tablaProductos.getValueAt(row, 1);
                        total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();
                        jTextField7.setText(String.valueOf(total));
                        tipoEstado[row] = "Otro";
                    }
                }

            }
        });

        setLocationRelativeTo(getParent());
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("SELECCIONAR TIPO DE GASTO");

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Vivienda");

        jLabel3.setText("Salud");

        jLabel4.setText("Educacion");

        jLabel5.setText("Alimentacion");

        jTextField1.setEditable(false);
        jTextField1.setText("0.0");

        jTextField2.setEditable(false);
        jTextField2.setText("0.0");

        jTextField3.setEditable(false);
        jTextField3.setText("0.0");

        jTextField4.setEditable(false);
        jTextField4.setText("0.0");

        jTextField5.setEditable(false);
        jTextField5.setText("0.0");

        jTextField6.setEditable(false);
        jTextField6.setText("0.0");

        jTextField7.setEditable(false);
        jTextField7.setText("0.0");

        jLabel6.setText("Vestimenta");

        jLabel7.setText("Negocio");

        jLabel8.setText("Otro");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                    .addComponent(jTextField2)
                                    .addComponent(jTextField3)
                                    .addComponent(jTextField4))
                                .addGap(81, 81, 81)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING))))
                        .addGap(0, 175, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int filasTotales = tablaProductos.getRowCount();
        boolean validado = true;

        for (int i = 0; i < filasTotales; i++) {
            if (tablaProductos.getValueAt(i, 2).equals("")) {
                validado = false;
                break;
            }
        }

        if (validado == true) {
            String query = "";
            Double total;
            
            double totales[] = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};

            if (!jTextField1.getText().equals("0.0")) {
                total = Double.parseDouble(jTextField1.getText());
                total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();

                query = "INSERT INTO TIPO_GASTO (id_factura,tipo,total)"
                        + "VALUES('" + numFac + "','" + jLabel2.getText() + "'," + total + ")";

                totales[0] = total;
                conTipo.insertar(query);
            }
            if (!jTextField2.getText().equals("0.0")) {
                total = Double.parseDouble(jTextField2.getText());
                total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();

                query = "INSERT INTO TIPO_GASTO (id_factura,tipo,total)"
                        + "VALUES('" + numFac + "','" + jLabel3.getText() + "'," + total + ")";

                totales[1] = total;
                conTipo.insertar(query);
            }
            if (!jTextField3.getText().equals("0.0")) {
                total = Double.parseDouble(jTextField3.getText());
                total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();

                query = "INSERT INTO TIPO_GASTO (id_factura,tipo,total)"
                        + "VALUES('" + numFac + "','" + jLabel4.getText() + "'," + total + ")";

                totales[2] = total;
                conTipo.insertar(query);
            }
            if (!jTextField4.getText().equals("0.0")) {
                total = Double.parseDouble(jTextField4.getText());
                total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();

                query = "INSERT INTO TIPO_GASTO (id_factura,tipo,total)"
                        + "VALUES('" + numFac + "','" + jLabel5.getText() + "'," + total + ")";

                totales[3] = total;
                conTipo.insertar(query);
            }
            if (!jTextField5.getText().equals("0.0")) {
                total = Double.parseDouble(jTextField5.getText());
                total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();

                query = "INSERT INTO TIPO_GASTO (id_factura,tipo,total)"
                        + "VALUES('" + numFac + "','" + jLabel6.getText() + "'," + total + ")";

                totales[4] = total;
                conTipo.insertar(query);
            }
            if (!jTextField6.getText().equals("0.0")) {
                total = Double.parseDouble(jTextField6.getText());
                total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();

                query = "INSERT INTO TIPO_GASTO (id_factura,tipo,total)"
                        + "VALUES('" + numFac + "','" + jLabel7.getText() + "'," + total + ")";

                totales[5] = total;
                conTipo.insertar(query);
            }
            if (!jTextField7.getText().equals("0.0")) {
                total = Double.parseDouble(jTextField7.getText());
                total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();

                query = "INSERT INTO TIPO_GASTO (id_factura,tipo,total)"
                        + "VALUES('" + numFac + "','" + jLabel8.getText() + "'," + total + ")";

                totales[6] = total;
                conTipo.insertar(query);
            }
            
            if (conTipo.verificar_usuario("SELECT * FROM HISTORIAL_PAGOS WHERE anio_historial=" + anio + "")) {
                query = "UPDATE HISTORIAL_PAGOS SET total_alimentacion=total_alimentacion+"+totales[3]+"::money,"
                        + "total_salud=total_salud+"+totales[1]+"::money,"
                        + "total_vivienda=total_vivienda+"+totales[0]+"::money,"
                        + "total_educacion=total_educacion+"+totales[2]+"::money,"
                        + "total_vestimenta=total_vestimenta+"+totales[4]+"::money,"
                        + "total_negocios=total_negocios+"+totales[5]+"::money,"
                        + "total_otros=total_otros+"+totales[6]+"::money WHERE anio_historial="+anio+" AND id_cliente='"+cedula+"'";
            } else {
                query = "INSERT INTO HISTORIAL_PAGOS VALUES ("+anio+",'"+cedula+"',"+totales[3]+","+totales[1]+","+totales[0]+","+totales[2]+","+totales[4]+","+totales[5]+","+totales[6]+")";
            }
            
            conTipo.insertar(query);

            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado el tipo para cada producto");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SeleccionarTipoGasto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SeleccionarTipoGasto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SeleccionarTipoGasto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SeleccionarTipoGasto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // new SeleccionarTipoGasto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}
