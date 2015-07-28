/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.sqlsvr.Nutratech_DB.facade;

import javax.ejb.Stateless;

/**
 *
 * @author maine
 */
@Stateless
public class ItemCategoryFacade {

    /*
     @PersistenceContext(unitName = "sqlsvrPU")
     private EntityManager em;

     public List<ItemCategory> selectAll(){
     return em.createQuery("Select i  from ItemCategory i").getResultList();
     }

     //     @EJB
     //     private ItemClassCFacade f1;
     //
     //     @EJB
     //     private ItemCategoryCFacade f2;
     //
     //     public void create(){
     //         for(ItemCategory ic : selectAll()){
     //             ItemCategoryC icc = new ItemCategoryC();
     //             icc.setCode(ic.getCode());
     //             icc.setDescs(ic.getDescs());
     //             icc.setItemClassId(f1.findByCode(ic.getItemClassCd()));
     //             f2.insert(icc);
     //         }
     //     }

     */
}
