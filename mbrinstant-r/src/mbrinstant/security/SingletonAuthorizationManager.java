/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.security;

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
import mbrinstant.exceptions.ServerException;
import mbrinstant.rest_client.HttpResponseHandler;
import mbrinstant.rest_client.SecureRestClientTrustManager;
import mbrinstant.rest_client.mbr.SingletonBottlingProcedureRestClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 *
 * @author maine
 */
public class SingletonAuthorizationManager {

    private Client client;
    private WebResource webResource;
    private final String BASE_URI = "https://localhost:8181/RedServer-v2/webresources/authenticate";
    private final HttpResponseHandler responseHandler = new HttpResponseHandler();
    private SSLContext sslContext = null;
    private HTTPBasicAuthFilter authFilter;

    private SingletonAuthorizationManager() {

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
            Logger.getLogger(SingletonBottlingProcedureRestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isUserPermitted(String method) throws ServerException {
        webResource = client.resource(BASE_URI + "/permission");
        ClientResponse response = webResource
                .queryParam("method", method)
                .type("application/json")
                .get(ClientResponse.class);
        responseHandler.setCode(response.getStatus());
        return responseHandler.isSuccessful();
    }

    public static SingletonAuthorizationManager getInstance() {
        return SingletonAuthorizationManagerHolder.INSTANCE;
    }

    private static class SingletonAuthorizationManagerHolder {

        private static final SingletonAuthorizationManager INSTANCE = new SingletonAuthorizationManager();
    }

    public HttpResponseHandler getResponseHandler() {
        return responseHandler;
    }

    public void setUsernameAndPassword(String username, String password) {
        authFilter = new HTTPBasicAuthFilter(username, password);
        client.addFilter(authFilter);
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
