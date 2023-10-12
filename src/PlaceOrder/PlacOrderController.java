package PlaceOrder;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.Cart_list;

public class PlacOrderController {
    @FXML
    private  GridPane cartGridPane;

    public  void Refresh() {
        String name = "rice";
        String price = "12";
        String qnt = "5";
        
        Cart_list item = new Cart_list();
        item.setName(name);
        item.setPrice(price);
        item.setQnt(qnt);
        
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Cart_ItemTemp.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            CartItemController itemController = fxmlLoader.getController();
            itemController.setData(item);

            cartGridPane.add(anchorPane, 0, cartGridPane.getChildren().size());
            GridPane.setMargin(anchorPane, new Insets(5));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
