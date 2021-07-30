package controller;

import BDDManager.BDDManager2;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.PointsOfInterest;
import model.Ville;


import java.io.IOException;
import java.net.URL;
import java.util.*;

public class PtInteretController implements Initializable {
    ObservableList<PointsOfInterest> list = FXCollections.observableArrayList();
    private ObservableList<Ville> villes;

    public PtInteretController() {
        villes = new ObservableList<Ville>() {
            @Override
            public void addListener(ListChangeListener<? super Ville> listener) {

            }

            @Override
            public void removeListener(ListChangeListener<? super Ville> listener) {

            }

            @Override
            public boolean addAll(Ville... elements) {
                return false;
            }

            @Override
            public boolean setAll(Ville... elements) {
                return false;
            }

            @Override
            public boolean setAll(Collection<? extends Ville> col) {
                return false;
            }

            @Override
            public boolean removeAll(Ville... elements) {
                return false;
            }

            @Override
            public boolean retainAll(Ville... elements) {
                return false;
            }

            @Override
            public void remove(int from, int to) {

            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Ville> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Ville ville) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Ville> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Ville> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Ville get(int index) {
                return null;
            }

            @Override
            public Ville set(int index, Ville element) {
                return null;
            }

            @Override
            public void add(int index, Ville element) {

            }

            @Override
            public Ville remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Ville> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Ville> listIterator(int index) {
                return null;
            }

            @Override
            public List<Ville> subList(int fromIndex, int toIndex) {
                return null;
            }

            @Override
            public void addListener(InvalidationListener listener) {

            }

            @Override
            public void removeListener(InvalidationListener listener) {

            }
        };
    }

    @FXML
    private JFXTextField txtfldid;

    @FXML
    private JFXComboBox<Ville> cmbville;

    @FXML
    private JFXTextField txtfldnom;

    @FXML
    private JFXTextField txtfldarchitecte;

    @FXML
    private JFXTextField txtfldcategorie;

    @FXML
    private JFXTextField txtfldepoque;

    @FXML
    private JFXTextField txtfldphoto1;

    @FXML
    private JFXTextField txtfldphoto3;

    @FXML
    private JFXTextField txtfldphoto2;

    @FXML
    private JFXTextArea txtareadescription;

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
            villes.add(new Ville(table_ptinteret.getSelectionModel().getSelectedItem().getVille()));
            cmbville.setItems(villes);

            txtfldnom.setText(String.valueOf(table_ptinteret.getSelectionModel().getSelectedItem().getNom()));
            txtfldepoque.setText(String.valueOf(table_ptinteret.getSelectionModel().getSelectedItem().getEpoque()));
            txtfldcategorie.setText(String.valueOf(table_ptinteret.getSelectionModel().getSelectedItem().getCategorie()));
            txtfldarchitecte.setText(String.valueOf(table_ptinteret.getSelectionModel().getSelectedItem().getArchitecte()));
            txtfldphoto1.setText(String.valueOf(table_ptinteret.getSelectionModel().getSelectedItem().getPhoto1()));
            txtfldphoto2.setText(String.valueOf(table_ptinteret.getSelectionModel().getSelectedItem().getPhoto2()));
            txtfldphoto3.setText(String.valueOf(table_ptinteret.getSelectionModel().getSelectedItem().getPhoto3()));
            txtareadescription.setText(String.valueOf(table_ptinteret.getSelectionModel().getSelectedItem().getDescription()));
        }
    }

    ObservableList<PointsOfInterest> listM;

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

    /**
     * Quand cette méthode est appelé ont enregistre ou modifie un point d'intérêt et recharge la scene
     */
    public void saveScreenButtonPushed(){
        BDDManager2 insert = new BDDManager2();

        if ( txtfldid.getText().trim().isEmpty()) {

            insert.start("jdbc:mysql://localhost:3306/voyage?characterEncoding=utf8", "root", "");
            String queryClient = ("INSERT INTO `point_interet` (`ID_pt_interet`, `nom_pt_interet`, `epoque`, `categorie`, `description_pt_interet`, `nom_architecte`, `publier`, `chemin_photo1`, `chemin_photo2`, `chemin_photo3`, `ID_ville`) " +
                    "VALUES (NULL, \"" + txtfldnom.getText() + "\", \"" + txtfldepoque.getText() + "\", \"" + txtfldcategorie.getText() + "\",\"" + txtareadescription.getText() + "\", \"" + txtfldarchitecte.getText() + "\", '0', \"" + txtfldphoto1.getText() + "\", \"" + txtfldphoto2.getText() + "\", \"" + txtfldphoto3.getText() + "\", '6' )");
            insert.insert(queryClient);
            insert.stop();


        } else {
            insert.start("jdbc:mysql://localhost:3306/voyage?characterEncoding=utf8", "root", "");
            String queryClient = ("UPDATE `point_interet` SET `nom_pt_interet` = \"" + txtfldnom.getText() + "\", `epoque` = \"" + txtfldepoque.getText() + "\", `categorie` = \"" + txtfldcategorie.getText() + "\", `description_pt_interet` = \"" + txtareadescription.getText() + "\", `nom_architecte` = \"" + txtfldarchitecte.getText() + "\", `chemin_photo1` = \"" + txtfldphoto1.getText() + "\", `chemin_photo2` = \"" + txtfldphoto2.getText() + "\", `chemin_photo3` = \"" + txtfldphoto3.getText() + "\", `ID_ville` = 6 " +
                    "WHERE `point_interet`.`ID_pt_interet` = " + txtfldid.getText() + "");
            insert.update(queryClient);
            insert.stop();

        }

        getDataPtInterest();
        table_ptinteret.refresh();

    }

    /**
     * Quand cette méthode est appelé ont publie un point d'intérêt
     */
    public void publishScreenButtonPushed() {
        BDDManager2 insert = new BDDManager2();
        insert.start("jdbc:mysql://localhost:3306/voyage?characterEncoding=utf8", "root", "");
        String queryClient = ("UPDATE `point_interet` SET `publier` = '1' WHERE `point_interet`.`ID_pt_interet` = " + txtfldid.getText() + "");
        insert.update(queryClient);
        insert.stop();

        getDataPtInterest();
        table_ptinteret.refresh();
    }


}
