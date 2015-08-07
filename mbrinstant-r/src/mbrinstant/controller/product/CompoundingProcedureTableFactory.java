/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.product;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import mbrinstant.entity.mbr.CompoundingProcedure;
import mbrinstant.entity.mbr.Dosage;

/**
 *
 * @author maine
 */
public class CompoundingProcedureTableFactory {

    public class HeaderCell extends TableCell<CompoundingProcedure, CompoundingProcedure> {

        TextArea textArea = new TextArea();

        public HeaderCell() {
            textArea.setPrefHeight(100);
            textArea.setEditable(false);
        }

        //Display text area if row is not empty
        @Override
        protected void updateItem(CompoundingProcedure cp, boolean empty) {
            super.updateItem(cp, empty);
            if (cp != null) {
                setGraphic(textArea);
                textArea.setText(cp.getHeader());
            } else {
                setGraphic(null);
            }
        }
    }

    public class ActionCell extends TableCell<CompoundingProcedure, Boolean> {

        HBox hbox = new HBox();
        Button delete = new Button("Remove");

        public ActionCell(ObservableList list, TableView table) {
            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().add(delete);

            delete.setOnAction(e -> {
                table.getSelectionModel().select(getTableRow().getIndex());
                list.remove(getTableRow().getIndex());
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

    public class FooterCell extends TableCell<CompoundingProcedure, CompoundingProcedure> {

        HBox hbox = new HBox();
        CheckBox checkBox = new CheckBox();

        public FooterCell() {
            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().add(checkBox);

            checkBox.setDisable(true);
        }

        //Display text area if row is not empty
        @Override
        protected void updateItem(CompoundingProcedure cp, boolean empty) {
            super.updateItem(cp, empty);
            if (cp != null) {
                // setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setGraphic(hbox);
                checkBox.setSelected(cp.getFooter());
            } else {
                setGraphic(null);
            }
        }
    }

    public class DosageListCell extends TableCell<CompoundingProcedure, CompoundingProcedure> {

        HBox hbox = new HBox();
        //  ListView<Dosage> listView = new ListView();
        TableView<Dosage> dosageTable = new TableView();
        TableColumn<Dosage, String> percent = new TableColumn("Qty");
        TableColumn<Dosage, String> rawMaterial = new TableColumn("Raw Material");

        public DosageListCell() {
            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().add(dosageTable);

            percent.setPrefWidth(100);
            rawMaterial.setPrefWidth(400);

            dosageTable.getColumns().addAll(rawMaterial, percent);
            dosageTable.setPrefHeight(117);
            dosageTable.setPrefWidth(500);

            percent.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getPercentMultiplier())));
            rawMaterial.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getRawMaterialRequirementId().getRawMaterialId().getName()));
        }

        //Display text area if row is not empty
        @Override
        protected void updateItem(CompoundingProcedure cp, boolean empty) {
            super.updateItem(cp, empty);
            if (cp != null && !cp.getDosageList().isEmpty()) {
                setGraphic(dosageTable);
                dosageTable.setItems(FXCollections.observableArrayList(cp.getDosageList()));
            } else {
                setGraphic(null);
            }
        }
    }
}
