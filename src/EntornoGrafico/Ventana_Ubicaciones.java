package EntornoGrafico;

/*********************************/
//Cosas Cambiar Nueva Ventana:
//
//Lista_de_Ubicaciones
//Lugar
//cone2= conex.CargarDB_Base_datos_Ubicaciones();
//ReAbrirVentanaUbicaciones()
//(Escribe la Nueva Ubicación)

/*********************************/

// Importo la relación con la base de datos
import CodigoFuente.Conexion;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Raul Eduardo Scalia
 * https://github.com/Res83
 */
public class Ventana_Ubicaciones extends javax.swing.JFrame
{
// Cargo la relación con la base de datos //////////////////////////////////////////////////////

    Conexion conex = new Conexion();
    Connection cone2;
     Conexion conexion_BaseDatosUbicaciones = new Conexion();

/////////////////////////////////////////////////////////////////////////////////////////////////
     
   // int contador_de_filas=1;
    private String SeBorroRegistro;
    private String TextoTemporal;
    private int id_borrado_categoria;
    private Integer Comienza_desde_Aqui;
    private String Bandera_Modificando;
    private int seleccion;

public Ventana_Ubicaciones()
{
    initComponents();
    
//Crea y carga Base de Datos /////////////////////////////////////////////////////////////

    conex.CrearDB_Base_datos_Ubicaciones();
    cone2= conex.CargarDB_Base_datos_Ubicaciones();
    
///////////////////////////////////////////////////////////////////////////////////////////    
    int id_borrado=0;
    
    
    jButton_EliminarRegistro.setVisible(false);
    
    jButton_ModificarRegistro.setVisible(false);
    
    jTextField_txtCuadro.requestFocus();
    
    Bandera_Modificando="No";
    
    if(cone2!=null)
    {
    PropiedadesTabla();
    
  

    
    
    
    //IniciarContador(contador_de_filas);
  
    
    //JOptionPane.showMessageDialog (null,"Mensaje","Titulo",JOptionPane.ERRROR_MESSAGE); 
    }
    
}

private void PropiedadesTabla()
{
cone2= conex.CargarDB_Base_datos_Ubicaciones();
//String columnas[] = {"ID", "Lugares"};
String columnas[] = {"Lugares","ID"};

if(!"SI".equals(SeBorroRegistro)){
int id=id_incrementable();
jTextField_Cuadro_ID.setText(String.valueOf(id));    
}
// Primero toma las filas pero no las tengo y pongo null
// Constructor de la Tabla
DefaultTableModel dft = new DefaultTableModel(null,columnas);

         try
         {
//Monstrar Algo de una Base de Datos:
    try (Statement orden = cone2.createStatement())
    {
        //Monstrar Algo de una Base de Datos:
        
        ResultSet r = orden.executeQuery("Select* From Lista_de_Ubicaciones");
        
        while (r.next())
        {
            Object Filas[]={r.getString("Lugar"),r.getString("ID")};
            //  Object Filas[]={r.getString("Titulo_Categoria")};
            
// Le digo ahora que tome estas filas dentro de la tabla

dft.addRow(Filas);

        }
        jTable_TabladeRegistros.setModel(dft);
        r.close();
    }
           
         }
         catch (SQLException ex)
         {
             Logger.getLogger(Ventana_Ubicaciones.class.getName()).log(Level.SEVERE, null, ex);
         }
}    

private void FiltrarRegistro(String Establezco_Filtro)
{
    cone2= conex.CargarDB_Base_datos_Ubicaciones();
    
    if(cone2!=null)
    {
        try
        {
            Statement orden = cone2.createStatement();
            String Filtro = "Select* From Lista_de_Ubicaciones where Lugar LIKE '%"+jTextField_txtCuadro.getText().trim()+"%' ";
            ResultSet r=orden.executeQuery(Filtro);
/////////////
String columnas[] = {"Lugares","ID"};

DefaultTableModel dft = new DefaultTableModel(null,columnas);

            
             while (r.next())
             {  
               Object Filas[]={r.getString("Lugar"),r.getString("ID")};
               dft.addRow(Filas);
             }
             jTable_TabladeRegistros.setModel(dft);
                          r.close();
                        orden.close();         
 
//////////////            
        }
        catch (Exception ex)
        {
            System.out.println("Error:" +ex);
        }
    
    }
    
    
}
private void EditarRegistro()
{
//Cargo la Base de Datos    
cone2= conex.CargarDB_Base_datos_Ubicaciones();

// Si la conexion esta corriendo arranca los pasos para trabajar con esta
if(cone2!=null)
{
    try
    {
// Orden de ejecutar para la base de datos, Codigo de SQL:
// Update = Editar o Actualizar
// Set (Establezco que voy a Editar)
// Update (Nombre de la Tabla) Set
        try (Statement orden = cone2.createStatement())
        {
            // Orden de ejecutar para la base de datos, Codigo de SQL:
// Update = Editar o Actualizar
// Set (Establezco que voy a Editar)
// Update (Nombre de la Tabla) Set
            
            String editar ="Update Lista_de_Ubicaciones Set Lugar='"+jTextField_txtCuadro.getText()+"' where ID="+jTextField_Cuadro_ID.getText()+"";
// Ejecuta ahora la Orden de arriba:
orden.executeUpdate(editar);
JOptionPane.showMessageDialog(this, "¡Modificada con Exito!");
        } 
        ReAbrirVentanaUbicaciones(); 
// Actualizar la tabla con el cambio realizado        
    //    PropiedadesTabla();
// Es importante cerrar cuando no se usa mas:

    }
    catch (SQLException ex)
    {
        System.out.println("Error: "+ex);
    }
}    
           
}
private void AgregarRegistro()
{
    cone2= conex.CargarDB_Base_datos_Ubicaciones();
// Cargo la base de datos mas arriba para tenerla disponible en todo.       
        if(jTextField_txtCuadro.getText().equals("")||jTextField_txtCuadro.getText().equals("(Escribe la Nueva Ubicacion)"))
        {
        JOptionPane.showMessageDialog(this, "Debe escribir la Ubicacion antes de agregar");
        }else 
        {
        if(cone2!=null)
  {
      try
      {
          try (Statement orden = cone2.createStatement())
          {
              String crear = "Insert into Lista_de_Ubicaciones(Lugar,ID) Values("
                      + "'"+jTextField_txtCuadro.getText()+ "',"
                      + ""+jTextField_Cuadro_ID.getText()+")";
              
//                  + ""+jTextField_ID_Categoria.getText()+","
//                  + "'"+jTextField_txtCuadroCategoria.getText()+ "')";
          
// Para ejecutar lo anterior se cree y actualice cada campo en la base de datos.
orden.executeUpdate(crear);
System.out.println("Registro Agregado OK");

//   IniciarContador(contador_de_filas);
PropiedadesTabla();
JOptionPane.showMessageDialog(this, "Nueva Ubicacion Agregada: [ "+jTextField_txtCuadro.getText()+" ]");
jTextField_txtCuadro.setText("");
//r.close();
SeBorroRegistro="NO";
ReAbrirVentanaUbicaciones();
          }
            
      }
      catch (SQLException ex)
      {
          System.out.println("Error:"+ex); 
    
      }
  } }    
    
}
public void ReAbrirVentanaUbicaciones()
{
        dispose();
        Panel_Inicio VB = new Panel_Inicio();
        Ventana_Ubicaciones ventanabierta = new Ventana_Ubicaciones();
        ventanabierta.setLocationRelativeTo(getParent());
        ventanabierta.setVisible(true); 
}

/**
 * This method is called from within the constructor to
 * initialize the form.
 * WARNING: Do NOT modify this code. The content of this method is
 * always regenerated by the Form Editor.
 */
@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField_Cuadro_ID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton_AgregarRegistro = new javax.swing.JButton();
        jButton_ModificarRegistro = new javax.swing.JButton();
        jButton_EliminarRegistro = new javax.swing.JButton();
        jButton_Cerrar = new javax.swing.JButton();
        jTextField_txtCuadro = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_TabladeRegistros = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("RemitoAPP / Ubicaciones");
        setAlwaysOnTop(true);
        setResizable(false);

        jPanel1.setName(""); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ubicaciones");

        jTextField_Cuadro_ID.setEditable(false);
        jTextField_Cuadro_ID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_Cuadro_ID.setAutoscrolls(false);
        jTextField_Cuadro_ID.setFocusable(false);

        jLabel2.setText("ID:");
        jLabel2.setFocusable(false);

        jButton_AgregarRegistro.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_AgregarRegistro.setText("+ Agregar");
        jButton_AgregarRegistro.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton_AgregarRegistro.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton_AgregarRegistro.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_AgregarRegistroActionPerformed(evt);
            }
        });

        jButton_ModificarRegistro.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_ModificarRegistro.setText("* Modificar");
        jButton_ModificarRegistro.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_ModificarRegistroActionPerformed(evt);
            }
        });

        jButton_EliminarRegistro.setBackground(new java.awt.Color(255, 0, 0));
        jButton_EliminarRegistro.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton_EliminarRegistro.setText("- Eliminar");
        jButton_EliminarRegistro.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_EliminarRegistroActionPerformed(evt);
            }
        });

        jButton_Cerrar.setText("Cerrar");
        jButton_Cerrar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_CerrarActionPerformed(evt);
            }
        });

        jTextField_txtCuadro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_txtCuadro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_txtCuadro.setFocusCycleRoot(true);
        jTextField_txtCuadro.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jTextField_txtCuadroMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                jTextField_txtCuadroMouseEntered(evt);
            }
        });
        jTextField_txtCuadro.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jTextField_txtCuadroActionPerformed(evt);
            }
        });
        jTextField_txtCuadro.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                jTextField_txtCuadroKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                jTextField_txtCuadroKeyReleased(evt);
            }
        });

        jTable_TabladeRegistros.setAutoCreateRowSorter(true);
        jTable_TabladeRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {

            }
        ));
        jTable_TabladeRegistros.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jTable_TabladeRegistros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable_TabladeRegistros.setGridColor(new java.awt.Color(0, 0, 0));
        jTable_TabladeRegistros.getTableHeader().setReorderingAllowed(false);
        jTable_TabladeRegistros.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jTable_TabladeRegistrosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_TabladeRegistros);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(264, 264, 264)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_Cuadro_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_AgregarRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton_EliminarRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                                .addComponent(jButton_ModificarRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGap(127, 127, 127)
                                    .addComponent(jButton_Cerrar))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(190, Short.MAX_VALUE)
                    .addComponent(jTextField_txtCuadro, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(22, 22, 22)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_Cuadro_ID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                        .addComponent(jButton_AgregarRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_ModificarRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jButton_EliminarRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(91, 91, 91)
                    .addComponent(jTextField_txtCuadro, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(182, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_AgregarRegistroActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_AgregarRegistroActionPerformed
    {//GEN-HEADEREND:event_jButton_AgregarRegistroActionPerformed
AgregarRegistro();
    }//GEN-LAST:event_jButton_AgregarRegistroActionPerformed

    private void jTextField_txtCuadroActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextField_txtCuadroActionPerformed
    {//GEN-HEADEREND:event_jTextField_txtCuadroActionPerformed

    }//GEN-LAST:event_jTextField_txtCuadroActionPerformed

    private void jTextField_txtCuadroMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTextField_txtCuadroMouseClicked
    {//GEN-HEADEREND:event_jTextField_txtCuadroMouseClicked
        if(jTextField_txtCuadro.getText().equals("(Escribe la Nueva Ubicación)"))
        {
                  jTextField_txtCuadro.setText("");
                  jButton_AgregarRegistro.setVisible(true);
        }  

    }//GEN-LAST:event_jTextField_txtCuadroMouseClicked

    private void jButton_CerrarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_CerrarActionPerformed
    {//GEN-HEADEREND:event_jButton_CerrarActionPerformed
//  Java: dispose() es usado para cerrar un jframe
        if(jTextField_txtCuadro.getText().equals("(Escribe la Nueva Ubicación)")||jTextField_txtCuadro.getText().equals("")){
          dispose();  
        }else
        {
        String opcion[]={"Si", "Cancelar"};
        int eleccion =JOptionPane.showOptionDialog(this, "¿Esta seguro de Cerrar esta Ventana?", "Aviso", 0, 0, null, opcion, NORMAL);
       
        if(eleccion==JOptionPane.YES_OPTION)
        {
            dispose();
        }else if (eleccion==JOptionPane.NO_OPTION)
        {
            
        }  
        }  
    }//GEN-LAST:event_jButton_CerrarActionPerformed

    private void jButton_ModificarRegistroActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_ModificarRegistroActionPerformed
    {//GEN-HEADEREND:event_jButton_ModificarRegistroActionPerformed
// Modificar
EditarRegistro(); 
    }//GEN-LAST:event_jButton_ModificarRegistroActionPerformed

    private void jTable_TabladeRegistrosMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTable_TabladeRegistrosMouseClicked
    {//GEN-HEADEREND:event_jTable_TabladeRegistrosMouseClicked
        Bandera_Modificando="Si";
        
        
        jButton_AgregarRegistro.setVisible(false);
        jButton_ModificarRegistro.setVisible(true);
        jButton_EliminarRegistro.setVisible(true);
        
        // evento Mouse Clicked en la Tabla:
             jTextField_Cuadro_ID.setText("");
             jTextField_txtCuadro.setText("");
             
             int seleccion=jTable_TabladeRegistros.rowAtPoint(evt.getPoint());
            
            
             jTextField_txtCuadro.setText(String.valueOf(jTable_TabladeRegistros.getValueAt(seleccion,0)));             
            jTextField_Cuadro_ID.setText(String.valueOf(jTable_TabladeRegistros.getValueAt(seleccion,1)));
                jTextField_txtCuadro.requestFocus();
            
    }//GEN-LAST:event_jTable_TabladeRegistrosMouseClicked

    private void jTextField_txtCuadroKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextField_txtCuadroKeyReleased
    {//GEN-HEADEREND:event_jTextField_txtCuadroKeyReleased
        FiltrarRegistro(jTextField_txtCuadro.getText().trim());
    }//GEN-LAST:event_jTextField_txtCuadroKeyReleased

    private void jButton_EliminarRegistroActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_EliminarRegistroActionPerformed
    {//GEN-HEADEREND:event_jButton_EliminarRegistroActionPerformed
// Eliminar
        if(jTextField_txtCuadro.getText().equals("")||jTextField_txtCuadro.getText().equals("(Escribe la Nueva Ubicación)"))
        {
        JOptionPane.showMessageDialog(this, "Debe Selecionar de la tabla y luego pulsar Eliminar");
        }else 
        {
// Se crea un metodo EditarRegistro() para que las instruciones las podamos usar por cualquier otra parte.        
           
        String opcion[]={"Eliminar", "Cancelar"};
        int eleccion =JOptionPane.showOptionDialog(this, "¿Esta seguro de Eliminar ("+jTextField_txtCuadro.getText()+") ?", "Eliminar", 0, 0, null, opcion, NORMAL);
       
        if(eleccion==JOptionPane.YES_OPTION)
        {
            EliminarRegistro();
        }else if (eleccion==JOptionPane.NO_OPTION)
        {
            
        }
            
        }
    }//GEN-LAST:event_jButton_EliminarRegistroActionPerformed

    private void jTextField_txtCuadroMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTextField_txtCuadroMouseEntered
    {//GEN-HEADEREND:event_jTextField_txtCuadroMouseEntered
        String Entro = null;

if(Bandera_Modificando=="Si"&& Entro!="Si")
{
    String TextoTemporal=jTextField_txtCuadro.getText();   
    System.out.println("TextoGuardado: "+TextoTemporal);
   Entro="No";  
}
        
    }//GEN-LAST:event_jTextField_txtCuadroMouseEntered

    private void jTextField_txtCuadroKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextField_txtCuadroKeyPressed
    {//GEN-HEADEREND:event_jTextField_txtCuadroKeyPressed
       
        if(evt.getKeyCode()==KeyEvent.VK_ENTER&&Bandera_Modificando=="No")
        {
         AgregarRegistro();
        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER&&Bandera_Modificando=="Si")
        {
            EditarRegistro();
        }   
    }//GEN-LAST:event_jTextField_txtCuadroKeyPressed

/**
 * @param args the command line arguments
 */
public static void main(String args[])
{
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try
    {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
        {
            if ("Nimbus".equals(info.getName()))
            {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    }
    catch (ClassNotFoundException ex)
    {
        java.util.logging.Logger.getLogger(Ventana_Ubicaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    catch (InstantiationException ex)
    {
        java.util.logging.Logger.getLogger(Ventana_Ubicaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    catch (IllegalAccessException ex)
    {
        java.util.logging.Logger.getLogger(Ventana_Ubicaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    catch (javax.swing.UnsupportedLookAndFeelException ex)
    {
        java.util.logging.Logger.getLogger(Ventana_Ubicaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable()
    {
    public void run()
    {
        new Ventana_Ubicaciones().setVisible(true);
    }
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_AgregarRegistro;
    private javax.swing.JButton jButton_Cerrar;
    private javax.swing.JButton jButton_EliminarRegistro;
    private javax.swing.JButton jButton_ModificarRegistro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_TabladeRegistros;
    private javax.swing.JTextField jTextField_Cuadro_ID;
    private javax.swing.JTextField jTextField_txtCuadro;
    // End of variables declaration//GEN-END:variables

    private void EliminarRegistro()
    {
        cone2= conex.CargarDB_Base_datos_Ubicaciones();

        if(cone2!=null)
        {
            try
            {
                Statement orden = cone2.createStatement();
                int Comienza_desde_Aqui=Integer.parseInt(jTextField_Cuadro_ID.getText());
                System.out.println("Comienza_desde_Aqui:"+Comienza_desde_Aqui);     

                String Elminar = "DELETE From Lista_de_Ubicaciones Where ID="+jTextField_Cuadro_ID.getText();

                orden.executeUpdate(Elminar);
                          
             cone2.close();
                JOptionPane.showMessageDialog(this, "Ubicación Elimina");
         //       ReAbrirVentanaCategorias();
         
                 jTextField_txtCuadro.setText("(Escribe la Nueva Ubicación)");

                  jButton_EliminarRegistro.setVisible(false);
                  jButton_AgregarRegistro.setVisible(true);
                  jButton_ModificarRegistro.setVisible(false);
                 
                 PropiedadesTabla();
                 
                id_borrado_categoria=Comienza_desde_Aqui;
                SeBorroRegistro="SI";

              
              jTextField_Cuadro_ID.setText(String.valueOf(Comienza_desde_Aqui));
                            
             orden.close();
            }
            catch (SQLException ex)
            {
                System.out.println("Error:" +ex);
            }
        }
    }

public int id_incrementable()
{
    int id=1;
    PreparedStatement ps =null;
    cone2= conex.CargarDB_Base_datos_Ubicaciones();
    try
    {
      Statement orden = cone2.createStatement();
      ResultSet r = orden.executeQuery("Select MAX(ID) From Lista_de_Ubicaciones");
        while (r.next())
        {            
                    id=r.getInt(1)+1;

        }
        System.out.println("Id Maximo:"+id);
        
            }
    catch (Exception ex)
    {
        System.out.println("Error:"+ex);
    }
    finally
    {
        try
        {
            ps.close();
            
            //DesconectarBasededatos Falta
        }
        catch (Exception e)
        {
        }
    
    }
    
        if(SeBorroRegistro=="SI")
    {
                        System.out.println("Se Borro un registro recien:"+SeBorroRegistro+"La Id era:"+id_borrado_categoria );
     id=Comienza_desde_Aqui;   
    }
    
        return id;
    }    }
