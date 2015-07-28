/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.sqlsvr.Nutratech_DB.rest;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import server.sqlsvr.Nutratech_DB.entity.Company;
import server.sqlsvr.Nutratech_DB.facade.CompanyFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("sqlsvr/company")
@RequestScoped
public class CompanyREST {

    @Context
    private UriInfo context;
    
    /*
    @Inject
    private CompanyFacade companyFacade;

   
    public CompanyREST() {
    }

   
    @GET
    @Produces("application/json")
    public List<Company> getAll() {
        return companyFacade.selectAll();
    }
    
   
//    @GET
//    @Path("/copy_to_postgre")
//    @Produces("application/json")
//    public List<Company> create(){
//       return companyFacade.create();
//    }
    

   
    */
}
