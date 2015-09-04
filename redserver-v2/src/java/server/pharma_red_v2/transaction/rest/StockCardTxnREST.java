/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.transaction.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import server.pharma_red_v2.sqlsvr_copy.entity.StockCardC;
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

    @Context
    private HttpServletRequest request;
    @Context
    private HttpServletResponse response;

    public StockCardTxnREST() {
    }
    /*
     @GET
     @Path("/g_reserved_approved_by_item_cd")
     @Produces("application/json")
     public List<StockCardTxn> getReservedAndApprovedtByItemCd(@QueryParam("item_cd") String item_cd) {
     return facade.findReservedAndApprovedByItemCd(item_cd);
     }*/

    /*
     @GET
     @Path("/g_reserved_approved_by_item_cd_company_cd")
     @Produces("application/json")
     public List<StockCardTxn> getReservedAndApprovedtByItemCdAndCompanyCd(@QueryParam("item_cd") String item_cd,
     @QueryParam("company_cd") String company_cd) {
     return facade.findReservedAndApprovedByItemCdAndCompanyCd(item_cd, company_cd);
     }
     */
    @POST
    @Path("/pst_new_stock_card_txn")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public StockCardTxn createNewStockCardTxn(@QueryParam("batch_item_req_id") String batchItemReqId, @QueryParam("stk_id") String stk_id, StockCardTxn txn) {
        int batchId = Integer.parseInt(batchItemReqId);
        int stkId = Integer.parseInt(stk_id);

        response.setHeader("old_value", "");
        response.setHeader("table_name", "stock_card_txn");
        response.setHeader("action", "INSERT");

        return facade.insert(batchId, stkId, txn);
    }

    //this will return the deleted object in the database
    @POST
    @Path("/pst_delete_stock_card_txn")
    @Produces(MediaType.APPLICATION_JSON)
    public StockCardTxn deleteStockCardTxn(@QueryParam("stk_id") String stk_id) {
        StockCardTxn old = facade.findById(Integer.parseInt(stk_id));
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        String json = gson.toJson(old);
        response.setHeader("table_name", "stock_card_txn");
        response.setHeader("action", "DELETE");
        facade.deleteStockCardTxn(Integer.parseInt(stk_id));
        return old;
    }
    /*
     @GET
     @Path("/g_stockcardtxn_by_batch_no")
     @Produces("application/json")
     public List<StockCardTxn> getStockCardTxnByBatchNo(@QueryParam("batch_no") String batchNo) {
     return facade.findStockCardByBatchNo(batchNo);
     }

     @GET
     @Path("/g_stockcardtxn_by_batch_no_and_item_category")
     @Produces("application/json")
     public List<StockCardTxn> getStockCardTxnByBatchNoAndItemCategory(@QueryParam("batch_no") String batchNo,
     @QueryParam("category") String category) {
     return facade.findStockCardByBatchNoAndItemCategory(batchNo, category);
     }
     */

    @GET
    @Path("/g_stockcard")
    @Produces("application/json")
    public StockCardC getStockCard(@QueryParam("stktxn_id") String id) {
        return facade.getStockCard(Integer.parseInt(id));
    }

}
