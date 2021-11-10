package co.edu.unbosque.controller;

import co.edu.unbosque.model.*;
import co.edu.unbosque.view.ViewConsole;

import java.lang.invoke.SwitchPoint;

public class Controller {

    private ViewConsole v;
    private Nodo a,a1,a2 ;
    private ArbolBinario arbol ;
    private ArbolBinarioBusqueda abb = new ArbolBinarioBusqueda() ;
    private PilaVector pila = new PilaVector();
    private int caso;
    private ArbolAvl avl;

    public Controller (){
        	v = new ViewConsole();
        	avl = new ArbolAvl();
        	start();
    }
    
    public void start() {

            try {
                v.mostrarInformacion("Binvenidos a Arboles Binarios");
                String[] opciones = {"Agregar elemento", "Eliminar elemento", "Buscar elemento",
                        "Recorrer preorden", "Recorrer inorden", "Recorrer postorden" , "Mostrar arbol abb por consola"};
                String metodo = v.mostrarLista("Seleccione una opcion", opciones);

                switch (metodo){
                    case "Agregar elemento":
                        int numAdd =Integer.parseInt(v.leerDato("Ingrese el dato que desea agregar en el arbol abb"));
                        avl.insertarr(numAdd);
                        start();
                        break;
                    case "Eliminar elemento":
                        int numDel =Integer.parseInt(v.leerDato("Ingrese el dato que desea eliminar en el arbol abb"));
                        abb.eliminar(numDel);
                        start();
                        break;
                    case "Buscar elemento":
                        int numBusc =Integer.parseInt(v.leerDato("Ingrese el dato que desea buscar en el arbol abb"));
                        if( abb.buscar(numBusc)==null){
                            v.mostrarInformacion("El numero ingresado no se encunetra en el arbol abb");
                        }else{
                            v.mostrarInformacion("El numero ingresado  se encuentra en el arbol abb");
                        }
                        start();
                        break;
                    case "Recorrer preorden":
                        ArbolBinario.preOrden(abb.getRaiz());
                        v.mostrarInformacion(arbol.getOrden());
                        arbol.setOrden(null);
                        start();
                        break;
                    case "Recorrer inorden":
                        ArbolBinario.inOrden(abb.getRaiz());
                        v.mostrarInformacion(arbol.getOrden());
                        arbol.setOrden(null);
                        start();
                        break;
                    case "Recorrer postorden":
                        ArbolBinario.postOrden(abb.getRaiz());
                        v.mostrarInformacion(arbol.getOrden());
                        arbol.setOrden(null);
                        start();
                        break;
                    case "Mostrar arbol abb por consola":
                        avl.print2D(avl.raizArbol());
                        System.out.println(avl.raizArbol());
                        start();
                        break;
                }

            }catch (Exception e) {
                    e.printStackTrace();
            }


    }


}