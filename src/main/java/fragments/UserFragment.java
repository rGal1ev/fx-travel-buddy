package fragments;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.User;
import util.DBManager;
import util.StateManager;

public class UserFragment {
    @FXML private TextField login;
    @FXML private TextField fio;
    @FXML private Button saveChanges;

    private User user = (User) StateManager.get("user");

    @FXML
    private void initialize() {
        handleEvents();
        handleProperties();
    }

    private void handleProperties() {
        login.setText(user.getLogin());

        if (user.getFIO() == null) {
            fio.setText("Не указано");
        } else {
            fio.setText(user.getFIO());
        }
    }

    private void handleEvents() {
        saveChanges.setOnMouseClicked(event -> {
            String newLogin = login.getText();
            String newFio = fio.getText();

            if (!newLogin.isEmpty() && !newFio.isEmpty()) {
                DBManager.updateUserInfo(user.getID(), newLogin, newFio);
            }
        });
    }
}
