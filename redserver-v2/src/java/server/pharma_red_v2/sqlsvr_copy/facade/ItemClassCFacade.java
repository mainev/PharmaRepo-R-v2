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
import javax.persistence.Query;
import server.pharma_red_v2.sqlsvr_copy.entity.ItemClassC;

/**
 *
 * @author maine
 */
@Stateless
public class ItemClassCFacade {

   @PersistenceContext(unitName = "RedServer-v2PU")
    private EntityManager em;
   
     public List<ItemClassC> selectAll() {
        return em.createQuery("Select i from ItemClassC i").getResultList();
    }
    
    public void insert(ItemClassC o) {
        em.persist(o);
    }
    
    public ItemClassC findByCode(String code){
        Query q =  em.createQuery("Select i from ItemClassC i where i.code = :code")
                .setParameter("code", code);
        return (ItemClassC) q.getSingleResult();
    }
}
