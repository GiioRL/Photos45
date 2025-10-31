package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import main.java.album.AlbumController;
import main.java.library.LibraryController;
import main.java.login.LoginController;

public class MainController {
    
    private static Stage primaryStage;

    private static FXMLLoader loginLoader;
    private static FXMLLoader libraryLoader;

    private static Parent loginRoot;
    private static Parent libraryRoot;

    private static LoginController loginController;
    private static LibraryController libraryController;

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        try {
            loginLoader = new FXMLLoader(getClass().getResource("login/LoginView.fxml"));
            loginRoot = loginLoader.load();
            loginController = loginLoader.getController();
            libraryLoader = new FXMLLoader(getClass().getResource("library/LibraryView.fxml"));
            libraryRoot = libraryLoader.load();
            libraryController = libraryLoader.getController();
        } catch (Exception e) {
            System.out.println("oopsie daisy");
            e.printStackTrace();
        }
        loginController.injectMainController(this);
        libraryController.injectMainController(this);

        Scene scene = new Scene(loginRoot);
        primaryStage.setTitle("Photos Application");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static Stage getStage() {
        return primaryStage;
    }

    public static LoginController getLoginController() {
        return loginController;
    }

    public static LibraryController getLibraryController() {
        return libraryController;
    }
}
