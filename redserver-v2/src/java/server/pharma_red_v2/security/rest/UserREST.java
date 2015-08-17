package server.pharma_red_v2.security.rest;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import server.pharma_red_v2.security.entity.User;
import server.pharma_red_v2.security.facade.UserFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("security/user")
@RequestScoped
public class UserREST {

    @Context
    private UriInfo context;

    @Context
    private SecurityContext sc;
    @Inject
    private UserFacade userFacade;

    /**
     * Creates a new instance of LoginREST
     */
    public UserREST() {
    }
    /*

     @GET
     @Path("/login")
     @Produces("application/json")
     @Consumes("application/json")
     public User loginUser(@QueryParam("emaid_ad") String emailAd, @QueryParam("pwd") String pwd) {
     return userFacade.findUserByEmailAndPassword(emailAd, pwd);
     }
     */

    @GET
    @Path("/g_user_list")
    @Produces("application/json")
    public List<User> getAllUsers() {
        return userFacade.findAll();
    }

}
