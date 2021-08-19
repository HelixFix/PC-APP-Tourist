package controller;

import BDDManager.BDDManager2;
import com.jfoenix.controls.JFXButton;
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

    // Instantiate list
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
    private TableColumn<PointsOfInterest, JFXButton> col_manage;

    @FXML
    private TableColumn<PointsOfInterest, JFXButton> col_link;

    @FXML
    private TableColumn<PointsOfInterest, Integer> col_id;

    @Override
    public void initialize (URL url, ResourceBundle rb) {

        name.setText(Data.username);

        // Set property to tableview columns
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_city.setCellValueFactory(new PropertyValueFactory<>("ville"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_manage.setCellValueFactory(new PropertyValueFactory<>("btnmanage"));
        col_link.setCellValueFactory(new PropertyValueFactory<>("btnlink"));


        // rune the method
        listM = getDataPtInterest();

        // set data to tableview
        table_fav.setItems(listM);

    }
    public static ObservableList<PointsOfInterest> listM;


// get the list of data PointsOfInterest
public ObservableList<PointsOfInterest> getDataPtInterest() {

    list.clear();

    BDDManager2 bdd = new BDDManager2();
    bdd.start("jdbc:mysql://localhost:3306/voyage?characterEncoding=utf8", "root", "");

    // Select query string
    String queryPointsOfInterest = ("SELECT DISTINCT id, point_interet.id_pt_interet, ville.nom_ville, point_interet.nom_pt_interet FROM `avoir` INNER JOIN point_interet ON point_interet.id_pt_interet = avoir.id_pt_interet INNER JOIN ville ON ville.id_ville = point_interet.id_ville INNER JOIN utilisateur ON utilisateur.ID_utilisateur = avoir.ID_utilisateur WHERE utilisateur.nom_utilisateur = \"" + Data.username +"\" ORDER BY nom_ville");
    ArrayList<ArrayList<String>> resultatDeMaRequete = new ArrayList<>(bdd.select(queryPointsOfInterest));

    for (ArrayList<String> strings : resultatDeMaRequete) {


        System.out.println("test1" + strings);

        list.add(new PointsOfInterest(Integer.parseInt(strings.get(0)),Integer.parseInt(strings.get(1)), strings.get(2), strings.get(3), new JFXButton("Supprimer"), new JFXButton("Voir")));

    }

    return list;


}



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
}

