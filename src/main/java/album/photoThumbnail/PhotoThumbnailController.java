package main.java.album.photoThumbnail;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import main.java.util.Photo;

public class PhotoThumbnailController {

    @FXML
    private Label captionLabel;

    @FXML
    private ImageView imageView;

    private static PhotoThumbnailModel thumbnailModel = PhotoThumbnailModel.getInstance();
    private Photo photo;

    public void injectPhoto(Photo photo) {
        this.photo = photo;
        initScene();
    }

    private void initScene() {
        imageView.setImage(thumbnailModel.getImage(photo));
        captionLabel.setText(thumbnailModel.getCaption(photo));
    }
}
