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
import javax.ws.rs.core.MediaType;
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
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public List<Mbr> findAll() {
       return mbrFacade.findAll();
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mbr create(Mbr mbr) {
       return mbrFacade.create(mbr);
    }
}
