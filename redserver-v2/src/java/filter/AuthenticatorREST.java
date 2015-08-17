/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import server.pharma_red_v2.security.entity.User;
import server.pharma_red_v2.security.facade.UserFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("authenticate")
@RequestScoped
public class AuthenticatorREST {

    @Inject
    private UserFacade userFacade;

    private final AuthorizationManager authManager = new AuthorizationManager();

    @Context
    private SecurityContext sc;

    public AuthenticatorREST() {
    }

    @GET
    @Produces("application/json")
    public User authenticateUser(@QueryParam("emaid_ad") String emailAd, @QueryParam("pwd") String pwd) {
        //the user's password to be returned to the client is not visible
        return userFacade.findByUsername(emailAd);
    }

    @GET
    @Path("/permission")
    public Response isUserPermitted(@QueryParam("method") String method) {
        String username = sc.getUserPrincipal().getName();
        if (method != null && username != null) {
            User user = userFacade.findByUsername(username);
            if (authManager.isUserAuthorized(user, method) || user.isAdmin()) {
                System.out.println("user is permitted");
                return Response.status(200).build();
            } else {//user is forbidden
                return Response.status(403).build();
            }
        }
        return Response.status(400).build();

    }

}
