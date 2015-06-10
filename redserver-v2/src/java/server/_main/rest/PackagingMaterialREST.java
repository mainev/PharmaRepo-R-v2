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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import server._main.entity.PackagingMaterial;
import server._main.facade.PackagingMaterialFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("main/packagingmaterial")
@RequestScoped
public class PackagingMaterialREST {

    @Context
    private UriInfo context;

    @Inject
    private PackagingMaterialFacade packagingMaterialFacade;

    /**
     * Creates a new instance of PackagingMaterialREST
     */
    public PackagingMaterialREST() {
    }

    /**
     * Retrieves representation of an instance of
     * server._main.rest.PackagingMaterialREST
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public List<PackagingMaterial> findAll() {
        return packagingMaterialFacade.findAll();
    }

    /**
     * PUT method for updating or creating an instance of PackagingMaterialREST
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
