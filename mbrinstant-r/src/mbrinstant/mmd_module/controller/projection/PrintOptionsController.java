/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.mmd_module.controller.projection;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class PrintOptionsController implements Initializable {

    @FXML
    RadioButton printByBatch;
    @FXML
    RadioButton printAll;
    @FXML
    RadioButton printProjection;

    private final ToggleGroup toggleGroup = new ToggleGroup();

    ProjectionController parentController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        printByBatch.setToggleGroup(toggleGroup);
        printAll.setToggleGroup(toggleGroup);
        printProjection.setToggleGroup(toggleGroup);

        printByBatch.setTooltip(new Tooltip("Prints the BOM of each batch per page."));
        printAll.setTooltip(new Tooltip("Prints the BOM of each batch per page with the summary for all batches."));
        printProjection.setTooltip(new Tooltip("Prints the BOM for all batches."));

        printByBatch.setOnMouseClicked(e -> {
            closeStage();
            System.out.println("You selected printByBatch");
            try {
                Map<String, Object> params = new HashMap();
                params.put("batch_record_list", parentController.batchRecordList);
                JasperPrint jasperPrint = JasperFillManager.fillReport("report/projection/bom_template.jasper",
                        params, (new JRBeanCollectionDataSource(parentController.batchRecordList)));
                JasperViewer.viewReport(jasperPrint, false);
            } catch (JRException ex) {
                ex.printStackTrace();
            }
        });

        printAll.setOnMouseClicked(e -> {
            closeStage();
            System.out.println("You selected printAll");
            try {
                Map<String, Object> params = new HashMap();
                params.put("batch_record_list", parentController.batchRecordList);
                params.put("batch_rm_list", parentController.batchRmList);
                params.put("batch_pm_list", parentController.batchPmList);
                JasperPrint jasperPrint = JasperFillManager.fillReport("report/projection/bom_template.jasper",
                        params, (new JRBeanCollectionDataSource(parentController.batchRecordList)));
                JasperViewer.viewReport(jasperPrint, false);
            } catch (JRException ex) {
                ex.printStackTrace();
            }
        });

        printProjection.setOnMouseClicked(e -> {
            closeStage();
            System.out.println("You selected printSummary");
            try {
                Map<String, Object> params = new HashMap();
                params.put("batch_record_list", parentController.batchRecordList);
                params.put("batch_rm_list", parentController.batchRmList);
                params.put("batch_pm_list", parentController.batchPmList);
                JasperPrint jasperPrint = JasperFillManager.fillReport("report/projection/summary_only_template.jasper",
                        params, (new JRBeanCollectionDataSource(parentController.batchRecordList)));
                JasperViewer.viewReport(jasperPrint, false);
            } catch (JRException ex) {
                ex.printStackTrace();
            }
        });
    }

    public ProjectionController getParentController() {
        return parentController;
    }

    public void setParentController(ProjectionController parentController) {
        this.parentController = parentController;
    }

    private void closeStage() {
        Stage stage = (Stage) printByBatch.getScene().getWindow();
        stage.close();
    }
}
