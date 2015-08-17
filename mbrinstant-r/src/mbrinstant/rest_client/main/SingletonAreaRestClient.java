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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import mbrinstant.entity.main.Area;
import mbrinstant.exceptions.ServerException;
import mbrinstant.rest_client.HttpResponseHandler;
import mbrinstant.rest_client.SecureRestClientTrustManager;
import mbrinstant.utils.Serializer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 *
 * @author maine
 */
public class SingletonAreaRestClient {

    private Client client;
    private WebResource webResource;
    private final String BASE_URI = "https://localhost:8181/RedServer-v2/webresources/main/area";
    private final HttpResponseHandler responseHandler = new HttpResponseHandler();
    SSLContext sslContext = null;

    private SingletonAreaRestClient() {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * DBMethodName: g_area_list
     *
     * @return
     */
    public ObservableList<Area> getAreaList() throws ServerException {
        ObservableList<Area> areaList = FXCollections.observableArrayList();
        webResource = client.resource(BASE_URI + "/g_area_list");
        ClientResponse response = webResource
                .queryParam("method", "g_area_list")
                .accept("application/json").get(ClientResponse.class);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            String jsonOutput = response.getEntity(String.class);
            areaList = Serializer.<Area>deserializeList(jsonOutput, Area.class);
        }

        return areaList;
    }

    public static SingletonAreaRestClient getInstance() {
        return SingletonAreaRestClientHolder.INSTANCE;
    }

    private static class SingletonAreaRestClientHolder {

        private static final SingletonAreaRestClient INSTANCE = new SingletonAreaRestClient();
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
