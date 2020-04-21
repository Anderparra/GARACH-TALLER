/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllersDatabase;

import Entitys.Venta;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author HERMANOS
 */
@Stateless
public class VentaFacade extends AbstractFacade<Venta> {

    @PersistenceContext(unitName = "Proyecto_IntegradorPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VentaFacade() {
        super(Venta.class);
    }
    public List<Venta> findBySearch(String search, String searchName) {
        Query cons = em.createNativeQuery("select * from venta where "+ searchName +" LIKE '%" + search + "%'", Venta.class);
        return cons.getResultList();

    }
    
     public void  comprar(String fecha_venta,String id_cliente, String id_productos, String cantidad, String subtotal, String total) {
        Query cons = em.createNativeQuery("insert into venta values('"+fecha_venta+"','"+id_cliente+"','"+id_productos+"','"+cantidad+"','"+subtotal+"','"+total+"') ", Venta.class);
        

    }
}
