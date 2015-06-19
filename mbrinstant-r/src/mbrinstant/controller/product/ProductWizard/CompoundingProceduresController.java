/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.product.ProductWizard;

import java.net.URL;
import java.util.ArrayList;
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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import mbrinstant.controller.product.CompoundingProcedureTableFactory;
import mbrinstant.entity.mbr.CompoundingProcedure;
import mbrinstant.entity.mbr.Dosage;
import mbrinstant.entity.mbr.RawMaterialRequirement;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class CompoundingProceduresController implements Initializable {

    @FXML
    Button addProcedureButton;
    @FXML
    ScrollPane mainScrollPane;

    CheckBox footer = new CheckBox("Require Time Started and Time Finished");
    Label contentLabel = new Label("Content:");
    TextArea contentArea = new TextArea();
    Label dosageLabel = new Label("Dosage List:");

    VBox dosagePane = new VBox();

    HBox dosageHBox = new HBox();
    Label materialLabel = new Label("Material:");
    ChoiceBox<RawMaterialRequirement> rmReqChoiceBox = new ChoiceBox();

    Label percentLabel = new Label("Percent(%)");
    TextField percentQty = new TextField();
    Button addDosage = new Button("Add Dosage");

    TableView dosagesTable = new TableView();
    TableColumn colAction = new TableColumn();
    TableColumn<Dosage, String> colMaterial = new TableColumn("Ingredients");
    TableColumn<Dosage, Double> colPercent = new TableColumn("Quantity");

    VBox scrollPaneVBox = new VBox();

    public ObservableList<Dosage> dosageList = FXCollections.observableArrayList();
    public ObservableList<RawMaterialRequirement> rmReqList = FXCollections.observableArrayList();
    public ObservableList<CompoundingProcedure> compoundingProcedureList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initDosageTable();
        initCompoundingProcedureTable();

        contentLabel.setStyle("-fx-font-weight: bold;");
        contentArea.setPrefHeight(100);
        dosageLabel.setStyle("-fx-font-weight: bold;");
        dosagePane.setSpacing(5);
        dosagePane.setPadding(new Insets(10, 30, 10, 30));
        dosageHBox.setSpacing(5);
        dosageHBox.setAlignment(Pos.CENTER_RIGHT);
        rmReqChoiceBox.setPrefWidth(200);
        rmReqChoiceBox.setItems(rmReqList);
        percentQty.setPrefWidth(100);
        dosageHBox.getChildren().addAll(materialLabel, rmReqChoiceBox, percentLabel, percentQty, addDosage);
        dosagePane.getChildren().addAll(dosageHBox, dosagesTable);
        dosagePane.setStyle("-fx-background-color: lightgray;");

        scrollPaneVBox.setSpacing(10);
        scrollPaneVBox.setPrefSize(700, 400);
        scrollPaneVBox.setPadding(new Insets(10, 10, 10, 10));
        scrollPaneVBox.getChildren().addAll(contentLabel, contentArea, footer, dosageLabel, dosagePane);

        mainScrollPane.setContent(scrollPaneVBox);

        addDosage.setOnAction(e -> {
            double p = Double.parseDouble(percentQty.getText()) * 0.01;
            Dosage dos = new Dosage(rmReqChoiceBox.getValue(), p);
            dosageList.add(dos);
        });

        addProcedureButton.setOnAction(e -> {
            List<Dosage> cpDosageList = new ArrayList();
            cpDosageList.addAll(dosageList);
            CompoundingProcedure tempCp = new CompoundingProcedure((short) 0, contentArea.getText(),
                    footer.isSelected(), null, null, cpDosageList);
            compoundingProcedureList.add(tempCp);
            //clear all values after adding the temporary procedure to the list
            dosageList.clear();
            footer.setSelected(false);
            contentArea.setText(null);
            rmReqChoiceBox.setValue(null);
            percentQty.setText(null);
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
    }

    private void initDosageTable() {
        dosagesTable.setPrefHeight(150);
        dosagesTable.getColumns().addAll(colAction, colMaterial, colPercent);
        colAction.setPrefWidth(100);
        colMaterial.setPrefWidth(400);
        colPercent.setPrefWidth(100);

        //
        dosagesTable.setItems(dosageList);
        colMaterial.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getRawMaterialRequirementId().toString()));
        colPercent.setCellValueFactory(c -> new SimpleObjectProperty(c.getValue().getPercentMultiplier()));

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

}
