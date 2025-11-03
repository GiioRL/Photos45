package util;

import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.LibraryController;
import main.App;

public class User {
    
    private String username = "";
    private String password = "";
    private LibraryController libraryController = new LibraryController();
    private Stage primaryStage;
    private static ArrayList<User> users = new ArrayList<>();

    static {
        users.add(new Admin());
        users.add(new Stock());
    }

    public User() {}

    public User(String username, String password) throws Exception {
        for (User user: users)
            if (user.username.equals(username))
                throw new Exception("User already exists with that username");
        this.username = username;
        this.password = password;
        users.add(this);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean equals(Object other) {
        if (!(other instanceof User)) {
            return false;
        }
        return (username.equals(((User)other).getUsername()) && password.equals(((User)other).getPassword()));
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public void start() {
        primaryStage = App.getStage();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/library/LibraryView.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        } catch (Exception e) {
            System.out.println("err there was an exception");
            e.printStackTrace();
        }
    }
}
