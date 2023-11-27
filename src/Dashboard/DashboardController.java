package Dashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Item.ItemController;
import Orders.OrderController;
import Orders.OrderDetails.OrderDetailsController;
import PlaceOrder.CartItemController;
import PlaceOrder.PlaceOrderController;
import PlaceOrder.Coupon.CouponpopupController;
import PlaceOrder.Discount.DiscountpopupController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import paymentMethod.paymentMethodController;

public class DashboardController implements Initializable{

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
    private Button manageBtn;

    @FXML
    private Button settingsBtn;

    @FXML
    private Button tableBtn;

    private boolean checkVisibleRightPane = false;

    private DashboardController dashboardController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PlaceOrderController.setDashboardBorderdPane(borderdPane);
        OrderController.setDashboardBorderdPane(borderdPane);
    }

    public void ResetButtonStyle(){
        homeBtn.setStyle("-fx-background-color : #fff; -fx-border-width:0;");
        customerBtn.setStyle("-fx-background-color : #fff; -fx-border-width:0;");
        tableBtn.setStyle("-fx-background-color : #fff; -fx-border-width:0;");
        cashierBtn.setStyle("-fx-background-color : #fff; -fx-border-width:0;");
        orderBtn.setStyle("-fx-background-color : #fff; -fx-border-width:0;");
        reportBtn.setStyle("-fx-background-color : #fff; -fx-border-width:0;");
        manageBtn.setStyle("-fx-background-color : #fff; -fx-border-width:0;");
        settingsBtn.setStyle("-fx-background-color : #fff; -fx-border-width:0;");
    }

    public void setDashboardBorderController(DashboardController dashboardController2){
        dashboardController = dashboardController2;
    }


    @FXML
    public void HomeButtonClick(ActionEvent event) {
        try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("../Home/Home.fxml"));
            borderdPane.setCenter(view);

        // right pane
        if (checkVisibleRightPane == false) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../PlaceOrder/place_order.fxml"));
            AnchorPane sideview = loader.load();
            PlaceOrderController placeOrderController = loader.getController();
            //item controller set place order controller object 
            ItemController.setPlacOrderController(placeOrderController);
            CartItemController.setPlacOrderController(placeOrderController);
            DiscountpopupController.setPlacOrderController(placeOrderController);
            CouponpopupController.setPlacOrderController(placeOrderController);
            paymentMethodController.setPlacOrderController(placeOrderController);
            
            borderdPane.setRight(sideview);
            checkVisibleRightPane = true;
        } 
        
        ResetButtonStyle();
        homeBtn.setStyle("-fx-background-color : #fff2e8; -fx-border-color : #fc8019;");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public void HomeButtonClick(ActionEvent event, String invoiceNum) {
        try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("../Home/Home.fxml"));
            borderdPane.setCenter(view);

        // right pane
        if (checkVisibleRightPane == false) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../PlaceOrder/place_order.fxml"));
            AnchorPane sideview = loader.load();
            PlaceOrderController placeOrderController = loader.getController();
            //item controller set place order controller object 
            ItemController.setPlacOrderController(placeOrderController);
            CartItemController.setPlacOrderController(placeOrderController);
            DiscountpopupController.setPlacOrderController(placeOrderController);
            CouponpopupController.setPlacOrderController(placeOrderController);
            paymentMethodController.setPlacOrderController(placeOrderController);

            placeOrderController.HoldOrderProcess(invoiceNum);
            
            borderdPane.setRight(sideview);
            checkVisibleRightPane = true;
        } 
        
        ResetButtonStyle();
        homeBtn.setStyle("-fx-background-color : #fff2e8; -fx-border-color : #fc8019;");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }



    @FXML
    public void CustomersButtonClick(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("../Customers/Customers.fxml"));
        borderdPane.setCenter(view);

        // right pane
        if (checkVisibleRightPane == false) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../PlaceOrder/place_order.fxml"));
            AnchorPane sideview = loader.load();
            PlaceOrderController placeOrderController = loader.getController();
            //item controller set place order controller object 
            ItemController.setPlacOrderController(placeOrderController);
            CartItemController.setPlacOrderController(placeOrderController);
            DiscountpopupController.setPlacOrderController(placeOrderController);
            borderdPane.setRight(sideview);
            checkVisibleRightPane = true;
        } 

        ResetButtonStyle();  
        customerBtn.setStyle("-fx-background-color : #fff2e8; -fx-border-color : #fc8019;");      
    }

    @FXML
    void TablesButtonClick(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("../Tables/Tables.fxml"));
        borderdPane.setCenter(view);

        // right pane
        if (checkVisibleRightPane == false) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../PlaceOrder/place_order.fxml"));
            AnchorPane sideview = loader.load();
            PlaceOrderController placeOrderController = loader.getController();
            //item controller set place order controller object 
            ItemController.setPlacOrderController(placeOrderController);
            CartItemController.setPlacOrderController(placeOrderController);
            DiscountpopupController.setPlacOrderController(placeOrderController);
            borderdPane.setRight(sideview);
            checkVisibleRightPane = true;
        }  

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
        checkVisibleRightPane = false;
    }

    @FXML
    void OrdersButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loaderView = new FXMLLoader(getClass().getResource("../Orders/Orders.fxml"));
        AnchorPane view = loaderView.load();
        borderdPane.setCenter(view);
        OrderController orderController = loaderView.getController();
        ResetButtonStyle();
        orderBtn.setStyle("-fx-background-color : #fff2e8; -fx-border-color : #fc8019;");
        borderdPane.setRight(null);

        FXMLLoader loaderSideView = new FXMLLoader(getClass().getResource("../Orders/OrderDetails/OrderDetails.fxml"));
        AnchorPane sideview = loaderSideView.load();
        borderdPane.setRight(sideview);
        OrderDetailsController orderDetailsController = loaderSideView.getController();
        OrderController.setController(orderDetailsController, orderController, dashboardController);
        
        checkVisibleRightPane = false;
    }

    @FXML
    void ReportsButtonClick(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("../Reports/Reports.fxml"));
        borderdPane.setCenter(view);
        ResetButtonStyle();
        reportBtn.setStyle("-fx-background-color : #fff2e8; -fx-border-color : #fc8019;");
        borderdPane.setRight(null);
        checkVisibleRightPane = false;
    }

    @FXML
    void ManageButtonClick(ActionEvent event) throws IOException {
        BorderPane view = FXMLLoader.load(getClass().getResource("../Manage/Manage.fxml"));
        borderdPane.setCenter(view);
        ResetButtonStyle();
        manageBtn.setStyle("-fx-background-color : #fff2e8; -fx-border-color : #fc8019;");
        borderdPane.setRight(null);
        checkVisibleRightPane = false;
    }

    @FXML
    void SettingsButtonClick(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("../Settings/Settings.fxml"));
        borderdPane.setCenter(view);
        ResetButtonStyle();
        settingsBtn.setStyle("-fx-background-color : #fff2e8; -fx-border-color : #fc8019;");
        borderdPane.setRight(null);
        checkVisibleRightPane = false;
    }

    public void HoldOrderProcess(String invoiceNum) {
        HomeButtonClick(null, invoiceNum);
    }
    


}
