/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.projection;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import mbrinstant.FXMLLocations;
import mbrinstant.controls.CustomAlertDialog;
import mbrinstant.controls.MyNotifications;
import mbrinstant.entity.mbr.Mbr;
import mbrinstant.entity.mbr.PackagingMaterialRequirement;
import mbrinstant.entity.mbr.RawMaterialRequirement;
import mbrinstant.exceptions.ServerException;
import mbrinstant.security.SingletonAuthorizationManager;
import mbrinstant.utils.Quantity;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class ProjectionController implements Initializable {

    @FXML
    Button newButton;
    @FXML
    Button printButton;

    @FXML
    TableView<Mbr> batchRecordTable;
    @FXML
    TableColumn colAction;
    @FXML
    TableColumn<Mbr, String> colBatchSize;
    @FXML
    TableView<BatchRm> batchRmTable;
    @FXML
    TableView<BatchPm> batchPmTable;

    ObservableList<Mbr> batchRecordList = FXCollections.observableArrayList();
    ObservableList<BatchRm> batchRmList = FXCollections.observableArrayList();
    ObservableList<BatchPm> batchPmList = FXCollections.observableArrayList();

    //rest client
    private SingletonAuthorizationManager authManager = SingletonAuthorizationManager.getInstance();

    //main method
    private final String MAIN_METHOD = "access_batch_projection";
    private final String PRINT_PROJECTION = "print_batch_projection";

    @FXML
    private AnchorPane mainPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            if (authManager.isUserPermitted(MAIN_METHOD)) {
                initBatchRecordTable();
                initBatchRmTable();
                initBatchPmTable();
                newButton.setOnAction(e -> {
                    openNewBatchDialog();
                });

                batchRecordList.addListener(new ListChangeListener() {
                    @Override
                    public void onChanged(ListChangeListener.Change c) {
                        System.out.println("list changed.. refreshing table");
                        Iterator<BatchRm> itr = batchRmList.iterator();
                        while (itr.hasNext()) {
                            BatchRm br = itr.next();
                            if (br.getRequiredQuantity().getValue() == 0) {
                                itr.remove();
                            }
                        }

                        Iterator<BatchPm> itr2 = batchPmList.iterator();
                        while (itr2.hasNext()) {
                            BatchPm bp = itr2.next();
                            if (bp.getRequiredQuantity().getValue() == 0) {
                                itr2.remove();
                            }
                        }
                        refreshTable(batchRecordTable);
                        refreshTable(batchRmTable);
                        refreshTable(batchPmTable);
                    }
                });
            } else {
                CustomAlertDialog.showAccessDeniedDialog();
                mainPane.setDisable(true);
            }
        } catch (ServerException ex) {

            Logger.getLogger(ProjectionController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    Map<String, BatchRm> batchRmMap = new HashMap();
    Map<String, BatchPm> batchPmMap = new HashMap();

    public void addBatchRecord(Mbr mbr) {
        batchRecordList.add(mbr);
        List<RawMaterialRequirement> rmReqList = mbr.getProductId().getUdfId().getRawMaterialRequirementList();
        List<PackagingMaterialRequirement> pmReqList = mbr.getProductId().getUdfId().getPackagingMaterialRequirementList();

        for (RawMaterialRequirement rmReq : rmReqList) {
            BatchRm bRm = batchRmMap.get(rmReq.getRawMaterialId().getCode());
            if (bRm == null) {
                bRm = new BatchRm();
                bRm.setRawMaterial(rmReq.getRawMaterialId());
                batchRmList.add(bRm);
                batchRmMap.put(rmReq.getRawMaterialId().getCode(), bRm);
            }
            Quantity next = new Quantity(rmReq.getNewQuantity(), rmReq.getNewUnit());
            bRm.addToMap(String.valueOf(rmReq.hashCode()), next);
        }

        for (PackagingMaterialRequirement pmReq : pmReqList) {
            BatchPm bPm = batchPmMap.get(pmReq.getPackagingMaterialId().getCode());
            if (bPm == null) {
                bPm = new BatchPm();
                bPm.setPackagingMaterial(pmReq.getPackagingMaterialId());
                batchPmList.add(bPm);
                batchPmMap.put(pmReq.getPackagingMaterialId().getCode(), bPm);
            }
            Quantity next = new Quantity(pmReq.getNewQuantity(), pmReq.getUnitId().getName());
            bPm.addToMap(String.valueOf(pmReq.hashCode()), next);
        }
    }

    private void initBatchRecordTable() {
        batchRecordTable.setItems(batchRecordList);
        colBatchSize.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getBatchSize() + " " + c.getValue().getUnitId()));
        colAction.setSortable(false);

        // define a simple boolean cell value for the action column so that the column will only be shown for non-empty rows.
        colAction.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mbr, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Mbr, Boolean> features) {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        });

        // create a cell value factory with an add button for each row in the table.
        colAction.setCellFactory(new Callback<TableColumn<Mbr, Boolean>, TableCell<Mbr, Boolean>>() {
            @Override
            public TableCell<Mbr, Boolean> call(TableColumn<Mbr, Boolean> rmCol) {
                return new ActionCell();
            }
        });

    }

    //Remove button for the batchRecordTable in the colAction column
    public class ActionCell extends TableCell<Mbr, Boolean> {

        HBox hbox = new HBox();
        Button delete = new Button("Remove");

        public ActionCell() {

            hbox.setAlignment(Pos.CENTER);
            hbox.setSpacing(5);
            hbox.getChildren().addAll(delete);
            delete.setPrefWidth(100);
            delete.setOnAction(e -> {

                batchRecordTable.getSelectionModel().select(getTableRow().getIndex());
                Mbr mbr = batchRecordTable.getSelectionModel().getSelectedItem();

                List<RawMaterialRequirement> rmReqList = mbr.getProductId().getUdfId().getRawMaterialRequirementList();
                List<PackagingMaterialRequirement> pmReqList = mbr.getProductId().getUdfId().getPackagingMaterialRequirementList();

                for (RawMaterialRequirement rmr : rmReqList) {
                    BatchRm brm = getBatchRm(rmr.getRawMaterialId().getCode());
                    brm.removeFromMap(String.valueOf(rmr.hashCode()));
                    batchRmMap.remove(rmr.getRawMaterialId().getCode());
                }

                for (PackagingMaterialRequirement pmReq : pmReqList) {
                    BatchPm bpm = getBatchPm(pmReq.getPackagingMaterialId().getCode());
                    System.out.println("removing " + batchPmMap.get(pmReq.getPackagingMaterialId().getCode()));
                    bpm.removeFromMap(String.valueOf(pmReq.hashCode()));
                    batchPmMap.remove(pmReq.getPackagingMaterialId().getCode());
                }

                batchRecordList.remove(getTableRow().getIndex());

            });

        }

        @Override
        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setGraphic(hbox);
            } else {
                setGraphic(null);
            }
        }

    }

    private void initBatchRmTable() {
        batchRmTable.setItems(batchRmList);

    }

    private void initBatchPmTable() {
        batchPmTable.setItems(batchPmList);

    }

    private BatchRm getBatchRm(String rmCode) {
        for (BatchRm br : batchRmList) {
            if (br.getRawMaterial().getCode().equals(rmCode)) {
                return br;
            }
        }
        return null;
    }

    private BatchPm getBatchPm(String pmCode) {
        for (BatchPm br : batchPmList) {
            if (br.getPackagingMaterial().getCode().equals(pmCode)) {
                return br;
            }
        }
        return null;
    }

    @FXML
    private void showPrintMaterialsDialog() throws ServerException {
        if (authManager.isUserPermitted(PRINT_PROJECTION)) {
            if (!batchRecordList.isEmpty()) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXMLLocations.PRINT_PROJECTION_DIALOG));

                    Parent root1 = (Parent) fxmlLoader.load();
                    PrintOptionsController controller = fxmlLoader.getController();
                    controller.setParentController(this);
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setTitle("Print Bill of Materials");
                    stage.setScene(new Scene(root1));
                    stage.show();
                } catch (IOException e) {
                    CustomAlertDialog.showExceptionDialog(e);
                }
            } else {
                MyNotifications.displayError("Nothing to print");
            }
        } else {
            CustomAlertDialog.showAccessDeniedDialog();
        }

    }

    private void openNewBatchDialog() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXMLLocations.ADD_BATCH_IN_PROJECTION_DIALOG));
            NewBatchController controller = new NewBatchController();
            fxmlLoader.setController(controller);
            controller.setParentController(this);
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add Batch Entry");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            CustomAlertDialog.showExceptionDialog(e);
        }

    }

    public static void refreshTable(TableView tableView) {
        for (int i = 0; i < tableView.getColumns().size(); i++) {
            ((TableColumn) (tableView.getColumns().get(i))).setVisible(false);
            ((TableColumn) (tableView.getColumns().get(i))).setVisible(true);
        }
    }
}
