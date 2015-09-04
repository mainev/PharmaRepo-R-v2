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
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import mbrinstant.controls.CustomAlertDialog;
import mbrinstant.entity.StockStatus;
import mbrinstant.entity.sqlsvr_copy.Company;
import mbrinstant.entity.sqlsvr_copy.Item;
import mbrinstant.entity.sqlsvr_copy.StockCardC;
import mbrinstant.exceptions.ServerException;
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
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            CustomAlertDialog.showExceptionDialog(e);
        }
    }

    public List<StockCardC> getStockCardList() throws ServerException {
        webResource = client.resource(BASE_URI + "/g_stock_card_list");
        ClientResponse response = webResource
                .accept("application/json").get(ClientResponse.class);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            String jsonOutput = response.getEntity(String.class);
            return Serializer.<StockCardC>deserializeList(jsonOutput, StockCardC.class);
        }
        return null;
    }

    public List<StockCardC> getStockCardByItemCd(String itemCd) throws ServerException {
        webResource = client.resource(BASE_URI + "/g_stock_card_by_item_cd");
        ClientResponse response = webResource
                .queryParam("item_cd", itemCd)
                .accept("application/json").get(ClientResponse.class);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            String jsonOutput = response.getEntity(String.class);
            return Serializer.<StockCardC>deserializeList(jsonOutput, StockCardC.class);
        }
        return null;
    }

    public StockCardC getStockCardById(int id) throws ServerException {
        webResource = client.resource(BASE_URI + "/g_stock_card_by_id");
        ClientResponse response = webResource
                .queryParam("id", String.valueOf(id))
                .accept("application/json").get(ClientResponse.class);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            String jsonOutput = response.getEntity(String.class);
            return Serializer.<StockCardC>deserialize(jsonOutput, StockCardC.class);
        }
        return null;
    }

    public List<StockCardC> getAvailableStockCardByCompanyCdAndItemId(String companyCd, int itemId) throws ServerException {
        webResource = client.resource(BASE_URI + "/g_stock_card_by_company_cd_and_item_id");
        ClientResponse response = webResource
                .queryParam("company_cd", companyCd)
                .queryParam("item_id", String.valueOf(itemId))
                .accept("application/json").get(ClientResponse.class);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            String jsonOutput = response.getEntity(String.class);
            return Serializer.<StockCardC>deserializeList(jsonOutput, StockCardC.class);
        }
        return null;
    }

    public List<StockCardC> getAvailableStockCard(Company company, Item item) throws ServerException {
        webResource = client.resource(BASE_URI + "/g_available_stockcard");
        ClientResponse response = webResource
                .queryParam("company_id", String.valueOf(company.getId()))
                .queryParam("item_id", String.valueOf(item.getId()))
                .accept("application/json").get(ClientResponse.class);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            String jsonOutput = response.getEntity(String.class);
            return Serializer.<StockCardC>deserializeList(jsonOutput, StockCardC.class);
        }
        return null;
    }

    public void changeStockCardStatusToDepleted(int stk_id) throws ServerException {
        webResource = client.resource(BASE_URI + "/pst_change_stock_card_status_to_depleted");
        ClientResponse response = webResource
                .queryParam("stk_id", String.valueOf(stk_id))
                .accept("application/json").post(ClientResponse.class);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            System.out.println("Stockcard status successfully updated.");
        }

    }

    public void updateStockCardStockStatus(StockCardC stk, StockStatus stockStat) throws ServerException {
        webResource = client.resource(BASE_URI + "/pst_update_stock_card_stock_status");
        ClientResponse response = webResource
                .queryParam("stk_id", String.valueOf(stk.getId()))
                .queryParam("stock_stat", stockStat.toString())
                .accept("application/json").post(ClientResponse.class);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            System.out.println("Stockcard status successfully updated.");
        }
    }

    public StockCardC getStockCardByControlNo(String controlNo) throws ServerException {

        webResource = client.resource(BASE_URI + "/g_stockcard_by_control_no");
        ClientResponse response = webResource
                .queryParam("control_no", controlNo)
                .accept("application/json").get(ClientResponse.class);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful() && response.getStatus() != 204) {
            String jsonOutput = response.getEntity(String.class);
            return Serializer.<StockCardC>deserialize(jsonOutput, StockCardC.class);
        }
        return null;

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
