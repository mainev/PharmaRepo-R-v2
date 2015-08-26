/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.batch_monitoring;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import mbrinstant.FXMLLocations;
import mbrinstant.ScreenNavigator;
import mbrinstant.controller.ard_batch_management.ListController;
import mbrinstant.controller.mmd_batch_management.MbrRequirementHelper;
import mbrinstant.controls.CustomAlertDialog;
import mbrinstant.controls.MyNotifications;
import mbrinstant.entity.mbr.Mbr;
import mbrinstant.entity.transaction.StockCardTxn;
import mbrinstant.exceptions.ServerException;
import mbrinstant.rest_client.mbr.SingletonMbrRestClient;
import mbrinstant.rest_client.sqlsvr_copy.SingletonStockCardRestClient;
import mbrinstant.rest_client.transaction.SingletonStockCardTxnRestClient;
import mbrinstant.security.SingletonAuthorizationManager;

/**
 * FXML Controller class to be replaced with MMDBatchManagementController
 *
 * @author maine
 */
public class BatchManager implements DataManager {

    private Mbr batch;

    //rest client
    SingletonMbrRestClient mbrRestClient = SingletonMbrRestClient.getInstance();
    SingletonStockCardTxnRestClient stockCardTxnRestClient = SingletonStockCardTxnRestClient.getInstance();
    SingletonAuthorizationManager authManager = SingletonAuthorizationManager.getInstance();

    //method names
    private final String CANCEL_RESERVATION = "cancel_reservation";
    private final String DISPENSE_MATERIALS = "dispense_batch_materials";

    public BatchManager(Mbr batch) {
        this.batch = batch;
    }

    public void reloadData() throws ServerException {
        this.batch = mbrRestClient.getBatchById(batch.getId());
    }

    @Override
    public void setData(Object object) {
        this.batch = (Mbr) object;
    }

    @Override
    public Object getData() {
        return batch;
    }

    public Mbr createNewBatch(Mbr batch) throws ServerException {
        return mbrRestClient.createNewBatch(batch);
    }

    public void cancelReservation(Mbr batch) throws ServerException {
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
                // ScreenNavigator.loadScreen(FXMLLocations.MMD_BATCH_MANAGEMENT_VIEW);

            }
        } else {
            CustomAlertDialog.showAccessDeniedDialog();
        }

    }

    public void dispenseBatchMaterials(Mbr batch) throws ServerException {
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

    MbrRequirementHelper mbrHandler;//must be final
    private final String RESERVE_MATERIAL = "reserve_material_req";
    SingletonStockCardRestClient stockCardRestClient = SingletonStockCardRestClient.getInstance();

    public void reserveMaterials() throws ServerException {
        if (authManager.isUserPermitted(RESERVE_MATERIAL)) {
            if (mbrHandler.isManufacturable()) {
                //show confirmation dialog
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Reservation");
                alert.setHeaderText("Confirm Material Reservation");
                alert.setContentText("Are you sure you want to reserve the needed materials for this batch?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {

                    try {
                        MyNotifications.displayInformation("MATERIALS RESERVED!");
                        System.out.println("depleted stockcard " + mbrHandler.getDepletedStockCardList());
                        //saved all allocated raw materials to the database
                        mbrHandler.getControlledRmList().forEach(crm -> {
                            crm.getTxnList().forEach(txn -> {
                                stockCardTxnRestClient.createNewStockCardTxn(batch.getId(), txn.getStockCard().getId(), txn);
                            });
                        });
                        //saved all allocated packaging materials to the database
                        mbrHandler.getControlledPmList().forEach(crm -> {
                            crm.getTxnList().forEach(txn -> {
                                stockCardTxnRestClient.createNewStockCardTxn(batch.getId(), txn.getStockCard().getId(), txn);
                            });
                        });
                        //changed all depleted stockcard status in the database
                        mbrHandler.getDepletedStockCardList()
                                .forEach(d -> {
                                    try {
                                        stockCardRestClient.changeStockCardStatusToDepleted(d.getId());
                                    } catch (ServerException ex) {
                                        Logger.getLogger(CheckAvailabilityController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                });
                        //change the mbr status to 'RESERVED'
                        mbrRestClient.reserveBatch(batch);

                    } catch (ServerException ex) {
                        Logger.getLogger(CheckAvailabilityController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            } else {
                MyNotifications.displayError("INSUFFICIENT MATERIALS!");
            }
        } else {
            CustomAlertDialog.showAccessDeniedDialog();
        }
    }

    public void generateMbr() {

        //show confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirm MBR Generation");
        alert.setContentText("You are about to generate the MBR for this batch. Proceeding will disable the cancellation of reserved materials.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            try {
                mbrRestClient.printBatch(batch);
            } catch (ServerException ex) {
                Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
            }

            ScreenNavigator.loadScreen(FXMLLocations.BATCH_RECORD_SCREEN);

        }

    }
}
