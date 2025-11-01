package main.java.library;

import java.util.ArrayList;

import javafx.scene.Node;

import main.java.album.AlbumModel;
import main.java.album.albumThumbnail.AlbumThumbnailController;
import main.java.util.*;

public class LibraryModel {

    private ArrayList<Tag> tags;
    private static LibraryModel instance;

    private LibraryModel() {}

    public static LibraryModel getInstance() {
        if (instance == null) {
            instance = new LibraryModel();
        }
        return instance;
    }
    
    public AlbumModel createAlbum(ArrayList<Photo> photos) {
        return null;
    }

    public void createAlbum(User user) {
        createAlbum(user, "defualt");
    }

    public void createAlbum(User user, String name) {
        Album album = new Album(user, name);
        user.addAlbum(album);
        album.start();
    }

    public ArrayList<Node> getThumbnails(User user) {
        ArrayList<Node> thumbnails = new ArrayList<Node>();
        System.out.println("Albums: " + user.getAlbums().size());
        for (Album album: user.getAlbums()) {
            thumbnails.add(album.getThumbnail());
        }
        if (thumbnails.get(0) == null) {
            System.out.println("oh this guy is null");
        }
        return thumbnails;
    }
}
