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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    private TableColumn<Category, HBox> tableCatAction;

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
        tableCatAction.setCellValueFactory(new PropertyValueFactory<Category ,HBox>("CategoryAction"));

        categoryTableView.setItems(categoryList);
        loadDataFromDatabase();
    }

    public void loadDataFromDatabase() {
        categoryList.clear();
        try {
            Statement statement = DBConnect.connectToDB().createStatement();
            statement.execute("select * from Category");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                String id = resultSet.getString("ID");
                String name = resultSet.getString("Name");

                Button editBtn = new Button("Edit");
                Button deleteBtn = new Button("Delete");
                HBox actionBtnContainer = new HBox(editBtn, deleteBtn);
                editBtn.setOnAction(e -> {
                    editCategory(new Category(id, name, actionBtnContainer));
                });
                deleteBtn.setOnAction(e ->{
                    deleteCategory(new Category(id, name, actionBtnContainer));
                });

                categoryList.add(new Category(id, name, actionBtnContainer));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Add or Edit Category pane function.........................................
    private void editCategory(Category category){
        catIdTextField.setText(category.getCategoryId());
        catNameTextField.setText(category.getCategoryName());
        catIdTextField.setEditable(false);
    }

    private void deleteCategory(Category category){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Category");
        alert.setHeaderText("Are you sure you want to delete this category?");
        alert.showAndWait().ifPresent(response -> {
            if (response == javafx.scene.control.ButtonType.OK) {
                try {
                    Statement statement = DBConnect.connectToDB().createStatement();
                    statement.execute("delete from Category where ID = '"+category.getCategoryId()+"'");
                    System.out.println("Category deleted successfully");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                loadDataFromDatabase();
            }
        });
    }

    @FXML
    void cancelCategory(ActionEvent event) {
        catIdTextField.setEditable(true);
        catIdTextField.clear();
        catNameTextField.clear();
    }

    @FXML
    void saveCategory(ActionEvent event) {
        String catId = catIdTextField.getText();
        String catName = catNameTextField.getText();

        if(!catId.isEmpty() || !catName.isEmpty()){
            //save to database
            if (catIdTextField.isEditable()) {
                try {
                    Statement statement = DBConnect.connectToDB().createStatement();
                    statement.execute("insert into Category values ('"+catId+"', '"+catName+"')");
                    System.out.println("Category saved successfully");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    Statement statement = DBConnect.connectToDB().createStatement();
                    statement.execute("update Category set Name = '"+catName+"' where ID = '"+catId+"'");
                    System.out.println("Category updated successfully");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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
