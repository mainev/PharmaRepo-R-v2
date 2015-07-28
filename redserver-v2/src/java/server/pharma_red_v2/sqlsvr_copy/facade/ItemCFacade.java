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
import server.pharma_red_v2.sqlsvr_copy.entity.ItemC;

/**
 *
 * @author maine
 */
@Stateless
public class ItemCFacade {

    @PersistenceContext(unitName = "RedServer-v2PU")
    private EntityManager em;

    public List<ItemC> selectAll() {
        return em.createQuery("Select i from ItemC i").getResultList();
    }

    public void insert(ItemC itemC) {
        em.persist(itemC);
    }
    /*
      public ItemC findByCode(String code){
         System.out.println("HAHAHAA   code:"+code+" "+em.createQuery("Select i from ItemC i where i.itemCd = :code")
                .setParameter("code", code)
                .getResultList());
        ItemC c =  (ItemC)em.createQuery("Select i from ItemC i where i.itemCd = :code")
                .setParameter("code", code)
                .getSingleResult();
      System.out.println("***********RESULT IS********:"+c);
        return c;
    }*/
      
        public ItemC findByCode(String code){
        
         return (ItemC)em.createQuery("Select i from ItemC i where i.itemCd = :code")
                .setParameter("code", code)
                .getSingleResult();
     
    }
        
         public  List<ItemC> findByCode2(String code){
        
        List<ItemC> c =  em.createQuery("Select i from ItemC i where i.itemCd = :code")
                .setParameter("code", code)
                .getResultList();
    return c;
//        if(!c.isEmpty() )
//            return c.get(0);
//        else
//        return null;
    }
        
        
      
      
      
}
