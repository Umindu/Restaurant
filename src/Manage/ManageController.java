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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ManageController implements Initializable {

    @FXML
    private Button proMainBtn;

    @FXML
    private ImageView proMainBtnImg;

    @FXML
    private Button addProBtn;

    @FXML
    private Button allProBtn;

    @FXML
    private Button categoryBtn;

    @FXML
    private Button emploMainBtn;

    @FXML
    private ImageView emploMainBtnImg;

    @FXML
    private Button allEmplonBtn;

    @FXML
    private Button addEmplonBtn;

    @FXML
    private Button tableMainBtn;

    @FXML
    private ImageView tableMainBtnImg;

    @FXML
    private BorderPane borderPane;

    @FXML
    private VBox productVbox;

    @FXML
    private VBox employeeVbox;

    @FXML
    private VBox tableVbox;

    //main windows variable
    @FXML
    private AnchorPane productView;
    @FXML
    private AnchorPane employeeView;
    @FXML
    private AnchorPane tableView;

    //product windows
    @FXML
    private static VBox addProPane;

    @FXML
    private static VBox allProPane;

    @FXML
    private static VBox categpryPane;

    //employee windows
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
        addProBtn.setStyle("-fx-background-color : #fff; -fx-text-fill: #000;");
        allProBtn.setStyle("-fx-background-color : #fff; -fx-text-fill: #000;");
        categoryBtn.setStyle("-fx-background-color : #fff; -fx-text-fill: #000;");
        allEmplonBtn.setStyle("-fx-background-color : #fff; -fx-text-fill: #000;");
        addEmplonBtn.setStyle("-fx-background-color : #fff; -fx-text-fill: #000;");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            productView = FXMLLoader.load(getClass().getResource("Products/Products.fxml"));
            borderPane.setCenter(productView);

            //all product button styles
            proMainBtnImg.setRotate(90);
            productVbox.setVisible(true);
            productVbox.setPrefHeight(Control.USE_COMPUTED_SIZE);
            allProBtn.setStyle("-fx-background-color : #fe8b2c !important; -fx-text-fill: #fff;");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Product tab.....................................
    @FXML
    void showProductVbox(ActionEvent event) throws IOException {
        if (productVbox.isVisible()) {
            proMainBtnImg.setRotate(0);
            productVbox.setVisible(false);
            productVbox.setPrefHeight(0);
        } else {
            proMainBtnImg.setRotate(90);
            productVbox.setVisible(true);
            productVbox.setPrefHeight(Control.USE_COMPUTED_SIZE);
        }
    }

    @FXML
    void showAddProduct(ActionEvent event) {
        if (borderPane.getCenter() != productView) {
            borderPane.setCenter(productView);
        }

        ProductController pController = new ProductController();
        pController.showAddProPane(allProPane, addProPane, categpryPane);
        ResetButtonStyle();
        addProBtn.setStyle("-fx-background-color : #fe8b2c !important; -fx-text-fill: #fff;");
    }

    @FXML
    void showAllProducts(ActionEvent event) {
        if (borderPane.getCenter() != productView) {
            borderPane.setCenter(productView);
        }

        ProductController pController = new ProductController();
        pController.showAllProPane(allProPane, addProPane, categpryPane);
        ResetButtonStyle();
        allProBtn.setStyle("-fx-background-color : #fe8b2c !important; -fx-text-fill: #fff;");
    }

    @FXML
    void showCategory(ActionEvent event) {
        if (borderPane.getCenter() != productView) {
            borderPane.setCenter(productView);
        }

        ProductController pController = new ProductController();
        pController.showCategoryPane(allProPane, addProPane, categpryPane);
        ResetButtonStyle();
        categoryBtn.setStyle("-fx-background-color : #fe8b2c !important; -fx-text-fill: #fff;");
    }

    // Employee tab......................................
    @FXML
    void showEmployeeVbox(ActionEvent event) throws IOException {
        if (employeeView == null) {
        employeeView = FXMLLoader.load(getClass().getResource("Employees/Employees.fxml"));
        System.out.println(employeeView);
        }
        
        if (employeeVbox.isVisible()) {
            emploMainBtnImg.setRotate(0);
            employeeVbox.setVisible(false);
            employeeVbox.setPrefHeight(0);
        } else {
            emploMainBtnImg.setRotate(90);
            employeeVbox.setVisible(true);
            employeeVbox.setPrefHeight(Control.USE_COMPUTED_SIZE);
        }
    }

    @FXML
    void showAllEmployees(ActionEvent event) throws IOException {
        if (borderPane.getCenter() != employeeView) {
            borderPane.setCenter(employeeView);
        }

        EmployeeController eController = new EmployeeController();
        eController.showAllEmpPane(allEmployeesPane, addEmployeesPane);
        ResetButtonStyle();
        allEmplonBtn.setStyle("-fx-background-color : #fe8b2c !important; -fx-text-fill: #fff;");
    }

    @FXML
    void showAddEmployees(ActionEvent event) throws IOException {
        if (borderPane.getCenter() != employeeView) {
            borderPane.setCenter(employeeView);
        }

        EmployeeController eController = new EmployeeController();
        eController.showAddEmpPane(allEmployeesPane, addEmployeesPane);
        ResetButtonStyle();
        addEmplonBtn.setStyle("-fx-background-color : #fe8b2c !important; -fx-text-fill: #fff;");
    } 


    // Table tab......................................
    @FXML
    void showTableVbox(ActionEvent event) {
        if (tableVbox.isVisible()) {
            tableMainBtnImg.setRotate(0);
            tableVbox.setVisible(false);
            tableVbox.setPrefHeight(0);
        } else {
            tableMainBtnImg.setRotate(90);
            tableVbox.setVisible(true);
            tableVbox.setPrefHeight(Control.USE_COMPUTED_SIZE);
        }
    }

}
