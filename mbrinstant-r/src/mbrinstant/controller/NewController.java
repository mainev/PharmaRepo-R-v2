/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mbrinstant.ScreenNavigator;
import mbrinstant.controls.TextFieldWithSearch;
import mbrinstant.entity.main.Product;
import mbrinstant.entity.main.Unit;
import mbrinstant.entity.mbr.Mbr;
import mbrinstant.exception.ServerException;
import mbrinstant.rest_client.main.SingletonProductRestClient;
import mbrinstant.rest_client.main.SingletonUnitRestClient;
import mbrinstant.rest_client.mbr.SingletonMbrRestClient;
import mbrinstant.utils.DateConverter;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class NewController implements Initializable {

    @FXML
    GridPane gridPane;
    @FXML
    ChoiceBox<Unit> unitsChoiceBox;
    @FXML
    TextField batchSizeTextField;
    @FXML
    DatePicker mfgDateDatePicker;
    @FXML
    TextField poNoTextField;

    @FXML
    Button addButton;
    @FXML
    Button cancelButton;

    TextFieldWithSearch<Product> textFieldWithProductSearch;

    //rest client
    SingletonProductRestClient productRestClient = SingletonProductRestClient.getInstance();
    SingletonUnitRestClient unitRestClient = SingletonUnitRestClient.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            initTextFieldWithProductSearch();
            setChoiceBoxUnit();
            cancelButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    handleCancelButton();
                }
            });

            addButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    handleAddButton();
                }
            });
        } catch (ServerException ex) {
            Logger.getLogger(NewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * *
     * Assign product items that is searchable in the text field.
     */
    private void initTextFieldWithProductSearch() throws ServerException {

        ObservableList<Product> productList = productRestClient.getProductList();
        textFieldWithProductSearch = new TextFieldWithSearch(productList);
        textFieldWithProductSearch.setAlignment(Pos.CENTER);
        gridPane.add(textFieldWithProductSearch, 1, 0);

    }

    private void setChoiceBoxUnit() throws ServerException {
        unitsChoiceBox.setItems(unitRestClient.getUnitList());
    }

    private void handleCancelButton() {
        Stage stage = (Stage) gridPane.getScene().getWindow();
        stage.close();

    }

    private void handleAddButton() {
        try {
            createMbr();
        } catch (Exception e) {
        }
        Stage stage = (Stage) gridPane.getScene().getWindow();
        stage.close();
        ScreenNavigator.loadScreen(ScreenNavigator.BATCH_RECORD_SCREEN);
    }

    private Mbr createMbr() throws Exception {
        SingletonMbrRestClient mbrRestClient = SingletonMbrRestClient.getInstance();

        Product productId = textFieldWithProductSearch.getSelectedItem();
        Double batchSize = Double.parseDouble(batchSizeTextField.getText());
        Unit unitId = unitsChoiceBox.getValue();
        Date mfgDate = DateConverter.convertLocalDateToDate(mfgDateDatePicker.getValue());
        Date expDate = DateConverter.convertLocalDateToDate(mfgDateDatePicker.getValue().plusYears(productId.getShelfLife()));
        String poNo = poNoTextField.getText();

        return mbrRestClient.createNewBatch(productId, batchSize, mfgDate, expDate, poNo, unitId);

    }

}
