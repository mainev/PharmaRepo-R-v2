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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import mbrinstant.controls.CustomAlertDialog;
import mbrinstant.entity.main.Product;
import mbrinstant.entity.main.Unit;
import mbrinstant.entity.mbr.Mbr;
import mbrinstant.entity.mbr.MbrStatus;
import mbrinstant.entity.mbr.PackagingMaterialRequirement;
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
public class SingletonMbrRestClient {

    private Client client;
    private WebResource webResource;
    private final String BASE_URI = "https://localhost:8181/RedServer-v2/webresources/mbr/mbr";
    private final HttpResponseHandler responseHandler = new HttpResponseHandler();

    SSLContext sslContext = null;

    private SingletonMbrRestClient() {
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
            Logger.getLogger(SingletonMbrRestClient.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public ObservableList<Mbr> getBatchList() throws ServerException {
        webResource = client.resource(BASE_URI + "/g_batch_list");
        ClientResponse response = webResource
                .accept("application/json")
                .get(ClientResponse.class);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            String jsonOutput = response.getEntity(String.class);
            return Serializer.<Mbr>deserializeList(jsonOutput, Mbr.class);
        }
        return null;
    }

    public ObservableList<Mbr> getBatchByStatus(MbrStatus status) throws ServerException {
        ObservableList<Mbr> mbrList = FXCollections.observableArrayList();

        webResource = client.resource(BASE_URI + "/g_batch_by_stat");
        ClientResponse response = webResource
                .queryParam("mbr_status", status.toString())
                .accept("application/json").get(ClientResponse.class);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            String jsonOutput = response.getEntity(String.class);
            mbrList = Serializer.<Mbr>deserializeList(jsonOutput, Mbr.class);
        }

        return mbrList;
    }

    public ObservableList<Mbr> getBatchByBatchNo(String batchNo) throws ServerException {
        ObservableList<Mbr> mbrList = FXCollections.observableArrayList();

        webResource = client.resource(BASE_URI + "/g_batch_by_batch_no");
        ClientResponse response = webResource
                .queryParam("batch_no", batchNo)
                .accept("application/json").get(ClientResponse.class);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            String jsonOutput = response.getEntity(String.class);
            mbrList = Serializer.<Mbr>deserializeList(jsonOutput, Mbr.class);
        }

        return mbrList;
    }

    public ObservableList<Mbr> getBatchByProductCode(String productCode) throws ServerException {
        ObservableList<Mbr> mbrList = FXCollections.observableArrayList();

        webResource = client.resource(BASE_URI + "/g_batch_by_product_code");
        ClientResponse response = webResource
                .queryParam("product_code", productCode)
                .accept("application/json").get(ClientResponse.class);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            String jsonOutput = response.getEntity(String.class);
            mbrList = Serializer.<Mbr>deserializeList(jsonOutput, Mbr.class);
        }

        return mbrList;
    }

    public ObservableList<Mbr> getBatchByArea(String productArea) throws ServerException {
        ObservableList<Mbr> mbrList = FXCollections.observableArrayList();

        webResource = client.resource(BASE_URI + "/g_batch_by_area");
        ClientResponse response = webResource
                .queryParam("product_area", productArea)
                .accept("application/json").get(ClientResponse.class);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            String jsonOutput = response.getEntity(String.class);
            mbrList = Serializer.<Mbr>deserializeList(jsonOutput, Mbr.class);
        }

        return mbrList;
    }

    public Mbr getBatchById(int id) throws ServerException {
        webResource = client.resource(BASE_URI + "/g_batch_by_id");

        ClientResponse response = webResource
                .queryParam("mbr_id", String.valueOf(id))
                .accept("application/json")
                .get(ClientResponse.class);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            String jsonResult = response.getEntity(String.class);
            return Serializer.<Mbr>deserialize(jsonResult, Mbr.class);
        }

        return null;
    }

    public Mbr createNewBatch(Product productId, double batchSize, Date mfgDate, Date expDate, String poNo, Unit unitId) throws ServerException {

        String batchNo = "batch1";
        System.out.println("NOTE: batchNo is temporarily set to 'batch1' in the MbrRestClient");

        Mbr mbr = new Mbr(productId, batchSize, batchNo, mfgDate, expDate,
                poNo, unitId);
        mbr.setStatus(MbrStatus.PENDING.toString());
        String input = Serializer.serialize(mbr);
        webResource = client.resource(BASE_URI + "/pst_new_batch");
        ClientResponse response = webResource
                .type("application/json").post(ClientResponse.class, input);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            String output = response.getEntity(String.class);
            return Serializer.<Mbr>deserialize(output, Mbr.class);
        }

        return null;
    }

    public Mbr createNewBatch(Mbr mbr) throws ServerException {

        mbr.setStatus(MbrStatus.PENDING.toString());
        String input = Serializer.serialize(mbr);
        webResource = client.resource(BASE_URI + "/pst_new_batch");
        ClientResponse response = webResource
                .type("application/json").post(ClientResponse.class, input);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            String output = response.getEntity(String.class);
            return Serializer.<Mbr>deserialize(output, Mbr.class);
        }
        return null;
    }

    public Mbr reserveBatch(Mbr mbr) throws ServerException {
        webResource = client.resource(BASE_URI + "/pst_reserve_mbr");
        ClientResponse response = webResource
                .queryParam("mbr_id", String.valueOf(mbr.getId()))
                .type("application/json").post(ClientResponse.class
                );
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            String output = response.getEntity(String.class);
            return Serializer.<Mbr>deserialize(output, Mbr.class);
        }

        return null;
    }

    public Mbr cancelBatchReservation(Mbr mbr) throws ServerException {
        webResource = client.resource(BASE_URI + "/pst_cancel_reservation");
        ClientResponse response = webResource
                .queryParam("mbr_id", String.valueOf(mbr.getId()))
                .type("application/json").post(ClientResponse.class
                );
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            String output = response.getEntity(String.class);
            return Serializer.<Mbr>deserialize(output, Mbr.class);
        }
        return null;
    }

    public void printBatch(Mbr mbr) throws ServerException {
        webResource = client.resource(BASE_URI + "/pst_print_batch");
        ClientResponse response = webResource
                .queryParam("mbr_id", String.valueOf(mbr.getId()))
                .type("application/json").post(ClientResponse.class
                );
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            System.out.println("Batch " + mbr.getBatchNo() + " printed.");
        }

    }

    public void dispenseBatchMaterials(Mbr mbr) throws ServerException {
        webResource = client.resource(BASE_URI + "/pst_dispense_batch_material");
        ClientResponse response = webResource
                .queryParam("mbr_id", String.valueOf(mbr.getId()))
                .type("application/json").post(ClientResponse.class
                );
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            System.out.println("Batch " + mbr.getBatchNo() + " printed.");
        }

    }

    public ObservableList<StockCardTxn> getBatchStockCardTxnList(int mbrId) throws ServerException {
        ObservableList<StockCardTxn> txnList = FXCollections.observableArrayList();

        webResource = client.resource(BASE_URI + "/g_batch_stock_card_txn_list");
        ClientResponse response = webResource
                .queryParam("mbr_id", String.valueOf(mbrId))
                .accept("application/json").get(ClientResponse.class);
        responseHandler.setCode(response.getStatus());
        if (responseHandler.isSuccessful()) {
            String jsonOutput = response.getEntity(String.class);
            txnList = Serializer.<StockCardTxn>deserializeList(jsonOutput, StockCardTxn.class);
        }

        return txnList;
    }

    public int getPrimaryPackagingQuantity(List<PackagingMaterialRequirement> pmrList, int bottleId) {
        for (PackagingMaterialRequirement bpmr : pmrList) {
            if (bpmr.getItemId().getId() == bottleId) {
                return (int) bpmr.getNewQuantity();
            }
        }
        return 0;
    }

    public int getSecondaryPackagingQuantity(List<PackagingMaterialRequirement> pmrList, int cBoxId) {
        for (PackagingMaterialRequirement pmr : pmrList) {
            if (pmr.getItemId().getId() == cBoxId) {
                return (int) pmr.getNewQuantity();
            }
        }
        return 0;
    }

    public static SingletonMbrRestClient getInstance() {
        return SingletonMbrRestClientHolder.INSTANCE;

    }

    private static class SingletonMbrRestClientHolder {

        private static final SingletonMbrRestClient INSTANCE = new SingletonMbrRestClient();
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
