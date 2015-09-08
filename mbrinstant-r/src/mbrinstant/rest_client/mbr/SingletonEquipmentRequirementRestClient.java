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
import mbrinstant.entity.ProcedureCategory;
import mbrinstant.entity.mbr.EquipmentRequirement;
import mbrinstant.exceptions.ServerException;
import mbrinstant.rest_client.HttpResponseHandler;
import mbrinstant.rest_client.SecureRestClientTrustManager;
import mbrinstant.utils.Serializer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 *
 * @author maine
 */
public class SingletonEquipmentRequirementRestClient {

    private Client client;
    private WebResource webResource;
    private String BASE_URI = "https://localhost:8181/RedServer-v2/webresources/mbr/equipment_requirement";
    private final HttpResponseHandler responseHandler = new HttpResponseHandler();

    private SSLContext sslContext = null;

    private SingletonEquipmentRequirementRestClient() {

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
            Logger.getLogger(SingletonEquipmentRequirementRestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * DBMethodName: g_find_by_mfg_id_and_procedure
     *
     * @param mfgId
     * @param procedure
     * @return
     */
    public ObservableList<EquipmentRequirement> getEquipmentByMfgIdAndProcedureCategory(int mfgId, ProcedureCategory procedure)
            throws ServerException {
        webResource = client.resource(BASE_URI + "/g_find_by_mfg_id_and_procedure");

        ClientResponse response = webResource
                .queryParam("mfgId", String.valueOf(mfgId))
                .queryParam("procedure", procedure.toString())
                .accept("application/json")
                .get(ClientResponse.class);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            String jsonResult = response.getEntity(String.class);
            return Serializer.<EquipmentRequirement>deserializeList(jsonResult, EquipmentRequirement.class);
        }
        return null;
    }

    /**
     * DBMethodName: pst_create_new_equip_req
     *
     * @param mfgId
     * @param er
     * @return
     */
    public EquipmentRequirement createEquipmentRequirement(int mfgId, EquipmentRequirement er) throws ServerException {
        String input = Serializer.serialize(er);
        System.out.println("serialized equipmentreq: " + input);
        webResource = client.resource(BASE_URI + "/pst_create_new_equip_req");
        ClientResponse response = webResource
                .queryParam("method", "pst_create_new_equip_req")
                .queryParam("mfg_id", String.valueOf(mfgId))
                .type("application/json")
                .post(ClientResponse.class, input);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            String output = response.getEntity(String.class);
            return Serializer.<EquipmentRequirement>deserialize(output, EquipmentRequirement.class);
        }
        return null;
    }

    public static SingletonEquipmentRequirementRestClient getInstance() {
        return SingletonEquipmentRequirementRestClientHolder.INSTANCE;
    }

    private static class SingletonEquipmentRequirementRestClientHolder {

        private static final SingletonEquipmentRequirementRestClient INSTANCE = new SingletonEquipmentRequirementRestClient();
    }

    public HttpResponseHandler getResponseHandler() {
        return responseHandler;
    }

    public final void setUsernameAndPassword(String username, String password) {
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
