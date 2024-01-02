
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

// @author LuisR
public class InterfazAutomataFD extends JFrame implements ActionListener {

    JPanel panel;
    JLabel seleccionEst;
    JLabel seleccionSimb;
    JLabel selecEstInic;
    JLabel selecCantEstFin;
    JLabel selecEstFin;
    JLabel lblF;
    JLabel lblW;
    JLabel[] conjEstados;
    JLabel[] conjSimbolos;
    JComboBox comboEst;
    JComboBox comboSimb;
    JComboBox comboEstInic;
    JComboBox comboCantFin;
    JComboBox[] estadosFinales;
    JComboBox[][] funcionTransicion;
    JButton btnSel;
    JButton btnSelSig;
    JButton btnSelAnt;
    JButton btnFinSig;
    JButton btnFinAnt;
    JButton btnAFDSig;
    JButton btnAFDAnt;
    JButton btnA;
    JButton btnB;
    JButton btnC;
    JButton btnClear;
    JButton btnEvaluar;
    JTextField txtCadena;
    JTextField txtResultado;
    Nodo[] estados;
    String[] alfabeto;
    int[] estadosFin;
    Nodo q;
    int numEst;

    public InterfazAutomataFD(String titulo) {
        super(titulo);
        setLayout(null);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(640, 480));
        panel.setBounds(0, 0, 640, 480);
        panel.setLayout(null);

        add(panel);

        seleccionEst = new JLabel("Seleccione la cantidad de Estados");
        seleccionEst.setBounds(120, 200, 200, 30);
        panel.add(seleccionEst);

        seleccionSimb = new JLabel("Seleccione la cantidad de Simbolos");
        seleccionSimb.setBounds(340, 200, 220, 30);
        panel.add(seleccionSimb);

        selecEstInic = new JLabel("Seleccione el estado inicial");
        selecEstInic.setBounds(240, 200, 200, 30);
        selecEstInic.setVisible(false);
        panel.add(selecEstInic);

        selecCantEstFin = new JLabel("Seleccione cuantos estados finales tendrá el automáta");
        selecCantEstFin.setBounds(160, 200, 350, 30);
        selecCantEstFin.setVisible(false);
        panel.add(selecCantEstFin);

        selecEstFin = new JLabel("Seleccione los estados finales o el estado final");
        selecEstFin.setBounds(180, 200, 350, 30);
        selecEstFin.setVisible(false);
        panel.add(selecEstFin);

        lblF = new JLabel("F =");
        lblF.setBounds(10, 240, 30, 30);
        lblF.setFont(new Font("Arial", 1, 20));
        lblF.setVisible(false);
        panel.add(lblF);

        lblW = new JLabel("W =");
        lblW.setBounds(260, 400, 30, 30);
        lblW.setVisible(false);
        panel.add(lblW);

        txtCadena = new JTextField();
        txtCadena.setBounds(150, 400, 100, 30);
        txtCadena.setVisible(false);
        txtCadena.setEditable(false);
        panel.add(txtCadena);

        txtResultado = new JTextField();
        txtResultado.setBounds(500, 450, 150, 30);
        txtResultado.setVisible(false);
        txtResultado.setEditable(false);
        panel.add(txtResultado);

        comboEst = new JComboBox();
        comboEst.setBounds(180, 240, 80, 20);
        panel.add(comboEst);

        comboEst.addItem("");
        comboEst.addItem("1");
        comboEst.addItem("2");
        comboEst.addItem("3");
        comboEst.addItem("4");
        comboEst.addItem("5");
        comboEst.addItem("6");
        comboEst.addItem("7");
        comboEst.addItem("8");
        comboEst.addItem("9");
        comboEst.addItem("10");

        comboSimb = new JComboBox();
        comboSimb.setBounds(400, 240, 80, 20);
        panel.add(comboSimb);

        comboSimb.addItem("");
        comboSimb.addItem("1");
        comboSimb.addItem("2");
        comboSimb.addItem("3");

        btnSel = new JButton("Continuar el llenado del automáta");
        btnSel.setBounds(360, 400, 240, 40);
        btnSel.addActionListener(this);
        panel.add(btnSel);

        btnSelSig = new JButton("Continuar");
        btnSelSig.setBounds(320, 300, 100, 40);
        btnSelSig.addActionListener(this);
        btnSelSig.setVisible(false);
        panel.add(btnSelSig);

        btnSelAnt = new JButton("Regresar");
        btnSelAnt.setBounds(210, 300, 100, 40);
        btnSelAnt.addActionListener(this);
        btnSelAnt.setVisible(false);
        panel.add(btnSelAnt);

        btnFinSig = new JButton("Continuar");
        btnFinSig.setBounds(320, 300, 100, 40);
        btnFinSig.addActionListener(this);
        btnFinSig.setVisible(false);
        panel.add(btnFinSig);

        btnFinAnt = new JButton("Regresar");
        btnFinAnt.setBounds(210, 300, 100, 40);
        btnFinAnt.addActionListener(this);
        btnFinAnt.setVisible(false);
        panel.add(btnFinAnt);

        btnAFDSig = new JButton("Continuar");
        btnAFDSig.setBounds(320, 300, 100, 40);
        btnAFDSig.addActionListener(this);
        btnAFDSig.setVisible(false);
        panel.add(btnAFDSig);

        btnAFDAnt = new JButton("Regresar");
        btnAFDAnt.setBounds(210, 300, 100, 40);
        btnAFDAnt.addActionListener(this);
        btnAFDAnt.setVisible(false);
        panel.add(btnAFDAnt);

        btnA = new JButton("A");
        btnA.setBounds(260, 400, 50, 30);
        btnA.setVisible(false);
        btnA.addActionListener(this);
        panel.add(btnA);

        btnB = new JButton("B");
        btnB.setBounds(320, 400, 50, 30);
        btnB.setVisible(false);
        btnB.addActionListener(this);
        panel.add(btnB);

        btnC = new JButton("C");
        btnC.setBounds(380, 400, 50, 30);
        btnC.setVisible(false);
        btnC.addActionListener(this);
        panel.add(btnC);

        btnClear = new JButton("Limpiar");
        btnClear.setBounds(440, 400, 90, 30);
        btnClear.setVisible(false);
        btnClear.addActionListener(this);
        panel.add(btnClear);

        btnEvaluar = new JButton("Evaluar");
        btnEvaluar.setBounds(540, 400, 90, 30);
        btnEvaluar.setVisible(false);
        btnEvaluar.addActionListener(this);
        panel.add(btnEvaluar);

        setSize(650, 510);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSel) {
            switch (comboEst.getSelectedItem().toString()) {
                case "1" ->
                    estados = new Nodo[1];
                case "2" ->
                    estados = new Nodo[2];
                case "3" ->
                    estados = new Nodo[3];
                case "4" ->
                    estados = new Nodo[4];
                case "5" ->
                    estados = new Nodo[5];
                case "6" ->
                    estados = new Nodo[6];
                case "7" ->
                    estados = new Nodo[7];
                case "8" ->
                    estados = new Nodo[8];
                case "9" ->
                    estados = new Nodo[9];
                case "10" ->
                    estados = new Nodo[10];
                default -> {
                }
            }
            switch (comboSimb.getSelectedItem().toString()) {
                case "1" -> {
                    alfabeto = new String[1];
                    alfabeto[0] = "A";
                    conjSimbolos = new JLabel[1];
                    conjSimbolos[0] = new JLabel(alfabeto[0]);
                    conjSimbolos[0].setBounds(275, 10, 50, 30);
                    conjSimbolos[0].setVisible(false);
                    panel.add(conjSimbolos[0]);
                }
                case "2" -> {
                    alfabeto = new String[2];
                    alfabeto[0] = "A";
                    alfabeto[1] = "B";
                    conjSimbolos = new JLabel[2];
                    conjSimbolos[0] = new JLabel(alfabeto[0]);
                    conjSimbolos[0].setBounds(285, 10, 50, 30);
                    conjSimbolos[0].setVisible(false);
                    panel.add(conjSimbolos[0]);
                    conjSimbolos[1] = new JLabel(alfabeto[1]);
                    conjSimbolos[1].setBounds(340, 10, 50, 30);
                    conjSimbolos[1].setVisible(false);
                    panel.add(conjSimbolos[1]);
                }
                case "3" -> {
                    alfabeto = new String[3];
                    alfabeto[0] = "A";
                    alfabeto[1] = "B";
                    alfabeto[2] = "C";
                    conjSimbolos = new JLabel[3];
                    conjSimbolos[0] = new JLabel(alfabeto[0]);
                    conjSimbolos[0].setBounds(285, 10, 50, 30);
                    conjSimbolos[0].setVisible(false);
                    panel.add(conjSimbolos[0]);
                    conjSimbolos[1] = new JLabel(alfabeto[1]);
                    conjSimbolos[1].setBounds(340, 10, 50, 30);
                    conjSimbolos[1].setVisible(false);
                    panel.add(conjSimbolos[1]);
                    conjSimbolos[2] = new JLabel(alfabeto[2]);
                    conjSimbolos[2].setBounds(395, 10, 50, 30);
                    conjSimbolos[2].setVisible(false);
                    panel.add(conjSimbolos[2]);
                }
                default -> {
                }
            }
            if (!comboEst.getSelectedItem().equals("")
                    && !comboSimb.getSelectedItem().equals("")) {
                seleccionEst.setVisible(false);
                seleccionSimb.setVisible(false);
                comboEst.setVisible(false);
                comboSimb.setVisible(false);
                btnSel.setVisible(false);
                selecEstInic.setVisible(true);
                btnSelSig.setVisible(true);
                btnSelAnt.setVisible(true);
                comboEstInic = new JComboBox();
                comboEstInic.setBounds(280, 240, 80, 20);
                panel.add(comboEstInic);
                for (int i = 0; i < estados.length; i++) {
                    comboEstInic.addItem("q" + i);
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Seleccione un valor válido");
            }
        } else if (e.getSource() == btnSelSig) {
            String estInic = comboEstInic.getSelectedItem().toString();
            estInic = estInic.substring(1);
            numEst = Integer.parseInt(estInic);
            selecEstInic.setVisible(false);
            btnSelSig.setVisible(false);
            btnSelAnt.setVisible(false);
            comboEstInic.setVisible(false);
            selecCantEstFin.setVisible(true);
            comboCantFin = new JComboBox();
            comboCantFin.setBounds(280, 240, 80, 20);
            panel.add(comboCantFin);
            for (int i = 1; i <= estados.length; i++) {
                comboCantFin.addItem(i);
            }
            btnFinSig.setVisible(true);
            btnFinAnt.setVisible(true);
        } else if (e.getSource() == btnSelAnt) {
            seleccionEst.setVisible(true);
            seleccionSimb.setVisible(true);
            comboEst.setVisible(true);
            comboSimb.setVisible(true);
            comboCantFin.setVisible(false);
            btnSel.setVisible(true);
            selecEstInic.setVisible(false);
            comboEstInic.setVisible(false);
            btnSelSig.setVisible(false);
            btnSelAnt.setVisible(false);
        } else if (e.getSource() == btnFinSig) {
            String s = comboCantFin.getSelectedItem().toString();
            int cantEstFin = Integer.parseInt(s);
            estadosFinales = new JComboBox[cantEstFin];
            selecCantEstFin.setVisible(false);
            comboCantFin.setVisible(false);
            btnFinSig.setVisible(false);
            btnFinAnt.setVisible(false);
            lblF.setVisible(true);
            selecEstFin.setVisible(true);
            int x = 0;
            for (int i = 0; i < cantEstFin; i++) {
                estadosFinales[i] = new JComboBox();
                estadosFinales[i].setBounds(60 + x, 240, 50, 20);
                panel.add(estadosFinales[i]);
                for (int j = 0; j < estados.length; j++) {
                    estadosFinales[i].addItem("q" + j);
                }
                x = x + 50;
            }
            btnAFDSig.setVisible(true);
            btnAFDAnt.setVisible(true);
        } else if (e.getSource() == btnFinAnt) {
            selecEstInic.setVisible(true);
            btnSelSig.setVisible(true);
            btnSelAnt.setVisible(true);
            comboEstInic.setVisible(true);
            comboCantFin.setVisible(false);
            selecCantEstFin.setVisible(false);
            btnFinSig.setVisible(false);
            btnFinAnt.setVisible(false);
        } else if (e.getSource() == btnAFDSig) {
            String[] est = new String[estadosFinales.length];
            estadosFin = new int[estadosFinales.length];
            for (int i = 0; i < estadosFinales.length; i++) {
                est[i] = estadosFinales[i].getSelectedItem().toString();
                est[i] = est[i].substring(1);
                estadosFin[i] = Integer.parseInt(est[i]);
            }
            boolean band = true;
            for (int i = 0; i < estadosFin.length; i++) {
                for (int j = 0; j < estadosFin.length; j++) {
                    if (estadosFin[i] == estadosFin[j] && i != j) {
                        band = false;
                        break;
                    }
                }
            }
            if (band) {
                lblF.setVisible(false);
                selecEstFin.setVisible(false);
                btnAFDSig.setVisible(false);
                btnAFDAnt.setVisible(false);
                for (int i = 0; i < estadosFinales.length; i++) {
                    estadosFinales[i].setVisible(false);
                }
                funcionTransicion = new JComboBox[estados.length][alfabeto.length];
                conjEstados = new JLabel[estados.length];
                for (int i = 0; i < alfabeto.length; i++) {
                    conjSimbolos[i].setVisible(true);
                }
                switch (alfabeto.length) {
                    case 1 ->
                        btnA.setVisible(true);
                    case 2 -> {
                        btnA.setVisible(true);
                        btnB.setVisible(true);
                    }
                    case 3 -> {
                        btnA.setVisible(true);
                        btnB.setVisible(true);
                        btnC.setVisible(true);
                    }
                }
                btnClear.setVisible(true);
                btnEvaluar.setVisible(true);
                int x, y;
                x = y = 0;
                for (int i = 0; i < estados.length; i++) {
                    conjEstados[i] = new JLabel("q" + i);
                    conjEstados[i].setBounds(220, 35 + y, 50, 30);
                    panel.add(conjEstados[i]);
                    for (int j = 0; j < alfabeto.length; j++) {
                        funcionTransicion[i][j] = new JComboBox();
                        funcionTransicion[i][j].setBounds(280 + x, 40 + y, 50, 20);
                        panel.add(funcionTransicion[i][j]);
                        for (int k = 0; k < estados.length; k++) {
                            funcionTransicion[i][j].addItem("q" + k);
                        }
                        x = x + 55;
                    }
                    y = y + 25;
                    x = 0;
                }
                lblW.setVisible(true);
                txtCadena.setVisible(true);
                txtResultado.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, """                         
                           Los estados finales deben ser distintos entre s\u00ed
                           Seleccione valores v\u00e1lidos.""");
            }

        } else if (e.getSource() == btnAFDAnt) {
            lblF.setVisible(false);
            selecEstFin.setVisible(false);
            btnAFDSig.setVisible(false);
            btnAFDAnt.setVisible(false);
            selecCantEstFin.setVisible(true);
            comboCantFin.setVisible(true);
            btnFinSig.setVisible(true);
            btnFinAnt.setVisible(true);
        } else if (e.getSource() == btnA) {
            String s = txtCadena.getText();
            s += "A";
            txtCadena.setText(s);
        } else if (e.getSource() == btnB) {
            String s = txtCadena.getText();
            s += "B";
            txtCadena.setText(s);
        } else if (e.getSource() == btnC) {
            String s = txtCadena.getText();
            s += "C";
            txtCadena.setText(s);
        } else if (e.getSource() == btnClear) {
            txtCadena.setText("");
        } else if (e.getSource() == btnEvaluar) {
            for (int i = 0; i < estados.length; i++) {
                for (int j = 0; j < alfabeto.length; j++) {
                    String s = funcionTransicion[i][j].getSelectedItem().toString();
                    s = s.substring(1);
                    int n = Integer.parseInt(s);
                    switch (j) {
                        case 1 ->
                            estados[i].camino1(alfabeto[j], estados[n]);
                        case 2 ->
                            estados[i].camino2(alfabeto[j], estados[n]);
                        case 3 ->
                            estados[i].camino3(alfabeto[j], estados[n]);
                    }
                }
                String w = txtCadena.getText();
                String s;
                int n = 0;
                q = estados[numEst];
                s = String.valueOf(w.charAt(n));
                while (n < w.length()) {
                    switch (s) {
                        case "A" -> {
                            q.camino1(s, q);
                            q = q.sigEstado1;
                        }
                        case "B" -> {
                            q.camino2(s, q);
                            q = q.sigEstado2;
                        }
                        case "C" -> {
                            q.camino3(s, q);
                            q = q.sigEstado3;
                        }
                    }
                    n++;
                    s = String.valueOf(w.charAt(n));
                }
                for (int j = 0; j < estadosFin.length; j++) {
                    for (int k = 0; k < estados.length; k++) {
                        if (k == estadosFin[j]) {
                            txtResultado.setText("La cadena es aceptada");
                        } else {
                            txtResultado.setText("La cadena no es aceptada");
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfazAutomataFD afd = new InterfazAutomataFD(
                    "Evaluación de cadenas con AFD");
            afd.setVisible(true);
        });
    }
}
