package main.java.library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
    private Button tagSearchButon;

    @FXML
    private ComboBox<String> tagDropdown;

    @FXML
    private ComboBox<String> valueDropdown;

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

    @FXML
    void deleteAlbum() {

    }

    @FXML
    void openAlbum() {
        currentlySelected.start();
    }

    @FXML
    void tagSearch() {
        Album album = libraryModel.tagSearch(tagDropdown.valueProperty().getValue(), valueDropdown.valueProperty().getValue(), user);
        if (album == null) {
            System.out.println("select value and tag!!");
            //error message, "select value and tag"
            return;
        }
        album.start(true);
    }

    @FXML
    void dateSearch() {
        LocalDate fromLocalDate = fromDate.valueProperty().getValue();
        LocalDate toLocalDate = toDate.valueProperty().getValue();
        if (fromLocalDate == null || toLocalDate == null) {
            System.out.println("select dates!!");
            //error message, "select dates"
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
        System.exit(0);
    }

    @FXML
    void renameAlbum() {

    }

}
