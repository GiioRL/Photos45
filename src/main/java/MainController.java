package main.java;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import main.java.album.AlbumController2;
import main.java.library.LibraryController;
import main.java.login.LoginController;

public class MainController {
    
    private static Stage primaryStage;

    private static FXMLLoader loginLoader;
    private static FXMLLoader libraryLoader;
    private static FXMLLoader albumLoader;

    private static Parent loginRoot;
    private static Parent libraryRoot;
    private static Parent albumRoot;

    private static Scene loginScene;
    private static Scene libraryScene;
    private static Scene albumScene;

    private static LoginController loginController;
    private static LibraryController libraryController;
    private static AlbumController2 albumController;

    public void start(Stage primaryStage) { //if you make more fxmlloaders you can have multiple instances of the same scene
        this.primaryStage = primaryStage;
        try {
            loginLoader = new FXMLLoader(getClass().getResource("login/LoginView.fxml"));
            loginRoot = loginLoader.load();
            loginController = loginLoader.getController();
            loginScene = new Scene(loginRoot);
            libraryLoader = new FXMLLoader(getClass().getResource("library/LibraryView.fxml"));
            libraryRoot = libraryLoader.load();
            libraryController = libraryLoader.getController();
            libraryScene = new Scene(libraryRoot);
            albumLoader = new FXMLLoader(getClass().getResource("album/AlbumView.fxml"));
            albumRoot = albumLoader.load();
            albumController = albumLoader.getController();
            albumScene = new Scene(albumRoot);
        } catch (Exception e) {
            System.out.println("oopsie daisy");
            e.printStackTrace();
        }
        loginController.injectMainController(this);
        libraryController.injectMainController(this);

        primaryStage.setTitle("Photos Application");
        primaryStage.setScene(loginScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static Stage getStage() {
        return primaryStage;
    }

    public static AlbumController2 getAlbumController() {
        return albumController;
    }

    public static LibraryController getLibraryController() {
        return libraryController;
    }

    public static LoginController getLoginController() {
        return loginController;
    }

    public static Parent getAlbumRoot() {
        return albumRoot;
    }

    public static Parent getLibraryRoot() {
        return libraryRoot;
    }

    public static Scene getAlbumScene() {
        return albumScene;
    }

    public static Scene getLibraryScene() {
        return libraryScene;
    }
}
