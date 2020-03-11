/*
 * RemitoAPP     / Version 1.5
 */
package Clases;

import EntornoGrafico.MenuPrimario;
//import EntornoGrafico.Panel_Inicio;

/**
 *
 * @author Raúl Eduardo Scalia
 */
public class Inicio
{

/**
 * @param args the command line arguments
 */
public static void main(String[] args)
{
//Carga la Ventana de Bienvenida y la hace visible
        EntornoGrafico.MenuPrimario VB=new EntornoGrafico.MenuPrimario();
        
        
//EntornoGrafico.LoginxPantalla VB=new EntornoGrafico.LoginxPantalla();
    VB.setLocationRelativeTo(null);
    
// Al Usar esto VB.setLocationRelativeTo(null) establezco
//    la ventana en el centro de la pantalla.
 VB.setVisible(true);  
}

}