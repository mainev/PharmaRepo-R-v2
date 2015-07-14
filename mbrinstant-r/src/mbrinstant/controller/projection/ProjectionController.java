/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.projection;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import mbrinstant.entity.mbr.Mbr;
import mbrinstant.entity.mbr.RawMaterialRequirement;
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
    //==added button
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
    //   StockCardService stockCardService = new StockCardService();
    //   ItemService itemService = new ItemService();
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

                Iterator<BatchRm> itr = batchRmList.iterator();
                while (itr.hasNext()) {
                    BatchRm br = itr.next();
                    if (br.getRequiredQuantity().getValue() == 0) {
                        itr.remove();
                    }
                }
                refreshTable(batchRecordTable);
                refreshTable(batchRmTable);
            }
        });

    }

    public void addBatchRecord(Mbr mbr) {
        batchRecordList.add(mbr);
        UDFCalculator uc = new UDFCalculator();
        uc.calculateRawMaterialBatchReq(mbr);
        List<RawMaterialRequirement> rmReqList = mbr.getProductId().getUdfId().getRawMaterialRequirementList();
        for (RawMaterialRequirement rmReq : rmReqList) {
            BatchRm bRm = isIn(rmReq.getRawMaterialId().getCode());
            if (bRm == null) {
                bRm = new BatchRm();
                bRm.setRawMaterial(rmReq.getRawMaterialId());
        //   bRm.setStock(stockCardService.getMaterialQuantity(rmReq.getRawMaterialId().getCode()));
            //    bRm.setUom(itemService.getItemUom(rmReq.getRawMaterialId().getCode()));
           
                batchRmList.add(bRm);
            }
            Quantity next = new Quantity(rmReq.getNewQuantity(), rmReq.getNewUnit());
            bRm.addToMap(String.valueOf(rmReq.hashCode()), next);
        }
    }

    private void initBatchRecordTable() {
        batchRecordTable.setItems(batchRecordList);
        colBatchSize.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getBatchSize() + " " + c.getValue().getUnitId()));
        colAction.setSortable(false);

        // define a simple boolean cell value for the action column so that the column will only be shown for non-empty rows.
        colAction.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mbr, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Mbr, Boolean> features) {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        });

        // create a cell value factory with an add button for each row in the table.
        colAction.setCellFactory(new Callback<TableColumn<Mbr, Boolean>, TableCell<Mbr, Boolean>>() {
            @Override
            public TableCell<Mbr, Boolean> call(TableColumn<Mbr, Boolean> rmCol) {
                return new ActionCell();
            }
        });
    }

    //Remove button for the batchRecordTable in the colAction column
    public class ActionCell extends TableCell<Mbr, Boolean> {

        HBox hbox = new HBox();
        Button delete = new Button("Remove");

        public ActionCell() {

            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().add(delete);
            delete.setOnAction(e -> {

                batchRecordTable.getSelectionModel().select(getTableRow().getIndex());
                Mbr mbr = batchRecordTable.getSelectionModel().getSelectedItem();

                List<RawMaterialRequirement> rmReqList = mbr.getProductId().getUdfId().getRawMaterialRequirementList();

                for (RawMaterialRequirement rmr : rmReqList) {
                    BatchRm brm = isIn(rmr.getRawMaterialId().getCode());
                    brm.removeFromMap(String.valueOf(rmr.hashCode()));
                }
                batchRecordList.remove(getTableRow().getIndex());
            });
        }

        @Override
        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setGraphic(hbox);
            } else {
                setGraphic(null);
            }
        }
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
