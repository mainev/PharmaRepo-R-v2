/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.audit;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author maine
 */
@Stateless
public class AuditFacade {

    @PersistenceContext(unitName = "RedServer-v2PU")
    private EntityManager em;

    public AuditTrail insert(AuditTrail audit) {
        em.persist(audit);
        em.flush();
        return em.find(AuditTrail.class, audit.getId());
    }

    public List<AuditTrail> findAll() {
        return em.createQuery("Select a from AuditTrail a").getResultList();
    }
}
