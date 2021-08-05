package controller;

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

import java.io.IOException;
import java.util.Objects;

public class LoginController {

    @FXML
    private TabPane tabPaneLogin;

    @FXML
    private Tab tabAdmin;

    @FXML
    private Tab tabUser;

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

    }

    @FXML
    void openUserTab(MouseEvent event) {

    }

    public void loginScreenButtonPushed(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent usersParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../fxml/Admin-UsersList.fxml")));
        Scene usersScene = new Scene(usersParent);

        // Cette ligne récupère l'information du Stage
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(usersScene);
        window.show();
    }

}
