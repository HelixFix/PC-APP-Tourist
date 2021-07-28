package model;

public class PointsOfInterest {
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

    public PointsOfInterest(String ville, String nom, String architecte, String categorie, String epoque, String photo1, String photo2, String photo3, String description) {
        this.ville = ville;
        this.nom = nom;
        this.architecte = architecte;
        this.categorie = categorie;
        this.epoque = epoque;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.photo3 = photo3;
        this.description = description;
    }
}
