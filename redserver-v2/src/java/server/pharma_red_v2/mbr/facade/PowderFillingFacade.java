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
import server.pharma_red_v2.mbr.entity.PowderFillingProcedure;

/**
 *
 * @author maine
 */
@Stateless
public class PowderFillingFacade {

   @EJB
    private ManufacturingProcedureFacade manufacturingProcedureFacade;

    @PersistenceContext(unitName = "RedServer-v2PU")
    private EntityManager em;

    public List<PowderFillingProcedure> findAll() {
        return em.createQuery("SELECT p from PowderFillingProcedure p order by p.id desc").getResultList();
    }

    public PowderFillingProcedure create(int mfgId, PowderFillingProcedure p) {

        p.setManufacturingProcedureId(manufacturingProcedureFacade.findById(mfgId));
        em.persist(p);
        em.flush();
        return em.find(PowderFillingProcedure.class, p.getId());
    }
}
