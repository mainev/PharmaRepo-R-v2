/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.mbr.rest;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
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

    @Inject
    private DosageFacade dosFacade;

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Dosage create(@QueryParam("cp_id") String cp_id, Dosage dos) {
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
