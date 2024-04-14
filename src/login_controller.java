import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Objects;


public class login_controller {
    private boolean con;
    private Alert alertDisco;
    @FXML
    private TextField textSignup, textLogin;
    @FXML
    private Label title;
    @FXML
    private ListView<String> listView;
    @FXML
    private Button esc;
    @FXML
    private AnchorPane welcome_page, signup_page, login_page;
    private Stage primaryStage;
    private Client client;

    public void setStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    public boolean connect() throws IOException {
        return Client.connectionChecker();
    }

    public void initialize() throws IOException {
        title.requestFocus();
        alertDisco = new Alert(AlertType.INFORMATION);
        alertDisco.setTitle("Connection Issue");
        alertDisco.setHeaderText("Connection Failed");
        System.out.println(connect());
        if(!connect()){
            alertDisco.showAndWait();
            con = false;
        }else{
        con = true;
        }
    }

    public void getSignupPage(ActionEvent event) throws IOException {
        if(con){
            welcome_page.setVisible(false);
            signup_page.setVisible(true);
            textSignup.clear();
            esc.setVisible(true);
            textSignup.setStyle("");
            title.requestFocus();
        }else{
            alertDisco.showAndWait();
            con = connect();
        }

    }
    public void requestSignup(ActionEvent event) throws SQLException {
        String username = textSignup.getText();
        Alert alert = new Alert(AlertType.INFORMATION);

        if(Objects.equals(username, "")){
            textSignup.setStyle("-fx-border-color: #cc0e2e; -fx-border-width: 1px; -fx-border-style: solid; -fx-border-radius: 2%;");
        }else{
            textSignup.setStyle("");
            if(Server.verifSignup(username)){
                alert.setTitle("INVALID");
                alert.setHeaderText("Username Already Taken");
                alert.showAndWait();
            }else{
                Server.execSignup(username);
                alert.setTitle("Success");
                alert.setHeaderText("Successfully Signed-up");
                alert.showAndWait();

            }
        }
    }
    public void requestLogin(ActionEvent event) throws SQLException, IOException {
        String username = textLogin.getText();
        Alert alert = new Alert(AlertType.INFORMATION);
        if(Objects.equals(username, "")){
            textSignup.setStyle("-fx-border-color: #cc0e2e; -fx-border-width: 1px; -fx-border-style: solid; -fx-border-radius: 2%;");
        }else{
            if(Server.verifSignup(username)){
                client.setUsername(username);
                FXMLLoader loader = new FXMLLoader(new File("resources/main_scene.fxml").toURI().toURL());
                Parent root = loader.load();
                Scene scene = new Scene(root, 364, 269);
                primaryStage.setScene(scene);

            }else{
                textLogin.setStyle("-fx-border-color: #cc0e2e; -fx-border-width: 1px; -fx-border-style: solid; -fx-border-radius: 2%;");
            }
        }
    }
    public void getLoginPage(ActionEvent event) throws IOException {
        if(con){
            welcome_page.setVisible(false);
            login_page.setVisible(true);
            textLogin.clear();
            esc.setVisible(true);
            title.requestFocus();
        }else{
            alertDisco.showAndWait();
            con = connect();
        }

    }
    public void getWelcomePage(ActionEvent event){
        welcome_page.setVisible(true);
        signup_page.setVisible(false);
        esc.setVisible(false);
        login_page.setVisible(false);
        title.requestFocus();
    }
}