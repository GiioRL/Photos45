package main.java.util;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.stage.Stage;

import main.java.library.LibraryController;
import main.java.MainController;

public class User { // this might become library model
    
    protected String username = "";
    protected String password = "";
    protected static LibraryController lc; //maybe private
    protected ArrayList<Photo> photos = new ArrayList<Photo>();
    protected ArrayList<Album> albums = new ArrayList<Album>();
    private static Stage primaryStage;
    private static Scene scene;

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

    public void addAlbum(Album album) {
        albums.add(album);
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void start() {
        if (scene == null) {
            initScene();
        }
        lc.injectUser(this);
        primaryStage.setScene(scene);
    }

    private void initScene() {
        primaryStage = MainController.getStage();
        lc = MainController.getLibraryController();
        scene = MainController.getLibraryScene();
    }
}
