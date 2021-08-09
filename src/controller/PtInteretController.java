package controller;

import BDDManager.BDDManager2;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.PointsOfInterest;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

import static java.lang.Integer.parseInt;

public class PtInteretController implements Initializable {
    ObservableList<PointsOfInterest> list = FXCollections.observableArrayList();
    ObservableList<PointsOfInterest> list2 = FXCollections.observableArrayList();
    private ArrayList<ArrayList<String>> listeVille;
    private int idVille;



    public PtInteretController() {

    }

    @FXML
    private JFXTextField txtfldid;

    @FXML
    private JFXComboBox<String> cmbville;

    @FXML
    private JFXTextField txtfldnom;

    @FXML
    private JFXTextField txtfldarchitecte;

    @FXML
    private JFXTextField txtfldcategorie;

    @FXML
    private JFXTextField txtfldepoque;

    @FXML
    private JFXTextArea txtareadescription;

    @FXML
    private JFXButton btnbrwseimg1;

    @FXML
    private JFXButton btnbrwseimg2;

    @FXML
    private JFXButton btnbrwseimg3;

    @FXML
    private ImageView img1View;

    @FXML
    private ImageView img2View;

    @FXML
    private ImageView img3View;

    @FXML
    private TableView<PointsOfInterest> table_ptinteret;

    @FXML
    private TableColumn<PointsOfInterest, Integer> col_id;

    @FXML
    private TableColumn<PointsOfInterest, String> col_nom;

    @FXML
    private TableColumn<PointsOfInterest, String> col_ville;

    @FXML
    private TableColumn<PointsOfInterest, String> col_epoque;

    @FXML
    private TableColumn<PointsOfInterest, String> col_categorie;

    @FXML
    private TableColumn<PointsOfInterest, String> col_description;

    @FXML
    private TableColumn<PointsOfInterest, String> col_architecte;

    @FXML
    private TableColumn<PointsOfInterest, Boolean> col_publier;

    @FXML
    // mouse clicked on item
    public void clickItem(MouseEvent event)
    {
        if (event.getClickCount() == 2) //Checking double click
        {
            System.out.println(table_ptinteret.getSelectionModel().getSelectedItem().getNom());
            txtfldid.setText(String.valueOf(table_ptinteret.getSelectionModel().getSelectedItem().getId()));
            txtfldnom.setText(String.valueOf(table_ptinteret.getSelectionModel().getSelectedItem().getNom()));
            txtfldepoque.setText(String.valueOf(table_ptinteret.getSelectionModel().getSelectedItem().getEpoque()));
            txtfldcategorie.setText(String.valueOf(table_ptinteret.getSelectionModel().getSelectedItem().getCategorie()));
            txtfldarchitecte.setText(String.valueOf(table_ptinteret.getSelectionModel().getSelectedItem().getArchitecte()));
            txtareadescription.setText(String.valueOf(table_ptinteret.getSelectionModel().getSelectedItem().getDescription()));
        }
    }

    ObservableList<PointsOfInterest> listM;


    private PreparedStatement store, retrieve;

    @Override
    // initializes list controller with given url
    public void initialize (URL url, ResourceBundle rb) {

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
        col_epoque.setCellValueFactory(new PropertyValueFactory<>("epoque"));
        col_categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_architecte.setCellValueFactory(new PropertyValueFactory<>("architecte"));
        col_publier.setCellValueFactory(new PropertyValueFactory<>("publier"));


        listM = getDataPtInterest();
        table_ptinteret.setItems(listM);

        fillComboBox();



        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/voyage?characterEncoding=utf8", "root", "");

            store = connection.prepareStatement(storeStatement);
            retrieve = connection.prepareStatement(retrieveStatement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void fillComboBox() {
        BDDManager2 bdd = new BDDManager2();
        bdd.start("jdbc:mysql://localhost:3306/voyage?characterEncoding=utf8", "root", "");


        listeVille = bdd.select("SELECT ID_ville, nom_ville FROM ville;");
        bdd.stop();
        for (int i =0; i < listeVille.size(); i++) {
            cmbville.getItems().addAll(listeVille.get(i).get(1));


        }



    }

    public void citySelection() {
        for (int i = 0; i < listeVille.size(); i++) {
            if (listeVille.get(i).get(1) ==  cmbville.getValue()){

                idVille = parseInt(listeVille.get(i).get(0));


            }
        }

        System.out.println(cmbville.getValue() +" " + idVille);
    }


    // get the list of data PointsOfInterest
    public ObservableList<PointsOfInterest> getDataPtInterest() {

        list.clear();

        BDDManager2 bdd = new BDDManager2();
        bdd.start("jdbc:mysql://localhost:3306/voyage?characterEncoding=utf8", "root", "");

        String queryPointsOfInterest = ("SELECT `ID_pt_interet`,`nom_pt_interet`,`nom_ville`,`nom_architecte`,`publier`,`categorie`,`description_pt_interet`,`epoque`,`chemin_photo1`,`chemin_photo2`,`chemin_photo3` FROM `point_interet` INNER JOIN ville ON ville.id_ville = point_interet.id_ville ORDER BY ID_pt_interet DESC");
        ArrayList<ArrayList<String>> resultatDeMaRequete = new ArrayList<>(bdd.select(queryPointsOfInterest));

        for (ArrayList<String> strings : resultatDeMaRequete) {


            System.out.println("test1" + strings);

            list.add(new PointsOfInterest(Integer.parseInt(strings.get(0)), strings.get(1), strings.get(2), strings.get(3), Integer.parseInt(strings.get(4)), strings.get(5), strings.get(6), strings.get(7), strings.get(8), strings.get(9), strings.get(10)));

        }

        return list;


    }

    public ObservableList<PointsOfInterest> getLastIDPtInterest() {

        list.clear();

        BDDManager2 bdd = new BDDManager2();
        bdd.start("jdbc:mysql://localhost:3306/voyage?characterEncoding=utf8", "root", "");

        String queryPointsOfInterest = ("SELECT `ID_pt_interet` FROM `point_interet` ORDER BY ID_pt_interet DESC LIMIT 1\n");
        ArrayList<ArrayList<String>> resultatDeMaRequete = new ArrayList<>(bdd.select(queryPointsOfInterest));

        for (ArrayList<String> strings : resultatDeMaRequete) {


            System.out.println("test1" + strings);

            list2.add(new PointsOfInterest(Integer.parseInt(strings.get(0))));

        }

        return list;
    }


    /**
     * Quand cette méthode est appelé ont change de scene vers Utilisateurs
     */
    public void usersMenuButtonPushed(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent usersParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../fxml/Admin-UsersList.fxml")));
        Scene usersScene = new Scene(usersParent);

        // Cette ligne récupère l'information du Stage
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(usersScene);
        window.show();
    }

    /**
     * Quand cette méthode est appelé ont change de scene vers Points d'intérêt - Editor
     */
    public void ptInteretEditorMenuButtonPushed(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent usersParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../fxml/Admin-PtInteret.fxml")));
        Scene usersScene = new Scene(usersParent);

        // Cette ligne récupère l'information du Stage
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(usersScene);
        window.show();
    }

    /**
     * Quand cette méthode est appelé ont change de scene vers Points d'intérêt - Admin
     */
    public void ptInteretAdminMenuButtonPushed(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent usersParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../fxml/Admin-PtInteret.fxml")));
        Scene usersScene = new Scene(usersParent);

        // Cette ligne récupère l'information du Stage
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(usersScene);
        window.show();
    }

    private String storeStatement = "INSERT INTO `point_interet` (`chemin_photo1`) VALUES (?)";
    private String retrieveStatement = "SELECT `chemin_photo1` FROM `point_interet` WHERE ID_pt_interet = ?";

    public void choosePhoto1() {

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(btnbrwseimg1.getScene().getWindow());
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            store.setBinaryStream(1, fileInputStream, fileInputStream.available());

            //store.execute();

            javafx.scene.image.Image image = new javafx.scene.image.Image(fileInputStream);
            img1View.setImage(image);



        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void choosePhoto2() {

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(btnbrwseimg2.getScene().getWindow());
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            store.setBinaryStream(1, fileInputStream, fileInputStream.available());

            //store.execute();

            javafx.scene.image.Image image = new javafx.scene.image.Image(fileInputStream);
            img2View.setImage(image);



        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void choosePhoto3() {

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(btnbrwseimg3.getScene().getWindow());
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            store.setBinaryStream(1, fileInputStream, fileInputStream.available());

            //store.execute();

            javafx.scene.image.Image image = new javafx.scene.image.Image(fileInputStream);
            img3View.setImage(image);



        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Quand cette méthode est appelé ont enregistre ou modifie un point d'intérêt et recharge la scene
     */
    public void saveScreenButtonPushed() throws SQLException {



        btnbrwseimg1.setOnAction(e ->{
            //Single File Selection

        });

        BDDManager2 db = new BDDManager2();

        if ( txtfldid.getText().trim().isEmpty()) {
            if (txtfldnom.getText().trim().isEmpty() || txtfldepoque.getText().trim().isEmpty() || txtfldcategorie.getText().trim().isEmpty() || txtfldarchitecte.getText().trim().isEmpty() ) {
                /**
                 * TODO ajout d'un feedback visuel avec un message invitant l'utilisateur à remplir les champs requis
                 */
            } else {

                db.start("jdbc:mysql://localhost:3306/voyage?characterEncoding=utf8", "root", "");
                String queryInterest = ("INSERT INTO `point_interet` (`ID_pt_interet`, `nom_pt_interet`, `epoque`, `categorie`, `description_pt_interet`, `nom_architecte`, `publier`, `chemin_photo1`, `chemin_photo2`, `chemin_photo3`, `ID_ville`) " +
                        "VALUES (NULL, \"" + txtfldnom.getText() + "\", \"" + txtfldepoque.getText() + "\", \"" + txtfldcategorie.getText() + "\",\"" + txtareadescription.getText() + "\", \"" + txtfldarchitecte.getText() + "\", '0', NULL, NULL, NULL, \"" +  idVille + "\")");
                db.insert(queryInterest);
                db.stop();

                getLastIDPtInterest();

                //store.execute();
                txtfldid.setText(String.valueOf(list2.get(0).getId()));
                //System.out.println(txtfldid.getText());
                getDataPtInterest();
                table_ptinteret.refresh();
            }

        } else {

            db.start("jdbc:mysql://localhost:3306/voyage?characterEncoding=utf8", "root", "");
            String queryInterest = ("UPDATE `point_interet` SET `nom_pt_interet` = \"" + txtfldnom.getText() + "\", `epoque` = \"" + txtfldepoque.getText() + "\", `categorie` = \"" + txtfldcategorie.getText() + "\", `description_pt_interet` = \"" + txtareadescription.getText() + "\", `nom_architecte` = \"" + txtfldarchitecte.getText() + "\", `ID_ville` = 6 " +
                    "WHERE `point_interet`.`ID_pt_interet` = " + txtfldid.getText() + "");
            db.update(queryInterest);
            db.stop();
            System.out.println("secnd else");

            getDataPtInterest();
            table_ptinteret.refresh();
        }



    }

    /*TODO
    Créer un bouton Nouveau
     */

    /**
     * Quand cette méthode est appelé ont publie un point d'intérêt
     */
    public void publishScreenButtonPushed() {

        if (txtfldid.getText().trim().isEmpty() ) {

            /**
             * TODO ajout d'un feedback visuel avec un message invitant l'utilisateur à selectionner une ligne du tableau
             */

        } else {

            BDDManager2 insert = new BDDManager2();
            insert.start("jdbc:mysql://localhost:3306/voyage?characterEncoding=utf8", "root", "");
            String queryInterest = ("UPDATE `point_interet` SET `publier` = NOT publier WHERE `point_interet`.`ID_pt_interet` = " + txtfldid.getText() + "");
            insert.update(queryInterest);
            insert.stop();

            getDataPtInterest();
            table_ptinteret.refresh();
        }
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