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
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import mbrinstant.FXMLLocations;
import mbrinstant.entity.mbr.Mbr;
import mbrinstant.exceptions.ServerException;
import mbrinstant.utils.Config;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class BatchMonitoringController implements Initializable, ParentController {

    private BatchManager batchManager;

    private Mbr batch;
    @FXML
    private ListView<String> processList;

    @FXML
    private AnchorPane mainPane;

    Config cf = new Config();

    public BatchMonitoringController(BatchManager batchManager) {
        this.batchManager = batchManager;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //add user permission control here...

        this.reload();

    }

    private void showBatchCreation() {
        if (batch != null) {
            ChildController controller = new BatchCreationDetailsController();
            controller.setParentController(this);
            cf.loadAnchorPane(mainPane, FXMLLocations.BATCH_CREATION_DETAILS_SCREEN, controller);
        } else {
            ChildController controller = new BatchCreationFormController();
            controller.setParentController(this);
            cf.loadAnchorPane(mainPane, FXMLLocations.BATCH_CREATION_FORM_SCREEN, controller);
        }
    }

    private void showMaterialAllocationStatus() {
        if (batch != null) {
            ChildController controller = new MaterialAllocationController();
            controller.setParentController(this);
            cf.loadAnchorPane(mainPane, FXMLLocations.MATERIAL_ALLOCATION_SCREEN, controller);
        }
    }

    private void showMbrGeneration() {
        if (batch != null) {
            ChildController controller = new MbrGenerationController();
            controller.setParentController(this);
            cf.loadAnchorPane(mainPane, FXMLLocations.MBR_GENERATION, controller);
        }
    }

    private void showDispensing() {
        if (batch != null) {
            ChildController controller = new DispenseController();
            controller.setParentController(this);
            cf.loadAnchorPane(mainPane, FXMLLocations.DISPENSE, controller);
        }
    }

    @Override
    public void reload() {
        try {
            if (batchManager.getData() != null) {
                batchManager.reloadData();
            }
            this.batch = (Mbr) batchManager.getData();
            initProcessList();
            setProcessListCellFactory();
            setProcessListListener();
            monitorSelectedBatch();

        } catch (ServerException ex) {
            Logger.getLogger(BatchMonitoringController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public DataManager getDataManager() {
        return batchManager;
    }

    private void initProcessList() {
        processList.getItems().clear();
        processList.getItems().addAll("Batch Creation", "Material Allocation", "MBR Generation", "Dispensing");
    }

    private void setProcessListCellFactory() {
        processList.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> list) {
                return new ProcessCell();
            }
        }
        );
    }

    private void setProcessListListener() {
        processList.getSelectionModel().selectedIndexProperty().addListener((ob, ov, nv) -> {
            switch ((int) nv) {
                case 0:
                    showBatchCreation();
                    break;
                case 1:
                    showMaterialAllocationStatus();
                    break;
                case 2:
                    showMbrGeneration();
                    break;
                case 3:
                    showDispensing();
                    break;
                default:
                    clearMainPane();
                    break;
            }
        });
    }

    private void clearMainPane() {
        mainPane.getChildren().clear();
    }

    private void monitorSelectedBatch() {
        if (batch != null) {
            switch (batch.getStatus()) {
                case "PENDING":
                    processList.getSelectionModel().select(1);
                    break;
                case "RESERVED":
                    processList.getSelectionModel().select(2);
                    break;
                case "PRINTED":
                    processList.getSelectionModel().select(3);
                    break;
                case "DISPENSED":
                    processList.getSelectionModel().select(3);
                    break;
                default:
                    break;
            }
        } else {
            processList.getSelectionModel().select(0);
        }
    }

    //sets what to display in the steps pane
    class ProcessCell extends ListCell<String> {

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                ImageView imgv = new ImageView();
                imgv.setFitWidth(30);
                imgv.setFitHeight(30);
                HBox hbox = new HBox();
                hbox.setPrefHeight(50);
                hbox.setAlignment(Pos.CENTER_RIGHT);
                hbox.getChildren().add(new Label(item));
                hbox.getChildren().add(imgv);
                if (batch != null) {
                    if (batch.getStatus().equals("PENDING")) {
                        switch (item) {
                            case "Batch Creation":
                                final Image image2 = new Image(BatchMonitoringController.class.getResourceAsStream("check.png"));
                                imgv.setImage(image2);
                                break;
                        }
                    } else if (batch.getStatus().equals("RESERVED")) {
                        switch (item) {
                            case "Batch Creation":
//                                final Image image2 = new Image(BatchMonitoringController.class.getResourceAsStream("check.png"));
//                                imgv.setImage(image2);
                            case "Material Allocation":
                                imgv.setImage(new Image(BatchMonitoringController.class.getResourceAsStream("check.png")));
                                break;

                        }
                    } else if (batch.getStatus().equals("PRINTED")) {
                        switch (item) {
                            case "Batch Creation":
//                                final Image image2 = new Image(BatchMonitoringController.class.getResourceAsStream("check.png"));
//                                imgv.setImage(image2);
                            case "Material Allocation":
                            //        imgv.setImage(new Image(BatchMonitoringController.class.getResourceAsStream("check.png")));
                            case "MBR Generation":
                                imgv.setImage(new Image(BatchMonitoringController.class.getResourceAsStream("check.png")));
                                break;
                        }
                    } else if (batch.getStatus().equals("DISPENSED")) {
                        switch (item) {
                            case "Batch Creation":
//                                final Image image2 = new Image(BatchMonitoringController.class.getResourceAsStream("check.png"));
//                                imgv.setImage(image2);
                            case "Material Allocation":
                            //      imgv.setImage(new Image(BatchMonitoringController.class.getResourceAsStream("check.png")));
                            case "MBR Generation":
                            //        imgv.setImage(new Image(BatchMonitoringController.class.getResourceAsStream("check.png")));
                            case "Dispensing":
                                imgv.setImage(new Image(BatchMonitoringController.class.getResourceAsStream("check.png")));
                                break;
                        }
                    }
                } else {
                }
                setGraphic(hbox);
            } else {
                setGraphic(null);
            }
        }
    }

    public AnchorPane getMainPane() {
        return mainPane;
    }

}
