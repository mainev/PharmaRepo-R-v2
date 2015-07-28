/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.mbr.rest;

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

    /**
     * Creates a new instance of MBRRest
     */
    public MbrREST() {
    }

    /**
     * Retrieves representation of an instance of server.mbr.rest.MBRRest
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public List<Mbr> findAll() {
        return mbrFacade.findAll();
    }

    @GET
    @Path("/get_by_status")
    @Produces("application/json")
    public List<Mbr> getByStatus(@QueryParam("mbr_status") String mbrStatus) {
        return mbrFacade.findByStatus(mbrStatus);
    }

    @GET
    @Path("/get_by_batch_no")
    @Produces("application/json")
    public List<Mbr> getByBatchNo(@QueryParam("batch_no") String batchNo) {
        return mbrFacade.findByBatchNo(batchNo);
    }

    @GET
    @Path("/get_by_product_code")
    @Produces("application/json")
    public List<Mbr> getByProductCode(@QueryParam("product_code") String productCode) {
        return mbrFacade.findByProductCode(productCode);
    }

    @GET
    @Path("/get_by_product_area")
    @Produces("application/json")
    public List<Mbr> getByProductArea(@QueryParam("product_area") String productArea) {
        return mbrFacade.findByProductArea(productArea);
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mbr create(Mbr mbr) {
        return mbrFacade.create(mbr);
    }

    @POST
    @Path("/reserve_mbr")

    public void reserveMbr(@QueryParam("mbr_id") String mbr_id) {
        mbrFacade.reserveMbr(Integer.parseInt(mbr_id));
    }

    @POST
    @Path("/cancel_reservation")
    public void cancelReservation(@QueryParam("mbr_id") String mbr_id) {
        mbrFacade.cancelReservation(Integer.parseInt(mbr_id));
    }

    @POST
    @Path("/release_mbr")
    public void releaseMbr(@QueryParam("mbr_id") String mbr_id) {
        mbrFacade.releaseMbr(Integer.parseInt(mbr_id));
    }

    @POST
    @Path("/dispense_mbr_materials")
    public void dispenseMbrMaterials(@QueryParam("mbr_id") String mbr_id) {
        mbrFacade.dispenseMbrMaterials(Integer.parseInt(mbr_id));
    }
}
