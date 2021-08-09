package controller;

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
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

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
        Parent usersParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../fxml/Login.fxml")));

        if (tabAdmin.isSelected()) {
            usersParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../fxml/Admin-UsersList.fxml")));
            if (chkboxeditor.isSelected()){
                usersParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../fxml/Editor-PtInteret.fxml")));
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

}
