/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.mbr.rest;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import server.pharma_red_v2.mbr.entity.RawMaterialRequirement;
import server.pharma_red_v2.mbr.facade.RawMaterialRequirementFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("mbr/raw_material_requirement")
@RequestScoped
public class RawMaterialRequirementREST {

    @Context
    private UriInfo context;

    @Inject
    private RawMaterialRequirementFacade rmReqFacade;

    public RawMaterialRequirementREST() {
    }

    @GET
    @Produces("application/json")
    public List<RawMaterialRequirement> findAll() {
        return rmReqFacade.findAll();
    }

    @POST
    @Path("/pst_new_raw_material_rew")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RawMaterialRequirement createRawMaterialRequirement(@QueryParam("udf_id") String udf_id, RawMaterialRequirement rmReq) {
        int udfId = Integer.parseInt(udf_id);
        return rmReqFacade.create(udfId, rmReq);
    }

    @GET
    @Path("/g_raw_material_req_by_udf_id")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<RawMaterialRequirement> getRawMaterialRequirementByUdfId(@QueryParam("udf_id") String udf_id) {
        int udfId = Integer.parseInt(udf_id);
        return rmReqFacade.findByUdfId(udfId);
    }

    @GET
    @Path("/g_raw_material_req_by_details")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RawMaterialRequirement getRawMaterialRequirementByDetails(@QueryParam("rm_id") String rm_id,
            @QueryParam("qty") String qty,
            @QueryParam("unit_id") String unit_id,
            @QueryParam("udf_id") String udf_id) {
        int udfId = Integer.parseInt(udf_id);
        int rmId = Integer.parseInt(rm_id);
        double quantity = Double.parseDouble(qty);
        short unitId = Short.parseShort(unit_id);

        return rmReqFacade.findByDetails(rmId, quantity, unitId, udfId);
    }
}
