package Item;

import PlaceOrder.PlacOrderController;
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

    public static void setRightSceneVBox(VBox VBox) {
        rightSceneVBox = VBox;
    }

    public void setData(Item item){
        Image image = new Image(getClass().getResourceAsStream(item.getImg()));
        itemImg.setImage(image);
        itemName.setText(item.getName());
        itemPrice.setText(item.getPrice());

        vbox.setOnMouseClicked(event -> {
            String id = item.getID();
            String name = item.getName();
            String price = item.getPrice();
            String qnt = "5";
            
            PlacOrderController itemObj = new PlacOrderController();
            itemObj.setItem(id, name, price, qnt, rightSceneVBox);

        }); 
    } 
}
