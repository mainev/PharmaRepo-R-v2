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
import server.pharma_red_v2._main.entity.Client;
import server.pharma_red_v2._main.facade.ClientFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("main/client")
@RequestScoped
public class ClientREST {

    @Context
    private UriInfo context;

   @Inject
   private ClientFacade clientFacade;
   
    public ClientREST() {
    }

    /**
     * Retrieves representation of an instance of server._main.rest.ClientREST
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public List<Client> getJson() {
        return clientFacade.findAll();
    }

    /**
     * PUT method for updating or creating an instance of ClientREST
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
