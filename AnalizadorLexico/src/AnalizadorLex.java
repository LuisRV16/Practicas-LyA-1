// @author LuisR

public class AnalizadorLex {
    private final String[] tablaSimbolos = new String[35];
    private final String[] alfabeto = {
        "L", "D", "+", "-", "*", "/", "<", ">", "=", ":", "b", ";", "otro"
    };
    private final int[] q = new int[25];
    private final int[] f = new int[16];
    private final int[][] d = new int[9][13];

    public AnalizadorLex() {
        tablaSimbolos[0] = "PROGRAM";tablaSimbolos[1] = "END";
        tablaSimbolos[2] = "STEP";tablaSimbolos[3] = "TO";
        tablaSimbolos[4] = "ARRAY";tablaSimbolos[5] = "INTEGER";
        tablaSimbolos[6] = "CASE";tablaSimbolos[7] = "DO";
        tablaSimbolos[8] = "VAR";tablaSimbolos[9] = "IF";
        tablaSimbolos[10] = "ELSE";tablaSimbolos[11] = "WHILE";
        tablaSimbolos[12] = "CONST";tablaSimbolos[13] = "THEN";
        tablaSimbolos[14] = "CHAR";tablaSimbolos[15] = "REPEAT";
        tablaSimbolos[16] = "BEGIN";tablaSimbolos[17] = "REAL";
        tablaSimbolos[18] = "FOR";tablaSimbolos[19] = "UNTIL";
        tablaSimbolos[20] = "suma";tablaSimbolos[21] = "comparacion";
        tablaSimbolos[22] = "n1";tablaSimbolos[23] = "n2";
        tablaSimbolos[24] = "rs";
        for (int i = 0; i < 10; i++) {
            tablaSimbolos[25 + i] = i + "";
        }
        for (int i = 0; i <= 8; i++) {
            q[i] = i;
        }
        int n = 100;
        for (int i = 9; i <= 22; i++, n++) {
            q[i] = n;
        }
        n = 200;
        for (int i = 23; i <= 24; i++, n++) {
            q[i] = n;
        }
        for (int i = 9, n1 = 0; i < q.length; i++, n1++) {
            f[n1] = q[i];
        }

        d[0][0] = 7;
        d[0][1] = 8;
        d[0][2] = 101;
        d[0][3] = 102;
        d[0][4] = 103;
        d[0][5] = 1;
        d[0][6] = 4;
        d[0][7] = 5;
        d[0][8] = 109;
        d[0][9] = 6;
        d[0][10] = 0;
        d[0][11] = 113;
        d[0][12] = 201;

        d[1][0] = 100;
        d[1][1] = 100;
        d[1][2] = 100;
        d[1][3] = 100;
        d[1][4] = 100;
        d[1][5] = 2;
        d[1][6] = 100;
        d[1][7] = 100;
        d[1][8] = 100;
        d[1][9] = 100;
        d[1][10] = 100;
        d[1][11] = 100;
        d[1][12] = 100;

        d[2][0] = 2;
        d[2][1] = 2;
        d[2][2] = 2;
        d[2][3] = 2;
        d[2][4] = 3;
        d[2][5] = 2;
        d[2][6] = 2;
        d[2][7] = 2;
        d[2][8] = 2;
        d[2][9] = 2;
        d[2][10] = 2;
        d[2][11] = 2;
        d[2][12] = 2;

        d[3][0] = 2;
        d[3][1] = 2;
        d[3][2] = 2;
        d[3][3] = 2;
        d[3][4] = 2;
        d[3][5] = 0;
        d[3][6] = 2;
        d[3][7] = 2;
        d[3][8] = 2;
        d[3][9] = 2;
        d[3][10] = 2;
        d[3][11] = 2;
        d[3][12] = 2;

        d[4][0] = 106;
        d[4][1] = 106;
        d[4][2] = 106;
        d[4][3] = 106;
        d[4][4] = 106;
        d[4][5] = 106;
        d[4][6] = 106;
        d[4][7] = 105;
        d[4][8] = 104;
        d[4][9] = 106;
        d[4][10] = 106;
        d[4][11] = 106;
        d[4][12] = 106;

        d[5][0] = 108;
        d[5][1] = 108;
        d[5][2] = 108;
        d[5][3] = 108;
        d[5][4] = 108;
        d[5][5] = 108;
        d[5][6] = 108;
        d[5][7] = 108;
        d[5][8] = 107;
        d[5][9] = 108;
        d[5][10] = 108;
        d[5][11] = 108;
        d[5][12] = 108;

        d[6][0] = 200;
        d[6][1] = 200;
        d[6][2] = 200;
        d[6][3] = 200;
        d[6][4] = 200;
        d[6][5] = 200;
        d[6][6] = 200;
        d[6][7] = 200;
        d[6][8] = 110;
        d[6][9] = 200;
        d[6][10] = 200;
        d[6][11] = 200;
        d[6][12] = 200;

        d[7][0] = 7;
        d[7][1] = 7;
        d[7][2] = 111;
        d[7][3] = 111;
        d[7][4] = 111;
        d[7][5] = 111;
        d[7][6] = 111;
        d[7][7] = 111;
        d[7][8] = 111;
        d[7][9] = 111;
        d[7][10] = 111;
        d[7][11] = 111;
        d[7][12] = 111;

        d[8][0] = 112;
        d[8][1] = 8;
        d[8][2] = 112;
        d[8][3] = 112;
        d[8][4] = 112;
        d[8][5] = 112;
        d[8][6] = 112;
        d[8][7] = 112;
        d[8][8] = 112;
        d[8][9] = 112;
        d[8][10] = 112;
        d[8][11] = 112;
        d[8][12] = 112;
    }
    
    public int getEstado(int q, char c){
        String s;
        int n = 0;
        if (c >= 65 && c <= 90 || c >= 97 && c <= 122) {
            s = "L";
        } else if (c >= 49 && c <= 57) {
            s = "D";
        } else if (c == 43) {
            s = "+";
        } else if (c == 45) {
            s = "-";
        } else if (c == 42) {
            s = "*";
        } else if (c == 47) {
            s = "/";
        } else if (c == 60) {
            s = "<";
        } else if (c == 62) {
            s = ">";
        } else if (c == 58) {
            s = ":";
        } else if (c == 10 || c == 32 || c == 9 || c == 13) {
            s = "b";
        } else if (c == 59) {
            s = ";";
        } else if (c == 61) {
            s = "=";
        } else {
            s = "otro";
        }
        for (int i = 0; i < alfabeto.length; i++) {
            if (s.equals(alfabeto[i])) {
                n = i;
                break;
            }
        }
        return d[q][n];
    }
    
    public int guarda(String componente){
        int n = 20;
        for (int i = 0; i < 20; i++) {
            String palabra = tablaSimbolos[i];
            if (!componente.equals(palabra)) {
            } else {
                return i;
            }
        }
        return n;
    }
}