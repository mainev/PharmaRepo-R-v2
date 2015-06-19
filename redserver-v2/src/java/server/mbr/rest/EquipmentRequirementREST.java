/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.mbr.rest;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import server.mbr.entity.EquipmentRequirement;
import server.mbr.facade.EquipmentRequirementFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("mbr/equipment_requirement")
@RequestScoped
public class EquipmentRequirementREST {

    @Context
    private UriInfo context;
    
    @Inject
    private EquipmentRequirementFacade equipmentRequirementFacade;

    /**
     * Creates a new instance of EquipmentRequirementREST
     */
    public EquipmentRequirementREST() {
    }

    /**
     * Retrieves representation of an instance of server.mbr.rest.EquipmentRequirementREST
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public List<EquipmentRequirement> getJson() {
       return equipmentRequirementFacade.findAll();
    }
    
    @GET
    @Produces("application/json")
    @Path("/find_by_mfg_id_and_procedure")
    public List<EquipmentRequirement> findByMfgIdAndProcedure(@QueryParam("mfgId") String mfgId, @QueryParam("procedure") String procedure) {
        int manufacturingId = Integer.parseInt(mfgId);

        return equipmentRequirementFacade.findAllByManufacturingIdAndProcedure(manufacturingId, procedure);
    }

    /**
     * PUT method for updating or creating an instance of EquipmentRequirementREST
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
