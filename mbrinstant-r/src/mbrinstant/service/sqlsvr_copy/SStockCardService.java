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
public class SStockCardService {

    static WebResource webResource;
    static String BASE_URI = "http://localhost:8080/RedServer-v2/webresources/sqlsvr_copy/stock_card_c";

    static Client client;

    static {
        DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        client = Client.create(defaultClientConfig);
    }

    public static List<StockCardC> getStockCardByItemCd(String itemCd) {
        webResource = client.resource(BASE_URI + "/get_stock_card_by_item_cd");
        ClientResponse response = webResource
                .queryParam("item_cd", itemCd)
                .accept("application/json").get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);
        return Serializer.<StockCardC>deserializeList(jsonOutput, StockCardC.class);
    }

    public static StockCardC getStockCardById(int id) {
        webResource = client.resource(BASE_URI + "/get_stock_card_by_id");
        ClientResponse response = webResource
                .queryParam("id", String.valueOf(id))
                .accept("application/json").get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);
        return Serializer.<StockCardC>deserialize(jsonOutput, StockCardC.class);
    }

    public static List<StockCardC> getStockCardByCompanyCdAndItemCd(String companyCd, String itemCd) {
        webResource = client.resource(BASE_URI + "/get_stock_card_by_company_cd_and_item_cd");
        ClientResponse response = webResource
                .queryParam("company_cd", companyCd)
                .queryParam("item_cd", itemCd)
                .accept("application/json").get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);
        return Serializer.<StockCardC>deserializeList(jsonOutput, StockCardC.class);
    }

    //change stockcard status to depleted
    public static void changeStockCardStatus(int stk_id) {
        webResource = client.resource(BASE_URI + "/change_stock_card_status");
        webResource.queryParam("stk_id", String.valueOf(stk_id))
                .accept("application/json").post(ClientResponse.class);

    }

    public static StockCardC fingByControlNo(String controlNo) {
        try {
            webResource = client.resource(BASE_URI + "/get_by_control_no");
            ClientResponse response = webResource
                    .queryParam("control_no", controlNo)
                    .accept("application/json").get(ClientResponse.class);
            String jsonOutput = response.getEntity(String.class);
            return Serializer.<StockCardC>deserialize(jsonOutput, StockCardC.class);
        } catch (Exception e) {

            return null;
        }
    }

}
