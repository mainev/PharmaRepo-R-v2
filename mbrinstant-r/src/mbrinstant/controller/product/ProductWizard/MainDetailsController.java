/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.product.ProductWizard;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import mbrinstant.controls.ConstraintValidator;
import mbrinstant.controls.CustomChoiceBox;
import mbrinstant.controls.CustomTextField;
import mbrinstant.controls.IntegerTextField;
import mbrinstant.controls.NumberTextField;
import mbrinstant.entity.main.Area;
import mbrinstant.entity.main.Classification;
import mbrinstant.entity.main.Company;
import mbrinstant.entity.main.Container;
import mbrinstant.entity.main.PackSize;
import mbrinstant.entity.main.Product;
import mbrinstant.entity.main.Unit;
import mbrinstant.exception.ServerException;
import mbrinstant.rest_client.main.SingletonAreaRestClient;
import mbrinstant.rest_client.main.SingletonClassificationRestClient;
import mbrinstant.rest_client.main.SingletonCompanyRestClient;
import mbrinstant.rest_client.main.SingletonContainerRestClient;
import mbrinstant.rest_client.main.SingletonPackSizeRestClient;
import mbrinstant.rest_client.main.SingletonProductRestClient;
import mbrinstant.rest_client.main.SingletonUnitRestClient;
import mbrinstant.rest_client.mbr.SingletonMfgProcedureRestClient;
import mbrinstant.rest_client.mbr.SingletonUdfRestClient;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class MainDetailsController implements Initializable, PageController {

    @FXML
    AnchorPane mainDetailsPane;
    @FXML
    CustomTextField productCodeTextField;
    @FXML
    Label codeValidationStatusLabel;
    @FXML
    CustomTextField brandNameTextField;
    @FXML
    CustomTextField genericNameTextField;
    @FXML
    CustomChoiceBox<Classification> classificationChoiceBox;
    @FXML
    CustomChoiceBox<Company> clientChoiceBox;
    @FXML
    CustomTextField vrNoTextField;
    @FXML
    IntegerTextField shelfLifeTextField;
    @FXML
    CustomChoiceBox<Area> areaChoiceBox;
    @FXML
    CustomChoiceBox<PackSize> packSizeChoiceBox;
    @FXML
    NumberTextField packSizeQty;
    @FXML
    CustomChoiceBox<Unit> packSizeUnit;
    @FXML
    CustomChoiceBox<Container> packSizeContainer;
    @FXML
    NumberTextField udfContent;
    @FXML
    CustomChoiceBox<Unit> udfUnit;

    //rest client
    SingletonProductRestClient productRestClient = SingletonProductRestClient.getInstance();
    SingletonUnitRestClient unitRestClient = SingletonUnitRestClient.getInstance();
    SingletonAreaRestClient areaRestClient = SingletonAreaRestClient.getInstance();
    SingletonClassificationRestClient classfRestClient = SingletonClassificationRestClient.getInstance();
    SingletonCompanyRestClient companyRestClient = SingletonCompanyRestClient.getInstance();
    SingletonContainerRestClient containerService = SingletonContainerRestClient.getInstance();
    SingletonPackSizeRestClient packSizeService = SingletonPackSizeRestClient.getInstance();
    SingletonMfgProcedureRestClient manufacturingProcedureService = SingletonMfgProcedureRestClient.getInstance();
    SingletonUdfRestClient udfService = SingletonUdfRestClient.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            initFields();
            initCodeTextField();
            createValidator();
        } catch (ServerException ex) {
            Logger.getLogger(MainDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initFields() throws ServerException {
        classificationChoiceBox.setItems(classfRestClient.getClassificationList());
        clientChoiceBox.setItems(companyRestClient.getCompanyList());
        areaChoiceBox.setItems(areaRestClient.getAreaList());
        packSizeUnit.setItems(unitRestClient.getUnitList());
        packSizeContainer.setItems(containerService.getContainerList());
        udfUnit.setItems(unitRestClient.getUnitList());
    }

    public PackSize createFinalPackSize() throws ServerException {
        return packSizeService.createNewPackSize(packSizeQty.getValue(), packSizeUnit.getValue(), packSizeContainer.getValue());
    }
    private Product finalProduct;

    public Product getFinalProduct() throws ServerException {
        return productRestClient.getProductById(finalProduct.getId());
    }

    public void createFinalProduct() throws ServerException {
        if (validator.validateFields()) {
            String code = productCodeTextField.getText();
            String brandName = brandNameTextField.getText();
            String genericName = genericNameTextField.getText();
            String vrNo = vrNoTextField.getText();
            short shelfLife = (short) shelfLifeTextField.getValue();
            Area areaId = areaChoiceBox.getValue();
            Classification classificationId = classificationChoiceBox.getValue();
            Company clientId = clientChoiceBox.getValue();
            PackSize packSizeId = createFinalPackSize();

            finalProduct = productRestClient.createNewProduct(code, brandName, genericName, vrNo, shelfLife, areaId, classificationId, clientId, packSizeId);
            System.out.println("product created in db: " + finalProduct);
            udfService.createNewUdf(finalProduct.getId(), udfContent.getValue(), udfUnit.getValue());
            manufacturingProcedureService.createNewMfgProcedure(finalProduct);
        }
    }

    private void initCodeTextField() throws ServerException {

        productCodeTextField.textProperty().addListener((ob, ov, nv) -> {
            if (!nv.isEmpty()) {
                try {
                    if (productRestClient.isCodeValid(nv)) {
                        codeValidationStatusLabel.setText("Code '" + nv + "' is available.");
                        codeValidationStatusLabel.setTextFill(Color.web("green"));

                    } else {
                        codeValidationStatusLabel.setText("Code '" + nv + "' is already used. Please use another code.");
                        codeValidationStatusLabel.setTextFill(Color.web("red"));
                    }
                } catch (ServerException ex) {
                    Logger.getLogger(MainDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                codeValidationStatusLabel.setText(null);
            }

        });

    }

    ConstraintValidator validator;

    @Override
    public void createValidator() {
        validator = new ConstraintValidator(
                productCodeTextField,
                brandNameTextField,
                genericNameTextField,
                classificationChoiceBox,
                clientChoiceBox,
                areaChoiceBox,
                packSizeUnit,
                packSizeContainer,
                udfUnit,
                packSizeQty,
                udfContent,
                vrNoTextField
        );

    }

    @Override
    public boolean allFieldsValid() {
        try {
            return validator.validateFields() && productRestClient.isCodeValid(productCodeTextField.getText());
        } catch (ServerException ex) {
            Logger.getLogger(MainDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getInstruction() {
        return "Enter product name and details";
    }

}
