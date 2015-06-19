/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server._main.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import server._main.entity.Area;

/**
 *
 * @author maine
 */
@Stateless
public class AreaFacade {
   @PersistenceContext(unitName = "RedServer-v2PU")
    private EntityManager em;

    public List<Area> findAll() {
        return em.createQuery("SELECT a from Area a").getResultList();
    }
}
