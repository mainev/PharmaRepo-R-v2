/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server._main.facade;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import server._main.entity.PackagingMaterial;
import server._main.entity.Product;

/**
 *
 * @author maine
 */
@Stateless
public class ProductFacade {

   @EJB
    private PackagingMaterialFacade packagingMaterialFacade;

    @PersistenceContext(unitName = "RedServer-v2PU")
    private EntityManager em;

    public List<Product> findAll() {
        return em.createQuery("SELECT p FROM Product p").getResultList();
    }

    public PackagingMaterial getPrimaryPackaging(Integer productId) {
        Query query = em.createNativeQuery("SELECT primary_packaging_id from mbr.primary_secondary_packaging "
                + "where mbr.primary_secondary_packaging.product_id = '" + productId + "'");
        Integer bottleId = (Integer) query.getSingleResult();
        return packagingMaterialFacade.findById(bottleId);
    }
    
     public PackagingMaterial getSecondaryPackaging(Integer productId) {
        Query query = em.createNativeQuery("SELECT secondary_packaging_id from mbr.primary_secondary_packaging "
                + "where mbr.primary_secondary_packaging.product_id = '" + productId + "'");
        Integer cBoxId = (Integer) query.getSingleResult();
        return packagingMaterialFacade.findById(cBoxId);
    }
    
    
    
}
