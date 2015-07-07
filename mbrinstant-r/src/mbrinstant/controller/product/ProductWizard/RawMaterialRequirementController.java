/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.product.ProductWizard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
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
import mbrinstant.controls.CustomChoiceBox;
import mbrinstant.controls.ConstraintValidator;
import mbrinstant.controls.CustomComboBox;
import mbrinstant.controls.NumberTextField;
import mbrinstant.controls.SearchTextField;
import mbrinstant.entity.main.RawMaterial;
import mbrinstant.entity.main.Unit;
import mbrinstant.entity.mbr.RawMaterialRequirement;
import mbrinstant.entity.mbr.Udf;
import mbrinstant.service.main.RawMaterialService;
import mbrinstant.service.main.UnitService;
import mbrinstant.service.mbr.RawMaterialRequirementService;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class RawMaterialRequirementController implements Initializable, PageController {

    @FXML
    CustomComboBox<Short> partComboBox;
    @FXML
    NumberTextField rmReqQty;
    @FXML
    CustomComboBox<Unit> rmReqUnit;
    @FXML
    Button addButton;
    @FXML
    TableView<RawMaterialRequirement> rmReqTable;
    @FXML
    TableColumn colAction;
    @FXML
    TableColumn<RawMaterialRequirement, String> colRawMaterial;

    @FXML
    SearchTextField<RawMaterial> rmTextField;

    //service
    RawMaterialService rmService = new RawMaterialService();
    UnitService unitService = new UnitService();
    RawMaterialRequirementService rmReqService = new RawMaterialRequirementService();

    ObservableList<RawMaterialRequirement> rmReqTemporaryList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partComboBox.setItems(FXCollections.observableArrayList(null, (short)1, (short)2, (short)3));
        rmTextField.setTextFieldMargin(new Insets(13, 0, 0, 0));
        rmTextField.setItems(rmService.getRawMaterialList());
        rmTextField.setPromptText("Type raw material here");
        rmReqUnit.setItems(unitService.getUnitList());
        initRmReqTable();

        addButton.setOnAction(e -> {
            if (validator.validateFields()) {
                
                RawMaterialRequirement temp = new RawMaterialRequirement(getRawMaterial(), getQuantity(), getUnit(), getPart());
           System.out.println("hashcode is: "+temp.hashCode());
                rmReqTemporaryList.add(temp);
                clearFields();
            }
        });
        createValidator();

    }
    
    private short getPart(){
        if(partComboBox.getValue()==null){
            return 0;
        }
        else
            return partComboBox.getValue();
    }

    public void createRawMaterialRequirements(Udf udfId) {
        if (allFieldsValid()) {
            for (RawMaterialRequirement rmReq : rmReqTemporaryList) {
                rmReqService.createRawMaterialRequirement(udfId.getId(), rmReq);
            }
        }
    }

    private void clearFields() {
        rmTextField.clearAll();
        rmReqQty.setText("");
        rmReqUnit.setValue(null);
        partComboBox.setValue(null);
    }

    private RawMaterial getRawMaterial() {

        return rmTextField.getSelectedItem();
    }

    private double getQuantity() {
        if (!rmReqQty.getText().isEmpty()) {
            return Double.parseDouble(rmReqQty.getText());
        }
        return 0;
    }

    private Unit getUnit() {
        return rmReqUnit.getValue();
    }
    
    

    private void initRmReqTable() {
        colRawMaterial.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().toString()));
        colAction.setSortable(false);

        // define a simple boolean cell value for the action column so that the column will only be shown for non-empty rows.
        colAction.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RawMaterialRequirement, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<RawMaterialRequirement, Boolean> features) {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        });

        // create a cell value factory with an add button for each row in the table.
        colAction.setCellFactory(new Callback<TableColumn<RawMaterialRequirement, Boolean>, TableCell<RawMaterialRequirement, Boolean>>() {
            @Override
            public TableCell<RawMaterialRequirement, Boolean> call(TableColumn<RawMaterialRequirement, Boolean> rmCol) {
                return new ActionCell(rmReqTable);
            }
        });

        rmReqTable.setItems(rmReqTemporaryList);
    }

    public class ActionCell extends TableCell<RawMaterialRequirement, Boolean> {

        HBox hbox = new HBox();
        Button delete = new Button("Remove");

        public ActionCell(TableView table) {
            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().add(delete);

            delete.setOnAction(e -> {
                table.getSelectionModel().select(getTableRow().getIndex());
                rmReqTemporaryList.remove(getTableRow().getIndex());

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

    public ObservableList<RawMaterialRequirement> getRmReqTemporaryList() {
        return rmReqTemporaryList;
    }

    ConstraintValidator validator;

    @Override
    public void createValidator() {
        validator = new ConstraintValidator(
                rmTextField,
                rmReqQty,
                rmReqUnit
        );
    }

    @Override
    public boolean allFieldsValid() {
        //advance to next page if the product formulation list is not empty
        return !rmReqTemporaryList.isEmpty();
    }

    @Override
    public String getInstruction() {
        return "Specify raw material requirements";
    }

}
