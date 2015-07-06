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
import javax.ws.rs.core.MediaType;
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

    @Inject
    private ManufacturingProcedureFacade manufacturingProcedureFacade;

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ManufacturingProcedure create(ManufacturingProcedure mp) {
        return manufacturingProcedureFacade.create(mp);
    }
}
