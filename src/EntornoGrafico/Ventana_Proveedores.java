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
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Raul Eduardo Scalia
 * https://github.com/Res83
 */
public class Ventana_Proveedores extends javax.swing.JFrame
{
// Cargo la relación con la base de datos //////////////////////////////////////////////////////

    Conexion conex = new Conexion();
    Connection cone2;
     Conexion conexion_Base_datos_Provedores = new Conexion();

/////////////////////////////////////////////////////////////////////////////////////////////////
     
   // int contador_de_filas=1;
    private String SeBorroRegistro;
    private String TextoTemporal;
    private int Codigo_Proveedor_borrado_categoria;
    private Integer Comienza_desde_Aqui;
    private String Bandera_Modificando;
    private int seleccion;

public Ventana_Proveedores()
{
    initComponents();
    
        setIconImage(new ImageIcon(getClass().getResource("../Imagenes/Logo.png")).getImage());
        
    
//Crea y carga Base de Datos /////////////////////////////////////////////////////////////

    conex.CrearDB_Base_datos_Proveedores();
    cone2= conex.CargarDB_Base_datos_Proveedores();
    
///////////////////////////////////////////////////////////////////////////////////////////    
    int Codigo_Proveedor_borrado=0;
    
    
    jButton_EliminarRegistro.setVisible(false);
    
    jButton_ModificarRegistro.setVisible(false);
    
   // jTextField2_txtTelefonoMovil_Proveedor.setVisible(false);
   // jTextField1_txtTelefonoFijo_Proveedor.setVisible(false);
    
    
    jTextField_Empresa.requestFocus();
    
    Bandera_Modificando="No";
    
    if(cone2!=null)
    {
    PropiedadesTabla();
 //   PropiedadesTablaProductos();
    
  

    
    
    
    //IniciarContador(contador_de_filas);
  
    
    //JOptionPane.showMessageDialog (null,"Mensaje","Titulo",JOptionPane.ERRROR_MESSAGE); 
    }
    
}

private void PropiedadesTabla()
{
cone2= conex.CargarDB_Base_datos_Proveedores();
String columnas[]=
     {
         "Cod",
         "Empresa",
         "Nombre y Apellido",
         "Calle",
         "Altura",
         "Teléfono",
         "Celular",
         "Piso",
         "Entre Calles",
         "GoogleMaps",
         "CP",
         "Provincia",
         "Ciudad",
         "E-mail",
         "Nota"
     };



if(!"SI".equals(SeBorroRegistro)){
int Codigo_Proveedor=Codigo_Proveedor_incrementable();
visor_codigo_proveedor.setText(String.valueOf(Codigo_Proveedor));    
}
// Primero toma las filas pero no las tengo y pongo null
// Constructor de la Tabla
DefaultTableModel dft = new DefaultTableModel(null,columnas);

//    jTable_TabladeRegistros.getColumn(0).setPreferredWidth(500);
//    jTable_TabladeRegistros.getColumn(1).setPreferredWidth(150);
//    jTable_TabladeRegistros.getColumn(2).setPreferredWidth(200);
//    jTable_TabladeRegistros.getColumn(3).setPreferredWidth(250);
//    jTable_TabladeRegistros.getColumn(4).setPreferredWidth(250);
//    jTable_TabladeRegistros.getColumn(5).setPreferredWidth(50);
//    jTable_TabladeRegistros.getColumn(6).setPreferredWidth(150);
//    jTable_TabladeRegistros.getColumn(7).setPreferredWidth(200);
//    jTable_TabladeRegistros.getColumn(8).setPreferredWidth(250);
//    jTable_TabladeRegistros.getColumn(9).setPreferredWidth(250);
//    jTable_TabladeRegistros.getColumn(10).setPreferredWidth(50);
//    jTable_TabladeRegistros.getColumn(11).setPreferredWidth(150);
//    jTable_TabladeRegistros.getColumn(12).setPreferredWidth(200);
//    jTable_TabladeRegistros.getColumn(13).setPreferredWidth(250);
//    jTable_TabladeRegistros.getColumn(14).setPreferredWidth(250);

         try
         {
//Monstrar Algo de una Base de Datos:
    try (Statement orden = cone2.createStatement())
    {
        //Monstrar Algo de una Base de Datos:
        
        ResultSet r = orden.executeQuery("Select* From ListadeProveedores");

        
        while (r.next())
        {           
            Object Filas[] = 
         {
             r.getString("Codigo_Proveedor"),
             r.getString("Empresa"),
             r.getString("NombreyApellidoProveedor"),
             r.getString("Calle_Proveedor"),
             r.getString("Calle_Numero_Proveedor"),
             r.getString("TelefonoFijo_Proveedor"),
             r.getString("TelefonoMovil_Proveedor"),
             r.getString("Piso_Proveedor"),
             r.getString("EntreCalles_Proveedor"),
             r.getString("GoogleMaps_Proveedor"),
             r.getString("CODPOSTAL_Proveedor"),
             r.getString("Provincia_Proveedor"),
             r.getString("Ciudad_Proveedor"),
             r.getString("Email_Proveedor"),
             r.getString("Anotacionl_Proveedor")
                 
         };
                       
            
            //  Object Filas[]={r.getString("Titulo_Categoria")};
            
// Le digo ahora que tome estas filas dentro de la tabla

dft.addRow(Filas);

        }
        jTable_TabladeRegistros.setModel(dft);
        r.close();
    }
          
         }
         catch (Exception ex)
         {
             Logger.getLogger(Ventana_Proveedores.class.getName()).log(Level.SEVERE, null, ex);
         }
      //   DefaultTableModel columnModel = jTable_TabladeRegistros.getColumnModel();


}    
private void PropiedadesTablaProductos()
{
cone2= conex.CargarDB_Base_datos_Productos();
String columnas[]=
     {
         "Codigo_Producto",
         "Categoria_Producto",
         "Descripcion_Producto"
     };

// Primero toma las filas pero no las tengo y pongo null
// Constructor de la Tabla
DefaultTableModel dft2 = new DefaultTableModel(null,columnas);

         try
         {
//Monstrar Algo de una Base de Datos:
    try (Statement orden = cone2.createStatement())
    {
        //Monstrar Algo de una Base de Datos:
        //Codigo_Provedor
//        Codigo_Proveedor="+jTextField_txtCodigo_Proveedor.getText();
        
        ResultSet r = orden.executeQuery("Select Codigo_Proveedor  From Listadeproductos");
        
        while (r.next())
        {           
            Object Filas[] = 
         {
             r.getString("Codigo_Producto"),
             r.getString("Categoria_Producto"),
             r.getString("Descripcion_Producto")
         };
            
            
            
            //  Object Filas[]={r.getString("Titulo_Categoria")};
            
// Le digo ahora que tome estas filas dentro de la tabla

dft2.addRow(Filas);

        }
        jTable_Productos.setModel(dft2);
        r.close();
    }
           
         }
         catch (Exception ex)
         {
             Logger.getLogger(Ventana_Proveedores.class.getName()).log(Level.SEVERE, null, ex);
         }
}    

private void FiltrarRegistro(String Establezco_Filtro)
{
    cone2= conex.CargarDB_Base_datos_Proveedores();
    
    if(cone2!=null)
    {
        try
        {
            Statement orden = cone2.createStatement();
            String Filtro = "Select* From ListadeProveedores where Empresa LIKE '%"+jTextField_Empresa.getText().trim()+"%' ";
            ResultSet r=orden.executeQuery(Filtro);
/////////////
String columnas[]=
     {
         "Codigo",
         "Empresa",
         "Nombre y Apellido",
         "Calle",
         "Altura",
         "Teléfono",
         "Celular",
         "Piso",
         "Entre Calles",
         "GoogleMaps",
         "CP",
         "Provincia",
         "Ciudad",
         "E-mail",
         "Nota"
     };

DefaultTableModel dft = new DefaultTableModel(null,columnas);

            
             while (r.next())
             {  
            Object Filas[] = 
         {
             r.getString("Codigo_Proveedor"),
             r.getString("Empresa"),
             r.getString("NombreyApellidoProveedor"),
             r.getString("Calle_Proveedor"),
             r.getString("Calle_Numero_Proveedor"),
             r.getString("TelefonoFijo_Proveedor"),
             r.getString("TelefonoMovil_Proveedor"),
             r.getString("Piso_Proveedor"),
             r.getString("EntreCalles_Proveedor"),
             r.getString("GoogleMaps_Proveedor"),
             r.getString("CODPOSTAL_Proveedor"),
             r.getString("Provincia_Proveedor"),
             r.getString("Ciudad_Proveedor"),
             r.getString("Email_Proveedor"),
             r.getString("Anotacionl_Proveedor")
         };
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
    cone2= conex.CargarDB_Base_datos_Proveedores();

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
            
               String editar="Update ListadeProveedores set "
                        + "Empresa='"+jTextField_Empresa.getText()+"',"
                        + "NombreyApellidoProveedor='"+jTextField1_txtNombreApellido_Proveedor.getText()+"',"
                        + "Calle_Proveedor='"+jTextField1_txtCalle_Proveedor.getText()+"',"
                        + "Calle_Numero_Proveedor="+jTextField1_txtCalle_Numero_Proveedor.getText()+","
                        + "TelefonoFijo_Proveedor="+numero_telefono_fijo.getText()+","
                        + "TelefonoMovil_Proveedor="+numero_celular.getText()+","
                        + "Piso_Proveedor='"+jTextField1_txtPiso_Proveedor.getText()+"',"
                        + "EntreCalles_Proveedor='"+jTextField1_txtEntreCalles_Proveedor.getText()+"',"
                        + "GoogleMaps_Proveedor='"+jTextField1_txtGoogleMaps_Proveedor.getText()+"',"
                        + "CODPOSTAL_Proveedor='"+jTextField1_txtCODPOSTAL_Proveedor.getText()+"',"
                        + "Provincia_Proveedor='"+jTextField1_txtProvincia_Proveedor.getText()+"',"
                        + "Ciudad_Proveedor='"+jTextField1_txtCiudad_Proveedor.getText()+"',"
                        + "Email_Proveedor='"+jTextField1_txtEmail_Proveedor.getText()+"',"
                        + "Anotacionl_Proveedor='"+jTextArea1_txtAnotacionl_Proveedor.getText()+"'"
                       + "Where Codigo_Proveedor="+visor_codigo_proveedor.getText();
// Ejecuta ahora la Orden de arriba:
orden.executeUpdate(editar);
JOptionPane.showMessageDialog(this, "¡Modificada con Exito!");
        } 

   ReAbrirVentana(); 
// Actualizar la tabla con el cambio realizado  
//    int Codigo_Proveedor_borrado=0;
//    
//            LimpiarCasilleros();
//                  jButton_EliminarRegistro.setVisible(false);
//                  jButton_AgregarRegistro.setVisible(true);
//                  jButton_ModificarRegistro.setVisible(false);
//    
//    jTextField_Empresa.requestFocus();
//    
//    Bandera_Modificando="No";
//    
//        PropiedadesTabla();

// Es importante cerrar cuando no se usa mas:

    }
    catch (Exception ex)
    {
        System.out.println("Error: "+ex);
    }
}    
           
}
private void AgregarRegistro()
{ 

    cone2= conex.CargarDB_Base_datos_Proveedores();
// Cargo la base de datos mas arriba para tenerla disponible en todo.       
        if(cone2!=null)
  {
      try
      {
          try (Statement orden = cone2.createStatement())
          {
              
                String crear = "Insert Into ListadeProveedores"
                        + "("
                        + "Codigo_Proveedor,"
                        + "Empresa,"
                        + "NombreyApellidoProveedor,"
                        + "Calle_Proveedor,"
                        + "Calle_Numero_Proveedor,"
                        + "TelefonoFijo_Proveedor,"
                        + "TelefonoMovil_Proveedor,"
                        + "Piso_Proveedor,"
                        + "EntreCalles_Proveedor,"
                        + "GoogleMaps_Proveedor,"
                        + "CODPOSTAL_Proveedor,"
                        + "Provincia_Proveedor,"
                        + "Ciudad_Proveedor,"
                        + "Email_Proveedor,"
                        + "Anotacionl_Proveedor"
                        + ") Values("
                        + ""+visor_codigo_proveedor.getText()+","
                        + "'"+jTextField_Empresa.getText()+"',"
                        + "'"+jTextField1_txtNombreApellido_Proveedor.getText()+"',"
                        + "'"+jTextField1_txtCalle_Proveedor.getText()+"',"
                        + ""+jTextField1_txtCalle_Numero_Proveedor.getText()+","
                        + ""+numero_telefono_fijo.getText()+","
                        + ""+numero_celular.getText()+","
                        + "'"+jTextField1_txtPiso_Proveedor.getText()+"',"
                        + "'"+jTextField1_txtEntreCalles_Proveedor.getText()+"',"
                        + "'"+jTextField1_txtGoogleMaps_Proveedor.getText()+"',"
                        + "'"+jTextField1_txtCODPOSTAL_Proveedor.getText()+"',"
                        + "'"+jTextField1_txtProvincia_Proveedor.getText()+"',"
                        + "'"+jTextField1_txtCiudad_Proveedor.getText()+"',"
                        + "'"+jTextField1_txtEmail_Proveedor.getText()+"',"
                        + "'"+jTextArea1_txtAnotacionl_Proveedor.getText()+"'"
                        + ")";
                
          
// Para ejecutar lo anterior se cree y actualice cada campo en la base de datos.
orden.executeUpdate(crear);
System.out.println("Registro Agregado OK");

//   IniciarContador(contador_de_filas);
PropiedadesTabla();
JOptionPane.showMessageDialog(this, "Nueva Proveedor Agregado: [ "+jTextField_Empresa.getText()+" ]");
//r.close();
LimpiarCasilleros();
SeBorroRegistro="NO";
ReAbrirVentana();
          }
            
      }
      catch (Exception ex)
      {
          System.out.println("Error:"+ex); 
                  JOptionPane.showMessageDialog(this, "Es necesario que agregue mas información");
    
      }
  }
//else
//        {
//       JOptionPane.showMessageDialog(this, "Debe escribir antes de agregar"); 
//        }    
    
}
public void ReAbrirVentana()
{
        dispose();
        Panel_Inicio VB = new Panel_Inicio();
        Ventana_Proveedores ventanabierta = new Ventana_Proveedores();
        ventanabierta.setLocationRelativeTo(getParent());
        ventanabierta.setVisible(true); 
}

/**
 * This method is called within the constructor to
 * initialize the form.
 * WARNING: Do NOT modify this code. The content of this method is
 * always regenerated by the Form Editor.
 */
@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_TabladeRegistros = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jTextField_Empresa = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField1_txtNombreApellido_Proveedor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField1_txtCalle_Proveedor = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField1_txtCalle_Numero_Proveedor = new javax.swing.JTextField();
        numero_celular = new javax.swing.JTextField();
        numero_telefono_fijo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextField1_txtPiso_Proveedor = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField1_txtEntreCalles_Proveedor = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField1_txtGoogleMaps_Proveedor = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField1_txtCODPOSTAL_Proveedor = new javax.swing.JTextField();
        jTextField1_txtProvincia_Proveedor = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField1_txtCiudad_Proveedor = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField1_txtEmail_Proveedor = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1_txtAnotacionl_Proveedor = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField_Buscar = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_Productos = new javax.swing.JTable();
        jLabel2_Codigo_Proveedor = new javax.swing.JLabel();
        visor_codigo_proveedor = new javax.swing.JLabel();
        jButton_AgregarRegistro = new javax.swing.JButton();
        jButton_ModificarRegistro = new javax.swing.JButton();
        jButton_EliminarRegistro = new javax.swing.JButton();
        jButton_Cerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("RemitoAPP / Ubicaciones");
        setAlwaysOnTop(true);
        setResizable(false);

        jPanel1.setName(""); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Proveedores");

        jTable_TabladeRegistros.setAutoCreateRowSorter(true);
        jTable_TabladeRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable_TabladeRegistros.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable_TabladeRegistros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable_TabladeRegistros.setGridColor(new java.awt.Color(0, 0, 0));
        jTable_TabladeRegistros.getTableHeader().setReorderingAllowed(false);
        jTable_TabladeRegistros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_TabladeRegistrosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_TabladeRegistros);

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Empresa:");

        jTextField_Empresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_EmpresaKeyPressed(evt);
            }
        });

        jLabel3.setText("Nombre y Apellido:");

        jTextField1_txtNombreApellido_Proveedor.setToolTipText("Campo Mínimo requerido (Requerido)");
        jTextField1_txtNombreApellido_Proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1_txtNombreApellido_ProveedorActionPerformed(evt);
            }
        });
        jTextField1_txtNombreApellido_Proveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1_txtNombreApellido_ProveedorKeyPressed(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Calle:");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jTextField1_txtCalle_Proveedor.setToolTipText("Campo Mínimo requerido (Requerido)");
        jTextField1_txtCalle_Proveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1_txtCalle_ProveedorKeyPressed(evt);
            }
        });

        jLabel6.setText("Numero:");

        jTextField1_txtCalle_Numero_Proveedor.setToolTipText("Campo Mínimo requerido (Requerido)");
        jTextField1_txtCalle_Numero_Proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1_txtCalle_Numero_ProveedorActionPerformed(evt);
            }
        });
        jTextField1_txtCalle_Numero_Proveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1_txtCalle_Numero_ProveedorKeyPressed(evt);
            }
        });

        numero_celular.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        numero_celular.setToolTipText("Campo Mínimo requerido (Requerido)");
        numero_celular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numero_celularActionPerformed(evt);
            }
        });
        numero_celular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                numero_celularKeyPressed(evt);
            }
        });

        numero_telefono_fijo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        numero_telefono_fijo.setToolTipText("Campo Mínimo requerido (Requerido)");
        numero_telefono_fijo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                numero_telefono_fijoKeyPressed(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Celular:");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Telefono Fijo:");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField_Empresa)
                    .addComponent(jTextField1_txtNombreApellido_Proveedor, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jTextField1_txtCalle_Proveedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1_txtCalle_Numero_Proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(numero_celular, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(numero_telefono_fijo, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(158, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField_Empresa)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1_txtNombreApellido_Proveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1_txtCalle_Proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jTextField1_txtCalle_Numero_Proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(numero_celular)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(numero_telefono_fijo)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Datos Basicos", jPanel2);

        jLabel8.setText("Piso / Depto :");

        jTextField1_txtPiso_Proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1_txtPiso_ProveedorActionPerformed(evt);
            }
        });
        jTextField1_txtPiso_Proveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1_txtPiso_ProveedorKeyPressed(evt);
            }
        });

        jLabel9.setText("Entre Calles :");

        jTextField1_txtEntreCalles_Proveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1_txtEntreCalles_ProveedorKeyPressed(evt);
            }
        });

        jLabel10.setText("GoogleMaps :");

        jTextField1_txtGoogleMaps_Proveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1_txtGoogleMaps_ProveedorKeyPressed(evt);
            }
        });

        jLabel11.setText("Código Postal :");

        jTextField1_txtCODPOSTAL_Proveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1_txtCODPOSTAL_ProveedorKeyPressed(evt);
            }
        });

        jTextField1_txtProvincia_Proveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1_txtProvincia_ProveedorKeyPressed(evt);
            }
        });

        jLabel12.setText("Provincia :");

        jLabel13.setText("Ciudad :");

        jTextField1_txtCiudad_Proveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1_txtCiudad_ProveedorKeyPressed(evt);
            }
        });

        jLabel14.setText("E-mail:");

        jTextField1_txtEmail_Proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1_txtEmail_ProveedorActionPerformed(evt);
            }
        });
        jTextField1_txtEmail_Proveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1_txtEmail_ProveedorKeyPressed(evt);
            }
        });

        jLabel16.setText("Anotación (Un bloque de texto Libre):");

        jTextArea1_txtAnotacionl_Proveedor.setColumns(20);
        jTextArea1_txtAnotacionl_Proveedor.setRows(5);
        jScrollPane3.setViewportView(jTextArea1_txtAnotacionl_Proveedor);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1_txtPiso_Proveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                            .addComponent(jTextField1_txtEntreCalles_Proveedor)
                            .addComponent(jTextField1_txtGoogleMaps_Proveedor)
                            .addComponent(jTextField1_txtEmail_Proveedor)
                            .addComponent(jTextField1_txtCiudad_Proveedor)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jTextField1_txtCODPOSTAL_Proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1_txtProvincia_Proveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))))
                    .addComponent(jLabel16)
                    .addComponent(jScrollPane3))
                .addContainerGap(330, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField1_txtPiso_Proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField1_txtEntreCalles_Proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField1_txtGoogleMaps_Proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField1_txtCODPOSTAL_Proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField1_txtProvincia_Proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField1_txtCiudad_Proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField1_txtEmail_Proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Datos Opcionales", jPanel3);

        jLabel2.setText("Buscar");

        jTable_Productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable_Productos);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(418, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Productos", jPanel4);

        jLabel2_Codigo_Proveedor.setText("Código:");

        visor_codigo_proveedor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        visor_codigo_proveedor.setText("000");
        visor_codigo_proveedor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2_Codigo_Proveedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(visor_codigo_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2_Codigo_Proveedor)
                        .addComponent(visor_codigo_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jButton_AgregarRegistro.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_AgregarRegistro.setText("+ Agregar");
        jButton_AgregarRegistro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_AgregarRegistro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_AgregarRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AgregarRegistroActionPerformed(evt);
            }
        });

        jButton_ModificarRegistro.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_ModificarRegistro.setText("* Modificar");
        jButton_ModificarRegistro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_ModificarRegistro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_ModificarRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ModificarRegistroActionPerformed(evt);
            }
        });

        jButton_EliminarRegistro.setBackground(new java.awt.Color(255, 0, 0));
        jButton_EliminarRegistro.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton_EliminarRegistro.setText("- Eliminar");
        jButton_EliminarRegistro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_EliminarRegistro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_EliminarRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EliminarRegistroActionPerformed(evt);
            }
        });

        jButton_Cerrar.setText("Cerrar");
        jButton_Cerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jButton_AgregarRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_ModificarRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(jButton_EliminarRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_Cerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_AgregarRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_ModificarRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_EliminarRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_Cerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_AgregarRegistroActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_AgregarRegistroActionPerformed
    {//GEN-HEADEREND:event_jButton_AgregarRegistroActionPerformed
        Estado_telefonos();
        AgregarRegistro();
    }//GEN-LAST:event_jButton_AgregarRegistroActionPerformed

    private void jButton_CerrarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_CerrarActionPerformed
    {//GEN-HEADEREND:event_jButton_CerrarActionPerformed
//  Java: dispose() es usado para cerrar un jframe
        if(jTextField_Empresa.getText().equals("(Escribe la Nueva Ubicación)")||jTextField_Empresa.getText().equals("")){
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
Estado_telefonos();
EditarRegistro(); 
    }//GEN-LAST:event_jButton_ModificarRegistroActionPerformed

    private void jTable_TabladeRegistrosMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTable_TabladeRegistrosMouseClicked
    {//GEN-HEADEREND:event_jTable_TabladeRegistrosMouseClicked
        Bandera_Modificando="Si";
        
        
        jButton_AgregarRegistro.setVisible(false);
        jButton_ModificarRegistro.setVisible(true);
        jButton_EliminarRegistro.setVisible(true);
        
        // evento Mouse Clicked en la Tabla:
             visor_codigo_proveedor.setText("");
             jTextField_Empresa.setText("");
             
             int seleccion=jTable_TabladeRegistros.rowAtPoint(evt.getPoint());
            
            
            visor_codigo_proveedor.setText(String.valueOf(jTable_TabladeRegistros.getValueAt(seleccion,0)));
            jTextField_Empresa.setText(String.valueOf(jTable_TabladeRegistros.getValueAt(seleccion,1)));
            jTextField1_txtNombreApellido_Proveedor.setText(String.valueOf(jTable_TabladeRegistros.getValueAt(seleccion,2)));
            jTextField1_txtCalle_Proveedor.setText(String.valueOf(jTable_TabladeRegistros.getValueAt(seleccion,3)));             
            jTextField1_txtCalle_Numero_Proveedor.setText(String.valueOf(jTable_TabladeRegistros.getValueAt(seleccion,4)));
            numero_telefono_fijo.setText(String.valueOf(jTable_TabladeRegistros.getValueAt(seleccion,5)));
            numero_celular.setText(String.valueOf(jTable_TabladeRegistros.getValueAt(seleccion,6)));             
            jTextField1_txtPiso_Proveedor.setText(String.valueOf(jTable_TabladeRegistros.getValueAt(seleccion,7)));
            jTextField1_txtEntreCalles_Proveedor.setText(String.valueOf(jTable_TabladeRegistros.getValueAt(seleccion,8)));
            jTextField1_txtGoogleMaps_Proveedor.setText(String.valueOf(jTable_TabladeRegistros.getValueAt(seleccion,9)));             
            jTextField1_txtCODPOSTAL_Proveedor.setText(String.valueOf(jTable_TabladeRegistros.getValueAt(seleccion,10)));
            jTextField1_txtProvincia_Proveedor.setText(String.valueOf(jTable_TabladeRegistros.getValueAt(seleccion,11)));
            jTextField1_txtCiudad_Proveedor.setText(String.valueOf(jTable_TabladeRegistros.getValueAt(seleccion,12)));             
            jTextField1_txtEmail_Proveedor.setText(String.valueOf(jTable_TabladeRegistros.getValueAt(seleccion,13)));
            jTextArea1_txtAnotacionl_Proveedor.setText(String.valueOf(jTable_TabladeRegistros.getValueAt(seleccion,14)));
                jTextField_Empresa.requestFocus();
            
    }//GEN-LAST:event_jTable_TabladeRegistrosMouseClicked

    private void jButton_EliminarRegistroActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_EliminarRegistroActionPerformed
    {//GEN-HEADEREND:event_jButton_EliminarRegistroActionPerformed
// Eliminar
        if(jTextField_Empresa.getText().equals("")||jTextField_Empresa.getText().equals("(Escribe la Nueva Ubicación)"))
        {
        JOptionPane.showMessageDialog(this, "Debe Selecionar de la tabla y luego pulsar Eliminar");
        }else 
        {
// Se crea un metodo EditarRegistro() para que las instruciones las podamos usar por cualquier otra parte.        
           
        String opcion[]={"Eliminar", "Cancelar"};
        int eleccion =JOptionPane.showOptionDialog(this, "¿Esta seguro de Eliminar ("+jTextField_Empresa.getText()+") ?", "Eliminar", 0, 0, null, opcion, NORMAL);
       
        if(eleccion==JOptionPane.YES_OPTION)
        {
            EliminarRegistro();
        }else if (eleccion==JOptionPane.NO_OPTION)
        {
            
        }
            
        }
    }//GEN-LAST:event_jButton_EliminarRegistroActionPerformed

    private void jTextField1_txtNombreApellido_ProveedorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextField1_txtNombreApellido_ProveedorActionPerformed
    {//GEN-HEADEREND:event_jTextField1_txtNombreApellido_ProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1_txtNombreApellido_ProveedorActionPerformed

    private void jTextField1_txtCalle_Numero_ProveedorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextField1_txtCalle_Numero_ProveedorActionPerformed
    {//GEN-HEADEREND:event_jTextField1_txtCalle_Numero_ProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1_txtCalle_Numero_ProveedorActionPerformed

    private void numero_celularActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_numero_celularActionPerformed
    {//GEN-HEADEREND:event_numero_celularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numero_celularActionPerformed

    private void jTextField1_txtPiso_ProveedorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextField1_txtPiso_ProveedorActionPerformed
    {//GEN-HEADEREND:event_jTextField1_txtPiso_ProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1_txtPiso_ProveedorActionPerformed

    private void jTextField1_txtEmail_ProveedorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextField1_txtEmail_ProveedorActionPerformed
    {//GEN-HEADEREND:event_jTextField1_txtEmail_ProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1_txtEmail_ProveedorActionPerformed

    private void jTextField_EmpresaKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextField_EmpresaKeyPressed
    {//GEN-HEADEREND:event_jTextField_EmpresaKeyPressed
        if(evt.getKeyCode()==evt.VK_ENTER|| evt.getKeyCode()==evt.VK_TAB)
        {
        jTextField1_txtNombreApellido_Proveedor.requestFocus();
        }
    }//GEN-LAST:event_jTextField_EmpresaKeyPressed

    private void jTextField1_txtNombreApellido_ProveedorKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextField1_txtNombreApellido_ProveedorKeyPressed
    {//GEN-HEADEREND:event_jTextField1_txtNombreApellido_ProveedorKeyPressed
        if(evt.getKeyCode()==evt.VK_ENTER|| evt.getKeyCode()==evt.VK_TAB)
        {
        jTextField1_txtCalle_Proveedor.requestFocus();
        }
    }//GEN-LAST:event_jTextField1_txtNombreApellido_ProveedorKeyPressed

    private void jTextField1_txtCalle_ProveedorKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextField1_txtCalle_ProveedorKeyPressed
    {//GEN-HEADEREND:event_jTextField1_txtCalle_ProveedorKeyPressed
        if(evt.getKeyCode()==evt.VK_ENTER|| evt.getKeyCode()==evt.VK_TAB)
        {
        jTextField1_txtCalle_Numero_Proveedor.requestFocus();
        }
    }//GEN-LAST:event_jTextField1_txtCalle_ProveedorKeyPressed

    private void jTextField1_txtCalle_Numero_ProveedorKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextField1_txtCalle_Numero_ProveedorKeyPressed
    {//GEN-HEADEREND:event_jTextField1_txtCalle_Numero_ProveedorKeyPressed
        if(evt.getKeyCode()==evt.VK_ENTER|| evt.getKeyCode()==evt.VK_TAB)
        {
        numero_telefono_fijo.requestFocus();
        }
    }//GEN-LAST:event_jTextField1_txtCalle_Numero_ProveedorKeyPressed

    private void numero_telefono_fijoKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_numero_telefono_fijoKeyPressed
    {//GEN-HEADEREND:event_numero_telefono_fijoKeyPressed
        if(evt.getKeyCode()==evt.VK_ENTER|| evt.getKeyCode()==evt.VK_TAB)
        {
        numero_celular.requestFocus();
        }
    }//GEN-LAST:event_numero_telefono_fijoKeyPressed

    private void numero_celularKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_numero_celularKeyPressed
    {//GEN-HEADEREND:event_numero_celularKeyPressed
        if(evt.getKeyCode()==evt.VK_ENTER|| evt.getKeyCode()==evt.VK_TAB)
        {
        jTextField1_txtPiso_Proveedor.requestFocus();
        }
    }//GEN-LAST:event_numero_celularKeyPressed

    private void jTextField1_txtPiso_ProveedorKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextField1_txtPiso_ProveedorKeyPressed
    {//GEN-HEADEREND:event_jTextField1_txtPiso_ProveedorKeyPressed
        if(evt.getKeyCode()==evt.VK_ENTER|| evt.getKeyCode()==evt.VK_TAB)
        {
        jTextField1_txtEntreCalles_Proveedor.requestFocus();
        }
    }//GEN-LAST:event_jTextField1_txtPiso_ProveedorKeyPressed

    private void jTextField1_txtEntreCalles_ProveedorKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextField1_txtEntreCalles_ProveedorKeyPressed
    {//GEN-HEADEREND:event_jTextField1_txtEntreCalles_ProveedorKeyPressed
        if(evt.getKeyCode()==evt.VK_ENTER|| evt.getKeyCode()==evt.VK_TAB)
        {
        jTextField1_txtGoogleMaps_Proveedor.requestFocus();
        }
    }//GEN-LAST:event_jTextField1_txtEntreCalles_ProveedorKeyPressed

    private void jTextField1_txtGoogleMaps_ProveedorKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextField1_txtGoogleMaps_ProveedorKeyPressed
    {//GEN-HEADEREND:event_jTextField1_txtGoogleMaps_ProveedorKeyPressed
        if(evt.getKeyCode()==evt.VK_ENTER|| evt.getKeyCode()==evt.VK_TAB)
        {
        jTextField1_txtCODPOSTAL_Proveedor.requestFocus();
        }
    }//GEN-LAST:event_jTextField1_txtGoogleMaps_ProveedorKeyPressed

    private void jTextField1_txtCODPOSTAL_ProveedorKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextField1_txtCODPOSTAL_ProveedorKeyPressed
    {//GEN-HEADEREND:event_jTextField1_txtCODPOSTAL_ProveedorKeyPressed
        if(evt.getKeyCode()==evt.VK_ENTER|| evt.getKeyCode()==evt.VK_TAB)
        {
        jTextField1_txtProvincia_Proveedor.requestFocus();
        }
    }//GEN-LAST:event_jTextField1_txtCODPOSTAL_ProveedorKeyPressed

    private void jTextField1_txtProvincia_ProveedorKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextField1_txtProvincia_ProveedorKeyPressed
    {//GEN-HEADEREND:event_jTextField1_txtProvincia_ProveedorKeyPressed
        if(evt.getKeyCode()==evt.VK_ENTER|| evt.getKeyCode()==evt.VK_TAB)
        {
        jTextField1_txtCiudad_Proveedor.requestFocus();
        }
    }//GEN-LAST:event_jTextField1_txtProvincia_ProveedorKeyPressed

    private void jTextField1_txtCiudad_ProveedorKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextField1_txtCiudad_ProveedorKeyPressed
    {//GEN-HEADEREND:event_jTextField1_txtCiudad_ProveedorKeyPressed
        if(evt.getKeyCode()==evt.VK_ENTER|| evt.getKeyCode()==evt.VK_TAB)
        {
        jTextField1_txtEmail_Proveedor.requestFocus();
        }
    }//GEN-LAST:event_jTextField1_txtCiudad_ProveedorKeyPressed

    private void jTextField1_txtEmail_ProveedorKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextField1_txtEmail_ProveedorKeyPressed
    {//GEN-HEADEREND:event_jTextField1_txtEmail_ProveedorKeyPressed
        if(evt.getKeyCode()==evt.VK_ENTER|| evt.getKeyCode()==evt.VK_TAB)
        {
        jTextArea1_txtAnotacionl_Proveedor.requestFocus();
        }
    }//GEN-LAST:event_jTextField1_txtEmail_ProveedorKeyPressed

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
        java.util.logging.Logger.getLogger(Ventana_Proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    catch (InstantiationException ex)
    {
        java.util.logging.Logger.getLogger(Ventana_Proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    catch (IllegalAccessException ex)
    {
        java.util.logging.Logger.getLogger(Ventana_Proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    catch (javax.swing.UnsupportedLookAndFeelException ex)
    {
        java.util.logging.Logger.getLogger(Ventana_Proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable()
    {
    public void run()
    {
        new Ventana_Proveedores().setVisible(true);
    }
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_AgregarRegistro;
    private javax.swing.JButton jButton_Cerrar;
    private javax.swing.JButton jButton_EliminarRegistro;
    private javax.swing.JButton jButton_ModificarRegistro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel2_Codigo_Proveedor;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable_Productos;
    private javax.swing.JTable jTable_TabladeRegistros;
    private javax.swing.JTextArea jTextArea1_txtAnotacionl_Proveedor;
    private javax.swing.JTextField jTextField1_txtCODPOSTAL_Proveedor;
    private javax.swing.JTextField jTextField1_txtCalle_Numero_Proveedor;
    private javax.swing.JTextField jTextField1_txtCalle_Proveedor;
    private javax.swing.JTextField jTextField1_txtCiudad_Proveedor;
    private javax.swing.JTextField jTextField1_txtEmail_Proveedor;
    private javax.swing.JTextField jTextField1_txtEntreCalles_Proveedor;
    private javax.swing.JTextField jTextField1_txtGoogleMaps_Proveedor;
    private javax.swing.JTextField jTextField1_txtNombreApellido_Proveedor;
    private javax.swing.JTextField jTextField1_txtPiso_Proveedor;
    private javax.swing.JTextField jTextField1_txtProvincia_Proveedor;
    private javax.swing.JTextField jTextField_Buscar;
    private javax.swing.JTextField jTextField_Empresa;
    private javax.swing.JTextField numero_celular;
    private javax.swing.JTextField numero_telefono_fijo;
    private javax.swing.JLabel visor_codigo_proveedor;
    // End of variables declaration//GEN-END:variables

    private void Estado_telefonos()
    {
    if("".equals(numero_celular.getText()))
    {
        numero_celular.setText("0");
    }
    if("".equals(numero_telefono_fijo.getText()))
    {
    numero_telefono_fijo.setText("0");
    }
    }
    
    private void EliminarRegistro()
    {
        cone2= conex.CargarDB_Base_datos_Proveedores();

        if(cone2!=null)
        {
            try
            {
                Statement orden = cone2.createStatement();
                int Comienza_desde_Aqui=Integer.parseInt(visor_codigo_proveedor.getText());
                
                System.out.println("Comienza_desde_Aqui:"+Comienza_desde_Aqui);     

                String Elminar = "DELETE From ListadeProveedores Where Codigo_Proveedor="+visor_codigo_proveedor.getText();

                orden.executeUpdate(Elminar);
                          
             cone2.close();
                JOptionPane.showMessageDialog(this, "Ubicación Elimina");
         //       ReAbrirVentanaCategorias();
         
   
                  jButton_EliminarRegistro.setVisible(false);
                  jButton_AgregarRegistro.setVisible(true);
                  jButton_ModificarRegistro.setVisible(false);
                 
                 PropiedadesTabla();
                 
               Codigo_Proveedor_borrado_categoria=Comienza_desde_Aqui;
                SeBorroRegistro="SI";

              
              visor_codigo_proveedor.setText(String.valueOf(Comienza_desde_Aqui));
                            
             orden.close();
             
//                 int Codigo_Proveedor_borrado=0;
//    
//            LimpiarCasilleros();
//                  jButton_EliminarRegistro.setVisible(false);
//                  jButton_AgregarRegistro.setVisible(true);
//                  jButton_ModificarRegistro.setVisible(false);
//    
//    jTextField_Empresa.requestFocus();
//    
//    Bandera_Modificando="No";
//    
//        PropiedadesTabla();
           ReAbrirVentana(); 
            }
            catch (Exception ex)
            {
                System.out.println("Error:" +ex);
            }
        }
    }

public int Codigo_Proveedor_incrementable()
{
    int Codigo_Proveedor=0;
    PreparedStatement ps =null;
    cone2= conex.CargarDB_Base_datos_Proveedores();
    try
    {
      Statement orden = cone2.createStatement();
      ResultSet r = orden.executeQuery("Select MAX(Codigo_Proveedor) From ListadeProveedores");
        while (r.next())
        {            
                    Codigo_Proveedor=r.getInt(1)+1;

        }
        System.out.println("Codigo_Proveedor Maximo:"+Codigo_Proveedor);
        
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
                        System.out.println("Se Borro un registro recien:"+SeBorroRegistro+"La Codigo Proveedor era:"+Codigo_Proveedor_borrado_categoria );
     Codigo_Proveedor=Comienza_desde_Aqui;   
    }
    
        return Codigo_Proveedor;
    }

    private void LimpiarCasilleros()
    {
     jTextField_Empresa.setText("");
     jTextField1_txtNombreApellido_Proveedor.setText("");
     jTextField1_txtCalle_Proveedor.setText("");
     jTextField1_txtCalle_Numero_Proveedor.setText("");
     numero_telefono_fijo.setText("");
     numero_celular.setText("");
     jTextField1_txtPiso_Proveedor.setText("");
     jTextField1_txtEntreCalles_Proveedor.setText("");
     jTextField1_txtGoogleMaps_Proveedor.setText("");
     jTextField1_txtCODPOSTAL_Proveedor.setText("");
     jTextField1_txtProvincia_Proveedor.setText("");
     jTextField1_txtCiudad_Proveedor.setText("");
     jTextField1_txtEmail_Proveedor.setText("");
     jTextArea1_txtAnotacionl_Proveedor.setText("");
     visor_codigo_proveedor.setText("");
    }
 }
