/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controls;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author maine An implementation of customized table view inside a borderpane.
 */
public class CustomTableView<T> extends BorderPane {

    private final BorderPane topBorder = new BorderPane();
    private final BorderPane bottomBorder = new BorderPane();

    private final HBox topLeftBox = new HBox();
    private final HBox topRightBox = new HBox();

    private final TableView<T> mainTable = new TableView();

    public CustomTableView() {
        super();
        this.setTop(topBorder);
        this.setBottom(bottomBorder);
        this.setCenter(mainTable);

        topBorder.setLeft(topLeftBox);
        topBorder.setRight(topRightBox);

        //topLeftBox layouts
        topLeftBox.setAlignment(Pos.CENTER_LEFT);
        topLeftBox.setSpacing(5);
        topLeftBox.setPadding(new Insets(0, 5, 0, 5));
        topLeftBox.setPrefHeight(40);

        //topRightBox layouts
        topRightBox.setAlignment(Pos.CENTER_RIGHT);
        topRightBox.setSpacing(5);
        topRightBox.setPadding(new Insets(0, 5, 0, 5));
        topRightBox.setPrefHeight(40);

        //adjust node styles
        topBorder.setStyle("-fx-background-color: lightgray;");
        bottomBorder.setStyle("-fx-background-color: lightgray;");
    }

    public void addColumn(TableColumn column) {
        this.mainTable.getColumns().add(column);
    }

    public void addTopLeftNode(Node node) {
        topLeftBox.getChildren().add(node);
    }

    public void addTopRightNode(Node node) {
        topRightBox.getChildren().add(node);
    }

}
