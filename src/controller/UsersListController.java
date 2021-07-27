package controller;

import BDDManager.BDDManager2;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Users;
import java.net.URL;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class UsersListController implements Initializable {

    @FXML
    private TableView<Users> table_users;

    @FXML
    private TableColumn<Users, Integer> col_id;

    @FXML
    private TableColumn<Users, String> col_nom;

    @FXML
    private TableColumn<Users, String> col_prenom;

    @FXML
    private TableColumn<Users, String> col_pseudo;

    @FXML
    private TableColumn<Users, Integer> col_autorisation;

    @FXML
    private TableColumn<Users, Boolean> col_activer;

    ObservableList<Users> listM;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    @Override
    // initializes list controller with given url
    public void initialize (URL url, ResourceBundle rb) {

        col_id.setCellValueFactory(new PropertyValueFactory<Users, Integer>("id"));
        col_autorisation.setCellValueFactory(new PropertyValueFactory<Users, Integer>("Autorisation"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Users, String>("nom"));
        col_prenom.setCellValueFactory(new  PropertyValueFactory<Users, String>("prenom"));
        col_pseudo.setCellValueFactory(new  PropertyValueFactory<Users, String>("pseudo"));
        col_activer.setCellValueFactory(new PropertyValueFactory<Users, Boolean>("Activer"));

        listM = BDDManager2.getDataUsers();
        table_users.setItems(listM);
    }


    /**
     * Quand cette méthode est appelé ont change de scene vers Formulaire
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
