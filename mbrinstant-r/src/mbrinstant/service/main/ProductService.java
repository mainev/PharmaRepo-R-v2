/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.service.main;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import javafx.collections.ObservableList;
import mbrinstant.entity.main.Area;
import mbrinstant.entity.main.Classification;
import mbrinstant.entity.main.PackSize;
import mbrinstant.entity.main.PackagingMaterial;
import mbrinstant.entity.main.Product;
import mbrinstant.entity.mbr.Udf;
import mbrinstant.utils.Serializer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 *
 * @author maine
 */
public class ProductService {

    public DefaultClientConfig defaultClientConfig;
    public Client client;
    public WebResource webResource;
    public final String BASE_URI = "http://localhost:8080/RedServer-v2/webresources/main/product";

    public ProductService() {
        initClient();
    }

    private void initClient() {
        defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        client = Client.create(defaultClientConfig);
    }

    public boolean isCodeUnique(String code) {
        webResource = client.resource(BASE_URI + "/codevalidation");

        ClientResponse response = webResource
                .queryParam("code", code)
                .get(ClientResponse.class);

        String jsonResult = response.getEntity(String.class);

        return jsonResult.equals("true");
    }

    public Product getProductById(int id) {
        webResource = client.resource(BASE_URI + "/find_by_id");

        ClientResponse response = webResource
                .queryParam("id", String.valueOf(id))
                .accept("application/json")
                .get(ClientResponse.class);

        String jsonResult = response.getEntity(String.class);
        return Serializer.<Product>deserialize(jsonResult, Product.class);
    }

    public ObservableList<Product> getProductList() {
        webResource = client.resource(BASE_URI);
        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);
        return Serializer.<Product>deserializeList(jsonOutput, Product.class);
    }

    public PackagingMaterial getPrimaryPackaging(Product productId) {
        webResource = client.resource(BASE_URI + "/getprimarypackaging");
        ClientResponse response = webResource
                .queryParam("productId", String.valueOf(productId.getId()))
                .accept("application/json")
                .get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);
        return Serializer.<PackagingMaterial>deserialize(jsonOutput, PackagingMaterial.class);
    }

    public PackagingMaterial getSecondaryPackaging(Product productId) {
        webResource = client.resource(BASE_URI + "/getsecondarypackaging");
        ClientResponse response = webResource
                .queryParam("productId", String.valueOf(productId.getId()))
                .accept("application/json")
                .get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);
        return Serializer.<PackagingMaterial>deserialize(jsonOutput, PackagingMaterial.class);
    }

    public Product create(String code, String brandName, String genericName, String vrNo,
            Short shelfLife, Area areaId, Classification classificationId, mbrinstant.entity.main.Client clientId, PackSize packSizeId) {
        Product p = new Product(code, brandName, genericName, vrNo, shelfLife, areaId, classificationId, clientId, packSizeId);
        String input = Serializer.serialize(p);
        webResource = client.resource(BASE_URI + "/create");
        ClientResponse response = webResource
                .type("application/json")
                .post(ClientResponse.class, input);
        String output = response.getEntity(String.class);
        return Serializer.<Product>deserialize(output, Product.class);

    }

    public void createPrimarySecondaryPackaging(int productId, int primaryId, int secondaryId) {

        webResource = client.resource(BASE_URI + "/create_primary_secondary_packaging");
        webResource
                .queryParam("product_id", String.valueOf(productId))
                .queryParam("primary_id", String.valueOf(primaryId))
                .queryParam("secondary_id", String.valueOf(secondaryId))
                .type("application/json")
                .post(ClientResponse.class);

    }
}
