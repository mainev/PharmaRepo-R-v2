/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.product.ProductWizard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import mbrinstant.controls.CharacterTextField;
import mbrinstant.controls.InputValidator;
import mbrinstant.controls.NumberTextField;
import mbrinstant.entity.main.Area;
import mbrinstant.entity.main.Classification;
import mbrinstant.entity.main.Client;
import mbrinstant.entity.main.Container;
import mbrinstant.entity.main.PackSize;
import mbrinstant.entity.main.Unit;
import mbrinstant.service.main.AreaService;
import mbrinstant.service.main.ClassificationService;
import mbrinstant.service.main.ClientService;
import mbrinstant.service.main.ContainerService;
import mbrinstant.service.main.ProductService;
import mbrinstant.service.main.UnitService;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class MainDetailsController implements Initializable, PageController {

    @FXML
    AnchorPane mainDetailsPane;
    @FXML
    CharacterTextField productCodeTextField;
    @FXML
    Label codeValidationStatusLabel;
    @FXML
    CharacterTextField brandNameTextField;
    @FXML
    CharacterTextField genericNameTextField;
    @FXML
    ChoiceBox<Classification> classificationChoiceBox;
    @FXML
    ChoiceBox<Client> clientChoiceBox;
    @FXML
    CharacterTextField vrNoTextField;
    @FXML
    NumberTextField shelfLifeTextField;
    @FXML
    ChoiceBox<Area> areaChoiceBox;
    @FXML
    ChoiceBox<PackSize> packSizeChoiceBox;
    @FXML
    NumberTextField packSizeQty;
    @FXML
    ChoiceBox<Unit> packSizeUnit;
    @FXML
    ChoiceBox<Container> packSizeContainer;
    @FXML
    NumberTextField udfContent;
    @FXML
    ChoiceBox<Unit> udfUnit;

    //services
    ClassificationService classificationService = new ClassificationService();
    ClientService clientService = new ClientService();
    AreaService areaService = new AreaService();
    UnitService unitService = new UnitService();
    ContainerService containerService = new ContainerService();
    ProductService productService = new ProductService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        classificationChoiceBox.setUserData(true);
        clientChoiceBox.setUserData(true);
        areaChoiceBox.setUserData(true);
        packSizeUnit.setUserData(true);
        packSizeContainer.setUserData(true);
        udfUnit.setUserData(true);
        
        classificationChoiceBox.setItems(classificationService.getClassificationList());
        clientChoiceBox.setItems(clientService.getClientList());
        areaChoiceBox.setItems(areaService.getAreaList());
        packSizeUnit.setItems(unitService.getUnitList());
        packSizeContainer.setItems(containerService.getContainerList());
        udfUnit.setItems(unitService.getUnitList());

        initCodeTextField();
        createValidator();
    }

    private void initCodeTextField() {

        productCodeTextField.textProperty().addListener((ob, ov, nv) -> {
            if (!nv.isEmpty()) {
                if (productService.isCodeUnique(nv)) {
                    codeValidationStatusLabel.setText("Code '" + nv + "' is available.");
                    codeValidationStatusLabel.setTextFill(Color.web("green"));

                } else {
                    codeValidationStatusLabel.setText("Code '" + nv + "' is already used. Please use another code.");
                    codeValidationStatusLabel.setTextFill(Color.web("red"));
                }
            } else {
                codeValidationStatusLabel.setText(null);
            }

        });

    }

    InputValidator validator;

    @Override
    public void createValidator() {
        validator = new InputValidator(
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
        return validator.validateFields() && productService.isCodeUnique(productCodeTextField.getText());
    }
    @Override
    public String getInstruction() {
        return "1. Enter product name and details";
    }

}
