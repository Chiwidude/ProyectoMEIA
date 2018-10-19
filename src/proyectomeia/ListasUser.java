/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomeia;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import proyectomeia.Clases.ObjectLista;
import proyectomeia.Clases.Singleton;

/**
 *
 * @author Pancho
 */
public class ListasUser extends javax.swing.JFrame {

    /**
     * Creates new form ListasUser
     */
    public ListasUser() {
        initComponents();
    }
    public ListasUser(Singleton object){
        this();
        fase = object;
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnCrearlista = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtNlista = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        btnBusqueda = new javax.swing.JButton();
        btnEditarL = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        txtCnombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        btnCnombre = new javax.swing.JButton();
        btnCdesc = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setText("Crear Lista");

        btnCrearlista.setText("Crear");
        btnCrearlista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearlistaActionPerformed(evt);
            }
        });

        jLabel2.setText("Nombre Lista");

        jLabel3.setText("Descripción");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane1)
                            .addComponent(txtNlista)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(btnCrearlista)))
                .addContainerGap(158, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNlista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCrearlista)
                .addGap(24, 24, 24))
        );

        jTabbedPane1.addTab("Ingresar", jPanel1);

        jLabel4.setText("Buscar");

        btnBusqueda.setText("Buscar");

        btnEditarL.setText("Editar");
        btnEditarL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarLActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");

        jLabel5.setText("Nombre:");

        jLabel6.setText("Descripción");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        btnCnombre.setText("Cambiar");

        btnCdesc.setText("Cambiar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBusqueda)
                                .addGap(42, 42, 42)
                                .addComponent(btnEditarL)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminar))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(btnCnombre))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnCdesc)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBusqueda)
                            .addComponent(btnEditarL)
                            .addComponent(btnEliminar))
                        .addGap(65, 65, 65))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(btnCnombre)))
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCdesc))
        );

        jTabbedPane1.addTab("Acciones", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearlistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearlistaActionPerformed

            String Nombre = txtNlista.getText().toString();
            String Descripcion = txtDescripcion.getText().toString();
            if(!Nombre.isEmpty()){
                if(Nombre.length() <= 30){
                    if(!Descripcion.isEmpty()){
                        if(Descripcion.length()<= 40){
                            if(!fase.Listas.masterfileLista.exists() && !fase.Listas.bitacora.masterfile.exists()){
                                ObjectLista nueva = new ObjectLista(Nombre,Descripcion);
                                nueva.setUsuario(fase.current.getUsername());
                                try {
                                    fase.Listas.Insertar(nueva.toString());
                                    JOptionPane.showMessageDialog(null, fase.current.getUsername() + " " + "agrego la lista:" + " " + Nombre);
                                } catch (IOException ex) {
                                    Logger.getLogger(ListasUser.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }else if(fase.Listas.bitacora.masterfile.exists()) {
                                List<String> res = fase.Listas.bitacora.Busqueda(Nombre+"|"+fase.current.getUsername());
                                if(res.isEmpty()){
                                    ObjectLista nueva = new ObjectLista(Nombre,Descripcion);
                                    nueva.setUsuario(fase.current.getUsername());
                                    try {
                                        fase.Listas.Insertar(nueva.toString());
                                        JOptionPane.showMessageDialog(null, fase.current.getUsername() + " " + "agrego la lista:" + " " + Nombre);
                                    } catch (IOException ex) {
                                        Logger.getLogger(ListasUser.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else {
                                    int counter = 0;
                                    for(int i = 0; i<res.size();i ++){
                                        String r = res.get(i);
                                        if(r.split("\\|")[r.split("\\|").length-1].equals("0")){
                                            counter++;
                                        }
                                    }
                                    if(counter == res.size()){
                                        if(!fase.Listas.masterfileLista.exists()){
                                        ObjectLista nueva = new ObjectLista(Nombre,Descripcion);
                                        nueva.setUsuario(fase.current.getUsername());
                                        try {
                                            fase.Listas.Insertar(nueva.toString());
                                            JOptionPane.showMessageDialog(null, fase.current.getUsername() + " " + "agrego la lista:" + " " + Nombre);
                                        } catch (IOException ex) {
                                            Logger.getLogger(ListasUser.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                      } else {
                                            try {
                                                String busqueda = fase.Listas.Buscar(Nombre +"|" + fase.current.getUsername());
                                                if(busqueda.isEmpty() || busqueda.trim().equals("")){
                                                    ObjectLista nueva = new ObjectLista(Nombre,Descripcion);
                                                     nueva.setUsuario(fase.current.getUsername());
                                                     fase.Listas.Insertar(nueva.toString());
                                                     JOptionPane.showMessageDialog(null, fase.current.getUsername() + " " + "agrego la lista:" + " " + Nombre);
                                                } else {
                                                    JOptionPane.showMessageDialog(null, fase.current.getUsername() + " " + "ya posee una lista con nombre:" + " " + Nombre);
                                                }
                                            } catch (IOException ex) {
                                                Logger.getLogger(ListasUser.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                            
                                        }
                                    } else {
                                         JOptionPane.showMessageDialog(null, fase.current.getUsername() + " " + "ya posee una lista con nombre:" + " " + Nombre);
                                    }
                                }
                            } 
                            
                        }else {
                            JOptionPane.showMessageDialog(null, "La descripción debe ser máximo de 40 caracteres");
                        }
                    }else {
                        JOptionPane.showMessageDialog(null, "La descripción no puede ser vacía");
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "El nombre debe ser máximo de 30 caracteres");
                }
            }else {
                JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío");
            }
            
           
    }//GEN-LAST:event_btnCrearlistaActionPerformed

    private void btnEditarLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarLActionPerformed

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
            java.util.logging.Logger.getLogger(ListasUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListasUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListasUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListasUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListasUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBusqueda;
    private javax.swing.JButton btnCdesc;
    private javax.swing.JButton btnCnombre;
    private javax.swing.JButton btnCrearlista;
    private javax.swing.JButton btnEditarL;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtCnombre;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtNlista;
    // End of variables declaration//GEN-END:variables
}