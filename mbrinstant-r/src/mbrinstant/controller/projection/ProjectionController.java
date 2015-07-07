/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.projection;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mbrinstant.entity.mbr.Mbr;
import mbrinstant.entity.mbr.RawMaterialRequirement;
import mbrinstant.service.sqlsvr.ItemService;
import mbrinstant.service.sqlsvr.StockCardService;
import mbrinstant.utils.MetricCalculator;
import mbrinstant.utils.Quantity;
import mbrinstant.utils.UDFCalculator;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class ProjectionController implements Initializable {

    @FXML
    Button newButton;
    @FXML
    TableView<Mbr> batchRecordTable;
    @FXML
    TableColumn colAction;
    @FXML
    TableColumn<Mbr, String> colBatchSize;
    @FXML
    TableView<BatchRm> batchRmTable;
    @FXML
    TableColumn<BatchRm, String> colStockRm;
    @FXML
    TableColumn<BatchRm, String> colRequiredRm;

    ObservableList<Mbr> batchRecordList = FXCollections.observableArrayList();
    ObservableList<BatchRm> batchRmList = FXCollections.observableArrayList();

    //service
    StockCardService stockCardService = new StockCardService();
    ItemService itemService = new ItemService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        initBatchRecordTable();
        initBatchRmTable();
        newButton.setOnAction(e -> {
            openNewBatchDialog();
        });

        batchRecordList.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
                // short i = 1;
                System.out.println("list changed");
                refreshTable(batchRecordTable);
                refreshTable(batchRmTable);
            }
        });

    }

    public void addBatchRecord(Mbr mbr) {
        batchRecordList.add(mbr);
        UDFCalculator uc = new UDFCalculator();
        MetricCalculator mc = new MetricCalculator();
        uc.calculateRawMaterialBatchReq(mbr);
        List<RawMaterialRequirement> rmReqList = mbr.getProductId().getUdfId().getRawMaterialRequirementList();
        for (RawMaterialRequirement rmReq : rmReqList) {
            BatchRm bRm = isIn(rmReq.getRawMaterialId().getCode());
            if (bRm != null) {
                Quantity prev = new Quantity(bRm.getRequired(), bRm.getRequiredUom());
                Quantity next = new Quantity(rmReq.getNewQuantity(), rmReq.getNewUnit());
                System.out.println("rmreq qty: "+rmReq.getNewQuantity());
                System.out.println("prev: "+bRm.toString());
                bRm.setRequired(mc.add(prev, next).getValue());
                  System.out.println("next: "+bRm.toString());

            } else {
                BatchRm batchRm = new BatchRm();
                batchRm.setRawMaterial(rmReq.getRawMaterialId());
                batchRm.setStock(stockCardService.getMaterialQuantity(rmReq.getRawMaterialId().getCode()));
                batchRm.setUom(itemService.getItemUom(rmReq.getRawMaterialId().getCode()));
                batchRm.setRequired(rmReq.getNewQuantity());
                batchRm.setRequiredUom(rmReq.getNewUnit());
                batchRmList.add(batchRm);
            }
        }
    }

    private void initBatchRecordTable() {
        batchRecordTable.setItems(batchRecordList);
        colBatchSize.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getBatchSize() + " " + c.getValue().getUnitId()));
        colRequiredRm.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getRequired() + " " + c.getValue().getRequiredUom()));
    }

    private void initBatchRmTable() {
        batchRmTable.setItems(batchRmList);
        colStockRm.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getStock() + " " + c.getValue().getUom()));
    }

    private BatchRm isIn(String rmCode) {
        for (BatchRm br : batchRmList) {
            if (br.getRawMaterial().getCode().equals(rmCode)) {
                return br;
            }
        }
        return null;
    }

    private void openNewBatchDialog() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mbrinstant/view/projection/new_batch.fxml"));
            NewBatchController controller = new NewBatchController();
            fxmlLoader.setController(controller);
            controller.setParentController(this);
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add Batch Entry");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void refreshTable(TableView tableView) {
        for (int i = 0; i < tableView.getColumns().size(); i++) {
            ((TableColumn) (tableView.getColumns().get(i))).setVisible(false);
            ((TableColumn) (tableView.getColumns().get(i))).setVisible(true);
        }
    }
}
