/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.product;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import mbrinstant.entity.main.Product;
import mbrinstant.entity.mbr.CompoundingProcedure;
import mbrinstant.entity.mbr.PackagingMaterialRequirement;
import mbrinstant.entity.mbr.RawMaterialRequirement;
import mbrinstant.entity.mbr.Udf;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class DetailsController implements Initializable {

    @FXML
    Button closeButton;
    @FXML
    Label codeLabel;
    @FXML
    Label brandNameLabel;

    /*PRODUCT FORMULATION*/
    @FXML
    ChoiceBox<Udf> udfChoiceBox;
    //raw material requirements pane
    @FXML
    TableView<RawMaterialRequirement> rawMaterialReqTable;
    @FXML
    TableColumn<RawMaterialRequirement, String> colProductFormulationRMUnit;
    @FXML
    TableColumn<RawMaterialRequirement, Double> colProductFormulationRMQty;
    @FXML
    TableColumn<RawMaterialRequirement, String> colProductFormulationRMMaterial;
    @FXML
    TableColumn colProductFormulationRMAction;
    //packaging material requirements pane
    @FXML
    TableView<PackagingMaterialRequirement> packagingMaterialReqTable;
    @FXML
    TableColumn<PackagingMaterialRequirement, String> colProductFormulationPMMaterial;
    @FXML
    TableColumn<PackagingMaterialRequirement, Double> colProductFormulationPMQty;
    @FXML
    TableColumn<PackagingMaterialRequirement, String> colProductFormulationPMUnit;
    /* END OF PRODUCT FORMULATION*/

    /*COMPOUNDING PROCEDURE PANE*/
    @FXML
    TableView<CompoundingProcedure> compoundingProcedureTableView;
    @FXML
    TableColumn<CompoundingProcedure, CompoundingProcedure> colCompoundingProcedureHeader;
    @FXML
    TableColumn<CompoundingProcedure, Short> colCompoundingProcedureStep;
    @FXML
    TableColumn<CompoundingProcedure, CompoundingProcedure> colCompoundingProcedureFooter;
    @FXML
    TableColumn<CompoundingProcedure, String> colCompoundingProcedureCheckedBy;
    @FXML
    TableColumn<CompoundingProcedure, String> colCompoundingProcedureDoneBy;
    @FXML
    TableColumn colCompoundingProcedureAction;
    @FXML
    TableColumn<CompoundingProcedure, CompoundingProcedure> colCompoundingProcedureDosageList;
    Product product;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        codeLabel.setText(product.getCode());
        brandNameLabel.setText(product.getBrandName());

        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                closeProductDetailsDialog();
            }
        });

        //product formulation
        initUdfChoiceBox();
        initRawMaterialRequirementTable();
        initPackagingMaterialRequirementTable();

        //compounding procedure
        initCompoundingProcedureTableView();

    }

    private void initCompoundingProcedureTableView() {
        compoundingProcedureTableView.setItems(FXCollections.observableArrayList(product.getManufacturingProcedureId().getCompoundingProcedureList()));

        colCompoundingProcedureStep.setCellValueFactory(c -> new SimpleObjectProperty(c.getValue().getStepNumber()));
        colCompoundingProcedureDoneBy.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDoneBy()));
        colCompoundingProcedureCheckedBy.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCheckedBy()));
        colCompoundingProcedureHeader.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<CompoundingProcedure, CompoundingProcedure>, ObservableValue<CompoundingProcedure>>() {

                    @Override
                    public ObservableValue<CompoundingProcedure> call(TableColumn.CellDataFeatures<CompoundingProcedure, CompoundingProcedure> cp) {
                        return new ReadOnlyObjectWrapper(cp.getValue());
                    }
                });

        colCompoundingProcedureHeader.setCellFactory(new Callback<TableColumn<CompoundingProcedure, CompoundingProcedure>, TableCell<CompoundingProcedure, CompoundingProcedure>>() {
            @Override
            public TableCell<CompoundingProcedure, CompoundingProcedure> call(TableColumn<CompoundingProcedure, CompoundingProcedure> colCompoundingProcedureHeader) {

                CompoundingProcedureTableFactory fact = new CompoundingProcedureTableFactory();
                return fact.new HeaderCell();
            }
        });

        //footer
        colCompoundingProcedureFooter.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<CompoundingProcedure, CompoundingProcedure>, ObservableValue<CompoundingProcedure>>() {

                    @Override
                    public ObservableValue<CompoundingProcedure> call(TableColumn.CellDataFeatures<CompoundingProcedure, CompoundingProcedure> cp) {
                        return new ReadOnlyObjectWrapper(cp.getValue());
                    }
                });

        colCompoundingProcedureFooter.setCellFactory(new Callback<TableColumn<CompoundingProcedure, CompoundingProcedure>, TableCell<CompoundingProcedure, CompoundingProcedure>>() {
            @Override
            public TableCell<CompoundingProcedure, CompoundingProcedure> call(TableColumn<CompoundingProcedure, CompoundingProcedure> colCompoundingProcedureHeader) {

                CompoundingProcedureTableFactory fact = new CompoundingProcedureTableFactory();
                return fact.new FooterCell();
            }
        });

       //dosage
        colCompoundingProcedureDosageList.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<CompoundingProcedure, CompoundingProcedure>, ObservableValue<CompoundingProcedure>>() {

                    @Override
                    public ObservableValue<CompoundingProcedure> call(TableColumn.CellDataFeatures<CompoundingProcedure, CompoundingProcedure> cp) {
                        return new ReadOnlyObjectWrapper(cp.getValue());
                    }
                });

        colCompoundingProcedureDosageList.setCellFactory(new Callback<TableColumn<CompoundingProcedure, CompoundingProcedure>, TableCell<CompoundingProcedure, CompoundingProcedure>>() {
            @Override
            public TableCell<CompoundingProcedure, CompoundingProcedure> call(TableColumn<CompoundingProcedure, CompoundingProcedure> colCompoundingProcedureHeader) {

                CompoundingProcedureTableFactory fact = new CompoundingProcedureTableFactory();
                return fact.new DosageListCell();
            }
        });
    }

    private void initRawMaterialRequirementTable() {
        rawMaterialReqTable.setItems(FXCollections.observableArrayList(udfChoiceBox.getValue().getRawMaterialRequirementList()));
        colProductFormulationRMMaterial.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getRawMaterialId().toString()));
        colProductFormulationRMQty.setCellValueFactory(c -> new SimpleObjectProperty(c.getValue().getQuantity()));
        colProductFormulationRMUnit.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getUnitId().toString()));
    }

    private void initPackagingMaterialRequirementTable() {
        packagingMaterialReqTable.setItems(FXCollections.observableArrayList(udfChoiceBox.getValue().getPackagingMaterialRequirementList()));
        colProductFormulationPMMaterial.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPackagingMaterialId().toString()));
        colProductFormulationPMQty.setCellValueFactory(c -> new SimpleObjectProperty(c.getValue().getQuantity()));
        colProductFormulationPMUnit.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getUnitId().toString()));
    }

    private void initUdfChoiceBox() {
        udfChoiceBox.setItems(FXCollections.observableArrayList(product.getUdfList()));
        udfChoiceBox.getSelectionModel().selectFirst();

        udfChoiceBox.valueProperty().addListener((ob, ov, nv) -> {
            rawMaterialReqTable.setItems(FXCollections.observableArrayList(nv.getRawMaterialRequirementList()));
            packagingMaterialReqTable.setItems(FXCollections.observableArrayList(nv.getPackagingMaterialRequirementList()));
        });
    }

    public void closeProductDetailsDialog() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
