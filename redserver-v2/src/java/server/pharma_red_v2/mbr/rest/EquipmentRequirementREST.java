/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.mbr.rest;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
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

    @Context
    private HttpServletResponse response;

    @Inject
    private EquipmentRequirementFacade equipmentRequirementFacade;

    /**
     * Creates a new instance of EquipmentRequirementREST
     */
    public EquipmentRequirementREST() {
    }

    @GET
    @Produces("application/json")
    @Path("/g_find_by_mfg_id_and_procedure")
    public List<EquipmentRequirement> getEquipmentReqByMfgIdAndProcedureType(@QueryParam("mfgId") String mfgId, @QueryParam("procedure") String procedure) {
        int manufacturingId = Integer.parseInt(mfgId);

        return equipmentRequirementFacade.findAllByManufacturingIdAndProcedure(manufacturingId, procedure);
    }

    @POST
    @Path("/pst_create_new_equip_req")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EquipmentRequirement createEquipmentRequirement(@QueryParam("mfg_id") String mfg_id, EquipmentRequirement er) {
        response.setHeader("old_value", "");
        response.setHeader("table_name", "equipment_requirement");
        response.setHeader("action", "INSERT");

        int mfgId = Integer.parseInt(mfg_id);
        return equipmentRequirementFacade.create(mfgId, er);
    }

}
