package main.java.util;

import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import main.java.album.AlbumController;
import main.java.album.albumThumbnail.AlbumThumbnailController;
import main.java.MainController;

public class Album {

    private String name;
    private User user;
    private ArrayList<Photo> photos;
    private Node thumbnail;
    private AlbumThumbnailController tc;

    private AlbumController ac;
    private Scene scene;
    private Stage primaryStage;

    public Album(User user, String name) {
        this.user = user;
        this.name = name;
        photos = new ArrayList<Photo>();
    }

    public Album(ArrayList<Photo> photos, String name, User user) {
        this.photos = photos;
        this.name = name;
        this.user = user;
        createThumbnail();
    }

    public void createThumbnail() {
        if (photos.size() == 0) {
            System.out.println("ALBUM HAS NO PHTOOS");
            return; // dont let this happen, delete album or something
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../album/albumThumbnail/albumThumbnailView.fxml"));
            thumbnail = loader.load();
            if (thumbnail == null) {
                System.out.println("why null???");
            }
            tc = loader.getController();
            tc.injectAlbum(this);
        } catch (Exception e) {
            System.out.println("o no bad thumbnail");
            e.printStackTrace();
        }
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    public Node getThumbnail() {
        return thumbnail;
    }

    public String getName() {
        return name;
    }
    
    public void start() {
        if (scene == null) {
            initScene();
        }
        // ac.injectAlbum(this);
        primaryStage.setScene(scene);
    }

    public void back() {
        createThumbnail(); //there may be better ways
        user.start();
    }

    public void delete() {
        user.getAlbums().remove(this);
    }

    private void initScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/album/AlbumView.fxml"));
            Parent root = loader.load();
            ac = loader.getController();
            ac.injectAlbum(this);
            scene = new Scene(root);
        } catch (Exception e) {
            System.out.println("album oops");
            e.printStackTrace();
        }
        primaryStage = MainController.getStage();
    }
}
