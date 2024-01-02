import java.util.Scanner;
// @author LuisR
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese la cadena a evaluar (Solo 0 y 1): ");
        String w = sc.nextLine();
        AFN afd = new AFN();
        int n = 0;
        String q = afd.getEstadoInic();
        String[] conjRes = null;
        while (n < w.length()) {
            if (n != 0) {
                conjRes = afd.d(conjRes, w.substring(n, n + 1));
            } else {
                conjRes = afd.funcionInic(q, w.substring(n, n + 1));
            }
            n++;
        }
        System.out.println(afd.evaluar(conjRes));
    }
}