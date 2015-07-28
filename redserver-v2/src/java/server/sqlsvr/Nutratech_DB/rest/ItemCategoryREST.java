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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import server.sqlsvr.Nutratech_DB.entity.ItemCategory;
import server.sqlsvr.Nutratech_DB.facade.ItemCategoryFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("sqlsvr/item_category")
@RequestScoped
public class ItemCategoryREST {

    @Context
    private UriInfo context;
    
    /*
    @Inject
    private ItemCategoryFacade facade;

   
    public ItemCategoryREST() {
    }

   
    @GET
    @Produces("application/json")
    public List<ItemCategory> getAll() {
        return facade.selectAll();
    }

//    @GET
//    @Path("/copy_to_postgre")
//    @Produces("application/json")
//    public String getJson() {
//        facade.create();
//        return "item category copied";
//    }
    
    */
}
