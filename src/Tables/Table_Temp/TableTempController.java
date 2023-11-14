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

    @FXML
    private Label tableSheetCount;

    public void setData(Table_list table){
        Image image = new Image(getClass().getResourceAsStream("../"+table.getTableimg()));
        tableImg.setImage(image);
        tableNum.setText(String.valueOf(table.getTableName()));
        tableSheetCount.setText(table.getTableSheetsCount());
        switch (table.getTablestates()) {
            case "Vacant":
                pane.setStyle("-fx-effect: dropshadow(three-pass-box, #09aa29, 0, 0, 0, 4);");
                break;

            case "Occupied":
                pane.setStyle("-fx-effect: dropshadow(three-pass-box, #ff0000, 0, 0, 0, 4);");
                break;

            case "Order on hold":
                pane.setStyle("-fx-effect: dropshadow(three-pass-box, #0067ff, 0, 0, 0, 4);");
                break;

            case "Disabled":
                pane.setStyle("-fx-effect: dropshadow(three-pass-box, #4f4f4f, 0, 0, 0, 4);");
                break;
        
            default:
                break;
        }
    }

}
