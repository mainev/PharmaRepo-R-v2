/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2._main.rest;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import server.pharma_red_v2._main.entity.PackagingMaterial;
import server.pharma_red_v2._main.entity.Product;
import server.pharma_red_v2._main.facade.ProductFacade;

/**
 * REST Web Service
 *
 * @author maine
 */
@Path("main/product")
@RequestScoped
public class ProductREST {

    @Context
    private UriInfo context;
    
    @Context
    private HttpServletRequest request;

    @Inject
    private ProductFacade productFacade;

    /**
     * Creates a new instance of ProductREST
     */
    public ProductREST() {
    }

    /**
     * Retrieves representation of an instance of server._main.rest.ProductREST
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public List<Product> findAll() {
        System.out.println("context: "+context);
        System.out.println("context: "+context.getBaseUri());
             System.out.println("requestURL: "+request.getRequestURL());
              System.out.println("remoteHost: "+request.getRemoteHost());
        return productFacade.findAll();
    }

    @GET
    @Path("/getprimarypackaging")
    @Produces("application/json")
    @Consumes("application/json")
    public PackagingMaterial getPrimaryPackaging(@QueryParam("productId") String productId) {
        Integer product_id = Integer.parseInt(productId);
        return productFacade.getPrimaryPackaging(product_id);
    }

    @GET
    @Path("/getsecondarypackaging")
    @Produces("application/json")
    @Consumes("application/json")
    public PackagingMaterial getSecondaryPackaging(@QueryParam("productId") String productId) {
        Integer product_id = Integer.parseInt(productId);
        return productFacade.getSecondaryPackaging(product_id);
    }

    @GET
    @Produces("application/json")
    @Path("/find_by_id")
    public Product findById(@QueryParam("id") String id) {
        int productId = Integer.parseInt(id);

        return productFacade.findById(productId);
    }

    @GET
    @Path("/codevalidation")
    public Boolean isCodeUnique(@QueryParam("code") String code) {
        return productFacade.isCodeUnique(code);
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Product create(Product product) {

        return productFacade.create(product);
    }

    @POST
    @Path("/create_primary_secondary_packaging")
    public void create(@QueryParam("product_id") String product_id,
            @QueryParam("primary_id") String primary_id,
            @QueryParam("secondary_id") String secondary_id) {

        int productId = Integer.parseInt(product_id);
        int primaryId = Integer.parseInt(primary_id);
        int secondaryId = Integer.parseInt(secondary_id);
        productFacade.createPrimarySecondaryPackaging(productId, primaryId, secondaryId);
    }

}
