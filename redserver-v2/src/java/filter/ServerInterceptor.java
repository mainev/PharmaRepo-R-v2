/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;
import server.pharma_red_v2.audit.AuditFacade;
import server.pharma_red_v2.audit.AuditTrail;

/**
 *
 * @author maine
 */
@Provider
public class ServerInterceptor implements ReaderInterceptor, WriterInterceptor {

    @Override
    public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException {
        System.out.println("INTERCEPTOR:" + context.getInputStream());
        return context.proceed();
    }

    @Context
    private HttpServletResponse response;

    @Context
    private HttpServletRequest request;

    @Context
    private SecurityContext secu;

    @EJB
    private AuditFacade auditFacade;

    @Override
    public void aroundWriteTo(WriterInterceptorContext responseContext) throws IOException {

        //   System.out.println("INTERCEPTOR(WRITE): responseEntity" + responseContext.getEntity());
//        System.out.println("Status: " + response.getStatus());
//        System.out.println("Table:" + response.getHeader("table_name"));
//        System.out.println("User:" + secu.getUserPrincipal().getName());
//        System.out.println("Method: " + request.getMethod());
//
//
        if (request.getMethod().equals("POST")) {
            System.out.println("\nIntercepting POST request");
            if (response.getStatus() == HttpServletResponse.SC_OK) {
                AuditTrail audit = new AuditTrail();
                audit.setDatetime(new Date());
                audit.setTableName(response.getHeader("table_name"));
                audit.setUsername(secu.getUserPrincipal().getName());
                audit.setMethod(request.getPathInfo());
                audit.setAction(response.getHeader("action"));

                if (!response.getHeader("action").equals("DELETE")) {
                    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                    String json = gson.toJson(responseContext.getEntity());
                    System.out.println(json);
                    audit.setNewValue(json);
                    audit.setOldValue(response.getHeader("old_value"));
                } else {
                    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                    String json = gson.toJson(responseContext.getEntity());
                    audit.setOldValue(json);
                    audit.setNewValue("");
                }
                auditFacade.insert(audit);

            }
            System.out.println("\nAUDIT RECORDED");

        }

        responseContext.proceed();
    }

}
