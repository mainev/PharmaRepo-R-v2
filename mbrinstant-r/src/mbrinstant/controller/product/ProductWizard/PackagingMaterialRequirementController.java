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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import mbrinstant.controls.TextFieldWithSearch;
import mbrinstant.entity.main.PackagingMaterial;
import mbrinstant.entity.main.Unit;
import mbrinstant.entity.mbr.PackagingMaterialRequirement;
import mbrinstant.service.main.PackagingMaterialService;
import mbrinstant.service.main.UnitService;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class PackagingMaterialRequirementController implements Initializable {

    @FXML
    HBox hbox;
    @FXML
    TextField pmReqQty;
    @FXML
    ChoiceBox<Unit> pmReqUnit;
    @FXML
    Button addButton;
    @FXML
    TableView<PackagingMaterialRequirement> pmReqTable;
    @FXML
    TableColumn colAction;
    @FXML
    TableColumn<PackagingMaterialRequirement, String> colPackagingMaterial;
    @FXML
    TableColumn<PackagingMaterialRequirement, String> colQuantity;

    TextFieldWithSearch<PackagingMaterial> pmTextField;

    //service
    PackagingMaterialService pmService = new PackagingMaterialService();
    UnitService unitService = new UnitService();

    ObservableList<PackagingMaterialRequirement> pmReqTemporaryList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pmTextField = new TextFieldWithSearch(pmService.getPackagingMaterialList());
        hbox.getChildren().add(1, pmTextField);

        pmReqUnit.setItems(unitService.getUnitList());
        initRmReqTable();

        addButton.setOnAction(e -> {
            PackagingMaterialRequirement temp = new PackagingMaterialRequirement(getPackagingMaterial(), getQuantity(), getUnit());
            pmReqTemporaryList.add(temp);
        });
    }

    private PackagingMaterial getPackagingMaterial() {

        return pmTextField.getSelectedItem();
    }

    private double getQuantity() {
        if (!pmReqQty.getText().isEmpty()) {
            return Double.parseDouble(pmReqQty.getText());
        }
        return 0;
    }

    private Unit getUnit() {
        return pmReqUnit.getValue();
    }

    private void initRmReqTable() {
        colPackagingMaterial.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPackagingMaterialId().toString()));
        colQuantity.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getQuantity() + " " + c.getValue().getUnitId()));

        colAction.setSortable(false);

        // define a simple boolean cell value for the action column so that the column will only be shown for non-empty rows.
        colAction.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PackagingMaterialRequirement, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<PackagingMaterialRequirement, Boolean> features) {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        });

        // create a cell value factory with an add button for each row in the table.
        colAction.setCellFactory(new Callback<TableColumn<PackagingMaterialRequirement, Boolean>, TableCell<PackagingMaterialRequirement, Boolean>>() {
            @Override
            public TableCell<PackagingMaterialRequirement, Boolean> call(TableColumn<PackagingMaterialRequirement, Boolean> pmCol) {
                return new ActionCell(pmReqTable);
            }
        });

        pmReqTable.setItems(pmReqTemporaryList);
    }

    public class ActionCell extends TableCell<PackagingMaterialRequirement, Boolean> {

        HBox hbox = new HBox();
        Button delete = new Button("Remove");

        public ActionCell(TableView table) {
            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().add(delete);
            
            delete.setOnAction(e->{
                table.getSelectionModel().select(getTableRow().getIndex());
           //     PackagingMaterialRequirement selectedPm = (PackagingMaterialRequirement)table.getSelectionModel().getSelectedItem();
                pmReqTemporaryList.remove(getTableRow().getIndex());
            
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

}
