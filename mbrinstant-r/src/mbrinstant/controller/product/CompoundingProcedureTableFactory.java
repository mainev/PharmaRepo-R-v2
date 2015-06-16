/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.product;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
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
            }
        }
    }
    
    public class DosageListCell extends TableCell<CompoundingProcedure, CompoundingProcedure> {

        HBox hbox = new HBox();
        ListView<Dosage> listView = new ListView();
        
        public DosageListCell() {
            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().add(listView);
            listView.setPrefHeight(100);
        }

        //Display text area if row is not empty
        @Override
        protected void updateItem(CompoundingProcedure cp, boolean empty) {
            super.updateItem(cp, empty);
            if (cp != null) {
                // setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setGraphic(hbox);
                listView.setItems(FXCollections.observableArrayList(cp.getDosageList()));
            }
        }
    }
}
