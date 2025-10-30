package library;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

import album.AlbumController;
import album.AlbumModel;
import util.*;

public class LibraryController {

    @FXML
    private HBox photoHBox;

    @FXML
    private Button searchButton;

    @FXML
    private ComboBox<?> tagDropdown;

    @FXML
    private ComboBox<?> valueDropdown;

    private User user;

    private LibraryModel libraryModel = new LibraryModel();
    private ArrayList<AlbumModel> albums = new ArrayList<AlbumModel>();
    // private ArrayList<AlbumController> albumControllers = new ArrayList<AlbumController>();

    @FXML
    void createAlbum() {
        albums.add(new AlbumModel());
        AlbumModel album = albums.get(albums.size()-1);
        album.start();
    }

    void createAlbum(ArrayList<Photo> photos) {
        libraryModel.createAlbum(photos);
    }

    public void start() {
        user.start();
    }
}
