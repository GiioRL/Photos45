package main.java.album.albumThumbnail;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import main.java.util.Album;

public class AlbumThumbnailController {

    @FXML
    private ImageView imageView;

    @FXML
    private Text dateRangeText;

    @FXML
    private Text photoNumText;

    @FXML
    private Text titleText;

    private Album album;
    private static AlbumThumbnailModel thumbnailModel = AlbumThumbnailModel.getInstance();

    public void injectAlbum(Album album) {
        this.album = album;
        initScene();
    }

    private void initScene() {
        imageView.setImage(thumbnailModel.getImage(album));
        titleText.setText(thumbnailModel.getName(album));
        dateRangeText.setText(thumbnailModel.getDateRange(album));
        photoNumText.setText(thumbnailModel.getNumPhotos(album));
    }
}
