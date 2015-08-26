/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2._main.rest;

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
import server.pharma_red_v2._main.entity.PackSize;
import server.pharma_red_v2._main.facade.PackSizeFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("main/pack_size")
@RequestScoped
public class PackSizeREST {

    @Context
    private UriInfo context;

    @Inject
    private PackSizeFacade packSizeFacade;

    @Context
    private HttpServletResponse response;

    @POST
    @Path("/pst_new_pack_size")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PackSize createNewPackSize(PackSize packSizeId) {
        response.setHeader("old_value", "");
        response.setHeader("table_name", "pack_size");
        response.setHeader("action", "INSERT");
        return packSizeFacade.create(packSizeId);
    }
}
