package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Data;

import java.io.IOException;
import java.util.Objects;

public class CityCardsController {

    /**
     * Quand cette méthode est appelé ont change de scene vers Villes
     */
    public void cityMenuButtonPushed(javafx.event.ActionEvent actionEvent) throws IOException {

        NavController.cityMenuButtonPushed(actionEvent);
    }

    /**
     * Quand cette méthode est appelé ont change de scene vers Favoris
     */
    public void favMenuButtonPushed(javafx.event.ActionEvent actionEvent) throws IOException {

        NavController.favMenuButtonPushed(actionEvent);
    }

    /**
     * Quand cette méthode est appelé ont change de scene vers Connexion
     */
    public void disconnectMenuButtonPushed(javafx.event.ActionEvent actionEvent) throws IOException {

        NavController.disconnectMenuButtonPushed(actionEvent);
    }


    /**
     * Quand cette méthode est appelé ont change de scene vers Rome
     */
    public void romaScreenButtonPushed(javafx.event.ActionEvent actionEvent) throws IOException {
        Data.idPtInterest = 0;
        Data.cityName     = "Roma";

        Parent usersParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../fxml/User-City-Details.fxml")));
        Scene usersScene = new Scene(usersParent);

        // Cette ligne récupère l'information du Stage
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(usersScene);
        window.show();
    }

    /**
     * Quand cette méthode est appelé ont change de scene vers Pékin
     */
    public void beijingScreenButtonPushed(javafx.event.ActionEvent actionEvent) throws IOException {
        Data.idPtInterest = 0;
        Data.cityName = "Beijing";

        Parent usersParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../fxml/User-City-Details.fxml")));
        Scene usersScene = new Scene(usersParent);

        // Cette ligne récupère l'information du Stage
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(usersScene);
        window.show();
    }

    /**
     * Quand cette méthode est appelé ont change de scene vers Grenade
     */
    public void granadaScreenButtonPushed(javafx.event.ActionEvent actionEvent) throws IOException {
        Data.idPtInterest = 0;
        Data.cityName     = "Granada";

        Parent usersParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../fxml/User-City-Details.fxml")));
        Scene usersScene = new Scene(usersParent);

        // Cette ligne récupère l'information du Stage
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(usersScene);
        window.show();
    }


}
