package model;

/**
 * @author Jerome 
 * Crée un nouvel utilisateur
 */
public class User {
    int id, autorisation, activer;
    String nom, prenom, pseudo;

    public User(int id, String nom, String prenom, String pseudo, int autorisation, int activer)  {
        this.id           = id;
        this.nom          = nom;
        this.prenom       = prenom;
        this.pseudo       = pseudo;
        this.autorisation = autorisation;
        this.activer      = activer;
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

    public int getActiver() {
        return activer;
    }
}

