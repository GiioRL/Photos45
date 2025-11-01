package main.java.library;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

import main.java.MainController;
import main.java.album.AlbumController;
import main.java.album.AlbumModel;
import main.java.util.*;

public class LibraryController {

    @FXML
    private HBox photoHBox;

    @FXML
    private Button searchButton;

    @FXML
    private ComboBox<?> tagDropdown;

    @FXML
    private ComboBox<?> valueDropdown;

    private MainController mc;

    private static User user;

    private LibraryModel libraryModel = new LibraryModel();

    public void injectMainController(MainController mc) {
        this.mc = mc;
    }

    public void injectUser(User user) {
        this.user = user;
    }

    @FXML
    void createAlbum() {
        libraryModel.createAlbum(user);
    }

    void createAlbum(ArrayList<Photo> photos) {
        libraryModel.createAlbum(photos);
    }

    // public void start() {
    //     user.start();
    // }
}
