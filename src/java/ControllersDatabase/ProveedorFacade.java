/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllersDatabase;

import Entitys.Proveedor;
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
public class ProveedorFacade extends AbstractFacade<Proveedor> {

    @PersistenceContext(unitName = "Proyecto_IntegradorPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProveedorFacade() {
        super(Proveedor.class);
    }
     public List<Proveedor> findBySearch(String search, String searchName) {
        Query cons = em.createNativeQuery("select * from proveedor where "+ searchName +" LIKE '%" + search + "%'", Proveedor.class);
        return cons.getResultList();

    }
    
}
