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
import javafx.collections.ObservableList;
import mbrinstant.entity.main.PackagingMaterial;
import mbrinstant.entity.main.Unit;
import mbrinstant.entity.mbr.PackagingMaterialRequirement;
import mbrinstant.utils.Serializer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 *
 * @author maine
 */
public class PackagingMaterialRequirementService {

    public DefaultClientConfig defaultClientConfig;
    public Client client;
    public WebResource webResource;
    public String BASE_URI = "http://localhost:8080/RedServer-v2/webresources/mbr/packaging_material_requirement";

    public PackagingMaterialRequirementService() {
        initClient();
    }

    private void initClient() {
        defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        client = Client.create(defaultClientConfig);
    }

    
    public ObservableList<PackagingMaterialRequirement> getByUdfId(int udfId){
    
    webResource = client.resource(BASE_URI + "/find_by_udf_id");

        ClientResponse response = webResource
                .queryParam("udf_id", String.valueOf(udfId))
                .accept("application/json")
                .get(ClientResponse.class);

        String jsonResult = response.getEntity(String.class);
        return Serializer.<PackagingMaterialRequirement>deserializeList(jsonResult, PackagingMaterialRequirement.class);
    }
    
     public PackagingMaterialRequirement createPackagingMaterialRequirement(int udfId, PackagingMaterial packagingMaterialId, double quantity, Unit unitId) {
        PackagingMaterialRequirement rmReq = new PackagingMaterialRequirement(packagingMaterialId, quantity, unitId);

        String input = Serializer.serialize(rmReq);
        webResource = client.resource(BASE_URI + "/create");
        ClientResponse response = webResource
                .queryParam("udf_id", String.valueOf(udfId))
                .type("application/json")
                .post(ClientResponse.class, input);
        String output = response.getEntity(String.class);
      
        return Serializer.<PackagingMaterialRequirement>deserialize(output, PackagingMaterialRequirement.class);

    }
}
