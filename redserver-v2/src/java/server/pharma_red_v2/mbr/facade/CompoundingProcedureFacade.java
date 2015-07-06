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
import server.pharma_red_v2.mbr.entity.CompoundingProcedure;

/**
 *
 * @author maine
 */
@Stateless
public class CompoundingProcedureFacade {

    @EJB
    private ManufacturingProcedureFacade mfgProcedureFacade;

    @PersistenceContext(unitName = "RedServer-v2PU")
    private EntityManager em;
    public CompoundingProcedure findById(int cpId){
        return em.find(CompoundingProcedure.class, cpId);
    }

    public CompoundingProcedure create(int mfgId, CompoundingProcedure cp) {
        cp.setManufacturingProcedureId(mfgProcedureFacade.findById(mfgId));

        em.persist(cp);
        em.flush();
        return em.find(CompoundingProcedure.class, cp.getId());
    }

    public List<CompoundingProcedure> findByMfgId(int mfgId) {
        return em.createQuery("select c from CompoundingProcedure c where c.manufacturingProcedureId.id = :id")
                .setParameter("id", mfgId).getResultList();
    }
}
