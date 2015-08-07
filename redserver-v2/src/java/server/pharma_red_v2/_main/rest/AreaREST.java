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
import server.pharma_red_v2._main.entity.Area;
import server.pharma_red_v2._main.facade.AreaFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("main/area")
@RequestScoped
public class AreaREST {

    @Context
    private UriInfo context;

    @Inject
    private AreaFacade areaFacade;

    /**
     * Creates a new instance of AreaREST
     */
    public AreaREST() {
    }

    @GET
    @Path("/g_area_list")
    @Produces("application/json")
    public List<Area> getAreaList() {
        return areaFacade.findAll();
    }

}
