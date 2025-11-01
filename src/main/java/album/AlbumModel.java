package main.java.album;

import java.util.ArrayList;
import java.util.Calendar;

import javafx.scene.image.Image;

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

    public String convertDatetoString(Calendar date) {
        return ("" + date.MONTH + "/" + date.DAY_OF_MONTH + "/" + date.YEAR);
    }

    public ArrayList<Image> getImages(Album album) {
        ArrayList<Image> images = new ArrayList<Image>();
        for (Photo photo : album.getPhotos()) {
            images.add(photo.getImage());
        }
        return images;
    }

    public void back(Album album) {
        if (album.getPhotos() == null) {
            album.delete();
        }
        album.back();
    }
}
