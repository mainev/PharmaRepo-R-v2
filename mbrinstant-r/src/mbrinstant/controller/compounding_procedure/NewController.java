/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.compounding_procedure;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import mbrinstant.entity.main.Product;
import mbrinstant.entity.mbr.Dosage;
import mbrinstant.entity.mbr.RawMaterialRequirement;
import mbrinstant.service.main.ProductService;
import mbrinstant.service.main.RawMaterialService;
import mbrinstant.service.mbr.RawMaterialRequirementService;

/**
 *
 * @author maine
 */
public class NewController implements Initializable {

    Product product;

    @FXML
    Label productNameLabel;
    @FXML
    TextArea contentTextArea;
    @FXML
    Button cancelButton;

    @FXML
    HBox dosageHbox;

    @FXML
    ChoiceBox<RawMaterialRequirement> rmReqChoiceBox;

    RawMaterialService rmService = new RawMaterialService();
    ProductService productService = new ProductService();
    RawMaterialRequirementService rmReqService = new RawMaterialRequirementService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productNameLabel.setText(product.toString());

        initDosagePane();
        cancelButton.setOnAction(e -> {
            cancel();
        });
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void cancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    TableView<Dosage> dosagesTable;

    @FXML
    Button addDosageButton;

    @FXML
    TextField percent;

    ObservableList<Dosage> dosagesList = FXCollections.observableArrayList();

    private void initDosagePane() {
        rmReqChoiceBox.setItems(rmReqService.getByUdfId(product.getUdfId().getId()));
        dosagesTable.setItems(dosagesList);
        addDosageButton.setOnAction(e -> {
            double p = Double.parseDouble(percent.getText()) * 0.01;
            Dosage dos = new Dosage(rmReqChoiceBox.getValue(), p);
            dosagesList.add(dos);
        });

    }
}
