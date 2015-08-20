/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.audit;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import mbrinstant.entity.audit.AuditTrail;
import mbrinstant.entity.mbr.Mbr;
import mbrinstant.entity.transaction.StockCardTxn;
import mbrinstant.exceptions.ServerException;
import mbrinstant.rest_client.audit.SingletonAuditRestClient;
import mbrinstant.utils.Serializer;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class AuditListController implements Initializable {

    @FXML
    AnchorPane mainPane;

    @FXML
    private TableView<AuditTrail> auditTable;
    @FXML
    private TableColumn<AuditTrail, String> colAction;
    @FXML
    private TableColumn<AuditTrail, String> colTable;
    @FXML
    private TableColumn<AuditTrail, Object> colDatetime;
    @FXML
    private TableColumn<AuditTrail, String> colUsername;
    @FXML
    private TableColumn<AuditTrail, String> colMethod;

    //right side pane
    @FXML
    private TableView<Field> dataCaptureTable;
    @FXML
    private TableColumn<Field, Object> colFieldName;
    @FXML
    private TableColumn<Field, Object> colAfter;
    @FXML
    private TableColumn<Field, Object> colBefore;

    //rest client
    private SingletonAuditRestClient auditClient = SingletonAuditRestClient.getInstance();

    private ObservableList<AuditTrail> auditList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //add user authorization here...

            initAuditTable();

            setAuditTableListener();
        } catch (ServerException ex) {
            Logger.getLogger(AuditListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setAuditTableListener() {
        auditTable.getSelectionModel().selectedItemProperty().addListener((ob, ov, nv) -> {
            resetDataCaptureTable();

            switch (nv.getTableName()) {
                case "mbr":
                    setDataCaptureTableForBatch(nv);
                    break;
                case "stock_card_txn":
                    setDataCaptureTableForStockCardTxn(nv);
                    break;
                default:
                    resetDataCaptureTable();
                    break;

            }

        });
    }

    private void initAuditTable() throws ServerException {
        auditList = auditClient.getAuditList();
        auditTable.setItems(auditList);

        //set cell value factory for all columns
        colAction.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getAction()));
        colTable.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTableName()));
        colDatetime.setCellValueFactory(c -> new SimpleObjectProperty(c.getValue().getDatetime()));
        colUsername.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getUsername()));
        colMethod.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getMethod()));
    }

    private void resetDataCaptureTable() {
        dataCaptureTable.getItems().clear();
        colFieldName.setCellValueFactory(null);
        colFieldName.setCellValueFactory(null);
    }

    private void setDataCaptureTableForBatch(AuditTrail audit) {
        List<Field> batchFields = new ArrayList();
        try {
            Class batchClass = Mbr.class;

            batchFields.add(batchClass.getDeclaredField("id"));
            batchFields.add(batchClass.getDeclaredField("product_id"));
            batchFields.add((batchClass.getDeclaredField("batchSize")));
            batchFields.add((batchClass.getDeclaredField("unit_id")));
            batchFields.add((batchClass.getDeclaredField("batchNo")));
            batchFields.add((batchClass.getDeclaredField("mfgDate")));
            batchFields.add((batchClass.getDeclaredField("expDate")));
            batchFields.add((batchClass.getDeclaredField("poNo")));
            batchFields.add((batchClass.getDeclaredField("status")));

        } catch (NoSuchFieldException | SecurityException ex) {
            Logger.getLogger(AuditListController.class.getName()).log(Level.SEVERE, null, ex);
        }

        dataCaptureTable.setItems(FXCollections.observableArrayList(batchFields));
        colFieldName.setCellValueFactory(c -> new SimpleObjectProperty(c.getValue().getName()));

        colBefore.setCellValueFactory(c -> new SimpleObjectProperty(getBatchFieldValue(c.getValue().getName(), audit.getOldValue(), Mbr.class)));
        colAfter.setCellValueFactory(c -> new SimpleObjectProperty(getBatchFieldValue(c.getValue().getName(), audit.getNewValue(), Mbr.class)));

    }

    private void setDataCaptureTableForStockCardTxn(AuditTrail audit) {
        List<Field> batchFields = new ArrayList();
        try {
            Class batchClass = StockCardTxn.class;

            batchFields.add(batchClass.getDeclaredField("id"));
            batchFields.add(batchClass.getDeclaredField("qty"));
            batchFields.add(batchClass.getDeclaredField("stock_card_id"));
            batchFields.add(batchClass.getDeclaredField("unit_id"));
            batchFields.add(batchClass.getDeclaredField("mbr_id"));

        } catch (NoSuchFieldException | SecurityException ex) {
            Logger.getLogger(AuditListController.class.getName()).log(Level.SEVERE, null, ex);
        }

        dataCaptureTable.setItems(FXCollections.observableArrayList(batchFields));
        colFieldName.setCellValueFactory(c -> new SimpleObjectProperty(c.getValue().getName()));

        colBefore.setCellValueFactory(c -> new SimpleObjectProperty(getBatchFieldValue(c.getValue().getName(), audit.getOldValue(), StockCardTxn.class)));
        colAfter.setCellValueFactory(c -> new SimpleObjectProperty(getBatchFieldValue(c.getValue().getName(), audit.getNewValue(), StockCardTxn.class)));

    }

    private Object getBatchFieldValue(String fieldName, String newValue, Class clas) {
        if (newValue.isEmpty()) {
            return "";
        }
        try {
//            Object result = Serializer.deserialize(newValue, Mbr.class);
//            Class cl = result.getClass();
//            Field f = cl.getDeclaredField(fieldName);
//            f.setAccessible(true);
//            return f.get(result);

            //.//  result = Serializer.deserialize(newValue, Mbr.class);
            Class cl = Serializer.deserialize(newValue, clas).getClass();
            Field f = cl.getDeclaredField(fieldName);
            f.setAccessible(true);
            return f.get(Serializer.deserialize(newValue, clas));

        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(AuditListController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
