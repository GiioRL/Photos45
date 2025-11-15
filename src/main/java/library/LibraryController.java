package main.java.library;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Node;

import main.java.App;
import main.java.util.*;

public class LibraryController {

    @FXML
    private HBox albumHBox;

    @FXML
    private Button createAlbumButon;

    @FXML
    private Button deleteAlbumButon;

    @FXML
    private Button logoutButton;

    @FXML
    private Button openAlbumButon;

    @FXML
    private Button quitButton;

    @FXML
    private Button renameAlbumButon;

    @FXML
    private Button searchButon;

    @FXML
    private ComboBox<String> tagDropdown;

    @FXML
    private ComboBox<String> valueDropdown;

    private static User user;

    private static LibraryModel libraryModel = LibraryModel.getInstance();

    private static Album currentlySelected = null;

    private boolean selection = false;

    public void injectUser(User user) {
        this.user = user;
        start();
    }

    @FXML
    void deselect() {
        if (selection) {
            selection = false;
            return;
        }
        if (currentlySelected != null) {
            currentlySelected.deselect();
        }
        openAlbumButon.setDisable(true);
        renameAlbumButon.setDisable(true);
        deleteAlbumButon.setDisable(true);
    }

    public void select(Album album) {
        selection = true;
        if (currentlySelected != null) {
            currentlySelected.deselect();
        }
        currentlySelected = album;
        currentlySelected.select();
        openAlbumButon.setDisable(false);
        renameAlbumButon.setDisable(false);
        deleteAlbumButon.setDisable(false);
    }

    public void start() {
        albumHBox.getChildren().clear(); // maybe do something else
        ArrayList<Node> thumbnails = libraryModel.getThumbnails(user);
        albumHBox.getChildren().addAll(thumbnails);
        tagDropdown.setItems(FXCollections.observableArrayList(libraryModel.getTypes(user)));
        tagDropdown.getSelectionModel().clearSelection();
        tagDropdown.valueProperty().addListener((obs, oldType, newType) -> {
            valueDropdown.setItems(FXCollections.observableArrayList(libraryModel.getValues(user, newType)));
            valueDropdown.getSelectionModel().clearSelection();
        });
        deselect();
    }

    @FXML
    void createAlbum() {
        libraryModel.createAlbum(user);
    }

    void createAlbum(ArrayList<Photo> photos) {
        libraryModel.createAlbum(photos);
    }

    @FXML
    void deleteAlbum() {

    }

    @FXML
    void openAlbum() {
        currentlySelected.start();
    }

    @FXML
    void search() {
        ArrayList<Photo> albumPhotos = new ArrayList<Photo>();
        Tag tag = new Tag(tagDropdown.valueProperty().getValue(), valueDropdown.valueProperty().getValue());
        ArrayList<Photo> photos = user.getPhotos();
        for (Photo photo: photos) {
            ArrayList<Tag> photoTags = photo.getTags();
            if (photoTags != null) {
                if (photoTags.contains(tag)) {
                    for (Tag photoTag: photoTags) {
                        if (photoTag.tagEquals(tag)) {
                            System.out.println(photoTag);
                            System.out.println(tag);
                            System.out.println("adding photo");
                            albumPhotos.add(photo);
                        }
                    }
                }
            }
        }
        Album album = new Album(albumPhotos, "Unnamed album", user);
        user.addAlbum(album);
        album.start();
    }

    @FXML
    void logout() {
        Stage primaryStage = App.getStage();
        primaryStage.setScene(App.getLoginScene());
    }

    @FXML
    void quit() {
        System.exit(0);
    }

    @FXML
    void renameAlbum() {

    }

}
