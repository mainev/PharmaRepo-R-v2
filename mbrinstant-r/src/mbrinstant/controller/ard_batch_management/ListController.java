/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.ard_batch_management;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import mbrinstant.FXMLLocations;
import mbrinstant.ScreenNavigator;
import mbrinstant.animations.FadeInUpTransition;
import mbrinstant.controls.CustomAlertDialog;
import mbrinstant.controls.MyNotifications;
import mbrinstant.entity.mbr.Mbr;
import mbrinstant.entity.mbr.MbrStatus;
import mbrinstant.exceptions.ServerException;
import mbrinstant.rest_client.mbr.SingletonMbrRestClient;
import mbrinstant.security.SingletonAuthorizationManager;
import mbrinstant.utils.DateConverter;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class ListController implements Initializable {

    @FXML
    private TableView<Mbr> mbrRecordTable;
    @FXML
    private TableColumn<Mbr, String> colProduct;
    @FXML
    private TableColumn<Mbr, String> _colBatchNo;
    @FXML
    private TableColumn<Mbr, String> _colBatchSize;
    @FXML
    private TableColumn<Mbr, Short> _colShelfLife;
    @FXML
    private TableColumn<Mbr, LocalDate> _colMfgDate;
    @FXML
    private TableColumn<Mbr, LocalDate> _colExpDate;
    @FXML
    private TableColumn<Mbr, String> _colPoNo;
    @FXML
    private TableColumn colAction;
    @FXML
    private TableColumn<Mbr, String> _colVrNo;
    @FXML
    private TableColumn<Mbr, String> colStatus;

    //rest client
    private SingletonMbrRestClient mbrRestClient = SingletonMbrRestClient.getInstance();
    private SingletonAuthorizationManager authManager = SingletonAuthorizationManager.getInstance();

    private ObservableList<Mbr> mbrList = FXCollections.observableArrayList();

    //for bottom settings
    @FXML
    private Button settingsButton;
    private PopOver popOver;

    //for search
    @FXML
    private TextField searchTextField;
    @FXML
    private ComboBox categoryBox;

    //parent pane
    @FXML
    private AnchorPane mainPane;

    //method
    private String MAIN_METHOD_NAME = "view_batch_record";
    private String CREATE_NEW_BATCH = "create_new_batch";

    @FXML
    private AnchorPane detailsPane;
    @FXML
    private ProgressIndicator progressIcon;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            if (authManager.isUserPermitted(MAIN_METHOD_NAME)) {
                batchRecordService.start();
                batchRecordService.setOnRunning((event) -> {
                    progressIcon.setVisible(true);
                });
                batchRecordService.setOnSucceeded((event) -> {
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

    private final Service batchRecordService = new Service() {
        @Override
        protected Task createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {

                    configBatchRecordTable();
                    configureSettingsButton();

                    //initialize fields for search operation
                    initSearchCategory();
                    initSearchTextField();

                    mbrList.addListener(new ListChangeListener() {
                        @Override
                        public void onChanged(ListChangeListener.Change c) {
                            refreshTable(mbrRecordTable);
                        }
                    });

                    Thread.sleep(0);
                    return null;
                }
            };
        }
    };

    @FXML
    private void showCreateNewBatchDialog() throws ServerException {
        if (authManager.isUserPermitted(CREATE_NEW_BATCH)) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXMLLocations.NEW_BATCH_DIALOG));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Create New Mbr");
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (IOException e) {
                CustomAlertDialog.showExceptionDialog(e);
            }
        } else {
            CustomAlertDialog.showAccessDeniedDialog();
        }
    }

    private void configureSettingsButton() {
        popOver = createSettingsPopOver();
        popOver.setContentNode(createSettingsPane());
        settingsButton.setOnAction(e -> {
            if (popOver.isShowing()) {
                popOver.hide(Duration.ZERO);
            } else {
                popOver.show(settingsButton);
            }
        });
    }

    private void initSearchCategory() {
        categoryBox.setItems(FXCollections.observableArrayList("Batch No", "Product Code", "Area"));
    }

    private void initSearchTextField() {

        searchTextField.setOnAction(e -> {
            if (!searchTextField.getText().isEmpty() && (categoryBox.getSelectionModel().getSelectedItem() != null)) {
                try {
                    String inputText = searchTextField.getText();
                    String category = (String) categoryBox.getSelectionModel().getSelectedItem();
                    ObservableList<Mbr> resultList = FXCollections.observableArrayList();
                    switch (category) {
                        case "Batch No":
                            resultList = mbrRestClient.getBatchByBatchNo(inputText);
                            break;
                        case "Product Code":
                            resultList = mbrRestClient.getBatchByProductCode(inputText);
                            break;
                        case "Area":
                            resultList = mbrRestClient.getBatchByArea(inputText);
                            break;
                        default:
                            break;
                    }

                    if (!resultList.isEmpty()) {
                        MyNotifications.displayInformation("Found " + resultList.size() + " results.");
                        mbrList.setAll(resultList);
                    } else {
                        MyNotifications.displayInformation("No results found.");

                    }
                } catch (ServerException ex) {
                    Logger.getLogger(ListController.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                //do something...
                MyNotifications.displayError("Search fields not complete.");
            }
        });
    }

    private VBox createSettingsPane() {
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(5, 5, 5, 5));
        vbox.getChildren().add(new Label("Status:"));
        vbox.setStyle("-fx-background-color: lightgray;");
        VBox vbox2 = new VBox();
        vbox2.setAlignment(Pos.CENTER_LEFT);
        vbox2.setSpacing(5);

        CheckComboBox<MbrStatus> checkComboBox2 = new CheckComboBox<>();
        checkComboBox2.getItems().addAll(FXCollections.observableArrayList(MbrStatus.values()));
        checkComboBox2.setPrefWidth(130);
        checkComboBox2.getCheckModel().checkAll();
        checkComboBox2.getCheckModel().getCheckedItems().addListener(new ListChangeListener<MbrStatus>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends MbrStatus> change) {

                while (change.next()) {
                    mbrList.clear();
                    for (MbrStatus s : change.getList()) {
                        try {
                            mbrList.addAll(mbrRestClient.getBatchByStatus(s));

                        } catch (ServerException ex) {
                            Logger.getLogger(ListController.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    refreshTable(mbrRecordTable);
                }
            }
        });
        vbox.getChildren().add(new ToolBar(checkComboBox2));

        vbox.getChildren().add(new Label("Max Record:"));
        vbox.getChildren().add(new TextField());
        return vbox;
    }

    private PopOver createSettingsPopOver() {
        PopOver popOver = new PopOver();
        popOver.setDetachable(false);
        popOver.setArrowLocation(PopOver.ArrowLocation.BOTTOM_RIGHT);
        popOver.setAutoHide(true);
        return popOver;
    }

    private void configBatchRecordTable() throws ServerException {
        mbrList = mbrRestClient.getBatchList();
        mbrRecordTable.setItems(mbrList);
        colProduct.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getProductId().toString()));
        _colBatchNo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getBatchNo()));
        _colBatchSize.setCellValueFactory(c -> new SimpleStringProperty("" + c.getValue().getBatchSize() + " " + c.getValue().getUnitId().getName()));
        _colShelfLife.setCellValueFactory(c -> new SimpleObjectProperty("" + c.getValue().getProductId().getShelfLife()));
        _colMfgDate.setCellValueFactory(c -> new SimpleObjectProperty(DateConverter.convertDateToLocalDate(c.getValue().getMfgDate())));
        _colExpDate.setCellValueFactory(c -> new SimpleObjectProperty(DateConverter.convertDateToLocalDate(c.getValue().getExpDate())));
        _colPoNo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPoNo()));
        _colVrNo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getProductId().getVrNo()));
        colStatus.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getStatus()));

        colAction.setSortable(false);
        colAction.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Mbr, Mbr>, ObservableValue<Mbr>>() {

                    @Override
                    public ObservableValue<Mbr> call(TableColumn.CellDataFeatures<Mbr, Mbr> cp) {
                        return new ReadOnlyObjectWrapper(cp.getValue());
                    }
                });

        colAction.setCellFactory(new Callback<TableColumn<Mbr, Mbr>, TableCell<Mbr, Mbr>>() {
            @Override
            public TableCell<Mbr, Mbr> call(TableColumn<Mbr, Mbr> col) {
                return new ActionCell2();
            }
        });

    }

    public class ActionCell2 extends TableCell<Mbr, Mbr> {

        Button release = new Button("Print/Release");
        Button approve = new Button("Approve");
        HBox releaseHbox = new HBox();
        HBox approveHbox = new HBox();

        public ActionCell2() {
            releaseHbox.setAlignment(Pos.CENTER);
            releaseHbox.getChildren().add(release);
            release.setPrefWidth(100);

            approveHbox.setAlignment(Pos.CENTER);
            approveHbox.getChildren().add(approve);
            approve.setPrefWidth(100);

            release.setOnAction(e -> {
                //show confirmation dialog
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Release");
                alert.setHeaderText("Confirm MBR Release");
                alert.setContentText("Are you sure you want to release this batch?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {

                    mbrRecordTable.getSelectionModel().select(getTableRow().getIndex());
                    Mbr mbr = mbrRecordTable.getSelectionModel().getSelectedItem();

                    try {
                        mbrRestClient.printBatch(mbr);
                    } catch (ServerException ex) {
                        Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    //close the info window
                    Stage stage = (Stage) release.getScene().getWindow();
                    stage.close();
                    ScreenNavigator.loadScreen(FXMLLocations.BATCH_RECORD_SCREEN);

                }

            });

            approve.setOnAction(e -> {
                MyNotifications.displayError("Approve method not yet implemented.");
            });
        }

        @Override
        protected void updateItem(Mbr mbr, boolean empty) {
            super.updateItem(mbr, empty);
            if (mbr != null) {
                if (mbr.getStatus().equals(MbrStatus.RESERVED.toString())) {
                    setGraphic(releaseHbox);
                } else if (mbr.getStatus().equals(MbrStatus.DISPENSED.toString())) {
                    setGraphic(approveHbox);
                }
            } else {
                setGraphic(null);
            }
        }
    }

    /*
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

     */
    public static void refreshTable(TableView tableView) {
        for (int i = 0; i < tableView.getColumns().size(); i++) {
            ((TableColumn) (tableView.getColumns().get(i))).setVisible(false);
            ((TableColumn) (tableView.getColumns().get(i))).setVisible(true);
        }
    }

}