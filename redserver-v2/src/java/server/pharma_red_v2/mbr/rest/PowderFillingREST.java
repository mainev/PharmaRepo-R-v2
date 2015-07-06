/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.mbr.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
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
    @Inject
    private PowderFillingFacade facade;

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PowderFillingProcedure create(@QueryParam("mfg_id") String mfg_id, PowderFillingProcedure p) {
        int mfgId = Integer.parseInt(mfg_id);
        return facade.create(mfgId, p);
    }
}
