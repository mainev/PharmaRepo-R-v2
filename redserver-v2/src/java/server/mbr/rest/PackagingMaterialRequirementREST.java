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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import server.mbr.entity.PackagingMaterialRequirement;
import server.mbr.facade.PackagingMaterialRequirementFacade;

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

    @Inject
    private PackagingMaterialRequirementFacade pmReqFacade;

    /**
     * Creates a new instance of RawMaterialRequirementREST
     */
    public PackagingMaterialRequirementREST() {
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PackagingMaterialRequirement create(@QueryParam("udf_id") String udf_id, PackagingMaterialRequirement pmReq) {
        int udfId = Integer.parseInt(udf_id);
        return pmReqFacade.create(udfId, pmReq);
    }
    
    @GET
    @Path("/find_by_udf_id")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<PackagingMaterialRequirement> findByUdfId(@QueryParam("udf_id") String udf_id) {
        int udfId = Integer.parseInt(udf_id);
        return pmReqFacade.findByUdfId(udfId);
    }
}
