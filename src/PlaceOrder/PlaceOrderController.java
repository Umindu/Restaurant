package PlaceOrder;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import Customers.CustomerController;
import Item.ItemController;
import PlaceOrder.Coupon.CouponpopupController;
import PlaceOrder.Discount.DiscountpopupController;
import Tables.TablesController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Cart_list;
import model.Order_details;
import paymentMethod.paymentMethodController;

public class PlaceOrderController implements Initializable {
    @FXML
    private static BorderPane DashboardBorderdPane;

    public static void setDashboardBorderdPane(BorderPane dashboardBorderdPane) {
        DashboardBorderdPane = dashboardBorderdPane;
    }

    @FXML
    private Pane orderCusPane;

    @FXML
    private Label orderCusID;

    @FXML
    private Label orderCusName;

    @FXML
    private Button addCusBtn;

    @FXML
    private Button addDiscountBtn;

    @FXML
    private Button addCouponBtn;

    @FXML
    private Button couponEnableBtn;

    @FXML
    private Button discountEnableBtn;

    @FXML
    private HBox couponLine;

    @FXML
    private HBox discountLine;

    @FXML
    private VBox rightSceneVBox ;

    //.....................
    @FXML
    private Label orderGrandTotal;

    @FXML
    private Label orderCouponCode;

    @FXML
    private Label orderDiscount;

    @FXML
    private Label orderDiscountMethod;

    @FXML
    private Label orderServiceCahrge;

    @FXML
    private Label orderSubTotal;

    private Cart_list item = new Cart_list();
    //create order details object
    Order_details orderDetails = new Order_details();

    private static List<Cart_list> cartItemList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ItemController.setRightSceneVBox(rightSceneVBox);
        CartItemController.setRightSceneVBox(rightSceneVBox);
        CustomerController.setPlaceOrderComponent(orderCusPane, orderCusName, orderCusID, addCusBtn);
        // ItemController.setPlaceOrderComponent(orderSubTotal, orderDiscount, orderAmount);
        CustomerController.setOrderDetailsObject(orderDetails);
        TablesController.setOrderDetailsObject(orderDetails);
        rightSceneVBox.getChildren().clear();
    }

    public void setCustomer(Pane cusOrderPane, Label cusOrderName, Label cusOrderID, Button cusAddBtn, String id, String name) {
        cusAddBtn.setPrefWidth(0);
        cusAddBtn.setVisible(false);
        cusOrderPane.setVisible(true);
        cusOrderID.setText("#"+String.valueOf(id));
        cusOrderName.setText(name);
    }

    public void removeSetCustomer(Pane cusOrderPane, Button cusAddBtn){
        cusOrderPane.setVisible(false);
        cusAddBtn.setPrefWidth(Control.USE_COMPUTED_SIZE);
        cusAddBtn.setVisible(true);
    }

    @FXML
    void AddCustomer(ActionEvent event) {
        try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("../Customers/Customers.fxml"));
            DashboardBorderdPane.setCenter(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void AddCouponSection(ActionEvent event) {
        couponEnableBtn.setPrefWidth(0);
        couponEnableBtn.setVisible(false);
        couponLine.setVisible(true);
        couponLine.setMinHeight(Control.USE_COMPUTED_SIZE);
        AddCoupon(event);
    }

    @FXML
    void AddDiscountSection(ActionEvent event) {
        discountEnableBtn.setPrefWidth(0);
        discountEnableBtn.setVisible(false);
        discountLine.setVisible(true);
        discountLine.setMinHeight(Control.USE_COMPUTED_SIZE);
        AddDiscount(event);
    }

    @FXML
    void AddDiscount(ActionEvent event) {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initStyle(StageStyle.UNDECORATED);
        popupStage.initStyle(StageStyle.TRANSPARENT);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Discount/Discountpopup.fxml"));  
            AnchorPane popupPane = loader.load();
            DiscountpopupController controller = loader.getController();
            controller.setObject(orderDetails);

            Scene popupScene = new Scene(popupPane);
            popupScene.setFill(Color.TRANSPARENT);
            popupStage.setScene(popupScene);

            //set position
            Node node = addDiscountBtn; 
            Point2D screenCoords = node.localToScreen(-240, -130);
            popupStage.setX(screenCoords.getX());
            popupStage.setY(screenCoords.getY());

            popupStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    @FXML
    void AddCoupon(ActionEvent event) {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initStyle(StageStyle.UNDECORATED);
        popupStage.initStyle(StageStyle.TRANSPARENT);
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Coupon/Couponpopup.fxml"));  
            AnchorPane popupPane = loader.load();
            CouponpopupController controller = loader.getController();
            controller.setObject(orderDetails);

            Scene popupScene = new Scene(popupPane);
            popupScene.setFill(Color.TRANSPARENT);
            popupStage.setScene(popupScene);

            //set position
            Node node = addCouponBtn; 
            Point2D screenCoords = node.localToScreen(-240, -130);
            popupStage.setX(screenCoords.getX());
            popupStage.setY(screenCoords.getY());

            popupStage.show();
        } catch (IOException e) { 
            e.printStackTrace();
            return;
        }
    }

    @FXML
    void RemoveDiscount(ActionEvent event) {
        discountEnableBtn.setVisible(true);
        discountEnableBtn.setPrefWidth(Control.USE_COMPUTED_SIZE);
        discountLine.setVisible(false);
        discountLine.setMinHeight(0);
        orderDetails.setDiscount("0");
        refrasOrderDetails();
    }

    @FXML
    void RemoveCoupon(ActionEvent event) {
        couponEnableBtn.setVisible(true);
        couponEnableBtn.setPrefWidth(Control.USE_COMPUTED_SIZE);
        couponLine.setVisible(false);
        couponLine.setMinHeight(0);
    }


    public void Refresh(VBox vBox) {
        vBox.getChildren().clear();
        try {
            for(int i = 0 ; i < cartItemList.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Cart_ItemTemp.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                CartItemController itemController = fxmlLoader.getController();
                itemController.setData(cartItemList.get(i));

                vBox.getChildren().add(anchorPane);
                VBox.setMargin(anchorPane, new Insets(5));
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setItem(String id, String name, String price, String qnt, String discount, VBox vBox){
        item.setID(id);
        item.setName(name);
        item.setPrice(price);
        item.setQnt(qnt);
        item.setDiscount(discount);
        item.setItemeDiscoutntAddPrice(discount, price);
        item.setProductTotalPrice(discount, price, qnt);
        cartItemList.add(item);
        Refresh(vBox);
    }

    public void addItemQnt(String id, float qnt, VBox vbox) {
        for (int i = 0; i < cartItemList.size(); i++) {
            if (cartItemList.get(i).getID() == id) {
                cartItemList.get(i).setQnt(String.valueOf(qnt));
                break; 
            }
        }
        refrasOrderDetails();
    }

    public void addItemDiscount(String id, float qnt, VBox vbox) {
        for (int i = 0; i < cartItemList.size(); i++) {
            if (cartItemList.get(i).getID() == id) {
                cartItemList.get(i).setDiscount(String.valueOf(qnt));
                break; 
            }
        }
        refrasOrderDetails();
    }

    public void deleteItem(String id, VBox vbox) {
        for (int i = 0; i < cartItemList.size(); i++) {
            if (cartItemList.get(i).getID() == id) {
                cartItemList.remove(i);
                break; 
            }
        }
        Refresh(vbox);
        refrasOrderDetails();
        
    }

    public void refrasOrderDetails(){
        float subTotal = 0;
        for(Cart_list item:cartItemList){
            subTotal += Float.parseFloat(item.getProductTotalPrice());
        }
        orderDetails.setSubTotal(String.valueOf(subTotal));
        orderSubTotal.setText("Rs. " + new BigDecimal(orderDetails.getSubTotal()).setScale(2, RoundingMode.HALF_UP));

        if ((orderDetails.getDiscountMethod())) {
            orderDiscount.setText("Rs. " + new BigDecimal((Float.parseFloat(orderDetails.getSubTotal()) * Float.parseFloat(orderDetails.getDiscount()) / 100)).setScale(2, RoundingMode.HALF_UP));
            orderDetails.setGrandTotal(String.valueOf(Float.parseFloat(orderDetails.getSubTotal()) - (Float.parseFloat(orderDetails.getSubTotal()) * Float.parseFloat(orderDetails.getDiscount()) / 100)));
        }else{
            orderDiscount.setText("Rs. " + new BigDecimal(Float.parseFloat(orderDetails.getDiscount())).setScale(2, RoundingMode.HALF_UP));
            orderDetails.setGrandTotal(String.valueOf(Float.parseFloat(orderDetails.getSubTotal()) - Float.parseFloat(orderDetails.getDiscount())));
        }
        orderDiscountMethod.setText(orderDetails.getDiscountMethod() ? "%" : "Rs.");
        orderDiscount.setText(orderDetails.getDiscount());
        orderCouponCode.setText("#"+orderDetails.getCoupnCode());
        orderGrandTotal.setText("Rs. " + new BigDecimal(orderDetails.getGrandTotal()).setScale(2, RoundingMode.HALF_UP));
    }

    //Order Hold Button Action
    @FXML
    void OrderHold(ActionEvent event) {
    }

    //Order Proceed Button Action
    @FXML
    void OrderProceed(ActionEvent event) {
        Stage addCustomerStage = new Stage();
        addCustomerStage.initModality(Modality.APPLICATION_MODAL);
        addCustomerStage.initStyle(StageStyle.UNDECORATED);
        addCustomerStage.initStyle(StageStyle.TRANSPARENT);


        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();
        addCustomerStage.setWidth(screenWidth);
        addCustomerStage.setHeight(screenHeight);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../paymentMethod/paymentMethod.fxml"));  
            HBox popupPane = loader.load();
            paymentMethodController controller = loader.getController();
            controller.setOrderDetailsObject(orderDetails);

            Scene popupScene = new Scene(popupPane);
            popupScene.setFill(Color.TRANSPARENT);
            addCustomerStage.setScene(popupScene);
            addCustomerStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

}
