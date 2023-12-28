package util;

import app.Configuration;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowManager {
    public static void changeViaPath(Node node, String path, String title) {
        FXMLLoader fxmlLoader = new FXMLLoader(WindowManager.class.getResource(path));
        Stage rootStage = (Stage) node.getScene().getWindow();

        try {
            BorderPane view = fxmlLoader.load();
            Scene scene = new Scene(view);

            rootStage.setScene(scene);
            rootStage.centerOnScreen();

            rootStage.setResizable(true);
            rootStage.setTitle(title);

        } catch (IOException ignored) {}
    }

    public static void change(Node node, String name, String title) {
        String path = Configuration.Windows.windows.get(name);
        FXMLLoader fxmlLoader = new FXMLLoader(WindowManager.class.getResource(path));
        Stage rootStage = (Stage) node.getScene().getWindow();

        try {
            BorderPane view = fxmlLoader.load();
            Scene scene = new Scene(view);

            rootStage.setScene(scene);
            rootStage.centerOnScreen();

            rootStage.setResizable(true);
            rootStage.setTitle(title);

        } catch (IOException ignored) {}
    }
}
