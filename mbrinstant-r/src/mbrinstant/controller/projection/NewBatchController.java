/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.projection;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import mbrinstant.controls.CustomComboBox;
import mbrinstant.controls.CustomTextField;
import mbrinstant.controls.NumberTextField;
import mbrinstant.controls.SearchTextField;
import mbrinstant.entity.main.Product;
import mbrinstant.entity.main.Unit;
import mbrinstant.entity.mbr.Mbr;
import mbrinstant.service.main.ProductService;
import mbrinstant.service.main.UnitService;
import mbrinstant.utils.DateConverter;

/**
 *
 * @author maine
 */
public class NewBatchController implements Initializable {

    @FXML
    SearchTextField<Product> productTextField;
    @FXML
    NumberTextField batchSizeTextField;
    @FXML
    CustomComboBox<Unit> unitComboBox;
    @FXML
    DatePicker mfgDateDatePicker;
    @FXML
    CustomTextField poNoTextField;
    @FXML
    Button cancelButton;
    @FXML
    Button addButton;

    ProjectionController parentController;

    //services
    ProductService productService = new ProductService();
    UnitService unitService = new UnitService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initProductTextField();
        initUnitComboBox();

        cancelButton.setOnAction(e -> {
            closeDialog();
        });

        addButton.setOnAction(e -> {
            Product productId = productTextField.getSelectedItem();
            Double batchSize = Double.parseDouble(batchSizeTextField.getText());
            Unit unitId = unitComboBox.getValue();
            Date mfgDate = DateConverter.convertLocalDateToDate(mfgDateDatePicker.getValue());
            Date expDate = DateConverter.convertLocalDateToDate(mfgDateDatePicker.getValue().plusYears(productId.getShelfLife()));
            String poNo = poNoTextField.getText();
            String batchNo = "";

            Mbr mbr = new Mbr(productId, batchSize, batchNo, mfgDate, expDate, poNo, unitId);
            parentController.addBatchRecord(mbr);
            closeDialog();
        });

    }

    private void initUnitComboBox() {
        unitComboBox.setItems(unitService.getUnitList());
    }

    public void initProductTextField() {
        productTextField.setItems(productService.getProductList());
    }

    private void closeDialog() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();

    }

    public void setParentController(ProjectionController parentController) {
        this.parentController = parentController;
    }

}
