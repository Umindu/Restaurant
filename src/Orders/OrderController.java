package Orders;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import DataBase.DBConnect;
import Orders.HoldOrderTemp.HoldOrderTempController;
import Orders.OrderDetails.OrderDetailsController;
import Orders.model.orders;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.Hold_invoice_list;

public class OrderController implements Initializable {

    @FXML
    private TableView<orders> ordersTableView;

    @FXML
    private TableColumn<orders, String> date;

    @FXML
    private TableColumn<orders, String> orderID;

    @FXML
    private TableColumn<orders, String> totalSales;

    private ObservableList<orders> ordersList = FXCollections.observableArrayList();

    private List<Hold_invoice_list> invoices;

    @FXML
    private Button orderHistoryBtn;

    @FXML
    private Button orderHoldBtn;

    @FXML
    private VBox historyPane;

    @FXML
    private ScrollPane holdPane;

    @FXML
    private VBox colOne;

    @FXML
    private VBox colThree;

    @FXML
    private VBox colTwo;

    @FXML
    private static BorderPane DashboardBorderdPane;

    private static OrderDetailsController orderDetailsController;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        orderID.setCellValueFactory(new PropertyValueFactory<orders, String>("OrderID"));
        date.setCellValueFactory(new PropertyValueFactory<orders, String>("Date"));
        totalSales.setCellValueFactory(new PropertyValueFactory<orders, String>("TotalSales"));

        ordersTableView.setItems(ordersList);
        OrderloadDataFromDatabase();
    }

    public static void setOrderDetailsController(OrderDetailsController orderDetailsController2) {
        orderDetailsController = orderDetailsController2;
    }

    public void OrderloadDataFromDatabase(){
        ordersList.clear();
        try {
            Statement statement = DBConnect.connectToDB().createStatement();
            statement.execute("select * from Order_Invoice");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                String orderId = resultSet.getString("InvoiceID");
                String date = resultSet.getString("Date");
                String totalSales = resultSet.getString("GrandTotal");

                ordersList.add(new orders(orderId, date, totalSales));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setDashboardBorderdPane(BorderPane borderdPane) {
        DashboardBorderdPane = borderdPane;
    }

    public void ResetButtonStyle(){
        orderHistoryBtn.setStyle("-fx-background-color : #fff; -fx-border-width:0;");
        orderHoldBtn.setStyle("-fx-background-color : #fff; -fx-border-width:0;");
    }

    @FXML
    void showOrderHistory(ActionEvent event) {
        holdPane.setVisible(false);
        historyPane.setVisible(true);
        try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("OrderDetails/OrderDetails.fxml"));
            DashboardBorderdPane.setRight(view);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ResetButtonStyle();
        orderHistoryBtn.setStyle("-fx-background-color : #fff2e8; -fx-border-color : #fc8019;");
    }

    @FXML
    void showHoldOrders(ActionEvent event) {
        historyPane.setVisible(false);
        holdPane.setVisible(true);
        DashboardBorderdPane.setRight(null);

        colOne.getChildren().clear();
        colTwo.getChildren().clear();
        colThree.getChildren().clear();

        invoices = new ArrayList<>(InvoiceList());

        try {
            for (int i = 0; i < invoices.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("HoldOrderTemp/HoldOrderTemp.fxml"));
                VBox box = fxmlLoader.load();
                HoldOrderTempController holdOrderTempController = fxmlLoader.getController();
                holdOrderTempController.setData(invoices.get(i));

                if (i % 3 == 0) {
                    colOne.getChildren().add(box);
                } else if (i % 3 == 1) {
                    colTwo.getChildren().add(box);
                } else if (i % 3 == 2) {
                    colThree.getChildren().add(box);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ResetButtonStyle();
        orderHoldBtn.setStyle("-fx-background-color : #fff2e8; -fx-border-color : #fc8019;");
    }

    private List<Hold_invoice_list> InvoiceList() {
        List<Hold_invoice_list> ls = new ArrayList<>();

        Hold_invoice_list invoice = new Hold_invoice_list();
        invoice.setInvoiceNum(1);
        invoice.setCusName("Dilesh");
        invoice.setTableNum("1");
        invoice.setDate("2021-05-20");
        invoice.setTime("12:00");
        ls.add(invoice);

        Hold_invoice_list invoice2 = new Hold_invoice_list();
        invoice2.setInvoiceNum(2);
        invoice2.setCusName("Ganesh");
        invoice2.setTableNum("4");
        invoice2.setDate("2021-05-20");
        invoice2.setTime("01:00");
        ls.add(invoice2);

        return ls;
    }

    @FXML
    void showOrderDetails(MouseEvent event) {
        String id = ordersTableView.getSelectionModel().getSelectedItem().getOrderID();
        orderDetailsController.showOrderDetails(id);
    }
}
