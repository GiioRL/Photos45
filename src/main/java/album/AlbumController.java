package main.java.album;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.java.App;
import main.java.album.customDialogs.*;
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
    private Button quitButton;

    @FXML
    private VBox butonBox;

    @FXML
    private VBox photoVBox;
    
    private static Album album;
    private static AlbumModel albumModel = AlbumModel.getInstance();
    private ArrayList<Node> photoBoxes = new ArrayList<Node>();
    private ArrayList<PhotoBoxController> pbControllers = new ArrayList<PhotoBoxController>();
    private Photo curSelected = null;

    public void injectAlbum(Album album) {
        this.album = album;
        initScene(false);
    }

    public void injectAlbum(Album album, boolean bool) {
        this.album = album;
        initScene(bool);
    }

    private void initScene() {
        ArrayList<Node> thumbnails = albumModel.getThumbnails(album);
        int num = thumbnails.size();
        albumModel.injectAlbumController(this);
        photoVBox.getChildren().clear(); // there could be better ways..
        photoBoxes.clear();
        pbControllers.clear();

        for (int i = 0; i < (num+2)/3; i++) {
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
        photoVBox.getChildren().addAll(photoBoxes);
    }

    private void initScene(boolean bool) {
        ArrayList<Node> thumbnails = albumModel.getThumbnails(album);
        int num = thumbnails.size();
        albumModel.injectAlbumController(this);
        photoVBox.getChildren().clear(); // there could be better ways..
        photoBoxes.clear();
        pbControllers.clear();

        for (int i = 0; i < (num+2)/3; i++) {
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
        photoVBox.getChildren().addAll(photoBoxes);
        if (bool) {
            Button buton = new Button("Add to Library");
            buton.setOnAction(e -> addToLibrary());
            butonBox.getChildren().addFirst(buton);
            butonBox.setSpacing(10);
        }
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
    void addToLibrary() {
        album.getUser().getAlbums().add(album.clone());
        album.getUser().start();
    }

    @FXML
    void addPhoto(ActionEvent event) {
        Stage primaryStage = App.getStage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Photo");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files (*.png, *.jpg, *.jpeg, *.gif, *.bmp)", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp"),
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
                initScene(false);
            }
            else {
                Alert warning = new Alert(Alert.AlertType.WARNING, "Photo already exists in album.");
                warning.setHeaderText("Photo Already Exists");
                warning.showAndWait();
            }
        }
    }

    @FXML
    void addTag(ActionEvent event) {
        AddTagDialog locationDialog = new AddTagDialog(album.getUser().getTags());
        locationDialog.showAndWait().ifPresent(tagData -> {
            if (tagData.getType() == null || tagData.getValue().length() == 0) {
                Alert warning = new Alert(Alert.AlertType.WARNING, "Enter a tag-value combination.");
                warning.setHeaderText("Invalid Tag Selection");
                warning.showAndWait();
                return;
            }
            Tag newTag = new Tag(tagData.getType(), tagData.getValue());
            ArrayList<Tag> curTags = curSelected.getTags();
            if (curTags == null)
                curTags = new ArrayList<Tag>();
            for (Tag tag: curTags) {
                if (tag.equals(newTag) && tag.tagEquals(newTag)) {
                    Alert warning = new Alert(Alert.AlertType.WARNING, "Photo already contains tag.");
                    warning.setHeaderText("Tag Already Exists");
                    warning.showAndWait();
                    return;
                }
            }
            curTags.add(newTag);
            curSelected.setTags(curTags);
            boolean userHasTag = false;
            for (Tag t: album.getUser().getTags())
                if (t.equals(newTag) && t.tagEquals(newTag))
                    userHasTag = true;
            if (!userHasTag)
                album.getUser().getTags().add(newTag);
            Alert info = new Alert(Alert.AlertType.WARNING, "Tag added successfully!");
            info.setHeaderText("Tag Added");
            info.showAndWait();
        });
    }

    @FXML
    void back(ActionEvent event) {
        deselect();
        albumModel.back(album);
    }

    @FXML
    void captionPhoto(ActionEvent event) {
        TextInputDialog captionDialog = new TextInputDialog();
        captionDialog.setContentText("Photo caption:");
        captionDialog.setHeaderText("Caption Photo");
        captionDialog.showAndWait().ifPresent(caption -> {
            curSelected.setCaption(caption);
            initScene();
            curSelected.select();
        });
    }

    @FXML
    void copy(ActionEvent event) {
        ChoiceDialog<String> albumDialog = new ChoiceDialog<String>();
        for (Album userAlbum: album.getUser().getAlbums())
            if (!userAlbum.getName().equals(album.getName()))
                albumDialog.getItems().add(userAlbum.getName());
        albumDialog.setContentText("Album to copy to:");
        albumDialog.setHeaderText("Choose Album");
        albumDialog.showAndWait().ifPresent(destAlbum -> {
            if (destAlbum == null)
                return;
            for (Album userAlbum: album.getUser().getAlbums()) {
                if (userAlbum.getName().equals(destAlbum)) {
                    if (!userAlbum.getPhotos().contains(curSelected)) {
                        userAlbum.getPhotos().add(curSelected);
                        Alert info = new Alert(Alert.AlertType.INFORMATION, "Photo copied to " + destAlbum + " successfully!");
                        info.setHeaderText("Copied Successfully");
                        info.showAndWait();
                    }
                    else {
                        Alert warning = new Alert(Alert.AlertType.WARNING, "Photo already exists in " + destAlbum + ".");
                        warning.setHeaderText("Photo Already Exists");
                        warning.showAndWait();
                    }
                    return;
                }
            }
        });
    }

    @FXML
    void displayPhoto(ActionEvent event) {
        new ImageDialog(curSelected).showAndWait();
    }

    @FXML
    void move(ActionEvent event) {
        ChoiceDialog<String> albumDialog = new ChoiceDialog<String>();
        for (Album userAlbum: album.getUser().getAlbums())
            if (!userAlbum.getName().equals(album.getName()))
                albumDialog.getItems().add(userAlbum.getName());
        albumDialog.setContentText("Album to move to:");
        albumDialog.setHeaderText("Choose Album");
        albumDialog.showAndWait().ifPresent(destAlbum -> {
            if (destAlbum == null)
                return;
            for (Album userAlbum: album.getUser().getAlbums()) {
                if (userAlbum.getName().equals(destAlbum)) {
                    if (!userAlbum.getPhotos().contains(curSelected)) {
                        userAlbum.getPhotos().add(curSelected);
                        album.getPhotos().remove(curSelected);
                        deselect();
                        initScene();
                        Alert info = new Alert(Alert.AlertType.INFORMATION, "Photo moved to " + destAlbum + " successfully!");
                        info.setHeaderText("Moved Successfully");
                        info.showAndWait();
                    }
                    else {
                        Alert warning = new Alert(Alert.AlertType.WARNING, "Photo already exists in " + destAlbum + ".");
                        warning.setHeaderText("Photo Already Exists");
                        warning.showAndWait();
                    }
                    return;
                }
            }
        });
    }

    @FXML
    void removePhoto(ActionEvent event) {
        album.getPhotos().remove(curSelected);
        deselect();
        initScene();
    }

    @FXML
    void removeTag(ActionEvent event) {
        ArrayList<Tag> curTags = curSelected.getTags();
        if (curTags == null || curTags.size() == 0)
        {
            Alert warning = new Alert(Alert.AlertType.WARNING, "Photo does not have any tags.");
            warning.setHeaderText("No Tags");
            warning.showAndWait();
            return;
        }
        RemoveTagDialog locationDialog = new RemoveTagDialog(curTags);
        locationDialog.showAndWait().ifPresent(tagData -> {
            if (tagData.getType() == null || tagData.getValue() == null) {
                Alert warning = new Alert(Alert.AlertType.WARNING, "Select a tag-value combination.");
                warning.setHeaderText("Invalid Selection");
                warning.showAndWait();
                return;
            }
            Tag oldTag = new Tag(tagData.getType(), tagData.getValue());
            for (int i = 0; i < curTags.size(); i++) {
                Tag t = curTags.get(i);
                if (t.equals(oldTag) && t.tagEquals(oldTag)) {
                    curTags.remove(i);
                    break;
                }
            }
            curSelected.setTags(curTags);
            boolean somePhotoHasTag = false;
            for (Album a: album.getUser().getAlbums())
                for (Photo p: a.getPhotos())
                    for (Tag t: p.getTags())
                        if (t.equals(oldTag) && t.tagEquals(oldTag))
                            somePhotoHasTag = true;
            if (!somePhotoHasTag) {
                ArrayList<Tag> userTags = album.getUser().getTags();
                for (int i = 0; i < userTags.size(); i++) {
                    Tag t = userTags.get(i);
                    if (t.equals(oldTag) && t.tagEquals(oldTag)) {
                        userTags.remove(i);
                        break;
                    }
                }
            }
            Alert info = new Alert(Alert.AlertType.INFORMATION, "Tag removed successfully!");
            info.setHeaderText("Tag Removed");
            info.showAndWait();
        });
    }

    @FXML
    void slideshow(ActionEvent event) {
        new SlidesDialog(album, curSelected).showAndWait();
    }

    @FXML
    void quit(ActionEvent event) {
        System.exit(0);
    }
}
