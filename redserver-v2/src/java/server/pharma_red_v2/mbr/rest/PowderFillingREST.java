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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import server.pharma_red_v2.mbr.entity.PowderFillingProcedure;
import server.pharma_red_v2.mbr.facade.PowderFillingFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("mbr/powder_filling")
@RequestScoped
public class PowderFillingREST {

    @Context
    private UriInfo context;

    @Context
    private HttpServletResponse response;
    @Inject
    private PowderFillingFacade facade;

    @POST
    @Path("/pst_new_powder_filling")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PowderFillingProcedure createNewPowderFilling(@QueryParam("mfg_id") String mfg_id, PowderFillingProcedure p) {

        response.setHeader("old_value", "");
        response.setHeader("table_name", "powder_filling_procedure");
        response.setHeader("action", "INSERT");
        int mfgId = Integer.parseInt(mfg_id);
        return facade.create(mfgId, p);
    }
}
