package main.java.album.customDialogs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Window;
import main.java.util.Album;
import main.java.util.Photo;
import main.java.util.Tag;

public class SlidesDialog extends Dialog<Object> {
    
    @FXML
    private ImageView imageView;

    @FXML
    private Button leftButton;

    @FXML
    private Button rightButton;

    private Album album;
    private Photo curPhoto;
    private int curIndex;

    public SlidesDialog(Album album, Photo curSelected) {
        super();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/main/java/album/customDialogs/SlidesDialog.fxml"));
            loader.setController(this);
            DialogPane pane = loader.load();

            this.album = album;
            if (curSelected != null) {
                this.curPhoto = curSelected;
                this.curIndex = album.getPhotos().indexOf(curPhoto);
            }
            else if (album.getPhotos().size() > 0) {
                this.curPhoto = album.getPhotos().get(0);
                this.curIndex = 0;
            }
            else {
                curSelected = null;
                this.curIndex = -1;
            }

            pane.getButtonTypes().add(ButtonType.OK);
            this.setDialogPane(pane);
            setupImage();

            if (this.curIndex == 0)
                this.leftButton.setDisable(true);
            this.leftButton.setOnAction(arg0 -> {
                if (rightButton.isDisable())
                    rightButton.setDisable(false);
                this.curIndex--;
                this.curPhoto = album.getPhotos().get(curIndex);
                if (this.curIndex == 0)
                    this.leftButton.setDisable(true);
                setupImage();
            });

            if (this.curIndex == this.album.getPhotos().size() - 1)
                this.rightButton.setDisable(true);
            this.rightButton.setOnAction(arg0 -> {
                if (leftButton.isDisable())
                    leftButton.setDisable(false);
                this.curIndex++;
                this.curPhoto = album.getPhotos().get(curIndex);
                if (this.curIndex == album.getPhotos().size() - 1)
                    this.rightButton.setDisable(true);
                setupImage();
            });

            this.setResultConverter((buttonType) -> {
                return null;
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupImage() {
        if (curPhoto == null)
            return;
        this.imageView.setImage(curPhoto.getImage());
        String header = curPhoto.getCaption().length() > 0 ? curPhoto.getCaption() : "(Uncaptioned)";
        header += " - " + curPhoto.getDateString();
        this.setHeaderText(header);

        DialogPane pane = this.getDialogPane();
        pane.getScene().getWindow().sizeToScene();
    }
}
