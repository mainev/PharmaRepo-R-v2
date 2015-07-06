/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2._main.rest;

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
import server.pharma_red_v2._main.entity.Classification;
import server.pharma_red_v2._main.facade.ClassificationFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("main/classification")
@RequestScoped
public class ClassificationREST {

    @Context
    private UriInfo context;

    @Inject
    private ClassificationFacade classificationFacade;

    public ClassificationREST() {
    }

    @GET
    @Produces("application/json")
    public List<Classification> getJson() {
        return classificationFacade.findAll();
    }

    /**
     * PUT method for updating or creating an instance of ClassificationREST
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
