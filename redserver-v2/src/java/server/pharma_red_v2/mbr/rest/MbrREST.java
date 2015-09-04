/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.mbr.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
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
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import server.pharma_red_v2.mbr.entity.Mbr;
import server.pharma_red_v2.mbr.facade.MbrFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("mbr/mbr")
@RequestScoped
public class MbrREST {

    @Context
    private UriInfo context;

    @Inject
    private MbrFacade mbrFacade;
    @Context
    private HttpServletRequest request;
    @Context
    private HttpServletResponse response;

    public MbrREST() {
    }

    @GET
    @Path("/g_batch_list")
    @Produces("application/json")
    public List<Mbr> getBatchList(@Context SecurityContext sc) {

        System.out.println("SecurityContext(isSecure): " + sc.isSecure());
        System.out.println("SecurityContext(isUserInRole-admin): " + sc.isUserInRole("ADMIN"));
        System.out.println("UserPrincipal: " + sc.getUserPrincipal());
        System.out.println("AuthenticationScheme: " + sc.getAuthenticationScheme());
        return mbrFacade.findAll();
    }

    @GET
    @Path("/g_batch_by_stat")
    @Produces("application/json")
    public List<Mbr> getBatchByStatus(@QueryParam("mbr_status") String mbrStatus) {
        return mbrFacade.findByStatus(mbrStatus);
    }

    @GET
    @Path("/g_batch_by_batch_no")
    @Produces("application/json")
    public List<Mbr> getBatchByBatchNo(@QueryParam("batch_no") String batchNo) {
        return mbrFacade.findByBatchNo(batchNo);
    }

    @GET
    @Path("/g_batch_by_product_code")
    @Produces("application/json")
    public List<Mbr> getBatchByProductCode(@QueryParam("product_code") String productCode) {
        return mbrFacade.findByProductCode(productCode);
    }

    @GET
    @Path("/g_batch_by_area")
    @Produces("application/json")
    public List<Mbr> getByProductArea(@QueryParam("product_area") String productArea) {
        return mbrFacade.findByProductArea(productArea);
    }

    @POST
    @Path("/pst_new_batch")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mbr createNewBatch(Mbr mbr) {
        response.setHeader("old_value", "");
        response.setHeader("table_name", "mbr");
        response.setHeader("action", "INSERT");
        return mbrFacade.create(mbr);
    }

    @POST
    @Path("/pst_reserve_mbr")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mbr reserveBatch(@QueryParam("mbr_id") String mbr_id) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        String json = gson.toJson(mbrFacade.findById(Integer.parseInt(mbr_id)));
        System.out.println("output from rest: " + json);
        response.setHeader("old_value", json);
        response.setHeader("table_name", "mbr");
        response.setHeader("action", "UPDATE");
        return mbrFacade.reserveMbr(Integer.parseInt(mbr_id));
    }

    @POST
    @Path("/pst_cancel_reservation")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mbr cancelBatchReservation(@QueryParam("mbr_id") String mbr_id) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        String json = gson.toJson(mbrFacade.findById(Integer.parseInt(mbr_id)));
        System.out.println("output from rest: " + json);
        response.setHeader("old_value", json);
        response.setHeader("table_name", "mbr");
        response.setHeader("action", "UPDATE");
        return mbrFacade.cancelReservation(Integer.parseInt(mbr_id));
    }

    @POST
    @Path("/pst_print_batch")
    public void printBatch(@QueryParam("mbr_id") String mbr_id) {
        mbrFacade.releaseMbr(Integer.parseInt(mbr_id));
    }

    @POST
    @Path("/pst_dispense_batch_material")
    public void dispenseBatchMaterials(@QueryParam("mbr_id") String mbr_id) {
        mbrFacade.dispenseMbrMaterials(Integer.parseInt(mbr_id));
    }

//    @GET
//    @Path("/g_batch_stock_card_txn_list")
//    @Produces("application/json")
//    public List<StockCardTxn> getBatchStockCardTxnList(@QueryParam("mbr_id") String mbr_id) {
//        Mbr batch = mbrFacade.findById(Integer.parseInt(mbr_id));
//        return batch.getStockCardTxnList();
//    }
    @GET
    @Path("/g_batch_by_id")
    @Produces("application/json")
    public Mbr getBatchById(@QueryParam("mbr_id") String mbr_id) {
        return mbrFacade.findById(Integer.parseInt(mbr_id));

    }
}
