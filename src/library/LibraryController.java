package library;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

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

    private LibraryModel albumModel = new LibraryModel();

    @FXML
    void createAlbum() {
        albumModel.createAlbum(null);
    }
}
