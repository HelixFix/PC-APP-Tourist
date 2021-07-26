import BDDManager.BDDManager2;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        BDDManager2 bdd = new BDDManager2();
        bdd.start("jdbc:mysql://localhost:3306/tourist?characterEncoding=utf8", "root", "");

        Parent root = FXMLLoader.load(getClass().getResource("fxml/main.fxml"));
        Scene scene = new Scene(root, 1280, 853);
        primaryStage.setTitle("Application touristique - Stage Two");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
