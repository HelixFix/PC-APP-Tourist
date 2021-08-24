package controller;

import BDDManager.BDDManager2;
import BDDManager.My_CNX;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import model.Data;
import model.PointsOfInterest;


import java.io.*;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CityDetailsController implements Initializable {
    ObservableList<PointsOfInterest> list = FXCollections.observableArrayList();

    @FXML
    private TableView<PointsOfInterest> table_ptinteret;

    @FXML
    private TableColumn<PointsOfInterest, Integer> col_id;

    @FXML
    private TableColumn<PointsOfInterest, String> col_ville;

    @FXML
    private Text title;

    @FXML
    private JFXTextArea resume;

    @FXML
    private Text title2;

    @FXML
    private Text epoque;

    @FXML
    private Text categorie;

    @FXML
    private Text architecte;

    @FXML
    private Text id;

    @FXML
    private Label lblcategorie;

    @FXML
    private Label lblepoque;

    @FXML
    private Pane rightsidepane;

    @FXML
    private Pane titlepane;

    @FXML
    private Line line;

    @FXML
    private JFXButton btnfav;

    @FXML
    private ImageView img1View;

    @FXML
    private ImageView img2View;

    @FXML
    private ImageView img3View;

    @FXML
    // mouse listener for table point d'intérêt
    public void clickItem(MouseEvent event)
    {
        if (event.getClickCount() == 1) //Checking click
        {

            PointsOfInterest dto = table_ptinteret.getSelectionModel().getSelectedItem();
            if (dto != null) {
                //Do my processing
                
                id.setText(String.valueOf(table_ptinteret.getSelectionModel().getSelectedItem().getIdptinteret()));
                title.setText(table_ptinteret.getSelectionModel().getSelectedItem().getNom());
                title2.setText(table_ptinteret.getSelectionModel().getSelectedItem().getNom());
                resume.setText(String.valueOf(table_ptinteret.getSelectionModel().getSelectedItem().getDescription()));
                epoque.setText(table_ptinteret.getSelectionModel().getSelectedItem().getEpoque());
                categorie.setText(table_ptinteret.getSelectionModel().getSelectedItem().getCategorie());


                // When point of interest selected show text
                title.setVisible(true);
                title2.setVisible(true);
                resume.setVisible(true);
                epoque.setVisible(true);
                categorie.setVisible(true);
                btnfav.setVisible(true);

                lblcategorie.setVisible(true);
                lblepoque.setVisible(true);

                line.setVisible(true);
                titlepane.setVisible(true);
                rightsidepane.setVisible(true);

                showImage1(table_ptinteret.getSelectionModel().getSelectedItem().getIdptinteret());

            }

        }
    }

    ObservableList<PointsOfInterest> listM;

    @Override
    // initializes list controller with given url
    public void initialize(URL url, ResourceBundle rb) {

        // liaison entre la vue et le modele
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_ville.setCellValueFactory(new PropertyValueFactory<>("nom"));


        if (Data.idPtInterest > 0) {
            listM = getOnePtInterest();

            id.setText(String.valueOf(listM.get(0).getIdptinteret()));
            title.setText(listM.get(0).getNom());
            title2.setText(listM.get(0).getNom());
            resume.setText(listM.get(0).getDescription());
            epoque.setText(listM.get(0).getEpoque());
            categorie.setText(listM.get(0).getCategorie());


            title.setVisible(true);
            title2.setVisible(true);
            resume.setVisible(true);
            epoque.setVisible(true);
            categorie.setVisible(true);
            btnfav.setVisible(true);

            lblcategorie.setVisible(true);
            lblepoque.setVisible(true);

            line.setVisible(true);
            titlepane.setVisible(true);
            rightsidepane.setVisible(true);

            showImage1(Data.idPtInterest);
        } else {
            listM = getDataPtInterest();
        }

        table_ptinteret.setItems(listM);
    }

    // get the list of data city
    public ObservableList<PointsOfInterest> getDataPtInterest() {

        list.clear();

        BDDManager2 bdd = new BDDManager2();
        bdd.start("jdbc:mysql://localhost:3306/voyage?characterEncoding=utf8", "root", "");

        // If user choose Roma city show the list of activated point of interest
        if (Objects.equals(Data.cityName, "Roma")) {
            String queryPointsOfInterest = ("SELECT `ID_pt_interet`,`nom_pt_interet`,`nom_ville`,`nom_architecte`,`publier`,`categorie`,`description_pt_interet`,`epoque`,`chemin_photo1`,`chemin_photo2`,`chemin_photo3` FROM `point_interet` INNER JOIN ville ON ville.id_ville = point_interet.id_ville WHERE `nom_ville` = 'Rome' AND `publier` = 1 ORDER BY nom_ville");

            ArrayList<ArrayList<String>> resultatDeMaRequete = new ArrayList<>(bdd.select(queryPointsOfInterest));

            for (ArrayList<String> strings : resultatDeMaRequete) {


                

                list.add(new PointsOfInterest(Integer.parseInt(strings.get(0)), strings.get(1), strings.get(2), strings.get(3), Integer.parseInt(strings.get(4)), strings.get(5), strings.get(6), strings.get(7), strings.get(8), strings.get(9), strings.get(10)));

            }
        }
        // If user choose Beijing city show the list of activated point of interest
        if (Objects.equals(Data.cityName, "Beijing")) {
            String queryPointsOfInterest = ("SELECT `ID_pt_interet`,`nom_pt_interet`,`nom_ville`,`nom_architecte`,`publier`,`categorie`,`description_pt_interet`,`epoque`,`chemin_photo1`,`chemin_photo2`,`chemin_photo3` FROM `point_interet` INNER JOIN ville ON ville.id_ville = point_interet.id_ville WHERE `nom_ville` = 'Pékin' AND `publier` = 1 ORDER BY nom_ville");

            ArrayList<ArrayList<String>> resultatDeMaRequete = new ArrayList<>(bdd.select(queryPointsOfInterest));

            for (ArrayList<String> strings : resultatDeMaRequete) {


                

                list.add(new PointsOfInterest(Integer.parseInt(strings.get(0)), strings.get(1), strings.get(2), strings.get(3), Integer.parseInt(strings.get(4)), strings.get(5), strings.get(6), strings.get(7), strings.get(8), strings.get(9), strings.get(10)));

            }
        }
        // If user choose grenada city show the list of activated point of interest
        if (Objects.equals(Data.cityName, "Granada")) {
            String queryPointsOfInterest = ("SELECT `ID_pt_interet`,`nom_pt_interet`,`nom_ville`,`nom_architecte`,`publier`,`categorie`,`description_pt_interet`,`epoque`,`chemin_photo1`,`chemin_photo2`,`chemin_photo3` FROM `point_interet` INNER JOIN ville ON ville.id_ville = point_interet.id_ville WHERE `nom_ville` = 'Grenade' AND `publier` = 1 ORDER BY nom_ville");

            ArrayList<ArrayList<String>> resultatDeMaRequete = new ArrayList<>(bdd.select(queryPointsOfInterest));

            for (ArrayList<String> strings : resultatDeMaRequete) {


                

                list.add(new PointsOfInterest(Integer.parseInt(strings.get(0)), strings.get(1), strings.get(2), strings.get(3), Integer.parseInt(strings.get(4)), strings.get(5), strings.get(6), strings.get(7), strings.get(8), strings.get(9), strings.get(10)));

            }
        }


        return list;
    }

    // get the data of one point of interest
    public ObservableList<PointsOfInterest> getOnePtInterest() {

        list.clear();

        BDDManager2 bdd = new BDDManager2();
        bdd.start("jdbc:mysql://localhost:3306/voyage?characterEncoding=utf8", "root", "");

        String queryPointsOfInterest = ("SELECT `ID_pt_interet`,`nom_pt_interet`,`nom_ville`,`nom_architecte`,`publier`,`categorie`,`description_pt_interet`,`epoque`,`chemin_photo1`,`chemin_photo2`,`chemin_photo3` FROM `point_interet` INNER JOIN ville ON ville.id_ville = point_interet.id_ville WHERE ID_pt_interet = " + Data.idPtInterest + "");
        ArrayList<ArrayList<String>> resultatDeMaRequete = new ArrayList<>(bdd.select(queryPointsOfInterest));

        for (ArrayList<String> strings : resultatDeMaRequete) {


            

            list.add(new PointsOfInterest(Integer.parseInt(strings.get(0)), strings.get(1), strings.get(2), strings.get(3), Integer.parseInt(strings.get(4)), strings.get(5), strings.get(6), strings.get(7), strings.get(8), strings.get(9), strings.get(10)));


        }

        return list;
    }

    PreparedStatement ps;
    ResultSet rs;

    private void showImage1(Integer idptinterest) {
        try {

            ps= My_CNX.getConnection().prepareStatement("SELECT `chemin_photo1` FROM `point_interet` WHERE `ID_pt_interet` = "+idptinterest+"");
            System.out.println(ps);

            rs = ps.executeQuery();
            if (rs.next()){
                InputStream is = rs.getBinaryStream(1);
                OutputStream os = new FileOutputStream(new File("photo1.jpg"));


                byte[]contents = new byte[1024];
                int size;
                if (is != null && is.available() > 1) {
                    while ((size = is.read(contents)) != -1) {
                        os.write(contents, 0, size);
                    }
                    os.close();
                    is.close();
                    Image image = new Image("file:photo1.jpg");

                    img1View.setImage(image);
                }
            }
            ps.close();
            rs.close();
        } catch (SQLException | IOException ex) {
            Logger.getLogger(PtInteretController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    public void favScreenButtonPushed(){
        BDDManager2 bdd = new BDDManager2();
        bdd.start("jdbc:mysql://localhost:3306/voyage?characterEncoding=utf8", "root", "");

        String queryFav = ("INSERT INTO avoir (id_utilisateur, ID_pt_interet) SELECT id_utilisateur, ID_pt_interet FROM utilisateur, point_interet WHERE nom_utilisateur = \"" + Data.username + "\" AND ID_pt_interet = " + id.getText() + "");
        bdd.insert(queryFav);
        bdd.stop();
    }


    /**
     * Quand cette méthode est appelé ont change de scene vers Villes
     */
    public void cityMenuButtonPushed(javafx.event.ActionEvent actionEvent) throws IOException {

        NavController.cityMenuButtonPushed(actionEvent);
    }

    /**
     * Quand cette méthode est appelé ont change de scene vers Favoris
     */
    public void favMenuButtonPushed(javafx.event.ActionEvent actionEvent) throws IOException {

        NavController.favMenuButtonPushed(actionEvent);
    }

    /**
     * Quand cette méthode est appelé ont change de scene vers Connexion
     */
    public void disconnectMenuButtonPushed(javafx.event.ActionEvent actionEvent) throws IOException {

        NavController.disconnectMenuButtonPushed(actionEvent);
    }
}