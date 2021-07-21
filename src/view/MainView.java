package view;

import javafx.scene.layout.StackPane;
import controller.MainController;
import model.MainModel;


public class MainView extends StackPane {
    private final MainModel model;
    private final MainController controller;

    public MainView(MainModel model, MainController controller) {
        this.model = model;
        this.controller = controller;

        //this.getChildren().add();

    }
}
