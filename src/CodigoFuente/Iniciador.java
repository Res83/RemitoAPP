/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodigoFuente;

/**
 *
 * @author Res
 */
public class Iniciador
{

/**
 * @param args the command line arguments
 */
public static void main(String[] args)
{
    //Carga la Ventana de Bienvenida y la hace visible
        EntornoGrafico.Panel_Inicio VB=new EntornoGrafico.Panel_Inicio();
 //        EntornoGrafico.LoginxPantalla VB=new EntornoGrafico.LoginxPantalla();
    VB.setLocationRelativeTo(null);
    // Al Usar esto VB.setLocationRelativeTo(null) establezco la ventana en el medio.
 VB.setVisible(true);  
}

}
