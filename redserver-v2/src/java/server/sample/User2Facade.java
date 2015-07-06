/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.sample;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author maine
 */
@Stateless
public class User2Facade {

   @PersistenceContext(unitName = "SampleServerPU")
    private EntityManager em;

    public List<SampleUser> findAll() {
        return em.createQuery("SELECT u from SampleUser u").getResultList();
    }
}
