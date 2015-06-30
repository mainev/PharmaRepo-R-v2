/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.product.ProductWizard;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import mbrinstant.controller.product.CompoundingProcedureTableFactory;
import mbrinstant.controls.CustomTextArea;
import mbrinstant.controls.InputValidator;
import mbrinstant.controls.NumberTextField;
import mbrinstant.entity.mbr.CompoundingProcedure;
import mbrinstant.entity.mbr.Dosage;
import mbrinstant.entity.mbr.ManufacturingProcedure;
import mbrinstant.entity.mbr.RawMaterialRequirement;
import mbrinstant.service.mbr.CompoundingProcedureService;
import mbrinstant.service.mbr.DosageService;
import mbrinstant.service.mbr.RawMaterialRequirementService;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class CompoundingProceduresController implements Initializable, PageController {

    @FXML
    Button addProcedureButton;
    @FXML
    ScrollPane mainScrollPane;

    @FXML
    CheckBox footer;
    @FXML
    CustomTextArea contentArea;

    @FXML
    ComboBox<RawMaterialRequirement> rmReqChoiceBox;
    @FXML
    NumberTextField percentQty;
    @FXML
    Button addDosage;
    @FXML
    TableView<Dosage> dosagesTable;
    @FXML
    TableColumn colAction;
    @FXML
    TableColumn colMaterial;
    @FXML
    TableColumn colPercent;

    public ObservableList<Dosage> dosageList = FXCollections.observableArrayList();
    public ObservableList<RawMaterialRequirement> rmReqList = FXCollections.observableArrayList();
    public ObservableList<CompoundingProcedure> compoundingProcedureList = FXCollections.observableArrayList();

    //services
    CompoundingProcedureService cpService = new CompoundingProcedureService();
    DosageService dosService = new DosageService();
    RawMaterialRequirementService rmReqService = new RawMaterialRequirementService();

    @FXML
    HBox hbox;

    ObservableList sample = FXCollections.observableArrayList();
    ObservableList<RawMaterialRequirement> tmp = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        initDosageTable();
        initCompoundingProcedureTable();
        rmReqChoiceBox.setPromptText("Click Me");

        rmReqChoiceBox.setItems(rmReqList);

        addDosage.setOnAction(e -> {
            InputValidator dosageValidator = new InputValidator(rmReqChoiceBox, percentQty);
            if (dosageValidator.validateFields()) {
                double p = Double.parseDouble(percentQty.getText()) * 0.01;
                Dosage dos = new Dosage(rmReqChoiceBox.getSelectionModel().getSelectedItem(), p);
                dosageList.add(dos);
                rmReqChoiceBox.getSelectionModel().clearSelection();
                rmReqChoiceBox.setValue(null);
                percentQty.setText("");
            }

        });

        addProcedureButton.setOnAction(e -> {
            if (validator.validateFields()) {
                List<Dosage> cpDosageList = new ArrayList();
                cpDosageList.addAll(dosageList);
                CompoundingProcedure tempCp = new CompoundingProcedure((short) 0, contentArea.getText(),
                        footer.isSelected(), null, null, cpDosageList);
                compoundingProcedureList.add(tempCp);

                //clear all values after adding the temporary procedure to the list
                dosageList.clear();
                footer.setSelected(false);
                contentArea.setText("");
                rmReqChoiceBox.setValue(null);
                percentQty.setText(null);
            }
        });

        compoundingProcedureList.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
                short i = 1;
                for (CompoundingProcedure cp : compoundingProcedureList) {
                    cp.setStepNumber(i);
                    i++;
                }
                refreshTable(compoundingProcedureTableView);
            }
        });

        createValidator();
        updateDosageAndCompoundingProcedureList();
    }

    public void createCompoundingProcedures(ManufacturingProcedure mfgId, int udfId) {
        if (allFieldsValid()) {
            for (CompoundingProcedure cp : compoundingProcedureList) {
                List<Dosage> dosList = cp.getDosageList();
                cp = cpService.createCompoundingProcedure(mfgId.getId(), cp);
                for (Dosage dos : dosList) {
                    RawMaterialRequirement rmReq = dos.getRawMaterialRequirementId();
                    rmReq = rmReqService.findByDetails(rmReq.getRawMaterialId().getId(), rmReq.getQuantity(), rmReq.getUnitId().getId(), udfId);
                    dos.setRawMaterialRequirementId(rmReq);
                    dosService.createDosage(cp.getId(), dos);
                } //   rmReqService.createRawMaterialRequirement(udfId.getId(), rmReq);
            }
        }
    }

    private boolean isIn(RawMaterialRequirement rmReq) {
        for (RawMaterialRequirement rm : rmReqList) {
            if (rm.getRawMaterialId().getCode().equals(rmReq.getRawMaterialId().getCode())) {
                return true;
            }
        }
        return false;
    }

    /**
     * This will automatically update all dosages/ingredients in the table
     * whenever a raw material is removed from the main requirement list.
     */
    private void updateDosageAndCompoundingProcedureList() {
        rmReqList.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change change) {
                while (change.next()) {
                    Iterator<Dosage> dosageListIterator = dosageList.iterator();
                    while (dosageListIterator.hasNext()) {
                        Dosage dos = dosageListIterator.next();
                        if (!isIn(dos.getRawMaterialRequirementId())) {
                            dosageListIterator.remove();
                        }
                    }
                    for (CompoundingProcedure cp : compoundingProcedureList) {
                        Iterator<Dosage> cpDosIter = cp.getDosageList().iterator();
                        while (cpDosIter.hasNext()) {
                            Dosage dos = cpDosIter.next();
                            if (!isIn(dos.getRawMaterialRequirementId())) {
                                cpDosIter.remove();
                                refreshTable(compoundingProcedureTableView);
                            }
                        }
                    }
                }
            }
        });
    }

    private void initDosageTable() {
        dosagesTable.setItems(dosageList);

        colAction.setSortable(false); // define a simple boolean cell value for the action column so that the column will only be shown for non-empty rows.
        colAction.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Dosage, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Dosage, Boolean> features) {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        });
        colAction.setCellFactory(new Callback<TableColumn<Dosage, Boolean>, TableCell<Dosage, Boolean>>() {
            @Override
            public TableCell<Dosage, Boolean> call(TableColumn<Dosage, Boolean> dos) {
                return new DosageActionCell();
            }
        });

    }

    @FXML
    TableView<CompoundingProcedure> compoundingProcedureTableView;
    @FXML
    TableColumn<CompoundingProcedure, String> colCompoundingProcedureHeader;
    @FXML
    TableColumn<CompoundingProcedure, Short> colCompoundingProcedureStep;
    @FXML
    TableColumn<CompoundingProcedure, CompoundingProcedure> colCompoundingProcedureDosageList;
    @FXML
    TableColumn<CompoundingProcedure, CompoundingProcedure> colCompoundingProcedureFooter;
    @FXML
    TableColumn colCompoundingProcedureAction;
    CompoundingProcedureTableFactory cpFactory = new CompoundingProcedureTableFactory();

    private void initCompoundingProcedureTable() {
        TableView<RawMaterialRequirement> tb = new TableView();

        //tb.itemsProperty().bind(rmReqChoiceBox.itemsProperty());
        compoundingProcedureTableView.setItems(compoundingProcedureList);
        colCompoundingProcedureHeader.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getHeader()));
        colCompoundingProcedureStep.setCellValueFactory(c -> new SimpleObjectProperty(c.getValue().getStepNumber()));

        colCompoundingProcedureAction.setSortable(false);
        colCompoundingProcedureAction.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CompoundingProcedure, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<CompoundingProcedure, Boolean> features) {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        });
        colCompoundingProcedureAction.setCellFactory(new Callback<TableColumn<CompoundingProcedure, Boolean>, TableCell<CompoundingProcedure, Boolean>>() {
            @Override
            public TableCell<CompoundingProcedure, Boolean> call(TableColumn<CompoundingProcedure, Boolean> cp) {
                return cpFactory.new ActionCell(compoundingProcedureList, compoundingProcedureTableView);
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
                return cpFactory.new FooterCell();
            }
        });

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
                return cpFactory.new DosageListCell();
            }
        });
    }

    public class DosageActionCell extends TableCell<Dosage, Boolean> {

        HBox hbox = new HBox();
        Button delete = new Button("Remove");

        public DosageActionCell() {
            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().add(delete);

            delete.setOnAction(e -> {
                dosagesTable.getSelectionModel().select(getTableRow().getIndex());
                dosageList.remove(getTableRow().getIndex());
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

    public ObservableList<Dosage> getDosageList() {
        return dosageList;
    }

    public static void refreshTable(TableView tableView) {
        for (int i = 0; i < tableView.getColumns().size(); i++) {
            ((TableColumn) (tableView.getColumns().get(i))).setVisible(false);
            ((TableColumn) (tableView.getColumns().get(i))).setVisible(true);
        }
    }

    InputValidator validator;

    @Override
    public void createValidator() {
        validator = new InputValidator(
                contentArea //a compounding procedure can only be added if the contentArea has values
        );
    }

    @Override
    public boolean allFieldsValid() {
        //advance to next page if compounding procedure list is not empty
        return !compoundingProcedureList.isEmpty();

    }

    @Override
    public String getInstruction() {
        return "Set compounding procedures";
    }

}
