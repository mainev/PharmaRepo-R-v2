/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.mbr.facade;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import server.mbr.entity.RawMaterialRequirement;

/**
 *
 * @author maine
 */
@Stateless
public class RawMaterialRequirementFacade {
    @EJB
    private UdfFacade udfFacade;

    @PersistenceContext(unitName = "RedServer-v2PU")
    private EntityManager em;
   
    
    
     public RawMaterialRequirement create(int udfId, RawMaterialRequirement rmreq){
        rmreq.setUdfId(udfFacade.findById(udfId));
        
        em.persist(rmreq);
        em.flush();
        return em.find(RawMaterialRequirement.class, rmreq.getId());
    }
     
     public List<RawMaterialRequirement> findByUdfId(int udfId){
         return em.createQuery("select r from RawMaterialRequirement r where r.udfId.id = :id")
                 .setParameter("id", udfId).getResultList();
     }
}
