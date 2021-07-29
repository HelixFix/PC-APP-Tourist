package controller;

import BDDManager.BDDManager2;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
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


import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class FormController implements Initializable {

    @FXML
    private JFXComboBox<?> cmbville;

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
    public void clickItem(MouseEvent event)
    {
        if (event.getClickCount() == 2) //Checking double click
        {
            System.out.println(table_ptinteret.getSelectionModel().getSelectedItem().getNom());
            txtfldnom.setText(String.valueOf(table_ptinteret.getSelectionModel().getSelectedItem().getNom()));
        }
    }

    ObservableList<PointsOfInterest> listM;

    @Override
    // initializes list controller with given url
    public void initialize (URL url, ResourceBundle rb) {

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_epoque.setCellValueFactory(new PropertyValueFactory<>("epoque"));
        col_categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_architecte.setCellValueFactory(new PropertyValueFactory<>("architecte"));
        col_publier.setCellValueFactory(new PropertyValueFactory<>("publier"));


        listM = BDDManager2.getDataPtInterest();
        table_ptinteret.setItems(listM);
    }

    /**
     * Quand cette méthode est appelé ont change de scene vers Formulaire
     */
    public void formMenuButtonPushed(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent usersParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../fxml/Admin-Form.fxml")));
        Scene usersScene = new Scene(usersParent);

        // Cette ligne récupère l'information du Stage
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(usersScene);
        window.show();
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
     * Quand cette méthode est appelé ont recharge la scene
     */
    public void saveScreenButtonPushed(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent usersParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../fxml/Admin-Formv2.fxml")));
        Scene usersScene = new Scene(usersParent);

        // Cette ligne récupère l'information du Stage
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(usersScene);
        window.show();
    }
}
