package Item;

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

    public void setData(Item item){
        Image image = new Image(getClass().getResourceAsStream(item.getImg()));
        itemImg.setImage(image);
        itemName.setText(item.getName());
        itemPrice.setText(item.getPrice());

        vbox.setOnMouseClicked(event -> {
            // Handle the click event here
            System.out.println("VBox clicked for " + item.getName());

            // PlacOrderController itemAdd = new PlacOrderController();
            // itemAdd.AddItemCart(item.getName(), item.getPrice());
        });
    }

    

    
}
