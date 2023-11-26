package Orders.OrderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import DataBase.DBConnect;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OrderDetailsController {

    @FXML
    private Label invoiceID;

    @FXML
    private Label orderCusID;

    @FXML
    private Label orderCusName;

    @FXML
    private Label subTotal;

    @FXML
    private Label serviceCharge;

    @FXML
    private Label discount;

    @FXML
    private Label grandTotal;

    @FXML
    private Label totalPayAmount;

    @FXML
    private Label balance;

    public void showOrderDetails(String invoiceID) {
        try (Statement statement = DBConnect.connectToDB().createStatement()) {
            String query = "SELECT * FROM Order_Invoice WHERE InvoiceID = '" + invoiceID + "'";
            ResultSet resultSet = statement.executeQuery(query);

            //CREATE TABLE Order_Invoice(InvoiceID varchar(15), Date datetime, CustomerID varchar(15), Tables varchar(30), SubTotal float, ServiceCharge float, Discount float, DiscountMethod varchar(5), CoupnCode varchar(15), GrandTotal float, TotalPayAmount float, CashPayAmount float, CardPayAmount float, CardBillNumber varchar(20), QRPayAmount float, Balance float)
            while (resultSet.next()) {
                this.invoiceID.setText("#"+resultSet.getString("InvoiceID"));

                if (resultSet.getString("CustomerID").equals("")) {
                    this.orderCusID.setText("#");
                    this.orderCusName.setText("My Customer");
                }else{
                    this.orderCusID.setText("#"+resultSet.getString("CustomerID"));
                    Statement nameStatement = DBConnect.connectToDB().createStatement();
                    nameStatement.execute("select Name from Customers where ID = '"+resultSet.getString("CustomerID")+"'");
                    ResultSet nameResultSet = nameStatement.getResultSet();
                    nameResultSet.next();
                    this.orderCusName.setText(nameResultSet.getString("Name"));
                }

                this.subTotal.setText(resultSet.getString("SubTotal"));
                this.serviceCharge.setText(resultSet.getString("ServiceCharge"));
                if (resultSet.getString("DiscountMethod").equals("true")) {
                    this.discount.setText(resultSet.getString("Discount")+"%");
                }else{
                    this.discount.setText("Rs. "+resultSet.getString("Discount"));
                }
                this.grandTotal.setText(resultSet.getString("GrandTotal"));
                this.totalPayAmount.setText(resultSet.getString("TotalPayAmount"));
                this.balance.setText(resultSet.getString("Balance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
