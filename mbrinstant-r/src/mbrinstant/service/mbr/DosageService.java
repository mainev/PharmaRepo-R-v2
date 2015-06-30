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
import mbrinstant.entity.mbr.Dosage;
import mbrinstant.utils.Serializer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 *
 * @author maine
 */
public class DosageService {

    public DefaultClientConfig defaultClientConfig;
    public Client client;
    public WebResource webResource;
    public String BASE_URI = "http://localhost:8080/RedServer-v2/webresources/mbr/dosage";

    public DosageService() {
        initClient();
    }

    private void initClient() {
        defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        client = Client.create(defaultClientConfig);
    }

    public Dosage createDosage(int cpId, Dosage dos) {
        String input = Serializer.serialize(dos);
        System.out.println("serialized dos: " + input);
        webResource = client.resource(BASE_URI + "/create");
        ClientResponse response = webResource
                .queryParam("cp_id", String.valueOf(cpId))
                .type("application/json")
                .post(ClientResponse.class, input);
        String output = response.getEntity(String.class);

        return Serializer.<Dosage>deserialize(output, Dosage.class);
    }
}