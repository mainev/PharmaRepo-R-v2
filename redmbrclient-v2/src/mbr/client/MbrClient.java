/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbr.client;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import mbr.client.controller.MainController;

/**
 *
 * @author maine
 */
public class MbrClient extends Application {
    
     @Override
    public void start(Stage stage) throws Exception {
       //  setUserAgentStylesheet(STYLESHEET_CASPIAN);
        Pane mainPane = loadMainPane();
        Scene scene = new Scene(mainPane);
        stage.setTitle("");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private Pane loadMainPane() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        AnchorPane mainPane = (AnchorPane) loader.load(getClass().getResourceAsStream(ScreenNavigator.MAIN));
        MainController mainController = loader.getController();
        ScreenNavigator.setMainController(mainController);

        ScreenNavigator.loadScreen(ScreenNavigator.WELCOME_SCREEN);
        return mainPane;
    }
    
}
