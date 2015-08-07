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
import server.pharma_red_v2._main.entity.PackagingMaterial;
import server.pharma_red_v2._main.facade.PackagingMaterialFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("main/packaging_material")
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

    @GET
    @Path("/g_packg_material_list")
    @Produces("application/json")
    public List<PackagingMaterial> getPackgMaterialList() {
        return packagingMaterialFacade.findAll();
    }

}
