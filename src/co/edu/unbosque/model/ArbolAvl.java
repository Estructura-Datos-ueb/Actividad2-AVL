package co.edu.unbosque.model;

public class ArbolAvl extends ArbolBinarioBusqueda{
    NodoAvl raiz;
    public ArbolAvl(){
        raiz=null;
    }
    public NodoAvl raizArbol(){
        return raiz;
    }

    private NodoAvl rotacionII(NodoAvl n, NodoAvl n1)
    {
        n.setIzquierdo(n1.getDerecho());
        n1.setDerecho(n);
// actualización de los factores de equilibrio
        if (n1.fe == -1) // se cumple en la inserción
        {
            n.fe = 0;
            n1.fe = 0;
        }
        else
        {
            n.fe = -1;
            n1.fe = 1;
        }
        return n1;
    }
    private NodoAvl rotacionDD(NodoAvl n, NodoAvl n1)
    {
        n.setDerecho(n1.getIzquierdo());
        n1.setIzquierdo(n);
// actualización de los factores de equilibrio
        if (n1.fe == +1) // se cumple en la inserción
        {
            n.fe = 0;
            n1.fe = 0;
        }
        else
        {
            n.fe = +1;
            n1.fe = -1;
        }
        return n1;
    }

    private NodoAvl rotacionID(NodoAvl n, NodoAvl n1)
    {
        NodoAvl n2;
        n2 = (NodoAvl) n1.getDerecho();
        n.setIzquierdo(n2.getDerecho());
        n2.setDerecho(n);
        n1.setDerecho(n2.getIzquierdo());
        n2.setIzquierdo(n1);
// actualización de los factores de equilibrio
        if (n2.fe == +1)
            n1.fe = -1;
        else
            n1.fe = 0;
        if (n2.fe == -1)
            n.fe = 1;
        else
            n.fe = 0;
        n2.fe = 0;
        return n2;
    }
    private NodoAvl rotacionDI(NodoAvl n, NodoAvl n1)
    {
        NodoAvl n2;
        n2 = (NodoAvl)n1.getIzquierdo();
        n.setDerecho(n2.getIzquierdo());
        n2.setIzquierdo(n);
        n1.setIzquierdo((n2.getIzquierdo()));
        n2.setDerecho(n1);
// actualización de los factores de equilibrio
        if (n2.fe == +1)
            n.fe = -1;
        else
            n.fe = 0;
        if (n2.fe == -1)
            n1.fe = 1;
        else
            n1.fe = 0;
        n2.fe = 0;
        return n2;
    }
    public NodoAvl insertarAvl(NodoAvl raiz, Object dt, Logical h) throws Exception
    {
        NodoAvl n1;
        if (raiz == null)
        {
            raiz = new NodoAvl(dt);
            h.setLogical(true);
            this.raiz = raiz;
        }
        else if (menorQue(raiz.getDato(), dt))
        {
            NodoAvl iz;
            iz = insertarAvl((NodoAvl) raiz.getIzquierdo(), dt, h);
            raiz.setIzquierdo(iz);
// regreso por los nodos del camino de búsqueda
            if (h.booleanValue())
            {
// decrementa el fe por aumentar la altura de rama izquierda
                switch (raiz.fe)
                {
                    case 1:
                        raiz.fe = 0;
                        h.setLogical(false);
                        break;
                    case 0:
                        raiz.fe = -1;
                        break;
                    case -1: // aplicar rotación a la izquierda
                        n1 = (NodoAvl)raiz.getIzquierdo();
                        if (n1.fe == -1)
                            raiz = rotacionII(raiz, n1);
                        else
                            raiz = rotacionID(raiz, n1);
                        h.setLogical(false);
                }
            }
        }
        else if (mayorQue(raiz.getDato(), dt))
        {
            NodoAvl dr;
            dr = insertarAvl((NodoAvl)raiz.getDerecho(), dt, h);
            raiz.setDerecho(dr);
// regreso por los nodos del camino de búsqueda
            if (h.booleanValue())
            {
// incrementa el fe por aumentar la altura de rama izquierda
                switch (raiz.fe)
                {
                    case 1: // aplicar rotación a la derecha
                        n1 = (NodoAvl)raiz.getDerecho();
                        if (n1.fe == +1)
                            raiz = rotacionDD(raiz, n1);
                        else
                            raiz = rotacionDI(raiz,n1);
                        h.setLogical(false);
                        break;
                    case 0:
                        raiz.fe = +1;
                        break;
                    case -1:
                        raiz.fe = 0;
                        h.setLogical(false);
                }
            }
        }
        else
            throw new Exception("No puede haber claves repetidas " );
        return raiz;
    }

    public void insertarr (Object valor)throws Exception
    {
        Object dato;
        Logical h = new Logical(false); // intercambia un valor booleano
        dato =  valor;
        raiz = insertarAvl(raiz, dato, h);
    }
}
