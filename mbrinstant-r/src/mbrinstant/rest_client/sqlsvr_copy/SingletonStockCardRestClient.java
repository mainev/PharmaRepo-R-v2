/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.rest_client.sqlsvr_copy;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import mbrinstant.entity.sqlsvr_copy.StockCardC;
import mbrinstant.rest_client.HttpResponseHandler;
import mbrinstant.rest_client.SecureRestClientTrustManager;
import mbrinstant.utils.Serializer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 *
 * @author maine
 */
public class SingletonStockCardRestClient {

    private Client client;
    private WebResource webResource;
    private String BASE_URI = "https://localhost:8181/RedServer-v2/webresources/sqlsvr_copy/stock_card_c";
    private final HttpResponseHandler responseHandler = new HttpResponseHandler();
    private SSLContext sslContext = null;

    private SingletonStockCardRestClient() {

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
     * DBMethodName : g_stock_card_by_item_cd
     *
     * @param itemCd
     * @return
     */
    public List<StockCardC> getStockCardByItemCd(String itemCd) {
        webResource = client.resource(BASE_URI + "/g_stock_card_by_item_cd");
        ClientResponse response = webResource
                .queryParam("method", "g_stock_card_by_item_cd")
                .queryParam("item_cd", itemCd)
                .accept("application/json").get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);
        return Serializer.<StockCardC>deserializeList(jsonOutput, StockCardC.class);
    }

    /**
     * DBMethodName : g_stock_card_by_id
     *
     * @param id
     * @return
     */
    public StockCardC getStockCardById(int id) {
        webResource = client.resource(BASE_URI + "/g_stock_card_by_id");
        ClientResponse response = webResource
                .queryParam("method", "g_stock_card_by_id")
                .queryParam("id", String.valueOf(id))
                .accept("application/json").get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);
        return Serializer.<StockCardC>deserialize(jsonOutput, StockCardC.class);
    }

    /**
     * DBMethodName : g_stock_card_by_company_cd_and_item_cd
     *
     * @param companyCd
     * @param itemCd
     * @return
     */
    public List<StockCardC> getStockCardByCompanyCdAndItemCd(String companyCd, String itemCd) {
        webResource = client.resource(BASE_URI + "/g_stock_card_by_company_cd_and_item_cd");
        ClientResponse response = webResource
                .queryParam("method", "g_stock_card_by_company_cd_and_item_cd")
                .queryParam("company_cd", companyCd)
                .queryParam("item_cd", itemCd)
                .accept("application/json").get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);
        return Serializer.<StockCardC>deserializeList(jsonOutput, StockCardC.class);
    }

    /**
     * DBMethodName : pst_change_stock_card_status
     *
     * @param stk_id
     */
    //change stockcard status to depleted
    public void changeStockCardStatusToDepleted(int stk_id) {
        webResource = client.resource(BASE_URI + "/pst_change_stock_card_status");
        webResource
                .queryParam("method", "pst_change_stock_card_status")
                .queryParam("stk_id", String.valueOf(stk_id))
                .accept("application/json").post(ClientResponse.class);

    }

    /**
     * DBMethodName : g_stockcard_by_control_no
     *
     * @param controlNo
     * @return
     */
    public StockCardC getStockCardByControlNo(String controlNo) {
        try {
            webResource = client.resource(BASE_URI + "/g_stockcard_by_control_no");
            ClientResponse response = webResource
                    .queryParam("method", "g_stockcard_by_control_no")
                    .queryParam("control_no", controlNo)
                    .accept("application/json").get(ClientResponse.class);
            String jsonOutput = response.getEntity(String.class);
            return Serializer.<StockCardC>deserialize(jsonOutput, StockCardC.class);
        } catch (Exception e) {

            return null;
        }
    }

    public static SingletonStockCardRestClient getInstance() {
        return SingletonStockCardRestClientHolder.INSTANCE;
    }

    private static class SingletonStockCardRestClientHolder {

        private static final SingletonStockCardRestClient INSTANCE = new SingletonStockCardRestClient();
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
