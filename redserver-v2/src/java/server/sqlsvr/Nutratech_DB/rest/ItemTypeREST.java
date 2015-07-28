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
import server.sqlsvr.Nutratech_DB.entity.ItemType;
import server.sqlsvr.Nutratech_DB.facade.ItemTypeFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("sqlsvr/item_type")
@RequestScoped
public class ItemTypeREST {

    @Context
    private UriInfo context;
    
    /*
    @Inject
    private ItemTypeFacade facade;

    public ItemTypeREST() {
    }

    
    @GET
    @Produces("application/json")
    public List<ItemType> getAll() {
        return facade.selectAll();
    }

      
//    @GET
//    @Path("/copy_to_postgre")
//    @Produces("application/json")
//    public String create() {
//        facade.insertAll();
//        return "copied";
//    }
    
    
    */
}
