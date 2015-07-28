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
import server.pharma_red_v2.sqlsvr_copy.entity.WarehouseC;
import server.pharma_red_v2.sqlsvr_copy.facade.WarehouseCFacade;
import server.sqlsvr.Nutratech_DB.entity.Warehouse;

/**
 *
 * @author maine
 */
@Stateless
public class WarehouseFacade {
    
/*
   
     @PersistenceContext(unitName = "sqlsvrPU")
    private EntityManager em;
     
     public List<Warehouse> selectAll(){
         return em.createQuery("Select w from Warehouse w").getResultList();
     }
     
//     @EJB
//    private WarehouseCFacade warehouseCFacade;
//      
//     public void create(){
//         List<Warehouse> wList = selectAll();
//         for(Warehouse w : wList){
//             WarehouseC wc = new WarehouseC();
//             wc.setCode(w.getCode());
//             wc.setDescs(w.getDescs());
//             warehouseCFacade.insert(wc);
//         }
//     }
    
    */
}
