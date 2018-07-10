/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntornoGrafico;

// Importo la relación con la base de datos
import CodigoFuente.Conexion;
import EntornoGrafico.Panel_Inicio;
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
    private String SeBorroCategoria;
    private int id_borrado_categoria;
    private Integer Comienza_desde_Aqui;
    
/**
 * Creates new form Ventana_Categorias
 */
private void PropiedadesTabla()
{
cone2= conex.CargarDB_Base_datos_Ubicaciones();
String columnas[] = {"ID", "Lugares"};
//String columnas[] = {"Categorias"};

if(SeBorroCategoria!="SI"){
int id=id_incrementable();
jTextField_ID_Categoria.setText(String.valueOf(id));    
}

//    }  


// Primero toma las filas pero no las tengo y pongo null
// Constructor de la Tabla
DefaultTableModel dft = new DefaultTableModel(null,columnas);

         try
         {
             Statement orden = cone2.createStatement();

//Monstrar Algo de una Base de Datos:

            ResultSet r = orden.executeQuery("Select* From ListaUbicaciones");
            
             while (r.next())
             { 
               Object Filas[]={r.getString("ID"),r.getString("Lugar")};
             //  Object Filas[]={r.getString("Lugar")};


// Le digo ahora que tome estas filas dentro de la tabla

            dft.addRow(Filas);

             }
             jTable_Listado_de_Categorias.setModel(dft);
                          r.close();
                        orden.close();
           
         }
         catch (SQLException ex)
         {
             Logger.getLogger(Ventana_Ubicaciones.class.getName()).log(Level.SEVERE, null, ex);
         }
}    

private void FiltrarCategorias(String Establezco_Filtro)
{
    cone2=conex.CargarDB_Base_datos_Ubicaciones();
    
    if(cone2!=null)
    {
        try
        {
            Statement orden = cone2.createStatement();
 //           String Filtro = "Select* From ListaUbicaciones where Lugar LIKE '%"+jTextField_BarradeBusqueda.getText().trim()+"%' ";
            String Filtro = "Select* From ListaUbicaciones where Lugar LIKE '%"+jTextField_txtCuadroCategoria.getText().trim()+"%' ";
            ResultSet r=orden.executeQuery(Filtro);
/////////////
String columnas[] = {"ID", "Categorias"};
DefaultTableModel dft = new DefaultTableModel(null,columnas);

            
             while (r.next())
             {  
               Object Filas[]={r.getString("ID"),r.getString("Lugar")};
               dft.addRow(Filas);
             }
             jTable_Listado_de_Categorias.setModel(dft);
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
// Este Metodo tambien se activa al pulsar ( * Modificar )

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

    String editar ="Update ListaUbicaciones Set Lugar='"+jTextField_txtCuadroCategoria.getText()+"' where ID="+jTextField_ID_Categoria.getText()+"";
// Ejecuta ahora la Orden de arriba:
String TextoTemporal=jTextField_txtCuadroCategoria.getText();
        orden.executeUpdate(editar);
         ReAbrirVentanaCategorias();     
        JOptionPane.showMessageDialog(this, "¡Categoria ("+TextoTemporal+") Agregada y Modificada con Exito!");
// Actualizar la tabla con el cambio realizado        
        PropiedadesTabla();
// Es importante cerrar cuando no se usa mas:
        orden.close(); 
    }
    catch (SQLException ex)
    {
        System.out.println("Error: "+ex);
    }
}
    
}
public void ReAbrirVentanaCategorias()
{
        dispose();
        Panel_Inicio VB = new Panel_Inicio();
        Ventana_Ubicaciones ventanabierta = new Ventana_Ubicaciones();
        ventanabierta.setLocationRelativeTo(getParent());
        ventanabierta.setVisible(true);    
}
public Ventana_Ubicaciones()
{
    initComponents();
    
       
    //Crea y carga al momento de cargar la ventana en pantalla.
    cone2= conex.CargarDB_Base_datos_Ubicaciones();
    int id_borrado=0;
    
    
    jButton_EliminarCategoria.setVisible(false);  
    
    if(cone2!=null)
    {
    PropiedadesTabla();
    
  

    
    
    
    //IniciarContador(contador_de_filas);
  
    
    //JOptionPane.showMessageDialog (null,"Mensaje","Titulo",JOptionPane.ERRROR_MESSAGE); 
    }
    
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Listado_de_Categorias = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jTextField_txtCuadroCategoria = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextField_ID_Categoria = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton_AgregarCategoria = new javax.swing.JButton();
        jButton_ModificarCategoria = new javax.swing.JButton();
        jButton_EliminarCategoria = new javax.swing.JButton();
        jButton_CerrarCategoria = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("RemitoAPP / Categorias");
        setAlwaysOnTop(true);

        jTable_Listado_de_Categorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {

            }
        ));
        jTable_Listado_de_Categorias.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable_Listado_de_Categorias.setAutoscrolls(false);
        jTable_Listado_de_Categorias.setColumnSelectionAllowed(true);
        jTable_Listado_de_Categorias.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable_Listado_de_Categorias.getTableHeader().setReorderingAllowed(false);
        jTable_Listado_de_Categorias.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jTable_Listado_de_CategoriasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Listado_de_Categorias);
        jTable_Listado_de_Categorias.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextField_txtCuadroCategoria.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_txtCuadroCategoria.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_txtCuadroCategoria.setText("(Escribe el nuevo Lugar)");
        jTextField_txtCuadroCategoria.setFocusCycleRoot(true);
        jTextField_txtCuadroCategoria.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jTextField_txtCuadroCategoriaMouseClicked(evt);
            }
        });
        jTextField_txtCuadroCategoria.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jTextField_txtCuadroCategoriaActionPerformed(evt);
            }
        });
        jTextField_txtCuadroCategoria.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                jTextField_txtCuadroCategoriaKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ubicación");

        jTextField_ID_Categoria.setEditable(false);
        jTextField_ID_Categoria.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setText("ID:");

        jButton_AgregarCategoria.setText("+ Agregar");
        jButton_AgregarCategoria.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_AgregarCategoriaActionPerformed(evt);
            }
        });

        jButton_ModificarCategoria.setText("* Modificar");
        jButton_ModificarCategoria.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_ModificarCategoriaActionPerformed(evt);
            }
        });

        jButton_EliminarCategoria.setBackground(new java.awt.Color(255, 0, 0));
        jButton_EliminarCategoria.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton_EliminarCategoria.setText("- Eliminar");
        jButton_EliminarCategoria.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_EliminarCategoriaActionPerformed(evt);
            }
        });

        jButton_CerrarCategoria.setText("Cerrar");
        jButton_CerrarCategoria.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_CerrarCategoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_ID_Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField_txtCuadroCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)))
                    .addComponent(jButton_EliminarCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jButton_CerrarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton_AgregarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_ModificarCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField_ID_Categoria)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_txtCuadroCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton_AgregarCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(jButton_ModificarCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jButton_EliminarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jButton_CerrarCategoria)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_AgregarCategoriaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_AgregarCategoriaActionPerformed
    {//GEN-HEADEREND:event_jButton_AgregarCategoriaActionPerformed
// Al Presionar Agregar Categoria ocurre estas acciones:
// Debe Cargar la Base de Datos para luego poder consultar, modificarla o agregar nuevos elementos.  
 cone2=conex.CargarDB_Base_datos_Ubicaciones();
// Cargo la base de datos mas arriba para tenerla disponible en todo.       
        if(jTextField_txtCuadroCategoria.getText().equals("")||jTextField_txtCuadroCategoria.getText().equals("(Escribe el nuevo Lugar)"))
        {
        JOptionPane.showMessageDialog(this, "Debe escribir la categoria antes de agregar");
        }else 
        {
        if(cone2!=null)
  {
      try
      {
          Statement orden = cone2.createStatement();
          String crear = "Insert into ListaUbicaciones(ID,Lugar) Values("
                  + ""+jTextField_ID_Categoria.getText()+","
                  + "'"+jTextField_txtCuadroCategoria.getText()+ "')";
          
// Para ejecutar lo anterior se cree y actualice cada campo en la base de datos.
           orden.executeUpdate(crear);
          System.out.println("Registro Agregado OK");
          
       //   IniciarContador(contador_de_filas);
          PropiedadesTabla();
          JOptionPane.showMessageDialog(this, "Nueva Categoria Agregada: [ "+jTextField_txtCuadroCategoria.getText()+" ]");
          jTextField_txtCuadroCategoria.setText("");
            //r.close();
            SeBorroCategoria="NO";
            ReAbrirVentanaCategorias();
            orden.close();
            
      }
      catch (SQLException ex)
      {
          System.out.println("Error:"+ex); 
    
      }
  } } 
    }//GEN-LAST:event_jButton_AgregarCategoriaActionPerformed

    private void jTextField_txtCuadroCategoriaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextField_txtCuadroCategoriaActionPerformed
    {//GEN-HEADEREND:event_jTextField_txtCuadroCategoriaActionPerformed

    }//GEN-LAST:event_jTextField_txtCuadroCategoriaActionPerformed

    private void jTextField_txtCuadroCategoriaMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTextField_txtCuadroCategoriaMouseClicked
    {//GEN-HEADEREND:event_jTextField_txtCuadroCategoriaMouseClicked
        if(jTextField_txtCuadroCategoria.getText().equals("(Escribe el nuevo Lugar)"))
        {
                  jTextField_txtCuadroCategoria.setText("");  
        }  

    }//GEN-LAST:event_jTextField_txtCuadroCategoriaMouseClicked

    private void jButton_CerrarCategoriaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_CerrarCategoriaActionPerformed
    {//GEN-HEADEREND:event_jButton_CerrarCategoriaActionPerformed
//  Java: dispose() es usado para cerrar un jframe
        dispose();
    }//GEN-LAST:event_jButton_CerrarCategoriaActionPerformed

    private void jButton_ModificarCategoriaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_ModificarCategoriaActionPerformed
    {//GEN-HEADEREND:event_jButton_ModificarCategoriaActionPerformed
// Modificar
         
        if(jTextField_txtCuadroCategoria.getText().equals("")||jTextField_txtCuadroCategoria.getText().equals("(Escribe el nuevo Lugar)"))
        {
        JOptionPane.showMessageDialog(this, "Debe Selecionar de la tabla una Categoria y escribir el  cambio de la categoria antes de Modificar");
        }else 
        {
// Se crea un metodo EditarRegistro() para que las instruciones las podamos usar por cualquier otra parte.        
           EditarRegistro(); 
        }
    }//GEN-LAST:event_jButton_ModificarCategoriaActionPerformed

    private void jTable_Listado_de_CategoriasMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTable_Listado_de_CategoriasMouseClicked
    {//GEN-HEADEREND:event_jTable_Listado_de_CategoriasMouseClicked

        jButton_AgregarCategoria.setVisible(false);
        jButton_EliminarCategoria.setVisible(true);
        
        // evento Mouse Clicked en la Tabla:
             jTextField_ID_Categoria.setText("");
             jTextField_txtCuadroCategoria.setText("");
             
             int seleccion=jTable_Listado_de_Categorias.rowAtPoint(evt.getPoint());
             
             jTextField_ID_Categoria.setText(String.valueOf(jTable_Listado_de_Categorias.getValueAt(seleccion,0)));
             jTextField_txtCuadroCategoria.setText(String.valueOf(jTable_Listado_de_Categorias.getValueAt(seleccion,1)));
    }//GEN-LAST:event_jTable_Listado_de_CategoriasMouseClicked

    private void jTextField_txtCuadroCategoriaKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextField_txtCuadroCategoriaKeyReleased
    {//GEN-HEADEREND:event_jTextField_txtCuadroCategoriaKeyReleased
        FiltrarCategorias(jTextField_txtCuadroCategoria.getText().trim());
    }//GEN-LAST:event_jTextField_txtCuadroCategoriaKeyReleased

    private void jButton_EliminarCategoriaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_EliminarCategoriaActionPerformed
    {//GEN-HEADEREND:event_jButton_EliminarCategoriaActionPerformed
// Eliminar
        if(jTextField_txtCuadroCategoria.getText().equals("")||jTextField_txtCuadroCategoria.getText().equals("(Escribe el nuevo Lugar)"))
        {
        JOptionPane.showMessageDialog(this, "Debe Selecionar de la tabla una Categoria y luego pulsar Eliminar");
        }else 
        {
// Se crea un metodo EditarRegistro() para que las instruciones las podamos usar por cualquier otra parte.        
           
        String opcion[]={"Eliminar Categoria", "Cancelar"};
        int eleccion =JOptionPane.showOptionDialog(this, "¿Esta seguro de Eliminar ("+jTextField_txtCuadroCategoria.getText()+")", "Eliminar Categoria", 0, 0, null, opcion, NORMAL);
       
        if(eleccion==JOptionPane.YES_OPTION)
        {
            EliminarRegistro();
        }else if (eleccion==JOptionPane.NO_OPTION)
        {
            
        }
            
        }
    }//GEN-LAST:event_jButton_EliminarCategoriaActionPerformed

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
    private javax.swing.JButton jButton_AgregarCategoria;
    private javax.swing.JButton jButton_CerrarCategoria;
    private javax.swing.JButton jButton_EliminarCategoria;
    private javax.swing.JButton jButton_ModificarCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Listado_de_Categorias;
    private javax.swing.JTextField jTextField_ID_Categoria;
    private javax.swing.JTextField jTextField_txtCuadroCategoria;
    // End of variables declaration//GEN-END:variables

    private void EliminarRegistro()
    {
        cone2 = conex.CargarDB_Base_datos_Ubicaciones();

        if(cone2!=null)
        {
            try
            {
                Statement orden = cone2.createStatement();
                int Comienza_desde_Aqui=Integer.parseInt(jTextField_ID_Categoria.getText());
                System.out.println("Comienza_desde_Aqui:"+Comienza_desde_Aqui);     

                String Elminar = "DELETE From ListaUbicaciones Where ID="+jTextField_ID_Categoria.getText();

                orden.executeUpdate(Elminar);
               
            //    String AlterarTabla = "ALTER TABLE ListaUbicaciones DROP ID";
                
            //    orden.executeUpdate(AlterarTabla);
                
            //    String Renumerar_ID = "ALTER TABLE ListaUbicaciones ADD ID Primary key";
                
            //    orden.executeUpdate(Renumerar_ID);
            
             cone2.close();
                JOptionPane.showMessageDialog(this, "Categoria Elimina");
         //       ReAbrirVentanaCategorias();
         
                 jTextField_txtCuadroCategoria.setText("(Escribe el nuevo Lugar)");

                  jButton_EliminarCategoria.setVisible(false);
                  jButton_AgregarCategoria.setVisible(true);
                  jButton_ModificarCategoria.setVisible(false);
                 
                 PropiedadesTabla();
                 
                id_borrado_categoria=Comienza_desde_Aqui;
                SeBorroCategoria="SI";

              
              jTextField_ID_Categoria.setText(String.valueOf(Comienza_desde_Aqui));
                            
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
      ResultSet r = orden.executeQuery("Select MAX(ID) From ListaUbicaciones");
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
    
        if(SeBorroCategoria=="SI")
    {
                        System.out.println("Se Borro un registro recien:"+SeBorroCategoria+"La Id era:"+id_borrado_categoria );
     id=Comienza_desde_Aqui;   
    }
    
        return id;
    }
    }
