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
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import server.sqlsvr.Nutratech_DB.entity.Item;
import server.sqlsvr.Nutratech_DB.facade.ItemFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("sqlsvr/item")
@RequestScoped
public class ItemREST  {

    @Context
    private UriInfo context;

    /*
    @Inject
    private ItemFacade facade;

    public ItemREST() {
    }

    @GET
    @Produces("application/json")
    public List<Item> getAll() {
      //  facade.create();
        return facade.selectAll();
    }
    
    @GET
    @Path("/raw_material")
    @Produces("application/json")
    public List<Item> getAllRawMaterial(){
        return facade.selectAllRawMaterial();
    }
    
    @GET
    @Path("/packaging_material")
    @Produces("application/json")
    public List<Item> getAllPackagingMaterial(){
        return facade.selectAllPackagingMaterial();
    }
    
     @GET
    @Path("/item_uom")
    @Produces("application/json")
    public String getItemUom(@QueryParam("item_cd") String itemCd){
        return facade.selectItemUom(itemCd);
    }
    
//    @GET
//    @Path("/copy_to_postgre")
//    @Produces("application/json")
//    public String create() {
//        facade.create();
//        return "items copied";
//    }
    
    */
}
