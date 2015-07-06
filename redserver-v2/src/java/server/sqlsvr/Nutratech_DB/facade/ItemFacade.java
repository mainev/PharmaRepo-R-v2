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
import javax.persistence.Query;
import server.pharma_red_v2._main.facade.RawMaterialFacade;
import server.sqlsvr.Nutratech_DB.entity.Item;

/**
 *
 * @author maine
 */
@Stateless
public class ItemFacade {

    @PersistenceContext(unitName = "sqlsvrPU")
    private EntityManager em;

 
    public List<Item> selectAll() {
        return em.createQuery("Select i from Item i").getResultList();
    }

    public List<Item> selectAllRawMaterial() {
        return em.createQuery("Select i from Item i where i.itemCategoryCd = :category")
                .setParameter("category", "RM")
                .getResultList();

    }
    
    public List<Item> selectAllPackagingMaterial(){
        return em.createQuery("Select i from Item i where i.itemCategoryCd = :category")
                .setParameter("category", "PM")
                .getResultList();
    }

    @EJB
    private RawMaterialFacade rawMaterialFacade;

    //warning: this will duplicate the copy of the item if its already in the postgre database
    //re-implement this for automation
    public void create() {
        rawMaterialFacade.insertAllRawMaterial(selectAllRawMaterial());
    }
    
    public String selectItemUom(String itemCd){
        Query query = em.createQuery("Select i from Item i where i.itemCd = :itemCd");
        query.setParameter("itemCd", itemCd);
        
        Item item = (Item) query.getSingleResult();
        return item.getUom();
    }

}
