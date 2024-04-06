import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;


public class SampleController {

    @FXML
    private ListView<String> listView;

    public void addmessage() {
        listView.getItems().add("Ahmed: i clicked the button !");
    }

    public void sendClick(ActionEvent event) {
        addmessage();
    }
}