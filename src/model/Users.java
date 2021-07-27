package model;

public class Users {
    int id, autorisation;
    String nom, prenom, pseudo;
    Boolean activer;

    public Users(int id, int autorisation, String nom) {
        this.id = id;
        this.autorisation = autorisation;
        this.nom = nom;
        //this.prenom = prenom;
        //this.pseudo = pseudo;
        //this.activer = activer;
    }

    public int getId() {
        return id;
    }

    public int getAutorisation() {
        return autorisation;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public Boolean getActiver() {
        return activer;
    }
}

