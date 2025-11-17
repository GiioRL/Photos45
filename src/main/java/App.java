package main.java;

import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.login.LoginController;
import main.java.util.User;
 
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
            loadData();
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
    
    public static void quit() {
        saveData();
        System.exit(0);
    }

    private static void initApp() {
        User.initClass();
    }

    public static void saveData() {
        ArrayList<User> users = User.getUsers();
        String saveFolder = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "data" + File.separator;
        for (User user: users) {
            try {
                user.save(saveFolder);
            } catch (Exception e) {
                System.out.println("saving error :/");
                e.printStackTrace();
            }
        }
    }

    public static void loadData() {
        File saveFolder = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "data" + File.separator);
        File[] files = saveFolder.listFiles();
        if (files != null) {
            if (files.length == 0) {
                initApp();
            } else {
                for (File file: files) {
                    try {
                        User.load(file);
                    } catch (Exception e) {
                        System.out.println("loading error :/");
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}