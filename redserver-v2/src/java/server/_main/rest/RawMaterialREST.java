/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server._main.rest;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import server._main.entity.RawMaterial;
import server._main.facade.RawMaterialFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("main/rawmaterial")
@RequestScoped
public class RawMaterialREST {

    @Context
    private UriInfo context;
    
     @Inject
    private RawMaterialFacade rawMaterialFacade;

    /**
     * Creates a new instance of RawMaterialREST
     */
    public RawMaterialREST() {
    }

    /**
     * Retrieves representation of an instance of server._main.RawMaterialREST
     * @return an instance of java.lang.String
     */
     @GET
    @Produces("application/json")
    public List<RawMaterial> findAll() {
        return rawMaterialFacade.findAll();
    }

    /**
     * PUT method for updating or creating an instance of RawMaterialREST
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
