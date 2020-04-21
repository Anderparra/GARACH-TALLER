/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import ControllersDatabase.ProveedorFacade;
import Entitys.Proveedor;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

@ManagedBean

public class controladorproveedor {

    @EJB
    private ProveedorFacade controllerproveedor;
    private List<Proveedor> proveedor;
    private List<Proveedor> Provee = new ArrayList();
    private String nombre;
    private String telefono;
    private String direccion;
    private String productos;
    private String search;
    private String searchName;

    public List<Proveedor> getProvee() {
        return Provee;
    }

    public void setProvee(List<Proveedor> Provee) {
        this.Provee = Provee;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }
    
    

    public ProveedorFacade getControllerproveedor() {
        return controllerproveedor;
    }

    public void setControllerproveedor(ProveedorFacade controllerproveedor) {
        this.controllerproveedor = controllerproveedor;
    }

    public List<Proveedor> getProveedor() {
        return proveedor;
    }

    public void setProveedor(List<Proveedor> proveedor) {
        this.proveedor = proveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public void registrar() {

        Proveedor obj = new Proveedor();

        obj.setNombre(nombre);
        obj.setTelefono(telefono);
        obj.setDireccion(direccion);
        obj.setProductos(productos);
        controllerproveedor.create(obj);

    }

    public String dirigir() {
        return "usuarios.xhtml";

    }
     public List<Proveedor> findAll() {
        List<Proveedor> pro;
        if (Provee.isEmpty()) {
            pro = controllerproveedor.findAll();
        } else {
            pro = Provee;
        }
        return pro;
    }
     
      public void findAllBySearch() {
        Provee = controllerproveedor.findBySearch(this.search, this.searchName);
        System.out.print(Provee.toString());
    }


}
