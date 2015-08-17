/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.product.ProductWizard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import mbrinstant.controls.ConstraintValidator;
import mbrinstant.controls.CustomTextArea;
import mbrinstant.entity.mbr.ManufacturingProcedure;
import mbrinstant.entity.mbr.PowderFillingProcedure;
import mbrinstant.exceptions.ServerException;
import mbrinstant.rest_client.mbr.SingletonPowderFillingRestClient;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class PowderFillingController implements Initializable, PageController {

    @FXML
    CustomTextArea doneBy;
    @FXML
    CustomTextArea checkedBy;
    @FXML
    CheckBox requiresEquipmentCheckBox;
    @FXML
    CustomTextArea instruction;
    @FXML
    Button addButton;

    @FXML
    TableView<PowderFillingProcedure> powderFillingTable;
    @FXML
    TableColumn colAction;
    @FXML
    TableColumn colStep;
    @FXML
    TableColumn<PowderFillingProcedure, PowderFillingProcedure> colInstruction;
    @FXML
    TableColumn colDoneBy;
    @FXML
    TableColumn colCheckedBy;

    //rest client
    SingletonPowderFillingRestClient powderFillingService = SingletonPowderFillingRestClient.getInstance();

    ConstraintValidator validator;
    ObservableList<PowderFillingProcedure> powderFillingList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createValidator();
        initPowderFillingTable();

        addButton.setOnAction(e -> {
            if (validator.validateFields()) {
                PowderFillingProcedure proc = new PowderFillingProcedure(getStepInstruction(), containsEquipment(), getDoneBy(), getCheckedBy());
                powderFillingList.add(proc);
            }
        });

        powderFillingList.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
                short i = 1;
                for (PowderFillingProcedure pf : powderFillingList) {
                    pf.setStepNumber(i);
                    i++;
                }
                //  refreshTable(packagingOperationTable);
            }
        });
    }

    public void createPackagingOperations(ManufacturingProcedure mfg) throws ServerException {
        if (!powderFillingList.isEmpty()) {
            for (PowderFillingProcedure p : powderFillingList) {
                powderFillingService.createNewPowderFilling(mfg.getId(), p);
            }
        }
    }

    private void initPowderFillingTable() {
        powderFillingTable.setItems(powderFillingList);
        colAction.setSortable(false);

        // define a simple boolean cell value for the action column so that the column will only be shown for non-empty rows.
        colAction.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PowderFillingProcedure, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<PowderFillingProcedure, Boolean> features) {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        });

        // create a cell value factory with an add button for each row in the table.
        colAction.setCellFactory(new Callback<TableColumn<PowderFillingProcedure, Boolean>, TableCell<PowderFillingProcedure, Boolean>>() {
            @Override
            public TableCell<PowderFillingProcedure, Boolean> call(TableColumn<PowderFillingProcedure, Boolean> erCol) {
                return new ActionCell();
            }
        });

        colInstruction.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<PowderFillingProcedure, PowderFillingProcedure>, ObservableValue<PowderFillingProcedure>>() {
                    @Override
                    public ObservableValue<PowderFillingProcedure> call(TableColumn.CellDataFeatures<PowderFillingProcedure, PowderFillingProcedure> cp) {
                        return new ReadOnlyObjectWrapper(cp.getValue());
                    }
                });

        colInstruction.setCellFactory(new Callback<TableColumn<PowderFillingProcedure, PowderFillingProcedure>, TableCell<PowderFillingProcedure, PowderFillingProcedure>>() {
            @Override
            public TableCell<PowderFillingProcedure, PowderFillingProcedure> call(TableColumn<PowderFillingProcedure, PowderFillingProcedure> col) {
                return new InstructionCell();
            }
        });

    }

    class InstructionCell extends TableCell<PowderFillingProcedure, PowderFillingProcedure> {

        @Override
        protected void updateItem(PowderFillingProcedure cp, boolean empty) {
            super.updateItem(cp, empty);
            if (cp != null) {
                String instruction = cp.getInstruction();
                if (cp.isRequiresEquipment()) {
                    instruction = instruction + "\n\n(Contains powder filling procedure equipments)";
                }
                setGraphic(new Label(instruction));
            } else {
                setGraphic(null);
            }
        }
    }

    class ActionCell extends TableCell<PowderFillingProcedure, Boolean> {

        HBox hbox = new HBox();
        Button delete = new Button("Remove");

        public ActionCell() {
            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().add(delete);

            delete.setOnAction(e -> {
                powderFillingTable.getSelectionModel().select(getTableRow().getIndex());
                powderFillingList.remove(getTableRow().getIndex());

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

    private String getCheckedBy() {
        return checkedBy.getText();
    }

    private String getDoneBy() {
        return doneBy.getText();
    }

    private boolean containsEquipment() {
        return requiresEquipmentCheckBox.isSelected();
    }

    private String getStepInstruction() {
        return instruction.getText();
    }

    @Override
    public void createValidator() {
        validator = new ConstraintValidator(instruction);
    }

    @Override
    public boolean allFieldsValid() {
        return true;
    }

    @Override
    public String getInstruction() {
        return "Set powder filling procedure";
    }

}
