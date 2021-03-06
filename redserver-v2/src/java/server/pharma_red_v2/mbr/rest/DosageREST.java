/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.mbr.rest;

import java.util.List;
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
import server.pharma_red_v2.mbr.entity.Dosage;
import server.pharma_red_v2.mbr.facade.DosageFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("mbr/dosage")
@RequestScoped
public class DosageREST {

    @Context
    private UriInfo context;

    @Context
    private HttpServletResponse response;

    @Inject
    private DosageFacade dosFacade;

    @POST
    @Path("/pst_new_dosage")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Dosage createDosage(@QueryParam("cp_id") String cp_id, Dosage dos) {
        response.setHeader("old_value", "");
        response.setHeader("table_name", "dosage");
        response.setHeader("action", "INSERT");
        int cpId = Integer.parseInt(cp_id);
        return dosFacade.create(cpId, dos);
    }

    @GET
    @Path("/find_by_cp_id")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Dosage> findByCompoundingProcedureId(@QueryParam("cp_id") String cp_id) {
        int cpId = Integer.parseInt(cp_id);
        return dosFacade.findByCompoundingProcedureId(cpId);
    }
}
