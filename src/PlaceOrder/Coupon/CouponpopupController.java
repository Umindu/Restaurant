package PlaceOrder.Coupon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class CouponpopupController {
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void PopupClose(ActionEvent event) {
        stage.close();
    }
}
