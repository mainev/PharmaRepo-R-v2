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
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import mbrinstant.entity.mbr.BatchItemRequirement;
import mbrinstant.entity.sqlsvr_copy.StockCardC;
import mbrinstant.entity.transaction.StockCardTxn;
import mbrinstant.exceptions.ServerException;
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

//    public List<StockCardTxn> getReservedAndApprovedtByItemCd(String itemCd) throws ServerException {
//        ObservableList<StockCardTxn> list = FXCollections.observableArrayList();
//        webResource = client.resource(BASE_URI + "/g_reserved_approved_by_item_cd");
//        ClientResponse response = webResource
//                .queryParam("item_cd", itemCd)
//                .accept("application/json").get(ClientResponse.class);
//        responseHandler.setCode(response.getStatus());
//        if (responseHandler.isSuccessful()) {
//            String jsonOutput = response.getEntity(String.class);
//            list = Serializer.<StockCardTxn>deserializeList(jsonOutput, StockCardTxn.class);
//        }
//
//        return list;
//    }
//    public List<StockCardTxn> getReservedAndApprovedtByItemCdAndCompanyCd(String itemCd, String companyCd) throws ServerException {
//        ObservableList<StockCardTxn> list = FXCollections.observableArrayList();
//        webResource = client.resource(BASE_URI + "/g_reserved_approved_by_item_cd_company_cd");
//        ClientResponse response = webResource
//                .queryParam("item_cd", itemCd)
//                .queryParam(("company_cd"), companyCd)
//                .accept("application/json").get(ClientResponse.class);
//        responseHandler.setCode(response.getStatus());
//        if (responseHandler.isSuccessful()) {
//            String jsonOutput = response.getEntity(String.class);
//            list = Serializer.<StockCardTxn>deserializeList(jsonOutput, StockCardTxn.class);
//        }
//
//        return list;
//    }
    public StockCardTxn createNewStockCardTxn(BatchItemRequirement batchItemReq, StockCardC stk, StockCardTxn txn) throws ServerException {
        String input = Serializer.serialize(txn);
        System.out.println("serialized txn : " + input);
        webResource = client.resource(BASE_URI + "/pst_new_stock_card_txn");
        ClientResponse response = webResource
                .queryParam("batch_item_req_id", String.valueOf(batchItemReq.getId()))
                .queryParam("stk_id", String.valueOf(stk.getId()))
                .type("application/json")
                .post(ClientResponse.class, input);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            String output = response.getEntity(String.class);
            return Serializer.<StockCardTxn>deserialize(output, StockCardTxn.class);
        }
        return null;

    }

    public void deleteStockCardTxn(StockCardTxn stkTxn) throws ServerException {
        webResource = client.resource(BASE_URI + "/pst_delete_stock_card_txn");
        ClientResponse response = webResource
                .queryParam("stk_id", String.valueOf(stkTxn.getId()))
                .accept("application/json").post(ClientResponse.class);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            System.out.println("batch stockcard transaction successfully deleted");
        }
    }
    /*
     public List<StockCardTxn> getStockCardTxnByBatchNo(String batchNo) throws ServerException {
     ObservableList<StockCardTxn> list = FXCollections.observableArrayList();
     webResource = client.resource(BASE_URI + "/g_stockcardtxn_by_batch_no");
     ClientResponse response = webResource
     .queryParam("batch_no", batchNo)
     .accept("application/json").get(ClientResponse.class);
     responseHandler.setCode(response.getStatus());
     if (responseHandler.isSuccessful()) {
     String jsonOutput = response.getEntity(String.class);
     list = Serializer.<StockCardTxn>deserializeList(jsonOutput, StockCardTxn.class);

     }

     return list;
     }*/

    /*

     public List<StockCardTxn> getStockCardTxnByBatchNoAndItemCategory(String batchNo, String itemCategory) throws ServerException {
     ObservableList<StockCardTxn> list = FXCollections.observableArrayList();
     webResource = client.resource(BASE_URI + "/g_stockcardtxn_by_batch_no_and_item_category");
     ClientResponse response = webResource
     .queryParam("batch_no", batchNo)
     .queryParam("category", itemCategory)
     .accept("application/json").get(ClientResponse.class);
     responseHandler.setCode(response.getStatus());
     if (responseHandler.isSuccessful()) {
     String jsonOutput = response.getEntity(String.class);
     list = Serializer.<StockCardTxn>deserializeList(jsonOutput, StockCardTxn.class);

     }

     return list;
     }

     */
    public StockCardC getStockCard(StockCardTxn stkTxn) throws ServerException {
        webResource = client.resource(BASE_URI + "/g_stockcard");
        ClientResponse response = webResource
                .queryParam("stktxn_id", String.valueOf(stkTxn.getId()))
                .accept("application/json").get(ClientResponse.class);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            String jsonOutput = response.getEntity(String.class);
            return Serializer.<StockCardC>deserialize(jsonOutput, StockCardC.class);
        }
        return null;
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
