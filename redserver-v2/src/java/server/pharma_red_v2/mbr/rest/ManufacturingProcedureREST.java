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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import server.pharma_red_v2.mbr.entity.ManufacturingProcedure;
import server.pharma_red_v2.mbr.facade.ManufacturingProcedureFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("mbr/manufacturing_procedure")
@RequestScoped
public class ManufacturingProcedureREST {

    @Context
    private UriInfo context;

    @Context
    private HttpServletResponse response;

    @Inject
    private ManufacturingProcedureFacade manufacturingProcedureFacade;

    @POST
    @Path("/pst_new_mfg_proc")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ManufacturingProcedure createNewMfgProcedure(ManufacturingProcedure mp) {
        response.setHeader("old_value", "");
        response.setHeader("table_name", "manufacturing_procedure");
        response.setHeader("action", "INSERT");
        return manufacturingProcedureFacade.create(mp);
    }
}
