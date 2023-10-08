package Home;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class HomeController {

    @FXML
    private HBox categoryHBox;

    @FXML
    public void initialize() {
    String[] array = {"Bus", "Car", "Van", "Bike"};

    for (String labelString : array) {
        Button button = new Button(labelString);
        button.setId(labelString);
        button.setMinWidth(Button.USE_PREF_SIZE);
        button.setPrefWidth(Button.USE_COMPUTED_SIZE); 
        button.setMaxWidth(Double.MAX_VALUE); 
        button.getStyleClass().add("categoryButton");
        categoryHBox.getChildren().add(button);

        button.setOnAction(event -> {
            // Your action for the button here
            String clickedLabel = button.getId();
            System.out.println(clickedLabel + " button clicked!");
        });
    }
    }

    

}
