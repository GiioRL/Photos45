package main.java.util;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import main.java.library.LibraryController;
import main.java.App;

public class User implements Serializable { // this might become library model
    
    protected String username = "";
    protected String password = "";
    protected ArrayList<Photo> photos = new ArrayList<Photo>();
    protected ArrayList<Album> albums = new ArrayList<Album>();
    private static ArrayList<User> users = new ArrayList<User>();
    protected ArrayList<Tag> tags = new ArrayList<Tag>(); // tags should have a (Type, null) for every (Type, Value)

    protected transient LibraryController lc;
    private transient Stage primaryStage;
    private transient Scene scene;

    // static {
    //     users.add(new Admin());
    //     users.add(new Stock());
    // }

    public User() {}

    public User(String username, String password) throws Exception {
        if (User.exists(username)) {
            throw new Exception("User already exists with that username.");
        }
        this.username = username;
        this.password = password;
        users.add(this);
        tags.add(new Tag("Location", null));
        tags.add(new Tag("Person", null));
    }

    public void addTag(Tag newTag) { // only checks the tag type
        if (tags.contains(newTag)) { // type exists in tags
            boolean bool = false;
            for (Tag tag: tags) {
                bool |= tag.tagEquals(newTag);
            }
            if (bool) {
                tags.add(newTag);
            }
        }
        tags.add(new Tag(newTag.getType(), null));
        if (newTag.getValue() != null) {
            tags.add(newTag);
        }        
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
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

    public LibraryController getLC() {
        return lc;
    }

    public void start() {
        if (scene == null) {
            initScene();
        }
        lc.start();
        primaryStage.setScene(scene);
    }

    private void initScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/library/LibraryView.fxml"));
            Parent root = loader.load();
            lc = loader.getController();
            lc.injectUser(this);
            scene = new Scene(root);
        } catch (Exception e) {
            System.out.println("user oops");
            e.printStackTrace();
        }
        primaryStage = App.getStage();
    }

    public static void initClass() {
        users.add(new Admin());
        users.add(new Stock());
    }

    public static void resetClass() {
        users.clear();
        File saveFolder = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "data" + File.separator);
        File[] files = saveFolder.listFiles();
        for (File file: files) {
            if (!file.delete()) {
                System.out.println("Failed to delete file: " + file.getAbsolutePath());
            }
        }
        initClass();
    }

    public static void restoreClass() {
        File saveFolder = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "data" + File.separator);
        File adminFile = new File(saveFolder + File.separator + "admin.dat");
        File stockFile = new File(saveFolder + File.separator + "stock.dat");
        if (adminFile.delete()) {
            users.remove(new Admin());   
        } else {
            System.out.println("Failed to delete admin file");
        }
        if (stockFile.delete()) {
            users.remove(new Stock());
        } else {
            System.out.println("Failed to delete stock file");
        }
        initClass();
    }

    public void save(String folder) throws Exception {
        File file = new File(folder + File.separator + username + ".dat");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.close();
    }

    public static void load(File file) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        User user = (User)(ois.readObject());
        users.add(user);
        ois.close();
    }

    public String toString() {
        return "User " + username;
    }
}
