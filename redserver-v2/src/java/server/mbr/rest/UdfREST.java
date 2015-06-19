/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.mbr.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import server.mbr.entity.Udf;
import server.mbr.facade.UdfFacade;

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

    /**
     * Creates a new instance of UdfREST
     */
    public UdfREST() {
    }

    @GET
    @Produces("application/json")
    @Path("/find_by_id")
    public Udf findById(@QueryParam("id") String id) {
        int udfId = Integer.parseInt(id);

        return udfFacade.findById(udfId);
    }

}
