/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.batch_monitoring;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import mbrinstant.controller.batch_monitoring.BatchItemAllocationHelper.ControlledItem;
import mbrinstant.controls.CustomAlertDialog;
import mbrinstant.entity.mbr.Mbr;
import mbrinstant.entity.transaction.StockCardTxn;
import mbrinstant.exceptions.ServerException;
import mbrinstant.rest_client.mbr.SingletonMbrRestClient;
import mbrinstant.rest_client.sqlsvr_copy.SingletonStockCardRestClient;
import mbrinstant.rest_client.transaction.SingletonStockCardTxnRestClient;
import mbrinstant.security.SingletonAuthorizationManager;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class CheckAvailabilityController implements Initializable, ChildController {

    private Mbr batch;
    private BatchMonitoringController parent;
    @FXML
    private TableView<ControlledItem> controlledRmTable;

    @FXML
    private TableColumn<ControlledItem, ControlledItem> colRmActualQty;
    @FXML
    private TableColumn<ControlledItem, ControlledItem> colRm;
    @FXML
    private TableColumn<ControlledItem, ControlledItem> colPm;
    @FXML
    private TableColumn colStatus;

    @FXML
    private TableView<ControlledItem> controlledPmTable;
    @FXML
    private TableColumn<ControlledItem, ControlledItem> colPmActualQty;
    @FXML
    private TableColumn colPmStatus;

    @FXML
    Label productLabel;
    @FXML
    Label batchSizeLabel;
    @FXML
    Label batchNoLabel;
    @FXML
    Button reserveButton;
    @FXML
    Button cancelButton;
    @FXML
    Button printButton;

    //rest client
    SingletonMbrRestClient mbrRestClient = SingletonMbrRestClient.getInstance();
    SingletonStockCardRestClient stockCardRestClient = SingletonStockCardRestClient.getInstance();
    SingletonStockCardTxnRestClient stockCardTxnRestClient = SingletonStockCardTxnRestClient.getInstance();
    private SingletonAuthorizationManager authManager = SingletonAuthorizationManager.getInstance();

    BatchManager batchManager;

    ObservableList<BatchItemAllocationHelper.ControlledItem> controlledRmList = FXCollections.observableArrayList();
    ObservableList<BatchItemAllocationHelper.ControlledItem> controlledPmList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //this will handle the allocation of materials
        try {
            this.batchManager = (BatchManager) this.parent.getDataManager();
            this.batch = (Mbr) batchManager.getData();
            this.batchManager.batchRequirementHandler = new BatchItemAllocationHelper(batch);

        } catch (Exception e) {
            e.printStackTrace();
            CustomAlertDialog.showExceptionDialog(e);
        }
        controlledRmList = batchManager.batchRequirementHandler.getControlledRawMatList();
        controlledPmList = batchManager.batchRequirementHandler.getControlledPackgMatList();

        productLabel.setText(batch.getProductId().toString());
        batchSizeLabel.setText(batch.getBatchSize() + " " + batch.getUnitId());
        batchNoLabel.setText(batch.getBatchNo());

        reserveButton.setOnAction(e -> {
            try {
                this.batchManager.reserveMaterials();
                this.parent.reload();
            } catch (ServerException ex) {
                Logger.getLogger(CheckAvailabilityController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        cancelButton.setOnAction(e
                -> {
                    this.parent.reload();
                }
        );

        printButton.setOnAction(e -> {
//            try {
//                this.batchManager.printProductFormulation(printButton);
//            } catch (ServerException ex) {
//                Logger.getLogger(CheckAvailabilityController.class.getName()).log(Level.SEVERE, null, ex);
//            }
        });
        initControlledRmTable();
        initControlledPmTable();
    }

    @Override
    public void setParentController(ParentController parentController) {
        this.parent = (BatchMonitoringController) parentController;
    }

    public void initControlledPmTable() {
        controlledPmTable.setItems(controlledPmList);
        colPm.setCellValueFactory(c -> new SimpleObjectProperty(c.getValue().getBatchItemReq()));
        colPmActualQty.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<ControlledItem, ControlledItem>, ObservableValue<ControlledItem>>() {

                    @Override
                    public ObservableValue<ControlledItem> call(TableColumn.CellDataFeatures<ControlledItem, ControlledItem> c) {
                        return new ReadOnlyObjectWrapper(c.getValue());
                    }
                });

        colPmActualQty.setCellFactory(new Callback<TableColumn<ControlledItem, ControlledItem>, TableCell<ControlledItem, ControlledItem>>() {
            @Override
            public TableCell<ControlledItem, ControlledItem> call(TableColumn<ControlledItem, ControlledItem> col) {
                return new PmActualQtyCell();
            }
        });
    }

    public void initControlledRmTable() {
        controlledRmTable.setItems(controlledRmList);
        colRm.setCellValueFactory(c -> new SimpleObjectProperty(c.getValue().getBatchItemReq()));

        colRmActualQty.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<ControlledItem, ControlledItem>, ObservableValue<ControlledItem>>() {

                    @Override
                    public ObservableValue<ControlledItem> call(TableColumn.CellDataFeatures<ControlledItem, ControlledItem> c) {
                        return new ReadOnlyObjectWrapper(c.getValue());
                    }
                });

        colRmActualQty.setCellFactory(new Callback<TableColumn<ControlledItem, ControlledItem>, TableCell<ControlledItem, ControlledItem>>() {
            @Override
            public TableCell<ControlledItem, ControlledItem> call(TableColumn<ControlledItem, ControlledItem> col) {
                return new RmActualQtyCell();
            }
        });

    }

    public class RmActualQtyCell extends TableCell<ControlledItem, ControlledItem> {

        VBox vbox = new VBox();
        GridPane grid = new GridPane();

        public RmActualQtyCell() {
            vbox.setAlignment(Pos.CENTER);
            vbox.getChildren().addAll(grid);

            ColumnConstraints colAmount = new ColumnConstraints();
            colAmount.setPercentWidth(50);
            colAmount.setHalignment(HPos.CENTER);
            ColumnConstraints colControlNo = new ColumnConstraints();
            colControlNo.setPercentWidth(50);
            colControlNo.setHalignment(HPos.CENTER);
            grid.getColumnConstraints().addAll(colAmount, colControlNo);

        }

        //Display text area if row is not empty
        @Override
        protected void updateItem(ControlledItem c, boolean empty) {
            super.updateItem(c, empty);
            if (c != null && !c.getTxnList().isEmpty()) {
                int amountColumnIndex = 0;
                int controlNoColumnIndex = 1;
                int rowIndex = 0;
                for (StockCardTxn txn : c.getTxnList()) {
                    grid.add(new Label(txn.getQty() + " " + txn.getUnitId().getName()), amountColumnIndex, rowIndex);
                    grid.add(new Label(txn.getTempStockCard().getControlNo()), controlNoColumnIndex, rowIndex);

                    rowIndex++;
                }

                setGraphic(vbox);
            } else {
                setGraphic(null);
            }
        }
    }

    public class PmActualQtyCell extends TableCell<ControlledItem, ControlledItem> {

        VBox vbox = new VBox();
        GridPane grid = new GridPane();

        public PmActualQtyCell() {
            vbox.setAlignment(Pos.CENTER);
            vbox.getChildren().addAll(grid);

            ColumnConstraints colAmount = new ColumnConstraints();
            colAmount.setPercentWidth(50);
            colAmount.setHalignment(HPos.CENTER);
            ColumnConstraints colControlNo = new ColumnConstraints();
            colControlNo.setPercentWidth(50);
            colControlNo.setHalignment(HPos.CENTER);
            grid.getColumnConstraints().addAll(colAmount, colControlNo);

        }

        @Override
        protected void updateItem(ControlledItem c, boolean empty) {
            super.updateItem(c, empty);
            if (c != null && !c.getTxnList().isEmpty()) {
                int amountColumnIndex = 0;
                int controlNoColumnIndex = 1;
                int rowIndex = 0;
                for (StockCardTxn txn : c.getTxnList()) {
                    grid.add(new Label(txn.getQty() + " " + txn.getUnitId().getName()), amountColumnIndex, rowIndex);
                    grid.add(new Label(txn.getTempStockCard().getControlNo()), controlNoColumnIndex, rowIndex);
                    rowIndex++;
                }

                setGraphic(vbox);
            } else {
                setGraphic(null);
            }
        }
    }

}
