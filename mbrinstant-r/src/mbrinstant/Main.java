/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mbrinstant.controls.CustomAlertDialog;
import mbrinstant.controls.MyNotifications;
import mbrinstant.rest_client.audit.SingletonAuditRestClient;
import mbrinstant.rest_client.main.SingletonAreaRestClient;
import mbrinstant.rest_client.main.SingletonClassificationRestClient;
import mbrinstant.rest_client.main.SingletonContainerRestClient;
import mbrinstant.rest_client.main.SingletonEquipmentRestClient;
import mbrinstant.rest_client.main.SingletonPackSizeRestClient;
import mbrinstant.rest_client.main.SingletonProductRestClient;
import mbrinstant.rest_client.main.SingletonUnitRestClient;
import mbrinstant.rest_client.mbr.SingletonBottlingProcedureRestClient;
import mbrinstant.rest_client.mbr.SingletonCompoundingProcRestClient;
import mbrinstant.rest_client.mbr.SingletonDosageRestClient;
import mbrinstant.rest_client.mbr.SingletonEquipmentRequirementRestClient;
import mbrinstant.rest_client.mbr.SingletonMbrRestClient;
import mbrinstant.rest_client.mbr.SingletonMfgProcedureRestClient;
import mbrinstant.rest_client.mbr.SingletonPackgMaterialRequirementRestClient;
import mbrinstant.rest_client.mbr.SingletonPackgOperationRestClient;
import mbrinstant.rest_client.mbr.SingletonPowderFillingRestClient;
import mbrinstant.rest_client.mbr.SingletonRawMaterialRequirementRestClient;
import mbrinstant.rest_client.mbr.SingletonUdfRestClient;
import mbrinstant.rest_client.sqlsvr_copy.SingletonCompanyRestClient;
import mbrinstant.rest_client.sqlsvr_copy.SingletonItemRestClient;
import mbrinstant.rest_client.sqlsvr_copy.SingletonStockCardRestClient;
import mbrinstant.rest_client.transaction.SingletonStockCardTxnRestClient;
import mbrinstant.security.Authenticator;
import mbrinstant.security.LoginController;
import mbrinstant.security.SingletonAuthorizationManager;
import mbrinstant.security.User;

/**
 *
 * @author maine
 */
public class Main extends Application {

    private User currentUser;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.loadLoginDialog();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public boolean USER_AUTHORIZED;

    /**
     * *
     * Returns true if the user is valid.
     *
     * @param emailAd
     * @param pwd
     * @return
     * @throws java.lang.Exception
     */
    public void processLogin(String emailAd, String pwd)
            throws Exception {

        Authenticator authenticator = new Authenticator();
        User user = authenticator.loginUser(emailAd, pwd);
        if (user != null) {
            //authentication success
            this.setCurrentUser(user);
            this.getCurrentUser().setPassword(pwd);

            USER_AUTHORIZED = true;
        } else {
            USER_AUTHORIZED = false;
        }

    }

    public void configureUser() {
        String emailAd = currentUser.getEmailAd();
        String pwd = currentUser.getPassword();

        //configure all rest clients
        SingletonMbrRestClient.getInstance().setUsernameAndPassword(emailAd, pwd);
        SingletonProductRestClient.getInstance().setUsernameAndPassword(emailAd, pwd);
        SingletonUnitRestClient.getInstance().setUsernameAndPassword(emailAd, pwd);
        SingletonAreaRestClient.getInstance().setUsernameAndPassword(emailAd, pwd);
        SingletonClassificationRestClient.getInstance().setUsernameAndPassword(emailAd, pwd);
        SingletonCompanyRestClient.getInstance().setUsernameAndPassword(emailAd, pwd);
        SingletonContainerRestClient.getInstance().setUsernameAndPassword(emailAd, pwd);
        SingletonEquipmentRestClient.getInstance().setUsernameAndPassword(emailAd, pwd);
        SingletonPackSizeRestClient.getInstance().setUsernameAndPassword(emailAd, pwd);
        SingletonBottlingProcedureRestClient.getInstance().setUsernameAndPassword(emailAd, pwd);
        SingletonCompoundingProcRestClient.getInstance().setUsernameAndPassword(emailAd, pwd);
        SingletonDosageRestClient.getInstance().setUsernameAndPassword(emailAd, pwd);
        SingletonEquipmentRequirementRestClient.getInstance().setUsernameAndPassword(emailAd, pwd);
        SingletonMfgProcedureRestClient.getInstance().setUsernameAndPassword(emailAd, pwd);
        SingletonPackgMaterialRequirementRestClient.getInstance().setUsernameAndPassword(emailAd, pwd);
        SingletonPackgOperationRestClient.getInstance().setUsernameAndPassword(emailAd, pwd);
        SingletonPowderFillingRestClient.getInstance().setUsernameAndPassword(emailAd, pwd);
        SingletonRawMaterialRequirementRestClient.getInstance().setUsernameAndPassword(emailAd, pwd);
        SingletonUdfRestClient.getInstance().setUsernameAndPassword(emailAd, pwd);
        SingletonItemRestClient.getInstance().setUsernameAndPassword(emailAd, pwd);
        SingletonStockCardRestClient.getInstance().setUsernameAndPassword(emailAd, pwd);
        SingletonStockCardTxnRestClient.getInstance().setUsernameAndPassword(emailAd, pwd);
        SingletonAuthorizationManager.getInstance().setUsernameAndPassword(emailAd, pwd);
        SingletonAuditRestClient.getInstance().setUsernameAndPassword(emailAd, pwd);

        loadMainPane();
    }

    public void userLogout() {
        this.currentUser = null;

        //reset all rest clients
        SingletonMbrRestClient.getInstance().destroy();
        SingletonProductRestClient.getInstance().destroy();
        SingletonUnitRestClient.getInstance().destroy();
        SingletonAreaRestClient.getInstance().destroy();
        SingletonClassificationRestClient.getInstance().destroy();
        SingletonCompanyRestClient.getInstance().destroy();
        SingletonContainerRestClient.getInstance().destroy();
        SingletonEquipmentRestClient.getInstance().destroy();
        SingletonPackSizeRestClient.getInstance().destroy();
        SingletonBottlingProcedureRestClient.getInstance().destroy();
        SingletonCompoundingProcRestClient.getInstance().destroy();
        SingletonDosageRestClient.getInstance().destroy();
        SingletonEquipmentRequirementRestClient.getInstance().destroy();
        SingletonMfgProcedureRestClient.getInstance().destroy();
        SingletonPackgMaterialRequirementRestClient.getInstance().destroy();
        SingletonPackgOperationRestClient.getInstance().destroy();
        SingletonPowderFillingRestClient.getInstance().destroy();
        SingletonRawMaterialRequirementRestClient.getInstance().destroy();
        SingletonUdfRestClient.getInstance().destroy();
        SingletonItemRestClient.getInstance().destroy();
        SingletonStockCardRestClient.getInstance().destroy();
        SingletonStockCardTxnRestClient.getInstance().destroy();
        SingletonAuthorizationManager.getInstance().destroy();
        SingletonAuditRestClient.getInstance().destroy();

        this.loadLoginDialog();
        MyNotifications.displayConfirm("SUCCESSFULLY LOGGED OUT");
    }

    public void loadMainPane() {
        try {
            FXMLLoader loader = new FXMLLoader();
            MainController mainController = new MainController();
            mainController.setApplication(this);
            loader.setController(mainController);
            AnchorPane mainPane = (AnchorPane) loader.load(getClass().getResourceAsStream(FXMLLocations.MAIN));
            ScreenNavigator.setMainController(mainController);
            ScreenNavigator.loadScreen(FXMLLocations.WELCOME_SCREEN);
            Scene scene = new Scene(mainPane);
            scene.getStylesheets().add(this.getClass().getResource("css/main.css").toExternalForm());
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setResizable(false);
            stage.setTitle("Pharma System");
            stage.setMaximized(true);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            CustomAlertDialog.showExceptionDialog(e);
        }

    }

    private void loadLoginDialog() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXMLLocations.LOGIN_SCREEN));
            LoginController controller = new LoginController();
            controller.setApplication(this);
            fxmlLoader.setController(controller);
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Member Login");
            stage.setResizable(false);
            Scene scene = new Scene(root1);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            CustomAlertDialog.showExceptionDialog(e);
        }
    }
}
