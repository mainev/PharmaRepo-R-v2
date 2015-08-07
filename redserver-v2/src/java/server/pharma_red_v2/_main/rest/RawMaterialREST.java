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
import server.pharma_red_v2._main.entity.RawMaterial;
import server.pharma_red_v2._main.facade.RawMaterialFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("main/raw_material")
@RequestScoped
public class RawMaterialREST {

    @Context
    private UriInfo context;

    @Inject
    private RawMaterialFacade rawMaterialFacade;

    public RawMaterialREST() {
    }

    @GET
    @Path("/g_raw_material_list")
    @Produces("application/json")
    public List<RawMaterial> getRawMaterialList() {
        return rawMaterialFacade.findAll();
    }

}
