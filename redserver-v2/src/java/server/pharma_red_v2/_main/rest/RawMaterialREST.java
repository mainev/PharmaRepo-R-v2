/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2._main.rest;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import server.pharma_red_v2._main.entity.RawMaterial;
import server.pharma_red_v2._main.facade.RawMaterialFacade;
import server.sqlsvr.Nutratech_DB.facade.ItemFacade;

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
    @Produces("application/json")
    public List<RawMaterial> findAll() {
        return rawMaterialFacade.findAll();
    }

}
