import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();

        // Get the controller associated with the FXML file
        SampleController controller = loader.getController();
        // Create a scene
        Scene scene = new Scene(root, 600, 400);

        // Set the scene to the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("FXML Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}