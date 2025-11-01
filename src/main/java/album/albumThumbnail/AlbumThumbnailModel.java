package main.java.album.albumThumbnail;

import java.util.ArrayList;
import java.util.Calendar;

import javafx.scene.image.ImageView;

import main.java.album.AlbumModel;
import main.java.util.Album;
import main.java.util.Photo;

public class AlbumThumbnailModel {

    private static AlbumThumbnailModel instance;
    private static AlbumModel albumModel = AlbumModel.getInstance();
    
    private AlbumThumbnailModel() {}

    public static AlbumThumbnailModel getInstance() {
        if (instance == null) {
            instance = new AlbumThumbnailModel();
        }
        return instance;
    }

    public ImageView getImage(Album album) {
        return album.getPhotos().get(0).getImageView();
    }

    public String getName(Album album) {
        return album.getName();
    }

    public String getDateRange(Album album) {
        ArrayList<Calendar> dates = new ArrayList<Calendar>();
        for (Photo photo: album.getPhotos()) {
            dates.add(photo.getDate());
        }
        dates.sort(null);
        
        return albumModel.convertDatetoString(dates.get(0)) + " - " + albumModel.convertDatetoString(dates.get(dates.size()-1));
    }

    public String getNumPhotos(Album album) {
        return "" + album.getPhotos().size();
    }
}
