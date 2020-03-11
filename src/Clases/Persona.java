/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Raúl Eduardo Scalia
 */
class Persona 
{

    public Persona(int codigo_id, String Nombre_Apellido, String Calle, int numero_de_calle, int numero_telefono_fijo, int numero_celular, String piso_depto, String piso_letra, String entre_calles, String enlace_google_maps, String codigo_postal, String Provincia, String Ciudad, String email, String nota_personal, String rol) {
        this.codigo_id = codigo_id;
        this.Nombre_Apellido = Nombre_Apellido;
        this.Calle = Calle;
        this.numero_de_calle = numero_de_calle;
        this.numero_telefono_fijo = numero_telefono_fijo;
        this.numero_celular = numero_celular;
        this.piso_depto = piso_depto;
        this.piso_letra = piso_letra;
        this.entre_calles = entre_calles;
        this.enlace_google_maps = enlace_google_maps;
        this.codigo_postal = codigo_postal;
        this.Provincia = Provincia;
        this.Ciudad = Ciudad;
        this.email = email;
        this.nota_personal = nota_personal;
        this.rol=rol;
    }
    
    public String getRol(){
        return rol;
    }
    
    public void setRol(String rol){
        
        if(rol.equals("Cliente"))
        {
          this.rol="Cliente";  
        }
        else if (rol.equals("Proveedor"))
       this.rol="Proveedor";
    }

    public int getCodigo_id() {
        return codigo_id;
    }

    public void setCodigo_id(int codigo_id) {
        this.codigo_id = codigo_id;
    }

    public String getNombre_Apellido() {
        return Nombre_Apellido;
    }

    public void setNombre_Apellido(String Nombre_Apellido) {
        this.Nombre_Apellido = Nombre_Apellido;
    }

    public String getCalle() {
        return Calle;
    }

    public void setCalle(String Calle) {
        this.Calle = Calle;
    }

    public int getNumero_de_calle() {
        return numero_de_calle;
    }

    public void setNumero_de_calle(int numero_de_calle) {
        this.numero_de_calle = numero_de_calle;
    }

    public int getNumero_telefono_fijo() {
        return numero_telefono_fijo;
    }

    public void setNumero_telefono_fijo(int numero_telefono_fijo) {
        this.numero_telefono_fijo = numero_telefono_fijo;
    }

    public int getNumero_celular() {
        return numero_celular;
    }

    public void setNumero_celular(int numero_celular) {
        this.numero_celular = numero_celular;
    }

    public String getPiso_depto() {
        return piso_depto;
    }

    public void setPiso_depto(String piso_depto) {
        this.piso_depto = piso_depto;
    }

    public String getPiso_letra() {
        return piso_letra;
    }

    public void setPiso_letra(String piso_letra) {
        this.piso_letra = piso_letra;
    }

    public String getEntre_calles() {
        return entre_calles;
    }

    public void setEntre_calles(String entre_calles) {
        this.entre_calles = entre_calles;
    }

    public String getEnlace_google_maps() {
        return enlace_google_maps;
    }

    public void setEnlace_google_maps(String enlace_google_maps) {
        this.enlace_google_maps = enlace_google_maps;
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public String getProvincia() {
        return Provincia;
    }

    public void setProvincia(String Provincia) {
        this.Provincia = Provincia;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String Ciudad) {
        this.Ciudad = Ciudad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNota_personal() {
        return nota_personal;
    }

    public void setNota_personal(String nota_personal) {
        this.nota_personal = nota_personal;
    }
    private int codigo_id;
    private String Nombre_Apellido;
    private String Calle;
    private int numero_de_calle;
    private int numero_telefono_fijo;
    private int numero_celular;
    private String piso_depto;
    private String piso_letra;
    private String entre_calles;
    private String enlace_google_maps;
    private String codigo_postal;
    private String Provincia;
    private String Ciudad;
    private String email;
    private String nota_personal;
    private String rol;
 

    
}
