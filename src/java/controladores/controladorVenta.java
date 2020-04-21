/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import ControllersDatabase.ProductoFacade;
import ControllersDatabase.VentaFacade;
import Entitys.Venta;
import static java.lang.Integer.parseInt;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import Entitys.Producto;
import Entitys.Usuarios;
import java.util.Date;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@ManagedBean
/**
 *
 * @author URIBE
 */
public class controladorVenta {

    @EJB
    VentaFacade controllerVenta;

    @EJB
    private ProductoFacade controllerProductos;

    private List<Producto> productosLista = new ArrayList();
    private List<Producto> Accesorios = new ArrayList();
    private List<Producto> Repuestos = new ArrayList();
    private List<Venta> venta = new ArrayList();
    private Venta objeAdmin = new Venta();
    private Producto temp= new Producto();
    private String fecha_venta;
    private String cantidad;
    private String subtotal;
    private String total;
    private String search;
    private String searchName;
    private int cantidades=1;

    public Producto getTemp() {
        return temp;
    }

    public void setTemp(Producto temp) {
        this.temp = temp;
    }
    
    
    
    

    public int getCantidades() {
        return cantidades;
    }

    public void setCantidades(int cantidades) {
        this.cantidades = cantidades;
    }

    public Venta getObjeAdmin() {
        return objeAdmin;
    }

    public void setObjeAdmin(Venta objeAdmin) {
        this.objeAdmin = objeAdmin;
    }

    @PostConstruct
    public void alIniciar() {

        productosLista = controllerProductos.cascos();
        Accesorios = controllerProductos.accesorios();
        Repuestos = controllerProductos.respuestos();
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public VentaFacade getControllerVenta() {
        return controllerVenta;
    }

    public void setControllerVenta(VentaFacade controllerVenta) {
        this.controllerVenta = controllerVenta;
    }

    public List<Venta> getVenta() {
        return venta;
    }

    public void setVenta(List<Venta> Venta) {
        this.venta = Venta;
    }

    public String getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(String fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void registrar() {

        /*
        Venta obj = new Venta();

        obj.setFechaVenta(fecha_venta);
        obj.setCantidad(0);
        obj.setSubtotal(0);
        obj.setTotal(0);
        controllerVenta.create(obj);
         */
        controllerVenta.create(objeAdmin);
    }

    public List<Venta> findAll() {
        List<Venta> ventas;
        if (venta.isEmpty()) {
            ventas = controllerVenta.findAll();
        } else {
            ventas = venta;
        }
        return ventas;
    }
    
    public void Eliminar(Venta temp) {
        controllerVenta.remove(temp);
    }

    public void update() {
        Venta obj = new Venta();

        obj.setFechaVenta(fecha_venta);
        obj.setCantidad(0);
        obj.setSubtotal(0);
        obj.setTotal(0);

        controllerVenta.edit(obj);
    }

    public void findAllBySearch() {
        venta = controllerVenta.findBySearch(this.search, this.searchName);
        System.out.print(venta.toString());
    }

    public String dirigir() {
        return "usuarios.xhtml";
    }

    private int idUltimoProductoComprado = 0;

    public void Insertar(Producto Temp) {

        System.out.println("----:"+Temp);
        
        
        Venta obj = new Venta();
        obj.setFechaVenta(new Date().toString());
    
        FacesContext context = FacesContext.getCurrentInstance();
        Application app = context.getApplication();
        
        controladorusuarios datosUser = app.evaluateExpressionGet(context, "#{controladorusuarios}", controladorusuarios.class);
        
        
        obj.setIdCliente(datosUser.sessionIniciada.getId());
        obj.setIdProducto(Temp.getId());
        obj.setCantidad(getCantidades());
        obj.setSubtotal(Temp.getPrecio());
        obj.setTotal(getCantidades()* (Temp.getPrecio()));
        controllerVenta.create(obj);
      
        idUltimoProductoComprado = 1;
        
        correo(Temp);
        saveMessage();

    }


    public void correo(Producto Temp) {
        
         Venta obj = new Venta();
        obj.setFechaVenta(new Date().toString());
    
        FacesContext context = FacesContext.getCurrentInstance();
        Application app = context.getApplication();
        
        controladorusuarios datosUser = app.evaluateExpressionGet(context, "#{controladorusuarios}", controladorusuarios.class);
        
        
        
        
        
        
        
        int Total=getCantidades()* Temp.getPrecio();
        String smtp = "smtp.gmail.com";
        int port = 587;
        String username = "garachsa@gmail.com";
        String password = "GarachSA2019";
        String mensaje = "Gracias por confiar en nosotros \n Hola "+datosUser.sessionIniciada.getNombre()+" la compra ha sido exitosa!, te inivitamos a segrir comprando en GARACH \n Compraste:"+Temp.getDescripcion()+"\n Marca:"+Temp.getMarca()+"   \n Con un precio unitario de:"+Temp.getPrecio()+" \n Una cantidad de:"+getCantidades()+"\n Con un total a pagar de:"+Total+"$";
        String asunto = "Compra Garach";
        String paraQuien = datosUser.sessionIniciada.getCorreo();

        Properties props = null;
        Session session = null;
        MimeMessage message = null;
        Address fromAddress = null;
        Address toAddress = null;

        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtp);
        props.put("mail.smtp.port", port);

        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        message = new MimeMessage(session);
        try {
            message.setContent(mensaje, "text/plain");
            message.setSubject(asunto);
            fromAddress = new InternetAddress(username);
            message.setFrom(fromAddress);

            toAddress = new InternetAddress(paraQuien);
            message.setRecipient(Message.RecipientType.TO, toAddress);

            message.saveChanges();

            Transport transport = session.getTransport("smtp");
            transport.connect(smtp, port, username, password);
            if (!transport.isConnected()) {
                System.out.println("Inicio de sesion incorrecto");
            }
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            System.out.println("Envio mensaje");
        } catch (MessagingException me) {
            me.printStackTrace();
            System.out.println("Error");
        }
    }
    
    
    
    
    
    
    
     
   
     
    public void saveMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
         
        context.addMessage(null, new FacesMessage("Tu compra a sido exitosa!!!!!!!","Su compra a sido exitosa!") );
        
    }


    
    
    
    public ProductoFacade getControllerProductos() {
        return controllerProductos;
    }

    public void setControllerProductos(ProductoFacade controllerProductos) {
        this.controllerProductos = controllerProductos;
    }

    public List<Producto> getProductosLista() {
        return productosLista;
    }

    public void setProductosLista(List<Producto> productosLista) {
        this.productosLista = productosLista;
    }

    public int getIdUltimoProductoComprado() {
        return idUltimoProductoComprado;
    }

    public void setIdUltimoProductoComprado(int idUltimoProductoComprado) {
        this.idUltimoProductoComprado = idUltimoProductoComprado;
    }

    public List<Producto> getAccesorios() {
        return Accesorios;
    }

    public void setAccesorios(List<Producto> Accesorios) {
        this.Accesorios = Accesorios;
    }

    public List<Producto> getRepuestos() {
        return Repuestos;
    }

    public void setRepuestos(List<Producto> Repuestos) {
        this.Repuestos = Repuestos;
    }

}
