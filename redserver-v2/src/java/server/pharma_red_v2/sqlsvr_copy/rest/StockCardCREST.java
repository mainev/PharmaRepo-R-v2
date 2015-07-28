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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import server.pharma_red_v2.sqlsvr_copy.entity.StockCardC;
import server.pharma_red_v2.sqlsvr_copy.facade.StockCardCFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("sqlsvr_copy/stock_card_c")
@RequestScoped
public class StockCardCREST {

    @Context
    private UriInfo context;

    @Inject
    private StockCardCFacade facade;

    public StockCardCREST() {
    }

    @GET
    @Produces("application/json")
    public List<StockCardC> getAll() {
        return facade.selectAll();
    }

    @GET
    @Path("/get_stock_card_by_id")
    @Produces("application/json")
    public StockCardC getStockCardById(@QueryParam("id") String id) {
        return facade.findById(Integer.parseInt(id));
    }

    @GET
    @Path("/get_stock_card_by_item_cd")
    @Produces("application/json")
    public List<StockCardC> getStockCardByItemCd(@QueryParam("item_cd") String item_cd) {
        return facade.findStockCardByItemCd(item_cd);
    }

    @GET
    @Path("/get_stock_card_by_company_cd_and_item_cd")
    @Produces("application/json")
    public List<StockCardC> getStockCardByCompanyCdAndItemCd(@QueryParam("company_cd") String company_cd, @QueryParam("item_cd") String item_cd) {
        return facade.findStockCardByCompanyCdAndItemCd(company_cd, item_cd);
    }

    @POST
    @Path("/change_stock_card_status")
    public void changeStockCardStatus(@QueryParam("stk_id") String stk_id) {
        //System.out.println("hehe");
        facade.updateStockCardStatusToDepleted(Integer.parseInt(stk_id));
    }

    @GET
    @Path("/get_by_control_no")
    @Produces("application/json")
    public StockCardC getByControlNo(@QueryParam("control_no") String controlNo) {
        return facade.findByControlNo(controlNo);
    }

}
