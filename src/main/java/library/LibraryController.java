package main.java.library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Node;

import main.java.App;
import main.java.util.*;

public class LibraryController {

    @FXML
    private HBox albumHBox;

    @FXML
    private Button createTagButton;

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
    private Button tagSearchButon;

    @FXML
    private ComboBox<String> tagDropdown1;

    @FXML
    private ComboBox<String> valueDropdown1;

    @FXML
    private ComboBox<String> conjunctionBox;

    @FXML
    private ComboBox<String> tagDropdown2;
    
    @FXML
    private ComboBox<String> valueDropdown2;

    @FXML
    private DatePicker fromDate;

    @FXML
    private DatePicker toDate;

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
        for (Album album: user.getAlbums())
            album.createThumbnail();
        ArrayList<Node> thumbnails = libraryModel.getThumbnails(user);
        albumHBox.getChildren().addAll(thumbnails);
        tagDropdown1.setItems(FXCollections.observableArrayList(libraryModel.getTypes(user)));
        tagDropdown1.getSelectionModel().clearSelection();
        tagDropdown1.valueProperty().addListener((obs, oldType, newType) -> {
            valueDropdown1.setItems(FXCollections.observableArrayList(libraryModel.getValues(user, newType)));
            valueDropdown1.getSelectionModel().clearSelection();
        });
        tagDropdown2.setItems(FXCollections.observableArrayList(libraryModel.getTypes(user)));
        tagDropdown2.getSelectionModel().clearSelection();
        tagDropdown2.valueProperty().addListener((obs, oldType, newType) -> {
            valueDropdown2.setItems(FXCollections.observableArrayList(libraryModel.getValues(user, newType)));
            valueDropdown2.getSelectionModel().clearSelection();
        });
        ArrayList<String> conjunctions = new ArrayList<String>() {{
            add("And");
            add("Or");
        }};
        conjunctionBox.setItems(FXCollections.observableArrayList(conjunctions));
        deselect();
    }

    @FXML
    void createAlbum() {
        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setContentText("Album name:");
        nameDialog.showAndWait().ifPresent(albumName -> {
            if (albumName.length() == 0) {
                Alert warning = new Alert(AlertType.WARNING, "Enter a non-empty name.");
                warning.setHeaderText("Invalid Name");
                warning.showAndWait();
                return;
            }
            for (Album album: user.getAlbums()) {
                if (album.getName().equals(albumName)) {
                    Alert warning = new Alert(AlertType.WARNING, "Album with that name already exists.");
                    warning.setHeaderText("Album Already Exists");
                    warning.showAndWait();
                    return;
                }
            }
            libraryModel.createAlbum(user, albumName);
        });
    }

    @FXML
    void deleteAlbum() {
        currentlySelected.delete();
        deselect();
        start();
    }

    @FXML
    void openAlbum() {
        currentlySelected.start();
    }

    @FXML
    void tagSearch() {
        Album album = libraryModel.tagSearch(tagDropdown1.valueProperty().getValue(), valueDropdown1.valueProperty().getValue(),
        conjunctionBox.valueProperty().getValue(), tagDropdown2.valueProperty().getValue(), valueDropdown2.valueProperty().getValue(), user);
        if (album == null) {
            Alert warning = new Alert(Alert.AlertType.WARNING, "Select a proper tag-value combination.");
            warning.setHeaderText("Invalid Selection");
            warning.showAndWait();
            return;
        }
        album.start(true);
    }

    @FXML
    void dateSearch() {
        LocalDate fromLocalDate = fromDate.valueProperty().getValue();
        LocalDate toLocalDate = toDate.valueProperty().getValue();
        if (fromLocalDate == null || toLocalDate == null) {
            Alert warning = new Alert(Alert.AlertType.WARNING, "Choose a proper date range.");
            warning.setHeaderText("Invalid Date Range");
            warning.showAndWait();
            return;
        }
        if (fromLocalDate.compareTo(toLocalDate) > 0) {
            LocalDate temp = fromLocalDate;
            fromLocalDate = toLocalDate;
            toLocalDate = temp;
        }
        Album album = libraryModel.dateSearch(fromLocalDate, toLocalDate, user);
        album.start(true);
    }

    @FXML
    void logout() {
        Stage primaryStage = App.getStage();
        primaryStage.setScene(App.getLoginScene());
    }

    @FXML
    void quit() {
        App.quit();
    }

    @FXML
    void renameAlbum() {
        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setContentText("Album name:");
        nameDialog.showAndWait().ifPresent(newName -> {
            if (newName.length() == 0) {
                Alert warning = new Alert(AlertType.WARNING, "Enter a non-empty name.");
                warning.setHeaderText("Invalid Name");
                warning.showAndWait();
                return;
            }
            for (Album album: user.getAlbums()) {
                if (album.getName().equals(newName)) {
                    Alert warning = new Alert(AlertType.WARNING, "Album with that name already exists.");
                    warning.setHeaderText("Album Already Exists");
                    warning.showAndWait();
                    return;
                }
            }
            currentlySelected.setName(newName);
            currentlySelected.createThumbnail();
            start();
        });
    }

    @FXML
    void createTag() {
        TextInputDialog tagDialog = new TextInputDialog();
        tagDialog.setContentText("Tag name:");
        tagDialog.showAndWait().ifPresent(newTagType -> {
            if (newTagType.length() == 0) {
                Alert warning = new Alert(AlertType.WARNING, "Enter a non-empty name.");
                warning.setHeaderText("Invalid Name");
                warning.showAndWait();
                return;
            }
            for (Tag t: user.getTags()) {
                if (t.getType().equals(newTagType)) {
                    Alert warning = new Alert(AlertType.WARNING, "Tag already exists.");
                    warning.setHeaderText("Tag Already Exists");
                    warning.showAndWait();
                    return;
                }
            }
            user.getTags().add(new Tag(newTagType, null));
            start();
        });
    }
}
