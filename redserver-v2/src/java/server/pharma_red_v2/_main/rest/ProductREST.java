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
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import server.pharma_red_v2._main.entity.Product;
import server.pharma_red_v2._main.facade.ProductFacade;
import server.pharma_red_v2.sqlsvr_copy.entity.Item;

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

    @Context
    private HttpServletResponse response;

    @Context
    private SecurityContext sc;

    @Inject
    private ProductFacade productFacade;

    /**
     * Creates a new instance of ProductREST
     */
    public ProductREST() {
    }

    @GET
    @Path("/g_product_list")
    @Produces("application/json")
    public List<Product> getProductList() {
        System.out.println(request.getMethod());
        System.out.println(request.getPathInfo());
        System.out.println(request.getRequestURI());

        response.setHeader("table_name", "product");

        return productFacade.findAll();
    }

    @GET
    @Path("/g_is_code_valid")
    public Boolean isCodeValid(@QueryParam("code") String code) {
        return productFacade.isCodeUnique(code);
    }

    @GET
    @Path("/g_primary_packg")
    @Produces("application/json")
    @Consumes("application/json")
    public Item getPrimaryPackaging(@QueryParam("productId") String productId) {
        Integer product_id = Integer.parseInt(productId);
        return productFacade.getPrimaryPackaging(product_id);
    }

    @GET
    @Path("/g_secondary_packg")
    @Produces("application/json")
    @Consumes("application/json")
    public Item getSecondaryPackaging(@QueryParam("productId") String productId) {
        Integer product_id = Integer.parseInt(productId);
        return productFacade.getSecondaryPackaging(product_id);
    }

    @GET
    @Path("/g_product_by_id")
    @Produces("application/json")
    public Product getProductById(@QueryParam("id") String id) {
        int productId = Integer.parseInt(id);

        return productFacade.findById(productId);
    }

    @POST
    @Path("/pst_new_product")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Product createNewProduct(Product product) {
        return productFacade.create(product);
    }

    @POST
    @Path("/pst_prim_sec_packg")
    public void createPrimarySecondaryPackg(@QueryParam("product_id") String product_id,
            @QueryParam("primary_id") String primary_id,
            @QueryParam("secondary_id") String secondary_id) {

        int productId = Integer.parseInt(product_id);
        int primaryId = Integer.parseInt(primary_id);
        int secondaryId = Integer.parseInt(secondary_id);
        productFacade.createPrimarySecondaryPackaging(productId, primaryId, secondaryId);
    }

}
