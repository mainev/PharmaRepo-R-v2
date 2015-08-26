/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.utils;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mbrinstant.controller.batch_monitoring.ChildController;
import mbrinstant.controller.batch_monitoring.ParentController;
import mbrinstant.controls.CustomAlertDialog;

/**
 *
 * @author maine
 */
public class Config {

    public Config() {
    }

    /**
     * Closes the current stage
     *
     * @param stage - current stage
     * @param lb - any label node in the current stage
     * @param load - fxml resource file location
     * @param stageTitle - title of the new stage
     * @param resize - stage's resizable property
     * @param style - stage style property
     * @param maximized - stage maximized property
     */
    public void newStage(Stage stage, Label lb, String load, String stageTitle, boolean resize, StageStyle style, boolean maximized) {
        try {
            Stage st = new Stage();
            stage = (Stage) lb.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(load));
            Scene scene = new Scene(root);
            st.initStyle(style);
            st.setResizable(resize);
            st.setMaximized(maximized);
            st.setTitle(stageTitle);
            st.setScene(scene);
            st.show();
            stage.close();
        } catch (Exception e) {
            CustomAlertDialog.showExceptionDialog(e);
        }
    }

    public void newChildStage(String fxmlLocation, String stageTitle, boolean resize, StageStyle style, boolean maximized, Modality modality, ChildController controller) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlLocation));
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage st = new Stage();
            st.initStyle(style);
            st.setResizable(resize);
            st.setMaximized(maximized);
            st.setTitle(stageTitle);
            st.setScene(scene);
            st.initModality(modality);
            st.show();

        } catch (Exception e) {
            CustomAlertDialog.showExceptionDialog(e);
        }
    }

    public void newParentStage(String fxmlLocation, String stageTitle, boolean resize, StageStyle style, boolean maximized, Modality modality, ParentController controller) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlLocation));
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage st = new Stage();
            st.initStyle(style);
            st.setResizable(resize);
            st.setMaximized(maximized);
            st.setTitle(stageTitle);
            st.setScene(scene);
            st.initModality(modality);
            st.show();

        } catch (Exception e) {
            CustomAlertDialog.showExceptionDialog(e);
        }
    }

    public void loadAnchorPane(AnchorPane ap, String location, ChildController controller) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(location));
            loader.setController(controller);
            AnchorPane p = loader.load();
            ap.getChildren().setAll(p);
        } catch (IOException e) {
            CustomAlertDialog.showExceptionDialog(e);
        }
    }

}
