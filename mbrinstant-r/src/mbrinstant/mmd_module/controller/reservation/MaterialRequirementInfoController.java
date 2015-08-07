/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.mmd_module.controller.reservation;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import mbrinstant.ScreenNavigator;
import mbrinstant.controls.MyNotifications;
import mbrinstant.entity.mbr.Mbr;
import mbrinstant.entity.transaction.StockCardTxn;
import mbrinstant.mmd_module.controller.reservation.MbrRequirementHandler.ControlledPackagingMaterial;
import mbrinstant.mmd_module.controller.reservation.MbrRequirementHandler.ControlledRawMaterial;
import mbrinstant.service.mbr.SMbrService;
import mbrinstant.service.sqlsvr_copy.SStockCardService;
import mbrinstant.service.transaction.SStockCardTxnService;
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
public class MaterialRequirementInfoController implements Initializable {

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

    Mbr mbr;
    MbrRequirementHandler mbrHandler;//must be final
    ObservableList<ControlledRawMaterial> controlledRmList = FXCollections.observableArrayList();
    ObservableList<ControlledPackagingMaterial> controlledPmList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //this will handle the allocation of materials
        mbrHandler = new MbrRequirementHandler(mbr);
        controlledRmList = mbrHandler.getControlledRmList();
        controlledPmList = mbrHandler.getControlledPmList();

        productLabel.setText(mbr.getProductId().toString());
        batchSizeLabel.setText(mbr.getBatchSize() + " " + mbr.getUnitId());
        batchNoLabel.setText(mbr.getBatchNo());

        printButton.setOnAction((ActionEvent e) -> {
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
        });
        reserveButton.setOnAction(e
                -> {
                    if (mbrHandler.isManufacturable()) {
                        //show confirmation dialog
                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation Reservation");
                        alert.setHeaderText("Confirm Material Reservation");
                        alert.setContentText("Are you sure you want to reserve the needed materials for this batch?");

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            MyNotifications.displayInformation("MATERIALS RESERVED!");
                            System.out.println("depleted stockcard " + mbrHandler.getDepletedStockCardList());
                            //saved all allocated raw materials to the database
                            controlledRmList.forEach(crm -> {
                                crm.getTxnList().forEach(txn -> {
                                    SStockCardTxnService.postStockCardTxn(mbr.getId(), txn.getStockCard().getId(), txn);
                                });
                            });
                            //saved all allocated packaging materials to the database
                            controlledPmList.forEach(crm -> {
                                crm.getTxnList().forEach(txn -> {
                                    SStockCardTxnService.postStockCardTxn(mbr.getId(), txn.getStockCard().getId(), txn);
                                });
                            });
                            //changed all depleted stockcard status in the database
                            mbrHandler.getDepletedStockCardList()
                            .forEach(d -> {
                                SStockCardService.changeStockCardStatus(d.getId());
                            });
                            //change the mbr status to 'RESERVED'
                            SMbrService.reserveMbr(mbr);

                            //close the info window
                            ScreenNavigator.loadScreen(ScreenNavigator.RESERVATION_FXML);
                            Stage stage = (Stage) reserveButton.getScene().getWindow();
                            stage.close();
                        }
                    } else {
                        MyNotifications.displayError("INSUFFICIENT MATERIALS!");
                    }
                }
        );

        cancelButton.setOnAction(e
                -> {
                    //close the info window
                    Stage stage = (Stage) cancelButton.getScene().getWindow();
                    stage.close();
                }
        );
        initControlledRmTable();

        initControlledPmTable();
    }

    private final Service jasperService = new Service() {
        @Override
        protected Task createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        Map<String, Object> params = new HashMap();
                        params.put("mbr", mbr);
                        params.put("controlled_rm_list", controlledRmList);
                        params.put("controlled_pm_list", controlledPmList);
                        JasperPrint jasperPrint = JasperFillManager.fillReport("report/reservation/reservation.jasper",
                                params, (new JREmptyDataSource()));

                        JasperViewer.viewReport(jasperPrint, false);
                    } catch (JRException ex) {
                        ex.printStackTrace();
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

    public Mbr getMbr() {
        return mbr;
    }

    public void setMbr(Mbr mbr) {
        this.mbr = mbr;
    }

    public void setControlledRmList(ObservableList<ControlledRawMaterial> controlledRmList) {
        this.controlledRmList = controlledRmList;
    }

}