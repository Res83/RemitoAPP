/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntornoGrafico;

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
 * @author Res
 */
public class Ventana_Ubicaciones extends javax.swing.JFrame
{
// Cargo la relación con la base de datos

    Conexion conex = new Conexion();
    Connection cone2;  
   // int contador_de_filas=1;
    private String TextoTemporal;
    private Integer Comienza_desde_Aqui;
    private String Bandera_Modificando;
    private int seleccion;
    private String SeBorroUbicaciones;
    private int id_borrado_Ubicaciones;
    private String id_borrado_categoria;
    
/**
 * Creates new form Ventana_Categorias
 */
public Ventana_Ubicaciones()
{
    initComponents();
    
       
    //Crea y carga al momento de cargar la ventana en pantalla.
    conex.CrearDB_Base_datos_Ubicaciones();
    cone2= conex.CargarDB_Base_datos_Ubicaciones();
    int id_borrado=0;
    
    
    jButton_EliminarUbicaciones.setVisible(false);
    
    jButton_ModificarUbicaciones.setVisible(false);
    
    jTextField_txtCuadroUbicaciones.requestFocus();
    
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
//String columnas[] = {"ID", "Ubicaciones"};
String columnas[] = {"Ubicaciones","ID"};

if(SeBorroUbicaciones!="SI"){
int id=id_incrementable();
jTextField_ID_Ubicaciones.setText(String.valueOf(id));    
}

//    }  


// Primero toma las filas pero no las tengo y pongo null
// Constructor de la Tabla
DefaultTableModel dft = new DefaultTableModel(null,columnas);

         try
         {
             Statement orden = cone2.createStatement();

//Monstrar Algo de una Base de Datos:

            ResultSet r = orden.executeQuery("Select* From Lista_de_Ubicaciones");
            
             while (r.next())
             { 
               Object Filas[]={r.getString("Lugar"),r.getString("ID")};

// Le digo ahora que tome estas filas dentro de la tabla

            dft.addRow(Filas);

             }
             jTable_Listado_de_Ubicaciones.setModel(dft);
                          r.close();
                        orden.close();
           
         }
         catch (SQLException ex)
         {
             Logger.getLogger(Ventana_Ubicaciones.class.getName()).log(Level.SEVERE, null, ex);
         }
}    

private void FiltrarUbicaciones(String Establezco_Filtro)
{
    cone2=conex.CargarDB_Base_datos_Ubicaciones();
    
    if(cone2!=null)
    {
        try
        {
            Statement orden = cone2.createStatement();
            String Filtro = "Select* From Lista_de_Ubicaciones where Lugar LIKE '%"+jTextField_txtCuadroUbicaciones.getText().trim()+"%' ";
            ResultSet r=orden.executeQuery(Filtro);
/////////////
String columnas[] = {"Lugar","ID"};

DefaultTableModel dft = new DefaultTableModel(null,columnas);

            
             while (r.next())
             {  
               Object Filas[]={r.getString("Lugar"),r.getString("ID")};
               dft.addRow(Filas);
             }
             jTable_Listado_de_Ubicaciones.setModel(dft);
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
cone2 = conex.CargarDB_Base_datos_Ubicaciones();

// Si la conexion esta corriendo arranca los pasos para trabajar con esta
if(cone2!=null)
{
    try
    {
        Statement orden = cone2.createStatement();

// Orden de ejecutar para la base de datos, Codigo de SQL:
// Update = Editar o Actualizar
// Set (Establezco que voy a Editar)
// Update (Nombre de la Tabla) Set
               
    String editar ="Update Lista_de_Ubicaciones Set Lugar='"+jTextField_txtCuadroUbicaciones.getText()+"' where ID="+jTextField_ID_Ubicaciones.getText()+"";
// Ejecuta ahora la Orden de arriba:
        orden.executeUpdate(editar);    
        JOptionPane.showMessageDialog(this, "¡La Ubicación se ha Modificado con Exito!");
        orden.close(); 
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
    cone2=conex.CargarDB_Base_datos_Ubicaciones();
// Cargo la base de datos mas arriba para tenerla disponible en todo.       
        if(jTextField_txtCuadroUbicaciones.getText().equals("")||jTextField_txtCuadroUbicaciones.getText().equals("(Escribe la Nueva Categoria)"))
        {
        JOptionPane.showMessageDialog(this, "Debe escribir la Ubicacion antes de agregar");
        }else 
        {
        if(cone2!=null)
  {
      try
      {
          Statement orden = cone2.createStatement();
          String crear = "Insert into Lista_de_Ubicaciones(Lugar,ID) Values("
                  + "'"+jTextField_txtCuadroUbicaciones.getText()+ "',"
                   + ""+jTextField_ID_Ubicaciones.getText()+")";                     
               
          
// Para ejecutar lo anterior se cree y actualice cada campo en la base de datos.
           orden.executeUpdate(crear);
          
       //   IniciarContador(contador_de_filas);
          PropiedadesTabla();
          JOptionPane.showMessageDialog(this, "Nueva Ubicación Agregada: [ "+jTextField_txtCuadroUbicaciones.getText()+" ]");
          jTextField_txtCuadroUbicaciones.setText("");
            //r.close();
            SeBorroUbicaciones="NO";
            ReAbrirVentanaUbicaciones();
            orden.close();
            
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
        jTextField_ID_Ubicaciones = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton_AgregarUbicaciones = new javax.swing.JButton();
        jButton_ModificarUbicaciones = new javax.swing.JButton();
        jButton_EliminarUbicaciones = new javax.swing.JButton();
        jButton_CerrarUbicaciones = new javax.swing.JButton();
        jTextField_txtCuadroUbicaciones = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Listado_de_Ubicaciones = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("RemitoAPP / Categorias");
        setAlwaysOnTop(true);
        setResizable(false);

        jPanel1.setName(""); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ubicaciones");

        jTextField_ID_Ubicaciones.setEditable(false);
        jTextField_ID_Ubicaciones.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_ID_Ubicaciones.setAutoscrolls(false);
        jTextField_ID_Ubicaciones.setFocusable(false);

        jLabel2.setText("ID:");
        jLabel2.setFocusable(false);

        jButton_AgregarUbicaciones.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_AgregarUbicaciones.setText("+ Agregar");
        jButton_AgregarUbicaciones.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton_AgregarUbicaciones.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton_AgregarUbicaciones.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_AgregarUbicacionesActionPerformed(evt);
            }
        });

        jButton_ModificarUbicaciones.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_ModificarUbicaciones.setText("* Modificar");
        jButton_ModificarUbicaciones.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_ModificarUbicacionesActionPerformed(evt);
            }
        });

        jButton_EliminarUbicaciones.setBackground(new java.awt.Color(255, 0, 0));
        jButton_EliminarUbicaciones.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton_EliminarUbicaciones.setText("- Eliminar");
        jButton_EliminarUbicaciones.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_EliminarUbicacionesActionPerformed(evt);
            }
        });

        jButton_CerrarUbicaciones.setText("Cerrar");
        jButton_CerrarUbicaciones.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_CerrarUbicacionesActionPerformed(evt);
            }
        });

        jTextField_txtCuadroUbicaciones.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_txtCuadroUbicaciones.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_txtCuadroUbicaciones.setFocusCycleRoot(true);
        jTextField_txtCuadroUbicaciones.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jTextField_txtCuadroUbicacionesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                jTextField_txtCuadroUbicacionesMouseEntered(evt);
            }
        });
        jTextField_txtCuadroUbicaciones.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jTextField_txtCuadroUbicacionesActionPerformed(evt);
            }
        });
        jTextField_txtCuadroUbicaciones.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                jTextField_txtCuadroUbicacionesKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                jTextField_txtCuadroUbicacionesKeyReleased(evt);
            }
        });

        jTable_Listado_de_Ubicaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {

            }
        ));
        jTable_Listado_de_Ubicaciones.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jTable_Listado_de_Ubicaciones.setAutoscrolls(false);
        jTable_Listado_de_Ubicaciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable_Listado_de_Ubicaciones.setGridColor(new java.awt.Color(0, 0, 0));
        jTable_Listado_de_Ubicaciones.getTableHeader().setReorderingAllowed(false);
        jTable_Listado_de_Ubicaciones.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jTable_Listado_de_UbicacionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Listado_de_Ubicaciones);

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
                        .addComponent(jTextField_ID_Ubicaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_AgregarUbicaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton_EliminarUbicaciones, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                                .addComponent(jButton_ModificarUbicaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGap(127, 127, 127)
                                    .addComponent(jButton_CerrarUbicaciones))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(190, Short.MAX_VALUE)
                    .addComponent(jTextField_txtCuadroUbicaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                            .addComponent(jTextField_ID_Ubicaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                        .addComponent(jButton_AgregarUbicaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_ModificarUbicaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jButton_EliminarUbicaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_CerrarUbicaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(91, 91, 91)
                    .addComponent(jTextField_txtCuadroUbicaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void jButton_AgregarUbicacionesActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_AgregarUbicacionesActionPerformed
    {//GEN-HEADEREND:event_jButton_AgregarUbicacionesActionPerformed
AgregarRegistro();
    }//GEN-LAST:event_jButton_AgregarUbicacionesActionPerformed

    private void jTextField_txtCuadroUbicacionesActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextField_txtCuadroUbicacionesActionPerformed
    {//GEN-HEADEREND:event_jTextField_txtCuadroUbicacionesActionPerformed

    }//GEN-LAST:event_jTextField_txtCuadroUbicacionesActionPerformed

    private void jTextField_txtCuadroUbicacionesMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTextField_txtCuadroUbicacionesMouseClicked
    {//GEN-HEADEREND:event_jTextField_txtCuadroUbicacionesMouseClicked
        if(jTextField_txtCuadroUbicaciones.getText().equals("(Escribe la Nueva Ubicación)"))
        {
                  jTextField_txtCuadroUbicaciones.setText("");
                  jButton_AgregarUbicaciones.setVisible(true);
        }  

    }//GEN-LAST:event_jTextField_txtCuadroUbicacionesMouseClicked

    private void jButton_CerrarUbicacionesActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_CerrarUbicacionesActionPerformed
    {//GEN-HEADEREND:event_jButton_CerrarUbicacionesActionPerformed
//  Java: dispose() es usado para cerrar un jframe
        dispose();
    }//GEN-LAST:event_jButton_CerrarUbicacionesActionPerformed

    private void jButton_ModificarUbicacionesActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_ModificarUbicacionesActionPerformed
    {//GEN-HEADEREND:event_jButton_ModificarUbicacionesActionPerformed
// Modificar
EditarRegistro(); 
    }//GEN-LAST:event_jButton_ModificarUbicacionesActionPerformed

    private void jTable_Listado_de_UbicacionesMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTable_Listado_de_UbicacionesMouseClicked
    {//GEN-HEADEREND:event_jTable_Listado_de_UbicacionesMouseClicked
        Bandera_Modificando="Si";
        
        
        jButton_AgregarUbicaciones.setVisible(false);
        jButton_ModificarUbicaciones.setVisible(true);
        jButton_EliminarUbicaciones.setVisible(true);
        
        // evento Mouse Clicked en la Tabla:
             jTextField_ID_Ubicaciones.setText("");
             jTextField_txtCuadroUbicaciones.setText("");
             
             int seleccion=jTable_Listado_de_Ubicaciones.rowAtPoint(evt.getPoint());
            
            
             jTextField_txtCuadroUbicaciones.setText(String.valueOf(jTable_Listado_de_Ubicaciones.getValueAt(seleccion,0)));             
            jTextField_ID_Ubicaciones.setText(String.valueOf(jTable_Listado_de_Ubicaciones.getValueAt(seleccion,1)));
                jTextField_txtCuadroUbicaciones.requestFocus();
            
    }//GEN-LAST:event_jTable_Listado_de_UbicacionesMouseClicked

    private void jTextField_txtCuadroUbicacionesKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextField_txtCuadroUbicacionesKeyReleased
    {//GEN-HEADEREND:event_jTextField_txtCuadroUbicacionesKeyReleased
        FiltrarUbicaciones(jTextField_txtCuadroUbicaciones.getText().trim());
    }//GEN-LAST:event_jTextField_txtCuadroUbicacionesKeyReleased

    private void jButton_EliminarUbicacionesActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_EliminarUbicacionesActionPerformed
    {//GEN-HEADEREND:event_jButton_EliminarUbicacionesActionPerformed
// Eliminar
        if(jTextField_txtCuadroUbicaciones.getText().equals("")||jTextField_txtCuadroUbicaciones.getText().equals("(Escribe la Nueva Ubicacion)"))
        {
        JOptionPane.showMessageDialog(this, "Debe Selecionar de la tabla un Lugar y luego pulsar Eliminar");
        }else 
        {
// Se crea un metodo EditarRegistro() para que las instruciones las podamos usar por cualquier otra parte.        
           
        String opcion[]={"Eliminar Ubicación", "Cancelar"};
        int eleccion =JOptionPane.showOptionDialog(this, "¿Esta seguro de Eliminar ("+jTextField_txtCuadroUbicaciones.getText()+")", "Eliminar Categoria", 0, 0, null, opcion, NORMAL);
       
        if(eleccion==JOptionPane.YES_OPTION)
        {
            EliminarRegistro();
        }else if (eleccion==JOptionPane.NO_OPTION)
        {
            
        }
            
        }
    }//GEN-LAST:event_jButton_EliminarUbicacionesActionPerformed

    private void jTextField_txtCuadroUbicacionesMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTextField_txtCuadroUbicacionesMouseEntered
    {//GEN-HEADEREND:event_jTextField_txtCuadroUbicacionesMouseEntered
        String Entro = null;

if(Bandera_Modificando=="Si"&& Entro!="Si")
{
    String TextoTemporal=jTextField_txtCuadroUbicaciones.getText();   
    System.out.println("TextoGuardado: "+TextoTemporal);
   Entro="No";  
}
        
    }//GEN-LAST:event_jTextField_txtCuadroUbicacionesMouseEntered

    private void jTextField_txtCuadroUbicacionesKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextField_txtCuadroUbicacionesKeyPressed
    {//GEN-HEADEREND:event_jTextField_txtCuadroUbicacionesKeyPressed
       
        if(evt.getKeyCode()==KeyEvent.VK_ENTER&&Bandera_Modificando=="No")
        {
         AgregarRegistro();
        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER&&Bandera_Modificando=="Si")
        {
            EditarRegistro();
        }   
    }//GEN-LAST:event_jTextField_txtCuadroUbicacionesKeyPressed

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
    private javax.swing.JButton jButton_AgregarUbicaciones;
    private javax.swing.JButton jButton_CerrarUbicaciones;
    private javax.swing.JButton jButton_EliminarUbicaciones;
    private javax.swing.JButton jButton_ModificarUbicaciones;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Listado_de_Ubicaciones;
    private javax.swing.JTextField jTextField_ID_Ubicaciones;
    private javax.swing.JTextField jTextField_txtCuadroUbicaciones;
    // End of variables declaration//GEN-END:variables

    private void EliminarRegistro()
    {
    cone2=conex.CargarDB_Base_datos_Ubicaciones();

        if(cone2!=null)
        {
            try
            {
                Statement orden = cone2.createStatement();
                int Comienza_desde_Aqui=Integer.parseInt(jTextField_ID_Ubicaciones.getText());
                System.out.println("Comienza_desde_Aqui:"+Comienza_desde_Aqui);     

                String Elminar = "DELETE From Lista_de_Ubicaciones Where ID="+jTextField_ID_Ubicaciones.getText();

                orden.executeUpdate(Elminar);
               
            
             cone2.close();
                JOptionPane.showMessageDialog(this, "Ubicación Elimina");
         //       ReAbrirVentanaUbicaciones();
         
                 jTextField_txtCuadroUbicaciones.setText("(Escribe la Nueva Ubicación)");

                  jButton_EliminarUbicaciones.setVisible(false);
                  jButton_AgregarUbicaciones.setVisible(true);
                  jButton_ModificarUbicaciones.setVisible(false);
                 
                 PropiedadesTabla();
                 
                id_borrado_Ubicaciones=Comienza_desde_Aqui;
                SeBorroUbicaciones="SI";

              
              jTextField_ID_Ubicaciones.setText(String.valueOf(Comienza_desde_Aqui));
                            
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
    cone2 = conex.CargarDB_Base_datos_Ubicaciones();
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
    
        if(SeBorroUbicaciones=="SI")
    {
                        System.out.println("Se Borro un registro recien:"+SeBorroUbicaciones+"La Id era:"+id_borrado_categoria );
     id=Comienza_desde_Aqui;   
    }
    
        return id;
    }
    }
