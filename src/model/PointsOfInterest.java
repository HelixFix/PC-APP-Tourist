package model;

import BDDManager.BDDManager2;
import com.jfoenix.controls.JFXButton;
import controller.FavController;
import javafx.collections.transformation.TransformationList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class PointsOfInterest {
    Integer id, idptinteret, publier;
    String ville, nom, architecte, categorie, epoque, photo1, photo2, photo3, description;

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getArchitecte() {
        return architecte;
    }

    public void setArchitecte(String architecte) {
        this.architecte = architecte;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getEpoque() {
        return epoque;
    }

    public void setEpoque(String epoque) {
        this.epoque = epoque;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public String getPhoto3() {
        return photo3;
    }

    public void setPhoto3(String photo3) {
        this.photo3 = photo3;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIdptinteret() {
        return idptinteret;
    }

    public void setIdptinteret(Integer idptinteret) {
        this.idptinteret = idptinteret;
    }

    public Integer getPublier() {
        return publier;
    }

    public void setPublier(Integer publier) {
        this.publier = publier;
    }

    public PointsOfInterest(Integer idptinteret) {
        this.idptinteret = idptinteret;
    }

    private JFXButton btnmanage;
    private JFXButton btnlink;

    public PointsOfInterest(Integer idptinteret, String nom, String ville, String architecte, Integer publier, String categorie, String description, String epoque, String photo1, String photo2, String photo3) {
        this.idptinteret = idptinteret;
        this.ville       = ville;
        this.nom         = nom;
        this.architecte  = architecte;
        this.publier     = publier;
        this.categorie   = categorie;
        this.description = description;
        this.epoque      = epoque;
        this.photo1      = photo1;
        this.photo2      = photo2;
        this.photo3      = photo3;

    }

    public JFXButton getBtnmanage() {
        return btnmanage;
    }

    public void setBtnmanage(JFXButton btnmanage) {
        this.btnmanage = btnmanage;
    }

    public JFXButton getBtnlink() {
        return btnlink;
    }

    public void setBtnlink(JFXButton btnlink) {
        this.btnlink = btnlink;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PointsOfInterest(Integer id, Integer idptinteret, String ville, String nom, JFXButton manage) {
        this.id = id;
        this.idptinteret = idptinteret;
        this.ville = ville;
        this.nom = nom;
        this.btnmanage = manage;
        this.btnlink = new JFXButton("Voir");

        btnmanage.setOnAction(e -> {

            for (PointsOfInterest pointsOfInterest: FavController.listM) {
                if (pointsOfInterest.getBtnmanage() == manage) {
                    System.out.println(""+pointsOfInterest.getIdptinteret());

                    BDDManager2 bdd = new BDDManager2();
                    bdd.start("jdbc:mysql://localhost:3306/voyage?characterEncoding=utf8", "root", "");

                    bdd.delete("DELETE FROM `avoir` WHERE `id`= " + pointsOfInterest.getId() +"");
                    bdd.stop();


                }
            }
        });
    }



}
