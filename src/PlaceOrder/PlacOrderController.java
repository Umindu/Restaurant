package PlaceOrder;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.Cart_list;

public class PlacOrderController implements Initializable{
    @FXML
    private GridPane cartGridPane;

    private List<Cart_list> items;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {


        // items list................................................    
        items = new ArrayList<>(items());
       

        try {
            for(int i = 0 ; i < items.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Cart_ItemTemp.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                CartItemController itemController = fxmlLoader.getController();
                itemController.setData(items.get(i));

            

                cartGridPane.add(anchorPane, 0, i);
                GridPane.setMargin(anchorPane, new Insets(5));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    private List<Cart_list> items(){
        List<Cart_list> ls = new ArrayList<>();

        for(int a=0; a<30; a++){
            Cart_list item = new Cart_list();
            item.setName("Rice");
            item.setPrice("Rs. 1400.00");
            item.setQnt("2");
            ls.add(item);
        }


        return ls;
    }

}
