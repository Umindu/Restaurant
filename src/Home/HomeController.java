package Home;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class HomeController {

    @FXML
    private HBox categoryHBox;

    @FXML
    public void initialize() {
        Button busLabel = new Button("Bus");
        Button carLabel = new Button("Car");
        Button vanLabel = new Button("Van");
    
        // Apply the "label" style to each label
        busLabel.getStyleClass().add("label");
        carLabel.getStyleClass().add("label");
        vanLabel.getStyleClass().add("label");
    
        categoryHBox.getChildren().addAll(busLabel, carLabel, vanLabel);
    }

}
