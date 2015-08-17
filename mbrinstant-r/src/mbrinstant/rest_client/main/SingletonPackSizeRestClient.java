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
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import mbrinstant.entity.main.Container;
import mbrinstant.entity.main.PackSize;
import mbrinstant.entity.main.Unit;
import mbrinstant.exceptions.ServerException;
import mbrinstant.rest_client.HttpResponseHandler;
import mbrinstant.rest_client.SecureRestClientTrustManager;
import mbrinstant.utils.Serializer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 *
 * @author maine
 */
public class SingletonPackSizeRestClient {

    private Client client;
    private WebResource webResource;
    private final String BASE_URI = "https://localhost:8181/RedServer-v2/webresources/main/pack_size";
    private final HttpResponseHandler responseHandler = new HttpResponseHandler();
    private SSLContext sslContext = null;

    private SingletonPackSizeRestClient() {

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
            Logger.getLogger(SingletonPackSizeRestClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * *
     * DBMethodName : pst_new_pack_size Creates a new pack size in the database
     * whenever the result is empty.
     *
     * @param quantity
     * @param unitId
     * @param containerId
     * @return Returns the instance of the pack size from the database.
     */
    public PackSize createNewPackSize(double quantity, Unit unitId, Container containerId) throws ServerException {
        PackSize packSizeId = new PackSize(quantity, containerId, unitId);
        String jsonInput = Serializer.serialize(packSizeId);
        webResource = client.resource(BASE_URI + "/pst_new_pack_size");
        ClientResponse response = webResource
                .queryParam("method", "pst_new_pack_size")
                .type("application/json").post(ClientResponse.class, jsonInput);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            String jsonResult = response.getEntity(String.class);
            return Serializer.<PackSize>deserialize(jsonResult, PackSize.class);
        }
        return null;
    }

    public static SingletonPackSizeRestClient getInstance() {
        return SingletonPackSizeRestClientHolder.INSTANCE;
    }

    private static class SingletonPackSizeRestClientHolder {

        private static final SingletonPackSizeRestClient INSTANCE = new SingletonPackSizeRestClient();
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
