import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;


public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/login.fxml")));
        Scene scene = new Scene(root, 1280, 853);
        primaryStage.setTitle("Application touristique - Stage Two");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}