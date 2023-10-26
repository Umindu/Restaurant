package Manage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.layout.VBox;

public class ManageController {

    @FXML
    private VBox productVbox;

    @FXML
    private VBox employeeVbox;

    @FXML
    private VBox tableVbox;

    @FXML
    void showProductVbox(ActionEvent event) {
        if (productVbox.isVisible()) {
            productVbox.setVisible(false);
            productVbox.setPrefHeight(0);
        } else {
            productVbox.setVisible(true);
            productVbox.setPrefHeight(Control.USE_COMPUTED_SIZE);
        }
    }

    @FXML
    void showEmployeeVbox(ActionEvent event) {
        if (employeeVbox.isVisible()) {
            employeeVbox.setVisible(false);
            employeeVbox.setPrefHeight(0);
        } else {
            employeeVbox.setVisible(true);
            employeeVbox.setPrefHeight(Control.USE_COMPUTED_SIZE);
        }
    }

    @FXML
    void showTableVbox(ActionEvent event) {
        if (tableVbox.isVisible()) {
            tableVbox.setVisible(false);
            tableVbox.setPrefHeight(0);
        } else {
            tableVbox.setVisible(true);
            tableVbox.setPrefHeight(Control.USE_COMPUTED_SIZE);
        }
    }
}
