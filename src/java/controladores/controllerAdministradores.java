package controladores;

import ControllersDatabase.AdministradorFacade;
import Entitys.Administrador;
import java.util.ArrayList;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class controllerAdministradores {

    @EJB
    AdministradorFacade controllerAdminitrador;

    private List<Administrador> administradores;
    private int id;

    private Administrador objeAdmin = new Administrador();

    private String nombre;
    private String apellido;
    private String direccion;
    private String contraseña;
    private String correo;
    private String mlogeo;
    
    private Administrador session;
    
    
    public Administrador getSession() {
        return session;
    }

    public void setSession(Administrador session) {
        this.session = session;
    }
    
    
    public AdministradorFacade getControllerAdminitrador() {
        return controllerAdminitrador;
    }

    public void setControllerAdminitrador(AdministradorFacade controllerAdminitrador) {
        this.controllerAdminitrador = controllerAdminitrador;
    }

    public void setAdministradores(List<Administrador> administradores) {
        this.administradores = administradores;
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

    public String getMlogeo() {
        return mlogeo;
    }

    public void setMlogeo(String mlogeo) {
        this.mlogeo = mlogeo;
    }

    public void registrar() {

        /*
        Administrador obj = new Administrador();
        obj.setNombre(nombre);
        obj.setApellido(apellido);
        obj.setDireccion(direccion);
        obj.setCorreo(correo);
         //obj.setContrasena(contraseña);
       
     
        Administrador obj = new Administrador();
        obj.setId(1);
         */
        controllerAdminitrador.create(objeAdmin);

    }

    public List<Administrador> getAdministradores() {
        return administradores;
    }

    public void consultar() {

        administradores = controllerAdminitrador.findAll();

        administradores.forEach((obj) -> {
            System.out.println(obj);
        });

    }

    public void eliminarPersona(Administrador temp) {
        controllerAdminitrador.remove(temp);
        consultar();
    }

    public void actualizar(Administrador temp2) {

        Administrador obj = new Administrador();
        obj.setId(id);
        obj.setNombre(nombre);
        obj.setApellido(apellido);
        obj.setDireccion(direccion);
        obj.setCorreo(correo);
        controllerAdminitrador.edit(obj);

    }

    public String dirigir() {
       session=null;
        return "motos.xhtml";
    }

    public String usuario() {
        return "usuarios.xhtml";
    }
      public String admin() {
        return "administradores.xhtml";
    }

    public Administrador getObjeAdmin() {
        return objeAdmin;
    }

    public void setObjeAdmin(Administrador objeAdmin) {
        this.objeAdmin = objeAdmin;
    }
    
    public String logeo(){
        List<Administrador> obj = controllerAdminitrador.iniciarSesion(nombre, contraseña);
        
        if(obj.isEmpty()){
            
            return "administradores.xhtml";
        }else{
             session = obj.get(0);
            return"sesionAdmin.xhtml";
        }
    }


    
}
