/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.product;

import javafx.scene.control.TableCell;
import javafx.scene.control.TextArea;
import mbrinstant.entity.mbr.PackagingProcedureOperation;

/**
 *
 * @author maine
 */
public class PackagingProcedureTableFactory {
   
    

    public class OperationProcedureCell extends TableCell<PackagingProcedureOperation, PackagingProcedureOperation> {

        TextArea textArea = new TextArea();

        public OperationProcedureCell() {
            textArea.setPrefHeight(100);
            textArea.setEditable(false);
        }

        //Display text area if row is not empty
        @Override
        protected void updateItem(PackagingProcedureOperation op, boolean empty) {
            super.updateItem(op, empty);
            if (op != null) {
                setGraphic(textArea);
                textArea.setText(op.getHeader());
            }
        }
    }

}
