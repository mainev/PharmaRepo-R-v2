/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbr.client.service.mbr;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javafx.collections.ObservableList;
import mbr.client.entity.main.Product;
import mbr.client.entity.main.Unit;
import mbr.client.entity.mbr.Mbr;
import mbr.client.entity.mbr.PackagingMaterialRequirement;
import mbr.client.utils.Serializer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 *
 * @author maine
 */
public class MbrService {

    public DefaultClientConfig defaultClientConfig;
    public Client client;
    public WebResource webResource;
    public String BASE_URI = "http://localhost:8080/RedServer-v2/webresources/mbr/mbr";

    public MbrService() {
        initClient();
    }

    private void initClient() {
        defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        client = Client.create(defaultClientConfig);
    }

    public ObservableList<Mbr> getMbrList() {

        webResource = client.resource(BASE_URI);
        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);
        //  System.out.println(Serializer.<Mbr>deserializeList(jsonOutput, Mbr.class));
        // return null;
        return Serializer.<Mbr>deserializeList(jsonOutput, Mbr.class);
    }

    public Mbr createMbr(Product productId, double batchSize, Date mfgDate, Date expDate, String poNo, Unit unitId) {

        String batchNo = "batch1";
        Mbr mbr = new Mbr(productId, batchSize, batchNo, mfgDate, expDate,
                poNo, unitId);
        String input = Serializer.serialize(mbr);
        webResource = client.resource(BASE_URI + "/create");
        ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);
        String output = response.getEntity(String.class);

        return Serializer.<Mbr>deserialize(output, Mbr.class);

    }

    public int getPrimaryPackagingQuantity(List<PackagingMaterialRequirement> pmrList, int bottleId) {
        for (PackagingMaterialRequirement bpmr : pmrList) {
            if (bpmr.getPackagingMaterialId().getId() == bottleId) {
                return (int) bpmr.getNewQuantity();
            }
        }
        return 0;
    }

    public int getSecondaryPackagingQuantity(List<PackagingMaterialRequirement> pmrList, int cBoxId) {
        for (PackagingMaterialRequirement pmr : pmrList) {
            if (pmr.getPackagingMaterialId().getId() == cBoxId) {
                return (int) pmr.getNewQuantity();
            }
        }
        return 0;
    }
}
