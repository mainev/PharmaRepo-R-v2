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
import server.pharma_red_v2.mbr.entity.PackagingProcedure;

/**
 *
 * @author maine
 */
@Stateless
public class PackagingProcedureFacade {

    @EJB
    private ManufacturingProcedureFacade manufacturingProcedureFacade;

    @PersistenceContext(unitName = "RedServer-v2PU")
    private EntityManager em;

    public List<PackagingProcedure> findAll() {
        return em.createQuery("SELECT p from PackagingProcedure p order by p.id desc").getResultList();
    }

    public PackagingProcedure create(int mfgId, PackagingProcedure ppo) {

        ppo.setManufacturingProcedureId(manufacturingProcedureFacade.findById(mfgId));
        em.persist(ppo);
        em.flush();
        return em.find(PackagingProcedure.class, ppo.getId());
    }
}
