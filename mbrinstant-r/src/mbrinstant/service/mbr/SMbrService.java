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
import java.io.IOException;
import java.util.Date;
import javafx.collections.ObservableList;
import mbrinstant.entity.main.Product;
import mbrinstant.entity.main.Unit;
import mbrinstant.entity.mbr.Mbr;
import mbrinstant.entity.mbr.MbrStatus;
import mbrinstant.utils.Serializer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 *
 * @author maine
 */
public class SMbrService {

    static WebResource webResource;
    static String BASE_URI = "http://localhost:8080/RedServer-v2/webresources/mbr/mbr";
    static Client client;

    static {
        DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        client = Client.create(defaultClientConfig);
    }

    public static ObservableList<Mbr> getMbrList() {

        webResource = client.resource(BASE_URI);
        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);

        return Serializer.<Mbr>deserializeList(jsonOutput, Mbr.class);
    }

    public static ObservableList<Mbr> getMbrListByStatus(MbrStatus status) {

        webResource = client.resource(BASE_URI + "/get_by_status");
        ClientResponse response = webResource
                .queryParam("mbr_status", status.toString())
                .accept("application/json").get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);

        return Serializer.<Mbr>deserializeList(jsonOutput, Mbr.class);
    }

    public static ObservableList<Mbr> getMbrListByBatchNo(String batchNo) {

        webResource = client.resource(BASE_URI + "/get_by_batch_no");
        ClientResponse response = webResource
                .queryParam("batch_no", batchNo)
                .accept("application/json").get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);

        return Serializer.<Mbr>deserializeList(jsonOutput, Mbr.class);
    }

    public static ObservableList<Mbr> getMbrListByProductCode(String productCode) {

        webResource = client.resource(BASE_URI + "/get_by_product_code");
        ClientResponse response = webResource
                .queryParam("product_code", productCode)
                .accept("application/json").get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);

        return Serializer.<Mbr>deserializeList(jsonOutput, Mbr.class);
    }

    public static ObservableList<Mbr> getMbrListByProductArea(String productArea) {

        webResource = client.resource(BASE_URI + "/get_by_product_area");
        ClientResponse response = webResource
                .queryParam("product_area", productArea)
                .accept("application/json").get(ClientResponse.class);
        String jsonOutput = response.getEntity(String.class);

        return Serializer.<Mbr>deserializeList(jsonOutput, Mbr.class);
    }

    public static Mbr createMbr(Product productId, double batchSize, Date mfgDate, Date expDate, String poNo, Unit unitId) {

        String batchNo = "batch1";
        System.out.println("NOTE: batchNo is temporarily set to 'batch1' in the SMbrService");

        Mbr mbr = new Mbr(productId, batchSize, batchNo, mfgDate, expDate,
                poNo, unitId);
        mbr.setStatus(MbrStatus.PENDING.toString());

        String input = Serializer.serialize(mbr);
        webResource = client.resource(BASE_URI + "/create");
        ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);
        String output = response.getEntity(String.class);

        return Serializer.<Mbr>deserialize(output, Mbr.class);

    }

    public static Mbr createMbr(Mbr mbr) {

        String batchNo = "batch1";
        System.out.println("NOTE: batchNo is temporarily set to 'batch1' in the SMbrService");
        mbr.setBatchNo(batchNo);
        mbr.setStatus(MbrStatus.PENDING.toString());
        String input = Serializer.serialize(mbr);
        webResource = client.resource(BASE_URI + "/create");
        ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);
        String output = response.getEntity(String.class);

        return Serializer.<Mbr>deserialize(output, Mbr.class);

    }

    public static void reserveMbr(Mbr mbr) {
        webResource = client.resource(BASE_URI + "/reserve_mbr");
        webResource
                .queryParam("mbr_id", String.valueOf(mbr.getId()))
                .type("application/json").post(ClientResponse.class);
    }

    public static void cancelReservation(Mbr mbr) throws IOException {
        if (mbr.getStatus().equals(MbrStatus.RESERVED.toString())) {
            //access web service
            webResource = client.resource(BASE_URI + "/cancel_reservation");
            webResource
                    .queryParam("mbr_id", String.valueOf(mbr.getId()))
                    .type("application/json").post(ClientResponse.class);
        } else {
            throw new IOException("Mbr[id:" + mbr.getId() + "] is not RESERVED.");
        }

    }

    public static void releaseMbr(Mbr mbr) throws IOException {
        if (mbr.getStatus().equals(MbrStatus.RESERVED.toString())) {
            //access web service
            webResource = client.resource(BASE_URI + "/release_mbr");
            webResource
                    .queryParam("mbr_id", String.valueOf(mbr.getId()))
                    .type("application/json").post(ClientResponse.class);
        } else {
            throw new IOException("ERROR: Cannot release mbr. Mbr[id:" + mbr.getId() + "] is not RESERVED.");
        }

    }

    public static void dispenseMbrMaterials(Mbr mbr) throws IOException {
        if (mbr.getStatus().equals(MbrStatus.PRINTED.toString())) {
            //access web service
            webResource = client.resource(BASE_URI + "/dispense_mbr_materials");
            webResource
                    .queryParam("mbr_id", String.valueOf(mbr.getId()))
                    .type("application/json").post(ClientResponse.class);
        } else {
            throw new IOException("ERROR: Cannot dispense materials. Mbr[id:" + mbr.getId() + "] is not RELEASED or not yet PRINTED.");
        }

    }
    /*
     public static int getPrimaryPackagingQuantity(List<PackagingMaterialRequirement> pmrList, int bottleId) {
     for (PackagingMaterialRequirement bpmr : pmrList) {
     if (bpmr.getPackagingMaterialId().getId() == bottleId) {
     return (int) bpmr.getNewQuantity();
     }
     }
     return 0;
     }

     public static int getSecondaryPackagingQuantity(List<PackagingMaterialRequirement> pmrList, int cBoxId) {
     for (PackagingMaterialRequirement pmr : pmrList) {
     if (pmr.getPackagingMaterialId().getId() == cBoxId) {
     return (int) pmr.getNewQuantity();
     }
     }
     return 0;
     }

     */
}
