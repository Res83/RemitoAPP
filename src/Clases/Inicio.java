/*
 * RemitoAPP     / Version 1.5
 */
package Clases;

import EntornoGrafico.Ventana_inicio_configuración;
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
        EntornoGrafico.Ventana_inicio_configuración VB=new EntornoGrafico.Ventana_inicio_configuración();
        
        
//EntornoGrafico.LoginxPantalla VB=new EntornoGrafico.LoginxPantalla();
    VB.setLocationRelativeTo(null);
    
// Al Usar esto VB.setLocationRelativeTo(null) establezco
//    la ventana en el centro de la pantalla.
 VB.setVisible(true);  
}

}