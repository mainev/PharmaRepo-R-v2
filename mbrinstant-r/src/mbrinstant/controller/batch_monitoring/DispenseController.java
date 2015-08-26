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
import mbrinstant.entity.mbr.Mbr;
import mbrinstant.exceptions.ServerException;

/**
 *
 * @author maine
 */
public class DispenseController implements Initializable, ChildController {

    private Mbr batch;
    private BatchMonitoringController parent;

    @FXML
    private Label message;
    @FXML
    private VBox vbox;

    private Button dispense = new Button("Dispense");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.batch = (Mbr) this.parent.getDataManager().getData();
        checkBatchStatus();

        dispense.setOnAction(e -> {
            try {
                BatchManager bm = (BatchManager) this.parent.getDataManager();
                bm.dispenseBatchMaterials(batch);
                this.parent.reload();
            } catch (ServerException ex) {
                Logger.getLogger(DispenseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void checkBatchStatus() {
        if (batch != null) {
            switch (batch.getStatus()) {
                case "PRINTED":
                    message.setText("Materials are ready for dispensing.");
                    vbox.getChildren().clear();
                    vbox.getChildren().addAll(message, dispense);
                    break;
                case "DISPENSED":
                    message.setText("Materials have been dispensed");
                    vbox.getChildren().clear();
                    vbox.getChildren().addAll(message);
                    break;
                default:
                    break;
            }
        }

    }

    @Override
    public void setParentController(ParentController parentController) {
        this.parent = (BatchMonitoringController) parentController;
    }

}
