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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import mbrinstant.exception.ServerException;
import mbrinstant.rest_client.HttpResponseHandler;
import mbrinstant.rest_client.SecureRestClientTrustManager;
import mbrinstant.utils.Serializer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 *
 * @author maine
 */
public class Authenticator {

    public Client client;
    public WebResource webResource;
    public final String BASE_URI = "https://localhost:8181/RedServer-v2/webresources/authenticate";
    private final HttpResponseHandler responseHandler = new HttpResponseHandler();
    SSLContext sslContext = null;

    public Authenticator() throws Exception {
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
    }

    /*
     public User loginUser(String emailAd, String pwd) {
     try {
     webResource = client.resource(BASE_URI);
     ClientResponse response = webResource
     .queryParam("emaid_ad", emailAd)
     .queryParam("pwd", Encryptor.encrypt(pwd))
     .type("application/json")
     .get(ClientResponse.class);
     responseHandler.setCode(response.getStatus());
     responseHandler.isSuccessful();

     //if the server did not return any matching user from the database
     if (response.getStatus() != 200) {
     return null;
     }

     String output = response.getEntity(String.class);
     return Serializer.<User>deserialize(output, User.class);

     } catch (NoSuchAlgorithmException e) {
     //create exception handler here...
     MyNotifications.displayError("SYSTEM ERROR:PASSWORD ENCRYPTION FAILED");
     return null;
     }
     }
     */
    //login without password hashing
    public User loginUser(String emailAd, String pwd) {
        try {
            client.addFilter(new HTTPBasicAuthFilter(emailAd, pwd));
            webResource = client.resource(BASE_URI);
            ClientResponse response = webResource
                    .queryParam("emaid_ad", emailAd)
                    .queryParam("pwd", pwd)
                    .type("application/json")
                    .get(ClientResponse.class);
            responseHandler.setCode(response.getStatus());
            responseHandler.isSuccessful();

            //if the server did not return any matching user from the database
            if (response.getStatus() != 200) {
                return null;
            }

            String output = response.getEntity(String.class);
            return Serializer.<User>deserialize(output, User.class);
        } catch (ServerException ex) {
            Logger.getLogger(Authenticator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public HttpResponseHandler getResponseHandler() {
        return responseHandler;
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
}
