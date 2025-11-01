package main.java.album.albumThumbnail;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.scene.text.Text;

import main.java.util.Album;

public class AlbumThumbnailController extends Node {

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
        imageView = thumbnailModel.getImage(album);
        titleText = new Text(thumbnailModel.getName(album));
        dateRangeText = new Text(thumbnailModel.getDateRange(album));
        photoNumText = new Text(thumbnailModel.getNumPhotos(album));
    }
}
