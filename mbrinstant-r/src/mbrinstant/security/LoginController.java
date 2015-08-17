/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.security;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mbrinstant.Main;
import mbrinstant.controls.CustomAlertDialog;
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
        cancelButton.setOnAction(e -> {
            Platform.exit();
            System.exit(0);
        });
    }

    @FXML
    private void login() {
        try {
            application.processLogin(emailAd.getText(), password.getText());
            if (application.USER_AUTHORIZED) {
                application.configureUser();
                MyNotifications.displayConfirm("ACCESS GRANTED");
                closeStage();
            } else {
                MyNotifications.displayError("ACCESS DENIED");
            }
        } catch (Exception ex) {
            CustomAlertDialog.showExceptionDialog(ex);
        }
    }

    private void closeStage() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public Main getApplication() {
        return application;
    }

    public void setApplication(Main application) {
        this.application = application;
    }

}
