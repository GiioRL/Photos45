package main.java.library;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashSet;

import javafx.scene.Node;

import main.java.album.AlbumModel;
import main.java.util.*;

public class LibraryModel {

    private ArrayList<Tag> tags;
    private static LibraryModel instance;
    private static AlbumModel albumModel = AlbumModel.getInstance();

    private LibraryModel() {}

    public static LibraryModel getInstance() {
        if (instance == null) {
            instance = new LibraryModel();
        }
        return instance;
    }

    public void createAlbum(User user) {
        createAlbum(user, "Unnamed album");
    }

    public void createAlbum(User user, String name) {
        Album album = new Album(user, name);
        user.addAlbum(album);
        album.start();
    }

    public ArrayList<Node> getThumbnails(User user) {
        ArrayList<Node> thumbnails = new ArrayList<Node>();
        for (Album album: user.getAlbums()) {
            Node thumbnail = album.getThumbnail();
            
            thumbnails.add(thumbnail);
        }
        return thumbnails;
    }

    public ArrayList<String> getTypes(User user) {
        ArrayList<String> types = new ArrayList<String>();
        ArrayList<Tag> tags = user.getTags();
        for (Tag tag: tags) {
            if (tag.getValue() == null) {
                types.add(tag.getType());
            }
        }
        return types;
    }

    public ArrayList<String> getValues(User user, String type) {
        ArrayList<String> values = new ArrayList<String>();
        ArrayList<Tag> tags = user.getTags();
        for (Tag tag: tags) {
            if (tag.equals(new Tag(type, null))) {
                if (tag.getValue() != null) {
                    values.add(tag.getValue());
                }
            }
        }
        return values;
    }

    public Album tagSearch(String type, String value, User user) {
        if (type == null || value == null) {
            return null;
        }
        HashSet<Photo> albumPhotos = new HashSet<Photo>();
        Tag tag = new Tag(type, value);
        // ArrayList<Photo> photos = user.getPhotos();
        ArrayList<Photo> photos = new ArrayList<>();
        for (Album album: user.getAlbums())
            photos.addAll(album.getPhotos());
        for (Photo photo: photos) {
            ArrayList<Tag> photoTags = photo.getTags();
            if (photoTags != null) {
                if (photoTags.contains(tag)) {
                    for (Tag photoTag: photoTags) {
                        if (photoTag.tagEquals(tag)) {
                            System.out.println(photoTag);
                            System.out.println(tag);
                            System.out.println("adding photo");
                            albumPhotos.add(photo);
                        }
                    }
                }
            }
        }
        
        return new Album(new ArrayList<Photo>(albumPhotos), "Unnamed album", user);
    }

    public Album dateSearch(LocalDate from, LocalDate to, User user) {
        if (from == null || to == null) {
            return null;
        }
        HashSet<Photo> albumPhotos = new HashSet<Photo>();
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.set(from.getYear(), from.getMonthValue()-1, from.getDayOfMonth()); // set to beginning of day
        Calendar toCalendar = Calendar.getInstance();
        toCalendar.set(to.getYear(), to.getMonthValue()-1, to.getDayOfMonth()); // set to end of day
        // ArrayList<Photo> photos = user.getPhotos();
        ArrayList<Photo> photos = new ArrayList<>();
        for (Album album: user.getAlbums())
            photos.addAll(album.getPhotos());
        String resFolder = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator;
        Photo fromDummy = albumModel.createPhoto(resFolder + "dummyPhoto1.jpg", "fromDummy", null, fromCalendar.getTimeInMillis());
        Photo toDummy = albumModel.createPhoto(resFolder + "dummyPhoto2.jpg", "toDummy", null, toCalendar.getTimeInMillis());
        ArrayList<Photo> photoCopy = (ArrayList<Photo>)photos.clone();
        photoCopy.add(fromDummy);
        photoCopy.add(toDummy);
        photoCopy.sort(Comparator.comparing(Photo::getDate));
        for (int i = photoCopy.indexOf(fromDummy)+1; i < photoCopy.indexOf(toDummy); i++) {
            albumPhotos.add(photoCopy.get(i));
        }
        return new Album(new ArrayList<Photo>(albumPhotos), "Unnamed Album", user);
    }
}
