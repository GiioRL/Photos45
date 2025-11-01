package main.java.util;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.stage.Stage;

import main.java.album.AlbumController;
import main.java.MainController;

public class Album {

    private ArrayList<Photo> photos;
    private static AlbumController ac;
    private static Scene scene;
    private static Stage primaryStage;
    private User user;
    private int count = 0;

    public Album(User user) {
        this.user = user;
    }

    public Album(ArrayList<Photo> photos) {
        this.photos = photos;
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
    }
    
    public void start() {
        count++;
        if (scene == null) {
            System.out.println("scene is empty!!" + count);
            initScene();
        }
        ac.injectUser(user); //this may not be necessary
        ac.injectAlbum(this);
        primaryStage.setScene(scene);
    }

    private void initScene() {
        primaryStage = MainController.getStage();
        ac = MainController.getAlbumController();
        scene = MainController.getAlbumScene();
    }
}
