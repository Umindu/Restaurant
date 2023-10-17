package PlaceOrder;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Item.ItemController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.Cart_list;

public class PlacOrderController implements Initializable {
    @FXML
    private VBox rightSceneVBox ;

    private Cart_list item = new Cart_list();

    private static List<Cart_list> cartItemList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ItemController.setRightSceneVBox(rightSceneVBox);
    }


    public void Refresh(VBox vBox) {
        vBox.getChildren().clear();
        try {
            for(int i = 0 ; i < cartItemList.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Cart_ItemTemp.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                CartItemController itemController = fxmlLoader.getController();
                itemController.setData(cartItemList.get(i));

                vBox.getChildren().add(anchorPane);
                VBox.setMargin(anchorPane, new Insets(5));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setItem(String name, String price, String qnt, VBox vBox){
        item.setName(name);
        item.setPrice(price);
        item.setQnt(qnt);
        cartItemList.add(item);
        Refresh(vBox);
    }
}
