/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mbrinstant.Main;
import mbrinstant.ScreenNavigator;
import mbrinstant.controller.MainController;
import mbrinstant.controls.MyNotifications;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class LoginController implements Initializable {

    private Main application;
    @FXML
    TextField emailAd;
    @FXML
    PasswordField password;
    @FXML
    Button loginButton;
    @FXML
    Button cancelButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginButton.setOnAction(e -> {

            try {
                if (application.processLogin(emailAd.getText(), password.getText())) {
                    MyNotifications.displayConfirm("ACCESS GRANTED");
                    closeStage();
                } else {
                    MyNotifications.displayError("ACCESS DENIED");
                }
            } catch (Exception ex) {
                //this happens when the application fails to create an authenticator class
                //create exception handler here that will notify the server of an application error

            }
        });

        cancelButton.setOnAction(e -> {
            closeStage();
        });
    }

    private void closeStage() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void loadMainPane() {

        //  FXMLLoader loader = new FXMLLoader();
        //   AnchorPane mainPane = (AnchorPane) loader.load(getClass().getResourceAsStream(ScreenNavigator.MAIN));
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ScreenNavigator.MAIN));
        AnchorPane mainPane = loader.getRoot();
        MainController mainController = loader.getController();
        ScreenNavigator.setMainController(mainController);
        ScreenNavigator.loadScreen(ScreenNavigator.WELCOME_SCREEN);
        Scene scene = new Scene(mainPane);
        Stage stage = new Stage();
        // stage.setResizable(false);
        stage.setTitle("Pharma");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();

    }

    public Main getApplication() {
        return application;
    }

    public void setApplication(Main application) {
        this.application = application;
    }

}
