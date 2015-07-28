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
import server.pharma_red_v2.sqlsvr_copy.entity.CompanyC;
import server.pharma_red_v2.sqlsvr_copy.facade.CompanyCFacade;
import server.sqlsvr.Nutratech_DB.entity.Company;

/**
 *
 * @author maine
 */
@Stateless(name = "CompanyFacade")
public class CompanyFacade {
    
    /*
    @EJB
    private CompanyCFacade companyFacadeC;

     @PersistenceContext(unitName = "sqlsvrPU")
    private EntityManager em;
     
     public List<Company> selectAll(){
         return em.createQuery("Select c from Company c").getResultList();
     }
     
     
     public List<Company> create(){
         System.out.println("calling create company method");
         List<Company> list = selectAll();
         
         for(Company c : list){
             CompanyC cc = new CompanyC();
             cc.setCode(c.getCode());
             cc.setDescs(c.getDescs());
             companyFacadeC.insert(cc);
         }
         
         return list;
     }
    
    */
}
