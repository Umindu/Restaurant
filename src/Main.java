import Dashboard.DashboardController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import javafx.stage.StageStyle;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        // Create an instance of the FXMLLoader class.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard/Dashboard.fxml"));

        // Load the FXML file.
        Parent root = (Parent) loader.load();

        //stage.initStyle(StageStyle.UNDECORATED);

        // Set the scene on the stage.
        stage.setScene(new Scene(root));

        // Show the stage.
        stage.show();

         // Get the controller instance from the FXMLLoader
        DashboardController dashboardController = loader.getController();
        dashboardController.HomeButtonClick(null); 
 
    }
}
