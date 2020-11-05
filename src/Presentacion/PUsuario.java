/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Negocio.NRecinto;
import Negocio.NUsuario;
import Negocio.NCargo;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

/**
 *
 * @author German
 */
public class PUsuario extends javax.swing.JFrame {

    /**
     * Creates new form VUsuario
     */
    NUsuario negocio;
    NCargo nCargo;
    NRecinto nRecinto;
    int privilegio;

    public PUsuario(int privilegio) {
        initComponents();
        setLocationRelativeTo(null);
        negocio = new NUsuario();
        nRecinto = new NRecinto();
        nCargo = new NCargo();
        this.privilegio = privilegio;
        cargarComboCargo();
        cargarComboRecinto();
//        actualizar();
        actualizar(privilegio);
    }

    private void limpiar() {
        lbIdUsuario.setText("");
        txtApMat.setText("");
        txtApPat.setText("");
        txtCi.setText("");
        txtCorreo.setText("");
        txtNombre.setText("");
        txtPassword.setText("");
        txtTelefono.setText("");
        txtUsuario.setText("");
        txtUsuario.setEditable(true);
    }

    private void actualizar(int privilegio) {
        lbIdUsuario.setText("");
        txtApMat.setText("");
        txtApPat.setText("");
        txtCi.setText("");
        txtCorreo.setText("");
        txtNombre.setText("");
        txtPassword.setText("");
        txtTelefono.setText("");
        txtUsuario.setText("");
        txtUsuario.setEditable(true);
        tbUsuario.setModel(negocio.listarTabla(privilegio));
        int[] anchos = {50, 80, 90, 90, 150, 110, 50, 60, 100, 150};
        for (int i = 0; i < tbUsuario.getColumnCount(); i++) {
            tbUsuario.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        cbCargo.setSelectedIndex(0);
        cbRecinto.setVisible(false);
    }
    private void cargarComboCargo() {
        cbCargo.setModel(nCargo.listarCombo());
    }

    private void cargarComboRecinto() {
        cbRecinto.setModel(nRecinto.listarCombo());
    }

    private void guardar() {
        String nombre = txtNombre.getText();
        String paterno = txtApPat.getText();
        String materno = txtApMat.getText();
        String correo = txtCorreo.getText();
        String user = txtUsuario.getText();
        String pass = new String(txtPassword.getPassword());
        int telefono;
        if (negocio.isNumeric(txtTelefono.getText())) {
            telefono = Integer.valueOf(txtTelefono.getText());
        } else {
            telefono = 0;
        }
        if (nombre.equals("") || paterno.equals("") || correo.equals("") || user.equals("")
                || pass.equals("") || cbCargo.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Debe llenar correctamente los campos requeridos");
        } else {
            if (!negocio.existeUsuario(user)) {
                if (negocio.esEmail(correo)) {
                    if (negocio.isNumeric(txtCi.getText())) {
                        int cargo = nCargo.getKey((String) cbCargo.getSelectedItem());
                        if (privilegio < cargo || privilegio == 1) {
                            int recinto = nRecinto.getKey((String) cbRecinto.getSelectedItem());
                            String nuevoPass = negocio.sha1(pass);
                            try {
                                negocio.guardar(nombre, paterno, materno, correo, user, nuevoPass, telefono, Integer.valueOf(txtCi.getText()), cargo, recinto);
                                actualizar(privilegio);
                                JOptionPane.showMessageDialog(null, "Registro Guardado");
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Hay un problema al registrar");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "No tiene privilegio de registrar alguien del mismo cargo o mayor");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "La Carnet de Identidad tienen que ser valor numerico", "Atencion", 1);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "El email es incorrecto");
                }
            } else {
                JOptionPane.showMessageDialog(null, "El usuario ya existe");
            }
        }
    }

    private void modificar() {
        String nombre = txtNombre.getText();
        String paterno = txtApPat.getText();
        String materno = txtApMat.getText();
        String correo = txtCorreo.getText();
        String user = txtUsuario.getText();
        String pass = new String(txtPassword.getPassword());
        int telefono;
        if (negocio.isNumeric(txtTelefono.getText())) {
            telefono = Integer.valueOf(txtTelefono.getText());
        } else {
            telefono = 0;
        }
        if (nombre.equals("") || paterno.equals("")
                || correo.equals("") || user.equals("") || pass.equals("") || lbIdUsuario.getText().equals("") || cbCargo.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Hay campos vacios, debe llenar todos los datos");
        } else {
            if (negocio.esEmail(correo)) {
                if (negocio.isNumeric(txtCi.getText())) {
                    int cargo = nCargo.getKey((String) cbCargo.getSelectedItem());
                    System.err.println("privilego =" + privilegio);
                    System.err.println("cargo seleccionado= " + cargo);
                    if (privilegio < cargo || privilegio == 1) {
                        int recinto = nRecinto.getKey((String) cbRecinto.getSelectedItem());
                        String nuevoPass = negocio.sha1(pass);
                        try {
                            negocio.modificar(Integer.valueOf(lbIdUsuario.getText()), nombre, paterno, materno, correo, user, nuevoPass, telefono, Integer.valueOf(txtCi.getText()), cargo, recinto);
                            actualizar(privilegio);
                            JOptionPane.showMessageDialog(null, "Registro Actualizado");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Hay un problema al actualizar");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No tiene privilegio de registrar alguien del mismo cargo o mayor");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "El email es incorrecto");
            }
        }
    }

    private void eliminar() {

        if (negocio.isNumeric(lbIdUsuario.getText())) {
            try {
                negocio.eliminar(Integer.valueOf(lbIdUsuario.getText()));
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
                actualizar(privilegio);
            } catch (SQLException ex) {
                Logger.getLogger(PUsuario.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Hay un problema al eliminar");
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtCorreo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtCi = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        cbCargo = new javax.swing.JComboBox<>();
        txtApPat = new javax.swing.JTextField();
        cbRecinto = new javax.swing.JComboBox<>();
        txtApMat = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        lbIdUsuario = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbUsuario = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel11.setText("Cargo*");

        jLabel12.setText("Recinto*");

        cbCargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCargo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCargoItemStateChanged(evt);
            }
        });

        txtApPat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApPatActionPerformed(evt);
            }
        });

        cbRecinto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtApMat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApMatActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(0, 0, 255));
        jLabel5.setText("Carnet de Identidad*");

        lbIdUsuario.setBackground(new java.awt.Color(0, 0, 255));
        lbIdUsuario.setText("jLabel13");

        jLabel7.setBackground(new java.awt.Color(0, 0, 255));
        jLabel7.setText("Correo*");

        jLabel8.setBackground(new java.awt.Color(0, 0, 255));
        jLabel8.setText("Usuario*");

        jLabel9.setBackground(new java.awt.Color(0, 0, 255));
        jLabel9.setText("Contraseña*");

        jLabel1.setBackground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("ID");

        jLabel2.setBackground(new java.awt.Color(0, 0, 255));
        jLabel2.setText("Nombre*");

        jLabel3.setBackground(new java.awt.Color(0, 0, 255));
        jLabel3.setText("Apellido Paterno*");

        jLabel4.setBackground(new java.awt.Color(0, 0, 255));
        jLabel4.setText("Apellido Materno");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jLabel10.setText("Telefono");

        jButton1.setText("Limpiar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                            .addComponent(txtCi)
                            .addComponent(txtApMat)
                            .addComponent(txtApPat)
                            .addComponent(txtNombre)
                            .addComponent(lbIdUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 110, Short.MAX_VALUE)
                        .addComponent(btnGuardar)
                        .addGap(46, 46, 46)
                        .addComponent(btnModificar)
                        .addGap(27, 27, 27)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbRecinto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbCargo, 0, 189, Short.MAX_VALUE)
                            .addComponent(txtCorreo)
                            .addComponent(txtPassword)
                            .addComponent(txtUsuario))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(30, 30, 30))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel8)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel9)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel7)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel11)
                            .addComponent(cbCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel12)
                            .addComponent(cbRecinto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbIdUsuario, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtApPat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtApMat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar)
                            .addComponent(btnEliminar)
                            .addComponent(btnModificar))
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))))
        );

        tbUsuario.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tbUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbUsuario.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbUsuarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbUsuario);

        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/back.png"))); // NOI18N
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel6.setText("USUARIOS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(284, 284, 284)
                        .addComponent(jLabel6))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 745, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtApPatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApPatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApPatActionPerformed

    private void txtApMatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApMatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApMatActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        modificar();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void tbUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbUsuarioMouseClicked
        // TODO add your handling code here:
        txtUsuario.setEditable(false);
        int row = tbUsuario.rowAtPoint(evt.getPoint());
        lbIdUsuario.setText(tbUsuario.getValueAt(row, 0).toString());
        txtNombre.setText(tbUsuario.getValueAt(row, 1).toString());
        txtApPat.setText(tbUsuario.getValueAt(row, 2).toString());
        txtApMat.setText(tbUsuario.getValueAt(row, 3).toString());
        txtCorreo.setText(tbUsuario.getValueAt(row, 4).toString());
        txtUsuario.setText(tbUsuario.getValueAt(row, 5).toString());
        txtTelefono.setText(tbUsuario.getValueAt(row, 6).toString());
        txtCi.setText(tbUsuario.getValueAt(row, 7).toString());

        String cargo = tbUsuario.getValueAt(row, 8).toString();
        for (int i = 0; i < cbCargo.getItemCount(); i++) {
            if (cargo.equals(cbCargo.getItemAt(i))) {
                cbCargo.setSelectedIndex(i);
            }
        }
        try {
            String recinto = tbUsuario.getValueAt(row, 9).toString();
            for (int i = 0; i < cbRecinto.getItemCount(); i++) {
                if (recinto.equals(cbRecinto.getItemAt(i))) {
                    cbRecinto.setSelectedIndex(i);
                }
            }
        } catch (Exception e) {
            cbRecinto.setSelectedIndex(0);
        }
    }//GEN-LAST:event_tbUsuarioMouseClicked

    private void cbCargoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCargoItemStateChanged
        // TODO add your handling code here:
        if (cbCargo.getSelectedIndex() < 3) {
            cbRecinto.setVisible(false);
            cbRecinto.setSelectedIndex(0);
        } else {
            cbRecinto.setVisible(true);
            cbRecinto.setSelectedIndex(0);
        }
    }//GEN-LAST:event_cbCargoItemStateChanged

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        limpiar();
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
            java.util.logging.Logger.getLogger(PUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PUsuario(1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnBack;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnGuardar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JComboBox<String> cbCargo;
    public javax.swing.JComboBox<String> cbRecinto;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lbIdUsuario;
    public javax.swing.JTable tbUsuario;
    public javax.swing.JTextField txtApMat;
    public javax.swing.JTextField txtApPat;
    public javax.swing.JTextField txtCi;
    public javax.swing.JTextField txtCorreo;
    public javax.swing.JTextField txtNombre;
    public javax.swing.JPasswordField txtPassword;
    public javax.swing.JTextField txtTelefono;
    public javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

}
