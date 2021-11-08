package co.edu.unbosque.view;


import javax.swing.JOptionPane;
import java.awt.*;

public class ViewConsole {


    public ViewConsole() {
    }
    public String mostrarLista(String mensaje, Object[] opciones) {
        String oString=null;
        try{
            Object o = JOptionPane.showInputDialog(null,mensaje, "Elegir",JOptionPane.QUESTION_MESSAGE,null,opciones, opciones[0]);
            oString= o.toString();
        } catch (Exception e) {
            System.exit(1);
        }
        return oString;

    }

    public void mostrarInformacion( String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
    
    public Integer mostrarOpcion(String mensaje) {
    	String [] options = {  "Pila de Enteros", "Cola de Reales"};
    	return JOptionPane.showOptionDialog(null,
    			mensaje,
    			"Tipo de Generacion de numeros",
    			JOptionPane.YES_NO_OPTION,
    			JOptionPane.QUESTION_MESSAGE,
    			null,     //do not use a custom Icon
    			options,  //the titles of buttons
    			options[0]); //default button title
    }

    public String leerDato(String mensaje) {
    	String opcion = "";
        opcion = JOptionPane.showInputDialog(mensaje);

        return opcion;
    }




}
