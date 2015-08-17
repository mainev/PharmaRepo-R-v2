/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.rest_client.main;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import mbrinstant.entity.main.Area;
import mbrinstant.entity.main.Classification;
import mbrinstant.entity.main.PackSize;
import mbrinstant.entity.main.PackagingMaterial;
import mbrinstant.entity.main.Product;
import mbrinstant.exceptions.ServerException;
import mbrinstant.rest_client.HttpResponseHandler;
import mbrinstant.rest_client.SecureRestClientTrustManager;
import mbrinstant.utils.Serializer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 *
 * @author maine
 */
public class SingletonProductRestClient {

    private Client client;
    private WebResource webResource;
    private final String BASE_URI = "https://localhost:8181/RedServer-v2/webresources/main/product";
    private final HttpResponseHandler responseHandler = new HttpResponseHandler();
    SSLContext sslContext = null;

    private SingletonProductRestClient() {

        try {
            SecureRestClientTrustManager secureRestClientTrustManager = new SecureRestClientTrustManager();
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new javax.net.ssl.TrustManager[]{secureRestClientTrustManager}, null);
            DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
            defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
            defaultClientConfig.getProperties().put(
                    com.sun.jersey.client.urlconnection.HTTPSProperties.PROPERTY_HTTPS_PROPERTIES,
                    new com.sun.jersey.client.urlconnection.HTTPSProperties(
                            getHostnameVerifier(), sslContext));

            client = Client.create(defaultClientConfig);
        } catch (NoSuchAlgorithmException | KeyManagementException ex) {
            Logger.getLogger(SingletonProductRestClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * DBMethodName: g_product_list
     *
     * @return
     */
    public ObservableList<Product> getProductList() throws ServerException {
        ObservableList<Product> productList = FXCollections.observableArrayList();
        webResource = client.resource(BASE_URI + "/g_product_list");
        ClientResponse response = webResource
                .queryParam("method", "g_product_list")
                .accept("application/json").get(ClientResponse.class);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            String jsonOutput = response.getEntity(String.class);
            productList = Serializer.<Product>deserializeList(jsonOutput, Product.class);
        }

        return productList;
    }

    /**
     * DBMethodName: g_is_code_valid
     *
     * @param code
     * @return
     * @throws mbrinstant.exceptions.ServerException
     */
    public boolean isCodeValid(String code) throws ServerException {
        webResource = client.resource(BASE_URI + "/g_is_code_valid");

        ClientResponse response = webResource
                .queryParam("method", "g_is_code_valid")
                .queryParam("code", code)
                .get(ClientResponse.class);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            String jsonResult = response.getEntity(String.class);
            return jsonResult.equals("true");
        }

        return false;
    }

    /**
     * DBMethodName: g_product_by_id
     *
     * @param id
     * @return
     */
    public Product getProductById(int id) throws ServerException {
        webResource = client.resource(BASE_URI + "/g_product_by_id");

        ClientResponse response = webResource
                .queryParam("method", "g_product_by_id")
                .queryParam("id", String.valueOf(id))
                .accept("application/json")
                .get(ClientResponse.class);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            String jsonResult = response.getEntity(String.class);
            return Serializer.<Product>deserialize(jsonResult, Product.class);
        }

        return null;
    }

    /**
     * DBMethodName: g_primary_packg
     *
     * @param productId
     * @return
     */
    public PackagingMaterial getPrimaryPackaging(Product productId) throws ServerException {
        webResource = client.resource(BASE_URI + "/g_primary_packg");
        ClientResponse response = webResource
                .queryParam("method", "g_primary_packg")
                .queryParam("productId", String.valueOf(productId.getId()))
                .accept("application/json")
                .get(ClientResponse.class);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            String jsonOutput = response.getEntity(String.class);
            return Serializer.<PackagingMaterial>deserialize(jsonOutput, PackagingMaterial.class);
        }

        return null;

    }

    /**
     * DBMethodName: g_secondary_packg
     *
     * @param productId
     * @return
     */
    public PackagingMaterial getSecondaryPackaging(Product productId) throws ServerException {
        webResource = client.resource(BASE_URI + "/g_secondary_packg");
        ClientResponse response = webResource
                .queryParam("method", "g_secondary_packg")
                .queryParam("productId", String.valueOf(productId.getId()))
                .accept("application/json")
                .get(ClientResponse.class);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            String jsonOutput = response.getEntity(String.class);
            return Serializer.<PackagingMaterial>deserialize(jsonOutput, PackagingMaterial.class);
        }
        return null;

    }

    /**
     * DBMethodName: pst_new_product
     *
     * @param code
     * @param brandName
     * @param genericName
     * @param vrNo
     * @param shelfLife
     * @param areaId
     * @param classificationId
     * @param clientId
     * @param packSizeId
     * @return
     */
    public Product createNewProduct(String code, String brandName, String genericName, String vrNo,
            Short shelfLife, Area areaId, Classification classificationId, mbrinstant.entity.main.Company clientId, PackSize packSizeId) throws ServerException {
        Product p = new Product(code, brandName, genericName, vrNo, shelfLife, areaId, classificationId, clientId, packSizeId);
        String input = Serializer.serialize(p);
        webResource = client.resource(BASE_URI + "/pst_new_product");
        ClientResponse response = webResource
                .queryParam("method", "pst_new_product")
                .type("application/json")
                .post(ClientResponse.class, input);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            String output = response.getEntity(String.class);
            return Serializer.<Product>deserialize(output, Product.class);
        }

        return null;

    }

    /**
     * DBMethodName: pst_prim_sec_packg
     *
     * @param productId
     * @param primaryId
     * @param secondaryId
     */
    public void createPrimarySecondaryPackg(int productId, int primaryId, int secondaryId) throws ServerException {

        webResource = client.resource(BASE_URI + "/pst_prim_sec_packg");
        ClientResponse response = webResource
                .queryParam("method", "pst_prim_sec_packg")
                .queryParam("product_id", String.valueOf(productId))
                .queryParam("primary_id", String.valueOf(primaryId))
                .queryParam("secondary_id", String.valueOf(secondaryId))
                .type("application/json")
                .post(ClientResponse.class);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            System.out.println("Primary and secondary packg material successfully created.");
        }

    }

    public HttpResponseHandler getResponseHandler() {
        return responseHandler;
    }

    public void setUsernameAndPassword(String username, String password) {
        client.addFilter(new HTTPBasicAuthFilter(username, password));
    }

    public SSLContext getSSLContext() {
        // for alternative implementation checkout org.glassfish.jersey.SslConfigurator
        javax.net.ssl.TrustManager x509 = new javax.net.ssl.X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws java.security.cert.CertificateException {
                return;
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws java.security.cert.CertificateException {
                return;
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        SSLContext ctx = null;
        try {
            ctx = SSLContext.getInstance("SSL");
            ctx.init(null, new javax.net.ssl.TrustManager[]{x509}, null);
        } catch (java.security.GeneralSecurityException ex) {
        }
        return ctx;
    }

    public HostnameVerifier getHostnameVerifier() {
        return new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
                return true;
            }
        };
    }

    public static SingletonProductRestClient getInstance() {
        return SingletonProductRestClientHolder.INSTANCE;
    }

    private static class SingletonProductRestClientHolder {

        private static final SingletonProductRestClient INSTANCE = new SingletonProductRestClient();
    }

    public void destroy() {
        client.removeAllFilters();
        client.destroy();
    }
}
