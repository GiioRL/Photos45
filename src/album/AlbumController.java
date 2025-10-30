package album;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import util.*;

public class AlbumController {

    @FXML
    private Button exitButon;

    @FXML
    private ImageView imageView;

    @FXML
    private Button nextButon;

    @FXML
    private Button prevButon;

    private User user;

    private AlbumModel albumModel = new AlbumModel();

    @FXML
    void exit(ActionEvent event) {
        user.start();
    }

    @FXML
    void next(ActionEvent event) {

    }

    @FXML
    void prev(ActionEvent event) {

    }

}
