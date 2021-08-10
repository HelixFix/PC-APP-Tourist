package controller;

import BDDManager.BDDManager2;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class CityRomaController {
    private ArrayList<ArrayList<String>> listeVille;

    @FXML
    private GridPane grid;
    private CheckBox[][] boxes; // for access by grid coordinates
    private JFXTextField[][] txtfld;

    @FXML
    private void initialize() {
        BDDManager2 bdd = new BDDManager2();
        bdd.start("jdbc:mysql://localhost:3306/voyage?characterEncoding=utf8", "root", "");


         listeVille = bdd.select("SELECT `nom_ville`,`description` FROM `ville` WHERE 1; ");
        bdd.stop();

           for (int row =0; row < listeVille.size(); row++) {
         for (int col = 0; col < listeVille.size(); col++) {

        JFXTextField txtfld = new JFXTextField();
        grid.add(txtfld.setText(listeVille.get(row).get(1)), col, row);
        }

        }


        boxes = new CheckBox[16][16];
        for (int row = 0; row < boxes.length; row++) {
        for (int col = 0; col < boxes[0].length; col++) {
         CheckBox box = new CheckBox();
        grid.add(box, col, row);
        boxes[row][col] = box;
         }
         }
    }
}
