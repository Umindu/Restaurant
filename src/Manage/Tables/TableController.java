package Manage.Tables;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import DataBase.DBConnect;
import Manage.model.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

public class TableController implements Initializable {
    @FXML
    private TextField TableIdTextField;

    @FXML
    private TextField TableNameTextField;

    @FXML
    private ImageView addTableImg;

    private String imageUrl;

    @FXML
    private ComboBox<String> tableStatesCombo;

    private ObservableList<Table> tableList = FXCollections.observableArrayList();

    @FXML
    private TableView<Table> tableView;

    @FXML
    private TableColumn<Table, String> tableActionCol;

    @FXML
    private TableColumn<Table, String> tableIDCol;

    @FXML
    private TableColumn<Table, String> tableNameCol;

    @FXML
    private TableColumn<Table, String> tableStatesCol;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ObservableList<String> tableStates = FXCollections.observableArrayList("Select States", "Available", "Reserved", "Disabled");
        tableStatesCombo.setItems(tableStates);
        tableStatesCombo.getSelectionModel().selectFirst();

        // product table
        tableIDCol.setCellValueFactory(new PropertyValueFactory<Table, String>("TableId"));
        tableNameCol.setCellValueFactory(new PropertyValueFactory<Table, String>("TableName"));
        tableStatesCol.setCellValueFactory(new PropertyValueFactory<Table, String>("TableStates"));
        tableActionCol.setCellValueFactory(new PropertyValueFactory<Table, String>("TableAction"));

        tableView.setItems(tableList);
        TablesloadDataFromDatabase();
    }

    // load Table data from database to table view
    public void TablesloadDataFromDatabase() {
        tableList.clear();
        try {
            Statement statement = DBConnect.connectToDB().createStatement();
            statement.execute("select * from Tables");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                String imgUrl = resultSet.getString("ImgUrl");
                String id = resultSet.getString("ID");
                String name = resultSet.getString("Name");
                String states = resultSet.getString("States");

                Button editBtn = new Button("Edit");
                editBtn.setId("actionEditBtn");
                Button deleteBtn = new Button("Delete");
                deleteBtn.setId("actionDeleteBtn");
                HBox actionBtnContainer = new HBox(editBtn, deleteBtn);
                actionBtnContainer.setId("actionBtnContainer");
                editBtn.setOnAction(e -> {
                    editProduct(new Table(imgUrl, id, name, states, actionBtnContainer));
                });
                deleteBtn.setOnAction(e -> {
                    deleteProduct(new Table(imgUrl, id, name, states, actionBtnContainer));
                });

                tableList
                        .add(new Table(imgUrl, id, name, states, actionBtnContainer));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void deleteProduct(Table table) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Table");
        alert.setHeaderText("Are you sure you want to delete this table?");
        alert.showAndWait().ifPresent(response -> {
            if (response == javafx.scene.control.ButtonType.OK) {
                try {
                    Statement statement = DBConnect.connectToDB().createStatement();
                    statement.execute("delete from Tables where ID = '" + table.getTableId() + "'");
                    System.out.println("Table deleted successfully");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                TablesloadDataFromDatabase();
            }
        });
    }

    private void editProduct(Table table) {
        Image image = new Image(getClass().getResourceAsStream("../../Tables/" + table.getImgUrl()));
        addTableImg.setImage(image);
        imageUrl = table.getImgUrl();
        TableIdTextField.setText(table.getTableId());
        TableNameTextField.setText(table.getTableName());
        tableStatesCombo.getSelectionModel().select(table.getTableStates());

        TableIdTextField.setEditable(false);
    }

    // choose product image 
    @FXML
    void chooseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Product Image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        File defaultDirectory = new File("src\\Tables\\Table_imge");
        fileChooser.setInitialDirectory(defaultDirectory);
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            String imageName = file.getName();
            String imgCaption = imageName.substring(0, imageName.lastIndexOf("."));
            String imageExtension = imageName.substring(imageName.lastIndexOf("."), imageName.length());
            String newImagePath = "src/Tables/Table_imge/" + imgCaption + imageExtension;

            imageUrl = "Table_imge/" + imgCaption + imageExtension;
            try {
                Files.copy(file.toPath(), new File(newImagePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image copied successfully");

                addTableImg.setImage(new Image(new File(newImagePath).toURI().toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void cancelTable(ActionEvent event) {
        Image image = new Image(getClass().getResourceAsStream("../../items_img/Temp.png"));
        addTableImg.setImage(image);
        TableIdTextField.clear();
        TableNameTextField.clear();
        tableStatesCombo.getSelectionModel().selectFirst();

        TableIdTextField.setEditable(true);
    }
    // product save button functions
    @FXML
    void saveTable(ActionEvent event) {
        String tableImg = imageUrl;
        String tableId = TableIdTextField.getText();
        String tableName = TableNameTextField.getText();
        String tableStates = tableStatesCombo.getSelectionModel().getSelectedItem();

        if (!tableId.isEmpty() && !tableName.isEmpty() && tableStates != "Select Category") {
            //insert product data to database
            if (TableIdTextField.isEditable()) {
                try {
                    Statement statement = DBConnect.connectToDB().createStatement();
                    statement.execute("insert into Tables values ('" + tableId + "', '" + tableName + "', '" + tableImg
                            + "', '" + tableStates + "')");
                    System.out.println("Product saved successfully");

                    cancelTable(null);
                    TablesloadDataFromDatabase();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } 
            //update tables data to database
            else {
                try {
                    Statement statement = DBConnect.connectToDB().createStatement();
                    statement.execute("update Tables set Name = '" + tableName + "', ImgUrl = '" + tableImg + "', States = '" + tableStates
                            + "' where ID = '" + tableId + "'");
                    System.out.println("Product updated successfully");

                    cancelTable(null);
                    TablesloadDataFromDatabase();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } else {
            // addProError.setVisible(true);
        }
    }
}
