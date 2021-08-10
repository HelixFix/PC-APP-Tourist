package controller;

import BDDManager.BDDManager2;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class CityController {
    private ArrayList<ArrayList<String>> listeVille;

    @FXML private GridPane grid;
    private CheckBox[][] boxes; // for access by grid coordinates
    private JFXTextField[][] txtfld;

    @FXML
    private void initialize() {
       // BDDManager2 bdd = new BDDManager2();
        //bdd.start("jdbc:mysql://localhost:3306/voyage?characterEncoding=utf8", "root", "");


       // listeVille = bdd.select("SELECT `nom_ville`,`description` FROM `ville` WHERE 1; ");
        //bdd.stop();

     //   for (int row =0; row < listeVille.size(); row++) {
           // for (int col = 0; col < listeVille.size(); col++) {

                //JFXTextField txtfld = new JFXTextField();
                //grid.add(txtfld.setText(listeVille.get(row).get(1)), col, row);
           //}

        //}


        //boxes = new CheckBox[16][16];
        //for (int row = 0; row < boxes.length; row++) {
            //for (int col = 0; col < boxes[0].length; col++) {
               // CheckBox box = new CheckBox();
                //grid.add(box, col, row);
                //boxes[row][col] = box;
           // }
       // }
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


    /**
     * Quand cette méthode est appelé ont change de scene vers Rome
     */
    public void romaScreenButtonPushed(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent usersParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../fxml/User-City-Rome.fxml")));
        Scene usersScene = new Scene(usersParent);

        // Cette ligne récupère l'information du Stage
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(usersScene);
        window.show();
    }


}
