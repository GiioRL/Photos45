package main.java.album;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import main.java.util.*;

public class AlbumController {

    @FXML
    private GridPane photoGrid;

    @FXML
    private Button addPhotoButon;

    @FXML
    private Button addTagButon;

    @FXML
    private Button backButon;

    @FXML
    private Button captionPhotoButon;

    @FXML
    private Button copyButon;

    @FXML
    private Button displayPhotoButon;

    @FXML
    private Button moveButon;

    @FXML
    private Button removePhotoButon;

    @FXML
    private Button removeTagButon;

    @FXML
    private Button slideshowButon;
    
    private static User user;
    private static Album album;
    private static AlbumModel albumModel = AlbumModel.getInstance();

    public void injectUser(User user) {
        this.user = user;
    }

    public void injectAlbum(Album album) {
        this.album = album;
        initScene();
    }

    private void initScene() {}

    @FXML
    void addPhoto(ActionEvent event) {

    }

    @FXML
    void addTag(ActionEvent event) {

    }

    @FXML
    void back(ActionEvent event) {
        user.start();
    }

    @FXML
    void captionPhoto(ActionEvent event) {

    }

    @FXML
    void copy(ActionEvent event) {

    }

    @FXML
    void displayPhoto(ActionEvent event) {

    }

    @FXML
    void move(ActionEvent event) {

    }

    @FXML
    void removePhoto(ActionEvent event) {

    }

    @FXML
    void removeTag(ActionEvent event) {

    }

    @FXML
    void slideshow(ActionEvent event) {

    }

}
