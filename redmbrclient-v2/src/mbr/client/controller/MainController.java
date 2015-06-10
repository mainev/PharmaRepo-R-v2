/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbr.client.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import mbr.client.ScreenNavigator;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class MainController implements Initializable {
   
    @FXML
    private ToggleButton _menuButtonMBR;
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
        _menuButtonMBR.setUserData(ScreenNavigator.MBR_LIST_SCREEN);

        menuToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                    Toggle toggle, Toggle new_toggle) {
                if (new_toggle == null) {
                    ScreenNavigator.loadScreen(ScreenNavigator.WELCOME_SCREEN);
                } else {
                    ScreenNavigator.loadScreen((String) menuToggleGroup.getSelectedToggle().getUserData());
                }
            }
        });
    }

    private void setMenuButtonToggleGroup() {
        _menuButtonMBR.setToggleGroup(menuToggleGroup);
        //  _menuButtonMBREditor.setToggleGroup(menuToggleGroup);
    }

    public void setScreen(Node node) {
        _mainStackPane.getChildren().setAll(node);
    }

    public void closeApp() {
        Stage stage = (Stage) _mainStackPane.getScene().getWindow();
        stage.close();

    }
    
}
