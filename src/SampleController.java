import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class SampleController {

    @FXML
    private ListView<String> listView;

    // You can now access listView in your controller methods
    public void initialize() {
        // Initialize ListView with some items
        listView.getItems().addAll("Ahmed: hellooo", "client 2: test test", "Ahmed: woah am i talking to a robot here ?");
    }
}
