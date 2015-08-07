/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.rest_client.transaction;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import mbrinstant.entity.transaction.StockCardTxn;
import mbrinstant.rest_client.HttpResponseHandler;
import mbrinstant.rest_client.SecureRestClientTrustManager;
import mbrinstant.utils.Serializer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 *
 * @author maine
 */
public class SingletonStockCardTxnRestClient {

    private Client client;
    private WebResource webResource;
    private final String BASE_URI = "https://localhost:8181/RedServer-v2/webresources/transaction/stock_card_txn";
    private final HttpResponseHandler responseHandler = new HttpResponseHandler();
    private SSLContext sslContext = null;

    private SingletonStockCardTxnRestClient() {
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
     * DBMethodName : g_reserved_approved_by_item_cd
     *
     * @param itemCd
     * @return
     */
    public List<StockCardTxn> getReservedAndApprovedtByItemCd(String itemCd) {
        webResource = client.resource(BASE_URI + "/g_reserved_approved_by_item_cd");
        ClientResponse response = webResource
                .queryParam("method", "g_reserved_approved_by_item_cd")
                .queryParam("item_cd", itemCd)
                .accept("application/json").get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);
        return Serializer.<StockCardTxn>deserializeList(jsonOutput, StockCardTxn.class);
    }

    /**
     * DBMethodName : g_reserved_approved_by_item_cd_company_cd
     *
     * @param itemCd
     * @param companyCd
     * @return
     */
    public List<StockCardTxn> getReservedAndApprovedtByItemCdAndCompanyCd(String itemCd, String companyCd) {
        webResource = client.resource(BASE_URI + "/g_reserved_approved_by_item_cd_company_cd");
        ClientResponse response = webResource
                .queryParam("method", "g_reserved_approved_by_item_cd_company_cd")
                .queryParam("item_cd", itemCd)
                .queryParam(("company_cd"), companyCd)
                .accept("application/json").get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);
        return Serializer.<StockCardTxn>deserializeList(jsonOutput, StockCardTxn.class);
    }

    /**
     * DBMethodName : pst_new_stock_card_txn
     *
     * @param mbrId
     * @param stkId
     * @param txn
     * @return
     */
    public StockCardTxn createNewStockCardTxn(int mbrId, int stkId, StockCardTxn txn) {
        String input = Serializer.serialize(txn);
        System.out.println("serialized txn : " + input);
        webResource = client.resource(BASE_URI + "/pst_new_stock_card_txn");
        ClientResponse response = webResource
                .queryParam("method", "pst_new_stock_card_txn")
                .queryParam("mbr_id", String.valueOf(mbrId))
                .queryParam("stk_id", String.valueOf(stkId))
                .type("application/json")
                .post(ClientResponse.class, input);
        String output = response.getEntity(String.class);

        return Serializer.<StockCardTxn>deserialize(output, StockCardTxn.class);

    }

    public static SingletonStockCardTxnRestClient getInstance() {
        return SingletonStockCardTxnRestClientHolder.INSTANCE;
    }

    private static class SingletonStockCardTxnRestClientHolder {

        private static final SingletonStockCardTxnRestClient INSTANCE = new SingletonStockCardTxnRestClient();
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
