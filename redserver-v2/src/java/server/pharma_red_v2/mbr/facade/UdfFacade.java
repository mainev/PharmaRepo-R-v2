/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.mbr.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import server.pharma_red_v2.mbr.entity.Udf;

/**
 *
 * @author maine
 */
@Stateless
public class UdfFacade {

   @PersistenceContext(unitName = "RedServer-v2PU")
    private EntityManager em;
   
   public Udf findById(int id){
       return em.find(Udf.class, id);
   }
   
   public Udf createUdf(Udf udfId){
       em.persist(udfId);
       em.flush();
       return findById(udfId.getId());
   }
}
