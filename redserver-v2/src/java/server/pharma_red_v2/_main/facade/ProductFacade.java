/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2._main.facade;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import server.pharma_red_v2._main.entity.Product;
import server.pharma_red_v2.sqlsvr_copy.entity.Item;
import server.pharma_red_v2.sqlsvr_copy.facade.ItemFacade;

/**
 *
 * @author maine
 */
@Stateless
public class ProductFacade {

    @PersistenceContext(unitName = "RedServer-v2PU")
    private EntityManager em;

    public List<Product> findAll() {
        return em.createQuery("SELECT p FROM Product p").getResultList();
    }

    public Product create(Product product) {
        em.persist(product);
        em.flush();
        return findById(product.getId());
    }

    public Product findById(int id) {
        return em.find(Product.class, id);
    }

    public void createPrimarySecondaryPackaging(int productId, int primaryId, int secondaryId) {
        Query query = em.createNativeQuery("INSERT INTO mbr.primary_secondary_packaging "
                + "(id, primary_packaging_id, secondary_packaging_id) VALUES (?, ?, ?)");
        query.setParameter(1, productId);
        query.setParameter(2, primaryId);
        query.setParameter(3, secondaryId);
        query.executeUpdate();
    }

    @EJB
    private ItemFacade itemFacade;

    public Item getPrimaryPackaging(Integer productId) {
        Query query = em.createNativeQuery("SELECT primary_packaging_id from mbr.primary_secondary_packaging "
                + "where mbr.primary_secondary_packaging.id = '" + productId + "'");
        Integer bottleId = (Integer) query.getSingleResult();
        return itemFacade.findById(bottleId);
    }

    public Item getSecondaryPackaging(Integer productId) {
        Query query = em.createNativeQuery("SELECT secondary_packaging_id from mbr.primary_secondary_packaging "
                + "where mbr.primary_secondary_packaging.id = '" + productId + "'");
        Integer cBoxId = (Integer) query.getSingleResult();
        return itemFacade.findById(cBoxId);
    }

    public Boolean isCodeUnique(String code) {
        List<Product> pList = findAll();
        for (Product p : pList) {
            if (p.getCode().toUpperCase().equals(code.toUpperCase())) {
                return false;
            }
        }
        return true;

    }

}
