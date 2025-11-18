package view.album.albumThumbnail;

import controller.album.AlbumThumbnailController;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.Album;
import view.library.LibraryViewController;

public class AlbumThumbnailViewController {

    @FXML
    private ImageView imageView;

    @FXML
    private Text dateRangeText;

    @FXML
    private Text photoNumText;

    @FXML
    private Text titleText;

    @FXML
    private AnchorPane backgroundPane;

    private Album album;
    private static AlbumThumbnailController thumbnailModel = AlbumThumbnailController.getInstance();
    private LibraryViewController lc;

    public void injectAlbum(Album album) {
        this.album = album;
        lc = album.getUser().getLC();
        initScene();
    }

    private void initScene() {
        imageView.setImage(thumbnailModel.getImage(album));
        titleText.setText(thumbnailModel.getName(album));
        dateRangeText.setText(thumbnailModel.getDateRange(album));
        photoNumText.setText(thumbnailModel.getNumPhotos(album));
    }

    @FXML
    void onClick(MouseEvent e) { // maybe this should be on the whole anchorpane and not just the image
        lc.select(album);
    }

    public void select() {
        backgroundPane.setStyle("-fx-border-color: TEAL");
    }

    public void deselect() {
        backgroundPane.setStyle("-fx-border-color: TRANSPARENT");
    }
}
