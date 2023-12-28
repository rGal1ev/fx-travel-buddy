package views;

import app.Configuration;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.User;
import util.FragmentManager;
import util.StateManager;
import util.WindowManager;

public class ContainerView {
    @FXML private VBox container;
    @FXML private Button toStart;
    @FXML private Label userName;
    @FXML private Label logo;

    private User user = (User) StateManager.get("user");

    @FXML
    private void initialize() {
        FragmentManager.injectFields(container, this);
        FragmentManager.change("home");

        handleEvents();
        handleProperties();
    }

    private void handleProperties() {
        userName.setText(user.getFIO());

        if (user.getFIO() == null) {
            userName.setText("Имя не указано");
        }
    }

    private void handleEvents() {
        toStart.setOnMouseClicked(event -> {
            WindowManager.changeViaPath(container, "/views/start-view.fxml", Configuration.WindowTitle.start);
            StateManager.update("user", null);
        });

        userName.setOnMouseClicked(event -> {
            FragmentManager.change("user");
            WindowManager.changeWindowTitle(container, "Travel Buddy - Профиль");
        });

        logo.setOnMouseClicked(event -> {
            FragmentManager.change("home");
            WindowManager.changeWindowTitle(container, "Travel Buddy - Главная");
        });
    }


}
