package Customers.Customer_list_temp;

import Customers.CustomerController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.Customer_list;

public class CustomerListTempController {

    @FXML
    private Label cusEmail;

    @FXML
    private Label cusID;

    @FXML
    private ImageView cusImg;

    @FXML
    private Label cusName;

    @FXML
    private Label cusPhone;

    @FXML
    private Label date;

    // @FXML
    // private HBox hBox;

    //send data to customer details pane
    @FXML
    private static Label cname;
    @FXML
    private static Label cid;
    @FXML
    private static Label cemail;
    @FXML
    private static Label cphone;
    @FXML
    private static HBox cHBox;

    public static void GetCustomerDetialsPaneID(Label cusName2, Label cusID2, Label cusEmail2, Label cusPhone2, HBox customerDetailsPane){
        cname = cusName2;
        cid = cusID2;
        cemail = cusEmail2;
        cphone = cusPhone2;
        cHBox = customerDetailsPane;
    }


    public void setData(Customer_list customerListItem, HBox box){
        cusName.setText(customerListItem.getCusName());
        cusID.setText(String.valueOf(customerListItem.getCusID()));
        cusEmail.setText(customerListItem.getCusEmail());
        cusPhone.setText(customerListItem.getCusPhone());
        date.setText(customerListItem.getDate());

        box.setOnMouseClicked(event -> {
            int id = customerListItem.getCusID();
            String name = customerListItem.getCusName();
            String email = customerListItem.getCusEmail();
            String phone = customerListItem.getCusPhone();

            CustomerController customerController = new CustomerController();
            customerController.CustomerDetailsPaneSet(cname, cid, cemail, cphone, name, id, email, phone, cHBox);
            
            // PlacOrderController itemObj = new PlacOrderController();
            // itemObj.setItem(id, name, price, qnt, rightSceneVBox);

        }); 
    }

}
