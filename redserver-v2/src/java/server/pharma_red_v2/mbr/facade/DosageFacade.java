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
import server.pharma_red_v2.mbr.entity.Dosage;

/**
 *
 * @author maine
 */
@Stateless
public class DosageFacade {

    @EJB
    private CompoundingProcedureFacade cpFacade;

    @PersistenceContext(unitName = "RedServer-v2PU")
    private EntityManager em;

    public Dosage create(int cpId, Dosage dos) {
        dos.setCompoundingProcedureId(cpFacade.findById(cpId));

        em.persist(dos);
        em.flush();
        return em.find(Dosage.class, dos.getId());
    }

    public List<Dosage> findByCompoundingProcedureId(int cpId) {
        return em.createQuery("select d from Dosage d where d.compoundingProcedureId.id = :id")
                .setParameter("id", cpId).getResultList();
    }
}
