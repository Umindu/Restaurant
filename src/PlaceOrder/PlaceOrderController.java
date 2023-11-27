package PlaceOrder;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import Customers.CustomerController;
import DataBase.DBConnect;
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
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlaceOrderController implements Initializable {
    @FXML
    private static BorderPane DashboardBorderdPane;

    public static void setDashboardBorderdPane(BorderPane dashboardBorderdPane) {
        DashboardBorderdPane = dashboardBorderdPane;
    }

    @FXML
    private Label invoiceIDLabel;

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

    public static List<Cart_list> cartItemList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ItemController.setRightSceneVBox(rightSceneVBox);
        CartItemController.setRightSceneVBox(rightSceneVBox);
        CustomerController.setPlaceOrderComponent(orderCusPane, orderCusName, orderCusID, addCusBtn);
        // ItemController.setPlaceOrderComponent(orderSubTotal, orderDiscount, orderAmount);
        CustomerController.setOrderDetailsObject(orderDetails);
        TablesController.setOrderDetailsObject(orderDetails);
        rightSceneVBox.getChildren().clear();

        //set invoice id
        setInvoiceID(null);

        if (!cartItemList.isEmpty()) {
            Refresh(rightSceneVBox);
            refrasOrderDetails();
        }
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
    public void RemoveDiscount(ActionEvent event) {
        discountEnableBtn.setVisible(true);
        discountEnableBtn.setPrefWidth(Control.USE_COMPUTED_SIZE);
        discountLine.setVisible(false);
        discountLine.setMinHeight(0);
        orderDetails.setDiscount("0");
        refrasOrderDetails();
    }

    @FXML
    public void RemoveCoupon(ActionEvent event) {
        couponEnableBtn.setVisible(true);
        couponEnableBtn.setPrefWidth(Control.USE_COMPUTED_SIZE);
        couponLine.setVisible(false);
        couponLine.setMinHeight(0);
        orderDetails.setCoupnCode("None");
        refrasOrderDetails();
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

    public void setItem(String id, String name, String cost, String price, String qnt, String discount,  VBox vBox){
        item.setID(id);
        item.setName(name);
        item.setCost(cost);
        item.setPrice(price);
        item.setQnt(qnt);
        item.setDiscount(discount);
        item.setItemeDiscoutntAddPrice(discount, price);
        item.setProductTotalPrice(discount, price, qnt);
        if(cartItemList.isEmpty()){
            cartItemList.add(item);
        }else{
            for (int i = 0; i < cartItemList.size(); i++) {
                if (cartItemList.get(i).getID() == id) {
                    cartItemList.get(i).setQnt(String.valueOf(Float.parseFloat(cartItemList.get(i).getQnt()) + Float.parseFloat(qnt)));
                    cartItemList.get(i).setProductTotalPrice(cartItemList.get(i).getDiscount(), cartItemList.get(i).getPrice(), cartItemList.get(i).getQnt());
                    break; 
                }else if(i == cartItemList.size()-1){
                    cartItemList.add(item);
                    break;
                }
            }
        }
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
        orderDetails.setInvoiceID(invoiceIDLabel.getText().substring(1));

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

        if (orderDetails.getCoupnCode().isEmpty() || orderDetails.getCoupnCode() == "None") {
            orderCouponCode.setText("None");
        }else{
            orderCouponCode.setText("#"+orderDetails.getCoupnCode());
        }
        orderGrandTotal.setText("Rs. " + new BigDecimal(orderDetails.getGrandTotal()).setScale(2, RoundingMode.HALF_UP));
    }

    //Order Hold Button Action
    @FXML
    void OrderHold(ActionEvent event) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(currentDate);

        if (!cartItemList.isEmpty()){
            try (Statement statement = DBConnect.connectToDB().createStatement()) {
                for (Cart_list cart_list : cartItemList) {
                    statement.execute("INSERT INTO Hold_Order_Product VALUES ('"+orderDetails.getInvoiceID()+"', '"+cart_list.getID()+"', '"+cart_list.getName()+"', '"+cart_list.getQnt()+"', '"+cart_list.getCost()+"', '"+cart_list.getPrice()+"', '"+cart_list.getDiscount()+"', '"+cart_list.getProductTotalPrice()+"')");
                }
                statement.execute("INSERT INTO Hold_Order_Invoice VALUES ('"+orderDetails.getInvoiceID()+"', '"+formattedDate+"', '"+orderDetails.getCustomerID()+"', '"+orderDetails.getCustomerName()+"', '"+orderDetails.getTables()+"')");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            cartItemList.clear();
            Refresh(rightSceneVBox);
            refrasOrderDetails();
            setInvoiceID(null);
        }
    }

    //Order Proceed Button Action
    @FXML
    void OrderProceed(ActionEvent event) {
        if (!cartItemList.isEmpty()){
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
                controller.setOrderDetailsObject(orderDetails, cartItemList);

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

    public void refreshPlaceOrder() throws SQLException {
        Refresh(rightSceneVBox);
        refrasOrderDetails();
        setInvoiceID(null);
    }

    //set invoice id
    public void setInvoiceID(String invoiceNum){
        if (invoiceNum == null) {
            try (Statement statement = DBConnect.connectToDB().createStatement()) {
                statement.execute("SELECT MAX(largest_value) AS overall_largest_value FROM (SELECT MAX(COALESCE(InvoiceID, 0)) AS largest_value FROM Order_Invoice UNION ALL SELECT MAX(COALESCE(InvoiceID, 0)) AS largest_value FROM Hold_Order_Invoice) AS combined_results;");
                ResultSet resultSet = statement.getResultSet();
                if (resultSet.next()) {
                    int maxInvoiceID = resultSet.getInt(1);
                    if (resultSet.wasNull()) {
                        invoiceIDLabel.setText("#1");
                    } else {
                        invoiceIDLabel.setText("#"+(maxInvoiceID + 1));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }  
        }else{
            invoiceIDLabel.setText(invoiceNum);
            orderDetails.setInvoiceID(invoiceNum);
        }
        
    }

    public void HoldOrderProcess(String invoiceNum) {
        try (Statement statement = DBConnect.connectToDB().createStatement()) {
            statement.execute("select * from Hold_Order_Product where InvoiceID = '"+invoiceNum+"'");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                PlaceOrderController itemObj = new PlaceOrderController();
                itemObj.setItem(resultSet.getString("Product_ID"), resultSet.getString("ProductName"), resultSet.getString("Cost"), resultSet.getString("Price"), resultSet.getString("Qnt"), resultSet.getString("Discount"), rightSceneVBox);
            }
            refrasOrderDetails();
            setInvoiceID(invoiceNum);
            statement.execute("Delete from Hold_Order_Invoice where InvoiceID = '"+invoiceNum+"'");
            statement.execute("Delete from Hold_Order_Product where InvoiceID = '"+invoiceNum+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}
