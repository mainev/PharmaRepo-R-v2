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
import server.pharma_red_v2.mbr.entity.CompoundingProcedure;
import server.pharma_red_v2.mbr.facade.CompoundingProcedureFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("mbr/compounding_procedure")
@RequestScoped
public class CompoundingProcedureREST {

    @Context
    private UriInfo context;

    @Inject
    private CompoundingProcedureFacade cpFacade;

    /**
     * Creates a new instance of CompoundingProcedureREST
     */
    public CompoundingProcedureREST() {
    }

    @POST
    @Path("/pst_new_compounding_proc")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CompoundingProcedure createNewCompoundingProc(@QueryParam("mfg_id") String mfg_id, CompoundingProcedure cp) {
        int mfgId = Integer.parseInt(mfg_id);
        return cpFacade.create(mfgId, cp);
    }

    @GET
    @Path("/find_by_mfg_id")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<CompoundingProcedure> findByMfgId(@QueryParam("mfg_id") String mfd_id) {
        int mfgId = Integer.parseInt(mfd_id);
        return cpFacade.findByMfgId(mfgId);
    }
}
