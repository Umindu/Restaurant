package Manage.Products;

import java.sql.Statement;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import DataBase.DBConnect;
import Manage.ManageController;
import Manage.Products.model.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ProductController implements Initializable{

    @FXML
    private VBox addProPane;

    @FXML
    private VBox allProPane;

    //Category .........................................
    @FXML
    private TableView<Category> categoryTableView;

    @FXML
    private TableColumn<Category, String> tableCatID;

    @FXML
    private TableColumn<Category, String> tableCatName;

    @FXML
    private TableColumn<Category, String> tableCatAction;

    private ObservableList<Category> categoryList = FXCollections.observableArrayList();

    @FXML
    private VBox categoryPane;

    @FXML
    private TextField catIdTextField;

    @FXML
    private TextField catNameTextField;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ManageController.setProductsPane(allProPane ,addProPane, categoryPane);

        tableCatID.setCellValueFactory(new PropertyValueFactory<Category ,String>("CategoryId"));
        tableCatName.setCellValueFactory(new PropertyValueFactory<Category ,String>("CategoryName"));
        tableCatAction.setCellValueFactory(new PropertyValueFactory<Category ,String>("CategoryAction"));

        categoryTableView.setItems(categoryList);
        loadDataFromDatabase();
    }

    private void loadDataFromDatabase() {
        categoryList.clear();
        try {
            Statement statement = DBConnect.connectToDB().createStatement();
            statement.execute("select * from Category");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                String id = resultSet.getString("ID");
                String name = resultSet.getString("Name");
                String action = "action";

                categoryList.add(new Category(id, name, action));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Add Category pane function.........................................
    @FXML
    void cancelCategory(ActionEvent event) {
        catIdTextField.clear();
        catNameTextField.clear();
    }

    @FXML
    void saveCategory(ActionEvent event) {
        String catId = catIdTextField.getText();
        String catName = catNameTextField.getText();

        if(!catId.isEmpty() || !catName.isEmpty()){
            //save to database
            try {
                Statement statement = DBConnect.connectToDB().createStatement();
                statement.execute("insert into Category values ('"+catId+"', '"+catName+"')");
                System.out.println("Category saved successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        cancelCategory(null);
        loadDataFromDatabase();
    }

    //Menu Button functions.........................................
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
