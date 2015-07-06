/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.mbr.rest;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import server.pharma_red_v2.mbr.entity.EquipmentRequirement;
import server.pharma_red_v2.mbr.facade.EquipmentRequirementFacade;

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

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EquipmentRequirement create(@QueryParam("mfg_id") String mfg_id, EquipmentRequirement er) {
        int mfgId = Integer.parseInt(mfg_id);
        return equipmentRequirementFacade.create(mfgId, er);
    }
    
}
