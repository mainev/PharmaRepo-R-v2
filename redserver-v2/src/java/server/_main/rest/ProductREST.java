/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server._main.rest;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import server._main.entity.PackagingMaterial;
import server._main.entity.Product;
import server._main.facade.ProductFacade;

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
        return productFacade.findAll();
    }
    
    @GET
    @Path("/getprimarypackaging")
    @Produces("application/json")
    @Consumes("application/json")
    public PackagingMaterial getPrimaryPackaging(@QueryParam("productId") String productId){
        Integer product_id = Integer.parseInt(productId);
        return productFacade.getPrimaryPackaging(product_id);
    }
    
     @GET
    @Path("/getsecondarypackaging")
    @Produces("application/json")
    @Consumes("application/json")
    public PackagingMaterial getSecondaryPackaging(@QueryParam("productId") String productId){
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
}
