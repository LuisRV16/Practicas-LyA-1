import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
// @author LuisR
public class Main {
    
    // C:\Users\manue\OneDrive\Escritorio
    public static void main(String[] args) {
        AnalizadorLex lexico = new AnalizadorLex();
        String dir = "CODIGO2.txt";
        File file = new File(dir);
        try {
            FileReader fr = new FileReader(file);

            int n = fr.read();
            String componente = "";
            int q = 0;
            int clase = 0;
            String tipo = "";
            boolean pendiente = true;
            char c = (char) n;

            while (n != -1) {
                q = lexico.getEstado(q, c);
                while (q < 100) {
                    if (!(c == 10 || c == 32 || c == 9)) {
                        componente += c;
                    }
                    n = fr.read();
                    c = (char) n;
                    q = lexico.getEstado(q, c);
                }
                switch (q) {
                    case 100 -> {
                        clase = 4;
                        tipo = 4 + "";
                        pendiente = true;
                    }
                    case 101 -> {
                        componente += c;
                        clase = 4;
                        tipo = 1 + "";
                        pendiente = false;
                    }
                    case 102 -> {
                        clase = 4;
                        tipo = 2 + "";
                        pendiente = false;
                    }
                    case 103 -> {
                        clase = 4;
                        tipo = 3 + "";
                        pendiente = false;
                    }
                    case 104 -> {
                        componente += c;
                        clase = 5;
                        tipo = 2 + "";
                        pendiente = false;
                    }
                    case 105 -> {
                        componente += c;
                        clase = 5;
                        tipo = 3 + "";
                        pendiente = false;
                    }
                    case 106 -> {
                        clase = 5;
                        tipo = 1 + "";
                        pendiente = true;
                    }
                    case 107 -> {
                        componente += c;
                        clase = 5;
                        tipo = 5 + "";
                        pendiente = false;
                    }
                    case 108 -> {
                        clase = 5;
                        tipo = 4 + "";
                        pendiente = true;
                    }
                    case 109 -> {
                        clase = 5;
                        tipo = 6 + "";
                        pendiente = false;
                    }
                    case 110 -> {
                        componente += c;
                        clase = 6;
                        tipo = 0 + "";
                        pendiente = false;
                    }
                    case 113 -> {
                        componente += c;
                        clase = 7;
                        tipo = 0 + "";
                        pendiente = false;
                    }
                    case 111 -> {
                        String temp = "";
                        for (int i = 0; i < componente.length(); i++) {
                            char car = componente.charAt(i);
                            boolean cond = car >= 65 && c <= 90 || car >= 97 && 
                                    car <= 122 || c >= 49 && c <= 57;
                            if (cond) {
                                temp += car;
                            }
                        }
                        tipo = lexico.guarda(temp) + "";
                        if (Integer.parseInt(tipo) < 20) {
                            clase = 1;
                        } else {
                            clase = 2;
                            pendiente = true;
                        }
                        tipo = "Dir TS";
                    }
                    case 112 -> {
                        clase = 3;
                        tipo = "Dir TS";
                        pendiente = true;
                    }
                    case 200 ->{
                        componente += c;
                        tipo = "Se esperaba un =";
                    }
                    case 201 ->{
                        componente += c;
                        tipo = "Carácter inválido";
                    }
                }
                if (!pendiente) {
                    n = fr.read();
                    c = (char) n;
                }
                System.out.println(componente + " | " + clase + " | " + tipo);
                pendiente = false;
                componente = "";
                q = 0;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
