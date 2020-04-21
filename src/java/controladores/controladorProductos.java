/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import ControllersDatabase.ProductoFacade;
import Entitys.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

@ManagedBean
/**
 *
 * @author URIBE
 */
public class controladorProductos {

    @EJB
    ProductoFacade controllerProducto;
     private Producto objetProducto = new Producto();
     private List<Producto> producto = new ArrayList();
    
    private List<Producto> productos;
    private String descripcion;
    private String marca;
    private int proveedor;
    private int precio;
    private int cantidad;
    private String search;
    private String searchName;

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
    
    
    

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    
    
    public ProductoFacade getControllerProducto() {
        return controllerProducto;
    }

    public void setControllerProducto(ProductoFacade controllerProducto) {
        this.controllerProducto = controllerProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getProveedor() {
        return proveedor;
    }

    public void setProveedor(int proveedor) {
        this.proveedor = proveedor;
    }

   

   
    
    
    
    
    public void registrar() {
        
        
       controllerProducto.create(objetProducto);
        
       
        
    }
    
     public String productos(){
         return "usuarios.xhtml";
     }

    public Producto getObjetProducto() {
        return objetProducto;
    }

    public void setObjetProducto(Producto objetProducto) {
        this.objetProducto = objetProducto;
    }
    
    public List<Producto> findAll() {
        List<Producto> produc;
        if (producto.isEmpty()) {
            produc = controllerProducto.findAll();
        } else {
            produc = productos;
        }
        return produc;
    }
     public void findAllBySearch() {
        producto = controllerProducto.findBySearch(this.search, this.searchName);
        System.out.print(producto.toString());
    }

    public List<Producto> getProducto() {
        return producto;
    }

    public void setProducto(List<Producto> producto) {
        this.producto = producto;
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
    
     
     
     
  
}
