/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.service.sqlsvr_copy;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.util.List;
import mbrinstant.entity.sqlsvr_copy.StockCardC;
import mbrinstant.utils.Serializer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 *
 * @author maine
 */
public class StockCardService {
     public DefaultClientConfig defaultClientConfig;
    public Client client;
    public WebResource webResource;
    public final String BASE_URI = "http://localhost:8080/RedServer-v2/webresources/sqlsvr_copy/stock_card_c";

    public StockCardService() {
        initClient();
    }
    
     private void initClient() {
        defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        client = Client.create(defaultClientConfig);
    }
     
     public List<StockCardC> getStockCardByItemCd(String itemCd){
      webResource = client.resource(BASE_URI +"/get_stock_card_by_item_cd");
        ClientResponse response = webResource
                .queryParam("item_cd", itemCd)
                .accept("application/json").get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);
        return Serializer.<StockCardC>deserializeList(jsonOutput, StockCardC.class);
     }
     
     public List<StockCardC> getStockCardByCompanyCdAndItemCd(String companyCd, String itemCd){
      webResource = client.resource(BASE_URI +"/get_stock_card_by_company_cd_and_item_cd");
        ClientResponse response = webResource
                .queryParam("company_cd", companyCd)
                .queryParam("item_cd", itemCd)
                .accept("application/json").get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);
        return Serializer.<StockCardC>deserializeList(jsonOutput, StockCardC.class);
     }
     
//    public float getMaterialQuantity(String itemCd) {
//        webResource = client.resource(BASE_URI + "/remaining_quantity");
//
//        ClientResponse response = webResource
//                .queryParam("item_cd", itemCd)
//                .get(ClientResponse.class);
//
//        String jsonResult = response.getEntity(String.class);
//
//        return Float.valueOf(jsonResult);
//    }
}
