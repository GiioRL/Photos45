package main.java.album;

import java.util.ArrayList;
import java.util.Calendar;

import javafx.scene.image.ImageView;

import main.java.util.*;

public class AlbumModel {

    static AlbumModel instance;

    private AlbumModel() {}

    public static AlbumModel getInstance() {
        if (instance == null) {
            instance = new AlbumModel();
        }
        return instance;
    }

    public Photo createPhoto(Calendar calendar, ArrayList<Tag> tags, String location, String caption) {
        return new Photo(calendar, tags, location, caption);
    }

    public Photo createPhoto(String location) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND, 0);
        return new Photo(calendar, null, location, "");
    }

    public ArrayList<ImageView> getImageViews(Album album) {
        ArrayList<ImageView> imageViews = new ArrayList<ImageView>();
        for (Photo photo : album.getPhotos()) {
            imageViews.add(photo.getImageView());
        }
        return imageViews;
    }
}
