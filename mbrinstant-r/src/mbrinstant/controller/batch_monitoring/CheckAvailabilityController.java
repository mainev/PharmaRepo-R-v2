/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.batch_monitoring;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import mbrinstant.controller.mmd_batch_management.MbrRequirementHelper;
import mbrinstant.controller.mmd_batch_management.MbrRequirementHelper.ControlledPackagingMaterial;
import mbrinstant.controller.mmd_batch_management.MbrRequirementHelper.ControlledRawMaterial;
import mbrinstant.controls.CustomAlertDialog;
import mbrinstant.entity.mbr.Mbr;
import mbrinstant.entity.transaction.StockCardTxn;
import mbrinstant.exceptions.ServerException;
import mbrinstant.rest_client.mbr.SingletonMbrRestClient;
import mbrinstant.rest_client.sqlsvr_copy.SingletonStockCardRestClient;
import mbrinstant.rest_client.transaction.SingletonStockCardTxnRestClient;
import mbrinstant.security.SingletonAuthorizationManager;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class CheckAvailabilityController implements Initializable, ChildController {

    private Mbr batch;
    private BatchMonitoringController parent;
    @FXML
    TableView<ControlledRawMaterial> controlledRmTable;

    @FXML
    TableColumn<ControlledRawMaterial, ControlledRawMaterial> colRmActualQty;
    @FXML
    TableColumn colStatus;

    @FXML
    TableView<ControlledPackagingMaterial> controlledPmTable;
    @FXML
    TableColumn<ControlledPackagingMaterial, ControlledPackagingMaterial> colPmActualQty;
    @FXML
    TableColumn colPmStatus;

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

    //method names
    private final String PRINT_PRODUCT_FORMULATION = "print_product_formulation";

    BatchManager batchManager;

    MbrRequirementHelper batchRequirementHandler;//must be final
    ObservableList<MbrRequirementHelper.ControlledRawMaterial> controlledRmList = FXCollections.observableArrayList();
    ObservableList<MbrRequirementHelper.ControlledPackagingMaterial> controlledPmList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //this will handle the allocation of materials
        try {
            this.batchManager = (BatchManager) this.parent.getDataManager();
            this.batch = (Mbr) batchManager.getData();
            this.batchManager.mbrHandler = new MbrRequirementHelper(batch);
            batchRequirementHandler = this.batchManager.mbrHandler;

        } catch (Exception e) {
            e.printStackTrace();
            CustomAlertDialog.showExceptionDialog(e);
        }
        controlledRmList = batchRequirementHandler.getControlledRmList();
        controlledPmList = batchRequirementHandler.getControlledPmList();

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
        initControlledRmTable();
        initControlledPmTable();
    }

    @Override
    public void setParentController(ParentController parentController) {
        this.parent = (BatchMonitoringController) parentController;
    }

    public void printProductFormulation() throws ServerException {
        if (authManager.isUserPermitted(PRINT_PRODUCT_FORMULATION)) {
            Stage primaryStage = (Stage) printButton.getScene().getWindow();
            primaryStage.getScene().getRoot()
                    .cursorProperty()
                    .bind(
                            Bindings
                            .when(jasperService.runningProperty())
                            .then(Cursor.WAIT)
                            .otherwise(Cursor.DEFAULT)
                    );
            jasperService.restart();
        } else {
            CustomAlertDialog.showAccessDeniedDialog();
        }
    }

    private final Service jasperService = new Service() {
        @Override
        protected Task createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        Map<String, Object> params = new HashMap();
                        params.put("mbr", batch);
                        params.put("controlled_rm_list", controlledRmList);
                        params.put("controlled_pm_list", controlledPmList);
                        JasperPrint jasperPrint = JasperFillManager.fillReport("report/reservation/reservation.jasper",
                                params, (new JREmptyDataSource()));

                        JasperViewer.viewReport(jasperPrint, false);
                    } catch (JRException ex) {
                        ex.printStackTrace();
                        CustomAlertDialog.showExceptionDialog(ex);
                    }

                    Thread.sleep(1000);
                    return null;
                }
            };
        }
    };

    public void initControlledPmTable() {
        controlledPmTable.setItems(controlledPmList);
        colPmActualQty.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<ControlledPackagingMaterial, ControlledPackagingMaterial>, ObservableValue<ControlledPackagingMaterial>>() {

                    @Override
                    public ObservableValue<ControlledPackagingMaterial> call(TableColumn.CellDataFeatures<ControlledPackagingMaterial, ControlledPackagingMaterial> c) {
                        return new ReadOnlyObjectWrapper(c.getValue());
                    }
                });

        colPmActualQty.setCellFactory(new Callback<TableColumn<ControlledPackagingMaterial, ControlledPackagingMaterial>, TableCell<ControlledPackagingMaterial, ControlledPackagingMaterial>>() {
            @Override
            public TableCell<ControlledPackagingMaterial, ControlledPackagingMaterial> call(TableColumn<ControlledPackagingMaterial, ControlledPackagingMaterial> col) {
                return new PmActualQtyCell();
            }
        });
    }

    public void initControlledRmTable() {
        controlledRmTable.setItems(controlledRmList);

        colRmActualQty.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<ControlledRawMaterial, ControlledRawMaterial>, ObservableValue<ControlledRawMaterial>>() {

                    @Override
                    public ObservableValue<ControlledRawMaterial> call(TableColumn.CellDataFeatures<ControlledRawMaterial, ControlledRawMaterial> c) {
                        return new ReadOnlyObjectWrapper(c.getValue());
                    }
                });

        colRmActualQty.setCellFactory(new Callback<TableColumn<ControlledRawMaterial, ControlledRawMaterial>, TableCell<ControlledRawMaterial, ControlledRawMaterial>>() {
            @Override
            public TableCell<ControlledRawMaterial, ControlledRawMaterial> call(TableColumn<ControlledRawMaterial, ControlledRawMaterial> col) {
                return new RmActualQtyCell();
            }
        });

    }

    public class RmActualQtyCell extends TableCell<ControlledRawMaterial, ControlledRawMaterial> {

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
        protected void updateItem(ControlledRawMaterial c, boolean empty) {
            super.updateItem(c, empty);
            if (c != null && !c.getTxnList().isEmpty()) {
                int amountColumnIndex = 0;
                int controlNoColumnIndex = 1;
                int rowIndex = 0;
                for (StockCardTxn txn : c.getTxnList()) {
                    grid.add(new Label(txn.getQty() + " " + txn.getUnitId().getName()), amountColumnIndex, rowIndex);
                    grid.add(new Label(txn.getStockCard().getControlNo()), controlNoColumnIndex, rowIndex);

                    rowIndex++;
                }

                setGraphic(vbox);
            } else {
                setGraphic(null);
            }
        }
    }

    public class PmActualQtyCell extends TableCell<ControlledPackagingMaterial, ControlledPackagingMaterial> {

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

        //Display text area if row is not empty
        @Override
        protected void updateItem(ControlledPackagingMaterial c, boolean empty) {
            super.updateItem(c, empty);
            if (c != null && !c.getTxnList().isEmpty()) {
                int amountColumnIndex = 0;
                int controlNoColumnIndex = 1;
                int rowIndex = 0;
                for (StockCardTxn txn : c.getTxnList()) {
                    grid.add(new Label(txn.getQty() + " " + txn.getUnitId().getName()), amountColumnIndex, rowIndex);
                    grid.add(new Label(txn.getStockCard().getControlNo()), controlNoColumnIndex, rowIndex);

                    rowIndex++;
                }

                setGraphic(vbox);
            } else {
                setGraphic(null);
            }
        }
    }

    public void setControlledRmList(ObservableList<ControlledRawMaterial> controlledRmList) {
        this.controlledRmList = controlledRmList;
    }

}
