/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controls;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 *
 * @author maine
 */
public class MyTreeView extends TreeView<String> {

    TreeItem<String> root = new TreeItem("Root");

    public MyTreeView() {
        TreeItem<String> packagingMaterialRoot = new TreeItem("Packaging Material");
        TreeItem<String> rawMaterialRoot = new TreeItem("Raw Material");

        root.getChildren().addAll(rawMaterialRoot, packagingMaterialRoot);
        this.setRoot(root);
        this.setShowRoot(false);

        TreeItem<String> itemReceivePackagingMaterialNode = new TreeItem("Receive");
        TreeItem<String> itemIssuePackagingMaterialNode = new TreeItem("Issue");
        packagingMaterialRoot.getChildren().addAll(itemReceivePackagingMaterialNode, itemIssuePackagingMaterialNode);

        TreeItem<String> itemReceiveRawMaterialNode = new TreeItem("Receive");
        TreeItem<String> itemIssueRawMaterialNode = new TreeItem("Issue");
        rawMaterialRoot.getChildren().addAll(itemReceiveRawMaterialNode, itemIssueRawMaterialNode);

    }
}
