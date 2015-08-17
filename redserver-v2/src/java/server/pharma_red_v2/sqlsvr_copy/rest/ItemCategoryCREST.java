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
import server.pharma_red_v2.sqlsvr_copy.entity.ItemCategoryC;
import server.pharma_red_v2.sqlsvr_copy.facade.ItemCategoryCFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("sqlsvr_copy/item_category_c")
@RequestScoped
public class ItemCategoryCREST {

    @Context
    private UriInfo context;

    @Inject
    private ItemCategoryCFacade facade;

    public ItemCategoryCREST() {
    }

    @GET
    @Path("/g_item_category_list")
    @Produces("application/json")
    public List<ItemCategoryC> getAll() {
        return facade.selectAll();
    }

}
