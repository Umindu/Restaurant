package Tables;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import DataBase.DBConnect;
import Tables.Table_Temp.TableTempController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Order_details;
import model.Table_list;

public class TablesController implements Initializable {

    @FXML
    private GridPane tableGridPane;

    @FXML
    private Label selectTablesLabel;

    private List<Table_list> tables;

    //order details object
    private static Order_details orderDetails;

    private ArrayList<String> selectTables = new ArrayList<>();

    public static void setOrderDetailsObject(Order_details orderDetails2) {
        orderDetails = orderDetails2;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //allready selected tables load
        selectTables = orderDetails.getTables();
    
        if(selectTables.isEmpty()){
            selectTablesLabel.setText("Not Selected");
        }else{
            selectTablesLabel.setText(selectTables.toString().substring(1, selectTables.toString().length()-1));
        }

        LoadTables("All");
    }

    private void LoadTables(String type){
        tableGridPane.getChildren().clear();
        //load tables
        tables = new ArrayList<>(tables(type));
        int column = 0;
        int row = 1;

        try {
            for(int i = 0 ; i < tables.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Table_Temp/TableTemp.fxml"));
                Pane pane = fxmlLoader.load();
                TableTempController tableController = fxmlLoader.getController();
                tableController.setData(tables.get(i), selectTables);

                if(column == 4){
                    column=0;
                    ++row;
                }

                tableGridPane.add(pane, column++, row);
                GridPane.setMargin(pane, new Insets(-25, 14, 50, 5));

                final int tableIndex = i;
                pane.setOnMouseClicked(event -> {
                    if (selectTables.contains(tables.get(tableIndex).getTableID())) {
                        selectTables.remove(tables.get(tableIndex).getTableID());
                        orderDetails.setTables(selectTables);
                    }else{
                        selectTables.add(tables.get(tableIndex).getTableID());
                        orderDetails.setTables(selectTables);
                    }

                    if(selectTables.isEmpty()){
                        selectTablesLabel.setText("Not Selected");
                    }else{
                        selectTablesLabel.setText(selectTables.toString().substring(1, selectTables.toString().length()-1));
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Table_list> tables(String type){
        List<Table_list> ls = new ArrayList<>();

        try {
            Statement statement = DBConnect.connectToDB().createStatement();
            if (type.equals("All")) {
                statement.execute("select * from Tables");
                
            }else if(type.equals("Vacant")){
                statement.execute("select * from Tables where States = 'Order on hold' OR States = 'Vacant'");
            }else{
                statement.execute("select * from Tables where States = '"+type+"'");
            }
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                String imgUrl = resultSet.getString("ImgUrl");
                String id = resultSet.getString("ID");
                String name = resultSet.getString("Name");
                String states = resultSet.getString("States");
                String sheetCount = resultSet.getString("SheetCount");

                ls.add(new Table_list(imgUrl, id, name, states, sheetCount));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ls;
    }


    @FXML
    void AllTables(ActionEvent event) {
        LoadTables("All");
    }

    @FXML
    void DisabledTables(ActionEvent event) {
        LoadTables("Disabled");
    }

    @FXML
    void OccupiedTables(ActionEvent event) {
        LoadTables("Occupied");
    }

    @FXML
    void VacantTables(ActionEvent event) {
        LoadTables("Vacant");
    }

}
