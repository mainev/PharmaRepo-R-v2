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
    private StackPane _mainStackPane;
    
    final ToggleGroup menuToggleGroup = new ToggleGroup();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setMenuButtonToggleGroup();
        addToggleGroupListener();
    }
    
    private void addToggleGroupListener() {
        batchRecordsButton.setUserData(ScreenNavigator.BATCH_RECORD_SCREEN);
        productButton.setUserData(ScreenNavigator.PRODUCT_SCREEN);
        auditButton.setUserData(ScreenNavigator.AUDIT_LIST_FXML);
        projectionButton.setUserData(ScreenNavigator.PROJECTION_FXML);
        
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
    }
    
    public void setScreen(Node node) {
        _mainStackPane.getChildren().setAll(node);
    }
    
    public void closeApp() {
        Stage stage = (Stage) _mainStackPane.getScene().getWindow();
        stage.close();
    }
    
}
