package main.java.album.photoThumbnail;

import javafx.scene.image.Image;

import main.java.util.Photo;

public class PhotoThumbnailModel {

    private static PhotoThumbnailModel instance;

    private PhotoThumbnailModel() {}

    public static PhotoThumbnailModel getInstance() {
        if (instance == null) {
            instance = new PhotoThumbnailModel();
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
