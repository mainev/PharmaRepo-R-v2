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

    public AuthenticatorREST() {
    }

    @GET
    @Produces("application/json")
    public User authenticateUser(@QueryParam("emaid_ad") String emailAd, @QueryParam("pwd") String pwd) {
        //the user's password to be returned to the client is not visible
        return userFacade.findByUsername(emailAd);

    }

}
