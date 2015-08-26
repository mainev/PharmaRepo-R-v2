/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.batch_monitoring;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import mbrinstant.entity.mbr.Mbr;

/**
 *
 * @author maine
 */
public class MbrGenerationController implements Initializable, ChildController {

    private Mbr batch;
    private BatchMonitoringController parent;

    @FXML
    private Label message;
    @FXML
    private VBox vbox;

    private Button generateMbr = new Button("Generate MBR");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.batch = (Mbr) this.parent.getDataManager().getData();
        checkBatchStatus();

        generateMbr.setOnAction(e -> {
            BatchManager bm = (BatchManager) this.parent.getDataManager();
            bm.generateMbr();
            this.parent.reload();
        });
    }

    @Override
    public void setParentController(ParentController parentController) {
        this.parent = (BatchMonitoringController) parentController;
    }

    private void checkBatchStatus() {
        if (batch != null) {
            switch (batch.getStatus()) {
                case "RESERVED":
                    message.setText("Batch is ready for MBR Generation.");
                    vbox.getChildren().clear();
                    vbox.getChildren().addAll(message, generateMbr);
                    break;
                case "PRINTED":
                case "DISPENSED":
                    message.setText("MBR printed.");
                    vbox.getChildren().clear();
                    vbox.getChildren().addAll(message);
                    break;

                default:
                    break;
            }
        }

    }

}
