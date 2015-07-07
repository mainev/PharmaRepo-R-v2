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
import mbrinstant.controls.CustomTextArea;
import mbrinstant.controls.ConstraintValidator;
import mbrinstant.controls.IntegerTextField;
import mbrinstant.entity.mbr.ManufacturingProcedure;
import mbrinstant.entity.mbr.PackagingOperation;
import mbrinstant.service.mbr.PackagingOperationService;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class PackagingOperationController implements Initializable, PageController {

    @FXML
    TableView<PackagingOperation> packagingOperationTable;
    @FXML
    TableColumn colAction;
    @FXML
    TableColumn colContent;
    @FXML
    TableColumn colStep;
    @FXML
    TableColumn colDoneBy;
    @FXML
    TableColumn colCheckedBy;

    @FXML
    CustomTextArea content;
    @FXML
    CustomTextArea doneBy;
    @FXML
    CustomTextArea checkedBy;
    @FXML
    IntegerTextField part;
    @FXML
    Button addButton;

    ObservableList<PackagingOperation> packagingProcedureList = FXCollections.observableArrayList();
    
    //services
    PackagingOperationService packagingOperationService = new PackagingOperationService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        initPackagingProcedureTable();
        
        //set properties for validator
        part.setMinValue(1);
        part.setMaxValue(2);
        
        addButton.setOnAction(e -> {
            if (validator.validateFields()) {
                PackagingOperation ppo = new PackagingOperation(getContent(), getPart(), getDoneBy(), getCheckedBy());
                packagingProcedureList.add(ppo);
            }
        });

        packagingProcedureList.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
                short i = 1;
                for (PackagingOperation ppo : packagingProcedureList) {
                    ppo.setStepNumber(i);
                    i++;
                }
                //  refreshTable(packagingOperationTable);
            }
        });

         createValidator();
    }

    public void createPackagingOperations(ManufacturingProcedure mfg){
        if(!packagingProcedureList.isEmpty()){
            for(PackagingOperation ppo : packagingProcedureList){
                packagingOperationService.createPackagingOperation(mfg.getId(), ppo);
            }
        }
    }
    private void initPackagingProcedureTable() {
        packagingOperationTable.setItems(packagingProcedureList);

        colAction.setSortable(false);

        // define a simple boolean cell value for the action column so that the column will only be shown for non-empty rows.
        colAction.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PackagingOperation, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<PackagingOperation, Boolean> features) {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        });

        // create a cell value factory with an add button for each row in the table.
        colAction.setCellFactory(new Callback<TableColumn<PackagingOperation, Boolean>, TableCell<PackagingOperation, Boolean>>() {
            @Override
            public TableCell<PackagingOperation, Boolean> call(TableColumn<PackagingOperation, Boolean> erCol) {
                return new ActionCell(packagingOperationTable);
            }
        });
    }

    public class ActionCell extends TableCell<PackagingOperation, Boolean> {

        HBox hbox = new HBox();
        Button delete = new Button("Remove");

        public ActionCell(TableView table) {
            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().add(delete);

            delete.setOnAction(e -> {
                table.getSelectionModel().select(getTableRow().getIndex());
                packagingProcedureList.remove(getTableRow().getIndex());

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

    private String getContent() {
        return content.getText();
    }

    private Short getPart() {
        return Short.parseShort(part.getText());
    }

    private String getDoneBy() {

        return doneBy.getText();

    }

    private String getCheckedBy() {

        return checkedBy.getText();
    }

    ConstraintValidator validator;

    @Override
    public void createValidator() {
        validator = new ConstraintValidator(
                content, part, doneBy, checkedBy
        );
    }

    @Override
    public boolean allFieldsValid() {
        //advance to next page if 
        return !packagingProcedureList.isEmpty();
    }

    @Override
    public String getInstruction() {
        return "Set packaging operations";
    }

}
