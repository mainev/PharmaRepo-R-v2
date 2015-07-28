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
import server.pharma_red_v2.sqlsvr_copy.entity.ItemCategoryC;

/**
 *
 * @author maine
 */
@Stateless
public class ItemCategoryCFacade {

  @PersistenceContext(unitName = "RedServer-v2PU")
    private EntityManager em;
  
    public List<ItemCategoryC> selectAll() {
        return em.createQuery("Select i from ItemCategoryC i").getResultList();
    }
    
    public void insert(ItemCategoryC o) {
        em.persist(o);
    }
    
      public ItemCategoryC findByCode(String code){
        return (ItemCategoryC)em.createQuery("Select c from ItemCategoryC c where c.code = :code")
                .setParameter("code", code)
                .getSingleResult();
    }
}
