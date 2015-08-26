/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.batch_monitoring;

import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mbrinstant.controls.SearchTextField;
import mbrinstant.entity.main.Product;
import mbrinstant.entity.main.Unit;
import mbrinstant.entity.mbr.Mbr;
import mbrinstant.exceptions.ServerException;
import mbrinstant.rest_client.main.SingletonProductRestClient;
import mbrinstant.rest_client.main.SingletonUnitRestClient;
import mbrinstant.rest_client.mbr.SingletonMbrRestClient;
import mbrinstant.utils.DateConverter;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class BatchCreationFormController implements Initializable, ChildController {

    @FXML
    private SearchTextField<Product> productField;
    @FXML
    private TextField batchSizeField;
    @FXML
    private ComboBox<Unit> unitField;
    @FXML
    private TextField poNoField;
    @FXML
    private DatePicker mfgDateField;
    @FXML
    private TextField batchNoField;

    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    //rest clients
    SingletonProductRestClient productRestClient = SingletonProductRestClient.getInstance();
    SingletonUnitRestClient unitRestClient = SingletonUnitRestClient.getInstance();
    SingletonMbrRestClient batchRestClient = SingletonMbrRestClient.getInstance();

    ObservableList<Product> productList = FXCollections.observableArrayList();
    ObservableList<Unit> unitList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //add user permission here....
            setProductFieldItems();
            setUnitFieldItems();

            saveButton.setOnAction(e -> {
                try {

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Batch Confirmation");
                    alert.setHeaderText("Confirm Batch Creation");
                    alert.setContentText("You are about to create a new batch for this product. Are you sure you want to proceed?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        Mbr batch = createNewBatch();//returns new created batch from server
                        this.parent.getDataManager().setData(batchRestClient.getBatchById(batch.getId()));
                        this.parent.reload();

                    }

                } catch (ServerException ex) {
                    Logger.getLogger(BatchCreationFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } catch (ServerException ex) {
            Logger.getLogger(BatchCreationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private BatchMonitoringController parent;

    @Override
    public void setParentController(ParentController parent) {
        this.parent = (BatchMonitoringController) parent;
    }

    public Mbr createNewBatch() throws ServerException {
        Product product = productField.getSelectedItem();
        double batchSize = Double.parseDouble(batchSizeField.getText());
        Unit unit = unitField.getValue();
        String batchNo = batchNoField.getText();
        Date mfgDate = DateConverter.convertLocalDateToDate(mfgDateField.getValue());
        Date expDate = DateConverter.convertLocalDateToDate(mfgDateField.getValue().plusYears(product.getShelfLife()));
        String poNo = poNoField.getText();
        Mbr batch = new Mbr(product, batchSize, batchNo, mfgDate, expDate,
                poNo, unit);
        BatchManager batchManager = (BatchManager) this.parent.getDataManager();
        return batchManager.createNewBatch(batch);
    }

    @FXML
    private void closeBatchMonitoring() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private void setProductFieldItems() throws ServerException {
        productList = productRestClient.getProductList();
        productField.setItems(productList);
    }

    private void setUnitFieldItems() throws ServerException {
        unitList = unitRestClient.getUnitList();
        unitField.setItems(unitList);
    }

}
