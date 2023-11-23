package PlaceOrder.Discount;

import PlaceOrder.PlaceOrderController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Order_details;

public class DiscountpopupController {

    @FXML
    private Button amountBtn;

    @FXML
    private Button percentBtn;

    @FXML
    private TextField dicountTextField;

    boolean DiscountPercentage = true;

    Order_details orderDetails;

    public void setObject(Order_details orderDetails){
        this.orderDetails = orderDetails;
    }

    //place order controller
    private static PlaceOrderController placeOrderController;

    public static void setPlacOrderController(PlaceOrderController placeOrderController2) {
        placeOrderController = placeOrderController2;
    }


    @FXML
    void discountAmount(ActionEvent event) {
        DiscountPercentage = false;
        amountBtn.setStyle("-fx-background-color: #fc8019;  -fx-text-fill: #fff;");
        percentBtn.setStyle("-fx-background-color: #f5f5f5;  -fx-text-fill: #000;");
    }

    @FXML
    void discountPercentage(ActionEvent event) {
        DiscountPercentage = true;
        percentBtn.setStyle("-fx-background-color: #fc8019;  -fx-text-fill: #fff;");
        amountBtn.setStyle("-fx-background-color: #f5f5f5;  -fx-text-fill: #000;");
    }

    @FXML
    void popupClose(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
        if (orderDetails.getDiscount().isEmpty() || orderDetails.getDiscount() == "0") {
            placeOrderController.RemoveDiscount(event);
        }
    }

    @FXML
    void addDiscount(ActionEvent event) {
        orderDetails.setDiscountMethod(DiscountPercentage);
        if (dicountTextField.getText().isEmpty()) {
            orderDetails.setDiscount("0");
        }else{
            orderDetails.setDiscount(dicountTextField.getText());
        }
        placeOrderController.refrasOrderDetails();
        popupClose(event);
    }

}
