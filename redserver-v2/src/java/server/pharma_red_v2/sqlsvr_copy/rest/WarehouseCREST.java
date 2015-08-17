/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.sqlsvr_copy.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import server.pharma_red_v2.sqlsvr_copy.facade.WarehouseCFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("sqlsvr_copy/warehouse_c")
@RequestScoped
public class WarehouseCREST {

    @Context
    private UriInfo context;

    @Inject
    private WarehouseCFacade facade;

    public WarehouseCREST() {
    }

}
