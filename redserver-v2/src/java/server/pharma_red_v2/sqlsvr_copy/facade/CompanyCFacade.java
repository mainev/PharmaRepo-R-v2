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
import server.pharma_red_v2.sqlsvr_copy.entity.CompanyC;

/**
 *
 * @author maine
 */
@Stateless
public class CompanyCFacade {

    @PersistenceContext(unitName = "RedServer-v2PU")
    private EntityManager em;

    public List<CompanyC> selectAll() {
        return em.createQuery("Select c from CompanyC c").getResultList();
    }
    
    public void insert(CompanyC company){
        em.persist(company);
    }
    
    public void insertAll(List<CompanyC> cList){
        for(CompanyC c : cList){
            em.persist(c);
        }
    }
    
    public CompanyC findByCode(String code){
        
        return (CompanyC)em.createQuery("Select c from CompanyC c where c.code = :code")
                .setParameter("code", code)
                .getSingleResult();
    }
    
}
