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
import mbrinstant.entity.main.Product;
import mbrinstant.entity.mbr.ManufacturingProcedure;
import mbrinstant.utils.Serializer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 *
 * @author maine
 */
public class ManufacturingProcedureService {

    public DefaultClientConfig defaultClientConfig;
    public Client client;
    public WebResource webResource;
    public String BASE_URI = "http://localhost:8080/RedServer-v2/webresources/mbr/manufacturing_procedure";

    public ManufacturingProcedureService() {
        initClient();
    }

    private void initClient() {
        defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        client = Client.create(defaultClientConfig);
    }

    public ManufacturingProcedure create(Product product) {
        ManufacturingProcedure mp = new ManufacturingProcedure(product.getId());
        String input = Serializer.serialize(mp);
        webResource = client.resource(BASE_URI + "/create");
        ClientResponse response = webResource
                .type("application/json")
                .post(ClientResponse.class, input);
        String output = response.getEntity(String.class);
        return Serializer.<ManufacturingProcedure>deserialize(output, ManufacturingProcedure.class);

    }
}
