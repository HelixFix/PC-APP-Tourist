package model;

public class PointsOfInterest {
    Integer id, publier;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPublier() {
        return publier;
    }

    public void setPublier(Integer publier) {
        this.publier = publier;
    }

    public PointsOfInterest(Integer id) {
        this.id = id;
    }

    public PointsOfInterest(Integer id, String nom, String ville, String architecte, Integer publier, String categorie, String description, String epoque, String photo1, String photo2, String photo3) {
        this.id          = id;
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

    public PointsOfInterest(Integer id, String ville, String nom) {
        this.id = id;
        this.ville = ville;
        this.nom = nom;
    }
}
