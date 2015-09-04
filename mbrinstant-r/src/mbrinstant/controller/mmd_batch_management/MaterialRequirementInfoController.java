/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.mmd_batch_management;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import mbrinstant.controller.batch_monitoring.BatchItemAllocationHelper;
import mbrinstant.controller.batch_monitoring.BatchItemAllocationHelper.ControlledItem;
import mbrinstant.entity.mbr.Mbr;
import mbrinstant.rest_client.mbr.SingletonMbrRestClient;
import mbrinstant.rest_client.sqlsvr_copy.SingletonStockCardRestClient;
import mbrinstant.rest_client.transaction.SingletonStockCardTxnRestClient;
import mbrinstant.security.SingletonAuthorizationManager;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class MaterialRequirementInfoController implements Initializable {

    @FXML
    TableView<ControlledItem> controlledRmTable;

    @FXML
    TableColumn<ControlledItem, ControlledItem> colRmActualQty;
    @FXML
    TableColumn colStatus;

    @FXML
    TableView<ControlledItem> controlledPmTable;
    @FXML
    TableColumn<ControlledItem, ControlledItem> colPmActualQty;
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

    Mbr mbr;
    BatchItemAllocationHelper mbrHandler;//must be final
//    ObservableList<ControlledRawMaterial> controlledRmList = FXCollections.observableArrayList();
//    ObservableList<ControlledPackagingMaterial> controlledPmList = FXCollections.observableArrayList();

    //rest client
    SingletonMbrRestClient mbrRestClient = SingletonMbrRestClient.getInstance();
    SingletonStockCardRestClient stockCardRestClient = SingletonStockCardRestClient.getInstance();
    SingletonStockCardTxnRestClient stockCardTxnRestClient = SingletonStockCardTxnRestClient.getInstance();
    private SingletonAuthorizationManager authManager = SingletonAuthorizationManager.getInstance();

    //method names
    private final String PRINT_PRODUCT_FORMULATION = "print_product_formulation";
    private final String RESERVE_MATERIAL = "reserve_material_req";

    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        //this will handle the allocation of materials
//        try {
//            mbrHandler = new BatchItemAllocationHelper(mbr);
//        } catch (Exception e) {
//            CustomAlertDialog.showExceptionDialog(e);
//        }
//        controlledRmList = mbrHandler.getControlledRmList();
//        controlledPmList = mbrHandler.getControlledPmList();
//
//        productLabel.setText(mbr.getProductId().toString());
//        batchSizeLabel.setText(mbr.getBatchSize() + " " + mbr.getUnitId());
//        batchNoLabel.setText(mbr.getBatchNo());
//
//        cancelButton.setOnAction(e
//                -> {
//                    //close the info window
//                    Stage stage = (Stage) cancelButton.getScene().getWindow();
//                    stage.close();
//                }
//        );
//        initControlledRmTable();
//
//        initControlledPmTable();
    }

//    @FXML
//    private void printProductFormulation() throws ServerException {
//        if (authManager.isUserPermitted(PRINT_PRODUCT_FORMULATION)) {
//            Stage primaryStage = (Stage) printButton.getScene().getWindow();
//            primaryStage.getScene().getRoot()
//                    .cursorProperty()
//                    .bind(
//                            Bindings
//                            .when(jasperService.runningProperty())
//                            .then(Cursor.WAIT)
//                            .otherwise(Cursor.DEFAULT)
//                    );
//            jasperService.restart();
//        } else {
//            CustomAlertDialog.showAccessDeniedDialog();
//        }
//    }
//
//    @FXML
//    private void reserveMaterials() throws ServerException {
//        if (authManager.isUserPermitted(RESERVE_MATERIAL)) {
//            if (mbrHandler.isManufacturable()) {
//                //show confirmation dialog
//                Alert alert = new Alert(AlertType.CONFIRMATION);
//                alert.setTitle("Confirmation Reservation");
//                alert.setHeaderText("Confirm Material Reservation");
//                alert.setContentText("Are you sure you want to reserve the needed materials for this batch?");
//
//                Optional<ButtonType> result = alert.showAndWait();
//                if (result.get() == ButtonType.OK) {
//
//                    try {
//                        MyNotifications.displayInformation("MATERIALS RESERVED!");
//                        System.out.println("depleted stockcard " + mbrHandler.getDepletedStockCardList());
//                        //saved all allocated raw materials to the database
//                        controlledRmList.forEach(crm -> {
//                            crm.getTxnList().forEach(txn -> {
//                                try {
//                                    stockCardTxnRestClient.createNewStockCardTxn(mbr.getId(), txn.getTempStockCard().getId(), txn);
//                                } catch (ServerException ex) {
//                                    Logger.getLogger(MaterialRequirementInfoController.class.getName()).log(Level.SEVERE, null, ex);
//                                }
//                            });
//                        });
//                        //saved all allocated packaging materials to the database
//                        controlledPmList.forEach(crm -> {
//                            crm.getTxnList().forEach(txn -> {
//                                try {
//                                    stockCardTxnRestClient.createNewStockCardTxn(mbr.getId(), txn.getTempStockCard().getId(), txn);
//                                } catch (ServerException ex) {
//                                    Logger.getLogger(MaterialRequirementInfoController.class.getName()).log(Level.SEVERE, null, ex);
//                                }
//                            });
//                        });
//                        //changed all depleted stockcard status in the database
//                        mbrHandler.getDepletedStockCardList()
//                                .forEach(d -> {
//                                    try {
//                                        stockCardRestClient.changeStockCardStatusToDepleted(d.getId());
//                                    } catch (ServerException ex) {
//                                        Logger.getLogger(MaterialRequirementInfoController.class.getName()).log(Level.SEVERE, null, ex);
//                                    }
//                                });
//                        //change the mbr status to 'RESERVED'
//                        mbrRestClient.reserveBatch(mbr);
//
//                        //close the info window
//                        ScreenNavigator.loadScreen(FXMLLocations.MMD_BATCH_MANAGEMENT_VIEW);
//                        Stage stage = (Stage) reserveButton.getScene().getWindow();
//                        stage.close();
//                    } catch (ServerException ex) {
//                        Logger.getLogger(MaterialRequirementInfoController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                }
//            } else {
//                MyNotifications.displayError("INSUFFICIENT MATERIALS!");
//            }
//        } else {
//            CustomAlertDialog.showAccessDeniedDialog();
//        }
//    }
//
//    private final Service jasperService = new Service() {
//        @Override
//        protected Task createTask() {
//            return new Task<Void>() {
//                @Override
//                protected Void call() throws Exception {
//                    try {
//                        Map<String, Object> params = new HashMap();
//                        params.put("mbr", mbr);
//                        params.put("controlled_rm_list", controlledRmList);
//                        params.put("controlled_pm_list", controlledPmList);
//                        JasperPrint jasperPrint = JasperFillManager.fillReport("report/reservation/reservation.jasper",
//                                params, (new JREmptyDataSource()));
//
//                        JasperViewer.viewReport(jasperPrint, false);
//                    } catch (JRException ex) {
//                        ex.printStackTrace();
//                        CustomAlertDialog.showExceptionDialog(ex);
//                    }
//
//                    Thread.sleep(1000);
//                    return null;
//                }
//            };
//        }
//    };
//
//    public void initControlledPmTable() {
//        controlledPmTable.setItems(controlledPmList);
//        colPmActualQty.setCellValueFactory(
//                new Callback<TableColumn.CellDataFeatures<ControlledPackagingMaterial, ControlledPackagingMaterial>, ObservableValue<ControlledPackagingMaterial>>() {
//
//                    @Override
//                    public ObservableValue<ControlledPackagingMaterial> call(TableColumn.CellDataFeatures<ControlledPackagingMaterial, ControlledPackagingMaterial> c) {
//                        return new ReadOnlyObjectWrapper(c.getValue());
//                    }
//                });
//
//        colPmActualQty.setCellFactory(new Callback<TableColumn<ControlledPackagingMaterial, ControlledPackagingMaterial>, TableCell<ControlledPackagingMaterial, ControlledPackagingMaterial>>() {
//            @Override
//            public TableCell<ControlledPackagingMaterial, ControlledPackagingMaterial> call(TableColumn<ControlledPackagingMaterial, ControlledPackagingMaterial> col) {
//                return new PmActualQtyCell();
//            }
//        });
//    }
//
//    public void initControlledRmTable() {
//        controlledRmTable.setItems(controlledRmList);
//
//        colRmActualQty.setCellValueFactory(
//                new Callback<TableColumn.CellDataFeatures<ControlledRawMaterial, ControlledRawMaterial>, ObservableValue<ControlledRawMaterial>>() {
//
//                    @Override
//                    public ObservableValue<ControlledRawMaterial> call(TableColumn.CellDataFeatures<ControlledRawMaterial, ControlledRawMaterial> c) {
//                        return new ReadOnlyObjectWrapper(c.getValue());
//                    }
//                });
//
//        colRmActualQty.setCellFactory(new Callback<TableColumn<ControlledRawMaterial, ControlledRawMaterial>, TableCell<ControlledRawMaterial, ControlledRawMaterial>>() {
//            @Override
//            public TableCell<ControlledRawMaterial, ControlledRawMaterial> call(TableColumn<ControlledRawMaterial, ControlledRawMaterial> col) {
//                return new RmActualQtyCell();
//            }
//        });
//
//    }
//
//    public class RmActualQtyCell extends TableCell<ControlledRawMaterial, ControlledRawMaterial> {
//
//        VBox vbox = new VBox();
//        GridPane grid = new GridPane();
//
//        public RmActualQtyCell() {
//            vbox.setAlignment(Pos.CENTER);
//            vbox.getChildren().addAll(grid);
//
//            ColumnConstraints colAmount = new ColumnConstraints();
//            colAmount.setPercentWidth(50);
//            colAmount.setHalignment(HPos.CENTER);
//            ColumnConstraints colControlNo = new ColumnConstraints();
//            colControlNo.setPercentWidth(50);
//            colControlNo.setHalignment(HPos.CENTER);
//            grid.getColumnConstraints().addAll(colAmount, colControlNo);
//
//        }
//
//        //Display text area if row is not empty
//        @Override
//        protected void updateItem(ControlledRawMaterial c, boolean empty) {
//            super.updateItem(c, empty);
//            if (c != null && !c.getTxnList().isEmpty()) {
//                int amountColumnIndex = 0;
//                int controlNoColumnIndex = 1;
//                int rowIndex = 0;
//                for (StockCardTxn txn : c.getTxnList()) {
//                    grid.add(new Label(txn.getQty() + " " + txn.getUnitId().getName()), amountColumnIndex, rowIndex);
//                    grid.add(new Label(txn.getTempStockCard().getControlNo()), controlNoColumnIndex, rowIndex);
//
//                    rowIndex++;
//                }
//
//                setGraphic(vbox);
//            } else {
//                setGraphic(null);
//            }
//        }
//    }
//
//    public class PmActualQtyCell extends TableCell<ControlledPackagingMaterial, ControlledPackagingMaterial> {
//
//        VBox vbox = new VBox();
//        GridPane grid = new GridPane();
//
//        public PmActualQtyCell() {
//            vbox.setAlignment(Pos.CENTER);
//            vbox.getChildren().addAll(grid);
//
//            ColumnConstraints colAmount = new ColumnConstraints();
//            colAmount.setPercentWidth(50);
//            colAmount.setHalignment(HPos.CENTER);
//            ColumnConstraints colControlNo = new ColumnConstraints();
//            colControlNo.setPercentWidth(50);
//            colControlNo.setHalignment(HPos.CENTER);
//            grid.getColumnConstraints().addAll(colAmount, colControlNo);
//
//        }
//
//        //Display text area if row is not empty
//        @Override
//        protected void updateItem(ControlledPackagingMaterial c, boolean empty) {
//            super.updateItem(c, empty);
//            if (c != null && !c.getTxnList().isEmpty()) {
//                int amountColumnIndex = 0;
//                int controlNoColumnIndex = 1;
//                int rowIndex = 0;
//                for (StockCardTxn txn : c.getTxnList()) {
//                    grid.add(new Label(txn.getQty() + " " + txn.getUnitId().getName()), amountColumnIndex, rowIndex);
//                    grid.add(new Label(txn.getTempStockCard().getControlNo()), controlNoColumnIndex, rowIndex);
//
//                    rowIndex++;
//                }
//
//                setGraphic(vbox);
//            } else {
//                setGraphic(null);
//            }
//        }
//    }
//
//    public Mbr getMbr() {
//        return mbr;
//    }
//
//    public void setMbr(Mbr mbr) {
//        this.mbr = mbr;
//    }
//
//    public void setControlledRmList(ObservableList<ControlledRawMaterial> controlledRmList) {
//        this.controlledRmList = controlledRmList;
//    }
}
