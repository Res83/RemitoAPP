/*
 * RemitoAPP     / Version 1.0
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
        
//EntornoGrafico.LoginxPantalla VB=new EntornoGrafico.LoginxPantalla();
    VB.setLocationRelativeTo(null);
    
// Al Usar esto VB.setLocationRelativeTo(null) establezco
//    la ventana en el centro de la pantalla.
 VB.setVisible(true);

 
}

}
