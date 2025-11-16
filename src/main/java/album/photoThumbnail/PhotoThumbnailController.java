package main.java.album.photoThumbnail;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import main.java.album.AlbumController;
import main.java.util.Photo;

public class PhotoThumbnailController {

    @FXML
    private Label captionLabel;

    @FXML
    private ImageView imageView;

    @FXML
    private AnchorPane container;

    private static PhotoThumbnailModel thumbnailModel = PhotoThumbnailModel.getInstance();
    private Photo photo;
    private AlbumController ac;

    public void injectPhoto(Photo photo) {
        this.photo = photo;
        initScene();
    }

    public void injectAlbumController(AlbumController ac) {
        this.ac = ac;
    }

    private void initScene() {
        imageView.setImage(thumbnailModel.getImage(photo));
        captionLabel.setText(thumbnailModel.getCaption(photo));
    }

    @FXML
    void onClick() {
        ac.select(photo);        
    }

    public void select() {
        container.setStyle("-fx-border-color: TEAL");
    }

    public void deselect() {
        container.setStyle("-fx-border-color: TRANSPARENT");
    }

    public AlbumController getAC() {
        return ac;
    }
}
