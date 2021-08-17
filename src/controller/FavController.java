package controller;

import BDDManager.BDDManager2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Data;
import model.PointsOfInterest;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class FavController implements Initializable {
    ObservableList<PointsOfInterest> list = FXCollections.observableArrayList();


    @FXML
    private Text name;

    @FXML
    private TableView<PointsOfInterest> table_fav;

    @FXML
    private TableColumn<PointsOfInterest, String> col_city;

    @FXML
    private TableColumn<PointsOfInterest, String> col_name;

    @FXML
    private TableColumn<PointsOfInterest, String> col_manage;

    @FXML
    private TableColumn<PointsOfInterest, String> col_link;

    @FXML
    private TableColumn<PointsOfInterest, Integer> col_id;

    @Override
    public void initialize (URL url, ResourceBundle rb) {

        name.setText(Data.username);

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_city.setCellValueFactory(new PropertyValueFactory<>("ville"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("nom"));

        listM = getDataPtInterest();
        table_fav.setItems(listM);

    }
    ObservableList<PointsOfInterest> listM;
/**
 * TODO table with action buttons and query group by city
 */

// get the list of data PointsOfInterest
public ObservableList<PointsOfInterest> getDataPtInterest() {

    list.clear();

    BDDManager2 bdd = new BDDManager2();
    bdd.start("jdbc:mysql://localhost:3306/voyage?characterEncoding=utf8", "root", "");

    String queryPointsOfInterest = ("SELECT point_interet.id_pt_interet, ville.nom_ville, point_interet.nom_pt_interet FROM `avoir` INNER JOIN point_interet ON point_interet.id_pt_interet = avoir.id_pt_interet INNER JOIN ville ON ville.id_ville = point_interet.id_ville INNER JOIN utilisateur ON utilisateur.ID_utilisateur = avoir.ID_utilisateur WHERE utilisateur.nom_utilisateur = \"" + Data.username +"\"");
    ArrayList<ArrayList<String>> resultatDeMaRequete = new ArrayList<>(bdd.select(queryPointsOfInterest));

    for (ArrayList<String> strings : resultatDeMaRequete) {


        System.out.println("test1" + strings);

        list.add(new PointsOfInterest(Integer.parseInt(strings.get(0)), strings.get(1), strings.get(2)));

    }

    return list;


}


    /**
     * Quand cette méthode est appelé ont change de scene vers Villes
     */
    public void cityMenuButtonPushed(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent usersParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../fxml/User-City.fxml")));
        Scene usersScene = new Scene(usersParent);

        // Cette ligne récupère l'information du Stage
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(usersScene);
        window.show();
    }

    /**
     * Quand cette méthode est appelé ont change de scene vers Favoris
     */
    public void favMenuButtonPushed(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent usersParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../fxml/User-Fav.fxml")));
        Scene usersScene = new Scene(usersParent);

        // Cette ligne récupère l'information du Stage
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(usersScene);
        window.show();
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

