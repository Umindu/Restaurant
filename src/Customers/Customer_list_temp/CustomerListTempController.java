package Customers.Customer_list_temp;

import Customers.CustomerController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
    private Label cusAddress;

    @FXML
    private Label cusOpenBal;

    // @FXML
    // private HBox hBox;

    //send data to customer details pane
    @FXML
    private static ImageView cimg;
    @FXML
    private static Label cname;
    @FXML
    private static Label cid;
    @FXML
    private static Label cemail;
    @FXML
    private static Label cphone;
    @FXML
    private static Label cAddress;
    @FXML
    private static Label cOpenBal;
    @FXML
    private static Button editBtn;
    @FXML
    private static HBox cHBox;

    public static void GetCustomerDetialsPaneID(ImageView cusImg2, Label cusName2, Label cusID2, Label cusEmail2, Label cusPhone2, Label cusAddress2, Label cusOpenBal2, Button editBtn2, HBox customerDetailsPane){
        cimg = cusImg2;
        cname = cusName2;
        cid = cusID2;
        cemail = cusEmail2;
        cphone = cusPhone2;
        cAddress = cusAddress2;
        cOpenBal= cusOpenBal2;
        editBtn = editBtn2;
        cHBox = customerDetailsPane;
    }


    public void setData(Customer_list customerListItem, HBox box){
        Image image = new Image(getClass().getResourceAsStream("../"+customerListItem.getCusImgUrl()));
        cusImg.setImage(image);
        cusName.setText(customerListItem.getCusName());
        cusID.setText(String.valueOf(customerListItem.getCusID()));
        cusEmail.setText(customerListItem.getCusEmail());
        cusPhone.setText(customerListItem.getCusPhone());
        cusAddress.setText(customerListItem.getCusAddress());
        cusOpenBal.setText(customerListItem.getOpenBal());

        box.setOnMouseClicked(event -> {
            String img = customerListItem.getCusImgUrl();
            String id = customerListItem.getCusID();
            String name = customerListItem.getCusName();
            String email = customerListItem.getCusEmail();
            String phone = customerListItem.getCusPhone();
            String address = customerListItem.getCusAddress();
            String openBal = customerListItem.getOpenBal();

            CustomerController customerController = new CustomerController();
            customerController.CustomerDetailsPaneSet(cimg, cname, cid, cemail, cphone, cAddress, cOpenBal, editBtn, img, name, id, email, phone, address, openBal, cHBox);
            
            // PlacOrderController itemObj = new PlacOrderController();
            // itemObj.setItem(id, name, price, qnt, rightSceneVBox);

        }); 
    }

}
