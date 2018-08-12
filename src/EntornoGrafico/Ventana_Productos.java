/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntornoGrafico;

import CodigoFuente.Conexion;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Ventana_Productos extends javax.swing.JFrame
{
 Connection cone2;
 Conexion conex = new Conexion();
 Conexion conexion_BaseDatosCategorias = new Conexion();
 Conexion conexion_BaseDatosUbicaciones = new Conexion();
 Conexion Base_datos_BaseDatosMarcas =new Conexion();
  Conexion conexion_BaseDatosProveedores = new Conexion();
 
 Conexion conexion_Base_datos_Productos = new Conexion();
 

    // int contador_de_filas=1;
    private String SeBorroRegistro;
    private String TextoTemporal;
    private int Codigo_Producto_borrado_categoria;
    private Integer Comienza_desde_Aqui;
    private String Bandera_Modificando;
    private int seleccion;
    

    


public Ventana_Productos()
{

    initComponents();
    
        setIconImage(new ImageIcon(getClass().getResource("../Imagenes/Logo.png")).getImage());
        
    
    conex.CrearDB_Base_datos_Productos();
    cone2= conex.CargarDB_Base_datos_Productos();


        CBoxCategorias();
        CBoxUbicacion();
        CBoxMarcas();
        
        CBoxProveedores();

        
        jTextField_Descripcion_Producto.requestFocus();
// Esconde el Boton de Modificar        
        jButton_Modificar.setVisible(false);
// Esconde el Boton de Eliminar   
        jButton_Eliminar.setVisible(false);
        
  if(cone2!=null)
    {
    PropiedadesTabla();

    }
}

private void PropiedadesTabla()
{
cone2= conex.CargarDB_Base_datos_Productos();
//String columnas[] = {"Codigo_Producto","Categoria_Producto","Descripcion_Producto","Unidades_Producto","Cajas_Producto","UnidadesxCaja_Producto","Atados_Producto","Un_Atado_Tiene_Producto","CostoxUnidad_Producto","CostoxCaja__Producto","CostoxAtado","Ubicacion_Producto","Codigo_Provedor","NombreyApellido__Provedor","Marcas","Anotacionl_Provedor","Foto","QR"};
String columnas[] = {
    "Codigo_Producto",
    "Categoria_Producto",
    "Descripcion_Producto",    
    "Cajas_Producto",
    "UnidadesxCaja_Producto",
    "Unidades_Producto",
    "Atados_Producto",
    "Un_Atado_Tiene_Producto",
    "CostoxUnidad_Producto",
    "CostoxCaja__Producto",
    "CostoxAtado",
    "Ubicacion_Producto",
    "Codigo_Provedor",
    "NombreyApellido__Provedor",
    "Anotacionl_Provedor"     
    };

if(SeBorroRegistro!="SI"){
int Codigo_Producto=Codigo_Producto_incrementable();
jTextField_int_Codigo.setText(String.valueOf(Codigo_Producto));    
}
// Primero toma las filas pero no las tengo y pongo null
// Constructor de la Tabla
DefaultTableModel dft = new DefaultTableModel(null,columnas);

         try
         {
             Statement orden = cone2.createStatement();

//Monstrar Algo de una Base de Datos:

            ResultSet r = orden.executeQuery("Select* From Listadeproductos");
            
             while (r.next())
             { 
               Object Filas[]={
                   r.getString("Codigo_Producto"),
                   r.getString("Categoria_Producto"),
                   r.getString("Descripcion_Producto"),
                   r.getString("Cajas_Producto"),
                   r.getString("UnidadesxCaja_Producto"),
                   r.getString("Unidades_Producto"),
                   r.getString("Atados_Producto"),
                   r.getString("Un_Atado_Tiene_Producto"),
                   r.getString("CostoxUnidad_Producto"),
                   r.getString("CostoxCaja__Producto"),
                   r.getString("CostoxAtado"),
                   r.getString("Ubicacion_Producto"),
                   r.getString("Codigo_Provedor"),
                   r.getString("NombreyApellido__Provedor"),
                   r.getString("Anotacionl_Provedor"),               
               };


// Le digo ahora que tome estas filas dentro de la tabla

            dft.addRow(Filas);

             }
             jTable.setModel(dft);
                          r.close();
                        orden.close();
           
         }
         catch (SQLException ex)
         {
             Logger.getLogger(Ventana_Ubicaciones.class.getName()).log(Level.SEVERE, null, ex);
         }
}    

public int Codigo_Producto_incrementable()
{
    int Codigo_Producto=1;
    PreparedStatement ps =null;
    cone2= conex.CargarDB_Base_datos_Productos();
    try
    {
      Statement orden = cone2.createStatement();
      ResultSet r = orden.executeQuery("Select MAX(Codigo_Producto) From Listadeproductos");
        while (r.next())
        {            
                    Codigo_Producto=r.getInt(1)+1;

        }
        System.out.println("Codigo_Producto Maximo:"+Codigo_Producto);
        
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
                      
     Codigo_Producto=Comienza_desde_Aqui;   
    }
    
        return Codigo_Producto;
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

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jTextField_Costo_xCaja = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jTextField_Costo_xAtado = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTextField_Costo_Unidad = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jTextField_int_Cantidad_de_cajas = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextField_int_caja_se_forma_xunidades = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jTextField_int_caja_se_forma_xAtados = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jTextField_int_Cantidad_de_unidades = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField_int_Cantidad_de_Atados = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField_int_UnAtados_se_forma_xunidades = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField_int_Codigo = new javax.swing.JTextField();
        jLabel2_Codigo_Cliente = new javax.swing.JLabel();
        jComboBox_Lista_de_Ubicacion = new javax.swing.JComboBox<>();
        jTextField_Descripcion_Producto = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel_Caja_de_Foto_Producto = new javax.swing.JLabel();
        jLabel_QR_Producto = new javax.swing.JLabel();
        jComboBox_Lista_de_Categorias = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jTextField_Buscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton_Actualizar_Categorias = new javax.swing.JButton();
        jButton_Actualizar_Categorias1 = new javax.swing.JButton();
        jComboBox_Marcas = new javax.swing.JComboBox<>();
        jButton_Actualizar_Categorias4 = new javax.swing.JButton();
        jButton_AgregarMarcas1 = new javax.swing.JButton();
        jComboBox_CodProveedores = new javax.swing.JComboBox<>();
        jButton_Actualizar_Categorias2 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane_NombreyApellidoProveedor = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_txtAnotacion_Producto = new javax.swing.JTextArea();
        jButton_GuardarNuevo = new javax.swing.JButton();
        jButton_Modificar = new javax.swing.JButton();
        jButton_Eliminar = new javax.swing.JButton();
        jButton_CerrarVentanAbierta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("RemitoAPP > Productos");
        setAlwaysOnTop(true);
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Productos");

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel30.setText("xCajas:");

        jTextField_Costo_xCaja.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField_Costo_xCaja.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_Costo_xCaja.setToolTipText("Campo Mínimo requerido (Requerido)");

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel38.setText("xAtado:");

        jTextField_Costo_xAtado.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField_Costo_xAtado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_Costo_xAtado.setToolTipText("Campo Mínimo requerido (Requerido)");
        jTextField_Costo_xAtado.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jTextField_Costo_xAtadoActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel29.setText("x Unidad:");

        jTextField_Costo_Unidad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField_Costo_Unidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_Costo_Unidad.setToolTipText("Campo Mínimo requerido (Requerido)");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField_Costo_xCaja, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_Costo_Unidad, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_Costo_xAtado, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_Costo_Unidad)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_Costo_xCaja)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_Costo_xAtado)
                    .addComponent(jLabel38))
                .addGap(6, 6, 6))
        );

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("> Costo <");

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel4.setAutoscrolls(true);

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("Cajas:");

        jTextField_int_Cantidad_de_cajas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField_int_Cantidad_de_cajas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_int_Cantidad_de_cajas.setToolTipText("Campo Mínimo requerido (Requerido)");

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("Una Caja tiene:");

        jTextField_int_caja_se_forma_xunidades.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField_int_caja_se_forma_xunidades.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_int_caja_se_forma_xunidades.setToolTipText("Campo Mínimo requerido (Requerido)");

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Unidades");

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("Una Caja tiene:");

        jTextField_int_caja_se_forma_xAtados.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_int_caja_se_forma_xAtados.setToolTipText("Campo Mínimo requerido (Requerido)");
        jTextField_int_caja_se_forma_xAtados.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jTextField_int_caja_se_forma_xAtadosActionPerformed(evt);
            }
        });

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Atados");

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText("Unidades:");

        jTextField_int_Cantidad_de_unidades.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField_int_Cantidad_de_unidades.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_int_Cantidad_de_unidades.setToolTipText("Campo Mínimo requerido (Requerido)");
        jTextField_int_Cantidad_de_unidades.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jTextField_int_Cantidad_de_unidadesActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Atados:");

        jTextField_int_Cantidad_de_Atados.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField_int_Cantidad_de_Atados.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_int_Cantidad_de_Atados.setToolTipText("Campo Mínimo requerido (Requerido)");
        jTextField_int_Cantidad_de_Atados.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jTextField_int_Cantidad_de_AtadosActionPerformed(evt);
            }
        });

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Un Atados tiene:");

        jTextField_int_UnAtados_se_forma_xunidades.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_int_UnAtados_se_forma_xunidades.setToolTipText("Campo Mínimo requerido (Requerido)");

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Unidades.");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_int_Cantidad_de_Atados))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_int_caja_se_forma_xAtados))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_int_UnAtados_se_forma_xunidades, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField_int_Cantidad_de_cajas, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField_int_caja_se_forma_xunidades, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField_int_Cantidad_de_unidades, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_int_Cantidad_de_unidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20)
                    .addComponent(jTextField_int_Cantidad_de_cajas, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jTextField_int_caja_se_forma_xunidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_int_Cantidad_de_Atados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField_int_UnAtados_se_forma_xunidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jTextField_int_caja_se_forma_xAtados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel15.setBackground(new java.awt.Color(204, 255, 204));
        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("> Cantidades <");

        jTextField_int_Codigo.setEditable(false);
        jTextField_int_Codigo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_int_Codigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_int_Codigo.setFocusable(false);
        jTextField_int_Codigo.setOpaque(false);

        jLabel2_Codigo_Cliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2_Codigo_Cliente.setText("Código:");

        jComboBox_Lista_de_Ubicacion.setToolTipText("Ubicaciones");
        jComboBox_Lista_de_Ubicacion.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jComboBox_Lista_de_UbicacionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                jComboBox_Lista_de_UbicacionMouseEntered(evt);
            }
        });
        jComboBox_Lista_de_Ubicacion.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jComboBox_Lista_de_UbicacionActionPerformed(evt);
            }
        });

        jTextField_Descripcion_Producto.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField_Descripcion_Producto.setToolTipText("Campo Mínimo requerido (Requerido)");
        jTextField_Descripcion_Producto.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jTextField_Descripcion_ProductoActionPerformed(evt);
            }
        });

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton3.setText("Cargar Foto");
        jButton3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Eliminar");
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setPreferredSize(new java.awt.Dimension(90, 23));
        jButton4.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Generar QR");
        jButton5.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Eliminar");

        jLabel_Caja_de_Foto_Producto.setBackground(new java.awt.Color(0, 0, 0));
        jLabel_Caja_de_Foto_Producto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_QR_Producto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_Caja_de_Foto_Producto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jLabel_Caja_de_Foto_Producto, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addGap(1, 1, 1)
                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_QR_Producto, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addGap(12, 12, 12))
        );

        jComboBox_Lista_de_Categorias.setToolTipText("Catagorias");
        jComboBox_Lista_de_Categorias.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jComboBox_Lista_de_CategoriasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                jComboBox_Lista_de_CategoriasMouseEntered(evt);
            }
        });
        jComboBox_Lista_de_Categorias.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jComboBox_Lista_de_CategoriasActionPerformed(evt);
            }
        });

        jButton1.setText("+");
        jButton1.setToolTipText("Abrir Categorias");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("+");
        jButton2.setToolTipText("Abrir Ubicaciones");
        jButton2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton2ActionPerformed(evt);
            }
        });

        jTable.setAutoCreateRowSorter(true);
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {

            }
        ));
        jTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane2.setViewportView(jTable);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Buscar:");

        jButton_Actualizar_Categorias.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton_Actualizar_Categorias.setText("Actualizar");
        jButton_Actualizar_Categorias.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jButton_Actualizar_CategoriasMouseClicked(evt);
            }
        });
        jButton_Actualizar_Categorias.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_Actualizar_CategoriasActionPerformed(evt);
            }
        });

        jButton_Actualizar_Categorias1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton_Actualizar_Categorias1.setText("Actualizar");
        jButton_Actualizar_Categorias1.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jButton_Actualizar_Categorias1MouseClicked(evt);
            }
        });
        jButton_Actualizar_Categorias1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_Actualizar_Categorias1ActionPerformed(evt);
            }
        });

        jComboBox_Marcas.setToolTipText("Marcas");
        jComboBox_Marcas.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jComboBox_MarcasMouseClicked(evt);
            }
        });

        jButton_Actualizar_Categorias4.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton_Actualizar_Categorias4.setText("Actualizar");
        jButton_Actualizar_Categorias4.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jButton_Actualizar_Categorias4MouseClicked(evt);
            }
        });
        jButton_Actualizar_Categorias4.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_Actualizar_Categorias4ActionPerformed(evt);
            }
        });

        jButton_AgregarMarcas1.setText("+");
        jButton_AgregarMarcas1.setToolTipText("Abrir Marcas");
        jButton_AgregarMarcas1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_AgregarMarcas1ActionPerformed(evt);
            }
        });

        jComboBox_CodProveedores.setToolTipText("Código de  Provedores");
        jComboBox_CodProveedores.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jComboBox_CodProveedoresMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                jComboBox_CodProveedoresMouseEntered(evt);
            }
        });
        jComboBox_CodProveedores.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jComboBox_CodProveedoresActionPerformed(evt);
            }
        });

        jButton_Actualizar_Categorias2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton_Actualizar_Categorias2.setText("Actualizar");
        jButton_Actualizar_Categorias2.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jButton_Actualizar_Categorias2MouseClicked(evt);
            }
        });
        jButton_Actualizar_Categorias2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_Actualizar_Categorias2ActionPerformed(evt);
            }
        });

        jButton7.setText("+");
        jButton7.setToolTipText("Abrir Proveedores");
        jButton7.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton7ActionPerformed(evt);
            }
        });

        jTextPane_NombreyApellidoProveedor.setEditable(false);
        jTextPane_NombreyApellidoProveedor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextPane_NombreyApellidoProveedor.setFocusCycleRoot(false);
        jTextPane_NombreyApellidoProveedor.setFocusable(false);
        jTextPane_NombreyApellidoProveedor.setOpaque(false);
        jScrollPane3.setViewportView(jTextPane_NombreyApellidoProveedor);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jTextField_Descripcion_Producto, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(jLabel2_Codigo_Cliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField_int_Codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addComponent(jComboBox_Lista_de_Categorias, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton_Actualizar_Categorias)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox_Lista_de_Ubicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_Actualizar_Categorias1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jComboBox_Marcas, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_Actualizar_Categorias4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_AgregarMarcas1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox_CodProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_Actualizar_Categorias2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7))
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel13Layout.createSequentialGroup()
                                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel13Layout.createSequentialGroup()
                                            .addGap(28, 28, 28)
                                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(37, 37, 37)
                                            .addComponent(jTextField_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 17, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2_Codigo_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_int_Codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextField_Descripcion_Producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox_Lista_de_Categorias, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Actualizar_Categorias, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_Lista_de_Ubicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Actualizar_Categorias1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBox_Marcas, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton_AgregarMarcas1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton_Actualizar_Categorias4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton_Actualizar_Categorias2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jComboBox_CodProveedores, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(14, 14, 14)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 477, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Datos Basicos", jPanel1);

        jLabel11.setText("Anotación (Un bloque de texto Libre):");

        jTextArea_txtAnotacion_Producto.setColumns(20);
        jTextArea_txtAnotacion_Producto.setRows(5);
        jScrollPane1.setViewportView(jTextArea_txtAnotacion_Producto);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Anotaciones", jPanel2);

        jButton_GuardarNuevo.setText("Guardar Nuevo Producto");
        jButton_GuardarNuevo.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_GuardarNuevoActionPerformed(evt);
            }
        });

        jButton_Modificar.setText("Modificar");

        jButton_Eliminar.setText("Eliminar");

        jButton_CerrarVentanAbierta.setText("Cerrar");
        jButton_CerrarVentanAbierta.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_CerrarVentanAbiertaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton_GuardarNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_CerrarVentanAbierta, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 817, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_GuardarNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_CerrarVentanAbierta, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_GuardarNuevoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_GuardarNuevoActionPerformed
    {//GEN-HEADEREND:event_jButton_GuardarNuevoActionPerformed

            //agregar clientes a la tabla
           conexion_Base_datos_Productos.CrearDB_Base_datos_Productos();
            cone2 = conex.CargarDB_Base_datos_Productos();
            if (cone2!=null)

            {
                try

                {
                    Statement orden = cone2.createStatement();
                    String crear;
                    crear = "Insert Into ListadeProductos"
                            + "("
                            + "int_Codigo_Producto,"
                            + "Descripcion_Producto,"
                            + "jCBox_Lista_de_Categorias,"
                            + "jCBox_Lista_de_Ubicacion,"
                            + "int_Cantidad_de_unidades,"
                            + "int_Cantidad_de_cajas,"
                            + "int_caja_se_forma_xunidades,"
                            + "int_caja_se_forma_xAtados,"
                            + "int_Cantidad_de_Atados,"
                            + "int_UnAtados_se_forma_xunidades,"
                            + "Costo_Unidad,"
                            + "Costo_xCaja,"
                            + "Costo_xAtado,"
                            + "cod_Provedor,"
                            + "NombreyApellidoProvedor,"
                            + "jCBOX_Marcas,"
                            + "txtAnotacion_Producto"
                            + ") Values("
                            + ""+jTextField_int_Codigo.getText()+","
                            + "'"+jTextField_Descripcion_Producto.getText()+"',"
                            + ""+jComboBox_Lista_de_Categorias.getSelectedItem().toString()+","
                            + ""+jComboBox_Lista_de_Ubicacion.getSelectedItem().toString()+","
                            + ""+jTextField_int_Cantidad_de_unidades.getText()+","
                            + ""+jTextField_int_Cantidad_de_cajas.getText()+","
                            + ""+jTextField_int_caja_se_forma_xunidades.getText()+","
                            + ""+jTextField_int_caja_se_forma_xAtados.getText()+","
                            + ""+jTextField_int_Cantidad_de_Atados.getText()+","
                            + ""+jTextField_int_UnAtados_se_forma_xunidades.getText()+","
                            + ""+jTextField_Costo_Unidad.getText()+","
                            + ""+jTextField_Costo_xCaja.getText()+","
                            + ""+jTextField_Costo_xAtado.getText()+","
                            + ""+jComboBox_CodProveedores.getSelectedItem().toString()+","
                            + "'"+jTextPane_NombreyApellidoProveedor.getText()+"',"
                            + ""+jComboBox_Marcas.getSelectedItem().toString()+","
                            + "'"+jTextArea_txtAnotacion_Producto.getText()+"'"
                            + ")";

                    orden.executeUpdate(crear);
                    System.out.println("Registro creado");

                    JOptionPane.showMessageDialog(this, "Se ha agregado correctamente ("+jTextField_Descripcion_Producto.getText()+")");
                }
                catch (SQLException ex)
                {
                    System.out.println("Error WILSONG:"+ex);
                    JOptionPane.showMessageDialog(this, "Error: Numero de Producto Repetido ("+jTextField_int_Codigo.getText()+") Cambie el numero");

                }}


        }

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
            catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex)
            {
                java.util.logging.Logger.getLogger(Ventana_Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>
            //</editor-fold>
            //</editor-fold>
            //</editor-fold>
            //</editor-fold>
            //</editor-fold>
            //</editor-fold>
            //</editor-fold>
            //</editor-fold>
            //</editor-fold>
            //</editor-fold>
            //</editor-fold>
            //</editor-fold>
            //</editor-fold>
            //</editor-fold>
            //</editor-fold>

            //</editor-fold>

            /* Create and display the form */
            java.awt.EventQueue.invokeLater(() ->
            {
                new Ventana_Productos().setVisible(true);
            });

    }//GEN-LAST:event_jButton_GuardarNuevoActionPerformed

    private void jButton_CerrarVentanAbiertaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_CerrarVentanAbiertaActionPerformed
    {//GEN-HEADEREND:event_jButton_CerrarVentanAbiertaActionPerformed
        // Clic Boton Cerrar

        //  Java: dispose() es usado para cerrar un jframe
        dispose();
    }//GEN-LAST:event_jButton_CerrarVentanAbiertaActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton5ActionPerformed
    {//GEN-HEADEREND:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField_Descripcion_ProductoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextField_Descripcion_ProductoActionPerformed
    {//GEN-HEADEREND:event_jTextField_Descripcion_ProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Descripcion_ProductoActionPerformed

    private void jTextField_int_caja_se_forma_xAtadosActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextField_int_caja_se_forma_xAtadosActionPerformed
    {//GEN-HEADEREND:event_jTextField_int_caja_se_forma_xAtadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_int_caja_se_forma_xAtadosActionPerformed

    private void jTextField_int_Cantidad_de_AtadosActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextField_int_Cantidad_de_AtadosActionPerformed
    {//GEN-HEADEREND:event_jTextField_int_Cantidad_de_AtadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_int_Cantidad_de_AtadosActionPerformed

    private void jTextField_int_Cantidad_de_unidadesActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextField_int_Cantidad_de_unidadesActionPerformed
    {//GEN-HEADEREND:event_jTextField_int_Cantidad_de_unidadesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_int_Cantidad_de_unidadesActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
//Boton + Categoria
        Ventana_Productos VB = new Ventana_Productos();
        Ventana_Categorias ventanabierta = new Ventana_Categorias();
        ventanabierta.setLocationRelativeTo(getParent());
        ventanabierta.setVisible(true);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox_Lista_de_CategoriasActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jComboBox_Lista_de_CategoriasActionPerformed
    {//GEN-HEADEREND:event_jComboBox_Lista_de_CategoriasActionPerformed
        
    }//GEN-LAST:event_jComboBox_Lista_de_CategoriasActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton2ActionPerformed
    {//GEN-HEADEREND:event_jButton2ActionPerformed
//Boton + Ubicacion
        Ventana_Productos VB = new Ventana_Productos();
        Ventana_Ubicaciones ventanabierta = new Ventana_Ubicaciones();
        ventanabierta.setLocationRelativeTo(getParent());
        ventanabierta.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton3ActionPerformed
    {//GEN-HEADEREND:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox_Lista_de_UbicacionActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jComboBox_Lista_de_UbicacionActionPerformed
    {//GEN-HEADEREND:event_jComboBox_Lista_de_UbicacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_Lista_de_UbicacionActionPerformed

    private void jComboBox_Lista_de_UbicacionMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jComboBox_Lista_de_UbicacionMouseClicked
    {//GEN-HEADEREND:event_jComboBox_Lista_de_UbicacionMouseClicked

    }//GEN-LAST:event_jComboBox_Lista_de_UbicacionMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton4ActionPerformed
    {//GEN-HEADEREND:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton7ActionPerformed
    {//GEN-HEADEREND:event_jButton7ActionPerformed
//Boton + Abrir Proveedores
        Ventana_Productos VB = new Ventana_Productos();
        Ventana_Proveedores ventanabierta = new Ventana_Proveedores();
        ventanabierta.setLocationRelativeTo(getParent());
        ventanabierta.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jComboBox_CodProveedoresActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jComboBox_CodProveedoresActionPerformed
    {//GEN-HEADEREND:event_jComboBox_CodProveedoresActionPerformed
        //Conexion conex = new Conexion();
        //Connection cone2;

        cone2= conex.CargarDB_Base_datos_Proveedores();
        if(cone2!=null)
        {
            try
            {
                Statement orden = cone2.createStatement();
                ResultSet r = orden.executeQuery("Select* From ListadeProveedores Where Codigo_Proveedor="+jComboBox_CodProveedores.getSelectedItem());
                if(r.next())
                {
                    jTextPane_NombreyApellidoProveedor.setText(r.getString("NombreyApellidoProveedor"));
                    
                    
//                    	jTextField_int_Codigo.setText(r.getString("Codigo_Producto"));
//	jTextField_Descripcion_Producto.setText(r.getString("Descripcion_Producto"));
//	jComboBox_Lista_de_Categorias.getSelectedItem()(r.getString("Categoria_Producto"));
//	jComboBox_Lista_de_Ubicacion.getSelectedItem()(r.getString("Ubicacion_Producto"));
//    jTextField_int_Cantidad_de_unidades.setText(r.getString("Unidades_Producto"));
//    jTextField_int_Cantidad_de_cajas.setText(r.getString("Cajas_Producto"));
//    jTextField_int_caja_se_forma_xunidades.setText(r.getString("UnidadesxCaja_Producto"));
//    jTextField_int_caja_se_forma_xAtados.setText(r.getString("UnidadesxCaja_Producto"));
//                            + ""+jTextField_int_Cantidad_de_Atados.getText()+","
//                            + ""+jTextField_int_UnAtados_se_forma_xunidades.getText()+","
//                            + ""+jTextField_Costo_Unidad.getText()+","
//                            + ""+jTextField_Costo_xCaja.getText()+","
//                            + ""+jTextField_Costo_xAtado.getText()+","
//                            + ""+jComboBox_CodProveedores.getSelectedItem()+","
//                            + "'"+jTextPane_NombreyApellidoProveedor.getText()+"',"
//                            + ""+jComboBox_Marcas.getSelectedItem()+","
//                            + "'"+jTextArea_txtAnotacion_Producto.getText()+"'"
                    
                    
                    r.close();
                    orden.close();
                }
            }
            catch (SQLException ex)
            {
                Logger.getLogger(Ventana_Proveedores.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("41");
            }
        }
    }//GEN-LAST:event_jComboBox_CodProveedoresActionPerformed

    private void jTextField_Costo_xAtadoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextField_Costo_xAtadoActionPerformed
    {//GEN-HEADEREND:event_jTextField_Costo_xAtadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Costo_xAtadoActionPerformed

    private void jComboBox_Lista_de_CategoriasMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jComboBox_Lista_de_CategoriasMouseClicked
    {//GEN-HEADEREND:event_jComboBox_Lista_de_CategoriasMouseClicked
          
    }//GEN-LAST:event_jComboBox_Lista_de_CategoriasMouseClicked

    private void jComboBox_Lista_de_CategoriasMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jComboBox_Lista_de_CategoriasMouseEntered
    {//GEN-HEADEREND:event_jComboBox_Lista_de_CategoriasMouseEntered
    
    }//GEN-LAST:event_jComboBox_Lista_de_CategoriasMouseEntered

    private void jComboBox_Lista_de_UbicacionMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jComboBox_Lista_de_UbicacionMouseEntered
    {//GEN-HEADEREND:event_jComboBox_Lista_de_UbicacionMouseEntered

    }//GEN-LAST:event_jComboBox_Lista_de_UbicacionMouseEntered

    private void jComboBox_CodProveedoresMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jComboBox_CodProveedoresMouseEntered
    {//GEN-HEADEREND:event_jComboBox_CodProveedoresMouseEntered
     
    }//GEN-LAST:event_jComboBox_CodProveedoresMouseEntered

    private void jComboBox_CodProveedoresMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jComboBox_CodProveedoresMouseClicked
    {//GEN-HEADEREND:event_jComboBox_CodProveedoresMouseClicked

    }//GEN-LAST:event_jComboBox_CodProveedoresMouseClicked

    private void jButton_Actualizar_CategoriasActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_Actualizar_CategoriasActionPerformed
    {//GEN-HEADEREND:event_jButton_Actualizar_CategoriasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_Actualizar_CategoriasActionPerformed

    private void jButton_Actualizar_Categorias1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_Actualizar_Categorias1ActionPerformed
    {//GEN-HEADEREND:event_jButton_Actualizar_Categorias1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_Actualizar_Categorias1ActionPerformed

    private void jButton_Actualizar_Categorias2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_Actualizar_Categorias2ActionPerformed
    {//GEN-HEADEREND:event_jButton_Actualizar_Categorias2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_Actualizar_Categorias2ActionPerformed

    private void jButton_Actualizar_CategoriasMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jButton_Actualizar_CategoriasMouseClicked
    {//GEN-HEADEREND:event_jButton_Actualizar_CategoriasMouseClicked
    //Boton Actualizar Categoria
        jComboBox_Lista_de_Categorias.disable();
        CBoxCategorias();
        jComboBox_Lista_de_Categorias.enable();
    }//GEN-LAST:event_jButton_Actualizar_CategoriasMouseClicked

    private void jButton_Actualizar_Categorias1MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jButton_Actualizar_Categorias1MouseClicked
    {//GEN-HEADEREND:event_jButton_Actualizar_Categorias1MouseClicked
    //Boton Actualizar Ubicaciones
        jComboBox_Lista_de_Ubicacion.disable();
        CBoxUbicacion(); 
        jComboBox_Lista_de_Ubicacion.enable();
    }//GEN-LAST:event_jButton_Actualizar_Categorias1MouseClicked

    private void jButton_Actualizar_Categorias2MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jButton_Actualizar_Categorias2MouseClicked
    {//GEN-HEADEREND:event_jButton_Actualizar_Categorias2MouseClicked
   //Boton Actualizar Proveedores       
        jComboBox_CodProveedores.disable();
        CBoxProveedores();
        jComboBox_CodProveedores.enable();
    }//GEN-LAST:event_jButton_Actualizar_Categorias2MouseClicked

    private void jComboBox_MarcasMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jComboBox_MarcasMouseClicked
    {//GEN-HEADEREND:event_jComboBox_MarcasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_MarcasMouseClicked

    private void jButton_Actualizar_Categorias4MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jButton_Actualizar_Categorias4MouseClicked
    {//GEN-HEADEREND:event_jButton_Actualizar_Categorias4MouseClicked
//Boton Actualizar Categoria
        jComboBox_Marcas.disable();
             CBoxMarcas();
        jComboBox_Marcas.enable();
    }//GEN-LAST:event_jButton_Actualizar_Categorias4MouseClicked

    private void jButton_Actualizar_Categorias4ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_Actualizar_Categorias4ActionPerformed
    {//GEN-HEADEREND:event_jButton_Actualizar_Categorias4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_Actualizar_Categorias4ActionPerformed

    private void jButton_AgregarMarcas1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_AgregarMarcas1ActionPerformed
    {//GEN-HEADEREND:event_jButton_AgregarMarcas1ActionPerformed
           //Boton + Categoria
        Ventana_Productos VB = new Ventana_Productos();
        Ventana_Marcas ventanabierta = new Ventana_Marcas();
        ventanabierta.setLocationRelativeTo(getParent());
        ventanabierta.setVisible(true);
    }//GEN-LAST:event_jButton_AgregarMarcas1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton_Actualizar_Categorias;
    private javax.swing.JButton jButton_Actualizar_Categorias1;
    private javax.swing.JButton jButton_Actualizar_Categorias2;
    private javax.swing.JButton jButton_Actualizar_Categorias4;
    private javax.swing.JButton jButton_AgregarMarcas1;
    private javax.swing.JButton jButton_CerrarVentanAbierta;
    private javax.swing.JButton jButton_Eliminar;
    private javax.swing.JButton jButton_GuardarNuevo;
    private javax.swing.JButton jButton_Modificar;
    private javax.swing.JComboBox<String> jComboBox_CodProveedores;
    private javax.swing.JComboBox<String> jComboBox_Lista_de_Categorias;
    private javax.swing.JComboBox<String> jComboBox_Lista_de_Ubicacion;
    private javax.swing.JComboBox<String> jComboBox_Marcas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel2_Codigo_Cliente;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel_Caja_de_Foto_Producto;
    private javax.swing.JLabel jLabel_QR_Producto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTextArea jTextArea_txtAnotacion_Producto;
    private javax.swing.JTextField jTextField_Buscar;
    private javax.swing.JTextField jTextField_Costo_Unidad;
    private javax.swing.JTextField jTextField_Costo_xAtado;
    private javax.swing.JTextField jTextField_Costo_xCaja;
    private javax.swing.JTextField jTextField_Descripcion_Producto;
    private javax.swing.JTextField jTextField_int_Cantidad_de_Atados;
    private javax.swing.JTextField jTextField_int_Cantidad_de_cajas;
    private javax.swing.JTextField jTextField_int_Cantidad_de_unidades;
    private javax.swing.JTextField jTextField_int_Codigo;
    private javax.swing.JTextField jTextField_int_UnAtados_se_forma_xunidades;
    private javax.swing.JTextField jTextField_int_caja_se_forma_xAtados;
    private javax.swing.JTextField jTextField_int_caja_se_forma_xunidades;
    private javax.swing.JTextPane jTextPane_NombreyApellidoProveedor;
    // End of variables declaration//GEN-END:variables
   
 private void CBoxCategorias()
 {
       jComboBox_Lista_de_Categorias.removeAllItems();
   //  conex.CrearDB_Lista_de_Categorias();
     cone2=conex.CargarDB_Lista_de_Categorias(); 
     conexion_BaseDatosCategorias.CrearDB_Lista_de_Categorias();
   //  conexion_BaseDatosCategorias.CargarDB_Lista_de_Categorias();
   Statement orden;
     try
     {
         orden = cone2.createStatement();
         ResultSet r=orden.executeQuery("Select* From Lista_de_Categorias");
         while (r.next())
         {             
             jComboBox_Lista_de_Categorias.addItem(r.getString("Titulo_Categoria"));
         }
     }
     catch (SQLException ex)
     {
         Logger.getLogger(Ventana_Productos.class.getName()).log(Level.SEVERE, null, ex);
     }

     
 }
  private void CBoxProveedores()
 {
          jComboBox_CodProveedores.removeAllItems();
    cone2= conex.CargarDB_Base_datos_Proveedores();
conex.CrearDB_Base_datos_Proveedores();
   Statement orden;
     try
     {
         orden = cone2.createStatement();
         ResultSet r=orden.executeQuery("Select* From ListadeProveedores");
         while (r.next())
         {             
             jComboBox_CodProveedores.addItem(r.getString("Codigo_Proveedor"));
         }
     }
     catch (SQLException ex)
     {
         Logger.getLogger(Ventana_Productos.class.getName()).log(Level.SEVERE, null, ex);
     }

     
 }   
 private void CBoxUbicacion()
 {
      jComboBox_Lista_de_Ubicacion.removeAllItems();
     conexion_BaseDatosCategorias.CrearDB_Base_datos_Ubicaciones();
     cone2= conex.CargarDB_Base_datos_Ubicaciones();
   Statement orden;
     try
     {
         orden = cone2.createStatement();
         ResultSet r=orden.executeQuery("Select* From Lista_de_Ubicaciones");
         while (r.next())
         {             
             jComboBox_Lista_de_Ubicacion.addItem(r.getString("Lugar"));
         }
     }
     catch (SQLException ex)
     {
         Logger.getLogger(Ventana_Productos.class.getName()).log(Level.SEVERE, null, ex);
     }

     
 }   

    private void CBoxMarcas()
    {
            jComboBox_Marcas.removeAllItems();
     Base_datos_BaseDatosMarcas.CrearDB_Base_datos_Marcas();
     cone2= conex.CargarDB_Base_datos_Marcas();
   Statement orden;
     try
     {
         orden = cone2.createStatement();
         ResultSet r=orden.executeQuery("Select* From Lista_de_Marcas");
         while (r.next())
         {             
             jComboBox_Marcas.addItem(r.getString("Marcas"));
         }
     }
     catch (SQLException ex)
     {
         Logger.getLogger(Ventana_Productos.class.getName()).log(Level.SEVERE, null, ex);
     }

      
    }
    }