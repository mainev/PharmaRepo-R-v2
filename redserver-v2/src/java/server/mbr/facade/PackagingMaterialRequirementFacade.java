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
import server.mbr.entity.PackagingMaterialRequirement;

/**
 *
 * @author maine
 */
@Stateless
public class PackagingMaterialRequirementFacade {

    @EJB
    private UdfFacade udfFacade;

    @PersistenceContext(unitName = "RedServer-v2PU")
    private EntityManager em;
   
    
    
     public PackagingMaterialRequirement create(int udfId, PackagingMaterialRequirement pmReq){
        pmReq.setUdfId(udfFacade.findById(udfId));
        
        em.persist(pmReq);
        em.flush();
        return em.find(PackagingMaterialRequirement.class, pmReq.getId());
    }
     
     public List<PackagingMaterialRequirement> findByUdfId(int udfId){
         return em.createQuery("select p from PackagingMaterialRequirement p where p.udfId.id = :id")
                 .setParameter("id", udfId).getResultList();
     }
}
