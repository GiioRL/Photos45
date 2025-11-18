package model;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.App;
import view.album.AlbumViewController;
import view.album.albumThumbnail.AlbumThumbnailViewController;

public class Album implements Serializable {

    private String name;
    private User user;
    private ArrayList<Photo> photos;

    private transient Node thumbnail;
    private transient AlbumThumbnailViewController tc;
    private transient AlbumViewController ac;
    private transient Scene scene;
    private transient Stage primaryStage;

    public Album(User user, String name) {
        this.user = user;
        this.name = name;
        photos = new ArrayList<Photo>();
    }

    public Album(ArrayList<Photo> photos, String name, User user) {
        this.photos = photos;
        this.name = name;
        this.user = user;
    }

    public void createThumbnail() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/album/albumThumbnail/albumThumbnailView.fxml"));
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
        if (thumbnail == null) {
            createThumbnail();
        }
        return thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public User getUser() {
        return user;
    }
    
    public void start() {
        if (scene == null) {
            initScene();
        }
        ac.injectAlbum(this);
        if (photos != null)
            for (Photo photo: photos)
                photo.getPhotoThumbnailController().injectAlbumController(ac);
        primaryStage.setScene(scene);
    }

    public void start(boolean bool) {
        if (scene == null) {
            initScene(bool);
        }
        ac.injectAlbum(this);
        if (photos != null)
            for (Photo photo: photos)
                photo.getPhotoThumbnailController().injectAlbumController(ac);
        primaryStage.setScene(scene);
    }

    public void select() {
        tc.select();
    }

    public void deselect() {
        tc.deselect();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/album/AlbumView.fxml"));
            Parent root = loader.load();
            ac = loader.getController();
            ac.injectAlbum(this);
            scene = new Scene(root);
        } catch (Exception e) {
            System.out.println("album oops");
            e.printStackTrace();
        }
        primaryStage = App.getStage();
    }

    private void initScene(boolean bool) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/album/AlbumView.fxml"));
            Parent root = loader.load();
            ac = loader.getController();
            ac.injectAlbum(this, bool);
            scene = new Scene(root);
        } catch (Exception e) {
            System.out.println("album oops");
            e.printStackTrace();
        }
        primaryStage = App.getStage();
    }

    public Album clone() {
        return new Album(photos, name, user);
    }
}
