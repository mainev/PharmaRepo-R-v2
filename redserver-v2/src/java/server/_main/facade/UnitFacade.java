/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server._main.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import server._main.entity.Unit;

/**
 *
 * @author maine
 */
@Stateless
public class UnitFacade {

    @PersistenceContext(unitName = "RedServer-v2PU")
    private EntityManager em;
    
    public List<Unit> findAll(){
        return em.createQuery("SELECT u FROM Unit u").getResultList();
        
    }
    
   
    
    
    
}
