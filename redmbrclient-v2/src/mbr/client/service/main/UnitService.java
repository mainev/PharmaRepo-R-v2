/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbr.client.service.main;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import javafx.collections.ObservableList;
import mbr.client.entity.main.Unit;
import mbr.client.utils.Serializer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 *
 * @author maine
 */
public class UnitService {

    public DefaultClientConfig defaultClientConfig;
    public Client client;
    public WebResource webResource;
    public final String BASE_URI = "http://localhost:8080/RedWebServer/webresources/unit";

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
       // System.out.println(Serializer.<Unit>toList(jsonOutput, Unit.class));
        
        return Serializer.<Unit>deserializeList(jsonOutput, Unit.class);
    }
}
