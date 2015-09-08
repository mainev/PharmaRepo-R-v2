/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.product.ProductWizard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import mbrinstant.FXMLLocations;
import mbrinstant.ScreenNavigator;
import mbrinstant.controls.CustomChoiceBox;
import mbrinstant.controls.MyNotifications;
import mbrinstant.entity.ProcedureCategory;
import mbrinstant.entity.main.Area;
import mbrinstant.entity.main.Product;
import mbrinstant.entity.mbr.RawMaterialRequirement;
import mbrinstant.exceptions.ServerException;

/**
 *
 * @author maine
 */
public class ProductWizardController implements Initializable {

    @FXML
    StackPane mainStackPane;

    @FXML
    ListView<AnchorPane> stepList;

    @FXML
    Button previousButton;
    @FXML
    Button nextButton;
    @FXML
    Button finishButton;
    @FXML
    Button cancelButton;

    String MAIN_DETAILS_LOCATION = "view/main_details.fxml";
    String COMPPROC_LOCATION = "view/compounding_procedures.fxml";
    String RMREQ_LOCATION = "view/raw_material_requirement.fxml";
    String PMREQ_LOCATION = "view/packaging_material_requirement.fxml";
    String EQUIPREQ_LOCATION = "view/equipment_requirements.fxml";
    String PACKG_OPERATION_LOCATION = "view/packaging_operation.fxml";
    String BOTTLING_PROC_LOCATION = "view/bottling_procedure.fxml";
    String PRIMARY_SECONDARY_LOCATION = "view/primary_secondary_packg.fxml";
    String POWDER_FILLING_LOCATION = "view/powder_filling.fxml";

    private AnchorPane MAIN_DETAILS_PAGE;
    private AnchorPane RMREQ_PAGE;
    private AnchorPane PMREQ_PAGE;
    private AnchorPane COMPROC_PAGE;
    private AnchorPane EQUIPREQ_PAGE;
    private AnchorPane PACKG_OPERATION_PAGE;
    private AnchorPane BOTTLING_PROC_PAGE;
    private AnchorPane PRIMARY_SECONDARY_PAGE;
    private AnchorPane POWDER_FILLING_PAGE;

    ObservableList<AnchorPane> pages = FXCollections.observableArrayList();

    int FIRST_PAGE = 0;

    PageController currentController;

    //controllers
    public static MainDetailsController mainDetailsController;
    public RawMaterialRequirementController rmReqController;
    public PackagingMaterialRequirementController pmReqController;
    public CompoundingProceduresController cpcontroller;
    public static EquipmentRequirementController equipReqController;
    public PackagingOperationController packgOperationController;
    public BottlingProcedureController bottlingProcedureController;
    public PrimarySecondaryController primarySecondaryController;
    public PowderFillingController powderFillingController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("product wizard controller initialized");

        try {
            loadControllers();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        setPagesByArea();
        initStepsListView();
        setInstructionPagesByArea();

        initButtons();

    }

    ObservableList<AnchorPane> DEFAULT_PAGES = FXCollections.observableArrayList();
    ObservableList<AnchorPane> TABLET_HUMAN_PAGES = FXCollections.observableArrayList();
    ObservableList<AnchorPane> POWDER_VET_PAGES = FXCollections.observableArrayList();
    ObservableList<AnchorPane> POWDER_AREA_PAGES = FXCollections.observableArrayList();

    private void setPagesByArea() {
        DEFAULT_PAGES.setAll(
                MAIN_DETAILS_PAGE
        //                RMREQ_PAGE,
        //                PMREQ_PAGE,
        //                COMPROC_PAGE,
        //                EQUIPREQ_PAGE,
        //                PACKG_OPERATION_PAGE,
        //                PRIMARY_SECONDARY_PAGE
        );

        TABLET_HUMAN_PAGES.setAll(
                MAIN_DETAILS_PAGE,
                RMREQ_PAGE,
                PMREQ_PAGE,
                COMPROC_PAGE,
                EQUIPREQ_PAGE,
                BOTTLING_PROC_PAGE,
                PACKG_OPERATION_PAGE,
                PRIMARY_SECONDARY_PAGE);

        POWDER_VET_PAGES.setAll(
                MAIN_DETAILS_PAGE,
                RMREQ_PAGE,
                PMREQ_PAGE,
                COMPROC_PAGE,
                EQUIPREQ_PAGE,
                POWDER_FILLING_PAGE,
                PACKG_OPERATION_PAGE,
                PRIMARY_SECONDARY_PAGE);

        POWDER_AREA_PAGES.setAll(
                MAIN_DETAILS_PAGE,
                RMREQ_PAGE,
                PMREQ_PAGE,
                COMPROC_PAGE,
                EQUIPREQ_PAGE,
                POWDER_FILLING_PAGE,
                PACKG_OPERATION_PAGE,
                PRIMARY_SECONDARY_PAGE);

        pages.setAll(DEFAULT_PAGES);
    }

    private void setInstructionPagesByArea() {
        equipReqController.procedureChoiceBox.setItems(FXCollections.observableArrayList(ProcedureCategory.values()));

        CustomChoiceBox area = mainDetailsController.areaChoiceBox;
        area.getSelectionModel().selectedItemProperty().addListener((ob, ov, nv) -> {
            Area selectedArea = (Area) nv;
            equipReqController.equipmentRequirementList.clear();

            if (selectedArea.getName().equals("TABLET HUMAN")) {
                pages.setAll(TABLET_HUMAN_PAGES);
                //  ObservableList<String> equipmentLocations = FXCollections.observableArrayList(EquipmentLocations.getEquipmentLocationsForTabletHuman());
                //  equipReqController.procedureChoiceBox.setItems(equipmentLocations);

            } else if (selectedArea.getName().equals("POWDER VET")) {
                pages.setAll(POWDER_VET_PAGES);
                //   ObservableList<String> equipmentLocations = FXCollections.observableArrayList(EquipmentLocations.getEquipmentLocationsForPowderVet());
                //   equipReqController.procedureChoiceBox.setItems(equipmentLocations);

            } else if (selectedArea.getName().equals("POWDER AREA")) {
                pages.setAll(POWDER_AREA_PAGES);
                //  ObservableList<String> equipmentLocations = FXCollections.observableArrayList(EquipmentLocations.getEquipmentLocationsForPowderArea());
                //   equipReqController.procedureChoiceBox.setItems(equipmentLocations);
            } else {
                pages.setAll(DEFAULT_PAGES);
                //  ObservableList<String> equipmentLocations = FXCollections.observableArrayList(EquipmentLocations.getEquipmentLocationsForAll());
                //   equipReqController.procedureChoiceBox.setItems(equipmentLocations);
            }
        });

    }

    private void initButtons() {
        nextButton.setOnAction(e -> {
            if (currentController.allFieldsValid()) {
                stepList.getSelectionModel().select(stepList.getSelectionModel().getSelectedIndex() + 1);
            } else {
                MyNotifications.displayError("Please enter all required fields");
            }
        });
        previousButton.setOnAction(e -> {
            stepList.getSelectionModel().select(stepList.getSelectionModel().getSelectedIndex() - 1);
        });
        cancelButton.setOnAction(e -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
        finishButton.setOnAction(e -> {
            if (currentController.allFieldsValid()) {
                try {
                    mainDetailsController.createFinalProduct();
                    Product finalProduct = mainDetailsController.getFinalProduct();
                    rmReqController.createRawMaterialRequirements(finalProduct.getUdfId());
                    pmReqController.createPackagingMaterialRequirements(finalProduct.getUdfId());
                    cpcontroller.createCompoundingProcedures(finalProduct.getManufacturingProcedureId(), finalProduct.getUdfId().getId());
                    equipReqController.createEquipmentRequirements(finalProduct.getManufacturingProcedureId());
                    packgOperationController.createPackagingOperations(finalProduct.getManufacturingProcedureId());
                    bottlingProcedureController.createBottlingProcedures(finalProduct.getManufacturingProcedureId());
                    powderFillingController.createPackagingOperations(finalProduct.getManufacturingProcedureId());
                    primarySecondaryController.createPrimarySecondaryPackg(finalProduct, finalProduct.getUdfId().getId());
                    Stage stage = (Stage) finishButton.getScene().getWindow();
                    stage.close();
                    ScreenNavigator.loadScreen(FXMLLocations.PRODUCT_LIST);

                } catch (ServerException ex) {
                    Logger.getLogger(ProductWizardController.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                MyNotifications.displayError("Please enter all required fields");
            }

        });

    }

    private void initStepsListView() {
        //for disabling list view selection
        //  stepList.setMouseTransparent(true);
        //  stepList.setFocusTraversable(false);

        stepList.getItems().clear();
        stepList.setItems(pages);
        stepList.setCellFactory(new Callback<ListView<AnchorPane>, ListCell<AnchorPane>>() {
            @Override
            public ListCell<AnchorPane> call(ListView<AnchorPane> list) {
                return new AnchorPaneCell();
            }
        }
        );

        stepList.getSelectionModel().selectedItemProperty().addListener((ob, ov, nv) -> {
            if (nv != null) {
                currentController = (PageController) nv.getUserData();
                setScreen(nv);
            }
        });

        stepList.getSelectionModel().selectedIndexProperty().addListener((ob, ov, index) -> {
            if (pages.size() != 0) {
                if ((int) index == FIRST_PAGE && (stepList.getItems().size() <= 1)) {
                    previousButton.setDisable(true);
                    nextButton.setDisable(true);
                    finishButton.setDisable(false);

                } else if ((int) index == FIRST_PAGE) {
                    previousButton.setDisable(true);
                    nextButton.setDisable(false);
                    finishButton.setDisable(true);
                } else if (((int) index + 1) < pages.size()) {
                    previousButton.setDisable(false);
                    nextButton.setDisable(false);
                    finishButton.setDisable(true);
                } else {
                    previousButton.setDisable(false);
                    nextButton.setDisable(true);
                    finishButton.setDisable(false);
                }
            }

        });
        stepList.getSelectionModel().selectFirst();
    }

    public void setScreen(Node node) {
        mainStackPane.getChildren().setAll(node);

    }

    //sets what to display in the steps pane
    class AnchorPaneCell extends ListCell<AnchorPane> {

        @Override
        public void updateItem(AnchorPane item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                PageController cn = (PageController) item.getUserData();

                int index = pages.indexOf(item);
                Label label = new Label(index + 1 + ". " + cn.getInstruction());
                setGraphic(label);
            } else {
                setGraphic(null);
            }
        }
    }

    ObservableList<RawMaterialRequirement> rmReqList = FXCollections.observableArrayList();

    public void loadControllers() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        MAIN_DETAILS_PAGE = (AnchorPane) loader.load(getClass().getResourceAsStream(MAIN_DETAILS_LOCATION));
        mainDetailsController = loader.getController();
        MAIN_DETAILS_PAGE.setUserData(loader.getController());

        loader = new FXMLLoader();
        RMREQ_PAGE = (AnchorPane) loader.load(getClass().getResourceAsStream(RMREQ_LOCATION));
        rmReqController = loader.getController();
        rmReqList = rmReqController.getRmReqTemporaryList();
        RMREQ_PAGE.setUserData(loader.getController());

        loader = new FXMLLoader();
        PMREQ_PAGE = (AnchorPane) loader.load(getClass().getResourceAsStream(PMREQ_LOCATION));
        pmReqController = loader.getController();
        PMREQ_PAGE.setUserData(loader.getController());

        loader = new FXMLLoader();
        COMPROC_PAGE = (AnchorPane) loader.load(getClass().getResourceAsStream(COMPPROC_LOCATION));
        cpcontroller = loader.getController();
        Bindings.bindContentBidirectional(cpcontroller.rmReqList, rmReqController.getRmReqTemporaryList());
        COMPROC_PAGE.setUserData(loader.getController());

        loader = new FXMLLoader();
        EQUIPREQ_PAGE = (AnchorPane) loader.load(getClass().getResourceAsStream(EQUIPREQ_LOCATION));
        equipReqController = loader.getController();
        EQUIPREQ_PAGE.setUserData(loader.getController());

        loader = new FXMLLoader();
        PACKG_OPERATION_PAGE = (AnchorPane) loader.load(getClass().getResourceAsStream(PACKG_OPERATION_LOCATION));
        packgOperationController = loader.getController();
        PACKG_OPERATION_PAGE.setUserData(loader.getController());

        loader = new FXMLLoader();
        BOTTLING_PROC_PAGE = (AnchorPane) loader.load(getClass().getResourceAsStream(BOTTLING_PROC_LOCATION));
        bottlingProcedureController = loader.getController();
        BOTTLING_PROC_PAGE.setUserData(loader.getController());

        loader = new FXMLLoader();
        POWDER_FILLING_PAGE = (AnchorPane) loader.load(getClass().getResourceAsStream(POWDER_FILLING_LOCATION));
        powderFillingController = loader.getController();
        POWDER_FILLING_PAGE.setUserData(loader.getController());

        loader = new FXMLLoader();
        PRIMARY_SECONDARY_PAGE = (AnchorPane) loader.load(getClass().getResourceAsStream(PRIMARY_SECONDARY_LOCATION));
        primarySecondaryController = loader.getController();
        Bindings.bindContentBidirectional(primarySecondaryController.pmReqList, pmReqController.pmReqTemporaryList);
        PRIMARY_SECONDARY_PAGE.setUserData(loader.getController());
    }

}
