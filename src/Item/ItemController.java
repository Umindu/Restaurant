package Item;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Item;

public class ItemController {
    
    @FXML
    private Label itemPrice;

    @FXML
    private ImageView itemImg;

    @FXML
    private Label itemName;

    public void setData(Item item){
        Image image = new Image(getClass().getResourceAsStream(item.getImg()));
        itemImg.setImage(image);
        itemName.setText(item.getName());
        itemPrice.setText(item.getPrice());
    }

    
}
