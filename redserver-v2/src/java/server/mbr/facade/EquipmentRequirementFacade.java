/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.mbr.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import server.mbr.entity.EquipmentRequirement;

/**
 *
 * @author maine
 */
@Stateless
public class EquipmentRequirementFacade {

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
}
