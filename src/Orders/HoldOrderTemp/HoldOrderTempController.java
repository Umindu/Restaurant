package Orders.HoldOrderTemp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Hold_invoice_list;

public class HoldOrderTempController {

    @FXML
    private VBox itemPane;

    @FXML
    private Label cusName;

    @FXML
    private Label date;

    @FXML
    private Label tableNum;

    @FXML
    private Label time;

    @FXML
    private Label note;

    private int invoiceId;

    public void setData(Hold_invoice_list hold_invoice_list){
        invoiceId = hold_invoice_list.getInvoiceNum();
        cusName.setText(hold_invoice_list.getCusName());
        tableNum.setText(hold_invoice_list.getTableNum());
        date.setText(hold_invoice_list.getDate());
        time.setText(hold_invoice_list.getTime());
        setItems(invoiceId);
    }

    private void setItems(int invoiceId){

        //hold invoice  get Items;
        for(int i =0; i<5; i++){
        HBox pane = new HBox();
        Pane emp = new Pane();
        Label itemName = new Label();
        Label qnt = new Label();

        pane.setPrefHeight(javafx.scene.layout.Region.USE_COMPUTED_SIZE);
        HBox.setHgrow(emp, javafx.scene.layout.Priority.ALWAYS);

        itemName.setText("Rice");
        qnt.setText("1");
        itemName.setStyle("-fx-text-fill: #000; -fx-font-size:12pt; -fx-font-weight:bold; -fx-padding:5 10 5 0;");
        qnt.setStyle("-fx-text-fill: #000; -fx-font-size:12pt; -fx-font-weight:bold; -fx-padding:5 0 5 0;");

        pane.getChildren().addAll(itemName,emp, qnt);
        itemPane.getChildren().add(pane);
        }
    }
}
