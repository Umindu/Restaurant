package Dashboard;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class DashboardController {

    @FXML
    private AnchorPane aaa;

    @FXML
    private BorderPane borderdPane;

    @FXML
    private Button cashierBtn;

    @FXML
    private Button customerBtn;

    @FXML
    private Button homeBtn;

    @FXML
    private Button orderBtn;

    @FXML
    private Button reportBtn;

    @FXML
    private Button settingsBtn;

    @FXML
    private Button tableBtn;

    @FXML
    void ResetButtonStyle(){
        homeBtn.setStyle("-fx-background-color : #fff; -fx-border-width:0;");
        customerBtn.setStyle("-fx-background-color : #fff; -fx-border-width:0;");
        tableBtn.setStyle("-fx-background-color : #fff; -fx-border-width:0;");
        cashierBtn.setStyle("-fx-background-color : #fff; -fx-border-width:0;");
        orderBtn.setStyle("-fx-background-color : #fff; -fx-border-width:0;");
        reportBtn.setStyle("-fx-background-color : #fff; -fx-border-width:0;");
        settingsBtn.setStyle("-fx-background-color : #fff; -fx-border-width:0;");
    }

    @FXML
    public void HomeButtonClick(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("../Home/Home.fxml"));
        borderdPane.setCenter(view);
        AnchorPane sideview = FXMLLoader.load(getClass().getResource("../PlaceOrder/place_order.fxml"));
        borderdPane.setRight(sideview);
        ResetButtonStyle();
        homeBtn.setStyle("-fx-background-color : #fff2e8; -fx-border-color : #fc8019;");
    }

    @FXML
    void CustomersButtonClick(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("../Customers/Customers.fxml"));
        borderdPane.setCenter(view);
        ResetButtonStyle();
        customerBtn.setStyle("-fx-background-color : #fff2e8; -fx-border-color : #fc8019;");
    }

    @FXML
    void TablesButtonClick(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("../Tables/Tables.fxml"));
        borderdPane.setCenter(view);
        ResetButtonStyle();
        tableBtn.setStyle("-fx-background-color : #fff2e8; -fx-border-color : #fc8019;");
    }

    @FXML
    void CashierButtonClick(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("../Cashier/Cashier.fxml"));
        borderdPane.setCenter(view);
        ResetButtonStyle();
        cashierBtn.setStyle("-fx-background-color : #fff2e8; -fx-border-color : #fc8019;");
        borderdPane.setRight(null);
    }

    @FXML
    void OrdersButtonClick(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("../Orders/Orders.fxml"));
        borderdPane.setCenter(view);
        ResetButtonStyle();
        orderBtn.setStyle("-fx-background-color : #fff2e8; -fx-border-color : #fc8019;");
    }

    @FXML
    void ReportsButtonClick(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("../Reports/Reports.fxml"));
        borderdPane.setCenter(view);
        ResetButtonStyle();
        reportBtn.setStyle("-fx-background-color : #fff2e8; -fx-border-color : #fc8019;");
        borderdPane.setRight(null);
    }

    @FXML
    void SettingsButtonClick(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("../Settings/Settings.fxml"));
        borderdPane.setCenter(view);
        ResetButtonStyle();
        settingsBtn.setStyle("-fx-background-color : #fff2e8; -fx-border-color : #fc8019;");
        borderdPane.setRight(null);
    }
    


}
