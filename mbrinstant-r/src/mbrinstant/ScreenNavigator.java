/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import mbrinstant.controls.CustomAlertDialog;

/**
 *
 * @author maine
 */
public class ScreenNavigator {

    private static MainController mainController;

    public static void setMainController(MainController mainController) {
        ScreenNavigator.mainController = mainController;
    }

    public static MainController getMainController() {
        return mainController;
    }

    public static void loadScreen(String fxml) {
        try {
            mainController.setScreen(FXMLLoader.load(ScreenNavigator.class.getResource(fxml)));
        } catch (IOException e) {
            CustomAlertDialog.showExceptionDialog(e);
        }

    }

}
