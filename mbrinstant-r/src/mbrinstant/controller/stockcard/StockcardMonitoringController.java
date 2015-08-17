/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.stockcard;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import mbrinstant.controls.CustomAlertDialog;
import mbrinstant.controls.MyNotifications;
import mbrinstant.entity.sqlsvr_copy.StockCardC;
import mbrinstant.entity.transaction.StockCardTxn;
import mbrinstant.exceptions.ServerException;
import mbrinstant.rest_client.sqlsvr_copy.SingletonStockCardRestClient;
import mbrinstant.utils.DateFormatter;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class StockcardMonitoringController implements Initializable {

    @FXML
    AnchorPane stockCardInfo;
    @FXML
    VBox transactionInfo;

    @FXML
    TextField controlNoTextField;

    @FXML
    TableView<StockCardTxn> txnTable;
    @FXML
    TableColumn<StockCardTxn, String> colQty;
    @FXML
    TableColumn<StockCardTxn, String> colPurpose;
    @FXML
    TableColumn<StockCardTxn, String> colStatus;

    SimpleBooleanProperty displayInfo = new SimpleBooleanProperty();

    //rest client
    SingletonStockCardRestClient stockCardRestClient = SingletonStockCardRestClient.getInstance();

    @FXML
    private AnchorPane mainPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            if (isUserPermitted()) {
                Bindings.bindBidirectional(displayInfo, stockCardInfo.visibleProperty());
                Bindings.bindBidirectional(displayInfo, transactionInfo.visibleProperty());
                displayInfo.setValue(Boolean.FALSE);
                initControlNoTextField();
                initTransactionTable();
            } else {
                CustomAlertDialog.showAccessDeniedDialog();
                mainPane.setDisable(true);
            }
        } catch (ServerException ex) {
            Logger.getLogger(StockcardMonitoringController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private boolean isUserPermitted() throws ServerException {
        SingletonStockCardRestClient.getInstance().getStockCardByControlNo("controlpm1");
        return SingletonStockCardRestClient.getInstance().getResponseHandler().isSuccessful();
    }

    private void initTransactionTable() {
        txnTable.setItems(txnList);

        colQty.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getQty() + " " + c.getValue().getUnitId()));
        colStatus.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getMbrId().getStatus()));
        colPurpose.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getMbrId().getBatchNo()));
    }

    private void initControlNoTextField() {
        controlNoTextField.setTooltip(new Tooltip("Type the control no and then press ENTER"));

        controlNoTextField.setOnAction(e -> {

            if (!controlNoTextField.getText().isEmpty()) {
                try {
                    StockCardC result = stockCardRestClient.getStockCardByControlNo(controlNoTextField.getText());
                    if (result != null) {
                        displayStockCardDetails(result);
                        displayInfo.setValue(Boolean.TRUE);
                    } else {
                        MyNotifications.displayError("No results found");
                        displayInfo.setValue(Boolean.FALSE);
                    }
                } catch (ServerException ex) {
                    Logger.getLogger(StockcardMonitoringController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                displayInfo.setValue(Boolean.FALSE);
            }
        });
    }

    @FXML
    Label itemLabel;
    @FXML
    Label controlNoLabel;
    @FXML
    Label warehouseLabel;
    @FXML
    Label companyLabel;
    @FXML
    Label unitCostLabel;
    @FXML
    Label quantityLabel;
    @FXML
    Label lotNoLabel;
    @FXML
    Label mfgDateLabel;
    @FXML
    Label expDateLabel;
    @FXML
    Label stockStatusLabel;
    @FXML
    Label statusLabel;
    @FXML
    Label inOutModeLabel;

    ObservableList<StockCardTxn> txnList = FXCollections.observableArrayList();

    private void displayStockCardDetails(StockCardC stockCard) {
        itemLabel.setText(stockCard.getItemId().getDescs());
        controlNoLabel.setText(stockCard.getControlNo());
        warehouseLabel.setText(stockCard.getWarehouseId().getDescs());
        companyLabel.setText(stockCard.getCompanyId().getDescs());
        unitCostLabel.setText(String.valueOf(stockCard.getUnitCost()));
        quantityLabel.setText(stockCard.getQty() + " " + stockCard.getUom());
        lotNoLabel.setText(stockCard.getLotNo());
        mfgDateLabel.setText(DateFormatter.convertToString(stockCard.getMfgDate()));
        expDateLabel.setText(DateFormatter.convertToString(stockCard.getExpDate()));
        stockStatusLabel.setText(stockCard.getStockStatus());
        statusLabel.setText(stockCard.getStatus());
        inOutModeLabel.setText(stockCard.getInoutMode());

        txnList.setAll(stockCard.getStockCardTxnList());

    }
}
