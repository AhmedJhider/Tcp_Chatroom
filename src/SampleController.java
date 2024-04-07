import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;


public class SampleController {

    @FXML
    private ListView<String> listView;
    @FXML
    private AnchorPane welcome_page;
    @FXML
    private AnchorPane signup_page;
    @FXML
    private AnchorPane login_page;

    public void addmessage() {
        listView.getItems().add("Ahmed: i clicked the button !");
    }

    public void sendClick(ActionEvent event) {
        addmessage();
    }
    public void signup(ActionEvent event){
        welcome_page.setVisible(false);
        signup_page.setVisible(true);
    }
    public void login(ActionEvent event){
        welcome_page.setVisible(false);
        login_page.setVisible(true);
    }
    public void esc(ActionEvent event){
        welcome_page.setVisible(true);
        signup_page.setVisible(false);
        login_page.setVisible(false);

    }
}