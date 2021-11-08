package co.edu.unbosque.model;

import org.w3c.dom.Node;

public class ArbolBinarioBusqueda extends  ArbolBinario implements Comparador{
    public Comparador comparador;
    public ArbolBinarioBusqueda() {
        super();
    }

    public Nodo buscar(Object buscado){
        Object elmBuscado= buscado;
        if(raiz==null){
            return null;
        }else{
            return localizar (raizArbol(),elmBuscado);
        }
    }
    protected Nodo localizar(Nodo raizSub,Object elmBuscado){
        if(raizSub==null){
            return null;
        }else if (igualQue(raizSub.getDato(),elmBuscado)){
            return raizSub;
        }
        else if (menorQue(raizSub.getDato(),elmBuscado)){
            return localizar(raizSub.getIzquierdo(),elmBuscado);
        }else {
            return localizar(raizSub.getDerecho(),elmBuscado);
        }
    }

    public Nodo insertar(Object valor )throws Exception{
        Object elmInsert= valor;
        raiz = insertar(raiz, elmInsert);
        return raiz;
    }

    protected Nodo insertar(Nodo raizSub, Object elmInsert)throws Exception{
        if(raizSub==null){
            raizSub = new Nodo(null,elmInsert,null);
        }else if(menorQue(raizSub.getDato(),elmInsert)){
            Nodo izq;
            if(raizSub.getIzquierdo()==null){
                izq = new Nodo(null,elmInsert,null);
                raizSub.setIzquierdo(izq);
            }else{
                insertar(raizSub.getIzquierdo(),elmInsert);
            }

        }else if(mayorQue(raizSub.getDato(),elmInsert)) {
            Nodo drch;
            if(raizSub.getDerecho()==null){
                drch = new Nodo(null,elmInsert,null);
                raizSub.setDerecho(drch);
            }else{
                insertar(raizSub.getDerecho(),elmInsert);
            }

        }else{
            throw new Exception("Nodo duplicado");
        }
        return raizSub;
    }



    @Override
    public boolean mayorQue(Object q1, Object q2) {
        try{
            int val1 = Integer.parseInt((String) q1.toString());
            int val2= Integer.parseInt((String) q2.toString());
            if(val2>val1){
                return true;
            }else {
                return false;
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean menorQue(Object q1, Object q2) {
        try{
            int val1 = Integer.parseInt((String) q1.toString());
            int val2= Integer.parseInt((String) q2.toString());
            if(val2<val1){
                return true;
            }else {
                return false;
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean menorIgualQue(Object q) {
        return false;
    }

    @Override
    public boolean igualQue(Object q1, Object q2) {
        try{
            int val1 = Integer.parseInt((String) q1.toString());
            int val2= Integer.parseInt((String) q2.toString());
            if(val2==val1){
                return true;
            }else {
                return false;
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean mayorIgualQue(Object q) {
        return false;
    }

    public void eliminar (Object valor) throws Exception {
        Object dato;
        dato = valor;
        raiz = eliminar(raiz, dato);
    }

    public Nodo eliminar (Nodo raizSub, Object dato) throws Exception
    {
        if (raizSub == null)
            throw new Exception ("No encontrado el nodo con la clave");
        else if (menorQue(raizSub.getDato(),  dato))
        {
            Nodo iz;
            iz = eliminar(raizSub.getIzquierdo(), dato);
            raizSub.setIzquierdo(iz);
        }else if (mayorQue(raizSub.getDato(), dato)) {
            Nodo dr;
            dr = eliminar(raizSub.getDerecho(), dato);
            raizSub.setDerecho(dr);
        }else{
            Nodo q;
            q = raizSub; // nodo a quitar del árbol
            if (q.getIzquierdo() == null)
                raizSub = q.getDerecho();
            else if (q.getDerecho() == null)
                raizSub = q.getIzquierdo();
            else
            { // tiene rama izquierda y derecha
                q = reemplazar(q);
            }
            q = null;
        }
        return raizSub;
    }
    // método interno para susutituir por el mayor de los menores
    private Nodo reemplazar(Nodo act)
    {
        Nodo a, p;
        p = act;
        a = act.getIzquierdo(); // rama de nodos menores
        while (a.getDerecho() != null)
        {
            p = a;
            a = a.getDerecho();
        }
        act.setDato(a.getDato());
        if (p == act)
            p.setDerecho(a.getIzquierdo());
        else
            p.setIzquierdo(a.getIzquierdo());
        return a;
    }


}
