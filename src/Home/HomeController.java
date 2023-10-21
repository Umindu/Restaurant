package Home;

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
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Item;

public class HomeController implements Initializable{

    @FXML
    private HBox categoryHBox;

    @FXML
    private GridPane itemGridPane;

    @FXML
    private ScrollPane itemScrollPane;

    private List<Item> items;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        String[] array = {"Bus", "Car", "Van", "Bike"};

        for (String labelString : array) {
            Button button = new Button(labelString);
            button.setId(labelString);
            button.setMinWidth(Button.USE_PREF_SIZE);
            button.setPrefWidth(Button.USE_COMPUTED_SIZE); 
            button.setMaxWidth(Double.MAX_VALUE); 
            button.getStyleClass().add("categoryButton");
            button.setCursor(Cursor.HAND);
            categoryHBox.getChildren().add(button);

            button.setOnAction(event -> {
                // Your action for the button here
                String clickedLabel = button.getId();
                System.out.println(clickedLabel + " button clicked!");
            });
        }

        // items list................................................    
        items = new ArrayList<>(items());
        int column = 0;
        int row = 1;

        try {
            for(int i = 0 ; i < items.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../Item/ItemTemp.fxml"));
                VBox box = fxmlLoader.load();
                ItemController itemController = fxmlLoader.getController();
                itemController.setData(items.get(i));

                if(column == 6){
                    column=0;
                    ++row;
                }

                itemGridPane.add(box, column++, row);
                GridPane.setMargin(box, new Insets(-30, 10, 50, 10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set up responsive constraints for the GridPane
        ColumnConstraints colConstraints = new ColumnConstraints();
        colConstraints.setPercentWidth(20);

        
        for (int i = 0; i < 6; i++) {
            itemGridPane.getColumnConstraints().add(colConstraints);
        }
    }


    private List<Item> items(){
        
        List<Item> ls = new ArrayList<>();
        
        Item item = new Item();
        item.setID(0);
        item.setImg("../items_img/Chicken-Biryani.png");
        item.setName("Rice");
        item.setPrice("Rs. 1400.00");
        ls.add(item);

        Item item2 = new Item();
        item2.setID(1);
        item2.setImg("../items_img/Chicken-Biryani.png");
        item2.setName("Rolls");
        item2.setPrice("Rs. 100.00");
        ls.add(item2);
        
  
        return ls;
    }

}
