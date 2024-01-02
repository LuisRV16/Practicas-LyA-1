package afnaafd;
// @author LuisR
import java.util.Set;
import java.util.TreeSet;
public class AFNaAFD {
    private final String[] conjEst;
    private final String[] alfabeto;
    private String q0;
    private String[] conjEstFin;
    private final Set<String>[][] funTransicion;
    private int fila;
    private int columna;
    private final Set<String> vacio = new TreeSet<>();
    public Set<String> getVacio() {
        return vacio;
    }
    public AFNaAFD(){
        vacio.add("{ }");
        conjEst = new String[3];
        alfabeto = new String[2];
        funTransicion = new TreeSet[3][2];
        conjEstFin = new String[2];
        conjEstFin[0] = "q1";
        conjEstFin[1] = "q3";
        fila = columna = 0;
        llenar();
        this.q0 = "q0";
        funTransicion[0][0] = new TreeSet<>();
        funTransicion[0][0].add("q2");
        funTransicion[0][1] = new TreeSet<>();
        funTransicion[0][1].add("q1");
        funTransicion[1][0] = new TreeSet<>();
        funTransicion[1][0].add("q0");
        funTransicion[1][1] = new TreeSet<>();
        funTransicion[1][1].add("q2");
        funTransicion[2][0] = new TreeSet<>();
        funTransicion[2][0].add("q1");
        funTransicion[2][0].add("q2");
        funTransicion[2][1] = new TreeSet<>();
        funTransicion[2][1].add("q0");
        funTransicion[2][1].add("q2");
    }
    public AFNaAFD(int cantQ, int cantA){
        vacio.add("");
        conjEst = new String[cantQ];
        alfabeto = new String[cantA];
        funTransicion = new TreeSet[cantQ][cantA];
        fila = columna = 0;
        llenar();
    }
    private void llenar(){
        for (int i = 0; i < conjEst.length; i++) {
            conjEst[i] = "q"+i;
        }
        for (int i = 0; i < alfabeto.length; i++) {
            alfabeto[i] = i+"";
        }
    }
    private String getConjunto(String simbolo, String[] conjunto){
        String s = simbolo + " = {";
        for (int i = 0; i < conjunto.length; i++) {
            s += conjunto[i];
            if (i != conjunto.length-1) {
                s += ", ";
            } else {
                s += "}";
            }
        }
        return s;
    }
    public String getQ(){
        return getConjunto("Q", conjEst);
    }
    public String getE(){
        return getConjunto("Σ", alfabeto);
    }
    public void setEstInic(String q0){
        this.q0 = q0;
    }
    public String getEstInic(){
        return q0;
    }
    public void setConjEstFin(Set<String> estFin){
        Object[] temp = estFin.toArray();
        conjEstFin = new String[estFin.size()];
        for (int i = 0; i < estFin.size(); i++) {
            conjEstFin[i] = (String)temp[i];
        }
    }
    public String getF(){
        return getConjunto("F", conjEstFin);
    }
    public void setD(Set<String> conjuntoD){
        funTransicion[fila][columna] = conjuntoD;
        columna++;
        valIncremento();
    }
    private void valIncremento(){
        if (columna == alfabeto.length) {
            fila++;
            columna = 0;
        }
    }
    public Set<String>[][] getFunTransicion(){
        return funTransicion;
    }
    public String getD(){
        String matriz = "";
        for (int i = 0; i < funTransicion.length; i++) {
            for (int j = 0; j < funTransicion[0].length; j++) {
                Set temp = funTransicion[i][j];
                if (!temp.contains("")) {
                    String s = temp.toString();
                    String s1 = "";
                    for (int k = 1; k < s.length()-1; k++) {
                        s1 += s.charAt(k);
                    }
                    s1 = "{"+s1+"}";
                    matriz += s1;
                } else {
                    matriz += "{ }";
                }
            }
            matriz += "\n";
        }
        return matriz;
    }
}
