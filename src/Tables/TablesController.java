package Tables;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import Tables.Table_Temp.TableTempController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Table_list;

public class TablesController implements Initializable {

    @FXML
    private GridPane tableGridPane;

    private List<Table_list> tables;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tables = new ArrayList<>(tables());
        int column = 0;
        int row = 1;

        try {
            for(int i = 0 ; i < tables.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Table_Temp/TableTemp.fxml"));
                Pane pane = fxmlLoader.load();
                TableTempController tableController = fxmlLoader.getController();
                tableController.setData(tables.get(i));

                if(column == 4){
                    column=0;
                    ++row;
                }

                tableGridPane.add(pane, column++, row);
                GridPane.setMargin(pane, new Insets(-25, 20, 50, 00));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Table_list> tables(){
        List<Table_list> ls = new ArrayList<>();

        Table_list table = new Table_list();
        table.setID(0);
        table.setTableNum(1);
        table.setNumOfGuest(4);
        table.setImg("../Table_imge/table.png");
        ls.add(table);

        Table_list table2 = new Table_list();
        table2.setID(1);
        table2.setTableNum(2);
        table2.setNumOfGuest(6);
        table2.setImg("../Table_imge/table.png");
        ls.add(table2);

        return ls;
    }

}
