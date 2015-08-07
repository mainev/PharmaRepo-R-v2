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
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import mbrinstant.Main;
import mbrinstant.ScreenNavigator;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class MainController implements Initializable {

    private Main application;

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

    //user info
    @FXML
    private Label username;
    @FXML
    private Label logout;//serves as hyperlink to logout

    final ToggleGroup menuToggleGroup = new ToggleGroup();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        username.setText(application.getCurrentUser().getFirstName());
        setMenuButtonToggleGroup();
        addMenuToggleGroupListener();

//        ProgressIndicator progressIndicator = new ProgressIndicator();
//        progressIndicator.setLayoutX(500);
//        progressIndicator.setLayoutY(500);
//        mainAnchorPane.getChildren().add(progressIndicator);
        //do action for logout here...
        logout.setOnMouseClicked(event -> {

            closeApp();
            application.userLogout();

        });

    }

    public void batchRecordMethod() {
        ScreenNavigator.loadScreen(ScreenNavigator.BATCH_RECORD_SCREEN);
    }

    private void addMenuToggleGroupListener() {

        //   batchRecordsButton.setUserData("batchRecordMethod");//shows batch record list
//        productButton.setUserData(ScreenNavigator.PRODUCT_SCREEN);//shows product list
//        auditButton.setUserData(ScreenNavigator.AUDIT_LIST_FXML);//shows audit list
//        projectionButton.setUserData(ScreenNavigator.PROJECTION_FXML);//show projection pane
//        reservationButton.setUserData(ScreenNavigator.RESERVATION_FXML);//show batch record list for mmd
//        stockCardButton.setUserData(ScreenNavigator.STOCKCARD_LIST_FXML);//show stockcard pane
//
        batchRecordsButton.setUserData(ScreenNavigator.BATCH_RECORD_SCREEN);//shows batch record list
        productButton.setUserData(ScreenNavigator.PRODUCT_SCREEN);//shows product list
        auditButton.setUserData(ScreenNavigator.AUDIT_LIST_FXML);//shows audit list
        projectionButton.setUserData(ScreenNavigator.PROJECTION_FXML);//show projection pane
        reservationButton.setUserData(ScreenNavigator.RESERVATION_FXML);//show batch record list for mmd
        stockCardButton.setUserData(ScreenNavigator.STOCKCARD_LIST_FXML);//show stockcard pane

        menuToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                    Toggle toggle, Toggle new_toggle) {
                if (new_toggle == null) {
                    //load the default welcome screen
                    ScreenNavigator.loadScreen(ScreenNavigator.WELCOME_SCREEN);
                } else {
                    String userData = (String) menuToggleGroup.getSelectedToggle().getUserData();
                    ScreenNavigator.loadScreen(userData);
//
//                    try {
//                        Class cls = Class.forName("mbrinstant.controller.MainController");
//                        Object obj = cls.newInstance();
//                        Class noparams[] = {};
//                        String userData = (String) menuToggleGroup.getSelectedToggle().getUserData();
//                        Method method = cls.getDeclaredMethod(userData, noparams);
//                        method.invoke(obj, noparams);
//                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException ex) {
//                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
//                    }

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

    public Main getApplication() {
        return application;
    }

    public void setApplication(Main application) {
        this.application = application;
    }

}
