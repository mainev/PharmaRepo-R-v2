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
import server.pharma_red_v2.StockStatus;
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
    @Path("/g_stock_card_list")
    @Produces("application/json")
    public List<StockCardC> getStockCardList() {
        return facade.selectAll();
    }

    @GET
    @Path("/g_stock_card_by_item_cd")
    @Produces("application/json")
    public List<StockCardC> getStockCardByItemCd(@QueryParam("item_cd") String item_cd) {
        return facade.findStockCardByItemCd(item_cd);
    }

    @GET
    @Path("/g_stock_card_by_id")
    @Produces("application/json")
    public StockCardC getStockCardById(@QueryParam("id") String id) {
        return facade.findById(Integer.parseInt(id));
    }

    @GET
    @Path("/g_stock_card_by_company_cd_and_item_id")
    @Produces("application/json")
    public List<StockCardC> getAvailableStockCardByCompanyCdAndItemId(@QueryParam("company_cd") String company_cd, @QueryParam("item_id") String item_id) {
        return facade.findAvailableStockCardByCompanyCdAndItemId(company_cd, Integer.parseInt(item_id));
    }

    @POST
    @Path("/pst_change_stock_card_status_to_depleted")
    public void changeStockCardStatusToDepleted(@QueryParam("stk_id") String stk_id) {
        facade.updateStockCardStatusToDepleted(Integer.parseInt(stk_id));
    }

    @GET
    @Path("/g_stockcard_by_control_no")
    @Produces("application/json")
    public StockCardC getStockCardByControlNo(@QueryParam("control_no") String controlNo) {
        return facade.findByControlNo(controlNo);
    }

    @GET
    @Path("/g_available_stockcard")
    @Produces("application/json")
    public List<StockCardC> getAvailableStockCard(@QueryParam("company_id") String company_id,
            @QueryParam("item_id") String item_id) {
        return facade
                .findAvailableStockCard(Integer.parseInt(company_id), Integer.parseInt(item_id));
    }

    @POST
    @Path("/pst_update_stock_card_stock_status")
    public void updateStockCardStockStatus(@QueryParam("stk_id") String stk_id, @QueryParam("stock_stat") String stock_stat) {
        facade.updateStockCardStockStatus(Integer.parseInt(stk_id), StockStatus.valueOf(stock_stat));
    }
}
