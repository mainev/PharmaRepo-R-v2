/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.mbr.facade;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import server.pharma_red_v2.mbr.entity.RawMaterialRequirement;

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
   
    public List<RawMaterialRequirement> findAll(){
        return em.createQuery("Select r from RawMaterialRequirement r order by r.id desc").getResultList();
    }
    
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
     
      public RawMaterialRequirement findByDetails(int rmId, double qty, short unitId, int udfId){
         List<RawMaterialRequirement> list =  em.createQuery("select r from RawMaterialRequirement r where r.udfId.id = :udfId and r.rawMaterialId.id = :rmId and r.quantity = :qty and r.unitId.id = :unitId")
                 .setParameter("udfId", udfId)
                 .setParameter("rmId", rmId)
                 .setParameter("qty", qty)
                 .setParameter("unitId", unitId)
                 .getResultList();
         if(!list.isEmpty()){
             return list.get(0);
            
         }
         return null;
     }
}
