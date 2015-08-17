/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.rest_client.mbr;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import mbrinstant.entity.main.RawMaterial;
import mbrinstant.entity.main.Unit;
import mbrinstant.entity.mbr.RawMaterialRequirement;
import mbrinstant.exceptions.ServerException;
import mbrinstant.rest_client.HttpResponseHandler;
import mbrinstant.rest_client.SecureRestClientTrustManager;
import mbrinstant.utils.Serializer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 *
 * @author maine
 */
public class SingletonRawMaterialRequirementRestClient {

    private Client client;
    private WebResource webResource;
    private String BASE_URI = "https://localhost:8181/RedServer-v2/webresources/mbr/raw_material_requirement";
    private final HttpResponseHandler responseHandler = new HttpResponseHandler();
    private SSLContext sslContext = null;

    private SingletonRawMaterialRequirementRestClient() {

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
            Logger.getLogger(SingletonRawMaterialRequirementRestClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * DBMethodName : pst_new_raw_material_rew
     *
     * @param udfId
     * @param rmReq
     * @return
     */
    public RawMaterialRequirement createRawMaterialRequirement(int udfId, RawMaterialRequirement rmReq) throws ServerException {
        String input = Serializer.serialize(rmReq);
        webResource = client.resource(BASE_URI + "/pst_new_raw_material_req");
        ClientResponse response = webResource
                .queryParam("method", "pst_new_raw_material_req")
                .queryParam("udf_id", String.valueOf(udfId))
                .type("application/json")
                .post(ClientResponse.class, input);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            String output = response.getEntity(String.class);
            return Serializer.<RawMaterialRequirement>deserialize(output, RawMaterialRequirement.class);
        }
        return null;
    }

    /**
     * DBMethodName : pst_new_raw_material_rew
     *
     * @param udfId
     * @param rawMaterialId
     * @param quantity
     * @param unitId
     * @param part
     * @return
     */
    public RawMaterialRequirement createRawMaterialRequirement(int udfId, RawMaterial rawMaterialId, double quantity, Unit unitId, short part)
            throws ServerException {
        RawMaterialRequirement rmReq = new RawMaterialRequirement(rawMaterialId, quantity, unitId, part);

        String input = Serializer.serialize(rmReq);
        webResource = client.resource(BASE_URI + "/pst_new_raw_material_rew");
        ClientResponse response = webResource
                .queryParam("method", "pst_new_raw_material_rew")
                .queryParam("udf_id", String.valueOf(udfId))
                .type("application/json")
                .post(ClientResponse.class, input);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            String output = response.getEntity(String.class);
            return Serializer.<RawMaterialRequirement>deserialize(output, RawMaterialRequirement.class);
        }
        return null;

    }

    /**
     * DBMethodName: g_raw_material_req_by_udf_id
     *
     * @param udfId
     * @return
     */
    public ObservableList<RawMaterialRequirement> getRawMaterialRequirementByUdfId(int udfId) throws ServerException {

        webResource = client.resource(BASE_URI + "/g_raw_material_req_by_udf_id");
        ClientResponse response = webResource
                .queryParam("method", "g_raw_material_req_by_udf_id")
                .queryParam("udf_id", String.valueOf(udfId))
                .accept("application/json")
                .get(ClientResponse.class);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            String jsonResult = response.getEntity(String.class);
            return Serializer.<RawMaterialRequirement>deserializeList(jsonResult, RawMaterialRequirement.class);
        }
        return null;
    }

    /**
     * DBMethodName : g_raw_material_req_by_details
     *
     * @param rmId
     * @param qty
     * @param unitId
     * @param udfId
     * @return
     */
    public RawMaterialRequirement getRawMaterialRequirementByDetails(int rmId, double qty, short unitId, int udfId) throws ServerException {

        webResource = client.resource(BASE_URI + "/g_raw_material_req_by_details");
        ClientResponse response = webResource
                .queryParam("method", "g_raw_material_req_by_details")
                .queryParam("rm_id", String.valueOf(rmId))
                .queryParam("qty", String.valueOf(qty))
                .queryParam("unit_id", String.valueOf(unitId))
                .queryParam("udf_id", String.valueOf(udfId))
                .accept("application/json")
                .get(ClientResponse.class);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            String jsonResult = response.getEntity(String.class);
            return Serializer.<RawMaterialRequirement>deserialize(jsonResult, RawMaterialRequirement.class);
        }
        return null;
    }

    public static SingletonRawMaterialRequirementRestClient getInstance() {
        return SingletonRawMaterialRequirementRestClientHolder.INSTANCE;
    }

    private static class SingletonRawMaterialRequirementRestClientHolder {

        private static final SingletonRawMaterialRequirementRestClient INSTANCE = new SingletonRawMaterialRequirementRestClient();
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

    public void destroy() {
        client.removeAllFilters();
        client.destroy();
    }
}
