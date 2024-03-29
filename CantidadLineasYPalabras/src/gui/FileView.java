package gui;

import clases.LineasYPalabras;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

// @author LuisR
public class FileView extends javax.swing.JFrame {

    /**
     * Creates new form FileView
     */
    public FileView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblResultados = new javax.swing.JLabel();
        btnSelect = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        taArchivo = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblResultados.setFont(new java.awt.Font("Yu Gothic", 0, 18)); // NOI18N

        btnSelect.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSelect.setText("Seleccionar Archivo");
        btnSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectActionPerformed(evt);
            }
        });

        taArchivo.setEditable(false);
        taArchivo.setColumns(20);
        taArchivo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        taArchivo.setRows(5);
        jScrollPane1.setViewportView(taArchivo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblResultados, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(btnSelect)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSelect, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(lblResultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clean() {
        String vacio = "";
        lblResultados.setText(vacio);
        taArchivo.setText(vacio);
    }

    private void btnSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectActionPerformed

        /*
         * Muestra el cuadro de diálogo de archivos, para la selección del
         * archivo de texto que se desee abrir
         */
        JFileChooser selectorArchivos = new JFileChooser();
        selectorArchivos.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        // Filtra los tipos de archivos que se mostrarán en el selector de archivos
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de Texto",
                "java", "txt", "csv", "html",
                "htm", "css", "js", "xml",
                "json", "yaml", "yml", "md",
                "bat", "cmd", "srt", "sql",
                "ini", "properties", "rtf",
                "log", "syslog", "htaccess",
                "lnk");

        selectorArchivos.setFileFilter(filtro);

        // Indica cual fue la acción del usuario sobre el JFileChooser
        int resultado = selectorArchivos.showOpenDialog(this);

        // Obtiene el archivo seleccionado por el usuario
        File archivo = selectorArchivos.getSelectedFile();

        /*
            Evalua que el archivo seleccionado tenga como extensión alguna de
            las ingresadas anteriormente, si el archivo son de algun tipo de las
            extensiones marcadas, se realiza el conteo de palabras, lineas y
            se muestran los resultados, en caso contrario, se muestra un mensaje
            indicando que sólo se permiten archivos de texto.
         */
        if (filtro.accept(archivo)) {

            LineasYPalabras contador = new LineasYPalabras(archivo);

            try {

                contador.cantLineasYPalabras();

                lblResultados.setText("Número de Palabras: " + contador.getNumPalabras()
                        + " | Número de Líneas: " + contador.getNumLineas());

                taArchivo.setText(contador.getTextoArchivo());

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Seleccione sólo archivos de texto", "Error",
                        JOptionPane.ERROR_MESSAGE);
                clean();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Seleccione sólo archivos de texto",
                    "Error", JOptionPane.ERROR_MESSAGE);
            clean();
        }

    }//GEN-LAST:event_btnSelectActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FileView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FileView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSelect;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblResultados;
    private javax.swing.JTextArea taArchivo;
    // End of variables declaration//GEN-END:variables
}
