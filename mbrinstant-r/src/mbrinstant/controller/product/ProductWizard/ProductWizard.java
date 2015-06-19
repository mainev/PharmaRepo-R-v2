/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.product.ProductWizard;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author maine
 */
public class ProductWizard {

    Stage mainStage = new Stage();
    public static String PRODUCT_WIZARD_LOCATION = "view/product_wizard.fxml";
    static ProductWizardController mainController;
    
 
  

    public ProductWizard() throws IOException {
       //    loadPages();
        Pane mainPane = loadMainPane();
        Scene scene = new Scene(mainPane);

        mainStage.initModality(Modality.APPLICATION_MODAL);
        mainStage.setTitle("New Product");
        mainStage.setResizable(false);
        mainStage.setScene(scene);
        mainStage.show();
        
        
      //  loadScreen(MAIN_DETAILS_PAGE);//default page in the stack pane
        
    }

    private Pane loadMainPane() throws IOException {
       
        FXMLLoader loader = new FXMLLoader();
        AnchorPane mainPane = (AnchorPane) loader.load(getClass().getResourceAsStream(PRODUCT_WIZARD_LOCATION));
      //  ProductWizardController mainController = loader.getController();
      //  this.mainController = mainController;
        return mainPane;
    }

   
   
    
//    public static AnchorPane getFirstPage(){
//        return pages.get(0);
//    }
}
