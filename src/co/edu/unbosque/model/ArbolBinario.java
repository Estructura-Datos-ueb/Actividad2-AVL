package co.edu.unbosque.model;

public class ArbolBinario {
    private static String orden;
    protected Nodo raiz;

    public ArbolBinario() {
        raiz=null;
    }

    public ArbolBinario(Nodo raiz) {
        this.raiz = raiz;
    }
    public Nodo raizArbol(){
        return raiz;
    }
    public boolean arbolVacio(){
        return raiz==null;
    }

    public static  Nodo nuevoArbol(Nodo ramaIzq,Object dato, Nodo ramaDcha){
        return  new Nodo(ramaIzq,dato,ramaDcha);
    }

    public static void preOrden(Nodo r){
        if(r != null){
            visitar(r.getDato());
            preOrden(r.getIzquierdo());
            preOrden(r.getDerecho());
        }
    }

    public static void inOrden(Nodo r){
        if(r != null){

            inOrden(r.getIzquierdo());
            visitar(r.getDato());
            inOrden(r.getDerecho());
        }
    }
    public static void postOrden(Nodo r){
        if(r != null){
            postOrden(r.getIzquierdo());
            postOrden(r.getDerecho());
            visitar(r.getDato());
        }
    }
    public static void visitar(Object dato){
        String p=orden+"";
        if(!p.equalsIgnoreCase("null")){
            orden= orden+""+dato+" , ";
        }else{
            orden= dato+" , ";
        }

    }

    // Function to print binary tree in 2D
// It does reverse inorder traversal
    public void print2DUtil(Nodo root, int space)
    {
        // Base case
        if (root == null)
            return;

        // Increase distance between levels
        space += 10;

        // Process right child first
        print2DUtil(root.getDerecho(), space);

        // Print current node after space
        // count
        System.out.print("\n");
        for (int i = 10; i < space; i++)
            System.out.print(" ");
        System.out.print(root.getDato() + "\n");

        // Process left child
        print2DUtil(root.getIzquierdo(), space);
    }

    // Wrapper over print2DUtil()
    public void print2D(Nodo root)
    {
        // Pass initial space count as 0
        print2DUtil(root, 0);
    }

    public static String getOrden() {
        return orden;
    }

    public static void setOrden(String orden) {
        ArbolBinario.orden = orden;
    }

    public Nodo getRaiz() {
        return raiz;
    }
}
