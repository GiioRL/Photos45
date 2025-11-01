package main.java.library;

import java.util.ArrayList;

import main.java.album.AlbumModel;
import main.java.util.*;

public class LibraryModel {

    private ArrayList<Tag> tags;
    
    public AlbumModel createAlbum(ArrayList<Photo> photos) {
        return null;
    }

    public void createAlbum(User user) {
        Album album = new Album(user);
        user.addAlbum(album);
        album.start();
    }
}
