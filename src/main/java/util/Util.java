package util;

import app.App;
import app.Configuration;
import javafx.fxml.FXMLLoader;
import org.w3c.dom.Node;

import java.io.IOException;

public class Util {
    public static Node loadComponent(String name) throws IOException {
        String path = Configuration.Components.components.get(name);
        System.out.println(path);
        FXMLLoader loader = new FXMLLoader(App.class.getResource(path));

        return loader.load();
    }
}
