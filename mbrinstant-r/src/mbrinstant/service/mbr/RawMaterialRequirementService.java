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
import mbrinstant.entity.main.RawMaterial;
import mbrinstant.entity.main.Unit;
import mbrinstant.entity.mbr.RawMaterialRequirement;
import mbrinstant.utils.Serializer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 *
 * @author maine
 */
public class RawMaterialRequirementService {

    public DefaultClientConfig defaultClientConfig;
    public Client client;
    public WebResource webResource;
    public String BASE_URI = "http://localhost:8080/RedServer-v2/webresources/mbr/raw_material_requirement";

    public RawMaterialRequirementService() {
        initClient();
    }

    private void initClient() {
        defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        client = Client.create(defaultClientConfig);
    }

    public RawMaterialRequirement createRawMaterialRequirement(int udfId, RawMaterialRequirement rmReq) {
        String input = Serializer.serialize(rmReq);
        webResource = client.resource(BASE_URI + "/create");
        ClientResponse response = webResource
                .queryParam("udf_id", String.valueOf(udfId))
                .type("application/json")
                .post(ClientResponse.class, input);
        String output = response.getEntity(String.class);

        return Serializer.<RawMaterialRequirement>deserialize(output, RawMaterialRequirement.class);

    }

    public RawMaterialRequirement createRawMaterialRequirement(int udfId, RawMaterial rawMaterialId, double quantity, Unit unitId) {
        RawMaterialRequirement rmReq = new RawMaterialRequirement(rawMaterialId, quantity, unitId);

        String input = Serializer.serialize(rmReq);
        webResource = client.resource(BASE_URI + "/create");
        ClientResponse response = webResource
                .queryParam("udf_id", String.valueOf(udfId))
                .type("application/json")
                .post(ClientResponse.class, input);
        String output = response.getEntity(String.class);

        return Serializer.<RawMaterialRequirement>deserialize(output, RawMaterialRequirement.class);

    }

    public ObservableList<RawMaterialRequirement> getByUdfId(int udfId) {

        webResource = client.resource(BASE_URI + "/find_by_udf_id");

        ClientResponse response = webResource
                .queryParam("udf_id", String.valueOf(udfId))
                .accept("application/json")
                .get(ClientResponse.class);

        String jsonResult = response.getEntity(String.class);
        return Serializer.<RawMaterialRequirement>deserializeList(jsonResult, RawMaterialRequirement.class);
    }

    public RawMaterialRequirement findByDetails(int rmId, double qty, short unitId, int udfId) {

        webResource = client.resource(BASE_URI + "/find_by_details");

        ClientResponse response = webResource
                .queryParam("rm_id", String.valueOf(rmId))
                .queryParam("qty", String.valueOf(qty))
                .queryParam("unit_id", String.valueOf(unitId))
                .queryParam("udf_id", String.valueOf(udfId))
                .accept("application/json")
                .get(ClientResponse.class);

        String jsonResult = response.getEntity(String.class);
        return Serializer.<RawMaterialRequirement>deserialize(jsonResult, RawMaterialRequirement.class);
    }
}
