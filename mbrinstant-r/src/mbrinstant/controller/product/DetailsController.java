/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import mbrinstant.controls.TextFieldWithSearch;
import mbrinstant.entity.main.PackagingMaterial;
import mbrinstant.entity.main.Product;
import mbrinstant.entity.main.RawMaterial;
import mbrinstant.entity.main.Unit;
import mbrinstant.entity.mbr.CompoundingProcedure;
import mbrinstant.entity.mbr.EquipmentRequirement;
import mbrinstant.entity.mbr.PackagingMaterialRequirement;
import mbrinstant.entity.mbr.PackagingProcedureOperation;
import mbrinstant.entity.mbr.RawMaterialRequirement;
import mbrinstant.entity.mbr.Udf;
import mbrinstant.service.main.PackagingMaterialService;
import mbrinstant.service.main.RawMaterialService;
import mbrinstant.service.main.UnitService;
import mbrinstant.service.mbr.EquipmentRequirementService;
import mbrinstant.service.mbr.PackagingMaterialRequirementService;
import mbrinstant.service.mbr.RawMaterialRequirementService;
import mbrinstant.service.mbr.UdfService;

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
    //raw material requirements pane
    @FXML
    HBox rmReqHbox;
    TextFieldWithSearch<RawMaterial> rmReqTextField;
    @FXML
    TextField rmReqQty;
    @FXML
    ChoiceBox<Unit> rmReqUnit;

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
    @FXML
    Button addRmReqButton;
    //packaging material requirements pane
    @FXML
    HBox pmReqHbox;
    TextFieldWithSearch<PackagingMaterial> pmReqTextField;
    @FXML
    TextField pmReqQty;
    @FXML
    ChoiceBox<Unit> pmReqUnit;
    @FXML
    Button addPmReqButton;
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
    @FXML
    Button addCompoundingProcedureButton;
    /*END OF COMPOUNDING PROCEDURE PANE*/

    /*EQUIPMENT REQUIREMENTS PANE*/
    @FXML
    ChoiceBox<String> procedureChoiceBox;
    @FXML
    TableView<EquipmentRequirement> equipmentRequirementTable;
    @FXML
    TableColumn<EquipmentRequirement, String> colEquipmentRequirementCode;
    @FXML
    TableColumn<EquipmentRequirement, String> colEquipmentRequirementName;
    /*END OF EQUIPMENT REQUIREMENTS PANE*/

    Product product;
    RawMaterialService rawMaterialService = new RawMaterialService();
    PackagingMaterialService packagingMaterialService = new PackagingMaterialService();
    CompoundingProcedureTableFactory compoundingProcedureTableFactory = new CompoundingProcedureTableFactory();
    EquipmentRequirementService equipmentRequirementService = new EquipmentRequirementService();
    UnitService unitService = new UnitService();
    RawMaterialRequirementService rmReqService = new RawMaterialRequirementService();
    PackagingMaterialRequirementService pmReqService = new PackagingMaterialRequirementService();
    UdfService udfService = new UdfService();

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

        initProductFormulationPane();
        initCompoundingProcedurePane();
        initEquipmentRequirementPane();
        initPackagingProcedurePane();

    }

    private void initProductFormulationPane() {
      //  initUdfChoiceBox();
        initRawMaterialRequirementTable();
        initPackagingMaterialRequirementTable();

        //for adding new raw material requirement
        rmReqTextField = new TextFieldWithSearch(rawMaterialService.getRawMaterialList());
        rmReqTextField.setPrefWidth(200);
        rmReqTextField.setTextFieldMargin(13, 0, 0, 0);
        rmReqHbox.getChildren().add(1, rmReqTextField);
        rmReqUnit.setItems(unitService.getUnitList());
        addRmReqButton.setOnAction(e -> {
            RawMaterial rm = rmReqTextField.getSelectedItem();
            double qty = Double.parseDouble(rmReqQty.getText());
            Unit unit = rmReqUnit.getValue();
            rmReqService.createRawMaterialRequirement(product.getUdfId().getId(), rm, qty, unit);
            initRawMaterialRequirementTable();
        });

        //for adding packaging material requirement
        pmReqTextField = new TextFieldWithSearch(packagingMaterialService.getPackagingMaterialList());
        pmReqTextField.setPrefWidth(200);
        pmReqTextField.setTextFieldMargin(13, 0, 0, 0);
        pmReqHbox.getChildren().add(1, pmReqTextField);
        pmReqUnit.setItems(unitService.getUnitList());
        addPmReqButton.setOnAction(e -> {
            PackagingMaterial pm = pmReqTextField.getSelectedItem();
            double qty = Double.parseDouble(pmReqQty.getText());
            Unit unit = pmReqUnit.getValue();
            pmReqService.createPackagingMaterialRequirement(product.getUdfId().getId(), pm, qty, unit);
            initPackagingMaterialRequirementTable();
        });
    }

    private void initCompoundingProcedurePane() {
        compoundingProcedureTableView.setItems(FXCollections.observableArrayList(product.getManufacturingProcedureId().getCompoundingProcedureList()));
        colCompoundingProcedureStep.setCellValueFactory(c -> new SimpleObjectProperty(c.getValue().getStepNumber()));
        colCompoundingProcedureDoneBy.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDoneBy()));
        colCompoundingProcedureCheckedBy.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCheckedBy()));

        //this will set all cell factory & cell value factory for the following table cells of compounding procedure
      
        initHeaderCell();
        initFooterCell();
        initDosageCell();

        addCompoundingProcedureButton.setOnAction(e -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mbrinstant/view/compounding_procedure/new.fxml"));
                mbrinstant.controller.compounding_procedure.NewController controller;
                controller = new mbrinstant.controller.compounding_procedure.NewController();
                controller.setProduct(product);
                fxmlLoader.setController(controller);
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Add Compounding Procedure");
                stage.setResizable(false);
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();

            }
        });

    }

    private void initRawMaterialRequirementTable() {
        rawMaterialReqTable.setItems(FXCollections.observableArrayList(rmReqService.getByUdfId(product.getUdfId().getId())));
    }

    private void initPackagingMaterialRequirementTable() {
        packagingMaterialReqTable.setItems(FXCollections.observableArrayList(pmReqService.getByUdfId(product.getUdfId().getId())));
    }

//    private void initUdfChoiceBox() {
//        udfChoiceBox.setItems(FXCollections.observableArrayList(product.getUdfList()));
//        udfChoiceBox.getSelectionModel().selectFirst();
//
//        udfChoiceBox.valueProperty().addListener((ob, ov, nv) -> {
//            rawMaterialReqTable.setItems(FXCollections.observableArrayList(rmReqService.getByUdfId(udfChoiceBox.getValue().getId())));
//            packagingMaterialReqTable.setItems(FXCollections.observableArrayList(pmReqService.getByUdfId(udfChoiceBox.getValue().getId())));
//        });
//    }

    public void closeProductDetailsDialog() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    private void initHeaderCell() {
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
                return compoundingProcedureTableFactory.new HeaderCell();
            }
        });
    }

    private void initFooterCell() {
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
                return compoundingProcedureTableFactory.new FooterCell();
            }
        });
    }

    private void initDosageCell() {

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
                return compoundingProcedureTableFactory.new DosageListCell();
            }
        });
    }

   


    public static String COMPOUNDING = "COMPOUNDING";
    public static String ENCAP = "ENCAPSULATION";
    public static String CODING = "CODING";
    public static String PACKG_PROC = "PACK'G PROCEDURE";

    private void initEquipmentRequirementPane() {

        initProcedureChoiceBox();
        initEquipmentRequirementTable();
    }

    private void initProcedureChoiceBox() {
        procedureChoiceBox.setItems(FXCollections.observableArrayList(COMPOUNDING, ENCAP, CODING, PACKG_PROC));
        procedureChoiceBox.getSelectionModel().selectFirst();

        procedureChoiceBox.valueProperty().addListener((ob, ov, nv) -> {
            equipmentRequirementTable.setItems(FXCollections.observableArrayList(equipmentRequirementService.getAllEquipmentRequirement(product.getManufacturingProcedureId().getId(), procedureChoiceBox.getValue())));
        });
    }

    private void initEquipmentRequirementTable() {
        int mfgId = product.getManufacturingProcedureId().getId();
        equipmentRequirementTable.setItems(FXCollections.observableArrayList(equipmentRequirementService.getAllEquipmentRequirement(mfgId, procedureChoiceBox.getValue())));
        colEquipmentRequirementCode.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEquipmentId().getCode()));
        colEquipmentRequirementName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEquipmentId().getCode()));

    }

    @FXML
    TableView<PackagingProcedureOperation> packgProcOperationTable;
    @FXML
    TableColumn<PackagingProcedureOperation, Short> operationStep;
    @FXML
    TableColumn<PackagingProcedureOperation, PackagingProcedureOperation> operationProcedure;

    PackagingProcedureTableFactory packgProcTableFactory = new PackagingProcedureTableFactory();

    private void initPackagingProcedurePane() {
        initOperationTable();

    }

    private void initOperationTable() {
        ObservableList<PackagingProcedureOperation> packgProcOperationList = FXCollections.observableArrayList(product.getManufacturingProcedureId().getPackagingProcedureOperationList());
        packgProcOperationTable.setItems(packgProcOperationList);
        operationStep.setCellValueFactory(c -> new SimpleObjectProperty(c.getValue().getStepNumber()));

        operationProcedure.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<PackagingProcedureOperation, PackagingProcedureOperation>, ObservableValue<PackagingProcedureOperation>>() {
                    @Override
                    public ObservableValue<PackagingProcedureOperation> call(TableColumn.CellDataFeatures<PackagingProcedureOperation, PackagingProcedureOperation> cp) {
                        return new ReadOnlyObjectWrapper(cp.getValue());
                    }
                });

        operationProcedure.setCellFactory(new Callback<TableColumn<PackagingProcedureOperation, PackagingProcedureOperation>, TableCell<PackagingProcedureOperation, PackagingProcedureOperation>>() {
            @Override
            public TableCell<PackagingProcedureOperation, PackagingProcedureOperation> call(TableColumn<PackagingProcedureOperation, PackagingProcedureOperation> colCompoundingProcedureHeader) {
                return packgProcTableFactory.new OperationProcedureCell();
            }
        });
    }

}
