package Customers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import Customers.Add_customer.AddCustomerController;
import Customers.Customer_list_temp.CustomerListTempController;
import DataBase.DBConnect;
import PlaceOrder.PlaceOrderController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Customer_list;
import model.Order_details;

public class CustomerController implements Initializable {

    @FXML
    private Label cusPhone;

    @FXML
    private Label cusEmail;

    @FXML
    private ImageView cusImg;

    @FXML
    private Label cusID;

    @FXML
    private Label cusName;

    @FXML
    private Label cusAddress;

    @FXML
    private Label cusOpenBal;

    @FXML
    private HBox customerDetailsPane;

    @FXML
    private Button editBtn;

    @FXML
    private VBox vBox;
    //order details object
    private static Order_details orderDetails;

    //Place order compenent variables
    @FXML
    private static Pane cusOrderPane; 
    @FXML
    private static Label cusOrderName; 
    @FXML
    private static Label cusOrderID; 
    @FXML
    private static Button cusAddBtn;


    private List<Customer_list> customers;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        CustomerListTempController.GetCustomerDetialsPaneID(cusImg, cusName, cusID, cusEmail, cusPhone, cusAddress, cusOpenBal, editBtn, customerDetailsPane);
        if (orderDetails.getCustomerID() != null) {
            System.out.println(orderDetails.getCustomerID());
            allReadyCustomerDetailsLoad();
        }
        createCustomerList();
    }

    public static void setOrderDetailsObject(Order_details orderDetails2) {
        orderDetails = orderDetails2;
    }

    private void createCustomerList(){
        vBox.getChildren().clear();
        customers = new ArrayList<>(CustomerList());
        try {
            for(int i = 0 ; i < customers.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Customer_list_temp/CustomerListTemp.fxml"));
                HBox box = fxmlLoader.load();
                CustomerListTempController customerListTempController = fxmlLoader.getController();
                customerListTempController.setData(customers.get(i), box);

                vBox.getChildren().add(box);
                VBox.setMargin(box, new Insets(2));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Customer_list> CustomerList(){
        List<Customer_list> ls = new ArrayList<>();

        try {
            Statement statement = DBConnect.connectToDB().createStatement();
            statement.execute("select * from Customers");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                String imgUrl = resultSet.getString("ImgUrl");
                String id = resultSet.getString("ID");
                String name = resultSet.getString("Name");
                String email = resultSet.getString("Email");
                String phone = resultSet.getString("Phone");
                String address = resultSet.getString("Address");
                String openBal = resultSet.getString("OpeningBalance");

                ls.add(new Customer_list(imgUrl, id, name, email, phone, address, openBal));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ls;
    }

    @FXML
    void AddNewCustomer(ActionEvent event) {
        Stage addCustomerStage = new Stage();
        addCustomerStage.initModality(Modality.APPLICATION_MODAL);
        addCustomerStage.initStyle(StageStyle.UNDECORATED);
        addCustomerStage.initStyle(StageStyle.TRANSPARENT);


        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();
        addCustomerStage.setWidth(screenWidth);
        addCustomerStage.setHeight(screenHeight);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Add_customer/Add_customer.fxml"));  
            HBox popupPane = loader.load();

            Scene popupScene = new Scene(popupPane);
            popupScene.setFill(Color.TRANSPARENT);
            addCustomerStage.setScene(popupScene);
            addCustomerStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }


    public void CustomerEdit(Customer_list Customer_list) {
        Stage addCustomerStage = new Stage();
        addCustomerStage.initModality(Modality.APPLICATION_MODAL);
        addCustomerStage.initStyle(StageStyle.UNDECORATED);
        addCustomerStage.initStyle(StageStyle.TRANSPARENT);


        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();
        addCustomerStage.setWidth(screenWidth);
        addCustomerStage.setHeight(screenHeight);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Add_customer/Add_customer.fxml"));  
            HBox popupPane = loader.load();
            AddCustomerController addCustomerController = loader.getController();
            addCustomerController.editCustomer(Customer_list);
            Scene popupScene = new Scene(popupPane);
            popupScene.setFill(Color.TRANSPARENT);
            addCustomerStage.setScene(popupScene);
            addCustomerStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    @FXML
    void RemoveCustomer(ActionEvent event) {
        customerDetailsPane.setVisible(false);
        customerDetailsPane.setPrefHeight(0);
        VBox.setMargin(customerDetailsPane, new Insets(0, 10, 0, 10));

        orderDetails.setCustomerID(null); 

        PlaceOrderController orderCustomer = new PlaceOrderController();
        orderCustomer.removeSetCustomer(cusOrderPane,cusAddBtn);
    }

    public static void setPlaceOrderComponent(Pane orderCusPane, Label orderCusName, Label orderCusID, Button addCusBtn){
        cusOrderPane = orderCusPane;
        cusOrderName = orderCusName;
        cusOrderID = orderCusID;
        cusAddBtn = addCusBtn;
    }

    public void allReadyCustomerDetailsLoad(){
        customerDetailsPane.setVisible(true);
        customerDetailsPane.setPrefHeight(170);
        VBox.setMargin(customerDetailsPane, new Insets(30, 10, 0, 10));


        try {
            Statement statement = DBConnect.connectToDB().createStatement();
            statement.execute("select * from Customers where ID = '"+orderDetails.getCustomerID()+"'");
            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            
            Image image = new Image(getClass().getResourceAsStream(resultSet.getString("ImgUrl")));
            cusImg.setImage(image);
            cusName.setText(resultSet.getString("Name"));
            cusID.setText(resultSet.getString("ID"));
            cusEmail.setText(resultSet.getString("Email"));
            cusPhone.setText(resultSet.getString("Phone"));
            cusAddress.setText(resultSet.getString("Address"));
            cusOpenBal.setText(resultSet.getString("OpeningBalance"));

            editBtn.setOnAction(e -> {
                try {
                    CustomerEdit(new Customer_list(resultSet.getString("ImgUrl"), resultSet.getString("ID"), resultSet.getString("Name"), resultSet.getString("Email"), resultSet.getString("Phone"), resultSet.getString("Address"), resultSet.getString("OpeningBalance")));
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void CustomerDetailsPaneSet(ImageView cimg, Label cname, Label cid, Label cemail, Label cphone, Label cAddress, Label cOpenBal, Button editBtn2, String img, String name, String id, String email, String phone, String address, String openBal, HBox customerDetailsPane){
        customerDetailsPane.setVisible(true);
        customerDetailsPane.setPrefHeight(170);
        VBox.setMargin(customerDetailsPane, new Insets(30, 10, 0, 10));
        Image image = new Image(getClass().getResourceAsStream(img));
        cimg.setImage(image);
        cname.setText(name);
        cid.setText("#"+id);
        cemail.setText(email);
        cphone.setText(phone);
        cAddress.setText(address);
        cOpenBal.setText(openBal);

        editBtn2.setOnAction(e -> {
            CustomerEdit(new Customer_list(img, id, name, email, phone, address, openBal));
        });

        PlaceOrderController orderCustomer = new PlaceOrderController();
        orderCustomer.setCustomer(cusOrderPane, cusOrderName, cusOrderID, cusAddBtn, id, name);
        orderDetails.setCustomerID(id);
    }

    

   
}