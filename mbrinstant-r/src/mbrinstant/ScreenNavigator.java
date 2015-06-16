/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import mbrinstant.controller.MainController;

/**
 *
 * @author maine
 */
public class ScreenNavigator {

    public static final String MAIN = "view/main.fxml";
    public static final String WELCOME_SCREEN = "view/welcome.fxml";
    public static final String BATCH_RECORD_SCREEN = "view/list.fxml";
    public static final String PRODUCT_SCREEN = "view/product/list.fxml";
  
    private static MainController mainController;

    public static void setMainController(MainController mainController) {
        ScreenNavigator.mainController = mainController;
    }

    public static void loadScreen(String fxml) {
        try {
            mainController.setScreen(
                    FXMLLoader.load(
                            ScreenNavigator.class.getResource(fxml)
                    )
            );
        } catch (IOException e) {
               e.printStackTrace();
        }
    }
}
