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
import server.pharma_red_v2.sqlsvr_copy.entity.Item;

/**
 *
 * @author maine
 */
@Stateless
public class ItemFacade {

    @PersistenceContext(unitName = "RedServer-v2PU")
    private EntityManager em;

    public List<Item> selectAll() {
        return em.createQuery("Select i from Item i").getResultList();
    }

    public List<Item> selectAllRawMaterial() {
        return em.createQuery("Select i from Item i where i.itemCategoryId.code = :category")
                .setParameter("category", "RM")
                .getResultList();
    }

    public List<Item> selectAllPackgMaterial() {
        return em.createQuery("Select i from Item i where i.itemCategoryId.code = :category")
                .setParameter("category", "PM")
                .getResultList();
    }

    public void insert(Item itemC) {
        em.persist(itemC);
    }

    public Item findById(int id) {
        return em.find(Item.class, id);
    }

    public List<Item> findByCode(String code) {

        return em.createQuery("Select i from Item i where i.itemCd = :code")
                .setParameter("code", code)
                .getResultList();

    }

    public Item findByCodeAndCategory(String code, String category) {

        return (Item) em.createQuery("Select i from Item i where i.itemCd = :code and i.itemCategoryId.code = :category")
                .setParameter("category", category)
                .setParameter("code", code)
                .getSingleResult();

    }

}
