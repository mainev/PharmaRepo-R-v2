/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.sqlsvr_copy.rest;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import server.pharma_red_v2.sqlsvr_copy.entity.Item;
import server.pharma_red_v2.sqlsvr_copy.facade.ItemFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("sqlsvr_copy/item")
@RequestScoped
public class ItemREST {

    @Context
    private UriInfo context;

    @Inject
    private ItemFacade itemCFacade;

    public ItemREST() {
    }

    @GET
    @Path("/g_item_list")
    @Produces("application/json")
    public List<Item> getAll() {
        return itemCFacade.selectAll();
    }

    @GET
    @Path("/g_rm_item_list")
    @Produces("application/json")
    public List<Item> getRawMaterialList() {
        return itemCFacade.selectAllRawMaterial();
    }

    @GET
    @Path("/g_pm_item_list")
    @Produces("application/json")
    public List<Item> getPackgMaterialList() {
        return itemCFacade.selectAllPackgMaterial();
    }

}
