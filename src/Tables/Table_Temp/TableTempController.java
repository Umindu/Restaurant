package Tables.Table_Temp;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.Table_list;

public class TableTempController {
    @FXML
    private Pane clickPane;

    @FXML
    private Pane pane;

    @FXML
    private ImageView tableImg;

    @FXML
    private Label tableNum;

    @FXML
    private Label tableSheetCount;

    public void setData(Table_list table, ArrayList<String> selectTables){
        Image image = new Image(getClass().getResourceAsStream("../"+table.getTableimg()));
        tableImg.setImage(image);
        tableNum.setText(table.getTableName());
        tableSheetCount.setText(table.getTableSheetsCount());

        switch (table.getTablestates()) {
            case "Vacant":
                pane.setStyle("-fx-effect: dropshadow(three-pass-box, #09aa29, 0, 0, 0, 4);");
                this.clickPane.setOnMouseClicked(event -> {
                    if(pane.getStyle() == "-fx-effect: dropshadow(three-pass-box, #09aa29, 0, 0, 0, 4);"){
                        pane.setStyle("-fx-effect: dropshadow(three-pass-box, #09aa29, 4, 4, 0, 0);");
                    }else{
                        pane.setStyle("-fx-effect: dropshadow(three-pass-box, #09aa29, 0, 0, 0, 4);");
                    }
                });
                break;

            case "Occupied":
                pane.setStyle("-fx-effect: dropshadow(three-pass-box, #ff0000, 0, 0, 0, 4);");
                pane.setDisable(true);
                break;

            case "Order on hold":
                pane.setStyle("-fx-effect: dropshadow(three-pass-box, #0067ff, 0, 0, 0, 4);");
                pane.setDisable(true);
                break;

            case "Disabled":
                pane.setStyle("-fx-effect: dropshadow(three-pass-box, #4f4f4f, 0, 0, 0, 4);");
                pane.setDisable(true);
                break;
        
            default:
                break;
        }
        
        if (selectTables.contains(table.getTableID())) {
            this.pane.setStyle("-fx-effect: dropshadow(three-pass-box, #09aa29, 4, 4, 0, 0);");
        }        
    }
}
