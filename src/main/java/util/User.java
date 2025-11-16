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
    private static ArrayList<User> users = new ArrayList<User>();
    protected ArrayList<Tag> tags = new ArrayList<Tag>();

    static {
        users.add(new Admin());
        users.add(new Stock());
    }

    public User() {}

    public User(String username, String password) throws Exception {
        if (User.exists(username)) {
            throw new Exception("User already exists with that username.");
        }
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

    public ArrayList<Tag> getTags() {
        return tags;
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

    public static boolean exists(String username) {
        return getUser(username) != null;
    }

    public static boolean exists(String username, String password) {
        return getUser(username, password) != null;
    }

    public static User getUser(String username) {
        for (User user: users) {
            if (user.username.equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static User getUser(String username, String password) {
        for (User user: users) {
            if (user.username.equals(username) && user.password.equals(password)) {
                return user;
            }
        }
        return null;
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
