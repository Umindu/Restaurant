package Orders.HoldOrderTemp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import DataBase.DBConnect;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Hold_invoice_list;

public class HoldOrderTempController {

    @FXML
    private Label invoiceID;

    @FXML
    private VBox itemPane;

    @FXML
    private Label cusName;

    @FXML
    private Label date;

    @FXML
    private Label tableNum;

    @FXML
    private Label note;

    public void setData(Hold_invoice_list hold_invoice_list){
        invoiceID.setText("#"+hold_invoice_list.getInvoiceNum());

        if (hold_invoice_list.getCusName().equals("")) {
            cusName.setText("My Customer");
        }else{
            cusName.setText(hold_invoice_list.getCusName());
        }
        if (hold_invoice_list.getTableNum().equals("[]")) {
            tableNum.setText("Take Away");
        }else{
            tableNum.setText(hold_invoice_list.getTableNum().substring(1, hold_invoice_list.getTableNum().length()-1));
        }
        date.setText(hold_invoice_list.getDate());
        setItems(hold_invoice_list.getInvoiceNum());
    }

    private void setItems(String invoiceId){


        try (Statement statement = DBConnect.connectToDB().createStatement()) {
            statement.execute("select * from Hold_Order_Product where InvoiceID = '"+invoiceId+"'");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                HBox pane = new HBox();
                Pane emp = new Pane();
                Label itemName = new Label();
                Label qnt = new Label();

                pane.setPrefHeight(javafx.scene.layout.Region.USE_COMPUTED_SIZE);
                HBox.setHgrow(emp, javafx.scene.layout.Priority.ALWAYS);

                itemName.setText(resultSet.getString("ProductName"));
                qnt.setText(resultSet.getString("Qnt"));
                itemName.setStyle("-fx-text-fill: #000; -fx-font-size:12pt; -fx-font-weight:bold; -fx-padding:5 10 5 0;");
                qnt.setStyle("-fx-text-fill: #000; -fx-font-size:12pt; -fx-font-weight:bold; -fx-padding:5 0 5 0;");

                pane.getChildren().addAll(itemName,emp, qnt);
                itemPane.getChildren().add(pane);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }







        //hold invoice  get Items;
        
        
    }
}
