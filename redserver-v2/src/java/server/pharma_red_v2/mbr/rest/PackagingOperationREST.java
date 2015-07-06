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
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import server.pharma_red_v2.mbr.entity.PackagingOperation;
import server.pharma_red_v2.mbr.facade.PackagingOperationFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("mbr/packaging_operation")
@RequestScoped
public class PackagingOperationREST {

    @Context
    private UriInfo context;
    @Inject
    private PackagingOperationFacade packagingOperationFacade;

    /**
     * Creates a new instance of PackagingOperationREST
     */
    public PackagingOperationREST() {
    }

    @GET
    @Produces("application/json")
    public List<PackagingOperation> getJson() {
        return packagingOperationFacade.findAll();
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PackagingOperation create(@QueryParam("mfg_id") String mfg_id, PackagingOperation ppo) {
        int mfgId = Integer.parseInt(mfg_id);
        return packagingOperationFacade.create(mfgId, ppo);
    }
}