package co.edu.unbosque.controller;

import co.edu.unbosque.view.ViewConsole;
import co.edu.unbosque.model.Nodo;
import co.edu.unbosque.model.PilaVector;
import co.edu.unbosque.model.ArbolBinario;
import co.edu.unbosque.model.ArbolBinarioBusqueda;

import java.lang.invoke.SwitchPoint;

public class Controller {

    private ViewConsole v;
    private Nodo a,a1,a2 ;
    private ArbolBinario arbol ;
    private ArbolBinarioBusqueda abb = new ArbolBinarioBusqueda() ;
    private PilaVector pila = new PilaVector();
    private int caso;

    public Controller (){
        	v = new ViewConsole();
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
                        abb.insertar(numAdd);
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
                        abb.print2D(abb.getRaiz());
                        System.out.println(abb.getRaiz());
                        start();
                        break;
                }

            }catch (Exception e) {
                    e.printStackTrace();
            }


    }


}