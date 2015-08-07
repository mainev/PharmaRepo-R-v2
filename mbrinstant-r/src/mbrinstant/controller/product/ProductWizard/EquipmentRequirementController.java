/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.product.ProductWizard;

import java.awt.Toolkit;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import mbrinstant.controls.ConstraintValidator;
import mbrinstant.controls.CustomChoiceBox;
import mbrinstant.controls.SearchTextField;
import mbrinstant.entity.main.Equipment;
import mbrinstant.entity.mbr.EquipmentRequirement;
import mbrinstant.entity.mbr.ManufacturingProcedure;
import mbrinstant.exception.ServerException;
import mbrinstant.rest_client.main.SingletonEquipmentRestClient;
import mbrinstant.rest_client.mbr.SingletonEquipmentRequirementRestClient;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class EquipmentRequirementController implements Initializable, PageController {

    @FXML
    SearchTextField<Equipment> equipmentTextField;
    @FXML
    CustomChoiceBox procedureChoiceBox;
    @FXML
    Button addEquipmentButton;

    @FXML
    TableView<EquipmentRequirement> equipmentReqTable;
    @FXML
    TableColumn colAction;
    @FXML
    TableColumn colEquipment;
    @FXML
    TableColumn colProcedure;

    ConstraintValidator validator;
    ObservableList<EquipmentRequirement> equipmentRequirementList = FXCollections.observableArrayList();

    //rest client
    SingletonEquipmentRestClient equipmentRestClient = SingletonEquipmentRestClient.getInstance();
    SingletonEquipmentRequirementRestClient equipmentReqRestClient = SingletonEquipmentRequirementRestClient.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            initEquipmentRequirementTable();
            equipmentTextField.setTextFieldMargin(new Insets(13, 0, 0, 0));
            equipmentTextField.setItems(equipmentRestClient.getEquipmentList());

            addEquipmentButton.setOnAction(e -> {
                if (validator.validateFields()) {
                    EquipmentRequirement temp = new EquipmentRequirement(getSelectedEquipment(), getProcedure());
                    equipmentRequirementList.add(temp);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            });
            createValidator();
        } catch (ServerException ex) {
            Logger.getLogger(EquipmentRequirementController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void createEquipmentRequirements(ManufacturingProcedure mfg) throws ServerException {
        for (EquipmentRequirement er : equipmentRequirementList) {
            equipmentReqRestClient.createEquipmentRequirement(mfg.getId(), er);
        }
    }

    private void initEquipmentRequirementTable() {
        equipmentReqTable.setItems(equipmentRequirementList);
        colAction.setSortable(false);

        // define a simple boolean cell value for the action column so that the column will only be shown for non-empty rows.
        colAction.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<EquipmentRequirement, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<EquipmentRequirement, Boolean> features) {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        });

        // create a cell value factory with an add button for each row in the table.
        colAction.setCellFactory(new Callback<TableColumn<EquipmentRequirement, Boolean>, TableCell<EquipmentRequirement, Boolean>>() {
            @Override
            public TableCell<EquipmentRequirement, Boolean> call(TableColumn<EquipmentRequirement, Boolean> erCol) {
                return new ActionCell(equipmentReqTable);
            }
        });

    }

    public class ActionCell extends TableCell<EquipmentRequirement, Boolean> {

        HBox hbox = new HBox();
        Button delete = new Button("Remove");

        public ActionCell(TableView table) {
            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().add(delete);

            delete.setOnAction(e -> {
                table.getSelectionModel().select(getTableRow().getIndex());
                equipmentRequirementList.remove(getTableRow().getIndex());

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

    private Equipment getSelectedEquipment() {
        return equipmentTextField.getSelectedItem();
    }

    private String getProcedure() {
        return procedureChoiceBox.getValue().toString();
    }

    @Override
    public void createValidator() {
        validator = new ConstraintValidator(
                equipmentTextField,
                procedureChoiceBox);
    }

    @Override
    public boolean allFieldsValid() {
        // return validator.validateFields();
        return !equipmentRequirementList.isEmpty();
    }

    @Override
    public String getInstruction() {
        return "Set equipment requirements";
    }

}
