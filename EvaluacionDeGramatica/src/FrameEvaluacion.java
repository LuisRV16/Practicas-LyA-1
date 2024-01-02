import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
public class FrameEvaluacion extends javax.swing.JFrame {
    public FrameEvaluacion() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtaGramatica = new javax.swing.JTextArea();
        btnEvaluar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtaResultados = new javax.swing.JTextArea();
        btnLimpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtaGramatica.setColumns(20);
        txtaGramatica.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtaGramatica.setRows(5);
        jScrollPane1.setViewportView(txtaGramatica);

        btnEvaluar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnEvaluar.setText("Evaluar");
        btnEvaluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEvaluarActionPerformed(evt);
            }
        });

        txtaResultados.setEditable(false);
        txtaResultados.setColumns(20);
        txtaResultados.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtaResultados.setRows(5);
        jScrollPane2.setViewportView(txtaResultados);

        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEvaluar)
                        .addGap(56, 56, 56)
                        .addComponent(btnLimpiar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLimpiar)
                    .addComponent(btnEvaluar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        txtaGramatica.setText("");
        txtaResultados.setText("");
    }//GEN-LAST:event_btnLimpiarActionPerformed
    private void btnEvaluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEvaluarActionPerformed
        try {
            txtaResultados.setForeground(Color.black);
            String inicial = "";
            String gramatica = txtaGramatica.getText();
            String resultados = "";
            if (!gramatica.equals("")) {
                char c = gramatica.charAt(0);
                if (c < 65 || c > 90) {
                    txtaResultados.setForeground(Color.red);
                    txtaResultados.setText("Introduzca una grámatica válida.");
                } else {
                    LinkedHashSet<String> v = new LinkedHashSet<>();
                    int cont1 = -1;
                    int cont2 = 0;
                    String s = "";
                    for (int i = 0; i < gramatica.length(); i++) {
                        c = gramatica.charAt(i);
                        s += c;
                        int res = cont2 - cont1;
                        if (c == 8594 && res == 1) {
                            s = s.substring(0, s.length() - 1);
                            s = s.replace(" ", "");
                            v.add(s);
                            cont1 = cont2;
                        } else if (c == 10) {
                            s = "";
                            cont2++;
                        } else if (c == 8594 && res != 1) {
                            txtaResultados.setForeground(Color.red);
                            txtaResultados.setText("Introduzca una grámatica válida.");
                            return;
                        }
                    }
                    Object[] vTemp = v.toArray();
                    inicial = (String) vTemp[0];
                    s = "";
                    HashMap<String, ArrayList<String>>[] cadenas = new HashMap[v.size()];
                    for (int i = 0; i < cadenas.length; i++) {
                        cadenas[i] = new HashMap<>();
                    }
                    ArrayList<String> temp = new ArrayList<>();
                    for (int i = 0, cont = 0; i < gramatica.length(); i++) {
                        c = gramatica.charAt(i);
                        s += c;
                        if (c == 8594) {
                            s = "";
                        } else if (c == '|') {
                            s = s.substring(0, s.length() - 1).replace(" ", "");
                            temp.add(s);
                            s = "";
                        } else if (c == 10) {
                            s = s.substring(0, s.length() - 1).replace(" ", "");
                            temp.add(s);
                            cadenas[cont].put((String) vTemp[cont], temp);
                            temp = new ArrayList<>();
                            cont++;
                        } else if (i == gramatica.length() - 1) {
                            s = s.replace(" ", "");
                            temp.add(s);
                            cadenas[cont].put((String) vTemp[cont], temp);
                            temp = new ArrayList<>();
                            cont++;
                        }
                    }
                    LinkedHashSet<String> t = new LinkedHashSet<>();
                    s = "";
                    for (int i = 0; i < vTemp.length; i++) {
                        HashMap<String, ArrayList<String>> hashTemporal = cadenas[i];
                        ArrayList<String> aux = hashTemporal.get((String) vTemp[i]);
                        boolean band = false;
                        for (int j = 0; j < aux.size(); j++) {
                            s = aux.get(j);
                            if (s.equals(s.toLowerCase())) {
                                if (s.length() == 1) {
                                    t.add(s);
                                } else {
                                    for (int l = 0; l < s.length(); l++) {
                                        boolean boo = t.add(String.valueOf(s.charAt(l)));
                                        if (boo) {
                                            t.remove(String.valueOf(s.charAt(l)));
                                        } else {
                                            band = true;
                                            break;
                                        }
                                    }
                                    if (!band) {
                                        t.add(s);
                                    }
                                }
                            } else {
                                char caracter;
                                for (int k = 0; k < s.length(); k++) {
                                    caracter = s.charAt(k);
                                    if (caracter < 65 || caracter > 90) {
                                        band = false;
                                        for (int l = 0; l < vTemp.length; l++) {
                                            String variableTemporal = (String) vTemp[l];
                                            if (variableTemporal.length() > 1) {
                                                for (int m = 0; m < variableTemporal.length(); m++) {
                                                    if (caracter == variableTemporal.charAt(m)) {
                                                        band = true;
                                                        break;
                                                    }
                                                }
                                            }
                                            if (band) {
                                                break;
                                            }
                                        }
                                        if (!band) {
                                            t.add(String.valueOf(caracter));
                                        }
                                    } else {
                                        v.add(String.valueOf(caracter));
                                    }
                                }
                            }
                        }
                    }
                    LinkedHashSet<String> nuevo = new LinkedHashSet<>();
                    LinkedHashSet<String> anterior = new LinkedHashSet<>();
                    LinkedHashSet<String> union = new LinkedHashSet<>();
                    String nuevoC = null;
                    String anteriorC = nuevoC;
                    String unionC = getConjuntoUnion(t, anteriorC);
                    if (nuevoC == null && anteriorC == null) {
                        nuevoC = anteriorC = String.valueOf("{}");
                    }
                    nuevo.add(nuevoC);
                    anterior.add(anteriorC);
                    union.add(unionC);
                    nuevoC = getVariables(vTemp, cadenas, unionC);
                    while (!nuevoC.equals(anteriorC)) {
                        anteriorC = nuevoC;
                        unionC = getConjuntoUnion(t, anteriorC);
                        nuevo.add(nuevoC);
                        anterior.add(anteriorC);
                        union.add(unionC);
                        nuevoC = getVariables(vTemp, cadenas, unionC);
                    }
                    Object[] nuevoV = nuevo.toArray();
                    Object[] anteriorV = anterior.toArray();
                    Object[] unionSV = union.toArray();
                    resultados += "V = " + v.toString() + "\n";
                    resultados += "T = " + t.toString() + "\n";
                    resultados += "S = " + inicial + "\n\n";
                    resultados += String.format("%40s", "Nuevo");
                    resultados += String.format("%40s", "Anterior");
                    resultados += String.format("%40s", "T U Anterior") + "\n\n";
                    for (int i = 0; i < nuevo.size(); i++) {
                        resultados += String.format("%40s", nuevoV[i]);
                        resultados += String.format("%40s", anteriorV[i]);
                        resultados += String.format("%40s", unionSV[i]) + "\n";
                    }
                    ArrayList<String> simbolos = new ArrayList<>();
                    for (int i = 0; i < nuevoC.length(); i++) {
                        if (nuevoC.charAt(i) != ' '
                                && nuevoC.charAt(i) != ','
                                && nuevoC.charAt(i) != '['
                                && nuevoC.charAt(i) != ']') {
                            simbolos.add(nuevoC.charAt(i) + "");
                        }
                    }
                    boolean band = false;
                    for (int i = 0; i < simbolos.size(); i++) {
                        if (inicial.equals(simbolos.get(i))) {
                            band = true;
                            break;
                        }
                    }
                    if (band) {
                        resultados += "L(G) <> {}";
                    } else {
                        resultados += "L(G) = {}";
                    }
                    txtaResultados.setText(resultados);
                }
            } else {
                txtaResultados.setForeground(Color.red);
                txtaResultados.setText("Introduzca una grámatica válida.");
            }
        } catch (Exception e) {
            txtaResultados.setForeground(Color.red);
            txtaResultados.setText("Introduzca una grámatica válida.");
            Logger.getLogger(FrameEvaluacion.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_btnEvaluarActionPerformed
    private String getConjuntoUnion(LinkedHashSet<String> t, String anteriorC) {
        LinkedHashSet<String> res = new LinkedHashSet<>();
        Object[] temp = t.toArray();
        for (int i = 0; i < temp.length; i++) {
            res.add((String) temp[i]);
        }
        if (anteriorC != null) {
            String s = "";
            for (int i = 0; i < anteriorC.length(); i++) {
                char c = anteriorC.charAt(i);
                s += c;
                if (c == ',' || c == ']') {
                    s = s.substring(1, s.length() - 1);
                    res.add(s);
                    s = "";
                }
            }
        }
        return res.toString();
    }
    private String getVariables(Object[] v, HashMap<String, ArrayList<String>>[] cadenas, String union) {
        LinkedHashSet<String> temp = new LinkedHashSet<>();
        ArrayList<String> simbolos = new ArrayList<>();
        for (int i = 0; i < union.length(); i++) {
            if (union.charAt(i) != ' '
                    && union.charAt(i) != ','
                    && union.charAt(i) != '['
                    && union.charAt(i) != ']') {
                simbolos.add(union.charAt(i) + "");
            }
        }
        for (int i = 0; i < v.length; i++) {
            HashMap<String, ArrayList<String>> temporal = cadenas[i];
            ArrayList<String> cad = temporal.get((String) v[i]);
            for (int j = 0; j < cad.size(); j++) {
                String s = cad.get(j);
                boolean[] band = new boolean[s.length()];
                for (int k = 0; k < band.length; k++) {
                    band[k] = false;
                }
                for (int k = 0; k < s.length(); k++) {
                    for (int l = 0; l < simbolos.size(); l++) {
                        if (String.valueOf(s.charAt(k)).equals(simbolos.get(l))) {
                            band[k] = true;
                            break;
                        }
                    }
                }
                boolean boo = band[0];
                for (int k = 0; k < s.length(); k++) {
                    boo = boo && band[k];
                }
                if (boo) {
                    temp.add((String) v[i]);
                }
            }
        }
        return temp.toString();
    }
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
            java.util.logging.Logger.getLogger(FrameEvaluacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameEvaluacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameEvaluacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameEvaluacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameEvaluacion().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEvaluar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtaGramatica;
    private javax.swing.JTextArea txtaResultados;
    // End of variables declaration//GEN-END:variables
}
