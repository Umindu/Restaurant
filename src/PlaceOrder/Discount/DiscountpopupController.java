package PlaceOrder.Discount;

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

    @FXML
    void discountAmount(ActionEvent event) {
        amountBtn.setStyle("-fx-background-color: #fc8019;  -fx-text-fill: #fff;");
        percentBtn.setStyle("-fx-background-color: #f5f5f5;  -fx-text-fill: #000;");
        orderDetails.setDiscountMethod(false);
    }

    @FXML
    void discountPercentage(ActionEvent event) {
        percentBtn.setStyle("-fx-background-color: #fc8019;  -fx-text-fill: #fff;");
        amountBtn.setStyle("-fx-background-color: #f5f5f5;  -fx-text-fill: #000;");
        orderDetails.setDiscountMethod(true);
    }

    @FXML
    void popupClose(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void addDiscount(ActionEvent event) {
        orderDetails.setDiscount(dicountTextField.getText());
        popupClose(event);
    }

}
