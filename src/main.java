import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;


public class main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("Current directory: " + currentDirectory);
        FXMLLoader loader = new FXMLLoader(new File("resources/login_scene.fxml").toURI().toURL());
        Parent root = loader.load();
        login_controller controller = loader.getController();
        Scene scene = new Scene(root, 364, 269);

        Image icon = new Image(new FileInputStream("resources/icon.jpg"));
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(scene);
        primaryStage.setTitle("PalChat");
        primaryStage.setResizable(false);
        root.requestFocus();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}