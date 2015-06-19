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
import javafx.scene.control.TextField;
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
import mbrinstant.service.main.UnitService;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class MainDetailsController implements Initializable {
    
    @FXML
    TextField productCodeTextField;
    
    @FXML
    TextField brandNameTextField;
    
    @FXML
    TextField genericNameTextField;
    
    @FXML
    ChoiceBox<Classification> classificationChoiceBox;
    
    @FXML
    ChoiceBox<Client> clientChoiceBox;
    
    @FXML
    TextField vrNoTextField;
    
    @FXML
    TextField shelfLifeTextField;
    
    @FXML
    ChoiceBox<Area> areaChoiceBox;
    
    @FXML
    ChoiceBox<PackSize> packSizeChoiceBox;
    
    @FXML
    TextField packSizeQty;
    @FXML
    ChoiceBox<Unit> packSizeUnit;
    @FXML
    ChoiceBox<Container> packSizeContainer;
    
    @FXML
    TextField udfContent;
    @FXML
    ChoiceBox<Unit> udfUnit;

    //services
    ClassificationService classificationService = new ClassificationService();
    ClientService clientService = new ClientService();
    AreaService areaService = new AreaService();
    UnitService unitService = new UnitService();
    ContainerService containerService = new ContainerService();

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
    }    
    
}
