package Tables.Table_Temp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.Table_list;

public class TableTempController {

    @FXML
    private Pane pane;

    @FXML
    private ImageView tableImg;

    @FXML
    private Label tableNum;

    public void setData(Table_list table){
        Image image = new Image(getClass().getResourceAsStream(table.getImg()));
        tableImg.setImage(image);
        tableNum.setText(String.valueOf(table.getTableNum()));
        
    }

}
