package BDDManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.PointsOfInterest;
import model.Users;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

/**
 Created by cladlink on 12/03/16.
 */

public class BDDManager2 {

    //private final String BDD_URL = "jdbc:mysql://localhost:3306/dvdtheque";
    //private final String BDD_USER = "root";
    //private final String BDD_PASSWORD =  "";
    private Connection connection;
    private Statement statement;

    /**
     * main
     * ce main n'est utiliser que pour créer les tables.
     */
    public static void main(String[] args) {
        BDDManager2 bdd = new BDDManager2();

        bdd.start("jdbc:mysql://localhost:3306/?characterEncoding=utf8", "root", "");
        bdd.lire("src/BDDManager/tourist.sql");
        bdd.stop();
    }

    /**
     * start()
     * sert à initialiser la connexion à la BDD
     */
    public void start(String url, String user, String password) {
        try {
            connection = DriverManager.getConnection (url ,user, password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * stop()
     * Sert à rompre la connexion avec le BDD
     */
    public void stop() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * lire()
     * permet de lire un fichier sql et de l'exécuter
     */
    public void lire(String adressSQLFile)
    {
        BufferedReader lecture;
        String fichier = "", fichierTemp;
        String[] requete;
        try
        {
            lecture = new BufferedReader(new FileReader(adressSQLFile));
            try
            {
                while ((fichierTemp = lecture.readLine()) != null)
                {
                    fichier += fichierTemp;
                    fichier += " ";
                }
                requete = fichier.split(";");
                for (int i = 0; i<requete.length; i++) {
                    requete[i] += ";";
                    System.out.println(i);
                    System.out.println(requete[i]);
                    if (requete[i].contains("SELECT")) {
                        select(requete[i]);
                    } else if (requete[i].contains("INSERT")) {
                        insert(requete[i]);
                    } else if (requete[i].contains("DELETE")) {
                        delete(requete[i]);
                    } else if (requete[i].contains("UPDATE")) {
                        update(requete[i]);
                    } else {
                        autreRequete(requete[i]);
                    }
                }
            }
            catch (EOFException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    lecture.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

        }
        catch (FileNotFoundException e)
        {
            System.err.println("le fichier est introuvable");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }



    public boolean autreRequete(String maRequete) {
        return executeMaRequete(maRequete);
    }

    public boolean insert(String maRequete) {

        return executeMaRequete(maRequete);
    }

    public ArrayList<ArrayList<String>> select(String maRequete) {
        System.out.println(maRequete);
        ArrayList<ArrayList<String>> resultatDeLaRequete = new ArrayList<>();

        try
        {
            ResultSet rs = statement.executeQuery(maRequete); // execution de la requete de type select
            ResultSetMetaData rsmd = rs.getMetaData(); // ca rend le truc lisible
            int nbcols = rsmd.getColumnCount(); // ici on récupére le nombre de lignes

            int i=0;
            while(rs.next()) // tant qu'il y a des lignes à lire
            {
                resultatDeLaRequete.add(new ArrayList<>()); // je crée une arraylist ...
                for (int j = 1; j <= nbcols; j++)
                    resultatDeLaRequete.get(i).add(rs.getString(j)); // ...
                i++;
            }
        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return resultatDeLaRequete;
    }

    public boolean update(String maRequete) {
        return executeMaRequete(maRequete);
    }

    public boolean delete(String maRequete) {

        return executeMaRequete(maRequete);
    }

    public boolean executeMaRequete(String maRequete){

        System.out.println(maRequete);
        try
        {
            statement.executeUpdate(maRequete);
            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }


    // get the list of data users
    public static ObservableList<Users> getDataUsers() {

        BDDManager2 bdd = new BDDManager2();
        bdd.start("jdbc:mysql://localhost:3306/voyage?characterEncoding=utf8", "root", "");
        ObservableList<Users> list = FXCollections.observableArrayList();
        String queryUsers = ("SELECT `ID_utilisateur`,`nom_utilisateur`,`prenom`,`pseudo`,`droit_acces`,`activer` FROM `utilisateur`");
        ArrayList<ArrayList<String>> resultatDeMaRequete = new ArrayList<>(bdd.select(queryUsers));

        for (int i = 0; i < resultatDeMaRequete.size(); i++) {


            System.out.println("test1" + resultatDeMaRequete.get(i));

            list.add(new Users(Integer.parseInt(resultatDeMaRequete.get(i).get(0)), resultatDeMaRequete.get(i).get(1), resultatDeMaRequete.get(i).get(2), resultatDeMaRequete.get(i).get(3), Integer.parseInt(resultatDeMaRequete.get(i).get(4)), Integer.parseInt(resultatDeMaRequete.get(i).get(5))));

        }

        return list;
    }

    // get the list of data PointsOfInterest
    public static ObservableList<PointsOfInterest> getDataPtInterest() {

        BDDManager2 bdd = new BDDManager2();
        bdd.start("jdbc:mysql://localhost:3306/voyage?characterEncoding=utf8", "root", "");
        ObservableList<PointsOfInterest> list = FXCollections.observableArrayList();
        String queryPointsOfInterest = ("SELECT `ID_pt_interet`,`nom_pt_interet`,`nom_architecte`,`publier`,`categorie`,`description_pt_interet`,`epoque`,`chemin_photo1`,`chemin_photo2`,`chemin_photo3` FROM `point_interet`");
        ArrayList<ArrayList<String>> resultatDeMaRequete = new ArrayList<>(bdd.select(queryPointsOfInterest));

        for (int i = 0; i < resultatDeMaRequete.size(); i++) {


            System.out.println("test1" + resultatDeMaRequete.get(i));

            list.add(new PointsOfInterest(Integer.parseInt(resultatDeMaRequete.get(i).get(0)), resultatDeMaRequete.get(i).get(1), resultatDeMaRequete.get(i).get(2), Integer.parseInt(resultatDeMaRequete.get(i).get(3)), resultatDeMaRequete.get(i).get(4), resultatDeMaRequete.get(i).get(5),resultatDeMaRequete.get(i).get(6),resultatDeMaRequete.get(i).get(7),resultatDeMaRequete.get(i).get(8),resultatDeMaRequete.get(i).get(9)));

        }

        return list;
    }
}


