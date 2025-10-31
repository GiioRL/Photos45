package main.java;

import javafx.application.Application;
import javafx.stage.Stage;
 
public class App extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) {
        MainController mc = new MainController();
        mc.start(primaryStage);
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}