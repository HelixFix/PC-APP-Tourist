package controller;

import BDDManager.BDDManager2;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Data;
import model.PointsOfInterest;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class CityRomaController implements Initializable {
    ObservableList<PointsOfInterest> list = FXCollections.observableArrayList();

    @FXML
    private TableView<PointsOfInterest> table_ptinteret;

    @FXML
    private TableColumn<PointsOfInterest, Integer> col_id;

    @FXML
    private TableColumn<PointsOfInterest, String> col_ville;

    @FXML
    private Text title;

    @FXML
    private JFXTextArea resume;

    @FXML
    private Text title2;

    @FXML
    private Text epoque;

    @FXML
    private Text categorie;

    @FXML
    private Text architecte;

    @FXML
    private JFXCheckBox chkboxfav;

    @FXML
    private Text id;

    @FXML
    // mouse listener for table point d'intérêt
    public void clickItem(MouseEvent event)
    {
        if (event.getClickCount() == 1) //Checking click
        {
            PointsOfInterest dto = table_ptinteret.getSelectionModel().getSelectedItem();
            if (dto != null) {
                //Do my processing
                System.out.println(table_ptinteret.getSelectionModel().getSelectedItem().getNom());
                id.setText(String.valueOf(table_ptinteret.getSelectionModel().getSelectedItem().getId()));
                title.setText(table_ptinteret.getSelectionModel().getSelectedItem().getNom());
                title2.setText(table_ptinteret.getSelectionModel().getSelectedItem().getNom());
                resume.setText(String.valueOf(table_ptinteret.getSelectionModel().getSelectedItem().getDescription()));
                epoque.setText(table_ptinteret.getSelectionModel().getSelectedItem().getEpoque());
                categorie.setText(table_ptinteret.getSelectionModel().getSelectedItem().getCategorie());

            }

        }
    }

    ObservableList<PointsOfInterest> listM;

    @Override
    // initializes list controller with given url
    public void initialize(URL url, ResourceBundle rb) {

        // liaison entre la vue et le modele
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_ville.setCellValueFactory(new PropertyValueFactory<>("nom"));


        listM = getDataPtInterest();
        table_ptinteret.setItems(listM);
    }

    // get the list of data city
    public ObservableList<PointsOfInterest> getDataPtInterest() {

        list.clear();

        BDDManager2 bdd = new BDDManager2();
        bdd.start("jdbc:mysql://localhost:3306/voyage?characterEncoding=utf8", "root", "");

        String queryPointsOfInterest = ("SELECT `ID_pt_interet`,`nom_pt_interet`,`nom_ville`,`nom_architecte`,`publier`,`categorie`,`description_pt_interet`,`epoque`,`chemin_photo1`,`chemin_photo2`,`chemin_photo3` FROM `point_interet` INNER JOIN ville ON ville.id_ville = point_interet.id_ville WHERE `nom_ville` = 'Rome' AND `publier` = 1 ORDER BY nom_ville");
        ArrayList<ArrayList<String>> resultatDeMaRequete = new ArrayList<>(bdd.select(queryPointsOfInterest));

        for (ArrayList<String> strings : resultatDeMaRequete) {


            System.out.println("test1" + strings);

            list.add(new PointsOfInterest(Integer.parseInt(strings.get(0)), strings.get(1), strings.get(2), strings.get(3), Integer.parseInt(strings.get(4)), strings.get(5), strings.get(6), strings.get(7), strings.get(8), strings.get(9), strings.get(10)));


        }

        return list;
    }

    public void chkbox() {
        if (chkboxfav.isSelected()){
            BDDManager2 bdd = new BDDManager2();
            bdd.start("jdbc:mysql://localhost:3306/voyage?characterEncoding=utf8", "root", "");

            String queryFav = ("INSERT INTO avoir (id_utilisateur, ID_pt_interet) SELECT id_utilisateur, ID_pt_interet FROM utilisateur, point_interet WHERE nom_utilisateur = \"" + Data.username + "\" AND ID_pt_interet = " + id.getText() + "");
            bdd.insert(queryFav);
            bdd.stop();
        } else {
            BDDManager2 bdd = new BDDManager2();
            bdd.start("jdbc:mysql://localhost:3306/voyage?characterEncoding=utf8", "root", "");

            String queryFav = ("DELETE avoir FROM avoir INNER JOIN utilisateur ON utilisateur.ID_utilisateur = avoir.ID_utilisateur WHERE `nom_utilisateur` = \"" + Data.username + "\"");
            bdd.delete(queryFav);
            bdd.stop();
        }
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