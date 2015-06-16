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
import javafx.collections.ObservableList;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import mbrinstant.entity.main.Unit;
import mbrinstant.utils.Serializer;

/**
 *
 * @author maine
 */
public class UnitService {

    public DefaultClientConfig defaultClientConfig;
    public Client client;
    public WebResource webResource;
    public final String BASE_URI = "http://localhost:8080/RedServer-v2/webresources/main/unit";

    public UnitService() {
        initClient();
    }

    private void initClient() {
        defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        client = Client.create(defaultClientConfig);
    }
    
    public ObservableList<Unit> getUnitList(){
        webResource = client.resource(BASE_URI);
        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);
        
        return Serializer.<Unit>deserializeList(jsonOutput, Unit.class);
    }
}
