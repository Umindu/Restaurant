package PlaceOrder;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Cart_list;

public class CartItemController {

    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private Label name;

    @FXML
    private Label price;

    @FXML
    private Label qnt;

    public void setData(Cart_list Cart_list_item){
        name.setText(Cart_list_item.getName());
        price.setText(Cart_list_item.getPrice());
        qnt.setText(Cart_list_item.getQnt());
    }
}
