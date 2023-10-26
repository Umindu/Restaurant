package Manage;

import java.io.IOException;

import Manage.Products.ProductController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Control;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ManageController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private VBox productVbox;

    @FXML
    private VBox employeeVbox;

    @FXML
    private VBox tableVbox;

    @FXML
    private static VBox addProPane;

    @FXML
    private static VBox allProPane;

    @FXML
    private static VBox categpryPane;

    public static void setPane(VBox allProPane2, VBox addProPane2, VBox categpryPane2) {
        addProPane = addProPane2;
        allProPane = allProPane2;
        categpryPane = categpryPane2;
    }

    // .....................................
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
    }

    @FXML
    void showAddProduct(ActionEvent event) {
        ProductController pController = new ProductController();
        pController.showAddProPane(allProPane, addProPane, categpryPane);
    }

    @FXML
    void showAllProducts(ActionEvent event) {
        ProductController pController = new ProductController();
        pController.showAllProPane(allProPane, addProPane, categpryPane);
    }

    @FXML
    void showCategory(ActionEvent event) {
        ProductController pController = new ProductController();
        pController.showCategoryPane(allProPane, addProPane, categpryPane);
    }

    // ......................................
    @FXML
    void showEmployeeVbox(ActionEvent event) {
        if (employeeVbox.isVisible()) {
            employeeVbox.setVisible(false);
            employeeVbox.setPrefHeight(0);
        } else {
            employeeVbox.setVisible(true);
            employeeVbox.setPrefHeight(Control.USE_COMPUTED_SIZE);
        }
    }

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
