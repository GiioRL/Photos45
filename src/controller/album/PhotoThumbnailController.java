package controller.album;

import javafx.scene.image.Image;
import model.Photo;

public class PhotoThumbnailController {

    private static PhotoThumbnailController instance;

    private PhotoThumbnailController() {}

    public static PhotoThumbnailController getInstance() {
        if (instance == null) {
            instance = new PhotoThumbnailController();
        }
        return instance;
    }

    public Image getImage(Photo photo) {
        return photo.getImage();
    }

    public String getCaption(Photo photo) {
        return photo.getCaption();
    }
    
}
