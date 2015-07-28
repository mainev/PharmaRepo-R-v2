/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.sqlsvr_copy.rest;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import server.pharma_red_v2.sqlsvr_copy.entity.CompanyC;
import server.pharma_red_v2.sqlsvr_copy.facade.CompanyCFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("sqlsvr_copy/companyc")
@RequestScoped
public class CompanyCREST {

    @Context
    private UriInfo context;
    
    @Inject
    private CompanyCFacade companyFacadeC;
    
    

  
    public CompanyCREST() {
    }

    
    @GET
    @Produces("application/json")
    public List<CompanyC> getAll() {
        return companyFacadeC.selectAll();
    }

   
}
