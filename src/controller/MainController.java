package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.MainModel;

import java.awt.event.ActionEvent;
import java.io.IOException;


public class MainController {




    /**
     * Quand cette méthode est appelé ont change de scene vers Utilisateurs
     */



    public void changeScreenButtonPushed(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent usersParent = FXMLLoader.load(getClass().getResource("../fxml/Admin-UsersList.fxml"));
        Scene usersScene = new Scene(usersParent);

        // Cette ligne récupère l'information du Stage
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(usersScene);
        window.show();
    }
}
