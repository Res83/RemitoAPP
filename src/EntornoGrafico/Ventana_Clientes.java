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
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Raul Eduardo Scalia
 * https://github.com/Res83
 */
public class Ventana_Clientes extends javax.swing.JFrame
{
// Cargo la relación con la base de datos //////////////////////////////////////////////////////

    Conexion conex = new Conexion();
    Connection cone2;
     Conexion Base_datos_Clientes = new Conexion();

/////////////////////////////////////////////////////////////////////////////////////////////////
     
   // int contador_de_filas=1;
    private String SeBorroRegistro;
    private String TextoTemporal;
    private int id_borrado_categoria;
    private Integer Comienza_desde_Aqui;
    private String Bandera_Modificando;
    private int seleccion;

public Ventana_Clientes()
{
    initComponents();
        setIconImage(new ImageIcon(getClass().getResource("../Imagenes/Logo.png")).getImage());
        
    
//Crea y carga Base de Datos /////////////////////////////////////////////////////////////

    conex.CrearDB_Base_datos_Clientes();
    cone2= conex.CargarDB_Base_datos_Clientes();
    
///////////////////////////////////////////////////////////////////////////////////////////    
    int id_borrado=0;
    
    
    jButton_EliminarRegistro.setVisible(false);
    
    jButton_ModificarRegistro.setVisible(false);
    
    jTextField1_txtNombreApellido_Cliente.requestFocus();
    
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
cone2= conex.CargarDB_Base_datos_Clientes();

     String columnas[]=
     {
         "cod",
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
int id=id_incrementable();
Visor_Cod.setText(String.valueOf(id));    
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
        
        ResultSet r = orden.executeQuery("Select* From ListadeClientes");
        
        while(r.next())
         {
         Object filas[] = 
         {
             r.getString("Codigo_Cliente"),
             r.getString("NombreyApellidoCliente"),
             r.getString("Calle_Cliente"),
             r.getString("Calle_Numero_Cliente"),
             r.getString("TelefonoFijo_Cliente"),
             r.getString("TelefonoMovil_Cliente"),
             r.getString("Piso_Cliente"),
             r.getString("EntreCalles_Cliente"),
             r.getString("GoogleMaps_Cliente"),
             r.getString("CODPOSTAL_Cliente"),
             r.getString("Provincia_Cliente"),
             r.getString("Ciudad_Cliente"),
             r.getString("Email_Cliente"),
             r.getString("Anotacionl_Cliente")
         };
// Le digo ahora que tome estas filas dentro de la tabla

dft.addRow(filas);

        }
        jTable_TabladeRegistros.setModel(dft);
        r.close();
    }
           
         }
         catch (SQLException ex)
         {
             Logger.getLogger(Ventana_Clientes.class.getName()).log(Level.SEVERE, null, ex);
         }
}    

private void FiltrarRegistro(String Establezco_Filtro)
{
    cone2= conex.CargarDB_Base_datos_Clientes();
    
    if(cone2!=null)
    {
        try
        {
            Statement orden = cone2.createStatement();
            String Filtro = "Select* From ListadeClientes where NombreyApellidoCliente LIKE '%"+jTextField1_txtNombreApellido_Cliente.getText().trim()+"%' ";
            ResultSet r=orden.executeQuery(Filtro);
/////////////
     String columnas[]=
     {
         "cod",
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
         Object filas[] = 
         {
             r.getString("Codigo_Cliente"),
             r.getString("NombreyApellidoCliente"),
             r.getString("Calle_Cliente"),
             r.getString("Calle_Numero_Cliente"),
             r.getString("TelefonoFijo_Cliente"),
             r.getString("TelefonoMovil_Cliente"),
             r.getString("Piso_Cliente"),
             r.getString("EntreCalles_Cliente"),
             r.getString("GoogleMaps_Cliente"),
             r.getString("CODPOSTAL_Cliente"),
             r.getString("Provincia_Cliente"),
             r.getString("Ciudad_Cliente"),
             r.getString("Email_Cliente"),
             r.getString("Anotacionl_Cliente")
         };
               dft.addRow(filas);
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
cone2= conex.CargarDB_Base_datos_Clientes();

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
            
String editar="Update ListadeClientes set "
                        + "NombreyApellidoCliente='"+jTextField1_txtNombreApellido_Cliente.getText()+"',"
                        + "Calle_Cliente='"+jTextField1_txtCalle_Cliente.getText()+"',"
                        + "Calle_Numero_Cliente="+jTextField1_txtCalle_Numero_Cliente.getText()+","
                        + "TelefonoFijo_Cliente="+jTextField1_txtTelefonoFijo_Cliente.getText()+","
                        + "TelefonoMovil_Cliente="+jTextField2_txtTelefonoMovil_Cliente.getText()+","
                        + "Piso_Cliente='"+jTextField1_txtPiso_Cliente.getText()+"',"
                        + "EntreCalles_Cliente='"+jTextField1_txtEntreCalles_Cliente.getText()+"',"
                        + "GoogleMaps_Cliente='"+jTextField1_txtGoogleMaps_Cliente.getText()+"',"
                        + "CODPOSTAL_Cliente='"+jTextField1_txtCODPOSTAL_Cliente.getText()+"',"
                        + "Provincia_Cliente='"+jTextField1_txtProvincia_Cliente.getText()+"',"
                        + "Ciudad_Cliente='"+jTextField1_txtCiudad_Cliente.getText()+"',"
                        + "Email_Cliente='"+jTextField1_txtEmail_Cliente.getText()+"',"
                        + "Anotacionl_Cliente='"+jTextArea1_txtAnotacionl_Cliente.getText()+"'"
                       + "Where Codigo_Cliente="+Visor_Cod.getText();
// Ejecuta ahora la Orden de arriba:
orden.executeUpdate(editar);
JOptionPane.showMessageDialog(this, "¡Modificada con Exito!");
        } 
        ReAbrirVentana(); 
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
    cone2= conex.CargarDB_Base_datos_Clientes();
// Cargo la base de datos mas arriba para tenerla disponible en todo.       
        if(jTextField1_txtNombreApellido_Cliente.getText().equals(""))
        {
        JOptionPane.showMessageDialog(this, "Debe escribir el nombre y apellido antes de agregar");
        }else 
        {
        if(cone2!=null)
  {
      try
      {
          try (Statement orden = cone2.createStatement())
          {
 String crear = "Insert Into ListadeClientes"
                        + "("
                        + "Codigo_Cliente,"
                        + "NombreyApellidoCliente,"
                        + "Calle_Cliente,"
                        + "Calle_Numero_Cliente,"
                        + "TelefonoFijo_Cliente,"
                        + "TelefonoMovil_Cliente,"
                        + "Piso_Cliente,"
                        + "EntreCalles_Cliente,"
                        + "GoogleMaps_Cliente,"
                        + "CODPOSTAL_Cliente,"
                        + "Provincia_Cliente,"
                        + "Ciudad_Cliente,"
                        + "Email_Cliente,"
                        + "Anotacionl_Cliente"
                        + ") Values("
                        + ""+Visor_Cod.getText()+","
                        + "'"+jTextField1_txtNombreApellido_Cliente.getText()+"',"
                        + "'"+jTextField1_txtCalle_Cliente.getText()+"',"
                        + ""+jTextField1_txtCalle_Numero_Cliente.getText()+","
                        + ""+jTextField1_txtTelefonoFijo_Cliente.getText()+","
                        + ""+jTextField2_txtTelefonoMovil_Cliente.getText()+","
                        + "'"+jTextField1_txtPiso_Cliente.getText()+"',"
                        + "'"+jTextField1_txtEntreCalles_Cliente.getText()+"',"
                        + "'"+jTextField1_txtGoogleMaps_Cliente.getText()+"',"
                        + "'"+jTextField1_txtCODPOSTAL_Cliente.getText()+"',"
                        + "'"+jTextField1_txtProvincia_Cliente.getText()+"',"
                        + "'"+jTextField1_txtCiudad_Cliente.getText()+"',"
                        + "'"+jTextField1_txtEmail_Cliente.getText()+"',"
                        + "'"+jTextArea1_txtAnotacionl_Cliente.getText()+"'"
                        + ")";
            

          
// Para ejecutar lo anterior se cree y actualice cada campo en la base de datos.
orden.executeUpdate(crear);
System.out.println("Registro Agregado OK");

//   IniciarContador(contador_de_filas);
PropiedadesTabla();
JOptionPane.showMessageDialog(this, "Se ha Agredado con exito: [ "+jTextField1_txtNombreApellido_Cliente.getText()+" ]");
jTextField1_txtNombreApellido_Cliente.setText("");
//r.close();
SeBorroRegistro="NO";
ReAbrirVentana();
          }
            
      }
      catch (SQLException ex)
      {
          System.out.println("Error:"+ex); 
    
      }
  } }    
    
}
    private void estado_telefonos()
    {
    if("".equals(jTextField2_txtTelefonoMovil_Cliente.getText()))
    {
        jTextField2_txtTelefonoMovil_Cliente.setText("0");
    }
    if("".equals(jTextField1_txtTelefonoFijo_Cliente.getText()))
    {
    jTextField1_txtTelefonoFijo_Cliente.setText("0");
    }
    }

public void ReAbrirVentana()
{
        dispose();
        Panel_Inicio VB = new Panel_Inicio();
        Ventana_Clientes ventanabierta = new Ventana_Clientes();
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
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton_AgregarRegistro = new javax.swing.JButton();
        jButton_ModificarRegistro = new javax.swing.JButton();
        jButton_EliminarRegistro = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_TabladeRegistros = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jTextField1_txtNombreApellido_Cliente = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField1_txtCalle_Cliente = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField1_txtCalle_Numero_Cliente = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextField1_txtTelefonoFijo_Cliente = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextField2_txtTelefonoMovil_Cliente = new javax.swing.JTextField();
        jTextField1_txtEmail_Cliente = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1_txtAnotacionl_Cliente = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jTextField1_txtPiso_Cliente = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextField1_txtEntreCalles_Cliente = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextField1_txtGoogleMaps_Cliente = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextField1_txtCODPOSTAL_Cliente = new javax.swing.JTextField();
        jTextField1_txtProvincia_Cliente = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jTextField1_txtCiudad_Cliente = new javax.swing.JTextField();
        jButton_Cerrar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2_Codigo_Cliente = new javax.swing.JLabel();
        Visor_Cod = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("RemitoAPP / Ubicaciones");
        setAlwaysOnTop(true);
        setResizable(false);

        jPanel1.setName(""); // NOI18N

        jButton_AgregarRegistro.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_AgregarRegistro.setText("+ Agregar");
        jButton_AgregarRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AgregarRegistroActionPerformed(evt);
            }
        });

        jButton_ModificarRegistro.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_ModificarRegistro.setText("* Modificar");
        jButton_ModificarRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ModificarRegistroActionPerformed(evt);
            }
        });

        jButton_EliminarRegistro.setBackground(new java.awt.Color(255, 0, 0));
        jButton_EliminarRegistro.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton_EliminarRegistro.setText("- Eliminar");
        jButton_EliminarRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EliminarRegistroActionPerformed(evt);
            }
        });

        jTable_TabladeRegistros.setAutoCreateRowSorter(true);
        jTable_TabladeRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable_TabladeRegistros.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jTable_TabladeRegistros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable_TabladeRegistros.setGridColor(new java.awt.Color(0, 0, 0));
        jTable_TabladeRegistros.getTableHeader().setReorderingAllowed(false);
        jTable_TabladeRegistros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_TabladeRegistrosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_TabladeRegistros);

        jLabel16.setText("Nombre y Apellido:");

        jTextField1_txtNombreApellido_Cliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1_txtNombreApellido_Cliente.setToolTipText("Campo Mínimo requerido (Requerido)");
        jTextField1_txtNombreApellido_Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1_txtNombreApellido_ClienteActionPerformed(evt);
            }
        });
        jTextField1_txtNombreApellido_Cliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1_txtNombreApellido_ClienteKeyReleased(evt);
            }
        });

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Calle:");

        jTextField1_txtCalle_Cliente.setToolTipText("Campo Mínimo requerido (Requerido)");
        jTextField1_txtCalle_Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1_txtCalle_ClienteActionPerformed(evt);
            }
        });

        jLabel18.setText("Nº:");

        jTextField1_txtCalle_Numero_Cliente.setToolTipText("Campo Mínimo requerido (Requerido)");

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Teléfono Fijo:");

        jTextField1_txtTelefonoFijo_Cliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1_txtTelefonoFijo_Cliente.setToolTipText("Campo Mínimo requerido (Requerido)");
        jTextField1_txtTelefonoFijo_Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1_txtTelefonoFijo_ClienteActionPerformed(evt);
            }
        });

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Celular:");

        jTextField2_txtTelefonoMovil_Cliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2_txtTelefonoMovil_Cliente.setToolTipText("Campo Mínimo requerido (Requerido)");
        jTextField2_txtTelefonoMovil_Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2_txtTelefonoMovil_ClienteActionPerformed(evt);
            }
        });

        jTextField1_txtEmail_Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1_txtEmail_ClienteActionPerformed(evt);
            }
        });

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("E-mail:");

        jLabel28.setText("Anotación (Un bloque de texto Libre)");

        jTextArea1_txtAnotacionl_Cliente.setColumns(20);
        jTextArea1_txtAnotacionl_Cliente.setRows(5);
        jScrollPane4.setViewportView(jTextArea1_txtAnotacionl_Cliente);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1_txtEmail_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jTextField1_txtCalle_Numero_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jTextField1_txtTelefonoFijo_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextField2_txtTelefonoMovil_Cliente)))
                            .addComponent(jTextField1_txtNombreApellido_Cliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1_txtCalle_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 182, Short.MAX_VALUE)
                        .addComponent(jLabel28)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel16))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jTextField1_txtNombreApellido_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jTextField1_txtCalle_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)
                            .addComponent(jTextField1_txtCalle_Numero_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(jTextField1_txtTelefonoFijo_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jTextField2_txtTelefonoMovil_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1_txtEmail_Cliente)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(39, 39, 39))
        );

        jTabbedPane1.addTab("Datos Basicos", jPanel2);

        jLabel21.setText("Piso / Depto :");

        jTextField1_txtPiso_Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1_txtPiso_ClienteActionPerformed(evt);
            }
        });

        jLabel22.setText("Entre Calles :");

        jLabel23.setText("GoogleMaps :");

        jLabel24.setText("Código Postal :");

        jLabel25.setText("Provincia :");

        jLabel26.setText("Ciudad :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jLabel26)
                    .addComponent(jLabel23)
                    .addComponent(jLabel22)
                    .addComponent(jLabel21))
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1_txtPiso_Cliente)
                    .addComponent(jTextField1_txtEntreCalles_Cliente)
                    .addComponent(jTextField1_txtGoogleMaps_Cliente)
                    .addComponent(jTextField1_txtCiudad_Cliente)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField1_txtCODPOSTAL_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1_txtProvincia_Cliente, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)))
                .addContainerGap(306, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jTextField1_txtPiso_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jTextField1_txtEntreCalles_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jTextField1_txtGoogleMaps_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jTextField1_txtCODPOSTAL_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(jTextField1_txtProvincia_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jTextField1_txtCiudad_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Datos Opcionales", jPanel3);

        jButton_Cerrar.setText("Cerrar");
        jButton_Cerrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_Cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton_Cerrar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_EliminarRegistro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_ModificarRegistro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_AgregarRegistro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_AgregarRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_ModificarRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jButton_EliminarRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_Cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Clientes");

        jLabel2_Codigo_Cliente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2_Codigo_Cliente.setText("Código:");

        Visor_Cod.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Visor_Cod.setText("000");
        Visor_Cod.setToolTipText("");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2_Codigo_Cliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Visor_Cod, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2_Codigo_Cliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Visor_Cod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2_txtTelefonoMovil_ClienteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextField2_txtTelefonoMovil_ClienteActionPerformed
    {//GEN-HEADEREND:event_jTextField2_txtTelefonoMovil_ClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2_txtTelefonoMovil_ClienteActionPerformed

    private void jTextField1_txtNombreApellido_ClienteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextField1_txtNombreApellido_ClienteActionPerformed
    {//GEN-HEADEREND:event_jTextField1_txtNombreApellido_ClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1_txtNombreApellido_ClienteActionPerformed

    private void jTextField1_txtEmail_ClienteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextField1_txtEmail_ClienteActionPerformed
    {//GEN-HEADEREND:event_jTextField1_txtEmail_ClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1_txtEmail_ClienteActionPerformed

    private void jTextField1_txtPiso_ClienteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextField1_txtPiso_ClienteActionPerformed
    {//GEN-HEADEREND:event_jTextField1_txtPiso_ClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1_txtPiso_ClienteActionPerformed

    private void jTable_TabladeRegistrosMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTable_TabladeRegistrosMouseClicked
    {//GEN-HEADEREND:event_jTable_TabladeRegistrosMouseClicked
        Bandera_Modificando="Si";

        jButton_AgregarRegistro.setVisible(false);
        jButton_ModificarRegistro.setVisible(true);
        jButton_EliminarRegistro.setVisible(true);

        // evento Mouse Clicked en la Tabla:
        Visor_Cod.setText("");
        jTextField1_txtNombreApellido_Cliente.setText("");
        jTextField1_txtCalle_Cliente.setText("");
        jTextField1_txtCalle_Numero_Cliente.setText("");
        jTextField1_txtTelefonoFijo_Cliente.setText("");
        jTextField2_txtTelefonoMovil_Cliente.setText("");
        jTextField1_txtPiso_Cliente.setText("");
        jTextField1_txtEntreCalles_Cliente.setText("");
        jTextField1_txtGoogleMaps_Cliente.setText("");
        jTextField1_txtCODPOSTAL_Cliente.setText("");
        jTextField1_txtProvincia_Cliente.setText("");
        jTextField1_txtCiudad_Cliente.setText("");
        jTextField1_txtEmail_Cliente.setText("");
        jTextArea1_txtAnotacionl_Cliente.setText("");

        int seleccion=jTable_TabladeRegistros.rowAtPoint(evt.getPoint());

        Visor_Cod.setText(String.valueOf(jTable_TabladeRegistros.getValueAt(seleccion,0)));
        jTextField1_txtNombreApellido_Cliente.setText(String.valueOf(jTable_TabladeRegistros.getValueAt(seleccion,1)));
        jTextField1_txtCalle_Cliente.setText(String.valueOf(jTable_TabladeRegistros.getValueAt(seleccion,2)));
        jTextField1_txtCalle_Numero_Cliente.setText(String.valueOf(jTable_TabladeRegistros.getValueAt(seleccion,3)));
        jTextField1_txtTelefonoFijo_Cliente.setText(String.valueOf(jTable_TabladeRegistros.getValueAt(seleccion,4)));
        jTextField2_txtTelefonoMovil_Cliente.setText(String.valueOf(jTable_TabladeRegistros.getValueAt(seleccion,5)));
        jTextField1_txtPiso_Cliente.setText(String.valueOf(jTable_TabladeRegistros.getValueAt(seleccion,6)));
        jTextField1_txtEntreCalles_Cliente.setText(String.valueOf(jTable_TabladeRegistros.getValueAt(seleccion,7)));
        jTextField1_txtGoogleMaps_Cliente.setText(String.valueOf(jTable_TabladeRegistros.getValueAt(seleccion,8)));
        jTextField1_txtCODPOSTAL_Cliente.setText(String.valueOf(jTable_TabladeRegistros.getValueAt(seleccion,9)));
        jTextField1_txtProvincia_Cliente.setText(String.valueOf(jTable_TabladeRegistros.getValueAt(seleccion,10)));
        jTextField1_txtCiudad_Cliente.setText(String.valueOf(jTable_TabladeRegistros.getValueAt(seleccion,11)));
        jTextField1_txtEmail_Cliente.setText(String.valueOf(jTable_TabladeRegistros.getValueAt(seleccion,12)));
        jTextArea1_txtAnotacionl_Cliente.setText(String.valueOf(jTable_TabladeRegistros.getValueAt(seleccion,13)));


    }//GEN-LAST:event_jTable_TabladeRegistrosMouseClicked

    private void jButton_CerrarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_CerrarActionPerformed
    {//GEN-HEADEREND:event_jButton_CerrarActionPerformed
        //  Java: dispose() es usado para cerrar un jframe
        if(jTextField1_txtNombreApellido_Cliente.getText().equals(""))
        {
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

    private void jButton_EliminarRegistroActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_EliminarRegistroActionPerformed
    {//GEN-HEADEREND:event_jButton_EliminarRegistroActionPerformed
        // Eliminar
        if(jTextField1_txtNombreApellido_Cliente.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Debe Selecionar de la tabla y luego pulsar Eliminar");
        }else
        {
            // Se crea un metodo EditarRegistro() para que las instruciones las podamos usar por cualquier otra parte.

            String opcion[]={"Eliminar", "Cancelar"};
            int eleccion =JOptionPane.showOptionDialog(this, "¿Esta seguro de Eliminar ("+jTextField1_txtNombreApellido_Cliente.getText()+") ?", "Eliminar", 0, 0, null, opcion, NORMAL);

            if(eleccion==JOptionPane.YES_OPTION)
            {
                EliminarRegistro();
            }else if (eleccion==JOptionPane.NO_OPTION)
            {

            }

        }
    }//GEN-LAST:event_jButton_EliminarRegistroActionPerformed

    private void jButton_ModificarRegistroActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_ModificarRegistroActionPerformed
    {//GEN-HEADEREND:event_jButton_ModificarRegistroActionPerformed
        // Modificar
        EditarRegistro();
    }//GEN-LAST:event_jButton_ModificarRegistroActionPerformed

    private void jButton_AgregarRegistroActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_AgregarRegistroActionPerformed
    {//GEN-HEADEREND:event_jButton_AgregarRegistroActionPerformed
        estado_telefonos();
        AgregarRegistro();
    }//GEN-LAST:event_jButton_AgregarRegistroActionPerformed

    private void jTextField1_txtTelefonoFijo_ClienteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextField1_txtTelefonoFijo_ClienteActionPerformed
    {//GEN-HEADEREND:event_jTextField1_txtTelefonoFijo_ClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1_txtTelefonoFijo_ClienteActionPerformed

    private void jTextField1_txtCalle_ClienteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextField1_txtCalle_ClienteActionPerformed
    {//GEN-HEADEREND:event_jTextField1_txtCalle_ClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1_txtCalle_ClienteActionPerformed

    private void jTextField1_txtNombreApellido_ClienteKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextField1_txtNombreApellido_ClienteKeyReleased
    {//GEN-HEADEREND:event_jTextField1_txtNombreApellido_ClienteKeyReleased
    FiltrarRegistro(jTextField1_txtNombreApellido_Cliente.getText().trim());
    }//GEN-LAST:event_jTextField1_txtNombreApellido_ClienteKeyReleased

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
        java.util.logging.Logger.getLogger(Ventana_Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    catch (InstantiationException ex)
    {
        java.util.logging.Logger.getLogger(Ventana_Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    catch (IllegalAccessException ex)
    {
        java.util.logging.Logger.getLogger(Ventana_Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    catch (javax.swing.UnsupportedLookAndFeelException ex)
    {
        java.util.logging.Logger.getLogger(Ventana_Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
        new Ventana_Clientes().setVisible(true);
    }
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Visor_Cod;
    private javax.swing.JButton jButton_AgregarRegistro;
    private javax.swing.JButton jButton_Cerrar;
    private javax.swing.JButton jButton_EliminarRegistro;
    private javax.swing.JButton jButton_ModificarRegistro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel2_Codigo_Cliente;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable_TabladeRegistros;
    private javax.swing.JTextArea jTextArea1_txtAnotacionl_Cliente;
    private javax.swing.JTextField jTextField1_txtCODPOSTAL_Cliente;
    private javax.swing.JTextField jTextField1_txtCalle_Cliente;
    private javax.swing.JTextField jTextField1_txtCalle_Numero_Cliente;
    private javax.swing.JTextField jTextField1_txtCiudad_Cliente;
    private javax.swing.JTextField jTextField1_txtEmail_Cliente;
    private javax.swing.JTextField jTextField1_txtEntreCalles_Cliente;
    private javax.swing.JTextField jTextField1_txtGoogleMaps_Cliente;
    private javax.swing.JTextField jTextField1_txtNombreApellido_Cliente;
    private javax.swing.JTextField jTextField1_txtPiso_Cliente;
    private javax.swing.JTextField jTextField1_txtProvincia_Cliente;
    private javax.swing.JTextField jTextField1_txtTelefonoFijo_Cliente;
    private javax.swing.JTextField jTextField2_txtTelefonoMovil_Cliente;
    // End of variables declaration//GEN-END:variables

    private void EliminarRegistro()
    {
        cone2= conex.CargarDB_Base_datos_Clientes();

        if(cone2!=null)
        {
            try
            {
                Statement orden = cone2.createStatement();
                int Comienza_desde_Aqui=Integer.parseInt(Visor_Cod.getText());
                System.out.println("Comienza_desde_Aqui:"+Comienza_desde_Aqui);     

                String Elminar = "DELETE From ListadeClientes Where Codigo_Cliente="+Visor_Cod.getText();

                orden.executeUpdate(Elminar);
                          
             cone2.close();
                JOptionPane.showMessageDialog(this, "Cliente Eliminado");
         //       ReAbrirVentanaCategorias();
         
                 jTextField1_txtNombreApellido_Cliente.setText("(Escribe el Nuevo Cliente)");

                  jButton_EliminarRegistro.setVisible(false);
                  jButton_AgregarRegistro.setVisible(true);
                  jButton_ModificarRegistro.setVisible(false);
                 
                 PropiedadesTabla();
                 
                id_borrado_categoria=Comienza_desde_Aqui;
                SeBorroRegistro="SI";

              
              Visor_Cod.setText(String.valueOf(Comienza_desde_Aqui));
                            
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
    cone2= conex.CargarDB_Base_datos_Clientes();
    try
    {
      Statement orden = cone2.createStatement();
      ResultSet r = orden.executeQuery("Select MAX(Codigo_Cliente) From ListadeClientes");
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
        catch (Exception ex)
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
