/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomeia;

import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import proyectomeia.Clases.Singleton;

/**
 *
 * @author Pancho
 */
public class UserView extends javax.swing.JFrame {

    /**
     * Creates new form UserView
     */
    public UserView() {
        initComponents();
    }
    public UserView(Singleton datos){
        this();
        this.fase = datos;
        String t1 = labelUsuario.getText();
        t1 = t1 + " " + fase.current.getUsername();
        labelUsuario.setText(t1);
        t1 = "";
        t1 = labelRol.getText();
        if(fase.current.isRol()==true){
            t1 = t1+" " + "Administrador";
        } else {
            t1 = t1 + " " + "Usuario Normal";
        }
        labelRol.setText(t1);
        imgIcon.setIcon(new ImageIcon(new ImageIcon(fase.current.getPath_fotografía()).getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT)));
        imgIcon.setText("");
        
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                try {
                    fase.Usuarios.refactorBitacora();
                } catch (IOException ex) {
                    Logger.getLogger(UserView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    private Singleton fase;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTitulo = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();
        labelRol = new javax.swing.JLabel();
        imgIcon = new javax.swing.JLabel();
        opcionesButton = new javax.swing.JButton();
        btnListas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelTitulo.setFont(new java.awt.Font("sansserif", 3, 18)); // NOI18N
        labelTitulo.setText("INFORMACIÓN");

        labelUsuario.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        labelUsuario.setText("Usuario:");

        labelRol.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        labelRol.setText("Rol:");

        imgIcon.setText("jLabel2");

        opcionesButton.setText("Opciones");
        opcionesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionesButtonActionPerformed(evt);
            }
        });

        btnListas.setText("Listas");
        btnListas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(labelTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(labelRol))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addComponent(imgIcon))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(opcionesButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnListas)
                            .addComponent(labelUsuario))))
                .addContainerGap(172, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(opcionesButton)
                    .addComponent(btnListas))
                .addGap(9, 9, 9)
                .addComponent(labelTitulo)
                .addGap(18, 18, 18)
                .addComponent(labelUsuario)
                .addGap(18, 18, 18)
                .addComponent(labelRol)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imgIcon)
                .addContainerGap(182, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void opcionesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionesButtonActionPerformed
        MenuCambios menu = new MenuCambios(fase);
        menu.show();
        this.dispose();
    }//GEN-LAST:event_opcionesButtonActionPerformed

    private void btnListasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListasActionPerformed
            ListasUser lists = new ListasUser(fase);
            lists.show();
            this.dispose();
    }//GEN-LAST:event_btnListasActionPerformed
    
     
     
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
            java.util.logging.Logger.getLogger(UserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnListas;
    private javax.swing.JLabel imgIcon;
    private javax.swing.JLabel labelRol;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JButton opcionesButton;
    // End of variables declaration//GEN-END:variables
}
