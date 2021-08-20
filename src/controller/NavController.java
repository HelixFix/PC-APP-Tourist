package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class NavController {


    /**
     * Quand cette méthode est appelé ont change de scene vers Villes
     */
    public static void cityMenuButtonPushed(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent usersParent = FXMLLoader.load(Objects.requireNonNull(NavController.class.getResource("../fxml/User-City-Cards.fxml")));
        Scene usersScene = new Scene(usersParent);

        // Cette ligne récupère l'information du Stage
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(usersScene);
        window.show();
    }

    /**
     * Quand cette méthode est appelé ont change de scene vers Favoris
     */
    public static void favMenuButtonPushed(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent usersParent = FXMLLoader.load(Objects.requireNonNull(NavController.class.getResource("../fxml/User-Fav.fxml")));
        Scene usersScene = new Scene(usersParent);

        // Cette ligne récupère l'information du Stage
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(usersScene);
        window.show();
    }

    /**
     * Quand cette méthode est appelé ont change de scene vers Connexion
     */
    public static void disconnectMenuButtonPushed(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent usersParent = FXMLLoader.load(Objects.requireNonNull(NavController.class.getResource("../fxml/login.fxml")));
        Scene usersScene = new Scene(usersParent);

        // Cette ligne récupère l'information du Stage
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(usersScene);
        window.show();
    }

}
