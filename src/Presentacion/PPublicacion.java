/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Negocio.NMesa;
import Negocio.NPartido;
import Negocio.NPublicacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author German
 */
public class PPublicacion extends javax.swing.JFrame {

    NMesa negocioMesa;
    NPartido negocioPartido;
    int[] usuarioRecinto;
    NPublicacion negocioPublicacion;

    /**
     * Creates new form PPublicacion
     */
    public PPublicacion() {
        initComponents();
    }

    public PPublicacion(int[] usuarioRecinto) {
        initComponents();
        setLocationRelativeTo(null);
        negocioMesa = new NMesa();
        negocioPartido = new NPartido();
        negocioPublicacion = new NPublicacion();
        this.usuarioRecinto = usuarioRecinto;
        cargarComboMesa();
        cargarComboPartido();
        actualizar();

    }

    private void guardar() {
        if (tbDetalle.getRowCount() > 0) {
            int idMesa = negocioMesa.getKey(cbMesa.getSelectedItem().toString(), usuarioRecinto[1]);
            negocioPublicacion.guardar(idMesa, getCantidadTotal());
            negocioPublicacion.guardarDetalle(getListaIdPartido(), getListaCantidad());
            actualizar();

        } else {
            JOptionPane.showMessageDialog(null, "No hay detalle de publicacion para guardar", "Atencion", 1);
        }

    }

    private void agregar() {
        cbMesa.setEnabled(false);
        int cantidad = Integer.parseInt(txtCantidad.getText());
        int nroMesa = Integer.parseInt(cbMesa.getSelectedItem().toString());
        String partido = cbPartido.getSelectedItem().toString();
        int idPartido = negocioPartido.getKey(partido);
        DefaultTableModel modelo = (DefaultTableModel) tbDetalle.getModel();
        modelo.addRow(new Object[]{nroMesa, partido,idPartido, cantidad});
        tbDetalle.setModel(modelo);
    }

    private void quitar() {
        if (tbDetalle.getRowCount() > 0) {
            if (!(tbDetalle.getSelectedRow() < 0)) {
                DefaultTableModel modelo = (DefaultTableModel) tbDetalle.getModel();
                modelo.removeRow(tbDetalle.getSelectedRow());
                if (tbDetalle.getRowCount() == 0) {
                    cbMesa.setEnabled(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila del detalle de la publicacion", "Atencion", JOptionPane.WARNING_MESSAGE, null);
            }
        } else {
            cbMesa.setEnabled(true);
            JOptionPane.showMessageDialog(null, "No existe ningun objeto de resultado a quitar", "Atencion", JOptionPane.WARNING_MESSAGE, null);
        }
    }

    @SuppressWarnings("unchecked")
    private void cargarComboMesa() {
        cbMesa.setModel(negocioMesa.listarCombo(usuarioRecinto[0]));
    }

    @SuppressWarnings("unchecked")
    private void cargarComboPartido() {
        cbPartido.setModel(negocioPartido.listarCombo());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGuardar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDetalle = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPublicacion = new javax.swing.JTable();
        btnQuitar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtCantidad = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        cbPartido = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cbMesa = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        btnGuardar.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        tbDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mesa", "Partido", "Cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbDetalle.setMinimumSize(new java.awt.Dimension(121, 64));
        jScrollPane1.setViewportView(tbDetalle);
        if (tbDetalle.getColumnModel().getColumnCount() > 0) {
            tbDetalle.getColumnModel().getColumn(0).setMinWidth(6);
            tbDetalle.getColumnModel().getColumn(1).setMinWidth(100);
        }

        tbPublicacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Título 4", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tbPublicacion);

        btnQuitar.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        btnQuitar.setText("Quitar");
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 153, 204));

        btnAgregar.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        cbPartido.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel2.setText("Partido");

        cbMesa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel3.setText("Mesa");

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel1.setText("PUBLICACIONES");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cbPartido, 0, 275, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(cbMesa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addContainerGap(49, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAgregar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(23, 23, 23)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbMesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbPartido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnQuitar)))
                .addGap(21, 21, 21))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQuitar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(btnGuardar)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void actualizar() {
        DefaultTableModel modelo = new DefaultTableModel();
        tbDetalle.setModel(modelo);
        modelo.addColumn("N° Mesa");
        modelo.addColumn("Partido");
        modelo.addColumn("Cod. Partido");
        modelo.addColumn("Cantidad");

        int[] anchos = {3, 150, 20, 6};
        for (int i = 0; i < tbDetalle.getColumnCount(); i++) {
            tbDetalle.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        txtCantidad.setText("");
        cbMesa.setEnabled(true);
        cbMesa.setSelectedIndex(0);
        cbPartido.setSelectedIndex(0);
        tbPublicacion.setModel(negocioPublicacion.listarTabla());

        int[] anchosPublicacion = {8, 8, 8, 8, 100};
        for (int i = 0; i < tbPublicacion.getColumnCount(); i++) {
            tbPublicacion.getColumnModel().getColumn(i).setPreferredWidth(anchosPublicacion[i]);
        }
    }
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregar();
    }//GEN-LAST:event_btnAgregarActionPerformed


    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        // TODO add your handling code here:
        quitar();
    }//GEN-LAST:event_btnQuitarActionPerformed


    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        /* La siguiente funcion no corresponde a guardar:
        lograr que con dos click poder ver el detalle de la publicacion
         */
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    //Obtener una lista de los ID de los partidos colocados en el detalle
    private ArrayList<Integer> getListaIdPartido() {
        ArrayList<Integer> lista = new ArrayList<>();
        for (int i = 0; i < tbDetalle.getRowCount(); i++) {
            int idPartido = Integer.valueOf(tbDetalle.getValueAt(i, 2).toString());
            lista.add(idPartido);
        }
        return lista;
    }

    private ArrayList<Integer> getListaCantidad() {
        ArrayList<Integer> lista = new ArrayList<>();
        for (int i = 0; i < tbDetalle.getRowCount(); i++) {
            int cantidad = Integer.valueOf(tbDetalle.getValueAt(i, 3).toString());
            lista.add(cantidad);
        }
        return lista;
    }

    private int getCantidadTotal() {
        int cantidadTotal = 0;
        for (int i = 0; i < tbDetalle.getRowCount(); i++) {
            cantidadTotal = cantidadTotal + Integer.valueOf(tbDetalle.getValueAt(i, 3).toString());
        }
        return cantidadTotal;
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
            java.util.logging.Logger.getLogger(PPublicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PPublicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PPublicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PPublicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new PPublicacion().setVisible(true);
                int[] usuarioRecinto = new int[2];
                usuarioRecinto[0] = 4;
                usuarioRecinto[1] = 2;

                new PPublicacion(usuarioRecinto).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JComboBox<String> cbMesa;
    private javax.swing.JComboBox<String> cbPartido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbDetalle;
    private javax.swing.JTable tbPublicacion;
    private javax.swing.JTextField txtCantidad;
    // End of variables declaration//GEN-END:variables
}
