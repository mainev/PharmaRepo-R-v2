/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbr.client.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mbr.client.entity.main.PackagingMaterial;
import mbr.client.entity.mbr.CompoundingProcedure;
import mbr.client.entity.mbr.Dosage;
import mbr.client.entity.mbr.Mbr;
import mbr.client.entity.mbr.PackagingMaterialRequirement;
import mbr.client.entity.mbr.RawMaterialRequirement;
import mbr.client.service.main.ProductService;
import mbr.client.service.mbr.MbrService;
import mbr.client.utils.DateConverter;
import mbr.client.utils.UDFCalculator;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class ListController implements Initializable {

    @FXML
    HBox _headerHBox;

    @FXML
    TableView<Mbr> _tableViewMbrList;
    @FXML
    TableColumn<Mbr, String> _colProductCode;
    @FXML
    TableColumn<Mbr, String> _colProductBrandName;
    @FXML
    TableColumn<Mbr, String> _colBatchNo;
    @FXML
    TableColumn<Mbr, String> _colBatchSize;
    @FXML
    TableColumn<Mbr, Short> _colShelfLife;
    @FXML
    TableColumn<Mbr, LocalDate> _colMfgDate;
    @FXML
    TableColumn<Mbr, LocalDate> _colExpDate;
    @FXML
    TableColumn<Mbr, String> _colPoNo;
    @FXML
    TableColumn<Mbr, String> _colPackSize;
    @FXML
    TableColumn<Mbr, String> _colVrNo;

    @FXML
    Button newButton;
    @FXML
    Button printButton;

    MbrService mbrService = new MbrService();
    ProductService productService = new ProductService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initMbrListTableView();
        printButton.setDisable(true);

        newButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleNewButton();
            }
        });

        printButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createMbrJasperReport();
            }
        });
        
        _tableViewMbrList.getSelectionModel().selectedItemProperty().addListener((ob, ov, nv)->{
            if(nv != null){
                printButton.setDisable(false);
            }
                
        });

    }

    private void initMbrListTableView() {
        ObservableList<Mbr> mbrList = mbrService.getMbrList();
        _tableViewMbrList.setItems(mbrList);

        _colProductCode.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getProductId().getCode()));
        _colProductBrandName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getProductId().getBrandName()));
        _colBatchNo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getBatchNo()));
        _colBatchSize.setCellValueFactory(c -> new SimpleStringProperty("" + c.getValue().getBatchSize() + " " + c.getValue().getUnitId().getName()));
        _colShelfLife.setCellValueFactory(c -> new SimpleObjectProperty("" + c.getValue().getProductId().getShelfLife()));
        _colMfgDate.setCellValueFactory(c -> new SimpleObjectProperty(DateConverter.convertDateToLocalDate(c.getValue().getMfgDate())));
        _colExpDate.setCellValueFactory(c -> new SimpleObjectProperty(DateConverter.convertDateToLocalDate(c.getValue().getExpDate())));
        _colPoNo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPoNo()));
        _colPackSize
                .setCellValueFactory(
                        c -> new SimpleStringProperty("" + c.getValue().getProductId().getPackSizeId().getQuantity() + " "
                                + c.getValue().getProductId().getPackSizeId().getUnitId().getName() + " per "
                                + c.getValue().getProductId().getPackSizeId().getContainerId().getName())
                );
        _colVrNo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getProductId().getVrNo()));

    }

    private void handleNewButton() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mbr/client/view/new.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Create New Mbr");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void createMbrJasperReport() {

        UDFCalculator udfCalculator = new UDFCalculator();
        Mbr mbr = _tableViewMbrList.getSelectionModel().getSelectedItem();
        List<RawMaterialRequirement> rmReqCollection = mbr.getProductId().getActiveUdf().getRawMaterialRequirementList();
        List<PackagingMaterialRequirement> pmReqCollection = mbr.getProductId().getActiveUdf().getPackagingMaterialRequirementList();

        udfCalculator.calculateRawMaterialBatchReq(mbr);
        udfCalculator.calculatePackMatBatchReq(mbr);
        
        for (CompoundingProcedure cp : mbr.getProductId().getActiveManufacturingProcedure().getCompoundingProcedureList()) {
            for (Dosage d : cp.getDosageList()) {
                for (RawMaterialRequirement rmr : rmReqCollection) {
                    if (rmr.getId().equals(d.getRawMaterialRequirementId().getId())) {
                        d.setRawMaterialRequirementId(rmr);
                    }
                }
            }
        }

        //this part gets the bottle and cbox used in the product to be displayed in report
        PackagingMaterial bottleContainer = productService.getPrimaryPackaging(mbr.getProductId());
        PackagingMaterial cBoxContainer = productService.getSecondaryPackaging(mbr.getProductId());
        double noOfBottles = mbrService.getPrimaryPackagingQuantity(pmReqCollection, bottleContainer.getId());
        int noOfCBox = mbrService.getSecondaryPackagingQuantity(pmReqCollection, cBoxContainer.getId());
        Integer[] primaryPackagingNoOfRows = new Integer[getNumberOfReportRows(noOfBottles / 1.02)];
        Integer[] secondaryPackagingNoOfRows = new Integer[noOfCBox];

        try {
            Map<String, Object> params = new HashMap();
            params.put("mbr", mbr);
            params.put("bottles", noOfBottles);
            params.put("rows", primaryPackagingNoOfRows);
            params.put("cbox_rows", secondaryPackagingNoOfRows);
            JasperPrint jasperPrint = JasperFillManager.fillReport("reports/mbr/TABLET HUMAN/mbr_template.jasper",
                    params, new JREmptyDataSource());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

  
    private int getNumberOfReportRows(double size) {
        int rows = 0;
        int item = 0;
        int counter = 0;
        while (item < size) {
            counter += 200;
            rows = counter / 5;
            item = item + 200;
        }
        return rows;
    }

}
