package co.edu.unbosque.model;

public class Nodo {
    protected Object dato;
    protected Nodo izquierdo;
    protected Nodo derecho;

    public Nodo(Object valor){

    }

    public Nodo(Nodo izquierdo,Object dato, Nodo derecho)  {
        this.dato = dato;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public Nodo getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(Nodo izquierdo) {
        this.izquierdo = izquierdo;
    }

    public Nodo getDerecho() {
        return derecho;
    }

    public void setDerecho(Nodo derecho) {
        this.derecho = derecho;
    }

    @Override
    public String toString() {
        return "Nodo{" +
                "dato=" + dato +
                ", izquierdo=" + izquierdo +
                ", derecho=" + derecho +
                '}';
    }
}
