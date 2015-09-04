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
import mbrinstant.controller.batch_monitoring.BatchItemAllocationHelper.ControlledItem;
import mbrinstant.controller.report_generation.mbr_generation.MbrGenerator;
import mbrinstant.controls.CustomAlertDialog;
import mbrinstant.controls.MyNotifications;
import mbrinstant.entity.ItemReqStatusEnum;
import mbrinstant.entity.StockStatus;
import mbrinstant.entity.mbr.BatchItemRequirement;
import mbrinstant.entity.mbr.Mbr;
import mbrinstant.entity.sqlsvr_copy.StockCardC;
import mbrinstant.entity.transaction.StockCardTxn;
import mbrinstant.exceptions.ServerException;
import mbrinstant.rest_client.mbr.SingletonBatchItemRequirementRestClient;
import mbrinstant.rest_client.mbr.SingletonMbrRestClient;
import mbrinstant.rest_client.sqlsvr_copy.SingletonStockCardRestClient;
import mbrinstant.rest_client.transaction.SingletonStockCardTxnRestClient;
import mbrinstant.security.SingletonAuthorizationManager;
import mbrinstant.utils.UDFCalculator;

/**
 * FXML Controller class to be replaced with MMDBatchManagementController
 *
 * @author maine
 */
public class BatchManager implements DataManager {

    private Mbr batch;

    //rest client
    SingletonMbrRestClient batchRestClient = SingletonMbrRestClient.getInstance();
    SingletonStockCardTxnRestClient stockCardTxnRestClient = SingletonStockCardTxnRestClient.getInstance();
    SingletonAuthorizationManager authManager = SingletonAuthorizationManager.getInstance();
    SingletonBatchItemRequirementRestClient batchItemReqRestClient = SingletonBatchItemRequirementRestClient.getInstance();

    //method names
    private final String CANCEL_RESERVATION = "cancel_reservation";
    private final String DISPENSE_MATERIALS = "dispense_batch_materials";

    BatchItemAllocationHelper batchRequirementHandler;
    private final String RESERVE_MATERIAL = "reserve_material_req";
    private final String PRINT_PRODUCT_FORMULATION = "print_product_formulation";
    SingletonStockCardRestClient stockCardRestClient = SingletonStockCardRestClient.getInstance();

    public BatchManager(Mbr batch) {
        this.batch = batch;
    }

    @Override
    public void reloadData() throws ServerException {
        this.batch = batchRestClient.getBatchById(batch.getId());
    }

    @Override
    public void setData(Object object) {
        try {
            this.batch = (Mbr) object;
            reloadData();
        } catch (ServerException ex) {
            Logger.getLogger(BatchManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Object getData() {
        return batch;
    }

    public Mbr createNewBatch(Mbr batch) throws ServerException {
        Mbr newBatch = batchRestClient.createNewBatch(batch);
        createBatchItemRequirement(batchRestClient.getBatchById(newBatch.getId()));
        return newBatch;
    }

    public void createBatchItemRequirement(Mbr batch) throws ServerException {

        UDFCalculator udfCalculator = new UDFCalculator();
        udfCalculator.calculateRawMaterialBatchReq(batch);
        udfCalculator.calculatePackMatBatchReq(batch);
        List<BatchItemRequirement> list = udfCalculator.getBatchItemRequirementList();
        for (BatchItemRequirement bir : list) {
            batchItemReqRestClient.saveBatchItemRequirement(batch.getId(), bir);
        }

    }

    public void cancelReservation(Mbr batch) throws ServerException {
        if (authManager.isUserPermitted(CANCEL_RESERVATION)) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Cancel Reservation");
            alert.setHeaderText("Confirm Cancellation");
            alert.setContentText("Are you sure you want to cancel the reservation for this batch?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                List<BatchItemRequirement> birList = batch.getBatchItemRequirementList();

                for (BatchItemRequirement bir : birList) {
                    List<StockCardTxn> txnList = bir.getStockCardTxnList();
                    Iterator<StockCardTxn> txnListIterator = txnList.iterator();
                    while (txnListIterator.hasNext()) {
                        StockCardTxn txn = txnListIterator.next();
                        StockCardC mainStk = stockCardTxnRestClient.getStockCard(txn);
                        stockCardRestClient.updateStockCardStockStatus(mainStk, StockStatus.AVAILABLE);
                        stockCardTxnRestClient.deleteStockCardTxn(txn);
                    }
                    batchItemReqRestClient.updateBatchItemRequirementStatus(bir, ItemReqStatusEnum.PENDING);
                }

                batchRestClient.cancelBatchReservation(batch);

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
                batchRestClient.dispenseBatchMaterials(batch);
                MyNotifications.displayInformation("Materials have been dispensed");
                ScreenNavigator.loadScreen(FXMLLocations.MMD_BATCH_MANAGEMENT_VIEW);
            }
        } else {
            CustomAlertDialog.showAccessDeniedDialog();
        }
    }

    public void reserveMaterials() throws ServerException {
        if (authManager.isUserPermitted(RESERVE_MATERIAL)) {
            if (batchRequirementHandler.isManufacturable()) {
                //show confirmation dialog
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Reservation");
                alert.setHeaderText("Confirm Material Reservation");
                alert.setContentText("Are you sure you want to reserve the needed materials for this batch?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {

                    try {
                        MyNotifications.displayInformation("MATERIALS RESERVED!");
                        System.out.println("depleted stockcard " + batchRequirementHandler.getDepletedStockCardList());
                        for (ControlledItem ci : batchRequirementHandler.getControlledItemList()) {
                            for (StockCardTxn txn : ci.getTxnList()) {
                                BatchItemRequirement batchItem = txn.getTempBatchItem();
                                StockCardC stk = txn.getTempStockCard();
                                stockCardTxnRestClient.createNewStockCardTxn(batchItem, stk, txn);
                            }

                            batchItemReqRestClient.updateBatchItemRequirementStatus(ci.getBatchItemReq(), ItemReqStatusEnum.ALLOCATED);
                        }

                        //change all depleted stockcard status in the database
                        for (StockCardC depleted : batchRequirementHandler.getDepletedStockCardList()) {
                            stockCardRestClient.changeStockCardStatusToDepleted(depleted.getId());
                        }

                        //change the mbr status to 'RESERVED'
                        batchRestClient.reserveBatch(batch);

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
                MbrGenerator mbrGenerator = new MbrGenerator(batchRestClient.getBatchById(this.batch.getId()));
                mbrGenerator.generateMbr();
                batchRestClient.printBatch(batch);
            } catch (ServerException ex) {
                Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
            }

            ScreenNavigator.loadScreen(FXMLLocations.BATCH_RECORD_SCREEN);

        }

    }

    /*
     public void printProductFormulation(Node node) throws ServerException {
     Button printButton = (Button) node;
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
     params.put("controlled_rm_list", batchRequirementHandler.getControlledRmList());
     params.put("controlled_pm_list", batchRequirementHandler.getControlledPmList());
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
     */
}
