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
import server.pharma_red_v2.sqlsvr_copy.entity.ItemTypeC;

/**
 *
 * @author maine
 */
@Stateless
public class ItemTypeCFacade {

   @PersistenceContext(unitName = "RedServer-v2PU")
    private EntityManager em;
   
   public List<ItemTypeC> selectAll() {
        return em.createQuery("Select i from ItemTypeC i").getResultList();
    }
    
    public void insert(ItemTypeC o) {
        em.persist(o);
    }
    
    public ItemTypeC findByCode(String code){
        return (ItemTypeC)em.createQuery("Select i from ItemTypeC i where i.code = :code")
                .setParameter("code", code)
                .getSingleResult();
    }
}
