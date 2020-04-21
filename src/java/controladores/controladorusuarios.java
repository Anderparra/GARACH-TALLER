/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import ControllersDatabase.UsuariosFacade;
import Entitys.Usuarios;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author HERMANOS
 */
@SessionScoped
@ManagedBean
public class controladorusuarios {

    @EJB
    UsuariosFacade controllerUsuario;
    
    private List<Usuarios> usuarios;
    private List<Usuarios> Chats = new ArrayList();
    private int id;
    private Usuarios objUsuario = new Usuarios();
    private String nombre;
    private String apellido;
    private String direccion;
    private String contraseña;
     private String correo;
    private String concatenacion;
    
    Usuarios sessionIniciada;

    public List<Usuarios> getChats() {
        return Chats;
    }

    public void setChats(List<Usuarios> Chats) {
        this.Chats = Chats;
    }
    
    
    
    
    public UsuariosFacade getControllerUsuario() {
        return controllerUsuario;
    }

    public void setControllerUsuario(UsuariosFacade controllerUsuario) {
        this.controllerUsuario = controllerUsuario;
    }

    public List<Usuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }
    
    
   public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
      
  
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellido() {
        return apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getConcatenacion() {
        return concatenacion;
    }

    public void setConcatenacion(String concatenacion) {
        this.concatenacion = concatenacion;
    }
     public Usuarios getObjUsuario() {
        return objUsuario;
    }

    public void setObjUsuario(Usuarios objUsuario) {
        this.objUsuario = objUsuario;
    }
    
     @PostConstruct
    public void iniciar(){
    
       Chats = controllerUsuario.Chat();
    
}
    
    
    public void registrar() {
        /*
        Usuarios obj = new Usuarios();
     
       obj.setNombre(nombre);
       obj.setApellido(apellido);
       obj.setDireccion(direccion);
       obj.setCorreo(correo);
       obj.setContraseña(contraseña);
       controllerUsuario.create(obj);
        */
        
        controllerUsuario.create(objUsuario);
       
        
    }
    
    public void consultar(){
        
         usuarios = controllerUsuario.findAll();
    }
    
    /*
     public String dirigir() {
        if (nombre.equalsIgnoreCase("Ander") && apellido.equalsIgnoreCase("anderson")) { 
            return "sesion.xhtml" ;
        } else {
            
            return "usuarios.xhtml";
        }
        
        
    }
*/
    
      public String dirigir() {
          sessionIniciada = null;
       
        return "motos.xhtml";
    }

      public String inicio(){
         return "motos.xhtml";
     }
      
     public String productos(){
         return "productos.xhtml";
     }
    public String proveedores(){
         return "proveedor.xhtml";
     }
    public String venta(){
         return "venta.xhtml";
     }
    
    public String logeo(){
        List<Usuarios> obj = controllerUsuario.iniciarSesion(nombre, contraseña);
        
        if(obj.isEmpty()){ 
            return "usuarios.xhtml";
        }else{
            sessionIniciada = obj.get(0);
            return"sesionUsuario.xhtml";
        }
    }

    public Usuarios getSessionIniciada() {
        return sessionIniciada;
    }

    public void setSessionIniciada(Usuarios sessionIniciada) {
        this.sessionIniciada = sessionIniciada;
    }

   
      
}
    

