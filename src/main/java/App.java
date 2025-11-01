package main.java;

import javafx.application.Application;
import javafx.stage.Stage;
 
public class App extends Application { //not sure why album thumbnail isn't centered and whats going on with the date

    @Override
    public void start(Stage primaryStage) {
        MainController mc = new MainController();
        mc.start(primaryStage);
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}