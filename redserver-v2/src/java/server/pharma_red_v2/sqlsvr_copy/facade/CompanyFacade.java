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
import server.pharma_red_v2.sqlsvr_copy.entity.Company;

/**
 *
 * @author maine
 */
@Stateless
public class CompanyFacade {

    @PersistenceContext(unitName = "RedServer-v2PU")
    private EntityManager em;

    public List<Company> selectAll() {
        return em.createQuery("Select c from Company c").getResultList();
    }

    public void insert(Company company) {
        em.persist(company);
    }

    public void insertAll(List<Company> cList) {
        for (Company c : cList) {
            em.persist(c);
        }
    }

    public Company findByCode(String code) {

        return (Company) em.createQuery("Select c from Company c where c.code = :code")
                .setParameter("code", code)
                .getSingleResult();
    }

}
