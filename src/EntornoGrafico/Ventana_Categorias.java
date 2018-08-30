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
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Res
 */
public class Ventana_Categorias extends javax.swing.JFrame
{
// Cargo la relación con la base de datos

    Conexion conex = new Conexion();
    Connection cone2;
     Conexion conexion_BaseDatosCategorias = new Conexion();
     
   // int contador_de_filas=1;
    private String SeBorroCategoria;
    private String TextoTemporal;
    private int id_borrado_categoria;
    private Integer Comienza_desde_Aqui;
    private String Bandera_Modificando;
    private int seleccion;
    
/**
 * Creates new form Ventana_Categorias
 */
public Ventana_Categorias()
{
    initComponents();
    
        setIconImage(new ImageIcon(getClass().getResource("../Imagenes/Logo.png")).getImage());
       
    //Crea y carga al momento de cargar la ventana en pantalla.
    conex.CrearDB_Lista_de_Categorias();
    cone2= conex.CargarDB_Lista_de_Categorias();
    int id_borrado=0;
    
    
    jButton_EliminarCategoria.setVisible(false);
    
    jButton_ModificarCategoria.setVisible(false);
    
    jTextField_txtCuadroCategoria.requestFocus();
    
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
cone2= conex.CargarDB_Lista_de_Categorias();
//String columnas[] = {"ID", "Categorias"};
String columnas[] = {"Categorias","ID"};

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

            ResultSet r = orden.executeQuery("Select* From Lista_de_Categorias");
            
             while (r.next())
             { 
               Object Filas[]={r.getString("Titulo_Categoria"),r.getString("ID")};
             //  Object Filas[]={r.getString("Titulo_Categoria")};

// Le digo ahora que tome estas filas dentro de la tabla

            dft.addRow(Filas);

             }
             jTable_Listado_de_Categorias.setModel(dft);
                          r.close();
                        orden.close();
           
         }
         catch (SQLException ex)
         {
             Logger.getLogger(Ventana_Categorias.class.getName()).log(Level.SEVERE, null, ex);
         }
}    

private void FiltrarCategorias(String Establezco_Filtro)
{
    cone2=conex.CargarDB_Lista_de_Categorias();
    
    if(cone2!=null)
    {
        try
        {
            Statement orden = cone2.createStatement();
 //           String Filtro = "Select* From Lista_de_Categorias where Titulo_Categoria LIKE '%"+jTextField_BarradeBusqueda.getText().trim()+"%' ";
            String Filtro = "Select* From Lista_de_Categorias where Titulo_Categoria LIKE '%"+jTextField_txtCuadroCategoria.getText().trim()+"%' ";
            ResultSet r=orden.executeQuery(Filtro);
/////////////
String columnas[] = {"Categorias","ID"};
//String columnas[] = {"ID", "Categorias"};
DefaultTableModel dft = new DefaultTableModel(null,columnas);

            
             while (r.next())
             {  
               Object Filas[]={r.getString("Titulo_Categoria"),r.getString("ID")};
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
//Cargo la Base de Datos    
cone2 = conex.CargarDB_Lista_de_Categorias();

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
               
    String editar ="Update Lista_de_Categorias Set Titulo_Categoria='"+jTextField_txtCuadroCategoria.getText()+"' where ID="+jTextField_ID_Categoria.getText()+"";
// Ejecuta ahora la Orden de arriba:
        orden.executeUpdate(editar);    
        JOptionPane.showMessageDialog(this, "¡Categoria sea Modificada con Exito!");
        orden.close(); 
        ReAbrirVentanaCategorias(); 
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
    cone2=conex.CargarDB_Lista_de_Categorias();
// Cargo la base de datos mas arriba para tenerla disponible en todo.       
        if(jTextField_txtCuadroCategoria.getText().equals("")||jTextField_txtCuadroCategoria.getText().equals("(Escribe la Nueva Categoria)"))
        {
        JOptionPane.showMessageDialog(this, "Debe escribir la categoria antes de agregar");
        }else 
        {
        if(cone2!=null)
  {
      try
      {
          Statement orden = cone2.createStatement();
          String crear = "Insert into Lista_de_Categorias(Titulo_Categoria,ID) Values("
                  + "'"+jTextField_txtCuadroCategoria.getText()+ "',"
                   + ""+jTextField_ID_Categoria.getText()+")";                     
                  
//                  + ""+jTextField_ID_Categoria.getText()+","
//                  + "'"+jTextField_txtCuadroCategoria.getText()+ "')";
          
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
    
}
public void ReAbrirVentanaCategorias()
{
        dispose();
        Panel_Inicio VB = new Panel_Inicio();
        Ventana_Categorias ventanabierta = new Ventana_Categorias();
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
        jTextField_ID_Categoria = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton_AgregarCategoria = new javax.swing.JButton();
        jButton_ModificarCategoria = new javax.swing.JButton();
        jButton_EliminarCategoria = new javax.swing.JButton();
        jButton_CerrarCategoria = new javax.swing.JButton();
        jTextField_txtCuadroCategoria = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Listado_de_Categorias = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("RemitoAPP / Categorias");
        setAlwaysOnTop(true);
        setResizable(false);

        jPanel1.setName(""); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Categorias");

        jTextField_ID_Categoria.setEditable(false);
        jTextField_ID_Categoria.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_ID_Categoria.setAutoscrolls(false);
        jTextField_ID_Categoria.setFocusable(false);

        jLabel2.setText("ID:");
        jLabel2.setFocusable(false);

        jButton_AgregarCategoria.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_AgregarCategoria.setText("+ Agregar");
        jButton_AgregarCategoria.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton_AgregarCategoria.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton_AgregarCategoria.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_AgregarCategoriaActionPerformed(evt);
            }
        });

        jButton_ModificarCategoria.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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

        jTextField_txtCuadroCategoria.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_txtCuadroCategoria.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_txtCuadroCategoria.setFocusCycleRoot(true);
        jTextField_txtCuadroCategoria.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jTextField_txtCuadroCategoriaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                jTextField_txtCuadroCategoriaMouseEntered(evt);
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
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                jTextField_txtCuadroCategoriaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                jTextField_txtCuadroCategoriaKeyReleased(evt);
            }
        });

        jTable_Listado_de_Categorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {

            }
        ));
        jTable_Listado_de_Categorias.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jTable_Listado_de_Categorias.setAutoscrolls(false);
        jTable_Listado_de_Categorias.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable_Listado_de_Categorias.setGridColor(new java.awt.Color(0, 0, 0));
        jTable_Listado_de_Categorias.getTableHeader().setReorderingAllowed(false);
        jTable_Listado_de_Categorias.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jTable_Listado_de_CategoriasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Listado_de_Categorias);

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
                        .addComponent(jTextField_ID_Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_AgregarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton_EliminarCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                                .addComponent(jButton_ModificarCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGap(127, 127, 127)
                                    .addComponent(jButton_CerrarCategoria))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(190, Short.MAX_VALUE)
                    .addComponent(jTextField_txtCuadroCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                            .addComponent(jTextField_ID_Categoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                        .addComponent(jButton_AgregarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_ModificarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jButton_EliminarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_CerrarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(91, 91, 91)
                    .addComponent(jTextField_txtCuadroCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void jButton_AgregarCategoriaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_AgregarCategoriaActionPerformed
    {//GEN-HEADEREND:event_jButton_AgregarCategoriaActionPerformed
AgregarRegistro();
    }//GEN-LAST:event_jButton_AgregarCategoriaActionPerformed

    private void jTextField_txtCuadroCategoriaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextField_txtCuadroCategoriaActionPerformed
    {//GEN-HEADEREND:event_jTextField_txtCuadroCategoriaActionPerformed

    }//GEN-LAST:event_jTextField_txtCuadroCategoriaActionPerformed

    private void jTextField_txtCuadroCategoriaMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTextField_txtCuadroCategoriaMouseClicked
    {//GEN-HEADEREND:event_jTextField_txtCuadroCategoriaMouseClicked
        if(jTextField_txtCuadroCategoria.getText().equals("(Escribe la Nueva Categoria)"))
        {
                  jTextField_txtCuadroCategoria.setText("");
                  jButton_AgregarCategoria.setVisible(true);
        }  

    }//GEN-LAST:event_jTextField_txtCuadroCategoriaMouseClicked

    private void jButton_CerrarCategoriaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_CerrarCategoriaActionPerformed
    {//GEN-HEADEREND:event_jButton_CerrarCategoriaActionPerformed
//  Java: dispose() es usado para cerrar un jframe
        if(jTextField_txtCuadroCategoria.getText().equals("(Escribe la Nueva Ubicación)")||jTextField_txtCuadroCategoria.getText().equals("")){
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
    }//GEN-LAST:event_jButton_CerrarCategoriaActionPerformed

    private void jButton_ModificarCategoriaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_ModificarCategoriaActionPerformed
    {//GEN-HEADEREND:event_jButton_ModificarCategoriaActionPerformed
// Modificar
EditarRegistro(); 
    }//GEN-LAST:event_jButton_ModificarCategoriaActionPerformed

    private void jTable_Listado_de_CategoriasMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTable_Listado_de_CategoriasMouseClicked
    {//GEN-HEADEREND:event_jTable_Listado_de_CategoriasMouseClicked
        Bandera_Modificando="Si";
        
        
        jButton_AgregarCategoria.setVisible(false);
        jButton_ModificarCategoria.setVisible(true);
        jButton_EliminarCategoria.setVisible(true);
        
        // evento Mouse Clicked en la Tabla:
             jTextField_ID_Categoria.setText("");
             jTextField_txtCuadroCategoria.setText("");
             
             int seleccion=jTable_Listado_de_Categorias.rowAtPoint(evt.getPoint());
            
            
             jTextField_txtCuadroCategoria.setText(String.valueOf(jTable_Listado_de_Categorias.getValueAt(seleccion,0)));             
            jTextField_ID_Categoria.setText(String.valueOf(jTable_Listado_de_Categorias.getValueAt(seleccion,1)));
                jTextField_txtCuadroCategoria.requestFocus();
            
    }//GEN-LAST:event_jTable_Listado_de_CategoriasMouseClicked

    private void jTextField_txtCuadroCategoriaKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextField_txtCuadroCategoriaKeyReleased
    {//GEN-HEADEREND:event_jTextField_txtCuadroCategoriaKeyReleased
        FiltrarCategorias(jTextField_txtCuadroCategoria.getText().trim());
    }//GEN-LAST:event_jTextField_txtCuadroCategoriaKeyReleased

    private void jButton_EliminarCategoriaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_EliminarCategoriaActionPerformed
    {//GEN-HEADEREND:event_jButton_EliminarCategoriaActionPerformed
// Eliminar
        if(jTextField_txtCuadroCategoria.getText().equals("")||jTextField_txtCuadroCategoria.getText().equals("(Escribe la Nueva Categoria)"))
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

    private void jTextField_txtCuadroCategoriaMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTextField_txtCuadroCategoriaMouseEntered
    {//GEN-HEADEREND:event_jTextField_txtCuadroCategoriaMouseEntered
        String Entro = null;

if(Bandera_Modificando=="Si"&& Entro!="Si")
{
    String TextoTemporal=jTextField_txtCuadroCategoria.getText();   
    System.out.println("TextoGuardado: "+TextoTemporal);
   Entro="No";  
}
        
    }//GEN-LAST:event_jTextField_txtCuadroCategoriaMouseEntered

    private void jTextField_txtCuadroCategoriaKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextField_txtCuadroCategoriaKeyPressed
    {//GEN-HEADEREND:event_jTextField_txtCuadroCategoriaKeyPressed
       
        if(evt.getKeyCode()==KeyEvent.VK_ENTER&&Bandera_Modificando=="No")
        {
         AgregarRegistro();
        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER&&Bandera_Modificando=="Si")
        {
            EditarRegistro();
        }   
    }//GEN-LAST:event_jTextField_txtCuadroCategoriaKeyPressed

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
        java.util.logging.Logger.getLogger(Ventana_Categorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    catch (InstantiationException ex)
    {
        java.util.logging.Logger.getLogger(Ventana_Categorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    catch (IllegalAccessException ex)
    {
        java.util.logging.Logger.getLogger(Ventana_Categorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    catch (javax.swing.UnsupportedLookAndFeelException ex)
    {
        java.util.logging.Logger.getLogger(Ventana_Categorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable()
    {
    public void run()
    {
        new Ventana_Categorias().setVisible(true);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Listado_de_Categorias;
    private javax.swing.JTextField jTextField_ID_Categoria;
    private javax.swing.JTextField jTextField_txtCuadroCategoria;
    // End of variables declaration//GEN-END:variables

    private void EliminarRegistro()
    {
        cone2 = conex.CargarDB_Lista_de_Categorias();

        if(cone2!=null)
        {
            try
            {
                Statement orden = cone2.createStatement();
                int Comienza_desde_Aqui=Integer.parseInt(jTextField_ID_Categoria.getText());
                System.out.println("Comienza_desde_Aqui:"+Comienza_desde_Aqui);     

                String Elminar = "DELETE From Lista_de_Categorias Where ID="+jTextField_ID_Categoria.getText();

                orden.executeUpdate(Elminar);
               
            //    String AlterarTabla = "ALTER TABLE Lista_de_Categorias DROP ID";
                
            //    orden.executeUpdate(AlterarTabla);
                
            //    String Renumerar_ID = "ALTER TABLE Lista_de_Categorias ADD ID Primary key";
                
            //    orden.executeUpdate(Renumerar_ID);
            
             cone2.close();
                JOptionPane.showMessageDialog(this, "Categoria Elimina");
         //       ReAbrirVentanaCategorias();
         
                 jTextField_txtCuadroCategoria.setText("");

                  jButton_EliminarCategoria.setVisible(false);
                  jButton_AgregarCategoria.setVisible(true);
                  jButton_ModificarCategoria.setVisible(false);                
                
                id_borrado_categoria=Comienza_desde_Aqui;
                SeBorroCategoria="SI";
              jTextField_ID_Categoria.setText(String.valueOf(Comienza_desde_Aqui));
              jTextField_txtCuadroCategoria.requestFocus();
              
              //PropiedadesTabla();
              ReAbrirVentanaCategorias();
              

                            
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
    cone2 = conex.CargarDB_Lista_de_Categorias();
    try
    {
      Statement orden = cone2.createStatement();
      ResultSet r = orden.executeQuery("Select MAX(ID) From Lista_de_Categorias");
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
