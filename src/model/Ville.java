package model;

import BDDManager.BDDManager2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Ville {

    Integer id;
    String nom;

    public Ville( String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Ville() {

    }

    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public ObservableList<Ville> getVilles() {
        ObservableList<Ville> obs = FXCollections.observableArrayList();

        BDDManager2 bdd = new BDDManager2();
        bdd.start("jdbc:mysql://localhost:3306/voyage?characterEncoding=utf8", "root", "");
        String queryCity = "SELECT nom_ville FROM ville";
        ArrayList<ArrayList<String>> resultatDeMaRequete = new ArrayList<>(bdd.select(queryCity));
        for (ArrayList<String> strings : resultatDeMaRequete) {


            System.out.println("ville" + strings);

            obs.add(new Ville(strings.get(0)));
            System.out.println("ville" + obs);

        }

        bdd.stop();

        return obs;

    }
}
