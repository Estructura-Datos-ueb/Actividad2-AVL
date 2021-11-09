package co.edu.unbosque.model;

public class NodoAvl extends Nodo{
    int fe;

    public NodoAvl(Object valor) {
        super(valor);
        fe=0;
    }

    public NodoAvl(Nodo izquierdo, Object dato, Nodo derecho) {
        super(izquierdo, dato, derecho);
        fe=0;
    }
}
