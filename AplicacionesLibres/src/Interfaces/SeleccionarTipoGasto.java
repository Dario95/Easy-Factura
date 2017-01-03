/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author andreu
 */
public class SeleccionarTipoGasto extends javax.swing.JFrame {

    final JComboBox comboBox;
    JTable tablaProductos;
    String tipoEstado[] = new String[7];
    
    String evtTipo = "";
    int filaTipo = -1;

    /**
     * Creates new form SeleccionarTipoGasto
     *
     * @param tipos
     */
    public SeleccionarTipoGasto(Object[][] tipos) {
        initComponents();

        String nombreCabeceras[] = {"Descripcion", "Precio Total", "Tipo de Gasto"};

        tablaProductos = new JTable(tipos, nombreCabeceras);
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

        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    int opc = comboBox.getSelectedIndex();

                    //evtTipo = ie.getItem().toString();
                    //filaTipo = tablaProductos.getSelectedRow();
                    System.out.print(ie.getItem());
                    System.out.println(" - " + tablaProductos.getSelectedRow());

                    if (ie.getItem().toString().equals("")) {
                        evtTipo = "";
                    } 

                    if (opc != 0) {
                        if (evtTipo.equals("")) {

                            Double total;
                            evtTipo = ie.getItem().toString();
                            filaTipo = tablaProductos.getSelectedRow();

                            switch (opc) {
                                case 1:
                                    total = Double.parseDouble(jTextField1.getText());
                                    total += (Double) tablaProductos.getValueAt(filaTipo, 1);
                                    total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();
                                    jTextField1.setText(String.valueOf(total));
                                    break;
                                case 2:
                                    total = Double.parseDouble(jTextField2.getText());
                                    total += (Double) tablaProductos.getValueAt(filaTipo, 1);
                                    total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();
                                    jTextField2.setText(String.valueOf(total));
                                    break;
                                case 3:
                                    total = Double.parseDouble(jTextField3.getText());
                                    total += (Double) tablaProductos.getValueAt(filaTipo, 1);
                                    total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();
                                    jTextField3.setText(String.valueOf(total));
                                    break;
                                case 4:
                                    total = Double.parseDouble(jTextField4.getText());
                                    total += (Double) tablaProductos.getValueAt(filaTipo, 1);
                                    total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();
                                    jTextField4.setText(String.valueOf(total));
                                    break;
                                case 5:
                                    total = Double.parseDouble(jTextField5.getText());
                                    total += (Double) tablaProductos.getValueAt(filaTipo, 1);
                                    total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();
                                    jTextField5.setText(String.valueOf(total));
                                    break;
                                case 6:
                                    total = Double.parseDouble(jTextField6.getText());
                                    total += (Double) tablaProductos.getValueAt(filaTipo, 1);
                                    total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();
                                    jTextField6.setText(String.valueOf(total));
                                    break;
                                case 7:
                                    total = Double.parseDouble(jTextField7.getText());
                                    total += (Double) tablaProductos.getValueAt(filaTipo, 1);
                                    total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();
                                    jTextField7.setText(String.valueOf(total));
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }
            }
        });

        DefaultTableCellRenderer alinearDerecha = new DefaultTableCellRenderer();
        alinearDerecha.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
        tablaProductos.getColumnModel().getColumn(1).setCellRenderer(alinearDerecha);

        tablaProductos.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(comboBox));

        setLocationRelativeTo(getParent());
        setResizable(false);
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
        jTextField1.setText("0");

        jTextField2.setEditable(false);
        jTextField2.setText("0");

        jTextField3.setEditable(false);
        jTextField3.setText("0");

        jTextField4.setEditable(false);
        jTextField4.setText("0");

        jTextField5.setEditable(false);
        jTextField5.setText("0");

        jTextField6.setEditable(false);
        jTextField6.setText("0");

        jTextField7.setEditable(false);
        jTextField7.setText("0");

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
