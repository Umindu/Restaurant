package Cashier;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import Cashier.model.InvoiceDetails;
import DataBase.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class CashierController implements Initializable {

    @FXML
    private Label DailyCashAmountLabel;

    @FXML
    private Label DailyCreditAmountLabel;

    @FXML
    private Label DailyDifferenceLabel;

    @FXML
    private Label DailyOtherMethodAmountLabel;

    @FXML
    private TextField expectedDrawerAmountTextField;

    @FXML
    private TextField openingDrawerAmountTextField;

    @FXML
    private Button drawerBtn;

    @FXML
    private Button todaySaleBtn;

    @FXML
    private Button saleHistoryBtn;

    @FXML
    private HBox summaryBar;

    @FXML
    private Pane tabOne;

    @FXML
    private VBox tabTwo;

    //today sale tab
    @FXML
    private Label todayCashPaymentAmount;

    @FXML
    private Label todayOpeningDrawerAmount;

    @FXML
    private Label todayOtherPaymentAmount;

    //table
    @FXML
    private TableView<InvoiceDetails> invoiceTable;
    @FXML
    private TableColumn<InvoiceDetails, String> dateColumn;
    @FXML
    private TableColumn<InvoiceDetails, String> invoiceIDColumn;
    @FXML
    private TableColumn<InvoiceDetails, String> cashPaymentColumn;
    @FXML
    private TableColumn<InvoiceDetails, String> otherPaymentColumn;
    @FXML
    private TableColumn<InvoiceDetails, String> totalSaleColumn;
    @FXML
    private TableColumn<InvoiceDetails, String> noteColumn;
    
    private ObservableList<InvoiceDetails> invoiceList = FXCollections.observableArrayList();
    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        LocalDate currentDate = LocalDate.now();
        LocalDate incrementedDate = currentDate.plusDays(1);

        //Check and Insert Daily Summary
        try (Statement statement = DBConnect.connectToDB().createStatement()) {
                statement.execute("Select * FROM DailySummary WHERE Date = '"+currentDate+"'");
                ResultSet resultSet = statement.getResultSet();
                if (resultSet.next()) {
                    openingDrawerAmountTextField.setText(resultSet.getString("OpeningDrawerAmount"));
                    expectedDrawerAmountTextField.setText(resultSet.getString("ExpectedDrawerAmount"));
                }
                else{
                    statement.execute("INSERT INTO DailySummary (Date, OpeningDrawerAmount, ExpectedDrawerAmount) VALUES ('"+currentDate+"', 0, 0)");
                    openingDrawerAmountTextField.setText("0");
                    expectedDrawerAmountTextField.setText("0");
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Get Daily Summary
        try (Statement statement = DBConnect.connectToDB().createStatement()) {
                statement.execute("SELECT SUM(CashPayAmount) AS CashPayAmount, SUM(CardPayAmount) AS CardPayAmount, SUM(QRPayAmount) AS QRPayAmount, SUM(Balance) AS Balance FROM Order_Invoice WHERE Date >= '"+currentDate+" 00:00:00' AND Date < '"+incrementedDate+" 00:00:00'");
                ResultSet resultSet = statement.getResultSet();
                if (resultSet.next()) {
                    DailyCashAmountLabel.setText("Rs. "+resultSet.getString("CashPayAmount"));
                    DailyOtherMethodAmountLabel.setText("Rs. "+resultSet.getFloat("CardPayAmount")+resultSet.getFloat("QRPayAmount")+"");
                    DailyCreditAmountLabel.setText("Rs. "+resultSet.getString("Balance"));
                    DailySummaryDifferenceCalculation();
                }

                } catch (SQLException e) {
            e.printStackTrace();
        }

        //select all text when click
        openingDrawerAmountTextField.setOnMouseClicked(event -> {
            openingDrawerAmountTextField.selectAll();
        });
        expectedDrawerAmountTextField.setOnMouseClicked(event -> {
            expectedDrawerAmountTextField.selectAll();
        });

        //Update Daily Summary Opening Drawer Amount
        openingDrawerAmountTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try (Statement statement = DBConnect.connectToDB().createStatement()) {
                if (openingDrawerAmountTextField.getText().isEmpty()) {
                    statement.execute("UPDATE DailySummary SET OpeningDrawerAmount = '0' WHERE Date = '"+currentDate+"'");
                    openingDrawerAmountTextField.setText("0");
                }else{
                    statement.execute("UPDATE DailySummary SET OpeningDrawerAmount = '"+newValue+"' WHERE Date = '"+currentDate+"'");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //Remove 0 from the beginning
            if (openingDrawerAmountTextField.getText().charAt(0) == '0' && openingDrawerAmountTextField.getText().length() > 1) {
                openingDrawerAmountTextField.setText(openingDrawerAmountTextField.getText().substring(1));
            }

            DailySummaryDifferenceCalculation();
        });

        //Update Daily Summary Expected Drawer Amount
        expectedDrawerAmountTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try (Statement statement = DBConnect.connectToDB().createStatement()) {
                if (expectedDrawerAmountTextField.getText().isEmpty()) {
                    statement.execute("UPDATE DailySummary SET ExpectedDrawerAmount = '0' WHERE Date = '"+currentDate+"'");
                    expectedDrawerAmountTextField.setText("0");
                }else{
                    statement.execute("UPDATE DailySummary SET ExpectedDrawerAmount = '"+newValue+"' WHERE Date = '"+currentDate+"'");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //Remove 0 from the beginning
            if (expectedDrawerAmountTextField.getText().charAt(0) == '0' && expectedDrawerAmountTextField.getText().length() > 1) {
                expectedDrawerAmountTextField.setText(expectedDrawerAmountTextField.getText().substring(1));
            }

            DailySummaryDifferenceCalculation();
        });

        //table view
        dateColumn.setCellValueFactory(new PropertyValueFactory<InvoiceDetails, String>("Date"));
        invoiceIDColumn.setCellValueFactory(new PropertyValueFactory<InvoiceDetails, String>("InvoiceID"));
        cashPaymentColumn.setCellValueFactory(new PropertyValueFactory<InvoiceDetails, String>("CashPayment"));
        otherPaymentColumn.setCellValueFactory(new PropertyValueFactory<InvoiceDetails, String>("OtherPayment"));
        totalSaleColumn.setCellValueFactory(new PropertyValueFactory<InvoiceDetails, String>("TotalSales"));
        noteColumn.setCellValueFactory(new PropertyValueFactory<InvoiceDetails, String>("Note"));

        invoiceTable.setItems(invoiceList);
        InvoiceDetailsloadDataFromDatabase();
    }

    public void ResetButtonStyle(){
        drawerBtn.setStyle("-fx-background-color : #fff; -fx-border-width:0;");
        todaySaleBtn.setStyle("-fx-background-color : #fff; -fx-border-width:0;");
        saleHistoryBtn.setStyle("-fx-background-color : #fff; -fx-border-width:0;");
    }

    @FXML
    void showDrawer(ActionEvent event) {
        tabOne.setVisible(true);
        tabTwo.setVisible(false);

        ResetButtonStyle();
        drawerBtn.setStyle("-fx-background-color : #fff2e8; -fx-border-color : #fc8019;");
    }

    @FXML
    void showTodaySale(ActionEvent event) {
        tabOne.setVisible(false);
        tabTwo.setVisible(true);
        summaryBar.setPrefHeight(130);
        summaryBar.setVisible(true);
        VBox.setMargin(summaryBar, new Insets(20, 0, 0, 0));

        ResetButtonStyle();
        todaySaleBtn.setStyle("-fx-background-color : #fff2e8; -fx-border-color : #fc8019;");
        UpdateData();
    }

    @FXML
    void showSaleHistory(ActionEvent event) {
        tabOne.setVisible(false);
        tabTwo.setVisible(true);
        summaryBar.setPrefHeight(0);
        summaryBar.setVisible(false);
        VBox.setMargin(summaryBar, new Insets(0, 0, 0, 0));

        ResetButtonStyle();
        saleHistoryBtn.setStyle("-fx-background-color : #fff2e8; -fx-border-color : #fc8019;");
    }

    //Daily Summary Difference Calculation
    private void DailySummaryDifferenceCalculation(){
       try (Statement statement = DBConnect.connectToDB().createStatement()) {
            statement.execute("SELECT SUM(CashPayAmount) AS CashPayAmount, SUM(CardPayAmount) AS CardPayAmount, SUM(QRPayAmount) AS QRPayAmount, SUM(Balance) AS Balance FROM Order_Invoice WHERE Date >= '"+LocalDate.now()+" 00:00:00' AND Date < '"+LocalDate.now().plusDays(1)+" 00:00:00'");
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                DailyDifferenceLabel.setText("Rs. "+(Float.parseFloat(expectedDrawerAmountTextField.getText()) - (resultSet.getFloat("CashPayAmount") + Float.parseFloat(openingDrawerAmountTextField.getText()))));
                
                statement.execute("UPDATE DailySummary SET CashPaymentAmount = '"+resultSet.getFloat("CashPayAmount")+"', CreditAmount = '"+resultSet.getFloat("Balance")+"', OtherPaymentsAmount = '"+((resultSet.getFloat("CardPayAmount"))+resultSet.getFloat("QRPayAmount"))+"', Difference = '"+(Float.parseFloat(expectedDrawerAmountTextField.getText()) - (resultSet.getFloat("CashPayAmount") + Float.parseFloat(openingDrawerAmountTextField.getText())))+"'  WHERE Date = '"+LocalDate.now()+"'");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    //Close Drawer
    @FXML
    void CloseDrawer(ActionEvent event) {
        try (Statement statement = DBConnect.connectToDB().createStatement()) {
            statement.execute("UPDATE DailySummary SET States = 'Done' WHERE Date = '"+LocalDate.now()+"'");
            System.out.println("Drawer Closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //today sale tab components
    public void UpdateData(){
        try (Statement statement = DBConnect.connectToDB().createStatement()) {
            statement.execute("SELECT * From DailySummary WHERE Date = '"+LocalDate.now()+"'");
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                todayOpeningDrawerAmount.setText("Rs. "+resultSet.getString("OpeningDrawerAmount"));
                todayCashPaymentAmount.setText("Rs. "+resultSet.getString("CashPaymentAmount"));
                todayOtherPaymentAmount.setText("Rs. "+resultSet.getString("OtherPaymentsAmount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void InvoiceDetailsloadDataFromDatabase(){
        invoiceList.clear();
        try (Statement statement = DBConnect.connectToDB().createStatement()) {
            statement.execute("select * from Order_Invoice");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                String date = resultSet.getString("Date");
                String invoiceID = resultSet.getString("InvoiceID");
                String cashPayment = resultSet.getString("CashPayAmount");
                String otherPayment = String.valueOf(resultSet.getFloat("CardPayAmount") + resultSet.getFloat("QRPayAmount"));
                String totalSale = resultSet.getString("GrandTotal");
                String note = resultSet.getString("Note");

                invoiceList.add(new InvoiceDetails(date, invoiceID, cashPayment, otherPayment, totalSale, note));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
