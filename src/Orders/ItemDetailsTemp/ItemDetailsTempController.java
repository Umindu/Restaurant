package Orders.ItemDetailsTemp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import Orders.model.orderItems;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ItemDetailsTempController {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label discount;

    @FXML
    private Label name;

    @FXML
    private Label price;

    @FXML
    private Label productTotalPrice;

    @FXML
    private Label qnt;

    public void setData(orderItems item){
        name.setText(item.getItemName());
        price.setText("Rs. "+ new BigDecimal(item.getItemPrice()).setScale(2, RoundingMode.HALF_UP));
        qnt.setText(item.getItemQuantity());
        productTotalPrice.setText("Rs. "+ new BigDecimal(item.getItemTotalPrice()).setScale(2, RoundingMode.HALF_UP));
        discount.setText(item.getItemDiscount()+"%");
    }
}
