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
import server.pharma_red_v2.mbr.entity.EquipmentRequirement;

/**
 *
 * @author maine
 */
@Stateless
public class EquipmentRequirementFacade {

    @EJB
    private ManufacturingProcedureFacade manufacturingProcedureFacade;

    @PersistenceContext(unitName = "RedServer-v2PU")
    private EntityManager em;

    public List<EquipmentRequirement> findAll() {
        return em.createQuery("SELECT e from EquipmentRequirement e order by e.id desc").getResultList();
    }

    public List<EquipmentRequirement> findAllByManufacturingIdAndProcedure(int mfgId, String procedure) {
        return em.createQuery("Select e from EquipmentRequirement e where e.manufacturingProcedureId.id = :mfgId and e.procedure = :proc")
                .setParameter("mfgId", mfgId)
                .setParameter("proc", procedure)
                .getResultList();

    }

    public EquipmentRequirement create(int mfgId, EquipmentRequirement er) {

        er.setManufacturingProcedureId(manufacturingProcedureFacade.findById(mfgId));
        em.persist(er);
        em.flush();
        return em.find(EquipmentRequirement.class, er.getId());
    }
}
