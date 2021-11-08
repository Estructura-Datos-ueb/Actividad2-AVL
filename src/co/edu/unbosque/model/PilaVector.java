package co.edu.unbosque.model;

import java.util.Vector;

public class PilaVector {
    private static final int Inicial=19;
    private int cima=-1;
    private Vector listaPila;

    public PilaVector(){
        cima=-1;
        listaPila = new Vector(Inicial);
    }

    public void insertar(Object elemento) {
        cima++;
        listaPila.addElement(elemento);
    }
    public Object quitar()throws Exception{
        Object aux;
        if(pilaVacia()){
            throw new Exception("Pila vacia, no se puede extraer");
        }
        aux=listaPila.elementAt(cima);
        listaPila.removeElementAt(cima);
        cima--;
        return aux;
    }
    public boolean  pilaVacia(){
        return cima==-1;
    }
    public Object  cimaPila() throws Exception{
        if(pilaVacia()){
            throw new Exception("La pila se encuentra vacia");
        }
        return  listaPila.elementAt(cima);
    }

    public void  limpiarPila()throws Exception{
        while (!pilaVacia()){
            quitar();
        }
    }
}
