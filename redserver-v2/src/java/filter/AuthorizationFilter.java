/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import server.pharma_red_v2.security.entity.User;
import server.pharma_red_v2.security.facade.UserFacade;

/**
 *
 * @author maine This filter intercepts all request in /webresources/* to handle
 * method authorization.
 */
public class AuthorizationFilter implements Filter {

    private HttpServletRequest request;
    private HttpServletResponse response;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
        System.out.println("finalized!!!!!!!!!!");
    }
    @EJB
    UserFacade userFacade;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        this.request = (HttpServletRequest) request;
        this.response = (HttpServletResponse) response;

        System.out.println("FILTERING USER CREDENTIALS....");
        System.out.println("**************************************");
        if (this.request.getHeader("authorization") != null) {

            String userName = this.request.getUserPrincipal().getName();
            String methodName = this.request.getPathInfo();

            User user = userFacade.findByUsername(userName);

            AuthorizationManager authManager = new AuthorizationManager();
            //all USERS are filtered except for ADMIN
            if (authManager.isUserAuthorized(user, methodName) || user.isAdmin()) {
                System.out.println("User is authorized to use " + this.request.getPathInfo());
                chain.doFilter(request, response);
            } else {
                //if user is not authorized ....
                this.response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                System.out.println("USER IS NOT AUTHORIZED TO USE " + methodName);
            }
        }
        System.out.println("**************************************");

    }

    @Override
    public void destroy() {
        System.out.println("destroyed");
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

}
