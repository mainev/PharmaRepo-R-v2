/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.product.ProductWizard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import mbrinstant.controls.ConstraintValidator;
import mbrinstant.controls.CustomChoiceBox;
import mbrinstant.entity.main.Product;
import mbrinstant.entity.mbr.PackagingMaterialRequirement;
import mbrinstant.exceptions.ServerException;
import mbrinstant.rest_client.main.SingletonProductRestClient;
import mbrinstant.rest_client.mbr.SingletonPackgMaterialRequirementRestClient;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class PrimarySecondaryController implements Initializable, PageController {

    @FXML
    CustomChoiceBox<PackagingMaterialRequirement> primary;
    @FXML
    CustomChoiceBox<PackagingMaterialRequirement> secondary;

    private ConstraintValidator validator;
    ObservableList<PackagingMaterialRequirement> pmReqList = FXCollections.observableArrayList();

    //rest client
    SingletonProductRestClient productRestClient = SingletonProductRestClient.getInstance();
    SingletonPackgMaterialRequirementRestClient pmReqService = SingletonPackgMaterialRequirementRestClient.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createValidator();
        primary.setItems(pmReqList);
        secondary.setItems(pmReqList);

        primary.getSelectionModel().selectedItemProperty().addListener((OB, OV, NV) -> {
            System.out.println(NV);
        });

        pmReqList.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change change) {
                while (change.next()) {
                    primary.setValue(null);
                    secondary.setValue(null);

                }
            }
        });
    }

    public void createPrimarySecondaryPackg(Product productId, int udfId) throws ServerException {
        if (allFieldsValid()) {
            PackagingMaterialRequirement primaryPackg = primary.getValue();
            PackagingMaterialRequirement secondaryPackg = secondary.getValue();
            primaryPackg = pmReqService.getPackgMaterialReqByDetails(
                    primaryPackg.getItemId().getId(),
                    primaryPackg.getQuantity(),
                    primaryPackg.getUnitId().getId(), udfId);
            secondaryPackg = pmReqService.getPackgMaterialReqByDetails(
                    secondaryPackg.getItemId().getId(),
                    secondaryPackg.getQuantity(),
                    secondaryPackg.getUnitId().getId(),
                    udfId);

            productRestClient.createPrimarySecondaryPackg(productId.getId(), primaryPackg.getId(), secondaryPackg.getId());
        }
    }

    @Override
    public void createValidator() {
        validator = new ConstraintValidator(primary, secondary);

    }

    @Override
    public boolean allFieldsValid() {
        // return validator.validateFields();
        return validator.validateFields();
    }

    @Override
    public String getInstruction() {
        return "Set primary and secondary packaging for product";
    }
}
