package Base_de_datos;

import java.io.File;
import java.sql.*;

/**
 *
 * @author Res
 */
public class Conexion
{
//////////////////////////////////////////////////////////////////////////////////////////////////
// Establecer Barra / de separacion:
String barra = File.separator; 

//Establecer Base de Datos Clientes:
String Base_datos_Clientes = System.getProperty("user.dir")+barra+"BaseDatos"+barra+"Clientes";

// Establecer Base de Datos Provedores:
String Base_datos_Provedores = System.getProperty("user.dir")+barra+"BaseDatos"+barra+"Provedores";

// Establecer Base de Datos Productos:
String Base_datos_Productos = System.getProperty("user.dir")+barra+"BaseDatos"+barra+"Productos";

// Establecer Base de Datos Lista de Categorias:
String Base_datos_Lista_de_Categorias = System.getProperty("user.dir")+barra+"BaseDatos"+barra+"Categorias";

///////////////////////////////////////////////////////////////////////////////////////////////////

final String drivers ="org.apache.derby.jdbc.EmbeddedDriver"; 

public Connection CrearDB_Base_datos_Clientes()
    {
      Connection con;
      File url= new File(Base_datos_Clientes);
      
      if(url.exists())
      {
          System.out.println("La Base de Datos de Provedores ya existe.");   
      }else
      {
          try
          {
             //Carga base de datos 
             System.out.println("Creando Base de Datos de Provedores...");
              Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            // decirle donde va estar la base de datos
            String db ="jdbc:derby:"+Base_datos_Clientes+";create=true";
            
            con = DriverManager.getConnection(db);
            
                        // Crear Tabla
            
            String tabla = "create table ListadeClientes("
                    + "Codigo_Cliente INT PRIMARY KEY,"
                    + "NombreyApellidoCliente Varchar(255),"
                    + "Calle_Cliente Varchar(255),"
                    + "Calle_Numero_Cliente INT,"
                    + "TelefonoFijo_Cliente INT,"
                    + "TelefonoMovil_Cliente INT,"
                    + "Piso_Cliente Varchar(5),"
                    + "EntreCalles_Cliente Varchar(255),"
                    + "GoogleMaps_Cliente Varchar(255),"
                    + "CODPOSTAL_Cliente Varchar(10),"
                    + "Provincia_Cliente Varchar(255),"
                    + "Ciudad_Cliente Varchar(255),"
                    + "Email_Cliente Varchar(255),"
                    + "Anotacionl_Cliente Varchar(255)" 
                    + ")";
              try (PreparedStatement ps = con.prepareStatement(tabla))
              {
                  ps.execute();
              }
                    
              System.out.println("Base de Datos de Cliente se ha Creado con Exito.");

             return con;

          }catch (ClassNotFoundException | SQLException ex)
              {
                             System.out.println("Error:"+ex);   
              }
      }
        return null;
    }

public Connection CrearDB_Base_datos_Provedores()
    {
      Connection con;

      File url= new File(Base_datos_Provedores);
      
      if(url.exists())
      {
          System.out.println("La Base de Datos de Provedores ya existe.");   
      }else
      {
          try
          {
             //Carga base de datos 
             System.out.println("Creando Base de Datos de Provedores...");
              Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            // decirle donde va estar la base de datos
            String db ="jdbc:derby:"+Base_datos_Provedores+";create=true";
            
            con = DriverManager.getConnection(db);
            
                        // Crear Tabla
            
            String tabla = "create table ListadeProvedores("
                    + "Codigo_Provedor INT PRIMARY KEY,"
                    + "NombreyApellidoProvedor Varchar(255),"
                    + "Calle_Provedor Varchar(255),"
                    + "Calle_Numero_Provedor INT,"
                    + "TelefonoFijo_Provedor INT,"
                    + "TelefonoMovil_Provedor INT,"
                    + "Piso_Provedor Varchar(5),"
                    + "EntreCalles_Provedor Varchar(255),"
                    + "GoogleMaps_Provedor Varchar(255),"
                    + "CODPOSTAL_Provedor Varchar(10),"
                    + "Provincia_Provedor Varchar(255),"
                    + "Ciudad_Provedor Varchar(255),"
                    + "Email_Provedor Varchar(255),"
                    + "Anotacionl_Provedor Varchar(255)" 
                    + ")";              
    try (PreparedStatement ps = con.prepareStatement(tabla))
              {
                  ps.execute();
              }
                    
              System.out.println("Base de Datos de Provedores Creada con Exito.");

             return con;

          }catch (ClassNotFoundException | SQLException ex)
              {
                             System.out.println("Error:"+ex);   
              }
      }
        return null;
    }
   
public Connection CrearDB_Base_datos_Productos()
    {
      Connection con;
      File url= new File(Base_datos_Productos);
      
      if(url.exists())
      {
          System.out.println("La Base de Datos de Productos ya existe.");   
      }else
      {
          try
          {
             //Carga base de datos 
             System.out.println("Creando Base de Datos de Productos...");
              Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            // decirle donde va estar la base de datos
            String db ="jdbc:derby:"+Base_datos_Productos+";create=true";
            
            con = DriverManager.getConnection(db);
            
                        // Crear Tabla
            
            String tabla = "create table Listadeproductos("
                    + "Codigo_Producto INT PRIMARY KEY,"
                    + "Categoria_Producto  Varchar(255),"
                    + "Descripcion_Producto Varchar(255),"
                    + "Cajas_Producto INT,"
                    + "UnidadesxCaja_Producto  INT,"
                    + "Unidades_Producto INT,"
                    + "Atados_Producto INT,"
                    + "Un_Atado_Tiene_Producto INT,"
                    + "CostoxUnidad_Producto INT,"
                    + "CostoxCaja__Producto INT,"
                    + "CostoxAtado INT,"
                    + "Ubicacion_Producto Varchar(255),"
                    + "Codigo_Provedor INT,"
                    + "NombreyApellido__Provedor Varchar(255),"
                    + "Anotacionl_Provedor Varchar(255)" 
                    + ")";
              try (PreparedStatement ps = con.prepareStatement(tabla))
              {
                  ps.execute();
              }
                    
              System.out.println("Base de Datos de Productos Creada con Exito.");

             return con;

          }catch (ClassNotFoundException | SQLException ex)
              {
                             System.out.println("Error:"+ex);   
              }
      }
        return null;
    }
        
public Connection CargarDB_Base_datos_Provedores()
    {

      int id=1;
      Connection con;
        try 
        {
           Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
           String db = "jdbc:derby:"+Base_datos_Provedores;
           con = DriverManager.getConnection(db);
            System.out.println("La base de datos esta cargada.");
                        
           return con;
               }
        catch (ClassNotFoundException | SQLException ex)
        {
            System.out.println("Error: "+ex);
        }
        return null;  
    }
public Connection CargarDB_Base_datos_Clientes()
    {
      int id=1;
      Connection con;      
        try 
        {
           Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
           String db = "jdbc:derby:"+Base_datos_Clientes;
           con = DriverManager.getConnection(db);
            System.out.println("La base de datos de Clientes esta cargada.");
                        
           return con;
               }
        catch (ClassNotFoundException | SQLException ex)
        {
            System.out.println("Error: "+ex);
        }
        return null;  
    }
        
public Connection CargarDB_Base_datos_Productos()
    {
      int id=1;
      Connection con;     
        try 
        {
           Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
           String db = "jdbc:derby:"+Base_datos_Productos;
           con = DriverManager.getConnection(db);
            System.out.println("La base de datos de Productos esta cargada.");
                        
           return con;
               }
        catch (ClassNotFoundException | SQLException ex)
        {
            System.out.println("Error: "+ex);
        }
        return null;  
    }

/////

public Connection CrearDB_Lista_de_Categorias()
    {
      Connection con;
      File url= new File(Base_datos_Lista_de_Categorias);
      
      if(url.exists())
      {
          System.out.println("La Base de Datos de Lista de Categorias ya existe.");   
      }else
      {
          try
          {
             //Carga base de datos 
             System.out.println("Creando Base de Datos de Lista de Categorias...");
              Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            // decirle donde va estar la base de datos
            String db ="jdbc:derby:"+Base_datos_Lista_de_Categorias+";create=true";
            
            con = DriverManager.getConnection(db);
            
                        // Crear Tabla
            
            String tabla = "create table Lista_de_Categorias("
                    + "ID INT PRIMARY KEY,"
 //                   + "ID INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    + "Titulo_Categoria Varchar(255)"
                    + ")";
              try (PreparedStatement ps = con.prepareStatement(tabla))
              {
                  ps.execute();
              }
                    
              System.out.println("Base de Datos de Lista de Categorias Creada con Exito.");

             return con;

          }catch (ClassNotFoundException | SQLException ex)
              {
                             System.out.println("Error:"+ex);   
              }
      }
        return null;
    }
        
public Connection CargarDB_Lista_de_Categorias()
    {
      Connection con;
        try 
        {
           Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
           String db = "jdbc:derby:"+Base_datos_Lista_de_Categorias;
           con = DriverManager.getConnection(db);
            System.out.println("La base de datos Categoria ya esta cargada.");
                        
           return con;
               }
        catch (ClassNotFoundException | SQLException ex)
        {
            System.out.println("Error: "+ex);
        }
        return null;  
    }




/////
//    public void AutoIncrementar_Cod_Clientes()
//    {
//     int id=1;
//     PreparedStatement ps=null;
//     ResultSet rs=null;
//     Conexion db = new Conexion();
//        try
//        {
//     ps.db.getconnection().prepareStatement("SELECT MAX(Codigo_Cliente) FROM ListadeClientes");
//rs=ps.executeQuery();
//            while (rs.next())
//            {
//                id = rs.getInt(1)+1;               
//            }
//        }
//        catch (SQLException e)
//        {
//        }  
//    }
}
