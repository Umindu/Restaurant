package Manage.Employees;

import java.net.URL;
import java.util.ResourceBundle;
import DataBase.DBConnect;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.io.IOException;
import Manage.ManageController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class EmployeeController implements Initializable {
    
    @FXML
    private VBox allEmployeesPane;

    @FXML
    private VBox addEmployeePane;

    //add employee components
    @FXML
    private TextField employeeAddressTextField;

    @FXML
    private TextArea employeeDescriptionTextField;

    @FXML
    private TextField employeeEmailTextField;

    @FXML
    private TextField employeeIDTextField;

    private ObservableList<String> jobRoleList = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> employeeJobRoleCombo;

    @FXML
    private TextField employeeNameTextField;

    @FXML
    private TextField employeePhoneTextField;

    @FXML
    private ImageView addProImg;

    private String imageUrl;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ManageController.setEmployeesPane(allEmployeesPane, addEmployeePane);

        //Job role list
        jobRoleList.add("Select Job Role");
        jobRoleList.add("Manager");
        jobRoleList.add("Salesman");
        jobRoleList.add("Cashier");
        jobRoleList.add("Stock Keeper");

        //Job Role Combo Box
        employeeJobRoleCombo.setItems(jobRoleList);
        employeeJobRoleCombo.getSelectionModel().selectFirst();
    }

    public void showAllEmpPane(VBox allEmployeesPane2, VBox addEmployeesPane2){
        addEmployeesPane2.setVisible(false);
        allEmployeesPane2.setVisible(true);
    }

    public void showAddEmpPane(VBox allEmployeesPane2, VBox addEmployeesPane2){
        allEmployeesPane2.setVisible(false);
        addEmployeesPane2.setVisible(true);
    }

    // choose product image 
    @FXML
    void chooseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Product Image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        File defaultDirectory = new File("src\\employees_img");
        fileChooser.setInitialDirectory(defaultDirectory);
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            String imageName = file.getName();
            String imgCaption = imageName.substring(0, imageName.lastIndexOf("."));
            String imageExtension = imageName.substring(imageName.lastIndexOf("."), imageName.length());
            String newImagePath = "src/employees_img/" + imgCaption + imageExtension;

            imageUrl = "../employees_img/" + imgCaption + imageExtension;
            try {
                Files.copy(file.toPath(), new File(newImagePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image copied successfully");

                addProImg.setImage(new Image(new File(newImagePath).toURI().toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // product cancel button functions
    @FXML
    void cancelEmployee(ActionEvent event) {
        Image image = new Image(getClass().getResourceAsStream("../../employees_img/Temp.png"));
        addProImg.setImage(image);
        employeeIDTextField.clear();
        employeeNameTextField.clear();
        employeeEmailTextField.clear();
        employeePhoneTextField.clear();
        employeeAddressTextField.clear();
        employeeDescriptionTextField.clear();
        employeeJobRoleCombo.getSelectionModel().selectFirst();
    }

    // product save button functions
    @FXML
    void saveEmployee(ActionEvent event) {
        String empImg = imageUrl;
        String empId = employeeIDTextField.getText();
        String empName = employeeNameTextField.getText();
        String empEmail = employeeEmailTextField.getText();
        String empPhone = employeePhoneTextField.getText();
        String empAddress = employeeAddressTextField.getText();
        String empDescription = employeeDescriptionTextField.getText();
        String empJobRole = employeeJobRoleCombo.getValue();

        if (empId.isEmpty() || empName.isEmpty() || empJobRole.isEmpty()) {
            System.out.println("Please fill all the fields");
        } else {
            try (Statement statement = DBConnect.connectToDB().createStatement()) {
                statement.execute("INSERT INTO Employees VALUES('" + empId + "', '" + empName + "', '" + empImg + "', '" + empJobRole + "', '" + empEmail + "', '" + empPhone + "', '" + empAddress + "', '" + empDescription + "')");
                System.out.println("Employee added successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            cancelEmployee(null);
        }
    }
}
