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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import mbrinstant.controls.CustomTextField;
import mbrinstant.controls.CustomChoiceBox;
import mbrinstant.controls.ConstraintValidator;
import mbrinstant.controls.IntegerTextField;
import mbrinstant.controls.NumberTextField;
import mbrinstant.entity.main.Area;
import mbrinstant.entity.main.Classification;
import mbrinstant.entity.main.Client;
import mbrinstant.entity.main.Container;
import mbrinstant.entity.main.PackSize;
import mbrinstant.entity.main.Product;
import mbrinstant.entity.main.Unit;
import mbrinstant.entity.mbr.Udf;
import mbrinstant.service.main.AreaService;
import mbrinstant.service.main.ClassificationService;
import mbrinstant.service.main.ClientService;
import mbrinstant.service.main.ContainerService;
import mbrinstant.service.main.PackSizeService;
import mbrinstant.service.main.ProductService;
import mbrinstant.service.main.UnitService;
import mbrinstant.service.mbr.ManufacturingProcedureService;
import mbrinstant.service.mbr.UdfService;

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
    CustomChoiceBox<Client> clientChoiceBox;
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

    //services
    ClassificationService classificationService = new ClassificationService();
    ClientService clientService = new ClientService();
    AreaService areaService = new AreaService();
    UnitService unitService = new UnitService();
    ContainerService containerService = new ContainerService();
    ProductService productService = new ProductService();
    PackSizeService packSizeService = new PackSizeService();
    UdfService udfService = new UdfService();
    ManufacturingProcedureService manufacturingProcedureService = new ManufacturingProcedureService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        classificationChoiceBox.setItems(classificationService.getClassificationList());
        clientChoiceBox.setItems(clientService.getClientList());
        areaChoiceBox.setItems(areaService.getAreaList());
        packSizeUnit.setItems(unitService.getUnitList());
        packSizeContainer.setItems(containerService.getContainerList());
        udfUnit.setItems(unitService.getUnitList());

        initCodeTextField();
        createValidator();
    }

    public PackSize createFinalPackSize() {
        return packSizeService.createPackSize(packSizeQty.getValue(), packSizeUnit.getValue(), packSizeContainer.getValue());
    }
    private Product finalProduct;

   
    public Product getFinalProduct() {
        return productService.getProductById(finalProduct.getId());
    }

    public void createFinalProduct() {
        if (validator.validateFields()) {
            String code = productCodeTextField.getText();
            String brandName = brandNameTextField.getText();
            String genericName = genericNameTextField.getText();
            String vrNo = vrNoTextField.getText();
            short shelfLife = (short) shelfLifeTextField.getValue();
            Area areaId = areaChoiceBox.getValue();
            Classification classificationId = classificationChoiceBox.getValue();
            Client clientId = clientChoiceBox.getValue();
            PackSize packSizeId = createFinalPackSize();
            
            finalProduct = productService.create(code, brandName, genericName, vrNo, shelfLife, areaId, classificationId, clientId, packSizeId);
            System.out.println("product created in db: "+finalProduct);
            udfService.createUdf(finalProduct.getId(), udfContent.getValue(), udfUnit.getValue());
            manufacturingProcedureService.create(finalProduct);
        }
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
        return validator.validateFields() && productService.isCodeUnique(productCodeTextField.getText());
    }

    @Override
    public String getInstruction() {
        return "Enter product name and details";
    }

}
