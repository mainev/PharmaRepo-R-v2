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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mbrinstant.entity.mbr.EquipmentRequirement;
import mbrinstant.utils.Serializer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 *
 * @author maine
 */
public class EquipmentRequirementService {
      public DefaultClientConfig defaultClientConfig;
    public Client client;
    public WebResource webResource;
    public String BASE_URI = "http://localhost:8080/RedServer-v2/webresources/mbr/equipment_requirement";

    public EquipmentRequirementService() {
        initClient();
    }

    private void initClient() {
        defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        client = Client.create(defaultClientConfig);
    }

    public ObservableList<EquipmentRequirement> getAllEquipmentRequirement(int mfgId, String procedure) {
        webResource = client.resource(BASE_URI + "/find_by_mfg_id_and_procedure");

        ClientResponse response = webResource
                .queryParam("mfgId", String.valueOf(mfgId))
                .queryParam("procedure", procedure)
                .accept("application/json")
                .get(ClientResponse.class);

        String jsonResult = response.getEntity(String.class);
        return Serializer.<EquipmentRequirement>deserializeList(jsonResult, EquipmentRequirement.class);
    }
    
    public EquipmentRequirement createEquipmentRequirement(int mfgId, EquipmentRequirement er) {
//        cp.setDosageList(new ArrayList());
        String input = Serializer.serialize(er);
        System.out.println("serialized equipmentreq: "+input);
        webResource = client.resource(BASE_URI + "/create");
        ClientResponse response = webResource
                .queryParam("mfg_id", String.valueOf(mfgId))
                .type("application/json")
                .post(ClientResponse.class, input);
        String output = response.getEntity(String.class);

        return Serializer.<EquipmentRequirement>deserialize(output, EquipmentRequirement.class);

       // return null;
    }
}
