/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.audit;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("audit/audit")
@RequestScoped
public class AuditREST {

    @Context
    private UriInfo context;

    @Inject
    private AuditFacade auditFacade;

    /**
     * Creates a new instance of AuditREST
     */
    public AuditREST() {
    }

    @GET
    @Produces("application/json")
    @Path("/g_audit_list")
    public List<AuditTrail> getAuditList() {
        return auditFacade.findAll();
    }
}
