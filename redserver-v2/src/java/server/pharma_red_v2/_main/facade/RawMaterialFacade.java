/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2._main.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import server.pharma_red_v2._main.entity.RawMaterial;
import server.sqlsvr.Nutratech_DB.entity.Item;

/**
 *
 * @author maine
 */
@Stateless
public class RawMaterialFacade {
  
   

    @PersistenceContext(unitName = "RedServer-v2PU")
    private EntityManager em;
    
    public List<RawMaterial> findAll(){
        return em.createQuery("SELECT r FROM RawMaterial r").getResultList();
    }
    
    public void insert(RawMaterial rm){
        em.persist(rm);
    }
    
    
    //warning: this will duplicate the copy of the item if its already in the database
    //re-implement this for automation
    public void insertAllRawMaterial(List<Item> itemList){
        for(Item i : itemList){
            RawMaterial rm = new RawMaterial();
            rm.setCode(i.getItemCd());
            rm.setName(i.getDescs());
            insert(rm);
        }
    }
    
}
