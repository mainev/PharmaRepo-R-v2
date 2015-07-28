/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.sqlsvr.Nutratech_DB.facade;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import server.pharma_red_v2.sqlsvr_copy.entity.ItemTypeC;
import server.pharma_red_v2.sqlsvr_copy.facade.ItemTypeCFacade;
import server.sqlsvr.Nutratech_DB.entity.ItemType;

/**
 *
 * @author maine
 */
@Stateless
public class ItemTypeFacade {

   /*
     @PersistenceContext(unitName = "sqlsvrPU")
    private EntityManager em;
     
     public List<ItemType> selectAll(){
         return em.createQuery("Select i from ItemType i").getResultList();
     }
     
//    @EJB
//    private ItemTypeCFacade itemTypeCFacade;
//    
//    public void insertAll(){
//        for(ItemType it : selectAll()){
//            ItemTypeC itc = new ItemTypeC();
//            itc.setCode(it.getCode());
//            itc.setDescs(it.getDescs());
//            itemTypeCFacade.insert(itc);
//        }
//    }
    
    */
}
