/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import conexionBDD.Conexionn;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
    String cedula, tipo;

    /**
     * Creates new form SeleccionarTipoGasto
     *
     * @param conn
     * @param tipos
     * @param factura
     * @param anio
     * @param cedula
     * @param tipo
     */
    public SeleccionarTipoGasto(Conexionn conn, Object[][] tipos, String factura, int anio, String cedula, String tipo) {
        initComponents();
        this.conTipo = conn;
        this.numFac = factura;
        this.anio = anio;
        this.cedula = cedula;
        this.tipo = tipo;

        String nombreCabeceras[] = {"Descripcion", "Precio Total", "Tipo de Gasto"};

        tipoEstado = new String[tipos.length];
        for (int i = 0; i < tipos.length; i++) {
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

        if (tipo.equals("Personal")) {
            comboBox.addItem("Vivienda");
            comboBox.addItem("Salud");
            comboBox.addItem("Educacion");
            comboBox.addItem("Alimentacion");
            comboBox.addItem("Vestimenta");
            comboBox.addItem("Otro");
                  
            lblMercaderia.setVisible(false);
            txtMercaderia.setVisible(false);
            lblArriendo.setVisible(false);
            txtArriendo.setVisible(false);
            lblServicios.setVisible(false);
            txtServicios.setVisible(false);
            lblSueldos.setVisible(false);
            txtSueldos.setVisible(false);
            lblMovilizacion.setVisible(false);
            txtMovilizacion.setVisible(false);
            lblViaticos.setVisible(false);
            txtViaticos.setVisible(false);
            lblCapacitacion.setVisible(false);
            txtCapacitacion.setVisible(false);
            lblSuministros.setVisible(false);
            txtSuministros.setVisible(false);
            lblHerramientas.setVisible(false);
            txtHerramientas.setVisible(false);
            
            
            
            pack();

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

                        if (!tipoEstado[row].equals("")) {
                            if (tipoEstado[row].equals("Vivienda")) {
                                restarAgregado(txtVivienda, row);
                            }
                            if (tipoEstado[row].equals("Salud")) {
                                restarAgregado(txtSalud, row);
                            }
                            if (tipoEstado[row].equals("Educacion")) {
                                restarAgregado(txtEducacion, row);
                            }
                            if (tipoEstado[row].equals("Alimentacion")) {
                                restarAgregado(txtAlimentacion, row);
                            }
                            if (tipoEstado[row].equals("Vestimenta")) {
                                restarAgregado(txtVestimenta, row);
                            }
                            if (tipoEstado[row].equals("Otro")) {
                                restarAgregado(txtOtro, row);
                            }
                        }

                        if (data.equals("Vivienda")) {
                            sumarAgregado(txtVivienda, row, "Vivienda");
                        }
                        if (data.equals("Salud")) {
                            sumarAgregado(txtSalud, row, "Salud");
                        }
                        if (data.equals("Educacion")) {
                            sumarAgregado(txtEducacion, row, "Educacion");
                        }
                        if (data.equals("Alimentacion")) {
                            sumarAgregado(txtAlimentacion, row, "Alimentacion");
                        }
                        if (data.equals("Vestimenta")) {
                            sumarAgregado(txtVestimenta, row, "Vestimenta");
                        }
                        if (data.equals("Otro")) {
                            sumarAgregado(txtOtro, row, "Otro");
                        }
                    }

                }
            });
        } else {
            comboBox.addItem("Mercaderia");
            comboBox.addItem("Arriendo");
            comboBox.addItem("Servicios Basicos");
            comboBox.addItem("Sueldos");
            comboBox.addItem("Movilizacion");
            comboBox.addItem("Viaticos");
            comboBox.addItem("Capacitacion");
            comboBox.addItem("Suministros");
            comboBox.addItem("Herramientas");
            
            lblVivienda.setVisible(false);
            txtVivienda.setVisible(false);
            lblSalud.setVisible(false);
            txtSalud.setVisible(false);
            lblEducacion.setVisible(false);
            txtEducacion.setVisible(false);
            lblAlimentacion.setVisible(false);
            txtAlimentacion.setVisible(false);
            lblVestimenta.setVisible(false);
            txtVestimenta.setVisible(false);
            lblOtro.setVisible(false);
            txtOtro.setVisible(false);
            
            pack();

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

                        if (!tipoEstado[row].equals("")) {
                            if (tipoEstado[row].equals("Mercaderia")) {
                                restarAgregado(txtVivienda, row);
                            }
                            if (tipoEstado[row].equals("Arriendo")) {
                                restarAgregado(txtSalud, row);
                            }
                            if (tipoEstado[row].equals("Servicios Basicos")) {
                                restarAgregado(txtEducacion, row);
                            }
                            if (tipoEstado[row].equals("Sueldos")) {
                                restarAgregado(txtAlimentacion, row);
                            }
                            if (tipoEstado[row].equals("Movilizacion")) {
                                restarAgregado(txtVestimenta, row);
                            }
                            if (tipoEstado[row].equals("Viaticos")) {
                                restarAgregado(txtOtro, row);
                            }
                            if (tipoEstado[row].equals("Capacitacion")) {
                                restarAgregado(txtOtro, row);
                            }
                            if (tipoEstado[row].equals("Suministros")) {
                                restarAgregado(txtOtro, row);
                            }
                            if (tipoEstado[row].equals("Herramientas")) {
                                restarAgregado(txtOtro, row);
                            }
                        }

                        if (data.equals("Mercaderia")) {
                            sumarAgregado(txtVivienda, row, "Mercaderia");
                        }
                        if (data.equals("Arriendo")) {
                            sumarAgregado(txtSalud, row, "Arriendo");
                        }
                        if (data.equals("Servicios Basicos")) {
                            sumarAgregado(txtEducacion, row, "Servicios Basicos");
                        }
                        if (data.equals("Sueldos")) {
                            sumarAgregado(txtAlimentacion, row, "Sueldos");
                        }
                        if (data.equals("Movilizacion")) {
                            sumarAgregado(txtVestimenta, row, "Movilizacion");
                        }
                        if (data.equals("Viaticos")) {
                            sumarAgregado(txtOtro, row, "Viaticos");
                        }
                        if (data.equals("Capacitacion")) {
                            sumarAgregado(txtOtro, row, "Capacitacion");
                        }
                        if (data.equals("Suministros")) {
                            sumarAgregado(txtOtro, row, "Suministros");
                        }
                        if (data.equals("Herramientas")) {
                            sumarAgregado(txtOtro, row, "Herramientas");
                        }
                    }

                }
            });
        }

        DefaultTableCellRenderer alinearDerecha = new DefaultTableCellRenderer();
        alinearDerecha.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
        tablaProductos.getColumnModel().getColumn(1).setCellRenderer(alinearDerecha);

        tablaProductos.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(comboBox));

        tablaProductos.getColumnModel().getColumn(1).setMinWidth(100);
        tablaProductos.getColumnModel().getColumn(1).setMaxWidth(100);
        tablaProductos.getColumnModel().getColumn(2).setMinWidth(150);
        tablaProductos.getColumnModel().getColumn(2).setMaxWidth(150);

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
        lblVivienda = new javax.swing.JLabel();
        lblSalud = new javax.swing.JLabel();
        lblEducacion = new javax.swing.JLabel();
        lblAlimentacion = new javax.swing.JLabel();
        txtVivienda = new javax.swing.JTextField();
        txtSalud = new javax.swing.JTextField();
        txtEducacion = new javax.swing.JTextField();
        txtAlimentacion = new javax.swing.JTextField();
        txtVestimenta = new javax.swing.JTextField();
        txtOtro = new javax.swing.JTextField();
        lblVestimenta = new javax.swing.JLabel();
        lblOtro = new javax.swing.JLabel();
        lblMercaderia = new javax.swing.JLabel();
        txtMercaderia = new javax.swing.JTextField();
        lblArriendo = new javax.swing.JLabel();
        txtArriendo = new javax.swing.JTextField();
        lblServicios = new javax.swing.JLabel();
        txtServicios = new javax.swing.JTextField();
        lblSueldos = new javax.swing.JLabel();
        txtSueldos = new javax.swing.JTextField();
        lblMovilizacion = new javax.swing.JLabel();
        txtMovilizacion = new javax.swing.JTextField();
        lblViaticos = new javax.swing.JLabel();
        txtViaticos = new javax.swing.JTextField();
        lblCapacitacion = new javax.swing.JLabel();
        txtCapacitacion = new javax.swing.JTextField();
        lblSuministros = new javax.swing.JLabel();
        txtSuministros = new javax.swing.JTextField();
        lblHerramientas = new javax.swing.JLabel();
        txtHerramientas = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("SELECCIONAR TIPO DE GASTO");

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblVivienda.setText("Vivienda");

        lblSalud.setText("Salud");

        lblEducacion.setText("Educacion");

        lblAlimentacion.setText("Alimentacion");

        txtVivienda.setEditable(false);
        txtVivienda.setText("0.0");

        txtSalud.setEditable(false);
        txtSalud.setText("0.0");

        txtEducacion.setEditable(false);
        txtEducacion.setText("0.0");

        txtAlimentacion.setEditable(false);
        txtAlimentacion.setText("0.0");

        txtVestimenta.setEditable(false);
        txtVestimenta.setText("0.0");

        txtOtro.setEditable(false);
        txtOtro.setText("0.0");

        lblVestimenta.setText("Vestimenta");

        lblOtro.setText("Otro");

        lblMercaderia.setText("Mercaderia");

        txtMercaderia.setEditable(false);
        txtMercaderia.setText("0.0");

        lblArriendo.setText("Arriendo");

        txtArriendo.setEditable(false);
        txtArriendo.setText("0.0");

        lblServicios.setText("Servicios Basicos");

        txtServicios.setEditable(false);
        txtServicios.setText("0.0");

        lblSueldos.setText("Sueldos");

        txtSueldos.setEditable(false);
        txtSueldos.setText("0.0");

        lblMovilizacion.setText("Movilizacion");

        txtMovilizacion.setEditable(false);
        txtMovilizacion.setText("0.0");

        lblViaticos.setText("Viaticos");

        txtViaticos.setEditable(false);
        txtViaticos.setText("0.0");

        lblCapacitacion.setText("Capacitacion");

        txtCapacitacion.setEditable(false);
        txtCapacitacion.setText("0.0");

        lblSuministros.setText("Sumistros");

        txtSuministros.setEditable(false);
        txtSuministros.setText("0.0");

        lblHerramientas.setText("Herramientas");

        txtHerramientas.setEditable(false);
        txtHerramientas.setText("0.0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(jLabel1))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(lblVivienda)
                            .addGap(75, 75, 75)
                            .addComponent(txtVivienda, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(65, 65, 65)
                            .addComponent(lblAlimentacion)
                            .addGap(36, 36, 36)
                            .addComponent(txtAlimentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(lblSalud)
                            .addGap(91, 91, 91)
                            .addComponent(txtSalud, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(65, 65, 65)
                            .addComponent(lblVestimenta)
                            .addGap(46, 46, 46)
                            .addComponent(txtVestimenta, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(lblEducacion)
                            .addGap(65, 65, 65)
                            .addComponent(txtEducacion, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(65, 65, 65)
                            .addComponent(lblOtro)
                            .addGap(84, 84, 84)
                            .addComponent(txtOtro, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(lblMercaderia)
                            .addGap(57, 57, 57)
                            .addComponent(txtMercaderia, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(65, 65, 65)
                            .addComponent(lblViaticos)
                            .addGap(66, 66, 66)
                            .addComponent(txtViaticos, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(lblArriendo)
                            .addGap(73, 73, 73)
                            .addComponent(txtArriendo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(65, 65, 65)
                            .addComponent(lblCapacitacion)
                            .addGap(37, 37, 37)
                            .addComponent(txtCapacitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(lblServicios)
                            .addGap(26, 26, 26)
                            .addComponent(txtServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(65, 65, 65)
                            .addComponent(lblSuministros)
                            .addGap(53, 53, 53)
                            .addComponent(txtSuministros, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(lblSueldos)
                            .addGap(78, 78, 78)
                            .addComponent(txtSueldos, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(65, 65, 65)
                            .addComponent(lblHerramientas)
                            .addGap(31, 31, 31)
                            .addComponent(txtHerramientas, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(lblMovilizacion)
                            .addGap(53, 53, 53)
                            .addComponent(txtMovilizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(512, 512, 512)
                            .addComponent(jButton1))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtVivienda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAlimentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblVivienda)
                            .addComponent(lblAlimentacion))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSalud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVestimenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSalud)
                            .addComponent(lblVestimenta))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEducacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOtro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEducacion)
                            .addComponent(lblOtro))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMercaderia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtViaticos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMercaderia)
                            .addComponent(lblViaticos))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtArriendo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCapacitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblArriendo)
                            .addComponent(lblCapacitacion))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtServicios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSuministros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblServicios)
                            .addComponent(lblSuministros))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSueldos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHerramientas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSueldos)
                            .addComponent(lblHerramientas))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblMovilizacion))
                    .addComponent(txtMovilizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

            if (!txtVivienda.getText().equals("0.0")) {
                total = Double.parseDouble(txtVivienda.getText());
                total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();

                query = "INSERT INTO TIPO_GASTO (id_factura,tipo,total)"
                        + "VALUES('" + numFac + "','" + lblVivienda.getText() + "'," + total + ")";

                totales[0] = total;
                conTipo.insertar(query);
            }
            if (!txtSalud.getText().equals("0.0")) {
                total = Double.parseDouble(txtSalud.getText());
                total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();

                query = "INSERT INTO TIPO_GASTO (id_factura,tipo,total)"
                        + "VALUES('" + numFac + "','" + lblSalud.getText() + "'," + total + ")";

                totales[1] = total;
                conTipo.insertar(query);
            }
            if (!txtEducacion.getText().equals("0.0")) {
                total = Double.parseDouble(txtEducacion.getText());
                total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();

                query = "INSERT INTO TIPO_GASTO (id_factura,tipo,total)"
                        + "VALUES('" + numFac + "','" + lblEducacion.getText() + "'," + total + ")";

                totales[2] = total;
                conTipo.insertar(query);
            }
            if (!txtAlimentacion.getText().equals("0.0")) {
                total = Double.parseDouble(txtAlimentacion.getText());
                total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();

                query = "INSERT INTO TIPO_GASTO (id_factura,tipo,total)"
                        + "VALUES('" + numFac + "','" + lblAlimentacion.getText() + "'," + total + ")";

                totales[3] = total;
                conTipo.insertar(query);
            }
            if (!txtVestimenta.getText().equals("0.0")) {
                total = Double.parseDouble(txtVestimenta.getText());
                total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();

                query = "INSERT INTO TIPO_GASTO (id_factura,tipo,total)"
                        + "VALUES('" + numFac + "','" + lblVestimenta.getText() + "'," + total + ")";

                totales[4] = total;
                conTipo.insertar(query);
            }
            if (!txtOtro.getText().equals("0.0")) {
                total = Double.parseDouble(txtOtro.getText());
                total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();

                query = "INSERT INTO TIPO_GASTO (id_factura,tipo,total)"
                        + "VALUES('" + numFac + "','" + lblOtro.getText() + "'," + total + ")";

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
                query = "UPDATE HISTORIAL_PAGOS SET total_alimentacion=total_alimentacion+" + totales[3] + "::money,"
                        + "total_salud=total_salud+" + totales[1] + "::money,"
                        + "total_vivienda=total_vivienda+" + totales[0] + "::money,"
                        + "total_educacion=total_educacion+" + totales[2] + "::money,"
                        + "total_vestimenta=total_vestimenta+" + totales[4] + "::money,"
                        + "total_negocios=total_negocios+" + totales[5] + "::money,"
                        + "total_otros=total_otros+" + totales[6] + "::money WHERE anio_historial=" + anio + " AND id_cliente='" + cedula + "'";
            } else {
                query = "INSERT INTO HISTORIAL_PAGOS VALUES (" + anio + ",'" + cedula + "'," + totales[3] + "," + totales[1] + "," + totales[0] + "," + totales[2] + "," + totales[4] + "," + totales[5] + "," + totales[6] + ")";
            }

            conTipo.insertar(query);

            JOptionPane.showMessageDialog(this, "Factura ingresada exitosamente");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado el tipo para cada producto");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public void restarAgregado(JTextField txtField, int row) {
        double total;
        total = Double.parseDouble(txtField.getText());
        total -= (Double) tablaProductos.getValueAt(row, 1);
        total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();
        txtField.setText(String.valueOf(total));
    }

    public void sumarAgregado(JTextField txtField, int row, String tipo) {
        double total;
        total = Double.parseDouble(txtField.getText());
        total += (Double) tablaProductos.getValueAt(row, 1);
        total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();
        txtField.setText(String.valueOf(total));
        tipoEstado[row] = tipo;
    }

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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAlimentacion;
    private javax.swing.JLabel lblArriendo;
    private javax.swing.JLabel lblCapacitacion;
    private javax.swing.JLabel lblEducacion;
    private javax.swing.JLabel lblHerramientas;
    private javax.swing.JLabel lblMercaderia;
    private javax.swing.JLabel lblMovilizacion;
    private javax.swing.JLabel lblOtro;
    private javax.swing.JLabel lblSalud;
    private javax.swing.JLabel lblServicios;
    private javax.swing.JLabel lblSueldos;
    private javax.swing.JLabel lblSuministros;
    private javax.swing.JLabel lblVestimenta;
    private javax.swing.JLabel lblViaticos;
    private javax.swing.JLabel lblVivienda;
    private javax.swing.JTextField txtAlimentacion;
    private javax.swing.JTextField txtArriendo;
    private javax.swing.JTextField txtCapacitacion;
    private javax.swing.JTextField txtEducacion;
    private javax.swing.JTextField txtHerramientas;
    private javax.swing.JTextField txtMercaderia;
    private javax.swing.JTextField txtMovilizacion;
    private javax.swing.JTextField txtOtro;
    private javax.swing.JTextField txtSalud;
    private javax.swing.JTextField txtServicios;
    private javax.swing.JTextField txtSueldos;
    private javax.swing.JTextField txtSuministros;
    private javax.swing.JTextField txtVestimenta;
    private javax.swing.JTextField txtViaticos;
    private javax.swing.JTextField txtVivienda;
    // End of variables declaration//GEN-END:variables
}
