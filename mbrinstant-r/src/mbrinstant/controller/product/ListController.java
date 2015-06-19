/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import mbrinstant.controller.product.ProductWizard.ProductWizard;
import mbrinstant.entity.main.Product;
import mbrinstant.service.main.ProductService;

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

    ProductService productService = new ProductService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initMbrListTableView();

        newProductButton.setOnAction(e -> {
//            Stage stage = new Stage();
//            stage.setScene(new Scene(new ProductWizard(stage), 400, 250));
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.setTitle("NEW Product");
//            stage.setResizable(false);
//            stage.show();
            
            try{
                new ProductWizard();
            }
            catch(IOException ex){
            }

        });
    }

    private void initMbrListTableView() {
        ObservableList<Product> productList = productService.getProductList();
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
