import java.util.Scanner;
// @author LuisR
public class AFD {
    String[] conjEstados = {"q0", "q1", "q2", "q3", "q4"};
    int[] alfabeto = {0, 1};
    String q0 = conjEstados[1];
    String[] conjEstFinales = {conjEstados[0], conjEstados[2], conjEstados[4]};
    String[][] transicion = {{"q4", "q3"},
                             {"q2", "q1"},
                             {"q0", "q4"},
                             {"q4", "q3"},
                             {"q0", "q0"}};
    public String d(String estado, int simbolo){
        return transicion[Integer.parseInt(estado.substring(1))][simbolo];
    }
    public String getQ0(){
        return q0;
    }
    public String[] getConjuntosEstadosFinales(){
        return conjEstFinales;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AFD afd = new AFD();
        System.out.print("Introduzca la cadena (compuesta solo por 0 y 1):");
        String w = sc.nextLine();
        String q = afd.getQ0();
        int n = 0;
        String[] cEF = afd.getConjuntosEstadosFinales();
        String res = "";
        while(n < w.length()){
            String s = String.valueOf(w.charAt(n));
            q = afd.d(q, Integer.parseInt(s));
            n++;
        }
        for (int i = 0; i < cEF.length; i++) {
            if (q.equals(cEF[i])) {
                res = "La cadena es aceptada";
                break;
            } else {
                res = "La cadena no es aceptada";
            }
        }
        System.out.println(res);
        
    }
    
}
