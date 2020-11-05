/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Negocio.NUsuario;

/**
 *
 * @author German
 */
public class PIndex extends javax.swing.JFrame {

    /**
     * Creates new form VMenu
     */
    NUsuario usuario;

    public PIndex() {
        initComponents();
    }

    public PIndex(NUsuario negocioUsuario) {
        initComponents();
        setLocationRelativeTo(null);
        usuario = negocioUsuario;
        mostrarModulo();

    }

    private void mostrarModulo() {
        switch (usuario.datosUsuario().getIdCargo()) {
            case 1:
                btnPublicacion.setVisible(false);
                break;
            case 2:
                btnPublicacion.setVisible(false);
                btnLugar.setVisible(false);
                btnPartido.setVisible(false);
                btnCandidatura.setVisible(false);
                break;
            case 3:
                btnUsuario.setVisible(false);
                btnRecinto.setVisible(false);
                btnMesa.setVisible(false);
                btnCandidato.setVisible(false);
                btnLugar.setVisible(false);
                btnPartido.setVisible(false);
                btnCandidatura.setVisible(false);
                break;
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
        btnUsuario = new javax.swing.JButton();
        btnMesa = new javax.swing.JButton();
        btnCandidato = new javax.swing.JButton();
        btnCandidatura = new javax.swing.JButton();
        btnPartido = new javax.swing.JButton();
        btnRecinto = new javax.swing.JButton();
        btnPublicacion = new javax.swing.JButton();
        btnLugar = new javax.swing.JButton();
        lbFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));

        btnUsuario.setBackground(new java.awt.Color(102, 102, 255));
        btnUsuario.setForeground(new java.awt.Color(255, 255, 255));
        btnUsuario.setText("Usuarios");
        btnUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuarioActionPerformed(evt);
            }
        });

        btnMesa.setBackground(new java.awt.Color(102, 102, 255));
        btnMesa.setForeground(new java.awt.Color(255, 255, 255));
        btnMesa.setText("Mesas");
        btnMesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesaActionPerformed(evt);
            }
        });

        btnCandidato.setBackground(new java.awt.Color(102, 102, 255));
        btnCandidato.setForeground(new java.awt.Color(255, 255, 255));
        btnCandidato.setText("Candidatos");
        btnCandidato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCandidatoActionPerformed(evt);
            }
        });

        btnCandidatura.setBackground(new java.awt.Color(102, 102, 255));
        btnCandidatura.setForeground(new java.awt.Color(255, 255, 255));
        btnCandidatura.setText("Candidaturas");
        btnCandidatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCandidaturaActionPerformed(evt);
            }
        });

        btnPartido.setBackground(new java.awt.Color(102, 102, 255));
        btnPartido.setForeground(new java.awt.Color(255, 255, 255));
        btnPartido.setText("Partidos");
        btnPartido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPartidoActionPerformed(evt);
            }
        });

        btnRecinto.setBackground(new java.awt.Color(102, 102, 255));
        btnRecinto.setForeground(new java.awt.Color(255, 255, 255));
        btnRecinto.setText("Recintos");
        btnRecinto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecintoActionPerformed(evt);
            }
        });

        btnPublicacion.setBackground(new java.awt.Color(102, 102, 255));
        btnPublicacion.setForeground(new java.awt.Color(255, 255, 255));
        btnPublicacion.setText("Publicacion de Votos");
        btnPublicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPublicacionActionPerformed(evt);
            }
        });

        btnLugar.setBackground(new java.awt.Color(102, 102, 255));
        btnLugar.setForeground(new java.awt.Color(255, 255, 255));
        btnLugar.setText("Departamentos/Ciudades");
        btnLugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLugarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCandidatura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPartido, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCandidato, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLugar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                    .addComponent(btnPublicacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRecinto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMesa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btnPublicacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUsuario)
                .addGap(52, 52, 52)
                .addComponent(btnRecinto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMesa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCandidato)
                .addGap(36, 36, 36)
                .addComponent(btnPartido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCandidatura)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLugar)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        lbFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/conteoDeVotos.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 534, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPublicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPublicacionActionPerformed
        // TODO add your handling code here:
        int[] usuarioRecinto = new int[2];
        usuarioRecinto[0] = usuario.datosUsuario().getIdUsuario();
        usuarioRecinto[1] = usuario.datosUsuario().getIdRecinto();
        PPublicacion pPublicacion = new PPublicacion(usuarioRecinto);
        pPublicacion.setVisible(true);
    }//GEN-LAST:event_btnPublicacionActionPerformed

    private void btnUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuarioActionPerformed
        // TODO add your handling code here:
        PUsuario pUsuario = new PUsuario(usuario.datosUsuario().getIdCargo());
        pUsuario.setVisible(true);
    }//GEN-LAST:event_btnUsuarioActionPerformed

    private void btnRecintoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecintoActionPerformed
        // TODO add your handling code here:
        PRecinto pRecinto = new PRecinto();
        pRecinto.setVisible(true);
    }//GEN-LAST:event_btnRecintoActionPerformed

    private void btnMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesaActionPerformed
        // TODO add your handling code here:
        PMesa pMesa = new PMesa();
        pMesa.setVisible(true);
    }//GEN-LAST:event_btnMesaActionPerformed

    private void btnCandidatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCandidatoActionPerformed
        // TODO add your handling code here:
        PCandidato pCandidato = new PCandidato();
        pCandidato.setVisible(true);
    }//GEN-LAST:event_btnCandidatoActionPerformed

    private void btnPartidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPartidoActionPerformed
        // TODO add your handling code here:
        PPartido pPartido = new PPartido();
        pPartido.setVisible(true);
    }//GEN-LAST:event_btnPartidoActionPerformed

    private void btnCandidaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCandidaturaActionPerformed
        // TODO add your handling code here:
        PCandidatura pCandidatura = new PCandidatura();
        pCandidatura.setVisible(true);
    }//GEN-LAST:event_btnCandidaturaActionPerformed

    private void btnLugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLugarActionPerformed
        // TODO add your handling code here:
        PLugar pLugar = new PLugar();
        pLugar.setVisible(true);
    }//GEN-LAST:event_btnLugarActionPerformed

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
            java.util.logging.Logger.getLogger(PIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PIndex().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCandidato;
    private javax.swing.JButton btnCandidatura;
    private javax.swing.JButton btnLugar;
    private javax.swing.JButton btnMesa;
    private javax.swing.JButton btnPartido;
    private javax.swing.JButton btnPublicacion;
    private javax.swing.JButton btnRecinto;
    private javax.swing.JButton btnUsuario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbFondo;
    // End of variables declaration//GEN-END:variables
}