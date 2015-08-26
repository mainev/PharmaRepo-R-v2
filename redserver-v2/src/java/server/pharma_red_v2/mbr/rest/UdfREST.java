/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.mbr.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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
import server.pharma_red_v2.mbr.entity.Udf;
import server.pharma_red_v2.mbr.facade.UdfFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("mbr/udf")
@RequestScoped
public class UdfREST {

    @Context
    private UriInfo context;

    @Inject
    private UdfFacade udfFacade;

    @Context
    private HttpServletResponse response;

    /**
     * Creates a new instance of UdfREST
     */
    public UdfREST() {
    }

    @GET
    @Produces("application/json")
    @Path("/g_udf_by_id")
    public Udf getUdfById(@QueryParam("id") String id) {
        int udfId = Integer.parseInt(id);

        return udfFacade.findById(udfId);
    }

    @POST
    @Path("/pst_new_udf")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Udf createNewUdf(Udf udfId) {
        response.setHeader("old_value", "");
        response.setHeader("table_name", "udf");
        response.setHeader("action", "INSERT");
        return udfFacade.createUdf(udfId);
    }

}
