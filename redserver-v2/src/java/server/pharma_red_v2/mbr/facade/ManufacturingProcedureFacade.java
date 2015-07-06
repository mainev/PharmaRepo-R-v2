/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.mbr.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import server.pharma_red_v2.mbr.entity.ManufacturingProcedure;

/**
 *
 * @author maine
 */
@Stateless
public class ManufacturingProcedureFacade {

  
    @PersistenceContext(unitName = "RedServer-v2PU")
    private EntityManager em;
    
    public ManufacturingProcedure findById(int id){
        return em.find(ManufacturingProcedure.class, id);
    }
    
     public ManufacturingProcedure create(ManufacturingProcedure mp){
       em.persist(mp);
       em.flush();
       return findById(mp.getId());
   }
}
