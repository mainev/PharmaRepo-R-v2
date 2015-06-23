/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.product.ProductWizard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import mbrinstant.controls.MyNotifications;

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

    public static String MAIN_DETAILS_LOCATION = "view/main_details.fxml";
    public static String COMPPROC_LOCATION = "view/compounding_procedures.fxml";
    public static String RMREQ_LOCATION = "view/raw_material_requirement.fxml";
    public static String PMREQ_LOCATION = "view/packaging_material_requirement.fxml";
    public String EQUIPREQ_LOCATION = "view/equipment_requirements.fxml";

    private AnchorPane MAIN_DETAILS_PAGE;
    private AnchorPane RMREQ_PAGE;
    private AnchorPane PMREQ_PAGE;
    private AnchorPane COMPROC_PAGE;
    private AnchorPane EQUIPREQ_PAGE;

    static ObservableList<AnchorPane> pages;

    static int FIRST_PAGE = 0;

    PageController currentController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("product wizard controller initialized");

        try {
            loadPages();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        initStepsListView();
        initButtons();
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

    }

    private void initStepsListView() {
        //for disabling list view selection
//        stepList.setMouseTransparent(true);
//        stepList.setFocusTraversable(false);

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

        stepList.getSelectionModel().selectedIndexProperty().addListener((ob, ov, nv) -> {
            if (pages.size() != 0) {
                if ((int) nv == FIRST_PAGE) {
                    previousButton.setDisable(true);
                    nextButton.setDisable(false);
                    finishButton.setDisable(true);
                } else if (((int) nv + 2) > pages.size()) {
                    previousButton.setDisable(false);
                    nextButton.setDisable(true);
                    finishButton.setDisable(false);
                } else {
                    previousButton.setDisable(false);
                    nextButton.setDisable(false);
                    finishButton.setDisable(true);
                }
            }

        });
        stepList.getSelectionModel().selectFirst();
    }

    public void setScreen(Node node) {
        mainStackPane.getChildren().setAll(node);
    }

    static class AnchorPaneCell extends ListCell<AnchorPane> {

        @Override
        public void updateItem(AnchorPane item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                PageController cn = (PageController) item.getUserData();
                Label label = new Label(cn.getInstruction());
                setGraphic(label);
            }
        }
    }

    public void loadPages() throws IOException {
        pages = FXCollections.observableArrayList();

        FXMLLoader loader = new FXMLLoader();
        MAIN_DETAILS_PAGE = (AnchorPane) loader.load(getClass().getResourceAsStream(MAIN_DETAILS_LOCATION));
        //  mainDetailsController = loader.getController();
        MAIN_DETAILS_PAGE.setUserData(loader.getController());

        loader = new FXMLLoader();
        RMREQ_PAGE = (AnchorPane) loader.load(getClass().getResourceAsStream(RMREQ_LOCATION));
        RawMaterialRequirementController rmReqController = loader.getController();
        RMREQ_PAGE.setUserData(loader.getController());
//
        loader = new FXMLLoader();
        PMREQ_PAGE = (AnchorPane) loader.load(getClass().getResourceAsStream(PMREQ_LOCATION));
        //  pmReqController = loader.getController();
        PMREQ_PAGE.setUserData(loader.getController());
//        
        loader = new FXMLLoader();
        COMPROC_PAGE = (AnchorPane) loader.load(getClass().getResourceAsStream(COMPPROC_LOCATION));
        CompoundingProceduresController cpcontroller = loader.getController();
        Bindings.bindContentBidirectional(cpcontroller.rmReqList, rmReqController.getRmReqTemporaryList());
        COMPROC_PAGE.setUserData(loader.getController());

        loader = new FXMLLoader();
        EQUIPREQ_PAGE = (AnchorPane) loader.load(getClass().getResourceAsStream(EQUIPREQ_LOCATION));
        EQUIPREQ_PAGE.setUserData(loader.getController());

        pages.add(MAIN_DETAILS_PAGE);
        pages.add(RMREQ_PAGE);
        pages.add(PMREQ_PAGE);
        pages.add(COMPROC_PAGE);
        pages.add(EQUIPREQ_PAGE);
    }

//    public void loadScreen(Node node) {
//
//        setScreen(node);
//
//    }
}
