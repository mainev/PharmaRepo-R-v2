/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.service.mbr;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import mbrinstant.entity.mbr.PackagingOperation;
import mbrinstant.utils.Serializer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 *
 * @author maine
 */
public class PackagingOperationService {

    public DefaultClientConfig defaultClientConfig;
    public Client client;
    public WebResource webResource;
    public String BASE_URI = "http://localhost:8080/RedServer-v2/webresources/mbr/packaging_operation";

    public PackagingOperationService() {
        initClient();
    }

    private void initClient() {
        defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        client = Client.create(defaultClientConfig);
    }

    public PackagingOperation createPackagingOperation(int mfgId, PackagingOperation ppo) {

        String input = Serializer.serialize(ppo);
        System.out.println("serialized packaging operation: " + input);
        webResource = client.resource(BASE_URI + "/create");
        ClientResponse response = webResource
                .queryParam("mfg_id", String.valueOf(mfgId))
                .type("application/json")
                .post(ClientResponse.class, input);
        String output = response.getEntity(String.class);

        return Serializer.<PackagingOperation>deserialize(output, PackagingOperation.class);

    }
}
