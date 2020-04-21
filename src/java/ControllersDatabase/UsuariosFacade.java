/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllersDatabase;

import Entitys.Usuarios;
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
public class UsuariosFacade extends AbstractFacade<Usuarios> {

    @PersistenceContext(unitName = "Proyecto_IntegradorPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }
      public List<Usuarios> iniciarSesion(String nombre,String contraseña){
        Query cons = em.createNativeQuery("select * from usuarios where nombre='"+nombre+"' and contraseña='"+contraseña+"'",Usuarios.class);
        
        return cons.getResultList();
        
    }
      public List<Usuarios> Chat(){
        Query cons = em.createNativeQuery("select * from usuarios",Usuarios.class);
        
        return cons.getResultList();
        
    }
      
}
