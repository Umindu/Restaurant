package Customers.Add_customer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.sql.Statement;

import DataBase.DBConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Customer_list;

public class AddCustomerController {

    @FXML
    private ImageView cusImg;

    private String imageUrl;

    @FXML
    private TextField cusAddressTextField;

    @FXML
    private TextField cusEmailTextField;

    @FXML
    private TextField cusIdTextField;

    @FXML
    private TextField cusNameTextField;

    @FXML
    private TextField cusOpenBalTextField;

    @FXML
    private TextField cusPhoneTextField;

    @FXML
    void chooseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Product Image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        File defaultDirectory = new File("src\\customers_img");
        fileChooser.setInitialDirectory(defaultDirectory);
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            String imageName = file.getName();
            String imgCaption = imageName.substring(0, imageName.lastIndexOf("."));
            String imageExtension = imageName.substring(imageName.lastIndexOf("."), imageName.length());
            String newImagePath = "src/customers_img/" + imgCaption + imageExtension;

            imageUrl = "../customers_img/" + imgCaption + imageExtension;
            try {
                Files.copy(file.toPath(), new File(newImagePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image copied successfully");

                cusImg.setImage(new Image(new File(newImagePath).toURI().toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void saveCustomer(ActionEvent event) {
        String cusImg = imageUrl;
        System.out.println(cusImg);
        String cusId = cusIdTextField.getText();
        String cusName = cusNameTextField.getText();
        String cusPhone = cusPhoneTextField.getText();
        String cusEmail = cusEmailTextField.getText();
        String cusAddress = cusAddressTextField.getText();
        String cusOpenBal = cusOpenBalTextField.getText();

        if (!cusId.isEmpty() && !cusName.isEmpty()) {
            //insert customer data to database
            if (cusIdTextField.isEditable()) {
                try {
                    Statement statement = DBConnect.connectToDB().createStatement();
                    statement.execute("insert into Customers values ('" + cusId + "', '" + cusName + "', '" + cusImg
                            + "', '" + cusPhone + "', '" + cusEmail + "', '" + cusAddress + "', '" + cusOpenBal + "')");
                    System.out.println("Customer saved successfully");

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    Statement statement = DBConnect.connectToDB().createStatement();
                    statement.execute("update Customers set Name = '" + cusName + "', ImgUrl = '" + cusImg + "', Phone = '" + cusPhone
                            + "', Email = '" + cusEmail + "', Address = '" + cusAddress + "', OpeningBalance = '" + cusOpenBal
                            +  "'  where ID = '"+cusId+"'");
                    System.out.println("Customer updated successfully");

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        OpoupClose(event);
    }

    public void editCustomer(Customer_list customer_list){
        Image image = new Image(getClass().getResourceAsStream("../"+customer_list.getCusImgUrl()));
        cusImg.setImage(image);
        imageUrl = customer_list.getCusImgUrl();
        cusIdTextField.setText(customer_list.getCusID());
        cusNameTextField.setText(customer_list.getCusName());
        cusPhoneTextField.setText(customer_list.getCusPhone());
        cusEmailTextField.setText(customer_list.getCusEmail());
        cusAddressTextField.setText(customer_list.getCusAddress());
        cusOpenBalTextField.setText(customer_list.getOpenBal());

        cusIdTextField.setEditable(false);
    }



    @FXML
    void OpoupClose(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
