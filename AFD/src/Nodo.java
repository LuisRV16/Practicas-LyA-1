// @author LuisR

public class Nodo {
    
    public String simbolo1;
    public String simbolo2;
    public String simbolo3;
    public Nodo sigEstado1;
    public Nodo sigEstado2;
    public Nodo sigEstado3;
    
    public Nodo(){}
    
    public void camino1(String simbolo, Nodo sigEstado){
        this.simbolo1 = simbolo;
        this.sigEstado1 = sigEstado;
    }
    public void camino2(String simbolo, Nodo sigEstado){
        this.simbolo2 = simbolo;
        this.sigEstado2 = sigEstado;
    }
    public void camino3(String simbolo, Nodo sigEstado){
        this.simbolo3 = simbolo;
        this.sigEstado3 = sigEstado;
    }
    public String getSimbolo1(){
        return simbolo1;
    }
    
    public Nodo getSigEstado(){
        return sigEstado1;
    }
    public String getSimbolo2(){
        return simbolo2;
    }
    
    public Nodo getSigEstado2(){
        return sigEstado2;
    }
    public String getSimbolo3(){
        return simbolo3;
    }
    
    public Nodo getSigEstado3(){
        return sigEstado3;
    }
}
