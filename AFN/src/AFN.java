import java.util.ArrayList;
import java.util.HashMap;
// @author LuisR
public class AFN {
    private final String q;
    private final String[] conjEst = {"q0", "q1", "q2", "q3", "q4"};
    private final String[] conjFin = {conjEst[2], conjEst[4]};
    private final String[] q0_0 = {conjEst[0], conjEst[3]};
    private final String[] q0_1 = {conjEst[0], conjEst[1]};
    private final String[] q1_0 = null;
    private final String[] q1_1 = {conjEst[2]};
    private final String[] q2_0 = {conjEst[2]};
    private final String[] q2_1 = {conjEst[2]};
    private final String[] q3_0 = {conjEst[4]};
    private final String[] q3_1 = null;
    private final String[] q4_0 = {conjEst[4]};
    private final String[] q4_1 = {conjEst[4]};
    private final HashMap<String, String[]> funTran = new HashMap<>();

    public AFN() {
        q = conjEst[0];
        funTran.put("q0-0", q0_0);
        funTran.put("q0-1", q0_1);
        funTran.put("q1-0", q1_0);
        funTran.put("q1-1", q1_1);
        funTran.put("q2-0", q2_0);
        funTran.put("q2-1", q2_1);
        funTran.put("q3-0", q3_0);
        funTran.put("q3-1", q3_1);
        funTran.put("q4-0", q4_0);
        funTran.put("q4-1", q4_1);
    }

    public String getEstadoInic() {
        return q;
    }

    public String[] funcionInic(String q, String s) {
        return funTran.get(q + "-" + s);
    }

    public String evaluar(String[] res) {
        boolean band = false;
        if (res != null) {
            for (int i = 0; i < conjFin.length; i++) {
                for (int j = 0; j < res.length; j++) {
                    if (conjFin[i].equals(res[j])) {
                        band = true;
                        break;
                    }
                }
                if (band) {
                    break;
                }
            }
        }
        if (band) {
            return "La cadena es aceptada";
        } else {
            return "La cadena no es aceptada";
        }
    }

    public String[] d(String[] funTr, String s) {
        String[] conjunto;
        ArrayList<String[]> temp = new ArrayList<>();
        for (int i = 0; i < funTr.length; i++) {
            temp.add(funcionInic(funTr[i], s));
        }
        if (temp.size() < 2) {
            conjunto = temp.get(0);
        } else {
            conjunto = unionEstados(temp);
        }
        return conjunto;
    }

    private String[] unionEstados(ArrayList<String[]> temp) {
        ArrayList<String> estRes = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            String[] estados = temp.get(i);
            if (estados != null) {
                for (int j = 0; j < estados.length; j++) {
                    estRes = ingresarElemento(estRes, estados[j]);
                }
            }
        }
        return convArrayObjToString(estRes.toArray());
    }

    private ArrayList<String> ingresarElemento(ArrayList<String> estRes, String q) {
        boolean existe = estRes.contains(q);
        if (!existe) {
            estRes.add(q);
        }
        return estRes;
    }

    private String[] convArrayObjToString(Object[] array) {
        String[] arrayCadenas = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            arrayCadenas[i] = array[i].toString();
        }
        return arrayCadenas;
    }
}