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
import mbrinstant.entity.main.Unit;
import mbrinstant.entity.mbr.Udf;
import mbrinstant.utils.Serializer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 *
 * @author maine
 */
public class UdfService {

    public DefaultClientConfig defaultClientConfig;
    public Client client;
    public WebResource webResource;
    public String BASE_URI = "http://localhost:8080/RedServer-v2/webresources/mbr/udf";

    public UdfService() {
        initClient();
    }

    private void initClient() {
        defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        client = Client.create(defaultClientConfig);
    }

    public Udf getUdfById(int id) {
        webResource = client.resource(BASE_URI + "/find_by_id");

        ClientResponse response = webResource
                .queryParam("id", String.valueOf(id))
                .accept("application/json")
                .get(ClientResponse.class);

        String jsonResult = response.getEntity(String.class);
        return Serializer.<Udf>deserialize(jsonResult, Udf.class);
    }
    
      public Udf createUdf(int id, double content, Unit unit) {
        Udf udf = new Udf(id, content, unit);
        String input = Serializer.serialize(udf);
        webResource = client.resource(BASE_URI + "/create");
        ClientResponse response = webResource
                .type("application/json")
                .post(ClientResponse.class, input);
        String output = response.getEntity(String.class);
        return Serializer.<Udf>deserialize(output, Udf.class);

    }
    
}
