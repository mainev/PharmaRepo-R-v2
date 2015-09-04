/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.mbr.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import server.pharma_red_v2.mbr.entity.BatchItemRequirement;
import server.pharma_red_v2.mbr.facade.BatchItemRequirementFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("mbr/batch_item_requirement")
@RequestScoped
public class BatchItemRequirementREST {

    @Context
    private UriInfo context;

    @Inject
    private BatchItemRequirementFacade batchItemReqFacade;

    public BatchItemRequirementREST() {
    }

    @POST
    @Path("/pst_new_batch_item_req")
    @Produces("application/json")
    public BatchItemRequirement saveBatchItemRequirement(@QueryParam("batch_id") String batch_id, BatchItemRequirement batchItemReq) {
        return batchItemReqFacade.insert(Integer.parseInt(batch_id), batchItemReq);
    }

    @POST
    @Path("/pst_update_batch_item_req_status")
    public void updateBatchItemRequirementStatus(@QueryParam("batch_item_req_id") String bir_id, @QueryParam("item_req_stat") String item_req_stat) {
        batchItemReqFacade.updateItemReqStatus(Integer.parseInt(bir_id), item_req_stat);
    }

}
