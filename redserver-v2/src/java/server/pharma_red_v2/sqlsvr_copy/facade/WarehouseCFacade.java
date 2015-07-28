/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.sqlsvr_copy.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import server.pharma_red_v2.sqlsvr_copy.entity.WarehouseC;

/**
 *
 * @author maine
 */
@Stateless
public class WarehouseCFacade {
    
    @PersistenceContext(unitName = "RedServer-v2PU")
    private EntityManager em;
    
    public List<WarehouseC> selectAll() {
        return em.createQuery("Select w from WarehouseC w").getResultList();
    }
    
    public void insert(WarehouseC warehouseC) {
        em.persist(warehouseC);
    }
    
    public WarehouseC findByCode(String code){
        return (WarehouseC) em.createQuery("Select w from WarehouseC w where w.code = :code")
                .setParameter("code", code)
                .getSingleResult();
    }
}
