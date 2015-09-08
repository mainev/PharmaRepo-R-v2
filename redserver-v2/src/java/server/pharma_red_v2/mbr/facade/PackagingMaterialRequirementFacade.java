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
import server.pharma_red_v2.mbr.entity.PackagingMaterialRequirement;

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

    public PackagingMaterialRequirement create(int udfId, PackagingMaterialRequirement pmReq) {
        pmReq.setUdfId(udfFacade.findById(udfId));

        em.persist(pmReq);
        em.flush();
        return em.find(PackagingMaterialRequirement.class, pmReq.getId());
    }

    public PackagingMaterialRequirement findById(int id) {
        return em.find(PackagingMaterialRequirement.class, id);
    }

    public List<PackagingMaterialRequirement> findByUdfId(int udfId) {
        return em.createQuery("select p from PackagingMaterialRequirement p where p.udfId.id = :id")
                .setParameter("id", udfId).getResultList();
    }

    public PackagingMaterialRequirement findByDetails(int itemId, double qty, short unitId, int udfId) {
        List<PackagingMaterialRequirement> list = em.createQuery("select p from PackagingMaterialRequirement p where p.udfId.id = :udfId and p.itemId.id = :itemId and p.quantity = :qty and p.unitId.id = :unitId")
                .setParameter("udfId", udfId)
                .setParameter("itemId", itemId)
                .setParameter("qty", qty)
                .setParameter("unitId", unitId)
                .getResultList();
        if (!list.isEmpty()) {
            return list.get(0);

        }
        return null;
    }
}
