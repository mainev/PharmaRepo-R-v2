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
import server.pharma_red_v2._main.entity.Classification;

/**
 *
 * @author maine
 */
@Stateless
public class ClassificationFacade {

   @PersistenceContext(unitName = "RedServer-v2PU")
    private EntityManager em;

    public List<Classification> findAll() {
        return em.createQuery("SELECT c from Classification c").getResultList();
    }
}
