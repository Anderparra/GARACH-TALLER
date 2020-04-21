/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllersDatabase;


import Entitys.Producto;
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
public class ProductoFacade extends AbstractFacade<Producto> {

    @PersistenceContext(unitName = "Proyecto_IntegradorPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }
    
     public List<Producto> cascos(){
        Query cons= em.createNativeQuery("select * from producto where proveedor=1",Producto.class);
        
        return cons.getResultList();
    }
    public List<Producto> respuestos(){
        Query cons= em.createNativeQuery("select * from producto where proveedor=3",Producto.class);
        
        return cons.getResultList();
    }
    
     public List<Producto> accesorios(){
        Query cons= em.createNativeQuery("select * from producto where proveedor=2",Producto.class);
        
        return cons.getResultList();
    }
      
     
     public List<Producto> findBySearch(String search, String searchName) {
        Query cons = em.createNativeQuery("select * from producto where "+ searchName +" LIKE '%" + search + "%'", Producto.class);
        return cons.getResultList();

    }
      
     
     
    
}
