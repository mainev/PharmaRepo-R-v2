/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.mbr.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import server.mbr.entity.Mbr;

/**
 *
 * @author maine
 */
@Stateless
public class MbrFacade {

    @PersistenceContext(unitName = "RedServer-v2PU")
    private EntityManager em;
    
    public List<Mbr> findAll(){
        return em.createQuery("SELECT m from Mbr m order by m.id desc").getResultList();
    }
    
    public Mbr create(Mbr mbr){
        em.persist(mbr);
        em.flush();
        return em.find(Mbr.class, mbr.getId());
    }
}
