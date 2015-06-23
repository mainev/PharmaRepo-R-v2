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
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import mbrinstant.controls.InputValidator;
import mbrinstant.controls.MyNotifications;
import mbrinstant.controls.NumberTextField;
import mbrinstant.controls.TextFieldWithSearch;
import mbrinstant.entity.main.RawMaterial;
import mbrinstant.entity.main.Unit;
import mbrinstant.entity.mbr.RawMaterialRequirement;
import mbrinstant.service.main.RawMaterialService;
import mbrinstant.service.main.UnitService;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class RawMaterialRequirementController implements Initializable, PageController {

    @FXML
    HBox hbox;
    @FXML
    NumberTextField rmReqQty;
    @FXML
    ChoiceBox<Unit> rmReqUnit;
    @FXML
    Button addButton;
    @FXML
    TableView<RawMaterialRequirement> rmReqTable;
    @FXML
    TableColumn colAction;
    @FXML
    TableColumn<RawMaterialRequirement, String> colRawMaterial;
    @FXML
    TableColumn<RawMaterialRequirement, String> colQuantity;

    TextFieldWithSearch<RawMaterial> rmTextField;

    //service
    RawMaterialService rmService = new RawMaterialService();
    UnitService unitService = new UnitService();

    ObservableList<RawMaterialRequirement> rmReqTemporaryList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rmReqUnit.setUserData(true);

        rmTextField = new TextFieldWithSearch(rmService.getRawMaterialList());
        hbox.getChildren().add(1, rmTextField);

        rmReqUnit.setItems(unitService.getUnitList());
        initRmReqTable();

        addButton.setOnAction(e -> {
            if(allFieldsValid()){
            RawMaterialRequirement temp = new RawMaterialRequirement(getRawMaterial(), getQuantity(), getUnit());
            rmReqTemporaryList.add(temp);
            }
            else{
                MyNotifications.displayError("Please enter all required fields");
            }
            // clearFields();
        });

        createValidator();
    }

    private void clearFields() {
        rmTextField.clearAll();
        rmReqQty.setText("");
        rmReqUnit.setValue(null);
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
        colRawMaterial.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getRawMaterialId().toString()));
        colQuantity.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getQuantity() + " " + c.getValue().getUnitId()));

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
                // RawMaterialRequirement selectedRm = (RawMaterialRequirement)table.getSelectionModel().getSelectedItem();
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

    InputValidator validator;

    @Override
    public void createValidator() {
        validator = new InputValidator(
                rmTextField,
                rmReqQty,
                rmReqUnit
        );
    }

    @Override
    public boolean allFieldsValid() {
        return validator.validateFields();
    }

    @Override
    public String getInstruction() {
        return "2. Specify raw material requirements";
    }

}
