package Cashier;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class CashierController {

    @FXML
    private Button drawerBtn;

    @FXML
    private Button todaySaleBtn;

    @FXML
    private Button saleHistoryBtn;

    @FXML
    private HBox summaryBar;

    @FXML
    private Pane tabOne;

    @FXML
    private VBox tabTwo;

    public void ResetButtonStyle(){
        drawerBtn.setStyle("-fx-background-color : #fff; -fx-border-width:0;");
        todaySaleBtn.setStyle("-fx-background-color : #fff; -fx-border-width:0;");
        saleHistoryBtn.setStyle("-fx-background-color : #fff; -fx-border-width:0;");
    }

    @FXML
    void showDrawer(ActionEvent event) {
        tabOne.setVisible(true);
        tabTwo.setVisible(false);

        ResetButtonStyle();
        drawerBtn.setStyle("-fx-background-color : #fff2e8; -fx-border-color : #fc8019;");
    }

    @FXML
    void showTodaySale(ActionEvent event) {
        tabOne.setVisible(false);
        tabTwo.setVisible(true);
        summaryBar.setPrefHeight(130);
        summaryBar.setVisible(true);
        VBox.setMargin(summaryBar, new Insets(20, 0, 0, 0));

        ResetButtonStyle();
        todaySaleBtn.setStyle("-fx-background-color : #fff2e8; -fx-border-color : #fc8019;");
    }

    @FXML
    void showSaleHistory(ActionEvent event) {
        tabOne.setVisible(false);
        tabTwo.setVisible(true);
        summaryBar.setPrefHeight(0);
        summaryBar.setVisible(false);
        VBox.setMargin(summaryBar, new Insets(0, 0, 0, 0));

        ResetButtonStyle();
        saleHistoryBtn.setStyle("-fx-background-color : #fff2e8; -fx-border-color : #fc8019;");
    }
}
