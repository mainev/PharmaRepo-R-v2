/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.product.ProductWizard;

import java.awt.Toolkit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import mbrinstant.controls.ConstraintValidator;
import mbrinstant.controls.CustomTextArea;
import mbrinstant.entity.mbr.BottlingProcedure;
import mbrinstant.entity.mbr.ManufacturingProcedure;
import mbrinstant.exception.ServerException;
import mbrinstant.rest_client.mbr.SingletonBottlingProcedureRestClient;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class BottlingProcedureController implements Initializable, PageController {

    @FXML
    TableView<BottlingProcedure> bottlingProcedureTable;
    @FXML
    TableColumn colAction;
    @FXML
    TableColumn colStep;
    @FXML
    TableColumn colContent;

    @FXML
    CustomTextArea content;
    @FXML
    Button addButton;

    ObservableList<BottlingProcedure> bottlingProcedureList = FXCollections.observableArrayList();

    //rest client
    SingletonBottlingProcedureRestClient bottlingProcedureRestClient = SingletonBottlingProcedureRestClient.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initBottlingProcedureTable();
        createValidator();

        addButton.setOnAction(e -> {
            if (validator.validateFields()) {
                BottlingProcedure bp = new BottlingProcedure(content.getText());
                bottlingProcedureList.add(bp);
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        });

        bottlingProcedureList.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
                short i = 1;
                for (BottlingProcedure bp : bottlingProcedureList) {
                    bp.setStepNumber(i);
                    i++;
                }
                //  refreshTable(packagingOperationTable);
            }
        });
    }

    public void createBottlingProcedures(ManufacturingProcedure mfg) throws ServerException {
        if (!bottlingProcedureList.isEmpty()) {
            for (BottlingProcedure bp : bottlingProcedureList) {
                bottlingProcedureRestClient.createBottlingProcedure(mfg.getId(), bp);
            }
        }
    }

    private void initBottlingProcedureTable() {
        bottlingProcedureTable.setItems(bottlingProcedureList);
        colAction.setSortable(false);

        // define a simple boolean cell value for the action column so that the column will only be shown for non-empty rows.
        colAction.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BottlingProcedure, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<BottlingProcedure, Boolean> features) {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        });

        // create a cell value factory with an add button for each row in the table.
        colAction.setCellFactory(new Callback<TableColumn<BottlingProcedure, Boolean>, TableCell<BottlingProcedure, Boolean>>() {
            @Override
            public TableCell<BottlingProcedure, Boolean> call(TableColumn<BottlingProcedure, Boolean> erCol) {
                return new ActionCell(bottlingProcedureTable);
            }
        });
    }

    public class ActionCell extends TableCell<BottlingProcedure, Boolean> {

        HBox hbox = new HBox();
        Button delete = new Button("Remove");

        public ActionCell(TableView table) {
            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().add(delete);

            delete.setOnAction(e -> {
                table.getSelectionModel().select(getTableRow().getIndex());
                bottlingProcedureList.remove(getTableRow().getIndex());

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
    ConstraintValidator validator;

    @Override
    public void createValidator() {
        validator = new ConstraintValidator(
                content
        );
    }

    @Override
    public boolean allFieldsValid() {
        //advance to next page if
        return !bottlingProcedureList.isEmpty();
    }

    @Override
    public String getInstruction() {
        return "Set bottling procedure";
    }

}
