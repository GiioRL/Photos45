package controller.album;

import java.util.ArrayList;
import java.util.Calendar;

import javafx.scene.image.Image;
import model.Album;
import model.Photo;

public class AlbumThumbnailController {

    private static AlbumThumbnailController instance;
    private static AlbumController albumModel = AlbumController.getInstance();
    
    private AlbumThumbnailController() {}

    public static AlbumThumbnailController getInstance() {
        if (instance == null) {
            instance = new AlbumThumbnailController();
        }
        return instance;
    }

    public Image getImage(Album album) {
        if (album.getPhotos().size() == 0) {
            return new Image("/resources/imageNotFound.png");
        }
        return album.getPhotos().get(0).getImage();
    }

    public String getName(Album album) {
        return album.getName();
    }

    public String getDateRange(Album album) {
        if (album.getPhotos().size() == 0) {
            return "";
        }
        ArrayList<Calendar> dates = new ArrayList<Calendar>();
        for (Photo photo: album.getPhotos()) {
            dates.add(photo.getDate());
        }
        dates.sort(null);
        
        return albumModel.convertDatetoString(dates.get(0)) + " - " + albumModel.convertDatetoString(dates.get(dates.size()-1));
    }

    public String getNumPhotos(Album album) {
        int num = album.getPhotos().size();
        if (num == 1) {
            return "1 photo";
        }
        return "" + num + " photos";
    }
}
