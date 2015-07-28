/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import mbrinstant.ScreenNavigator;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class MainController implements Initializable {

    @FXML
    private ToggleButton batchRecordsButton;
    @FXML
    private ToggleButton productButton;
    @FXML
    private ToggleButton auditButton;
    @FXML
    private ToggleButton projectionButton;
    @FXML
    private ToggleButton reservationButton;
    @FXML
    private ToggleButton stockCardButton;
    @FXML
    private StackPane _mainStackPane;

    @FXML
    private AnchorPane mainHeader;
    @FXML
    private HBox mainFooter;
    @FXML
    private AnchorPane mainAnchorPane;

    final ToggleGroup menuToggleGroup = new ToggleGroup();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setMenuButtonToggleGroup();
        addToggleGroupListener();

//        ProgressIndicator progressIndicator = new ProgressIndicator();
//        progressIndicator.setLayoutX(500);
//        progressIndicator.setLayoutY(500);
//        mainAnchorPane.getChildren().add(progressIndicator);
    }

    private void addToggleGroupListener() {
        batchRecordsButton.setUserData(ScreenNavigator.BATCH_RECORD_SCREEN);
        productButton.setUserData(ScreenNavigator.PRODUCT_SCREEN);
        auditButton.setUserData(ScreenNavigator.AUDIT_LIST_FXML);
        projectionButton.setUserData(ScreenNavigator.PROJECTION_FXML);
        reservationButton.setUserData(ScreenNavigator.RESERVATION_FXML);
        stockCardButton.setUserData(ScreenNavigator.STOCKCARD_LIST_FXML);

        menuToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                    Toggle toggle, Toggle new_toggle) {
                if (new_toggle == null) {
                    ScreenNavigator.loadScreen(ScreenNavigator.WELCOME_SCREEN);
                } else {
                    String userData = (String) menuToggleGroup.getSelectedToggle().getUserData();
                    ScreenNavigator.loadScreen(userData);
                }
            }
        });
    }

    private void setMenuButtonToggleGroup() {
        batchRecordsButton.setToggleGroup(menuToggleGroup);
        productButton.setToggleGroup(menuToggleGroup);
        auditButton.setToggleGroup(menuToggleGroup);
        projectionButton.setToggleGroup(menuToggleGroup);
        reservationButton.setToggleGroup(menuToggleGroup);
        stockCardButton.setToggleGroup(menuToggleGroup);
    }

    public void setScreen(Node node) {
        _mainStackPane.getChildren().setAll(node);
    }

    public void closeApp() {
        Stage stage = (Stage) _mainStackPane.getScene().getWindow();
        stage.close();
    }

}
