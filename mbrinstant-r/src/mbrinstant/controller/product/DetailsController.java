/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.product;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import mbrinstant.entity.main.Product;
import mbrinstant.entity.mbr.CompoundingProcedure;
import mbrinstant.entity.mbr.EquipmentRequirement;
import mbrinstant.entity.mbr.PackagingMaterialRequirement;
import mbrinstant.entity.mbr.PackagingOperation;
import mbrinstant.entity.mbr.RawMaterialRequirement;
import mbrinstant.exceptions.ServerException;
import mbrinstant.rest_client.main.SingletonUnitRestClient;
import mbrinstant.rest_client.mbr.SingletonEquipmentRequirementRestClient;
import mbrinstant.rest_client.mbr.SingletonPackgMaterialRequirementRestClient;
import mbrinstant.rest_client.mbr.SingletonRawMaterialRequirementRestClient;
import mbrinstant.rest_client.mbr.SingletonUdfRestClient;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class DetailsController implements Initializable {

    @FXML
    private Button closeButton;
    @FXML
    private Label codeLabel;
    @FXML
    private Label brandNameLabel;

    /*PRODUCT FORMULATION*/
    //raw material requirements pane
    @FXML
    private HBox rmReqHbox;

    @FXML
    private TableView<RawMaterialRequirement> rawMaterialReqTable;
    @FXML
    private TableColumn<RawMaterialRequirement, String> colProductFormulationRMUnit;
    @FXML
    private TableColumn<RawMaterialRequirement, Double> colProductFormulationRMQty;
    @FXML
    private TableColumn<RawMaterialRequirement, String> colProductFormulationRMMaterial;
    @FXML
    private TableColumn colProductFormulationRMAction;

    @FXML
    private HBox pmReqHbox;

    @FXML
    private TableView<PackagingMaterialRequirement> packagingMaterialReqTable;
    @FXML
    private TableColumn<PackagingMaterialRequirement, String> colProductFormulationPMMaterial;
    @FXML
    private TableColumn<PackagingMaterialRequirement, Double> colProductFormulationPMQty;
    @FXML
    private TableColumn<PackagingMaterialRequirement, String> colProductFormulationPMUnit;

    /* END OF PRODUCT FORMULATION*/

    /*COMPOUNDING PROCEDURE PANE*/
    @FXML
    private TableView<CompoundingProcedure> compoundingProcedureTableView;
    @FXML
    private TableColumn<CompoundingProcedure, CompoundingProcedure> colCompoundingProcedureHeader;
    @FXML
    private TableColumn<CompoundingProcedure, Short> colCompoundingProcedureStep;
    @FXML
    private TableColumn<CompoundingProcedure, CompoundingProcedure> colCompoundingProcedureFooter;
    @FXML
    private TableColumn<CompoundingProcedure, String> colCompoundingProcedureCheckedBy;
    @FXML
    private TableColumn<CompoundingProcedure, String> colCompoundingProcedureDoneBy;
    @FXML
    private TableColumn colCompoundingProcedureAction;
    @FXML
    private TableColumn<CompoundingProcedure, CompoundingProcedure> colCompoundingProcedureDosageList;
    @FXML
    private Button addCompoundingProcedureButton;
    /*END OF COMPOUNDING PROCEDURE PANE*/

    /*EQUIPMENT REQUIREMENTS PANE*/
    @FXML
    private ChoiceBox<String> procedureChoiceBox;
    @FXML
    private TableView<EquipmentRequirement> equipmentRequirementTable;
    @FXML
    private TableColumn<EquipmentRequirement, String> colEquipmentRequirementCode;
    @FXML
    private TableColumn<EquipmentRequirement, String> colEquipmentRequirementName;
    /*END OF EQUIPMENT REQUIREMENTS PANE*/

    //rest client
    private SingletonUnitRestClient unitRestClient = SingletonUnitRestClient.getInstance();

    private SingletonEquipmentRequirementRestClient equipmentRequirementService = SingletonEquipmentRequirementRestClient.getInstance();
    private SingletonPackgMaterialRequirementRestClient pmReqService = SingletonPackgMaterialRequirementRestClient.getInstance();
    private SingletonRawMaterialRequirementRestClient rmReqService = SingletonRawMaterialRequirementRestClient.getInstance();
    private SingletonUdfRestClient udfService = SingletonUdfRestClient.getInstance();

    private Product product;

    private CompoundingProcedureTableFactory compoundingProcedureTableFactory;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            compoundingProcedureTableFactory = new CompoundingProcedureTableFactory();

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
        } catch (ServerException ex) {
            Logger.getLogger(DetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void initProductFormulationPane() throws ServerException {
        //  initUdfChoiceBox();
        initRawMaterialRequirementTable();
        initPackagingMaterialRequirementTable();

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

    }

    private void initRawMaterialRequirementTable() throws ServerException {
        rawMaterialReqTable.setItems(FXCollections.observableArrayList(rmReqService.getRawMaterialRequirementByUdfId(product.getUdfId().getId())));
    }

    private void initPackagingMaterialRequirementTable() throws ServerException {
        packagingMaterialReqTable.setItems(FXCollections.observableArrayList(pmReqService.getPackgMaterialReqByUdfId(product.getUdfId().getId())));
    }

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

    private void initEquipmentRequirementPane() throws ServerException {

        initProcedureChoiceBox();
        initEquipmentRequirementTable();
    }

    private void initProcedureChoiceBox() {
        procedureChoiceBox.setItems(FXCollections.observableArrayList(COMPOUNDING, ENCAP, CODING, PACKG_PROC));
        procedureChoiceBox.getSelectionModel().selectFirst();

        procedureChoiceBox.valueProperty().addListener((ob, ov, nv) -> {
            try {
                equipmentRequirementTable.setItems(FXCollections.observableArrayList(equipmentRequirementService.getEquipmentByMfgIdAndProcedureType(product.getManufacturingProcedureId().getId(), procedureChoiceBox.getValue())));
            } catch (ServerException ex) {
                Logger.getLogger(DetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void initEquipmentRequirementTable() throws ServerException {
        int mfgId = product.getManufacturingProcedureId().getId();
        equipmentRequirementTable.setItems(FXCollections.observableArrayList(equipmentRequirementService.getEquipmentByMfgIdAndProcedureType(mfgId, procedureChoiceBox.getValue())));
        colEquipmentRequirementCode.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEquipmentId().getCode()));
        colEquipmentRequirementName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEquipmentId().getName()));
    }

    @FXML
    TableView<PackagingOperation> packgProcOperationTable;
    @FXML
    TableColumn<PackagingOperation, Short> operationStep;
    @FXML
    TableColumn<PackagingOperation, PackagingOperation> operationProcedure;

    PackagingProcedureTableFactory packgProcTableFactory = new PackagingProcedureTableFactory();

    private void initPackagingProcedurePane() {
        initOperationTable();

    }

    private void initOperationTable() {
        ObservableList<PackagingOperation> packgProcOperationList = FXCollections.observableArrayList(product.getManufacturingProcedureId().getPackagingProcedureOperationList());
        packgProcOperationTable.setItems(packgProcOperationList);
        operationStep.setCellValueFactory(c -> new SimpleObjectProperty(c.getValue().getStepNumber()));

        operationProcedure.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PackagingOperation, PackagingOperation>, ObservableValue<PackagingOperation>>() {
            @Override
            public ObservableValue<PackagingOperation> call(TableColumn.CellDataFeatures<PackagingOperation, PackagingOperation> cp) {
                return new ReadOnlyObjectWrapper(cp.getValue());
            }
        });

        operationProcedure.setCellFactory(new Callback<TableColumn<PackagingOperation, PackagingOperation>, TableCell<PackagingOperation, PackagingOperation>>() {
            @Override
            public TableCell<PackagingOperation, PackagingOperation> call(TableColumn<PackagingOperation, PackagingOperation> colCompoundingProcedureHeader) {
                return packgProcTableFactory.new OperationProcedureCell();
            }
        });
    }

}
