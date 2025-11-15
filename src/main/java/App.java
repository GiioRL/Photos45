package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.login.LoginController;
 
public class App extends Application {

    private static Stage primaryStage;

    private static FXMLLoader loginLoader;
    private static Parent loginRoot;
    private static Scene loginScene;
    private static LoginController loginController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        App.primaryStage = primaryStage;
        try {
            loginLoader = new FXMLLoader(getClass().getResource("login/LoginView.fxml"));
            loginRoot = loginLoader.load();
            loginController = loginLoader.getController();
            loginScene = new Scene(loginRoot);
        } catch (Exception e) {
            System.out.println("oopsie daisy");
            e.printStackTrace();
        }

        primaryStage.setTitle("Photos Application");
        primaryStage.setResizable(false);
        loginController.start();
        primaryStage.show();
    }

    public static Stage getStage() {
        return primaryStage;
    }

    public static LoginController getLoginController() {
        return loginController;
    }

    public static Scene getLoginScene() {
        return loginScene;
    }
}