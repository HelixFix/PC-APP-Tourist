package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class UsersListController {

    @FXML
    private TableView<?> table_users;

    @FXML
    private TableColumn<?, ?> col_id;

    @FXML
    private TableColumn<?, ?> col_nom;

    @FXML
    private TableColumn<?, ?> col_prenom;

    @FXML
    private TableColumn<?, ?> col_pseudo;

    @FXML
    private TableColumn<?, ?> col_autorisation;

    @FXML
    private TableColumn<?, ?> col_activer;

    @FXML
    void formScreenButtonPushed(ActionEvent event) {

    }

    /**
     * Quand cette méthode est appelé ont change de scene vers Utilisateurs
     */
    public void formScreenButtonPushed(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent usersParent = FXMLLoader.load(getClass().getResource("../fxml/Admin-Form.fxml"));
        Scene usersScene = new Scene(usersParent);

        // Cette ligne récupère l'information du Stage
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(usersScene);
        window.show();
    }

}
