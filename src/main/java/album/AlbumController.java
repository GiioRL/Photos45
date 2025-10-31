package main.java.album;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.java.library.LibraryController;
import main.java.util.*;

public class AlbumController {

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
    
    @FXML private LibraryController lc;
    

    public AlbumController() {}

    public AlbumController(LibraryController lc) {
        this.lc = lc;
    }

    @FXML private void initialize() {
        lc.injectAlbumController(this);
    }

    @FXML
    void addPhoto(ActionEvent event) {

    }

    @FXML
    void addTag(ActionEvent event) {

    }

    @FXML
    void back(ActionEvent event) {
        lc.start();
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
