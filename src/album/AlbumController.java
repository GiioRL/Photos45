package album;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

public class AlbumController {

    @FXML
    private HBox photoHBox;

    @FXML
    private Button searchButton;

    @FXML
    private ComboBox<?> tagDropdown;

    @FXML
    private ComboBox<?> valueDropdown;

}
