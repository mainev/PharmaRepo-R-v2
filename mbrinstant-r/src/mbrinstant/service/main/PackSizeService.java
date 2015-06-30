/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.service.main;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import mbrinstant.entity.main.Container;
import mbrinstant.entity.main.PackSize;
import mbrinstant.entity.main.Unit;
import mbrinstant.utils.Serializer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 *
 * @author maine
 */
public class PackSizeService {
      public DefaultClientConfig defaultClientConfig;
    public Client client;
    public WebResource webResource;
    public final String BASE_URI = "http://localhost:8080/RedServer-v2/webresources/main/pack_size";

    public PackSizeService() {
        initClient();
    }

    private void initClient() {
        defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        client = Client.create(defaultClientConfig);
    }

      /***
     * Creates a new pack size in the database whenever the result is empty.
     * @param quantity
     * @param unitId
     * @param containerId
     * @return Returns the instance of the pack size from the database.
     */
    public PackSize createPackSize(double quantity, Unit unitId, Container containerId) {
        PackSize packSizeId = new PackSize(quantity,  containerId, unitId);
        String jsonInput = Serializer.serialize(packSizeId);
        webResource = client.resource(BASE_URI + "/create");
        ClientResponse response = webResource.type("application/json").post(ClientResponse.class, jsonInput);
        String jsonResult = response.getEntity(String.class);
        return Serializer.<PackSize>deserialize(jsonResult, PackSize.class);
    }
}
