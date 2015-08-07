/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.product;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mbrinstant.entity.main.Product;
import mbrinstant.rest_client.main.SingletonProductRestClient;

/**
 * Modified version of jewelsea JavaFX sample TableView with Add Buttons sources
 *
 * @web https://gist.github.com/jewelsea/3081826
 *
 * @author maine
 */
public class ViewProductCellButton extends TableCell<Product, Boolean> {

    // a button for adding a new person.
    final Button viewDetailsButton = new Button("View");
    // pads and centers the add button in the cell.
    final HBox paddedButton = new HBox();
//    // records the y pos of the last button press so that the add person dialog can be shown next to the cell.
//    final DoubleProperty buttonY = new SimpleDoubleProperty();

    /**
     * ViewProductCellButton constructor
     *
     * @param stage the stage in which the table is placed.
     * @param table the table to which a new person can be added.
     */
    ViewProductCellButton(final Stage stage, final TableView<Product> table) {
        viewDetailsButton.setPrefWidth(100);
        paddedButton.setAlignment(Pos.CENTER);
        paddedButton.getChildren().add(viewDetailsButton);
        viewDetailsButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //  buttonY.set(mouseEvent.getScreenY());
            }
        });
        viewDetailsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                table.getSelectionModel().select(getTableRow().getIndex());
                Product selectedProduct = table.getSelectionModel().getSelectedItem();
                // System.out.println("highlighted row: "+selectedProduct);
                showProductDetailsDialog(selectedProduct);

            }
        });
    }

    /**
     * places an add button in the row only if the row is not empty.
     */
    @Override
    protected void updateItem(Boolean item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            setGraphic(paddedButton);
        } else {
            setGraphic(null);
        }
    }

    private void showProductDetailsDialog(Product product) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mbrinstant/view/product/details.fxml"));

            DetailsController controller = new DetailsController();
            fxmlLoader.setController(controller);
            controller.setProduct(SingletonProductRestClient.getInstance().getProductById(product.getId()));

            Parent root1 = (Parent) fxmlLoader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setTitle("Product Details");
            stage.setResizable(false);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
