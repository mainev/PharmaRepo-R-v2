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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import mbrinstant.FXMLLocations;
import mbrinstant.controls.CustomAlertDialog;
import mbrinstant.entity.MbrStatus;
import mbrinstant.entity.mbr.Mbr;
import mbrinstant.exceptions.ServerException;
import mbrinstant.rest_client.mbr.SingletonMbrRestClient;
import mbrinstant.security.SingletonAuthorizationManager;
import mbrinstant.utils.Config;

/**
 *
 * @author maine
 */
public class MaterialAllocationController implements Initializable, ChildController {

    private Mbr batch;
    private BatchMonitoringController parent;

    @FXML
    private Label message;
    @FXML
    private VBox vbox;

    private final Button checkAvailability = new Button("Check Availability");
    private final Button cancelReservation = new Button("Cancel Reservation");
    private final Button viewAllocation = new Button("View Allocation");

    //rest client
    SingletonMbrRestClient batchRestClient = SingletonMbrRestClient.getInstance();
    SingletonAuthorizationManager authManager = SingletonAuthorizationManager.getInstance();

    private final String CHECK_MATERIAL_AVAILABILITY = "check_material_availability";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //add user permission here...

        this.batch = (Mbr) this.parent.getDataManager().getData();
        checkBatchStatus();

        checkAvailability.setOnAction(e -> {
            try {
                checkMaterialAvailability(batch);
            } catch (ServerException ex) {
                Logger.getLogger(MaterialAllocationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        cancelReservation.setOnAction(e -> {
            try {
                BatchManager bm = (BatchManager) this.parent.getDataManager();
                bm.cancelReservation(batch);
                this.parent.reload();
            } catch (ServerException ex) {
                Logger.getLogger(MaterialAllocationController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    public void checkMaterialAvailability(Mbr batch) throws ServerException {
        if (authManager.isUserPermitted(CHECK_MATERIAL_AVAILABILITY)) {
            Config cf = new Config();
            ChildController controller = new CheckAvailabilityController();
            controller.setParentController(parent);
            cf.loadAnchorPane(parent.getMainPane(), FXMLLocations.CHECK_AVAILABILITY, controller);

        }
    }

    @Override
    public void setParentController(ParentController parentController) {
        this.parent = (BatchMonitoringController) parentController;
    }

    private void checkBatchStatus() {
        if (batch != null) {
            if (batch.getStatus() == MbrStatus.PENDING) {
                message.setText("Materials ready for allocation");
                vbox.getChildren().clear();
                vbox.getChildren().addAll(message, checkAvailability);
            } else if (batch.getStatus() == MbrStatus.RESERVED) {
                message.setText("Materials have been reserved.");
                vbox.getChildren().clear();
                vbox.getChildren().addAll(message, cancelReservation);
            } else if (batch.getStatus() == MbrStatus.PRINTED) {
                message.setText("Materials have been allocated.");
                vbox.getChildren().clear();
                vbox.getChildren().addAll(message, viewAllocation);
            } else if (batch.getStatus() == MbrStatus.DISPENSED) {
                message.setText("Materials have been allocated.");
                vbox.getChildren().clear();
                vbox.getChildren().addAll(message, viewAllocation);
            } else {
                try {
                    throw new Exception("Exception in MaterialAllocationController.");
                } catch (Exception ex) {
                    CustomAlertDialog.showExceptionDialog(ex);
                    Logger.getLogger(MaterialAllocationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }
}
