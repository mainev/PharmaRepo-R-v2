/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbr.client.controller;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mbr.client.ScreenNavigator;
import mbr.client.controls.TextFieldWithSearch;
import mbr.client.entity.main.PackagingMaterial;
import mbr.client.entity.main.Product;
import mbr.client.entity.main.Unit;
import mbr.client.entity.mbr.CompoundingProcedure;
import mbr.client.entity.mbr.Dosage;
import mbr.client.entity.mbr.Mbr;
import mbr.client.entity.mbr.PackagingMaterialRequirement;
import mbr.client.entity.mbr.RawMaterialRequirement;
import mbr.client.service.main.ProductService;
import mbr.client.service.main.UnitService;
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
public class NewController implements Initializable {

    @FXML
    GridPane gridPane;
    @FXML
    ChoiceBox<Unit> unitsChoiceBox;
    @FXML
    TextField batchSizeTextField;
    @FXML
    DatePicker mfgDateDatePicker;
    @FXML
    TextField poNoTextField;

    @FXML
    Button addPrintButton;
    @FXML
    Button cancelButton;

    TextFieldWithSearch<Product> textFieldWithProductSearch;
    MbrService mbrService = new MbrService();
    ProductService productService = new ProductService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTextFieldWithProductSearch();
        setUnits();

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleCancelButton();
            }
        });

        addPrintButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleAddPrintButton();
            }
        });
    }

    /**
     * *
     * Assign product items that is searchable in the text field.
     */
    private void initTextFieldWithProductSearch() {

        ObservableList<Product> productList = new ProductService().getProductList();
        textFieldWithProductSearch = new TextFieldWithSearch(productList);
        textFieldWithProductSearch.setAlignment(Pos.CENTER);
        gridPane.add(textFieldWithProductSearch, 1, 0);

//        textFieldWithProductSearch.listViewSelectedItemProperty().addListener((ob, ov, nv) -> {
//            if (nv != null) {
//            }
//
//        });
    }

    private void setUnits() {
        unitsChoiceBox.setItems(new UnitService().getUnitList());
    }

    private void handleCancelButton() {
        Stage stage = (Stage) gridPane.getScene().getWindow();
        stage.close();

    }

    private void handleAddPrintButton() {
        createMbrJasperReport(createMbr());
        Stage stage = (Stage) gridPane.getScene().getWindow();
        stage.close();
        ScreenNavigator.loadScreen(ScreenNavigator.MBR_LIST_SCREEN);
    }

    private Mbr createMbr() {
        Product productId = textFieldWithProductSearch.getSelectedItem();
        Double batchSize = Double.parseDouble(batchSizeTextField.getText());
        Unit unitId = unitsChoiceBox.getValue();
        Date mfgDate = DateConverter.convertLocalDateToDate(mfgDateDatePicker.getValue());
        Date expDate = DateConverter.convertLocalDateToDate(mfgDateDatePicker.getValue().plusYears(productId.getShelfLife()));
        String poNo = poNoTextField.getText();

        return mbrService.createMbr(productId, batchSize, mfgDate, expDate, poNo, unitId);
    }
    
    private void createMbrJasperReport(Mbr mbr) {
        System.out.println(mbr);
        UDFCalculator udfCalculator = new UDFCalculator();
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
