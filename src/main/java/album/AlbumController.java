package main.java.album;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.java.MainController;
import main.java.album.photoBox.PhotoBoxController;
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

    @FXML
    private VBox photoVBox;
    
    private static Album album;
    private static AlbumModel albumModel = AlbumModel.getInstance();
    private ArrayList<Node> photoBoxes = new ArrayList<Node>();
    private ArrayList<PhotoBoxController> pbControllers = new ArrayList<PhotoBoxController>();
    private Photo curSelected = null;

    public void injectAlbum(Album album) {
        this.album = album;
        initScene();
    }

    private void initScene() {
        ArrayList<Node> thumbnails = albumModel.getThumbnails(album);
        int num = thumbnails.size();
        albumModel.injectAlbumController(this);

        for (int i = 0; i <= num/3; i++) {
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
        // photoVBox.getChildren().addAll(photoBoxes);
        for (Node pb: photoBoxes)
            if (!photoVBox.getChildren().contains(pb))
                photoVBox.getChildren().add(pb);
    }

    public void injectPB(PhotoBoxController pb) {
        pbControllers.add(pb);
    }

    public void select(Photo photo) {
        if (curSelected == null || !curSelected.equals(photo)) {
            deselect();
            curSelected = photo;
            curSelected.select();
            removePhotoButon.setDisable(false);
            captionPhotoButon.setDisable(false);
            displayPhotoButon.setDisable(false);
            addTagButon.setDisable(false);
            removeTagButon.setDisable(false);
            copyButon.setDisable(false);
            moveButon.setDisable(false);
        }
        else
            deselect();
    }

    private void deselect() {
        if (curSelected != null) {
            curSelected.deselect();
            removePhotoButon.setDisable(true);
            captionPhotoButon.setDisable(true);
            displayPhotoButon.setDisable(true);
            addTagButon.setDisable(true);
            removeTagButon.setDisable(true);
            copyButon.setDisable(true);
            moveButon.setDisable(true);
        }
        curSelected = null;
    }

    @FXML
    void addPhoto(ActionEvent event) {
        Stage primaryStage = MainController.getStage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Photo");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files (*.png, *.jpg, *.jpeg, *.gif, *.webp, *.bmp, *.heic, *.svg, *.avif)", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.webp", "*.bmp", "*.heic", "*.svg", "*.avif"),
            new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        List<File> photoFiles = fileChooser.showOpenMultipleDialog(primaryStage);
        if (photoFiles == null)
            return;
        
        for (File photoFile: photoFiles) {
            String location = photoFile.getAbsolutePath();
            Photo newPhoto = albumModel.createPhoto(location);
            if (!album.getPhotos().contains(newPhoto)) {
                newPhoto.getPhotoThumbnailController().injectAlbumController(this);
                album.getPhotos().add(newPhoto);
                initScene();
            }
            else {
                Alert error = new Alert(Alert.AlertType.ERROR, "Photo already exists in album.");
                error.setHeaderText("Photo Already Exists");
                error.showAndWait();
            }
        }
    }

    @FXML
    void addTag(ActionEvent event) {
        TagInputDialog locationDialog = new TagInputDialog();
        locationDialog.setHeaderText("Add Tag");
        locationDialog.showAndWait().ifPresent(tagData -> {
            Tag newTag = new Tag(tagData.getType(), tagData.getValue());
            ArrayList<Tag> curTags = curSelected.getTags();
            if (curTags == null)
                curTags = new ArrayList<Tag>();
            for (Tag tag: curTags) {
                if (tag.equals(newTag) && tag.tagEquals(newTag)) {
                    Alert error = new Alert(Alert.AlertType.ERROR, "Photo already contains tag.");
                    error.setHeaderText("Tag Already Exists");
                    error.showAndWait();
                    return;
                }
            }
            curTags.add(newTag);
            curSelected.setTags(curTags);
        });
    }

    @FXML
    void back(ActionEvent event) {
        deselect();
        albumModel.back(album);
    }

    @FXML
    void captionPhoto(ActionEvent event) {
        TextInputDialog locationDialog = new TextInputDialog();
        locationDialog.setContentText("Photo caption:");
        locationDialog.setHeaderText("Caption Photo");
        locationDialog.showAndWait().ifPresent(caption -> {
            curSelected.setCaption(caption);
            initScene();
        });
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
        album.getPhotos().remove(curSelected);
        deselect();
        initScene();
    }

    @FXML
    void removeTag(ActionEvent event) {
        
    }

    @FXML
    void slideshow(ActionEvent event) {

    }
    
}
