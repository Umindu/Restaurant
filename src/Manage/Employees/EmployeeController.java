package Manage.Employees;

import java.net.URL;
import java.util.ResourceBundle;

import Manage.ManageController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class EmployeeController implements Initializable {
    
    @FXML
    private VBox allEmployeesPane;

    @FXML
    private VBox addEmployeePane;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ManageController.setEmployeesPane(allEmployeesPane, addEmployeePane);
    }

    public void showAllEmpPane(VBox allEmployeesPane2, VBox addEmployeesPane2){
        addEmployeesPane2.setVisible(false);
        allEmployeesPane2.setVisible(true);
    }

    public void showAddEmpPane(VBox allEmployeesPane2, VBox addEmployeesPane2){
        allEmployeesPane2.setVisible(false);
        addEmployeesPane2.setVisible(true);
    }
}
