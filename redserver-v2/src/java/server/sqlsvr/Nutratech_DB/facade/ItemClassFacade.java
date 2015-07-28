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
import server.pharma_red_v2.sqlsvr_copy.entity.ItemClassC;
import server.pharma_red_v2.sqlsvr_copy.facade.ItemClassCFacade;
import server.sqlsvr.Nutratech_DB.entity.ItemClass;

/**
 *
 * @author maine
 */
@Stateless
public class ItemClassFacade {

   /*
     @PersistenceContext(unitName = "sqlsvrPU")
    private EntityManager em;
     
     public List<ItemClass> selectAll(){
         return em.createQuery("Select i from ItemClass i").getResultList();
     }
     
//     @EJB
//     private ItemClassCFacade facade;
//     public void create(){
//         for(ItemClass ic : selectAll()){
//             ItemClassC icc = new ItemClassC();
//             icc.setCode(ic.getCode());
//             icc.setDescs(ic.getDescs());
//             facade.insert(icc);
//         }
//     }
    
    */
}
