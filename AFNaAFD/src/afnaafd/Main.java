package afnaafd;
// @author LuisR
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cantQ = 0;
        int cantA = 0;
        int valQ0 = -1;
        String q0 = null;
        String q = null;
        AFNaAFD conv;
        while (cantQ < 1 || cantQ > 10) {
            try {
                System.out.print("Ingrese la cantidad del conjunto finito de estados (1-10): ");
                cantQ = sc.nextInt();
                if (cantQ < 1 || cantQ > 10) {
                    System.out.println("Solo valores de 1 a 10.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un número entero válido.\n");
                sc.next();
            }
        }

        while (cantA < 1 || cantA > 3) {
            try {
                System.out.print("Ingrese la cantidad de simbolos del alfabeto de entrada (1-3): ");
                cantA = sc.nextInt();
                if (cantA < 1 || cantA > 3) {
                    System.out.println("Solo valores de 1 a 3.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un número entero válido.\n");
                sc.next();
            }
        }

        conv = new AFNaAFD(cantQ, cantA);

        System.out.println(conv.getQ());
        System.out.println(conv.getE());

        char qLetra = 0;

        while (qLetra != 'q' || valQ0 < 0 || valQ0 >= cantQ) {
            try {
                System.out.print("Ingrese el estado inicial: ");
                q0 = sc.next();
                qLetra = q0.charAt(0);
                valQ0 = Integer.parseInt(q0.substring(1));
                if (qLetra != 'q' && (valQ0 < 0 || valQ0 >= cantQ)) {
                    System.out.println("Redaccion incorrecta.\n");
                } else if (qLetra != 'q') {
                    System.out.println("""
                                       El estado inicial debe iniciar con la letra q,
                                       y esta, posteriormente, tener un valor valido
                                       dentro del conjunto de estados.\n""");
                } else if (valQ0 < 0 || valQ0 >= cantQ) {
                    System.out.println("El conjunto de estados abarca de q0 a q" + (cantQ - 1));
                    System.out.println();
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Debe ingresar un estado válido dentro del conjunto finito de estados.\n");
            }
        }

        conv.setEstInic(q0);

        System.out.println("q\u2080 = " + conv.getEstInic());

        boolean band = true;
        Set<String> conjTemp = new TreeSet<>();

        while (band) {
            try {
                qLetra = 0;
                valQ0 = -1;
                boolean val = false;
                while (qLetra != 'q' || valQ0 < 0 || valQ0 >= cantQ || val) {
                    try {
                        System.out.print("Ingrese el estado para el conjunto de estados finales: ");
                        q = sc.next();
                        qLetra = q.charAt(0);
                        valQ0 = Integer.parseInt(q.substring(1));
                        val = conjTemp.contains(q);
                        if (qLetra != 'q' && (valQ0 < 0 || valQ0 >= cantQ)) {
                            System.out.println("Redaccion incorrecta.\n");
                        } else if (qLetra != 'q') {
                            System.out.println("""
                                       El estado debe iniciar con la letra q,
                                       y esta, posteriormente, tener un valor valido
                                       dentro del conjunto de estados.\n""");
                        } else if (valQ0 < 0 || valQ0 >= cantQ) {
                            System.out.println("El conjunto de estados abarca de q0 a q" + (cantQ - 1));
                            System.out.println();
                        } else if (val) {
                            System.out.println("El estado final que acaba de ingresar ya fue ingresado con anterioridad.\n");
                        }
                    } catch (InputMismatchException | NumberFormatException e) {
                        System.out.println("Debe ingresar un estado válido dentro del conjunto finito de estados.\n");
                    }
                }
                conjTemp.add(q);
                if (conjTemp.size() == cantQ) {
                    band = false;
                } else {
                    System.out.println("Desea seguir ingresando mas estados al mismo conjunto? (true/false)");
                    band = sc.nextBoolean();
                }
            } catch (InputMismatchException e) {
                System.out.println("""
                                   Solamente se permite ingresar true o false.
                                   Si se desea seguir ingresando mas estados, debe ingresar true.
                                   Si no se desea seguir ingresando mas estados, debe ingresar false.\n""");
                band = true;
            }
        }

        conv.setConjEstFin(conjTemp);
        System.out.println(conv.getF());

        for (int i = 0; i < cantQ; i++) {
            for (int j = 0; j < cantA; j++) {
                System.out.println("δ(q" + i + ", " + j + ")");
                band = true;
                conjTemp = new TreeSet<>();
                while (band) {
                    try {
                        if (conjTemp.isEmpty()) {
                            System.out.println("Desea ingresar un conjunto vacio? (true/false)");
                            band = sc.nextBoolean();
                        } else {
                            band = false;
                        }
                        if (band) {
                            q = "";
                        } else {
                            qLetra = 0;
                            valQ0 = -1;
                            boolean val = false;
                            while (qLetra != 'q' || valQ0 < 0 || valQ0 >= cantQ || val) {
                                try {
                                    System.out.print("Ingrese el estado para la funcion de transicion de q" + i + " con " + j + ": ");
                                    q = sc.next();
                                    qLetra = q.charAt(0);
                                    valQ0 = Integer.parseInt(q.substring(1));
                                    val = conjTemp.contains(q);
                                    if (qLetra != 'q' && (valQ0 < 0 || valQ0 >= cantQ)) {
                                        System.out.println("Redaccion incorrecta.\n");
                                    } else if (qLetra != 'q') {
                                        System.out.println("""
                                       El estado debe iniciar con la letra q,
                                       y esta, posteriormente, tener un valor valido
                                       dentro del conjunto de estados.\n""");
                                    } else if (valQ0 < 0 || valQ0 >= cantQ) {
                                        System.out.println("El conjunto de estados abarca de q0 a q" + (cantQ - 1));
                                        System.out.println();
                                    } else if (val) {
                                        System.out.println("El estado final ingresado ya fue ingresado con anterioridad.\n");
                                    }
                                } catch (InputMismatchException | NumberFormatException e) {
                                    System.out.println("Debe ingresar un estado válido dentro del conjunto finito de estados.\n");
                                }
                            }
                        }
                        conjTemp.add(q);
                        if (q.equals("") || conjTemp.size() == cantQ) {
                            band = false;
                        } else {
                            System.out.println("Desea seguir ingresando mas estados al mismo conjunto? (true/false)");
                            band = sc.nextBoolean();
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("""
                                   Solamente se permite ingresar true o false.
                                   Si se desea ingresar un conjunto vacio, debe ingresar true.
                                   Si no se desea ingresar un conjunto vacio, debe ingresar false.\n""");
                        band = true;
                        sc.next();
                    }
                }
                conv.setD(conjTemp);
            }
        }

        System.out.println(conv.getD());

        Set<String>[][] listaB = conv.getFunTransicion();

        Set<String> t = new TreeSet<>();
        t.add(conv.getEstInic());

        Set<String> delta = new LinkedHashSet<>();

        ArrayList<String>[] conjDelta = new ArrayList[listaB[0].length];
        for (int i = 0; i < conjDelta.length; i++) {
            conjDelta[i] = new ArrayList<>();
        }

        Set<Set<String>> d = new LinkedHashSet<>();
        d.add(t);
        delta.add(t.toString());

        Iterator<Set<String>> iterador = d.iterator();

        Set<String> c = new LinkedHashSet<>();
        int n1 = 0;
        int n2 = 0;
        int n3 = d.size();

        ArrayList<Set<String>>[] conjD = new ArrayList[listaB[0].length];
        for (int i = 0; i < conjD.length; i++) {
            conjD[i] = new ArrayList<>();
        }

        while (n2 < n3) {
            for (int i = 0; i <= n1; i++) {
                c = iterador.next();
            }
            n1++;
            if (c.size() > 1) {
                for (int i = 0; i < conjD.length; i++) {
                    Set<String> temp = new TreeSet<>();
                    boolean band1 = true;
                    for (String s : c) {
                        if (s.equals("{ }") || s.equals("")) {
                            t = conv.getVacio();
                        } else {
                            t = listaB[Integer.parseInt(s.substring(1))][i];
                        }
                        temp = t;
                        band1 = band1 && t.toString().equals("[{ }]");
                    }
                    if (!band1) {
                        temp = new TreeSet<>();
                        Object[] arrayT = c.toArray();
                        for (Object o : arrayT) {
                            String s = (String) o;
                            if (s.equals("{ }") || s.equals("")) {
                                t = conv.getVacio();
                            } else {
                                t = listaB[Integer.parseInt(s.substring(1))][i];
                            }
                            if (!(t.toString().equals("[{ }]") || t.toString().equals("[]"))) {
                                for (String s1 : t) {
                                    temp.add(s1);
                                }
                            }
                        }
                    }
                    conjD[i].add(temp);
                    conjDelta[i].add(temp.toString());
                    d.add(temp);
                    delta.add(temp.toString());
                }
            } else {
                for (String s : c) {
                    for (int i = 0; i < conjD.length; i++) {
                        if (s.equals("{ }") || s.equals("")) {
                            t = conv.getVacio();
                        } else {
                            t = listaB[Integer.parseInt(s.substring(1))][i];
                        }
                        conjD[i].add(t);
                        conjDelta[i].add(t.toString());
                        d.add(t);
                        delta.add(t.toString());
                    }
                }
            }
            iterador = d.iterator();
            n2++;
            n3 = d.size();
        }

        Object[] array = delta.toArray();
        String[][] afdC = new String[array.length][conjDelta.length + 1];

        for (int i = 0; i < array.length; i++) {
            afdC[i][0] = (String) array[i];
        }
        for (int i = 0; i < conjDelta.length; i++) {
            for (int j = 0; j < array.length; j++) {
                afdC[j][i + 1] = conjDelta[i].get(j);
            }
        }
        for (int i = 0; i < afdC.length; i++) {
            for (int j = 0; j < afdC[0].length; j++) {
                System.out.printf("%21s", afdC[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        HashMap<String, String> renombre = new HashMap<>();
        for (int i = 0; i < afdC.length; i++) {
            renombre.put(afdC[i][0], "r" + i);
        }
        String[][] afd = new String[afdC.length][afdC[0].length];
        for (int i = 0; i < afd.length; i++) {
            for (int j = 0; j < afd[0].length; j++) {
                afd[i][j] = renombre.get(afdC[i][j]);
            }
        }
        for (int i = 0; i < afd.length; i++) {
            for (int j = 0; j < afd[0].length; j++) {
                System.out.printf("%5s", afd[i][j]);
            }
            System.out.println();
        }
    }
}
