package main.java.library;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.Node;

import main.java.MainController;
import main.java.util.*;

public class LibraryController {

    @FXML
    private HBox albumHBox;

    @FXML
    private Button searchButon;

    @FXML
    private Button createAlbumButon;

    @FXML
    private ComboBox<?> tagDropdown;

    @FXML
    private ComboBox<?> valueDropdown;

    private MainController mc;

    private static User user;

    private static LibraryModel libraryModel = LibraryModel.getInstance();

    public void injectMainController(MainController mc) {
        this.mc = mc;
    }

    public void injectUser(User user) {
        this.user = user;
        initScene();
    }

    private void initScene() {
        albumHBox.getChildren().clear(); // maybe do something else
        ArrayList<Node> thumbnails = libraryModel.getThumbnails(user);
        albumHBox.getChildren().add(createAlbumButon);
        albumHBox.getChildren().addAll(thumbnails);
    }

    @FXML
    void createAlbum() {
        libraryModel.createAlbum(user);
    }

    void createAlbum(ArrayList<Photo> photos) {
        libraryModel.createAlbum(photos);
    }
}
