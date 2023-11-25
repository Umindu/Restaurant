package Manage.Products;

import java.sql.Statement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import DataBase.DBConnect;
import Manage.ManageController;
import Manage.model.Category;
import Manage.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class ProductController implements Initializable {

    @FXML
    private Label addAndEditLableTitle;

    // Add Products .........................................

    @FXML
    private Label addProError;

    @FXML
    private ImageView addProImg;

    private String imageUrl;

    @FXML
    private TextField proIdTextField;

    @FXML
    private TextField proNameTextField;

    @FXML
    private ComboBox<String> proCategoryCombo;

    @FXML
    private TextField proCostTextField;

    @FXML
    private TextField proPriceTextField;

    @FXML
    private TextField proDiscountTextField;

    @FXML
    private TextArea proDescriptionTextArea;

    @FXML
    private VBox addProPane;

    private ObservableList<String> categoryComboList = FXCollections.observableArrayList();

    // All Products .........................................
    @FXML
    private TableView<Product> productsTableView;

    @FXML
    private TableColumn<Product, String> tableProID;

    @FXML
    private TableColumn<Product, String> tableProName;

    @FXML
    private TableColumn<Product, String> tableProCategory;

    @FXML
    private TableColumn<Product, String> tableProCost;

    @FXML
    private TableColumn<Product, String> tableProDiscount;

    @FXML
    private TableColumn<Product, String> tableProPrice;

    @FXML
    private TableColumn<Product, HBox> tableProAction;

    private ObservableList<Product> productList = FXCollections.observableArrayList();

    @FXML
    private VBox allProPane;

    // Category .........................................
    @FXML
    private Label addCatError;

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
        ManageController.setProductsPane(allProPane, addProPane, categoryPane);

        // category table
        tableCatID.setCellValueFactory(new PropertyValueFactory<Category, String>("CategoryId"));
        tableCatName.setCellValueFactory(new PropertyValueFactory<Category, String>("CategoryName"));
        tableCatAction.setCellValueFactory(new PropertyValueFactory<Category, HBox>("CategoryAction"));

        categoryTableView.setItems(categoryList);
        CategoryloadDataFromDatabase();

        // product table
        tableProID.setCellValueFactory(new PropertyValueFactory<Product, String>("ProductId"));
        tableProName.setCellValueFactory(new PropertyValueFactory<Product, String>("ProductName"));
        tableProCategory.setCellValueFactory(new PropertyValueFactory<Product, String>("ProductCategory"));
        tableProCost.setCellValueFactory(new PropertyValueFactory<Product, String>("ProductCost"));
        tableProDiscount.setCellValueFactory(new PropertyValueFactory<Product, String>("ProductDiscount"));
        tableProPrice.setCellValueFactory(new PropertyValueFactory<Product, String>("ProductPrice"));
        tableProAction.setCellValueFactory(new PropertyValueFactory<Product, HBox>("ProductAction"));

        productsTableView.setItems(productList);
        ProductloadDataFromDatabase();

        // product category combo refresh
        refrashCategoryCombo();
    }


    // All Product#########################################################################
    // load product data from database to table view
    public void ProductloadDataFromDatabase() {
        productList.clear();
        try {
            Statement statement = DBConnect.connectToDB().createStatement();
            statement.execute("select * from Products");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                String imgUrl = resultSet.getString("ImgUrl");
                String id = resultSet.getString("ID");
                String name = resultSet.getString("Name");
                String category = resultSet.getString("Category");
                String cost = resultSet.getString("Cost");
                String discount = resultSet.getString("Discount");
                String price = resultSet.getString("Price");
                String description = resultSet.getString("Description");

                Button editBtn = new Button("Edit");
                editBtn.setId("actionEditBtn");
                Button deleteBtn = new Button("Delete");
                deleteBtn.setId("actionDeleteBtn");
                HBox actionBtnContainer = new HBox(editBtn, deleteBtn);
                actionBtnContainer.setId("actionBtnContainer");
                editBtn.setOnAction(e -> {
                    editProduct(
                            new Product(imgUrl, id, name, category, cost, discount, price, description, actionBtnContainer));
                });
                deleteBtn.setOnAction(e -> {
                    deleteProduct(
                            new Product(imgUrl, id, name, category, cost, discount, price, description, actionBtnContainer));
                });

                productList
                        .add(new Product(imgUrl, id, name, category, cost, discount, price, description, actionBtnContainer));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Edit Product function
    private void editProduct(Product product) {
        Image image = new Image(getClass().getResourceAsStream("../" + product.getImgUrl()));
        addProImg.setImage(image);
        imageUrl = product.getImgUrl();
        proIdTextField.setText(product.getProductId());
        proNameTextField.setText(product.getProductName());
        proCategoryCombo.getSelectionModel().select(product.getProductCategory());
        proCostTextField.setText(product.getProductCost());
        proPriceTextField.setText(product.getProductPrice());
        proDiscountTextField.setText(product.getProductDiscount());
        proDescriptionTextArea.setText(product.getProductDescription());
        proIdTextField.setEditable(false);

        allProPane.setVisible(false);
        addProPane.setVisible(true);

        addAndEditLableTitle.setText("Edit Product");
    }
    // Delete Product function
    private void deleteProduct(Product product) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Product");
        alert.setHeaderText("Are you sure you want to delete this product?");
        alert.showAndWait().ifPresent(response -> {
            if (response == javafx.scene.control.ButtonType.OK) {
                try {
                    Statement statement = DBConnect.connectToDB().createStatement();
                    statement.execute("delete from Products where ID = '" + product.getProductId() + "'");
                    System.out.println("Product deleted successfully");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ProductloadDataFromDatabase();
            }
        });
    }



    // Add Product#########################################################################
    // choose product image 
    @FXML
    void chooseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Product Image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        File defaultDirectory = new File("src\\items_img");
        fileChooser.setInitialDirectory(defaultDirectory);
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            String imageName = file.getName();
            String imgCaption = imageName.substring(0, imageName.lastIndexOf("."));
            String imageExtension = imageName.substring(imageName.lastIndexOf("."), imageName.length());
            String newImagePath = "src/items_img/" + imgCaption + imageExtension;

            imageUrl = "../items_img/" + imgCaption + imageExtension;
            try {
                Files.copy(file.toPath(), new File(newImagePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image copied successfully");

                addProImg.setImage(new Image(new File(newImagePath).toURI().toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // refresh product category combo
    private void refrashCategoryCombo() {
        categoryComboList.clear();
        categoryComboList.add("Select Category");
        try {
            Statement statement = DBConnect.connectToDB().createStatement();
            statement.execute("select * from Category");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                categoryComboList.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        proCategoryCombo.setItems(categoryComboList);
        proCategoryCombo.getSelectionModel().selectFirst();
    }
    // product cancel button functions
    @FXML
    void cancelProduct(ActionEvent event) {
        Image image = new Image(getClass().getResourceAsStream("../../items_img/Temp.png"));
        addProImg.setImage(image);
        proIdTextField.clear();
        proNameTextField.clear();
        proCategoryCombo.getSelectionModel().select(0);
        proCostTextField.clear();
        proPriceTextField.clear();
        proDiscountTextField.clear();
        proDescriptionTextArea.clear();

        if (!proIdTextField.isEditable()) {
            addProPane.setVisible(false);
            allProPane.setVisible(true);
        }
        proIdTextField.setEditable(true);

        addAndEditLableTitle.setText("Add Product");
        addProError.setVisible(false);
    }
    // product save button functions
    @FXML
    void saveProduct(ActionEvent event) {
        String proImg = imageUrl;
        String proId = proIdTextField.getText();
        String proName = proNameTextField.getText();
        String proCategory = proCategoryCombo.getSelectionModel().getSelectedItem();
        String proCost = proCostTextField.getText();
        String proPrice = proPriceTextField.getText();
        String proDiscount = proDiscountTextField.getText();
        String proDescription = proDescriptionTextArea.getText();

        if (!proId.isEmpty() && !proName.isEmpty() && proCategory != "Select Category" && !proCost.isEmpty()
                && !proPrice.isEmpty()) {
            //insert product data to database
            System.out.println("##########");
            if (proIdTextField.isEditable()) {
                try {
                    Statement statement = DBConnect.connectToDB().createStatement();
                    statement.execute("insert into Products values ('" + proId + "', '" + proName + "', '" + proImg
                            + "', '" + proCategory
                            + "', '" + proCost + "', '" + proDiscount + "', '" + proPrice + "', '" + proDescription
                            + "')");
                    System.out.println("Product saved successfully");

                    cancelProduct(null);
                    ProductloadDataFromDatabase();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } 
            //update product data to database
            else {
                try {
                    Statement statement = DBConnect.connectToDB().createStatement();
                    statement.execute("update Products set Name = '" + proName + "', ImgUrl = '" + proImg + "', Category = '" + proCategory
                            + "', Cost = '" + proCost + "', Discount = '" + proDiscount + "', Price = '" + proPrice
                            + "', Description = '" + proDescription + "' where ID = '" + proId + "'");
                    System.out.println("Product updated successfully");

                    cancelProduct(null);
                    ProductloadDataFromDatabase();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } else {
            addProError.setVisible(true);
        }
    }

    

    // Category############################################################################
    // load category data from database to table view
    public void CategoryloadDataFromDatabase() {
        categoryList.clear();
        try {
            Statement statement = DBConnect.connectToDB().createStatement();
            statement.execute("select * from Category");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                String id = resultSet.getString("ID");
                String name = resultSet.getString("Name");

                Button editBtn = new Button("Edit");
                editBtn.setId("actionEditBtn");
                Button deleteBtn = new Button("Delete");
                deleteBtn.setId("actionDeleteBtn");
                HBox actionBtnContainer = new HBox(editBtn, deleteBtn);
                actionBtnContainer.setId("actionBtnContainer");
                editBtn.setOnAction(e -> {
                    editCategory(new Category(id, name, actionBtnContainer));
                });
                deleteBtn.setOnAction(e -> {
                    deleteCategory(new Category(id, name, actionBtnContainer));
                });

                categoryList.add(new Category(id, name, actionBtnContainer));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Edit Category function
    private void editCategory(Category category) {
        catIdTextField.setText(category.getCategoryId());
        catNameTextField.setText(category.getCategoryName());
        catIdTextField.setEditable(false);
    }
    // Delete Category function
    private void deleteCategory(Category category) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Category");
        alert.setHeaderText("Are you sure you want to delete this category?");
        alert.showAndWait().ifPresent(response -> {
            if (response == javafx.scene.control.ButtonType.OK) {
                try {
                    Statement statement = DBConnect.connectToDB().createStatement();
                    statement.execute("delete from Category where ID = '" + category.getCategoryId() + "'");
                    System.out.println("Category deleted successfully");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                CategoryloadDataFromDatabase();
            }
        });
    }
    // Category cancel button functions
    @FXML
    void cancelCategory(ActionEvent event) {
        catIdTextField.setEditable(true);
        catIdTextField.clear();
        catNameTextField.clear();

        addCatError.setVisible(false);

        refrashCategoryCombo();
    }
    // Category save button functions
    @FXML
    void saveCategory(ActionEvent event) {
        String catId = catIdTextField.getText();
        String catName = catNameTextField.getText();

        if (!catId.isEmpty() && !catName.isEmpty()) {
            if (catIdTextField.isEditable()) {
                try {
                    Statement statement = DBConnect.connectToDB().createStatement();
                    statement.execute("insert into Category values ('" + catId + "', '" + catName + "')");
                    System.out.println("Category saved successfully");
                    cancelCategory(null);
                    CategoryloadDataFromDatabase();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Statement statement = DBConnect.connectToDB().createStatement();
                    statement.execute("update Category set Name = '" + catName + "' where ID = '" + catId + "'");
                    System.out.println("Category updated successfully");
                } catch (SQLException e) {
                    e.printStackTrace();
                    cancelCategory(null);
                    CategoryloadDataFromDatabase();
                }
            }
        } else {
            addCatError.setVisible(true);
        }
    }

    // Menu Button functions
    public void showAllProPane(VBox allProPane2, VBox addProPane2, VBox categoryPane2) {
        addProPane2.setVisible(false);
        categoryPane2.setVisible(false);
        allProPane2.setVisible(true);
    }

    public void showAddProPane(VBox allProPane2, VBox addProPane2, VBox categoryPane2) {
        allProPane2.setVisible(false);
        categoryPane2.setVisible(false);
        addProPane2.setVisible(true);
    }

    public void showCategoryPane(VBox allProPane2, VBox addProPane2, VBox categoryPane2) {
        allProPane2.setVisible(false);
        addProPane2.setVisible(false);
        categoryPane2.setVisible(true);
    }

}
