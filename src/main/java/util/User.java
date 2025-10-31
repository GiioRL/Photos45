package main.java.util;

import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.album.AlbumModel;
import main.java.library.LibraryController;
import main.java.App;

public class User { //maybe this gets split into controller and model?
    
    protected String username = "";
    protected String password = "";
    protected LibraryController libraryController = new LibraryController();
    protected ArrayList<Photo> photos = new ArrayList<Photo>();
    protected ArrayList<AlbumModel> albums = new ArrayList<AlbumModel>();
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

    // protected void addPhoto(String location) {
    //     libraryController.method();
    // }

    public void start() {
        primaryStage = App.getStage();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/main/java/library/LibraryView.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        } catch (Exception e) {
            System.out.println("err there was an exception");
            e.printStackTrace();
        }
    }
}
