package Customers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import Customers.Customer_list_temp.CustomerListTempController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Customer_list;

public class CustomerController implements Initializable {

    @FXML
    private Label CusPhone;

    @FXML
    private Label cusEmail;

    @FXML
    private Label cusID;

    @FXML
    private Label cusName;

    @FXML
    private HBox customerDetailsPane;

    @FXML
    private ScrollPane customerScrollPane;

    @FXML
    private VBox vBox;

    private List<Customer_list> customers;

    @FXML
    void AddNewCustomer(ActionEvent event) {

    }

    @FXML
    void CustomerDelete(ActionEvent event) {

    }

    @FXML
    void CustomerEdit(ActionEvent event) {

    }

    @FXML
    void RemoveCustomer(ActionEvent event) {

    }

     @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        customers = new ArrayList<>(CustomerList());

        try {
            for(int i = 0 ; i < customers.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Customer_list_temp/CustomerListTemp.fxml"));
                HBox box = fxmlLoader.load();
                CustomerListTempController customerListTempController = fxmlLoader.getController();
                customerListTempController.setData(customers.get(i));

                vBox.getChildren().add(box);
                VBox.setMargin(box, new Insets(5));
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Customer_list> CustomerList(){
        
        List<Customer_list> ls = new ArrayList<>();
        for(int i = 0 ; i < 20; i++){
        Customer_list customer = new Customer_list();
        customer.setCusID(1);
        customer.setCusName("Dilesh");
        customer.setCusEmail("dilesh@gmail.com");
        customer.setCusPhone("077335467");
        customer.setDate("2023/05/11");
        ls.add(customer);

        Customer_list customer2 = new Customer_list();
        customer2.setCusID(2);
        customer2.setCusName("Ganesh");
        customer2.setCusEmail("ganesh@gmail.com");
        customer2.setCusPhone("0773365467");
        customer2.setDate("2023/12/23");
        ls.add(customer2);
        }
        return ls;
    }

   
}