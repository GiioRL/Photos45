package main.java.album;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.Node;

import main.java.util.*;

public class AlbumController2 {

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
    
    private static Album album;
    private static AlbumModel albumModel = AlbumModel.getInstance();

    public void injectAlbum(Album album) {
        this.album = album;
        initScene();
    }

    private void initScene() {
        // photoGrid.addRow(0, albumModel.getThumbnails(album));
        ArrayList<Node> thumbnails = albumModel.getThumbnails(album);
        // ObservableList<Node> children = photoGrid.getChildren();
        for (int i = 0; i < thumbnails.size(); i++) {
            photoGrid.add(thumbnails.get(i), i%3, i/3);
        }
        // photoGrid.add(thumbnails.get(0), 0, 0);
        // photoGrid.add(thumbnails.get(1), 0, 1);
    }

    @FXML
    void addPhoto(ActionEvent event) {

    }

    @FXML
    void addTag(ActionEvent event) {

    }

    @FXML
    void back(ActionEvent event) {
        albumModel.back(album);
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
