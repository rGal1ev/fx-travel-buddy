package util;

import app.App;
import app.Configuration;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import views.ContainerView;

import java.io.IOException;

public class FragmentManager {
    private static VBox container;
    private static ContainerView containerViewController;

    public static void injectFields(VBox injectableContainer, ContainerView injectableController) {
        container = injectableContainer;
        containerViewController = injectableController;
    }

    public static void change(String name) {
        container.getChildren().clear();

        String fragmentPath =  Configuration.Fragments.fragments.get(name);
        loadFragment(fragmentPath);
    }

    public static ContainerView getContainerViewController() {
        return containerViewController;
    }

    private static void loadFragment(String path) {
        FXMLLoader fragmentLoader = new FXMLLoader(App.class.getResource(path));

        try {
            container.getChildren().add(fragmentLoader.load());

        } catch (IOException ignored) {}
    }
}
