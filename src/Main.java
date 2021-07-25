import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.MainController;
import model.MainModel;
import view.MainView;


public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        MainModel model = new MainModel();
        MainController controller = new MainController(primaryStage, model);
        MainView view = new MainView(model, controller);


        Parent root = FXMLLoader.load(getClass().getResource("fxml/main.fxml"));
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Sample JavaFx MVC APP");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
