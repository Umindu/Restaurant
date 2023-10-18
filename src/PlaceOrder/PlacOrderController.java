package PlaceOrder;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import Item.ItemController;
import PlaceOrder.Coupon.CouponpopupController;
import PlaceOrder.Discount.DiscountpopupController;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Cart_list;

public class PlacOrderController implements Initializable {
    @FXML
    private static BorderPane DashboardBorderdPane;

    public static void setDashboardBorderdPane(BorderPane dashboardBorderdPane) {
        DashboardBorderdPane = dashboardBorderdPane;
    }

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

    private Cart_list item = new Cart_list();

    private static List<Cart_list> cartItemList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ItemController.setRightSceneVBox(rightSceneVBox);
        CartItemController.setRightSceneVBox(rightSceneVBox);
        rightSceneVBox.getChildren().clear();
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

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Discount/Discountpopup.fxml"));  
            AnchorPane popupPane = loader.load();
            DiscountpopupController controller = loader.getController();

            Scene popupScene = new Scene(popupPane);
            popupStage.setScene(popupScene);
            controller.setStage(popupStage);

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
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Coupon/Couponpopup.fxml"));  
            AnchorPane popupPane = loader.load();
            CouponpopupController controller = loader.getController();

            Scene popupScene = new Scene(popupPane);
            popupStage.setScene(popupScene);
            controller.setStage(popupStage);

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
                // FXMLLoader fxmlLoader = new FXMLLoader();
                // fxmlLoader.setLocation(getClass().getResource("Cart_ItemTemp.fxml"));
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

    public void setItem(int id, String name, String price, String qnt, VBox vBox){
        item.setID(id);
        item.setName(name);
        item.setPrice(price);
        item.setQnt(qnt);
        cartItemList.add(item);
        Refresh(vBox);
    }

    public void deleteItem(int id, VBox vbox) {
        for (int i = 0; i < cartItemList.size(); i++) {
            if (cartItemList.get(i).getID() == id) {
                cartItemList.remove(i);
                break; 
            }
        }
        Refresh(vbox);
    }
    
}
