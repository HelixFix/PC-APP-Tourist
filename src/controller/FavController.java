package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import model.Data;

import java.net.URL;
import java.util.ResourceBundle;

public class FavController implements Initializable {

    @FXML
    private Text name;

    @FXML
    void cityMenuButtonPushed(ActionEvent event) {

    }

    @FXML
    void disconnectMenuButtonPushed(ActionEvent event) {

    }

    @FXML
    void favMenuButtonPushed(ActionEvent event) {

    }

    @Override
    public void initialize (URL url, ResourceBundle rb) {
        if (Data.username.equals("")) {

        } else {
            name.setText(Data.username);
        }
    }



}

