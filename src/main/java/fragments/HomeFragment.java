package fragments;

import javafx.fxml.FXML;
import models.User;
import util.StateManager;

public class HomeFragment {
    @FXML
    private void initialize() {
        User user = (User) StateManager.get("user");
    }
}
