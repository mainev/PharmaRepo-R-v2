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
import server.pharma_red_v2.mbr.entity.BottlingProcedure;

/**
 *
 * @author maine
 */
@Stateless
public class BottlingProcedureFacade {

    @EJB
    private ManufacturingProcedureFacade manufacturingProcedureFacade;

    @PersistenceContext(unitName = "RedServer-v2PU")
    private EntityManager em;

    public List<BottlingProcedure> findAll() {
        return em.createQuery("SELECT b from BottlingProcedure b order by b.id desc").getResultList();
    }

    public BottlingProcedure create(int mfgId, BottlingProcedure bp) {

        bp.setManufacturingProcedureId(manufacturingProcedureFacade.findById(mfgId));
        em.persist(bp);
        em.flush();
        return em.find(BottlingProcedure.class, bp.getId());
    }
}
