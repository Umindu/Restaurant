package Customers.Customer_list_temp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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

    public void setData(Customer_list customerListItem){
        cusName.setText(customerListItem.getCusName());
        cusID.setText(String.valueOf(customerListItem.getCusID()));
        cusEmail.setText(customerListItem.getCusEmail());
        cusPhone.setText(customerListItem.getCusPhone());
        date.setText(customerListItem.getDate());
    }

}
