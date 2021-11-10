package co.edu.unbosque.model;

public class ArbolAvl extends ArbolBinarioBusqueda{
    NodoAvl raiz;
    public ArbolAvl(){
        raiz=null;
    }
    public NodoAvl raizArbol(){
        return raiz;
    }

    public Nodo buscar(Object buscado){
        Object elmBuscado= buscado;
        if(raiz==null){
            return null;
        }else{
            return localizar (raizArbol(),elmBuscado);
        }
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

    public void eliminar (Object valor) throws Exception
    {
        Object dato;
        dato =  valor;
        Logical flag = new Logical(false);
        raiz = borrarAvl(raiz, dato, flag);
    }

    private NodoAvl borrarAvl(NodoAvl r, Object clave,
                              Logical cambiaAltura) throws Exception
    {
        if (r == null)
        {
            throw new Exception (" Nodo no encontrado ");
        }
        else if (menorQue(r.getDato(), clave))
        {
            NodoAvl iz;
            iz = borrarAvl((NodoAvl)r.getIzquierdo(), clave, cambiaAltura);
            r.setIzquierdo(iz);
            if (cambiaAltura.booleanValue())
                r = equilibrar1(r, cambiaAltura);
        }
        else if (mayorQue(r.getDato(), clave))
        {
            NodoAvl dr;
            dr = borrarAvl((NodoAvl)r.getDerecho(), clave, cambiaAltura);
            r.setDerecho(dr);
            if (cambiaAltura.booleanValue())
                r = equilibrar2(r, cambiaAltura);
        }
        else // Nodo encontrado
        {
            NodoAvl q;
            q = r; // nodo a quitar del árbol
            if (q.getIzquierdo()== null)
            {
                r = (NodoAvl) q.getDerecho();
                cambiaAltura.setLogical(true);
            }
            else if (q.getDerecho() == null)
            {
                r = (NodoAvl) q.getIzquierdo();
                cambiaAltura.setLogical(true);
            }
            else
            { // tiene rama izquierda y derecha
                NodoAvl iz = reemplazar(r, (NodoAvl)r.getIzquierdo(), cambiaAltura);
                r.setIzquierdo(iz);
                if (cambiaAltura.booleanValue())
                    r = equilibrar1(r, cambiaAltura);
            }
            q = null;
        }
        return r;
    }
    private
    NodoAvl reemplazar(NodoAvl n, NodoAvl act, Logical cambiaAltura)
    {
        if (act.getDerecho() != null)
        {
            NodoAvl d;
            d = reemplazar(n, (NodoAvl)act.getDerecho(), cambiaAltura);
            act.setDerecho(d);
            if (cambiaAltura.booleanValue())
                act = equilibrar2(act, cambiaAltura);
        }
        else
        {
            n. setDato(act.getDato());
            n = act;
            act = (NodoAvl)act.getIzquierdo();
            n = null;
            cambiaAltura.setLogical(true);
        }
        return act;
    }
    private NodoAvl equilibrar1(NodoAvl n, Logical cambiaAltura)
    {
        NodoAvl n1;
        switch (n.fe)
        {
            case -1 : n.fe = 0;
                break;
            case 0 : n.fe = 1;
                cambiaAltura.setLogical(false);
                break;
            case +1 : //se aplicar un tipo de rotación derecha
                n1 = (NodoAvl)n.getDerecho();
                if (n1.fe >= 0)
                {
                    if (n1.fe == 0) //la altura no vuelve a disminuir
                        cambiaAltura.setLogical(false);
                    n = rotacionDD(n, n1);
                }
                else
                    n = rotacionDI(n, n1);
                break;
        }
        return n;
    } private NodoAvl equilibrar2(NodoAvl n, Logical cambiaAltura)
    {
        NodoAvl n1;
        switch (n.fe)
        {
            case -1: // Se aplica un tipo de rotación izquierda
                n1 = (NodoAvl)n.getIzquierdo();
                if (n1.fe <= 0)
                {
                    if (n1.fe == 0)
                        cambiaAltura.setLogical(false);
                    n = rotacionII(n, n1);
                }
                else
                    n = rotacionID(n,n1);
                break;
            case 0 : n.fe = -1;
                cambiaAltura.setLogical(false);
                break;
            case +1 : n.fe = 0;
                break;
        }
        return n;
    }

    @Override
    public NodoAvl getRaiz() {

        return raiz;
    }

    public void setRaiz(NodoAvl raiz) {
        this.raiz = raiz;
    }
}
