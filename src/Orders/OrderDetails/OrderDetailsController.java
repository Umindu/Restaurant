package Orders.OrderDetails;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import DataBase.DBConnect;
import Orders.ItemDetailsTemp.ItemDetailsTempController;
import Orders.model.orderItems;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    @FXML
    private VBox vBox;

    private List<orderItems> items;

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

        createItem(invoiceID);
    }


    private void createItem(String invoiceID){ 
        vBox.getChildren().clear();
        items = new ArrayList<>(items(invoiceID));
    
        try {
            for(int i = 0 ; i < items.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ItemDetailsTemp/ItemDetailsTemp.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemDetailsTempController itemDetailsTempController = fxmlLoader.getController();
                itemDetailsTempController.setData(items.get(i));

                vBox.getChildren().add(anchorPane);
                VBox.setMargin(anchorPane, new Insets(5));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // get items from database
    private List<orderItems> items(String invoiceID){
        List<orderItems> ls = new ArrayList<>();
        ResultSet result;
        try  {
            Statement statements = DBConnect.connectToDB().createStatement();
            statements.execute("Select * From Order_Product Where InvoiceID = '"+ invoiceID +"'");
            result = statements.getResultSet();
            
            while (result.next()) {
                orderItems item = new orderItems();
                Statement statement = DBConnect.connectToDB().createStatement();
                statement.execute("Select Name From Products Where ID = '"+ result.getString("Product_ID") +"'");
                ResultSet resultSet = statement.getResultSet();
                resultSet.next();
                item.setItemName(resultSet.getString("Name"));
                item.setItemPrice(result.getString("Price"));
                item.setItemQuantity(result.getString("Qnt").replace(".0", ""));
                item.setItemTotalPrice(result.getString("LastPrice"));
                item.setItemDiscount(result.getString("Discount"));
                ls.add(item);
            }
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ls;
    }
}
