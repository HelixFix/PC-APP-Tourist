package controller;

import BDDManager.BDDManager2;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.PointsOfInterest;
import model.User;
import java.net.URL;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class UsersListController implements Initializable {
    ObservableList<User> list = FXCollections.observableArrayList();

    @FXML
    private TableView<User> table_users;

    @FXML
    private TableColumn<User, Integer> col_id;

    @FXML
    private TableColumn<User, String> col_nom;

    @FXML
    private TableColumn<User, String> col_prenom;

    @FXML
    private TableColumn<User, String> col_pseudo;

    @FXML
    private TableColumn<User, Integer> col_autorisation;

    @FXML
    private TableColumn<User, Boolean> col_activer;

    @FXML
    private JFXTextField txtfldid;

    @FXML
    private JFXButton btnactive;

    @FXML
    // mouse listener for table users
    public void clickItem(MouseEvent event)
    {
        if (event.getClickCount() == 2) //Checking double click
        {
            User dto = table_users.getSelectionModel().getSelectedItem();
            if (dto != null) {
                btnactive.setDisable(false);

                System.out.println(table_users.getSelectionModel().getSelectedItem().getId());
                txtfldid.setText(String.valueOf(table_users.getSelectionModel().getSelectedItem().getId()));
            }
        }
    }

    ObservableList<User> listM;

    @Override
    // initializes list controller with given url
    public void initialize (URL url, ResourceBundle rb) {

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_autorisation.setCellValueFactory(new PropertyValueFactory<>("Autorisation"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new  PropertyValueFactory<>("prenom"));
        col_pseudo.setCellValueFactory(new  PropertyValueFactory<>("pseudo"));
        col_activer.setCellValueFactory(new PropertyValueFactory<>("Activer"));

        listM = getDataUsers();
        table_users.setItems(listM);
    }

    // get the list of data users
    public ObservableList<User> getDataUsers() {

        list.clear();

        BDDManager2 bdd = new BDDManager2();
        bdd.start("jdbc:mysql://localhost:3306/voyage?characterEncoding=utf8", "root", "");

        String queryUsers = ("SELECT `ID_utilisateur`,`nom_utilisateur`,`prenom`,`pseudo`,`droit_acces`,`activer` FROM `utilisateur` ORDER BY ID_utilisateur DESC");
        ArrayList<ArrayList<String>> resultatDeMaRequete = new ArrayList<>(bdd.select(queryUsers));

        for (ArrayList<String> strings : resultatDeMaRequete) {


            System.out.println("test1" + strings);

            list.add(new User(Integer.parseInt(strings.get(0)), strings.get(1), strings.get(2), strings.get(3), Integer.parseInt(strings.get(4)), Integer.parseInt(strings.get(5))));

        }

        return list;
    }

    /**
     * Quand cette méthode est appelé ont change de scene vers Points d'intérêt
     */
    public void ptInteretMenuButtonPushed(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent usersParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../fxml/Admin-PtInteret.fxml")));
        Scene usersScene = new Scene(usersParent);

        // Cette ligne récupère l'information du Stage
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(usersScene);
        window.show();
    }

    /**
     * Quand cette méthode est appelé ont active un utilisateur
     */
    public void activateScreenButtonPushed() {

        if ( txtfldid.getText().trim().isEmpty()) {

            /**
             * TODO ajout d'un feedback visuel avec un message invitant l'utilisateur à selectionner une ligne du tableau
             */

        } else {
            BDDManager2 insert = new BDDManager2();
            insert.start("jdbc:mysql://localhost:3306/voyage?characterEncoding=utf8", "root", "");
            String queryClient = ("UPDATE `utilisateur` SET `activer` = NOT `activer` WHERE `utilisateur`.`ID_utilisateur` = " + txtfldid.getText() + "");
            insert.update(queryClient);
            insert.stop();

            getDataUsers();
            table_users.refresh();
        }
    }

    /**
     * Quand cette méthode est appelé ont change de scene vers Connexion
     */
    public void disconnectMenuButtonPushed(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent usersParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../fxml/loginv2.fxml")));
        Scene usersScene = new Scene(usersParent);

        // Cette ligne récupère l'information du Stage
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(usersScene);
        window.show();
    }

}
