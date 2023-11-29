package Reports;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class ReportsController implements Initializable{
    @FXML
    private LineChart<String, Integer> dailyOrder;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Monday", 23));
        series.getData().add(new XYChart.Data<>("Tuesday", 14));
        series.getData().add(new XYChart.Data<>("Wednesday", 15));
        series.getData().add(new XYChart.Data<>("Thursday", 24));
        series.getData().add(new XYChart.Data<>("Friday", 34));
        series.getData().add(new XYChart.Data<>("Saturday", 36));
        series.getData().add(new XYChart.Data<>("Sunday", 22));
        dailyOrder.getData().add(series);
    }



}
