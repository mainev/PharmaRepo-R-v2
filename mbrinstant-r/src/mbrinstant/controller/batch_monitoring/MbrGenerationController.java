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
import mbrinstant.controller.report_generation.mbr_generation.MbrGenerator;
import mbrinstant.entity.MbrStatus;
import mbrinstant.entity.mbr.Mbr;
import mbrinstant.exceptions.ServerException;
import mbrinstant.rest_client.mbr.SingletonMbrRestClient;

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
    private Button reprint = new Button("Reprint MBR");

    SingletonMbrRestClient batchRestClient = SingletonMbrRestClient.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.batch = (Mbr) this.parent.getDataManager().getData();
        checkBatchStatus();

        generateMbr.setOnAction(e -> {

            BatchManager bm = (BatchManager) this.parent.getDataManager();
            bm.generateMbr();
            this.parent.reload();
        });

        reprint.setOnAction(e -> {
            try {
                Mbr batch = batchRestClient.getBatchById(this.batch.getId());

                MbrGenerator mbrGenerator = new MbrGenerator(batch);
                mbrGenerator.generateMbr();
            } catch (ServerException ex) {
                Logger.getLogger(MbrGenerationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @Override
    public void setParentController(ParentController parentController) {
        this.parent = (BatchMonitoringController) parentController;
    }

    private void checkBatchStatus() {
        if (batch != null) {
            if (batch.getStatus() == MbrStatus.RESERVED) {
                message.setText("Batch is ready for MBR Generation.");
                vbox.getChildren().clear();
                vbox.getChildren().addAll(message, generateMbr);
            } else if (batch.getStatus() == MbrStatus.PRINTED || batch.getStatus() == MbrStatus.DISPENSED) {
                message.setText("MBR printed.");
                vbox.getChildren().clear();
                vbox.getChildren().addAll(message, reprint);
            }

        }

    }

}
