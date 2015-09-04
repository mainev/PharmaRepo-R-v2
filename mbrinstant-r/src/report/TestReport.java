/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import mbrinstant.controller.report_generation.mbr_generation.MbrGenerator;
import mbrinstant.entity.mbr.Mbr;
import mbrinstant.rest_client.main.SingletonProductRestClient;
import mbrinstant.rest_client.mbr.SingletonMbrRestClient;
import mbrinstant.rest_client.transaction.SingletonStockCardTxnRestClient;

/**
 *
 * @author maine
 */
public class TestReport extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button print = new Button("Print");
        StackPane root = new StackPane();
        root.getChildren().add(print);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();

        print.setOnAction(e -> {
            try {
                System.out.println("=================TESTING=======================================================");
                SingletonProductRestClient productRestClient = SingletonProductRestClient.getInstance();
                SingletonMbrRestClient batchRestClient = SingletonMbrRestClient.getInstance();
                SingletonStockCardTxnRestClient stkTxnRestClient = SingletonStockCardTxnRestClient.getInstance();
                batchRestClient.setUsernameAndPassword("mainevillarias@gmail.com", "password");
                productRestClient.setUsernameAndPassword("mainevillarias@gmail.com", "password");
                stkTxnRestClient.setUsernameAndPassword("mainevillarias@gmail.com", "password");

                Mbr batch = batchRestClient.getBatchById(164);
                System.out.println("Batch to print: " + batch);
                System.out.println(batch.getProductId().getAreaId().getMainProcedureList());
                // createMbrJasperReport(batch);
                MbrGenerator mbrGenerator = new MbrGenerator(batch);
                mbrGenerator.generateMbr();
                System.out.println("===============================================================================");
            } catch (Exception ex) {
            }

        });

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }

}
