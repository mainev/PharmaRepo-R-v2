/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2._main.rest;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import server.pharma_red_v2._main.entity.Container;
import server.pharma_red_v2._main.facade.ContainerFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("main/container")
@RequestScoped
public class ContainerREST {

    @Context
    private UriInfo context;

    @Inject
    private ContainerFacade containerFacade;

    public ContainerREST() {
    }

    @GET
    @Path("/g_container_list")
    @Produces("application/json")
    public List<Container> getContainerList() {
        return containerFacade.findAll();
    }

}
