/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.mmd_batch_management;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import mbrinstant.FXMLLocations;
import mbrinstant.ScreenNavigator;
import mbrinstant.controls.CustomAlertDialog;
import mbrinstant.controls.MyNotifications;
import mbrinstant.entity.mbr.Mbr;
import mbrinstant.entity.MbrStatus;
import mbrinstant.exceptions.ServerException;
import mbrinstant.rest_client.mbr.SingletonMbrRestClient;
import mbrinstant.rest_client.transaction.SingletonStockCardTxnRestClient;
import mbrinstant.security.SingletonAuthorizationManager;
import mbrinstant.utils.DateConverter;

/**
 * FXML Controller class to be replaced with MMDBatchManagementController
 *
 * @author maine
 */
public class MmdBatchManagementController implements Initializable {

    @FXML
    TableView<Mbr> mbrRecordTableView;
    @FXML
    TableColumn colAction2;
    @FXML
    TableColumn<Mbr, String> colBatchSize;
    @FXML
    TableColumn<Mbr, Date> colMfgDate;
    @FXML
    TableColumn<Mbr, Date> colExpDate;
    @FXML
    TableColumn<Mbr, String> colVrNo;
    @FXML
    TableColumn<Mbr, String> colShelfLife;

    ObservableList<Mbr> mbrList = FXCollections.observableArrayList();

    //rest client
    SingletonMbrRestClient mbrRestClient = SingletonMbrRestClient.getInstance();
    SingletonStockCardTxnRestClient stockCardTxnRestClient = SingletonStockCardTxnRestClient.getInstance();
    private SingletonAuthorizationManager authManager = SingletonAuthorizationManager.getInstance();

    //method names
    private final String MAIN_METHOD = "mmd_view_batch_record";
    private final String CHECK_MATERIAL_AVAILABILITY = "check_material_availability";
    private final String CANCEL_RESERVATION = "cancel_reservation";
    private final String DISPENSE_MATERIALS = "dispense_batch_materials";

    //main anchor pane
    @FXML
    private AnchorPane mainPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            if (authManager.isUserPermitted(MAIN_METHOD)) {
                initMbrRecordTable();

                mbrList.addListener(new ListChangeListener() {
                    @Override
                    public void onChanged(ListChangeListener.Change c) {
                        refreshTable(mbrRecordTableView);
                    }
                });
            } else {
                CustomAlertDialog.showAccessDeniedDialog();
                mainPane.setDisable(true);
            }
        } catch (ServerException ex) {
            Logger.getLogger(MmdBatchManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void initMbrRecordTable() throws ServerException {
        mbrList = mbrRestClient.getBatchList();
        mbrRecordTableView.setItems(mbrList);

        colBatchSize.setCellValueFactory(c -> new SimpleStringProperty("" + c.getValue().getBatchSize() + " " + c.getValue().getUnitId().getName()));
        colMfgDate.setCellValueFactory(c -> new SimpleObjectProperty(DateConverter.convertDateToLocalDate(c.getValue().getMfgDate())));
        colExpDate.setCellValueFactory(c -> new SimpleObjectProperty(DateConverter.convertDateToLocalDate(c.getValue().getExpDate())));
        colVrNo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getProductId().getVrNo()));
        colShelfLife.setCellValueFactory(c -> new SimpleObjectProperty("" + c.getValue().getProductId().getShelfLife()));

        colAction2.setSortable(false);
        colAction2.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Mbr, Mbr>, ObservableValue<Mbr>>() {

                    @Override
                    public ObservableValue<Mbr> call(TableColumn.CellDataFeatures<Mbr, Mbr> cp) {
                        return new ReadOnlyObjectWrapper(cp.getValue());
                    }
                });

        colAction2.setCellFactory(new Callback<TableColumn<Mbr, Mbr>, TableCell<Mbr, Mbr>>() {
            @Override
            public TableCell<Mbr, Mbr> call(TableColumn<Mbr, Mbr> col) {
                return new ActionCell2();
            }
        });
    }

    private void checkMaterialAvailability(Mbr batch) throws ServerException {
        if (authManager.isUserPermitted(CHECK_MATERIAL_AVAILABILITY)) {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXMLLocations.MATERIAL_REQUIREMENT_DIALOG));
                MaterialRequirementInfoController controller = new MaterialRequirementInfoController();
                //    controller.setMbr(batch);
                fxmlLoader.setController(controller);
                Parent root1 = (Parent) fxmlLoader.load();

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);

                stage.setTitle("Material Requirement Details");
                stage.setResizable(false);
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (IOException ex) {
                CustomAlertDialog.showExceptionDialog(ex);
            }
        } else {
            CustomAlertDialog.showAccessDeniedDialog();
        }
    }

    private void cancelReservation(Mbr batch) throws ServerException {
        /*
         if (authManager.isUserPermitted(CANCEL_RESERVATION)) {
         Alert alert = new Alert(AlertType.CONFIRMATION);
         alert.setTitle("Cancel Reservation");
         alert.setHeaderText("Confirm Cancellation");
         alert.setContentText("Are you sure you want to cancel the reservation for this batch?");

         Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK) {
         List<StockCardTxn> txnList = mbrRestClient.getBatchStockCardTxnList(batch.getId());

         Iterator<StockCardTxn> txnListIterator = txnList.iterator();
         while (txnListIterator.hasNext()) {
         StockCardTxn txn = txnListIterator.next();
         stockCardTxnRestClient.deleteStockCardTxn(txn.getId());
         }
         mbrRestClient.cancelBatchReservation(batch);
         ScreenNavigator.loadScreen(FXMLLocations.MMD_BATCH_MANAGEMENT_VIEW);

         }
         } else {
         CustomAlertDialog.showAccessDeniedDialog();
         }

         */

    }

    private void dispenseBatchMaterials(Mbr batch) throws ServerException {
        if (authManager.isUserPermitted(DISPENSE_MATERIALS)) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Dispense Materials");
            alert.setHeaderText("WARNING");
            alert.setContentText("This method will proceed to the dispension of required materials.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                mbrRestClient.dispenseBatchMaterials(batch);
                MyNotifications.displayInformation("Materials have been dispensed");
                ScreenNavigator.loadScreen(FXMLLocations.MMD_BATCH_MANAGEMENT_VIEW);
            }
        } else {
            CustomAlertDialog.showAccessDeniedDialog();
        }
    }

    public class ActionCell2 extends TableCell<Mbr, Mbr> {

        Button check = new Button("Check Availability");
        Button cancel = new Button("Cancel");
        Button dispense = new Button("Dispense");
        HBox checkHbox = new HBox();
        HBox cancelHbox = new HBox();
        HBox dispenseHbox = new HBox();

        public ActionCell2() {

            check.setPrefWidth(120);
            check.setTooltip(new Tooltip("Check the availability of materials for this batch."));
            checkHbox.setAlignment(Pos.CENTER);
            checkHbox.getChildren().add(check);

            cancel.setPrefWidth(120);
            cancel.setTooltip(new Tooltip("Cancel material reservation."));
            cancelHbox.setAlignment(Pos.CENTER);
            cancelHbox.getChildren().add(cancel);

            dispense.setPrefWidth(120);
            dispense.setTooltip(new Tooltip("This mbr is now available for dispensing."));
            dispenseHbox.setAlignment(Pos.CENTER);
            dispenseHbox.getChildren().add(dispense);

            check.setOnAction(e -> {

                try {
                    mbrRecordTableView.getSelectionModel().select(getTableRow().getIndex());
                    Mbr mbr = mbrRecordTableView.getSelectionModel().getSelectedItem();
                    checkMaterialAvailability(mbr);
                } catch (ServerException ex) {
                    Logger.getLogger(MmdBatchManagementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            cancel.setOnAction(e -> {

                try {
                    mbrRecordTableView.getSelectionModel().select(getTableRow().getIndex());
                    Mbr mbr = mbrRecordTableView.getSelectionModel().getSelectedItem();
                    cancelReservation(mbr);
                } catch (ServerException ex) {
                    Logger.getLogger(MmdBatchManagementController.class.getName()).log(Level.SEVERE, null, ex);
                }

            });

            dispense.setOnAction(e -> {
                try {
                    mbrRecordTableView.getSelectionModel().select(getTableRow().getIndex());
                    Mbr batch = mbrRecordTableView.getSelectionModel().getSelectedItem();
                    dispenseBatchMaterials(batch);
                } catch (ServerException ex) {
                    Logger.getLogger(MmdBatchManagementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }

        //Display button if row is not empty
        @Override
        protected void updateItem(Mbr mbr, boolean empty) {
            super.updateItem(mbr, empty);
            if (mbr != null) {
                if (mbr.getStatus().equals(MbrStatus.PENDING.toString())) {
                    setGraphic(checkHbox);
                } else if (mbr.getStatus().equals(MbrStatus.RESERVED.toString())) {
                    setGraphic(cancelHbox);
                } else if (mbr.getStatus().equals(MbrStatus.PRINTED.toString())) {
                    setGraphic(dispenseHbox);
                }
            } else {
                setGraphic(null);
            }
        }
    }

    public static void refreshTable(TableView tableView) {
        for (int i = 0; i < tableView.getColumns().size(); i++) {
            ((TableColumn) (tableView.getColumns().get(i))).setVisible(false);
            ((TableColumn) (tableView.getColumns().get(i))).setVisible(true);
        }
    }
}
