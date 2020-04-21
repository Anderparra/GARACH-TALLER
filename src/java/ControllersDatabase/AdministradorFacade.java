/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllersDatabase;

import Entitys.Administrador;
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
public class AdministradorFacade extends AbstractFacade<Administrador> {

    @PersistenceContext(unitName = "Proyecto_IntegradorPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministradorFacade() {
        super(Administrador.class);
    }
     public List<Administrador> iniciarSesion(String nombre,String contraseña){
        Query cons = em.createNativeQuery("select * from administrador where nombre='"+nombre+"' and contraseña='"+contraseña+"'",Administrador.class);
        
        return cons.getResultList();
        
    }
    
}
