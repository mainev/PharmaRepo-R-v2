/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import mbrinstant.animations.FadeInUpTransition;
import mbrinstant.controls.CustomAlertDialog;
import mbrinstant.entity.main.Product;
import mbrinstant.exceptions.ServerException;
import mbrinstant.rest_client.main.SingletonProductRestClient;
import mbrinstant.controller.product.ProductWizard.ProductWizard;
import mbrinstant.security.SingletonAuthorizationManager;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class ListController implements Initializable {

    @FXML
    HBox _headerHBox;

    @FXML
    TableView<Product> productListTableView;
    @FXML
    TableColumn<Product, String> colProductCode;
    @FXML
    TableColumn<Product, String> colBrandName;
    @FXML
    TableColumn<Product, String> colGenericName;
    @FXML
    TableColumn<Product, String> colClientName;
    @FXML
    TableColumn<Product, String> colVrNo;
    @FXML
    TableColumn<Product, Short> colShelfLife;
    @FXML
    TableColumn<Product, String> colArea;
    @FXML
    TableColumn<Product, String> colPackSize;
    @FXML
    TableColumn colAction;

    @FXML
    Button newProductButton;

    //rest client
    SingletonProductRestClient productRestClient = SingletonProductRestClient.getInstance();
    private SingletonAuthorizationManager authManager = SingletonAuthorizationManager.getInstance();

    //methods
    private final String MAIN_METHOD_NAME = "view_product_list";
    private final String CREATE_NEW_PRODUCT = "create_new_product";

    @FXML
    private AnchorPane mainPane;
    @FXML
    private AnchorPane detailsPane;
    @FXML
    private ProgressIndicator progressIcon;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            if (authManager.isUserPermitted(MAIN_METHOD_NAME)) {
                productControllerService.start();
                productControllerService.setOnRunning((event) -> {
                    progressIcon.setVisible(true);
                });
                productControllerService.setOnSucceeded((event) -> {
                    progressIcon.setVisible(false);
                    detailsPane.setVisible(true);
                    new FadeInUpTransition(detailsPane).play();
                });
            } else {
                CustomAlertDialog.showAccessDeniedDialog();
                mainPane.setDisable(true);
            }
        } catch (ServerException ex) {
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showProductWizard() {
        try {
            if (authManager.isUserPermitted(CREATE_NEW_PRODUCT)) {
                try {
                    new ProductWizard();
                } catch (IOException ex) {
                    CustomAlertDialog.showExceptionDialog(ex);
                }
            } else {
                CustomAlertDialog.showAccessDeniedDialog();
            }
        } catch (ServerException ex) {
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private final Service productControllerService = new Service() {
        @Override
        protected Task createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    configureProductTable();

                    Thread.sleep(0);
                    return null;
                }
            };
        }
    };

    private void configureProductTable() throws ServerException {
        ObservableList<Product> productList = productRestClient.getProductList();

        productListTableView.setItems(productList);
        colProductCode.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCode()));
        colBrandName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getBrandName()));
        colGenericName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getGenericName()));
        colClientName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getClientId().getName()));
        colVrNo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getVrNo()));
        colShelfLife.setCellValueFactory(c -> new SimpleObjectProperty(c.getValue().getShelfLife()));
        colArea.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getAreaId().getName()));
        colPackSize.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPackSizeId().toString()));

        colAction.setSortable(false);

        // define a simple boolean cell value for the action column so that the column will only be shown for non-empty rows.
        colAction.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Product, Boolean> features) {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        });

        // create a cell value factory with an add button for each row in the table.
        colAction.setCellFactory(new Callback<TableColumn<Product, Boolean>, TableCell<Product, Boolean>>() {
            @Override
            public TableCell<Product, Boolean> call(TableColumn<Product, Boolean> productBooleanTableColumn) {
                return new ViewProductCellButton((Stage) productListTableView.getScene().getWindow(), productListTableView);
            }
        });
    }

}
