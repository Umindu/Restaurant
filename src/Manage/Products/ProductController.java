package Manage.Products;

import java.net.URL;
import java.util.ResourceBundle;

import Manage.ManageController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class ProductController implements Initializable{

    @FXML
    private VBox addProPane;

    @FXML
    private VBox allProPane;

    @FXML
    private VBox categoryPane;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ManageController.setProductsPane(allProPane ,addProPane, categoryPane);
    }

    public void showAllProPane(VBox allProPane2, VBox addProPane2, VBox categoryPane2){
        addProPane2.setVisible(false);
        categoryPane2.setVisible(false);
        allProPane2.setVisible(true);
    }

    public void showAddProPane(VBox allProPane2, VBox addProPane2, VBox categoryPane2){
        allProPane2.setVisible(false);
        categoryPane2.setVisible(false);
        addProPane2.setVisible(true);
    }

    public void showCategoryPane(VBox allProPane2, VBox addProPane2, VBox categoryPane2){
        allProPane2.setVisible(false);
        addProPane2.setVisible(false);
        categoryPane2.setVisible(true);
    }

    
    
}
