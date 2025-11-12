package main.java.album;

import java.util.ArrayList;
import java.util.Calendar;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import main.java.util.*;

public class AlbumModel2 {

    static AlbumModel2 instance;
    private AlbumController2 ac;

    private AlbumModel2() {}

    public static AlbumModel2 getInstance() {
        if (instance == null) {
            instance = new AlbumModel2();
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

    public ArrayList<Node> getThumbnails(Album album) {
        ArrayList<Node> thumbnails = new ArrayList<Node>();
        for (Photo photo: album.getPhotos()) {
            Node thumbnail = photo.getThumbnail();
            thumbnails.add(thumbnail);
        }
        if (thumbnails.get(0) == null) {
            System.out.println("oh this guy is null");
        }
        return thumbnails;
    }

    public void back(Album album) {
        if (album.getPhotos() == null) {
            album.delete();
        }
        album.back();
    }

    public void injectAlbumController(AlbumController2 ac) {
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
            System.out.println("o no bad thumbnail in AM2");
            e.printStackTrace();
        }
        return photoBox;
    }
}
