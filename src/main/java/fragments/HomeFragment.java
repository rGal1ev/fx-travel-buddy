package fragments;

import app.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Place;
import models.User;
import util.StateManager;
import util.Util;

import java.io.IOException;
import java.util.ArrayList;

public class HomeFragment {
    @FXML private VBox placeList;

    private User user = (User) StateManager.get("user");
    private ArrayList<Place> places = (ArrayList<Place>) StateManager.get("places");

    @FXML
    private void initialize() throws IOException {
        renderPlaceList();
    }

    private void renderPlaceList() throws IOException {
        placeList.getChildren().clear();

        for (Place place : places) {
            placeList.getChildren().add(generatePlaceCard(place));
        }
    }

    private HBox generatePlaceCard(Place place) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/components/place-card.fxml"));
        HBox card = loader.load();

        Image image = new Image(place.getImage(), true);

        assert card != null;
        ((Label) card.lookup("#title")).setText(place.getTitle());
        ((Label) card.lookup("#descr")).setText(place.getDescription());
        ((Label) card.lookup("#city")).setText(place.getCity());
        ((ImageView) card.lookup("#image")).setImage(image);

        return card;
    }
}
