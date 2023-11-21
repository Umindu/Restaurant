package PlaceOrder.Coupon;

import PlaceOrder.PlaceOrderController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Order_details;

public class CouponpopupController {
    @FXML
    private TextField couponTextField;

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
    void popupClose(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void addCouponCode(ActionEvent event) {
        orderDetails.setCoupnCode(couponTextField.getText());
        placeOrderController.refrasOrderDetails();
        popupClose(event);
    }
}
