package controller;

import BDDManager.BDDManager2;
import BDDManager.My_CNX;
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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.PointsOfInterest;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

public class PtInteretController implements Initializable {
    ObservableList<PointsOfInterest> list = FXCollections.observableArrayList();
    ObservableList<PointsOfInterest> list2 = FXCollections.observableArrayList();
    private ArrayList<ArrayList<String>> listeVille;
    private int idVille;

    // Create a variable to set the image path in it
    String imagePath1 = null;
    String imagePath2 = null;
    String imagePath3 = null;

    String username = "root";
    String password = "";
    String dburl = "jdbc:mysql://localhost:3306/voyage?characterEncoding=utf8";


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
    private JFXButton btnSave;

    @FXML
    private JFXButton btnpublish;

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
            PointsOfInterest dto = table_ptinteret.getSelectionModel().getSelectedItem();
            if (dto != null) {
                
                txtfldid.setText(String.valueOf(table_ptinteret.getSelectionModel().getSelectedItem().getIdptinteret()));
                txtfldnom.setText(String.valueOf(table_ptinteret.getSelectionModel().getSelectedItem().getNom()));
                txtfldepoque.setText(String.valueOf(table_ptinteret.getSelectionModel().getSelectedItem().getEpoque()));
                txtfldcategorie.setText(String.valueOf(table_ptinteret.getSelectionModel().getSelectedItem().getCategorie()));
                txtfldarchitecte.setText(String.valueOf(table_ptinteret.getSelectionModel().getSelectedItem().getArchitecte()));
                txtareadescription.setText(String.valueOf(table_ptinteret.getSelectionModel().getSelectedItem().getDescription()));
                cmbville.setValue(String.valueOf(table_ptinteret.getSelectionModel().getSelectedItem().getVille()));


                btnbrwseimg1.setDisable(false);
                btnbrwseimg2.setDisable(false);
                btnbrwseimg3.setDisable(false);

                btnpublish.setDisable(false);

                img1View.setImage(null);
                img2View.setImage(null);
                img3View.setImage(null);

                txtfldnom.setStyle("-fx-text-fill: black; -fx-font-weight : normal;");
                cmbville.setStyle("-fx-text-fill: black; -fx-font-weight : normal;");

                showImage1(table_ptinteret.getSelectionModel().getSelectedItem().getIdptinteret());
                showImage2(table_ptinteret.getSelectionModel().getSelectedItem().getIdptinteret());
                showImage3(table_ptinteret.getSelectionModel().getSelectedItem().getIdptinteret());
            }
        }
    }

    ObservableList<PointsOfInterest> listM;



    @Override
    // initializes list controller with given url
    public void initialize (URL url, ResourceBundle rb) {

        col_id.setCellValueFactory(new PropertyValueFactory<>("idptinteret"));
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


        btnbrwseimg1.setDisable(true);
        btnbrwseimg2.setDisable(true);
        btnbrwseimg3.setDisable(true);



    }

    public void fillComboBox() {
        BDDManager2 bdd = new BDDManager2();
        bdd.start("jdbc:mysql://localhost:3306/voyage?characterEncoding=utf8", "root", "");


        listeVille = bdd.select("SELECT ID_ville, nom_ville FROM ville;");
        bdd.stop();
        for (ArrayList<String> strings : listeVille) {
            cmbville.getItems().add(strings.get(1));

        }
    }

    public void citySelection() {
        for (ArrayList<String> strings : listeVille) {
            if (Objects.equals(strings.get(1), cmbville.getValue())) {

                idVille = parseInt(strings.get(0));

            }
        }
    }


    // get the list of data PointsOfInterest
    public ObservableList<PointsOfInterest> getDataPtInterest() {

        list.clear();

        BDDManager2 bdd = new BDDManager2();
        bdd.start("jdbc:mysql://localhost:3306/voyage?characterEncoding=utf8", "root", "");

        String queryPointsOfInterest = ("SELECT `ID_pt_interet`,`nom_pt_interet`,`nom_ville`,`nom_architecte`,`publier`,`categorie`,`description_pt_interet`,`epoque`,`chemin_photo1`,`chemin_photo2`,`chemin_photo3` FROM `point_interet` INNER JOIN ville ON ville.id_ville = point_interet.id_ville ORDER BY ID_pt_interet DESC");
        ArrayList<ArrayList<String>> resultatDeMaRequete = new ArrayList<>(bdd.select(queryPointsOfInterest));

        for (ArrayList<String> strings : resultatDeMaRequete) {

            list.add(new PointsOfInterest(Integer.parseInt(strings.get(0)), strings.get(1), strings.get(2), strings.get(3), Integer.parseInt(strings.get(4)), strings.get(5), strings.get(6), strings.get(7), strings.get(8), strings.get(9), strings.get(10)));
        }

        return list;
    }

    public void getLastIDPtInterest() {

        list.clear();

        BDDManager2 bdd = new BDDManager2();
        bdd.start(dburl, username, password);

        String queryPointsOfInterest = ("SELECT `ID_pt_interet` FROM `point_interet` ORDER BY ID_pt_interet DESC LIMIT 1");
        ArrayList<ArrayList<String>> resultatDeMaRequete = new ArrayList<>(bdd.select(queryPointsOfInterest));

        for (ArrayList<String> strings : resultatDeMaRequete) {

            list2.add(new PointsOfInterest(Integer.parseInt(strings.get(0))));
        }
    }

    PreparedStatement ps;
    ResultSet rs;


    public void choosePhoto1() throws IOException, SQLException {
        String queryInterest = ("UPDATE `point_interet` SET `chemin_photo1` = ? WHERE `point_interet`.`ID_pt_interet` = " + txtfldid.getText() + "");
        ps = My_CNX.getConnection().prepareStatement(queryInterest);

        // Select an image and set the image
        JFileChooser chooser = new JFileChooser();

        chooser.setCurrentDirectory(new File((System.getProperty("user.home"))));

        // file extension
        FileNameExtensionFilter extension = new FileNameExtensionFilter("Images",".jpg",".png",".jpeg");
        chooser.addChoosableFileFilter(extension);

        int filestate = chooser.showSaveDialog(null);

        // check if the user select an image
        if (filestate == JFileChooser.APPROVE_OPTION) {
            File selectedImage1 = chooser.getSelectedFile();
            imagePath1 = selectedImage1.getAbsolutePath();

            // preview of the selected image
            FileInputStream fis = new FileInputStream(selectedImage1);
            javafx.scene.image.Image image2 = new javafx.scene.image.Image(fis);
            img1View.setImage(image2);

        }
        try {
            // Save images as blob in the database
            if (imagePath1 != null) {
                InputStream image1 = new FileInputStream(imagePath1);
                ps.setBlob(1, image1);

            } else {
                ps.setNull(1, Types.NULL);
            }
            if(ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "Photo 1 ajouter");
            }

        } catch (FileNotFoundException ignored){

        }
    }

    public void choosePhoto2() throws IOException, SQLException {
        String queryInterest = ("UPDATE `point_interet` SET `chemin_photo2` = ? WHERE `point_interet`.`ID_pt_interet` = " + txtfldid.getText() + "");
        ps = My_CNX.getConnection().prepareStatement(queryInterest);

        // Select an image and set the image
        JFileChooser chooser = new JFileChooser();

        chooser.setCurrentDirectory(new File((System.getProperty("user.home"))));

        // file extension
        FileNameExtensionFilter extension = new FileNameExtensionFilter("Images",".jpg",".png",".jpeg");
        chooser.addChoosableFileFilter(extension);

        int filestate = chooser.showSaveDialog(null);

        // check if the user select an image
        if (filestate == JFileChooser.APPROVE_OPTION) {
            File selectedImage1 = chooser.getSelectedFile();
            imagePath2 = selectedImage1.getAbsolutePath();

            // preview of the selected image
            FileInputStream fis = new FileInputStream(selectedImage1);
            javafx.scene.image.Image image2 = new javafx.scene.image.Image(fis);
            img2View.setImage(image2);

        }
        try {
            // Save images as blob in the database
            if (imagePath2 != null) {
                InputStream image1 = new FileInputStream(imagePath2);
                ps.setBlob(1, image1);

            } else {
                ps.setNull(1, Types.NULL);
            }
            if(ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "Photo 2 ajouter");
            }

        } catch (FileNotFoundException ignored){

        }
    }

    public void choosePhoto3() throws IOException, SQLException {
        String queryInterest = ("UPDATE `point_interet` SET `chemin_photo3` = ? WHERE `point_interet`.`ID_pt_interet` = " + txtfldid.getText() + "");
        ps = My_CNX.getConnection().prepareStatement(queryInterest);

        // Select an image and set the image
        JFileChooser chooser = new JFileChooser();

        chooser.setCurrentDirectory(new File((System.getProperty("user.home"))));

        // file extension
        FileNameExtensionFilter extension = new FileNameExtensionFilter("Images",".jpg",".png",".jpeg");
        chooser.addChoosableFileFilter(extension);

        int filestate = chooser.showSaveDialog(null);

        // check if the user select an image
        if (filestate == JFileChooser.APPROVE_OPTION) {
            File selectedImage1 = chooser.getSelectedFile();
            imagePath3 = selectedImage1.getAbsolutePath();

            // preview of the selected image
            FileInputStream fis = new FileInputStream(selectedImage1);
            javafx.scene.image.Image image2 = new javafx.scene.image.Image(fis);
            img3View.setImage(image2);

        }
        try {
            // Save images as blob in the database
            if (imagePath3 != null) {
                InputStream image1 = new FileInputStream(imagePath3);
                ps.setBlob(1, image1);
            } else {
                ps.setNull(1, Types.NULL);
            }
            if(ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "Photo 3 ajouter");
            }

        } catch (FileNotFoundException ignored){

        }
    }

    private void showImage1(Integer idptinterest) {
        try {
            String imageQuery = ("SELECT `chemin_photo1` FROM `point_interet` WHERE `ID_pt_interet` = "+idptinterest+"");
            ps=My_CNX.getConnection().prepareStatement(imageQuery);
            System.out.println(imageQuery);

            rs = ps.executeQuery();
            if (rs.next()){
                InputStream is = rs.getBinaryStream(1);
                OutputStream os = new FileOutputStream(("photo1.jpg"));


                byte[]contents = new byte[1024];
                int size;

                if (is != null && is.available() > 1) {
                    while ((size = is.read(contents)) != -1) {
                        os.write(contents, 0, size);
                    }
                    Image image = new Image("file:photo1.jpg");

                    img1View.setImage(image);
                    btnbrwseimg1.setStyle("-fx-text-fill: black;");
                } else {
                    btnbrwseimg1.setStyle("-fx-text-fill: red; -fx-font-weight: bold");
                }
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(PtInteretController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    private void showImage2(Integer idptinterest) {
        try {
            String imageQuery = ("SELECT `chemin_photo2` FROM `point_interet` WHERE `ID_pt_interet` = "+idptinterest+"");
            ps=My_CNX.getConnection().prepareStatement(imageQuery);
            System.out.println(imageQuery);

            rs = ps.executeQuery();
            if (rs.next()){
                InputStream is = rs.getBinaryStream(1);
                OutputStream os = new FileOutputStream(("photo2.jpg"));


                byte[]contents = new byte[1024];
                int size;

                if (is != null && is.available() > 1) {
                    while ((size = is.read(contents)) != -1) {
                        os.write(contents, 0, size);
                    }
                    Image image = new Image("file:photo2.jpg");

                    img2View.setImage(image);
                }
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(PtInteretController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    private void showImage3(Integer idptinterest) {
        try {
            String imageQuery = ("SELECT `chemin_photo3` FROM `point_interet` WHERE `ID_pt_interet` = "+idptinterest+"");
            ps=My_CNX.getConnection().prepareStatement(imageQuery);
            System.out.println(imageQuery);
            OutputStream os = new FileOutputStream(("photo3.jpg"));

            rs = ps.executeQuery();
            if (rs.next()){
                InputStream is = rs.getBinaryStream(1);



                byte[]contents = new byte[1024];
                int size;

                if (is != null && is.available() > 1) {
                    while ((size = is.read(contents)) != -1) {
                        os.write(contents, 0, size);
                    }
                    Image image = new Image("file:photo3.jpg");

                    img3View.setImage(image);
                }
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(PtInteretController.class.getName()).log(Level.SEVERE,null,ex);
        }

    }

    /**
     * Quand cette méthode est appelé ont vide les champs de saisi
     */
    public void newScreenButtonPushed() {
        txtfldnom.clear();
        txtfldid.clear();
        txtfldarchitecte.clear();
        txtfldcategorie.clear();
        txtfldepoque.clear();
        txtareadescription.clear();
        cmbville.setValue(null);

        btnbrwseimg1.setDisable(true);
        btnbrwseimg2.setDisable(true);
        btnbrwseimg3.setDisable(true);

        img1View.setImage(null);
        img2View.setImage(null);
        img3View.setImage(null);

        btnpublish.setDisable(true);

        txtfldnom.setStyle("-fx-font-weight : bold;");
        cmbville.setStyle("-fx-font-weight : bold;");
        btnbrwseimg1.setStyle("-fx-text-fill: black;");

    }

    /**
     * Quand cette méthode est appelé ont enregistre ou modifie un point d'intérêt et recharge la scene
     */
    public void saveScreenButtonPushed() {

        Window owner = btnSave.getScene().getWindow();

        BDDManager2 db = new BDDManager2();

        if ( txtfldid.getText().trim().isEmpty() ) {


            if (txtfldnom.getText().isEmpty()  || cmbville.getValue() == null) {
                    showAlert(owner);

            } else {
                btnbrwseimg1.setDisable(false);
                btnbrwseimg2.setDisable(false);
                btnbrwseimg3.setDisable(false);

                btnpublish.setDisable(false);

                btnbrwseimg1.setStyle("-fx-text-fill: red; -fx-font-weight: bold");

                db.start(dburl, username, password);
                String queryInterest = ("INSERT INTO `point_interet` (`ID_pt_interet`, `nom_pt_interet`, `epoque`, `categorie`, `description_pt_interet`, `nom_architecte`, `publier`, `chemin_photo1`, `chemin_photo2`, `chemin_photo3`, `ID_ville`) " +
                        "VALUES (NULL, \"" + txtfldnom.getText() + "\", \"" + txtfldepoque.getText() + "\", \"" + txtfldcategorie.getText() + "\",\"" + txtareadescription.getText() + "\", \"" + txtfldarchitecte.getText() + "\", '0', NULL, NULL, NULL, \"" +  idVille + "\")");
                db.insert(queryInterest);
                db.stop();

                // clear first the list to avoid mistakes
                list2.clear();
                getLastIDPtInterest();


                txtfldid.setText(String.valueOf(list2.get(0).getIdptinteret()));
                //
                getDataPtInterest();
                table_ptinteret.refresh();
            }

        } else {

            if (txtfldnom.getText().isEmpty()) {
                showAlert(owner);

            } else {

                db.start(dburl, username, password);
                String queryInterest = ("UPDATE `point_interet` SET `nom_pt_interet` = \"" + txtfldnom.getText() + "\", `epoque` = \"" + txtfldepoque.getText() + "\", `categorie` = \"" + txtfldcategorie.getText() + "\", `description_pt_interet` = \"" + txtareadescription.getText() + "\", `nom_architecte` = \"" + txtfldarchitecte.getText() + "\", `ID_ville` = " + idVille + " " +
                        "WHERE `point_interet`.`ID_pt_interet` = " + txtfldid.getText() + "");
                db.update(queryInterest);
                db.stop();


                getDataPtInterest();
                table_ptinteret.refresh();
            }
        }
    }

    private static void showAlert(Window owner) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Form Error!");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez saisir un nom et choisir une ville");
        alert.initOwner(owner);
        alert.show();
    }

    private static void showAlertPhoto(Window owner) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Form Error!");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez ajouter la photo 1");
        alert.initOwner(owner);
        alert.show();
    }


    /**
     * Quand cette méthode est appelé ont publie un point d'intérêt
     */
    public void publishScreenButtonPushed() {
        Window owner = btnpublish.getScene().getWindow();

        if (img1View.getImage() == null) {
            showAlertPhoto(owner);
        }

            BDDManager2 insert = new BDDManager2();
            insert.start("jdbc:mysql://localhost:3306/voyage?characterEncoding=utf8", "root", "");
            String queryInterest = ("UPDATE `point_interet` SET `publier` = NOT publier WHERE `point_interet`.`ID_pt_interet` = " + txtfldid.getText() + " AND `chemin_photo1` is not null");
            insert.update(queryInterest);
            insert.stop();

            getDataPtInterest();
            table_ptinteret.refresh();

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

    /**
     * Quand cette méthode est appelé ont change de scene vers Points d'intérêt - Editor
     */
    public void ptInteretEditorMenuButtonPushed(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent usersParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../fxml/Editor-PtInteret.fxml")));
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

        NavController.disconnectMenuButtonPushed(actionEvent);
    }

}