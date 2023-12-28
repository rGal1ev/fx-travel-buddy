package views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import util.FragmentManager;

public class ContainerView {
    @FXML private VBox container;
    @FXML private Button home;

    @FXML
    private void initialize() {
        FragmentManager.injectFields(container, this);
        FragmentManager.change("home");

        handleEvents();
    }

    private void handleEvents() {
        home.setOnMouseClicked(event -> FragmentManager.change("home"));
    }
}
