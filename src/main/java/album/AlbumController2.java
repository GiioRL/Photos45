package main.java.album;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.Node;
import main.java.album.photoBox.PhotoBoxController;

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
    private static AlbumModel2 albumModel = AlbumModel2.getInstance();
    private ArrayList<Node> photoBoxes = new ArrayList<Node>();
    private ArrayList<PhotoBoxController> pbControllers = new ArrayList<PhotoBoxController>();

    public void injectAlbum(Album album) {
        this.album = album;
        initScene();
    }

    private void initScene() {
        ArrayList<Node> thumbnails = albumModel.getThumbnails(album);
        int num = thumbnails.size();

        for (int i = 0; i < num/3; i++) {
            photoBoxes.add(albumModel.getPhotoBox());
            Node[] photos = new Node[3];
            for (int j = 0; j < 3; j++) {
                if (3*i+j >= num) {
                    photos[j] = null;
                } else {
                    photos[j] = thumbnails.get(3*i + j);
                }
            }
            pbControllers.get(i).init(photos);
        }
    }

    public void injectPB(PhotoBoxController pb) {
        pbControllers.add(pb);
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
