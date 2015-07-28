/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.service.transaction;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.util.List;
import mbrinstant.entity.transaction.StockCardTxn;
import mbrinstant.utils.Serializer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 *
 * @author maine
 */
public class StockCardTxnService {

    public DefaultClientConfig defaultClientConfig;
    public Client client;
    public WebResource webResource;
    public final String BASE_URI = "http://localhost:8080/RedServer-v2/webresources/transaction/stock_card_txn";

    public StockCardTxnService() {
        initClient();
    }

    private void initClient() {
        defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        client = Client.create(defaultClientConfig);
    }

    public List<StockCardTxn> getReservedAndApprovedtByItemCd(String itemCd) {
        webResource = client.resource(BASE_URI + "/get_reserved_approved_by_item_cd");
        ClientResponse response = webResource
                .queryParam("item_cd", itemCd)
                .accept("application/json").get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);
        return Serializer.<StockCardTxn>deserializeList(jsonOutput, StockCardTxn.class);
    }
    
     public List<StockCardTxn> getReservedAndApprovedtByItemCdAndCompanyCd(String itemCd, String companyCd) {
        webResource = client.resource(BASE_URI + "/get_reserved_approved_by_item_cd_company_cd");
        ClientResponse response = webResource
                .queryParam("item_cd", itemCd)
                .queryParam(("company_cd"), companyCd)
                .accept("application/json").get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);
        return Serializer.<StockCardTxn>deserializeList(jsonOutput, StockCardTxn.class);
    }

    public StockCardTxn postStockCardTxn(int mbrId, int stkId, StockCardTxn txn) {
        String input = Serializer.serialize(txn);
        System.out.println("serialized txn : " + input);
        webResource = client.resource(BASE_URI + "/create");
        ClientResponse response = webResource
                .queryParam("mbr_id", String.valueOf(mbrId))
                .queryParam("stk_id", String.valueOf(stkId))
                .type("application/json")
                .post(ClientResponse.class, input);
        String output = response.getEntity(String.class);

        return Serializer.<StockCardTxn>deserialize(output, StockCardTxn.class);

    }

}
