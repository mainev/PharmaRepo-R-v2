/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.transaction.rest;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import server.pharma_red_v2.transaction.entity.StockCardTxn;
import server.pharma_red_v2.transaction.facade.StockCardTxnFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("transaction/stock_card_txn")
@RequestScoped
public class StockCardTxnREST {

    @Context
    private UriInfo context;

    @Inject
    private StockCardTxnFacade facade;

    public StockCardTxnREST() {
    }

    @GET
    @Produces("application/json")
    public List<StockCardTxn> getAll() {
        return facade.selectAll();
    }

    @GET
    @Path("/g_reserved_approved_by_item_cd")
    @Produces("application/json")
    public List<StockCardTxn> getReservedAndApprovedtByItemCd(@QueryParam("item_cd") String item_cd) {
        return facade.findReservedAndApprovedByItemCd(item_cd);
    }

    @GET
    @Path("/g_reserved_approved_by_item_cd_company_cd")
    @Produces("application/json")
    public List<StockCardTxn> getReservedAndApprovedtByItemCdAndCompanyCd(@QueryParam("item_cd") String item_cd,
            @QueryParam("company_cd") String company_cd) {
        return facade.findReservedAndApprovedByItemCdAndCompanyCd(item_cd, company_cd);
    }

    @POST
    @Path("/pst_new_stock_card_txn")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public StockCardTxn createNewStockCardTxn(@QueryParam("mbr_id") String mbr_id, @QueryParam("stk_id") String stk_id, StockCardTxn txn) {
        int mbrId = Integer.parseInt(mbr_id);
        int stkId = Integer.parseInt(stk_id);
        return facade.insert(mbrId, stkId, txn);
    }

}
