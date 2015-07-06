/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2._main.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import server.pharma_red_v2._main.entity.PackSize;

/**
 *
 * @author maine
 */
@Stateless
public class PackSizeFacade {

      @PersistenceContext(unitName = "RedServer-v2PU")
    private EntityManager em;

    public PackSize create(PackSize packSizeId) {
        PackSize ps = findByPackSizeDetails(packSizeId);
        if (ps != null) {
            return ps;
        } else {
            em.persist(packSizeId);
            em.flush();
            return em.find(PackSize.class, packSizeId.getId());
        }
    }

    public List<PackSize> findAll() {
        return em.createQuery("Select p from PackSize p order by p.id desc").getResultList();
    }

    public PackSize findByPackSizeDetails(PackSize packSizeId) {
        List<PackSize> list = em.createQuery("Select p from PackSize p where p.quantity = :quantity and p.containerId = :containerId and p.unitId = :unitId")
                .setParameter("quantity", packSizeId.getQuantity())
                .setParameter("containerId", packSizeId.getContainerId())
                .setParameter("unitId", packSizeId.getUnitId())
                .getResultList();
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
}
