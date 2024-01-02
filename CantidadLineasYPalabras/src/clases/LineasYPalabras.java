package clases;
// @author LuisR
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LineasYPalabras {

    private final File archivo;

    private int numLineas;
    private int numPalabras;

    private final StringBuilder textoArchivo;

    private String textoArc;

    /*
     *  El constructor recibe un parámetro de tipo File, el cual es el archivo
     *  seleccionado por el usuario, posteriormente se instancia el atributo
     *  de tipo StringBuilder.
     */
    public LineasYPalabras(File archivo) {

        this.archivo = archivo;

        textoArchivo = new StringBuilder();

    }

    // Método que cuenta la cantidad de lineas del archivo
    private void cantLineas() throws IOException {

        /*
            Se hace uso de un objeto de la clase BufferedReader para una mayor
            optimización en la velocidad de la lectura del archivo, recibe como
            parámetro en el constructo una instancia de la clase FileReader
            que a su vez recibe este en el constructor el archivo seleccionado
            por el usuario.
         */
        BufferedReader lector = new BufferedReader(new FileReader(archivo));

        String linea = lector.readLine();

        /*
            Si la linea de texto es distinta de nulo, entonces la variable que
            lleva el conteo de líneas del archivo aumenta en 1, y el objeto
            textoArchivo concatena la linea en curso y le agrega un salto de
            línea al final de esta, posteriormente, la variable "linea" toma el
            valor de la línea siguiente del archivo, esto hasta que el método
            lance el valor null, en ese caso se termina el ciclo.
        
            Esto funciona aún si el archivo está vacío, en ese caso, el valor
            de la primer línea es null, entonces no se realizan las operaciones
            dentro del ciclo.
         */
        while (linea != null) {

            numLineas++;

            textoArchivo.append(linea).append("\n");

            linea = lector.readLine();

        }

    }

    private void cantPalabras() {

        /*
            Al momento de realizar el conteo de líneas, se fue almacenando el
            contenido del archivo en el objeto textoArchivo, se evalua si
            el numero de letras en el objeto textoArchivo es distinto de cero,
            entonces quiere decir que el archivo de texto no está vacío y 
            comienza a realizar el conteo de palabras.
         */
        if (textoArchivo.length() != 0) {

            /* 
               El atributo textArc toma el valor de textoArchivo hasta justo
               antes del ultimo carácter debido a que al momento de realizar el
               conteo de línea, se fue concantenando un salto de línea al final,
               entonces, si el archivo no está vacío, el texto final de texto
               archivo siempre tendrá un salto de línea extra.
             */
            textoArc = textoArchivo.substring(0, textoArchivo.length() - 1);

            int letraFinal = 0;

            /*
                Este ciclo for es para guardar la posición de la última letra
                o numero en textoArchivo, esto para posteriormente utilizar ese
                valor como punto final.
             */
            for (int i = 0; i < textoArchivo.length(); i++) {

                if (textoArchivo.charAt(i) >= 65 && textoArchivo.charAt(i) <= 90
                        || textoArchivo.charAt(i) >= 97 && textoArchivo.charAt(i) <= 122
                        || textoArchivo.charAt(i) >= 48 && textoArchivo.charAt(i) <= 57) {
                    letraFinal = i;
                }

            }

            int temp = 0;

            /*
                Este condicional es para evaluar que textoArchivo no esté vacío,
                es decir, si el archivo de texto seleccionado por el usuario,
                fue un archivo vacío, entonces no debe realizar el conteo de
                palabras y así el resultado del numero de palabras a retornar
                posteriormente, será 0.
             */
            if (letraFinal != 0 && textoArchivo.charAt(letraFinal) != 32
                    || textoArchivo.charAt(letraFinal) != 10) {

                /*
                    Dentro de un ciclo while infinito, se realizan otros dos
                    ciclos for, el primero es para la busqueda de alguna letra
                    del alfabeto o algún numero, si encuentra una letra o numero
                    se interpreta como una palabra, entonces el valor de la
                    variable (int) temporal será la posición en la que inicia
                    o se interpreta que hay una palabra, si no se encuentra
                    ninguna letra o número de igual forma el valor de temp
                    será el valor de i que va en incremento conforme pasa
                    carácter por carácter, esto para continuar con la lectura 
                    del texto y el ciclo tenga un final.
                 */
                while (true) {

                    for (int i = temp; i <= letraFinal; i++) {

                        if (textoArchivo.charAt(i) >= 65 && textoArchivo.charAt(i) <= 90
                                || textoArchivo.charAt(i) >= 97 && textoArchivo.charAt(i) <= 122
                                || textoArchivo.charAt(i) >= 48 && textoArchivo.charAt(i) <= 57) {

                            temp = i;

                            break;

                        } else {
                            temp = i;
                        }

                    }
                    
                    /*
                        Una vez encontrada alguna letra o número, se sale del
                        ciclo anterior e inmediatamente empieza el segundo ciclo
                        a buscar un espacio, salto de línea, o la letra o numero
                        final del texto, esto debido a que la definición de
                        palabra dice:
                            1. Unidad léxica constituida por un sonido o
                               conjunto de sonidos articulados que tienen un
                               significado fijo y una categoría gramatical.
                            2. Representación gráfica de estos sonidos, que 
                               consiste en una letra o un grupo de letras
                               delimitado por espacios blancos.
                        Basandome en el punto 2, se comprende que una palabra
                        termina cuando hay un espacio en blanco, o en su defecto
                        salto de línea o en el ultimo de los casos, que se 
                        inicie la busque de la palabra y esta finalice en la 
                        última letra o numero del texto.
                     */

                    for (int i = temp; i <= letraFinal; i++) {

                        if (textoArchivo.charAt(i) == 32 || textoArchivo.charAt(i) == 10
                                || i != 0 && i == letraFinal) {
                            numPalabras++;

                            temp = i;

                            break;

                        }

                    }

                    /*
                      En caso de que el valor de la variable temporal usada para
                      indicar la posición en la que se buscan los inicios y
                      finales de palabras, llegue a la posición de la letra
                      final, quiere decir que ya no hay más palabras en el texto
                      por ende, se procede a terminar con el ciclo infinito.
                     */
                    if (temp == letraFinal) {
                        break;
                    }

                }

            }

        }

    }

    public void cantLineasYPalabras() throws IOException {
        cantLineas();
        cantPalabras();
    }

    public int getNumLineas() {
        return numLineas;
    }

    public int getNumPalabras() {
        return numPalabras;
    }

    public String getTextoArchivo() {
        return textoArc;
    }

}
