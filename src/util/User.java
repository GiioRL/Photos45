package util;

import album.AlbumController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import main.App;

public class User {
    
    private String username = "";
    private String password = "";
    private AlbumController albumController = new AlbumController();
    private Stage primaryStage;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
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

    public void start() {
        primaryStage = App.getStage();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/album/AlbumView.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        } catch (Exception e) {
            System.out.println("err there was an exception");
            e.printStackTrace();
        }
    }
}
