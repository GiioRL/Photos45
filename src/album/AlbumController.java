package album;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

import util.*;

public class AlbumController {

    @FXML
    private HBox photoHBox;

    @FXML
    private Button searchButton;

    @FXML
    private ComboBox<?> tagDropdown;

    @FXML
    private ComboBox<?> valueDropdown;

    private User user;

    private AlbumModel albumModel = new AlbumModel();

    @FXML
    void createAlbum() {
        albumModel.createAlbum(null);
    }
}
