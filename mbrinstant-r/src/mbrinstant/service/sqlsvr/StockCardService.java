/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.service.sqlsvr;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 *
 * @author maine
 */
public class StockCardService {
     public DefaultClientConfig defaultClientConfig;
    public Client client;
    public WebResource webResource;
    public final String BASE_URI = "http://localhost:8080/RedServer-v2/webresources/sqlsvr/stock_card";

    public StockCardService() {
        initClient();
    }
    
     private void initClient() {
        defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        client = Client.create(defaultClientConfig);
    }
     
    public float getMaterialQuantity(String itemCd) {
        webResource = client.resource(BASE_URI + "/remaining_quantity");

        ClientResponse response = webResource
                .queryParam("item_cd", itemCd)
                .get(ClientResponse.class);

        String jsonResult = response.getEntity(String.class);

        return Float.valueOf(jsonResult);
    }
}