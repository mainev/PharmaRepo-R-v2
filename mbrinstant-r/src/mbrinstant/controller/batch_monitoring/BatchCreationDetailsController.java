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
import javafx.scene.control.Label;
import mbrinstant.entity.mbr.Mbr;
import mbrinstant.utils.DateFormatter;

public class BatchCreationDetailsController implements Initializable, ChildController {

    @FXML
    private Label batchNoLabel;
    @FXML
    private Label productLabel;
    @FXML
    private Label batchSizeLabel;
    @FXML
    private Label mfgDateLabel;
    @FXML
    private Label expDateLabel;
    @FXML
    private Label poNoLabel;

    private Mbr batch;
    private ParentController parent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //add user permission here

        this.batch = (Mbr) this.parent.getDataManager().getData();
        System.out.println(batch);
        setBatchDetails();
    }

    @Override
    public void setParentController(ParentController parent) {
        this.parent = parent;
    }

    private void setBatchDetails() {
        batchNoLabel.setText(batch.getBatchNo());
        productLabel.setText(batch.getProductId().toString());
        batchSizeLabel.setText(batch.getBatchSize() + " " + batch.getUnitId());
        mfgDateLabel.setText(DateFormatter.convertToString(batch.getMfgDate()));
        expDateLabel.setText(DateFormatter.convertToString(batch.getExpDate()));
        poNoLabel.setText(batch.getPoNo());

    }

}
