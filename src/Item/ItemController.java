package Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import PlaceOrder.PlaceOrderController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import model.Item;

public class ItemController {
    @FXML
    private Label itemPrice;

    @FXML
    private ImageView itemImg;

    @FXML
    private Label itemName;

    @FXML
    private VBox vbox;

    @FXML
    private static VBox rightSceneVBox ;

    private String discount;

    //place order controller
    private static PlaceOrderController placeOrderController;


    public static void setRightSceneVBox(VBox VBox) {
        rightSceneVBox = VBox;
    }

    public static void setPlacOrderController(PlaceOrderController placeOrderController2) {
        placeOrderController = placeOrderController2;
    }

    public void setData(Item item){
        Image image = new Image(getClass().getResourceAsStream(item.getImg()));
        itemImg.setImage(image);
        itemName.setText(item.getName());
        itemPrice.setText("Rs. "+ new BigDecimal(item.getPrice()).setScale(2, RoundingMode.HALF_UP));
        discount = item.getDiscount();

        vbox.setOnMouseClicked(event -> {
            String id = item.getID();
            String name = item.getName();
            String price = item.getPrice();
            String qnt = "1";
            
            PlaceOrderController itemObj = new PlaceOrderController();
            itemObj.setItem(id, name, price, qnt, discount, rightSceneVBox);

            // itemObj.refrasOrderDetails(orderSubTotal, orderDiscount, orderAmount);
            // placeOrderController.setItem(id, name, price, qnt, discount, rightSceneVBox);
            placeOrderController.refrasOrderDetails();  
        }); 
    } 
}
