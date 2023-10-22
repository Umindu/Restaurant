package Cashier;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CashierController {

    @FXML
    private HBox summaryBar;

    @FXML
    private VBox tabOne;

    @FXML
    private VBox tabTwo;

    @FXML
    void showTabOne(ActionEvent event) {
        tabOne.setVisible(false);
        tabTwo.setVisible(true);
    }

    @FXML
    void showTodaySale(ActionEvent event) {
        tabOne.setVisible(true);
        tabTwo.setVisible(false);
        summaryBar.setPrefHeight(130);
        summaryBar.setVisible(true);
        VBox.setMargin(summaryBar, new Insets(20, 0, 0, 0));
    }

    @FXML
    void showSaleHistory(ActionEvent event) {
        tabOne.setVisible(true);
        tabTwo.setVisible(false);
        summaryBar.setPrefHeight(0);
        summaryBar.setVisible(false);
        VBox.setMargin(summaryBar, new Insets(0, 0, 0, 0));
    }
}
