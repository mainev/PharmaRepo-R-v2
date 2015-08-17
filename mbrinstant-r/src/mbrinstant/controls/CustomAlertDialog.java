/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controls;

import javafx.scene.control.Alert;
import org.controlsfx.dialog.ExceptionDialog;

/**
 *
 * @author maine
 */
public class CustomAlertDialog {

    public static void showErrorAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.show();

    }

    public static void showAccessDeniedDialog() {
        showErrorAlert("Access Denied", "ACCESS DENIED", "You are not allowed to access this feature.");
    }

    public static void showExceptionDialog(Exception e) {
        ExceptionDialog dlg = new ExceptionDialog(e);
        dlg.show();
    }
}
