/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.sample;

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

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("sample/user2")
@RequestScoped
public class User2REST {

    @Context
    private UriInfo context;

    /*
    @Inject
    private User2Facade facade;
    
    public User2REST() {
    }

    @GET
    @Produces("application/json")
    public List<SampleUser> getJson() {
        return facade.findAll();
    }

    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
    
    */
}
