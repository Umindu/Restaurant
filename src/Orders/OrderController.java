package Orders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Orders.HoldOrderTemp.HoldOrderTempController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Hold_invoice_list;

public class OrderController {

    private List<Hold_invoice_list> invoices;

    @FXML
    private VBox historyPane;

    @FXML
    private HBox holdPane;

    @FXML
    private VBox colOne;

    @FXML
    private VBox colThree;

    @FXML
    private VBox colTwo;

    @FXML
    private static BorderPane DashboardBorderdPane;

    public static void setDashboardBorderdPane(BorderPane borderdPane) {
        DashboardBorderdPane = borderdPane;
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
    }

    private List<Hold_invoice_list> InvoiceList() {
        List<Hold_invoice_list> ls = new ArrayList<>();
        // for(int i =0; i<10; i++){
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

        // }

        return ls;
    }

}
