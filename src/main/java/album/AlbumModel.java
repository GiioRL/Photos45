package main.java.album;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import main.java.util.*;

public class AlbumModel implements Serializable{

    static AlbumModel instance;
    private transient AlbumController ac;

    private AlbumModel() {}

    public static AlbumModel getInstance() {
        if (instance == null) {
            instance = new AlbumModel();
        }
        return instance;
    }

    public Photo createPhoto(String location, String caption, ArrayList<Tag> tags, long millis) {
        return new Photo(location, caption, tags, millis);
    }

    public Photo createPhoto(String location, String caption, ArrayList<Tag> tags) {
        File file = new File(location);
        return createPhoto(location, caption, tags, file.lastModified());
    }

    public Photo createPhoto(String location, String caption) {
        return createPhoto(location, caption, null);
    }

    public Photo createPhoto(String location) {
        return createPhoto(location, "");
    }

    public String convertDatetoString(Calendar date) {
        return ("" + (date.get(Calendar.MONTH)+1) + "/" + date.get(Calendar.DAY_OF_MONTH) + "/" + date.get(Calendar.YEAR));
    }

    public ArrayList<Node> getThumbnails(Album album) {
        ArrayList<Node> thumbnails = new ArrayList<Node>();
        ArrayList<Photo> photos = album.getPhotos();
        if (photos != null) {
            for (Photo photo: album.getPhotos()) {
                Node thumbnail = photo.getThumbnail();
                thumbnails.add(thumbnail);
            }
        }
        return thumbnails;
    }

    public void back(Album album) {
        // if (album.getPhotos() == null) {
        //     album.delete();
        // }
        album.back();
    }

    public void injectAlbumController(AlbumController ac) {
        this.ac = ac;
    }

    public Node getPhotoBox() {
        Node photoBox = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("photoBox/PhotoBox.fxml"));
            photoBox = loader.load();
            if (photoBox == null) {
                System.out.println("why null???");
            }
            ac.injectPB(loader.getController());
        } catch (Exception e) {
            System.out.println("o no bad thumbnail in AM");
            e.printStackTrace();
        }
        return photoBox;
    }
}
