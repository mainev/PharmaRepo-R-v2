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
import server.pharma_red_v2.mbr.entity.PackagingMaterialRequirement;
import server.pharma_red_v2.mbr.facade.PackagingMaterialRequirementFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("mbr/packaging_material_requirement")
@RequestScoped
public class PackagingMaterialRequirementREST {

    @Context
    private UriInfo context;

    @Context
    private HttpServletResponse response;

    @Inject
    private PackagingMaterialRequirementFacade pmReqFacade;

    /**
     * Creates a new instance of RawMaterialRequirementREST
     */
    public PackagingMaterialRequirementREST() {
    }

    @POST
    @Path("/pst_packg_material_req")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PackagingMaterialRequirement createPackgMaterialRequirement(@QueryParam("udf_id") String udf_id, PackagingMaterialRequirement pmReq) {

        response.setHeader("old_value", "");
        response.setHeader("table_name", "packaging_material_requirement");
        response.setHeader("action", "INSERT");
        int udfId = Integer.parseInt(udf_id);
        return pmReqFacade.create(udfId, pmReq);
    }

    @GET
    @Path("/g_packg_material_req_by_udf_id")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<PackagingMaterialRequirement> getPackgMaterialReqByUdfId(@QueryParam("udf_id") String udf_id) {
        int udfId = Integer.parseInt(udf_id);
        return pmReqFacade.findByUdfId(udfId);
    }

    @GET
    @Path("/g_packg_material_req_by_details")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PackagingMaterialRequirement getPackgMaterialReqByUdfIdAndDetails(@QueryParam("pm_id") String pm_id,
            @QueryParam("qty") String qty,
            @QueryParam("unit_id") String unit_id,
            @QueryParam("udf_id") String udf_id) {
        int udfId = Integer.parseInt(udf_id);
        int pmId = Integer.parseInt(pm_id);
        double quantity = Double.parseDouble(qty);
        short unitId = Short.parseShort(unit_id);

        return pmReqFacade.findByDetails(pmId, quantity, unitId, udfId);
    }
}
