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
import mbrinstant.controls.ConstraintValidator;
import mbrinstant.controls.CustomChoiceBox;
import mbrinstant.controls.NumberTextField;
import mbrinstant.controls.SearchTextField;
import mbrinstant.entity.main.PackagingMaterial;
import mbrinstant.entity.main.Unit;
import mbrinstant.entity.mbr.PackagingMaterialRequirement;
import mbrinstant.entity.mbr.Udf;
import mbrinstant.exception.ServerException;
import mbrinstant.rest_client.main.SingletonPackgMaterialRestClient;
import mbrinstant.rest_client.main.SingletonUnitRestClient;
import mbrinstant.rest_client.mbr.SingletonPackgMaterialRequirementRestClient;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class PackagingMaterialRequirementController implements Initializable, PageController {

    @FXML
    NumberTextField pmReqQty;
    @FXML
    CustomChoiceBox<Unit> pmReqUnit;
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

    @FXML
    SearchTextField<PackagingMaterial> pmTextField;

    //rest client
    SingletonUnitRestClient unitService = SingletonUnitRestClient.getInstance();
    SingletonPackgMaterialRestClient pmService = SingletonPackgMaterialRestClient.getInstance();
    SingletonPackgMaterialRequirementRestClient pmReqService = SingletonPackgMaterialRequirementRestClient.getInstance();

    //service
    ObservableList<PackagingMaterialRequirement> pmReqTemporaryList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            pmTextField.setTextFieldMargin(new Insets(13, 0, 0, 0));
            pmTextField.setItems(pmService.getPackgMaterialList());

            pmReqUnit.setItems(unitService.getUnitList());
            initRmReqTable();

            addButton.setOnAction(e -> {

                if (validator.validateFields()) {
                    PackagingMaterialRequirement temp = new PackagingMaterialRequirement(getPackagingMaterial(), getQuantity(), getUnit());
                    pmReqTemporaryList.add(temp);
                    clearFields();
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            });
            createValidator();
        } catch (ServerException ex) {
            Logger.getLogger(PackagingMaterialRequirementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void clearFields() {
        pmTextField.clearAll();
        pmReqQty.setText("");
        pmReqUnit.setValue(null);
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

            delete.setOnAction(e -> {
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

    public void createPackagingMaterialRequirements(Udf udfId) throws ServerException {
        if (allFieldsValid()) {
            for (PackagingMaterialRequirement pmReq : pmReqTemporaryList) {
                pmReqService.createPackgMaterialRequirement(udfId.getId(), pmReq);
            }
        }
    }

    ConstraintValidator validator;

    @Override
    public void createValidator() {
        validator = new ConstraintValidator(
                pmTextField,
                pmReqQty,
                pmReqUnit
        );
    }

    @Override
    public boolean allFieldsValid() {
        //advance to next page if the packaging material requirement list is not empty
        return !pmReqTemporaryList.isEmpty();
    }

    @Override
    public String getInstruction() {
        return "Specify packaging material requirements";
    }

}
