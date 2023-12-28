package util;

import app.Configuration;
import javafx.fxml.FXMLLoader;
import org.w3c.dom.Node;

import java.io.IOException;

public class Util {
    public static Node loadComponent(String name) {
        String path = Configuration.Components.components.get(name);
        FXMLLoader loader = new FXMLLoader(Util.class.getResource(path));

        try {
            return loader.load();

        } catch (IOException ignored) {}

        return null;
    }
}
