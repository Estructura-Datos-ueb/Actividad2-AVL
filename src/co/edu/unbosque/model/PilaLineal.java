package co.edu.unbosque.model;

import javax.management.ObjectName;

public class PilaLineal {
    private static final int TAMPILLA= 79;
    private int cima;
    private Object [] listaPila;

    public PilaLineal(){
        cima=-1;
        listaPila= new Object[TAMPILLA];
    }

    public void insertat(Object elemento) throws Exception{
        if (pilaLlena()){
            throw  new Exception("La pila esta llena");
        }
        cima++;
        listaPila[cima]= elemento;
    }

    public Object quitar() throws Exception{
        Object aux;
        if(pilaVacia()){
            throw new Exception("La pila esta vacia");
        }
        aux =listaPila[cima];
        cima--;
        return aux;
    }

    public boolean pilaLlena(){
        return cima==TAMPILLA-1;
    }
    public boolean pilaVacia(){
        return cima==-1;
    }
}
