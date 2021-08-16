package controller;

import BDDManager.My_CNX;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController {

    @FXML
    private TabPane tabPaneLogin;

    @FXML
    private Tab tabAdmin;

    @FXML
    private JFXPasswordField txtfldadminpassword;

    @FXML
    private JFXTextField txtfldadminusername;

    @FXML
    private JFXCheckBox chkboxeditor;

    @FXML
    private Tab tabUser;

    @FXML
    private JFXPasswordField txtflduserpassword;

    @FXML
    private JFXTextField txtflduserusername;

    @FXML
    private Label lblCreateAccount;

    @FXML
    private Pane slidingPane;

    @FXML
    private Label lblAdmin;

    @FXML
    private Label lblUser;

    @FXML
    private Label lblStatus;


    @FXML
    private JFXButton btncnx;

    @FXML
    void openAdminTab(MouseEvent event) {
        // Open admin tab when admin label is clicked
        TranslateTransition toLeftTransition = new TranslateTransition(new Duration(500), lblStatus);
        toLeftTransition.setToX(slidingPane.getTranslateX());
        toLeftTransition.play();
        toLeftTransition.setOnFinished((ActionEvent event2)->{
            lblStatus.setText("ADMINISTRATEUR");
        });
        tabPaneLogin.getSelectionModel().select(tabAdmin);

    }

    @FXML
    void openUserTab(MouseEvent event) {
        // Using translation animation to slide label left and right
        TranslateTransition toRightAnimation = new TranslateTransition(new Duration(500), lblStatus);
        // ugly fix
        toRightAnimation.setToX(slidingPane.getTranslateX()+(slidingPane.getPrefWidth()-lblStatus.getPrefWidth()));
        toRightAnimation.play();
        toRightAnimation.setOnFinished((ActionEvent event1) -> {
            lblStatus.setText("UTILISATEUR");
        });
        // Set selected tab as Utilisateur
        tabPaneLogin.getSelectionModel().select(tabUser);

    }



    public void loginScreenButtonPushed(javafx.event.ActionEvent actionEvent) throws IOException {
        Window owner = btncnx.getScene().getWindow();
        PreparedStatement st;
        ResultSet rs;

        Parent usersParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../fxml/Login.fxml")));

        if (tabAdmin.isSelected()) {
            // get the username & password
            String editorUsername = txtfldadminusername.getText();
            String editorPassword = String.valueOf(txtfldadminpassword.getText());

            if (chkboxeditor.isSelected()){
                // Create a select query to check if the username and the password exist in the database
                String editorQuery = "SELECT * FROM `utilisateur` WHERE BINARY `pseudo` = ? AND BINARY `mot_de_passe` = ? AND `droit_acces` = 1";

                try {
                    st = My_CNX.getConnection().prepareStatement(editorQuery);

                    st.setString(1, editorUsername);
                    st.setString(2,editorPassword);
                    rs = st.executeQuery();

                    if (rs.next()) {
                        // show a new form
                        usersParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../fxml/Editor-PtInteret.fxml")));
                    } else {
                        // error message
                        if (txtfldadminusername.getText().isEmpty()) {
                            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                                    "Veuillez entrer votre pseudo");
                            return;
                        } if (txtfldadminpassword.getText().isEmpty()) {
                            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                                    "Veuillez entrer votre mot de passe");
                            return;
                        }
                        showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                                "Pseudo ou mot de passe incorrect");
                        return;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE,null, ex);
                }

            } else {
                // get the username & password
                String adminUsername = txtfldadminusername.getText();
                String adminPassword = String.valueOf(txtfldadminpassword.getText());

                // Create a select query to check if the username and the password exist in the database
                String query = "SELECT * FROM `utilisateur` WHERE BINARY `pseudo` = ? AND BINARY `mot_de_passe` = ? AND `droit_acces` = 0";

                try {
                    st = My_CNX.getConnection().prepareStatement(query);

                    st.setString(1, adminUsername);
                    st.setString(2,adminPassword);
                    rs = st.executeQuery();

                    if (rs.next()) {
                        // show a new form
                        usersParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../fxml/Admin-UsersList.fxml")));
                    } else {
                        // error message
                        if (txtfldadminusername.getText().isEmpty()) {
                            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                                    "Veuillez entrer votre pseudo");
                            return;
                        } if (txtfldadminpassword.getText().isEmpty()) {
                            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                                    "Veuillez entrer votre mot de passe");
                            return;
                        }
                        showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                                "Pseudo ou mot de passe incorrect");
                        return;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE,null, ex);
                }
            }

        } else if (tabUser.isSelected()) {
            usersParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../fxml/User-City.fxml")));
        }

        Scene usersScene = new Scene(usersParent);

        // Cette ligne récupère l'information du Stage
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(usersScene);
        window.show();
    }
    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }


}
