package fragments;

import javafx.fxml.FXML;
import models.User;
import util.StateManager;

public class HomeFragment {
    private User user = (User) StateManager.get("user");

    @FXML
    private void initialize() {
        System.out.println(user);
    }
}
