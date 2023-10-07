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
    public
    void HomeButtonClick(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("../Home/Home.fxml"));
        borderdPane.setCenter(view);
    }

    @FXML
    void CustomersButtonClick(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("../Customers/Customers.fxml"));
        borderdPane.setCenter(view);
    }


    @FXML
    void CashierButtonClick(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("../Cashier/Cashier.fxml"));
        borderdPane.setCenter(view);
    }


    @FXML
    void OrdersButtonClick(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("../Orders/Orders.fxml"));
        borderdPane.setCenter(view);
    }

    @FXML
    void ReportsButtonClick(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("../Reports/Reports.fxml"));
        borderdPane.setCenter(view);
    }

    @FXML
    void SettingsButtonClick(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("../Settings/Settings.fxml"));
        borderdPane.setCenter(view);
    }

    @FXML
    void TablesButtonClick(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("../Tables/Tables.fxml"));
        borderdPane.setCenter(view);
    }



}
