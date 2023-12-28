package views;

import app.Configuration;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import models.User;
import util.DBManager;
import util.StateManager;
import util.WindowManager;

public class StartView {
    @FXML private VBox loginContainer;
    @FXML private VBox regContainer;
    @FXML private Label showRegistration;
    @FXML private Button register;
    @FXML private Button showLogin;
    @FXML private Button login;
    @FXML private Label message;

    private final BooleanProperty isRegistering = new SimpleBooleanProperty(false);
    private final StringProperty messageHint = new SimpleStringProperty("");

    @FXML
    private void initialize() {
        DBManager.initTables();

        handleProperties();
        handleEvents();
    }

    private void handleProperties() {
        regContainer.managedProperty().bind(isRegistering);
        regContainer.visibleProperty().bind(isRegistering);

        loginContainer.managedProperty().bind(isRegistering.not());
        loginContainer.visibleProperty().bind(isRegistering.not());

        message.textProperty().bind(messageHint);
        message.managedProperty().bind(messageHint.isEmpty().not());
        message.visibleProperty().bind(messageHint.isEmpty().not());
    }

    private void handleEvents() {
        showRegistration.setOnMouseClicked(event -> {
            isRegistering.set(true);
            messageHint.set("");
        });

        showLogin.setOnMouseClicked(event -> {
            isRegistering.set(false);
            messageHint.set("");
        });

        register.setOnMouseClicked(event -> registerUser());
        login.setOnMouseClicked(event -> authUser());
    }

    private void authUser() {
        TextField login = (TextField) loginContainer.lookup("#authLogin");
        TextField password = (TextField) loginContainer.lookup("#authPassword");

        DBManager.authUser(login.getText(), password.getText());
        if (((User) StateManager.get("user")).getID() != -1) {
            WindowManager.change(register, "main", Configuration.WindowTitle.main);
            return;
        }

        messageHint.set("Неправильный логин или пароль");
    }

    private void registerUser() {
        TextField login = (TextField) regContainer.lookup("#regLogin");
        TextField password = (TextField) regContainer.lookup("#regPassword");

        DBManager.createUser(login.getText(), password.getText());
        if ((boolean) StateManager.get("user_registered")) {
            isRegistering.set(false);

            login.setText("");
            password.setText("");
            return;
        }

        messageHint.set("Выбранный логин уже используется");
    }
}