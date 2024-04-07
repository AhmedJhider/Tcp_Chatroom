import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Objects;


public class SampleController {
    private boolean con;
    private Alert alertDisco;
    @FXML
    private TextField textSignup;
    @FXML
    private Label title;
    @FXML
    private ListView<String> listView;
    @FXML
    private Button esc;
    @FXML
    private AnchorPane welcome_page, signup_page, login_page;

    public void initialize(){
        title.requestFocus();

        alertDisco = new Alert(AlertType.INFORMATION);
        alertDisco.setTitle("Connection Issue");
        alertDisco.setHeaderText("Connection Failed");
        if(!Client.connect()){
            alertDisco.showAndWait();
            con = false;
        }else{
        con = true;
        }
    }

    public void getSignupPage(ActionEvent event){
        if(con){
            welcome_page.setVisible(false);
            signup_page.setVisible(true);
            esc.setVisible(true);
            textSignup.setStyle("");
            title.requestFocus();
        }else{
            alertDisco.showAndWait();
            con = Client.connect();
        }

    }
    public void requestSignup(ActionEvent event) throws SQLException {
        String username = textSignup.getText();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("INVALID");
        alert.setHeaderText("Username Already Taken");
        if(Objects.equals(username, "")){
            textSignup.setStyle("-fx-border-color: #cc0e2e; -fx-border-width: 1px; -fx-border-style: solid; -fx-border-radius: 2%;");
        }else{
            textSignup.setStyle("");
            if(Server.verifSignup(username)){
                alert.showAndWait();
            }else{
                Server.execSignup(username);
                alert.setTitle("Success");
                alert.setHeaderText("Successfully Signed-up");
                alert.showAndWait();

            }
        }
    }
    public void getLoginPage(ActionEvent event){
        if(con){
            welcome_page.setVisible(false);
            login_page.setVisible(true);
            esc.setVisible(true);
            title.requestFocus();
        }else{
            alertDisco.showAndWait();
            con = Client.connect();

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