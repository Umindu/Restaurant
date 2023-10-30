package Manage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Manage.Employees.EmployeeController;
import Manage.Products.ProductController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ManageController implements Initializable {

    @FXML
    private Button proMainBtn;

    @FXML
    private Button addProBtn;

    @FXML
    private Button allProBtn;

    @FXML
    private Button categoryBtn;

    @FXML
    private Button emploMainBtn;

    @FXML
    private Button allEmplonBtn;

    @FXML
    private Button addEmplonBtn;

    @FXML
    private Button tableMainBtn;

    @FXML
    private BorderPane borderPane;

    @FXML
    private VBox productVbox;

    @FXML
    private VBox employeeVbox;

    @FXML
    private VBox tableVbox;

    //product window
    @FXML
    private static VBox addProPane;

    @FXML
    private static VBox allProPane;

    @FXML
    private static VBox categpryPane;

    //employee window
    @FXML
    private static VBox allEmployeesPane;

    @FXML
    private static VBox addEmployeesPane;

    public static void setProductsPane(VBox allProPane2, VBox addProPane2, VBox categpryPane2) {
        addProPane = addProPane2;
        allProPane = allProPane2;
        categpryPane = categpryPane2;
    }

    public static void setEmployeesPane(VBox allEmployeesPane2, VBox addEmployeePane2) {
        allEmployeesPane = allEmployeesPane2;
        addEmployeesPane = addEmployeePane2;
    }

    // Reset button style.....................
    public void ResetButtonStyle(){
        proMainBtn.setStyle("-fx-background-color : #fff; -fx-text-fill: #000; -fx-background-radius: 10 10 0 0;");
        addProBtn.setStyle("-fx-background-color : #fff; -fx-text-fill: #000;");
        allProBtn.setStyle("-fx-background-color : #fff; -fx-text-fill: #000;");
        categoryBtn.setStyle("-fx-background-color : #fff; -fx-text-fill: #000;");
        emploMainBtn.setStyle("-fx-background-color : #fff; -fx-text-fill: #000;");
        allEmplonBtn.setStyle("-fx-background-color : #fff; -fx-text-fill: #000;");
        addEmplonBtn.setStyle("-fx-background-color : #fff; -fx-text-fill: #000;");
        tableMainBtn.setStyle("-fx-background-color : #fff; -fx-text-fill: #000;");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("Products/Products.fxml"));
            borderPane.setCenter(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Product tab.....................................
    @FXML
    void showProductVbox(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("Products/Products.fxml"));
        borderPane.setCenter(view);
        if (productVbox.isVisible()) {
            productVbox.setVisible(false);
            productVbox.setPrefHeight(0);
        } else {
            productVbox.setVisible(true);
            productVbox.setPrefHeight(Control.USE_COMPUTED_SIZE);
        }
        ResetButtonStyle();
        proMainBtn.setStyle("-fx-background-color : #fe8b2c !important; -fx-background-radius: 10 10 0 0; -fx-text-fill: #fff;");
    }

    @FXML
    void showAddProduct(ActionEvent event) {
        ProductController pController = new ProductController();
        pController.showAddProPane(allProPane, addProPane, categpryPane);
        ResetButtonStyle();
        addProBtn.setStyle("-fx-background-color : #fe8b2c !important; -fx-text-fill: #fff;");
    }

    @FXML
    void showAllProducts(ActionEvent event) {
        ProductController pController = new ProductController();
        pController.showAllProPane(allProPane, addProPane, categpryPane);
        ResetButtonStyle();
        allProBtn.setStyle("-fx-background-color : #fe8b2c !important; -fx-text-fill: #fff;");
    }

    @FXML
    void showCategory(ActionEvent event) {
        ProductController pController = new ProductController();
        pController.showCategoryPane(allProPane, addProPane, categpryPane);
        ResetButtonStyle();
        categoryBtn.setStyle("-fx-background-color : #fe8b2c !important; -fx-text-fill: #fff;");
    }

    // Employee tab......................................
    @FXML
    void showEmployeeVbox(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("Employees/Employees.fxml"));
        borderPane.setCenter(view);
        if (employeeVbox.isVisible()) {
            employeeVbox.setVisible(false);
            employeeVbox.setPrefHeight(0);
        } else {
            employeeVbox.setVisible(true);
            employeeVbox.setPrefHeight(Control.USE_COMPUTED_SIZE);
        }
        ResetButtonStyle();
        emploMainBtn.setStyle("-fx-background-color : #fe8b2c !important; -fx-text-fill: #fff;");
    }

    @FXML
    void showAllEmployees(ActionEvent event) {
        EmployeeController eController = new EmployeeController();
        eController.showAllEmpPane(allEmployeesPane, addEmployeesPane);
        ResetButtonStyle();
        allEmplonBtn.setStyle("-fx-background-color : #fe8b2c !important; -fx-text-fill: #fff;");
    }

    @FXML
    void showAddEmployees(ActionEvent event) {
        EmployeeController eController = new EmployeeController();
        eController.showAddEmpPane(allEmployeesPane, addEmployeesPane);
        ResetButtonStyle();
        addEmplonBtn.setStyle("-fx-background-color : #fe8b2c !important; -fx-text-fill: #fff;");
    } 


    // Table tab......................................
    @FXML
    void showTableVbox(ActionEvent event) {
        if (tableVbox.isVisible()) {
            tableVbox.setVisible(false);
            tableVbox.setPrefHeight(0);
        } else {
            tableVbox.setVisible(true);
            tableVbox.setPrefHeight(Control.USE_COMPUTED_SIZE);
        }
    }

}
