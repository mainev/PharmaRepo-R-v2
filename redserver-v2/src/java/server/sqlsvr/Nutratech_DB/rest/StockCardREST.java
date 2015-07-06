/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.sqlsvr.Nutratech_DB.rest;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import server.sqlsvr.Nutratech_DB.entity.StockCard;
import server.sqlsvr.Nutratech_DB.facade.StockCardFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("sqlsvr/stock_card")
@RequestScoped
public class StockCardREST {

    @Context
    private UriInfo context;

    @Inject
    private StockCardFacade facade;

    public StockCardREST() {
    }

    @GET
    @Produces("application/json")
    public List<StockCard> getAll() {
        return facade.selectAll();
    }
    
    @GET
    @Path("/remaining_quantity")
    @Consumes("application/json")
    public Float getMaterialQuantity( @QueryParam("item_cd") String itemCd){
       return facade.selectMaterialQuantity(itemCd);
    }

}
