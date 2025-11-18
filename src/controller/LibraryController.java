package controller;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashSet;

import controller.album.AlbumController;
import javafx.scene.Node;
// import main.java.util.*;
import model.*;

public class LibraryController {

    private ArrayList<Tag> tags;
    private static LibraryController instance;
    private static AlbumController albumModel = AlbumController.getInstance();

    private LibraryController() {}

    public static LibraryController getInstance() {
        if (instance == null) {
            instance = new LibraryController();
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
                            albumPhotos.add(photo);
                        }
                    }
                }
            }
        }
        
        return new Album(new ArrayList<Photo>(albumPhotos), "Unnamed album", user);
    }

    public Album tagSearch(String type1, String value1, String conjunction, String type2, String value2, User user) {
        Album album1 = tagSearch(type1, value1, user);
        if (conjunction == null) {
            return album1;
        } else {
            Album album2 = tagSearch(type2, value2, user);
            ArrayList<Photo> album1Photos = album1.getPhotos();
            ArrayList<Photo> album2Photos = album2.getPhotos();
            ArrayList<Photo> albumPhotos = new ArrayList<Photo>();
            if (conjunction.equals("And")) {
                for (Photo photo: album1Photos) {
                    if (album2Photos.contains(photo)) {
                        albumPhotos.add(photo);
                    }
                }
                return trimDuplicates(new Album(albumPhotos, "Unnamed Album", user));
            } else if (conjunction.equals("Or")) {
                for (Photo photo: album2Photos) {
                    album1Photos.add(photo);
                }
                return trimDuplicates(new Album(album1Photos, "Unnamed Album", user));
            } else {
                System.out.println("invalid conjunction");
                return null;
            }
        }
    }

    private Album trimDuplicates(Album album) {
        ArrayList<Photo> photos = album.getPhotos();
        if (photos.size() <= 1) {
            return album;
        }
        photos.sort(Comparator.comparing(Photo::getLocation));
        ArrayList<Photo> albumPhotos = new ArrayList<Photo>();
        albumPhotos.add(photos.get(0));
        for (int i = 1; i < photos.size(); i++) {
            if (photos.get(i).getLocation().compareTo(photos.get(i-1).getLocation()) != 0) {
                albumPhotos.add(photos.get(i));
            }
        }
        return new Album(albumPhotos, album.getName(), album.getUser());
    }

    public Album dateSearch(LocalDate from, LocalDate to, User user) {
        if (from == null || to == null) {
            return null;
        }
        HashSet<Photo> albumPhotos = new HashSet<Photo>();
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.set(from.getYear(), from.getMonthValue()-1, from.getDayOfMonth(), fromCalendar.getMinimum(Calendar.HOUR_OF_DAY), fromCalendar.getMinimum(Calendar.MINUTE), fromCalendar.getMinimum(Calendar.SECOND)); // set to beginning of day
        Calendar toCalendar = Calendar.getInstance();
        toCalendar.set(to.getYear(), to.getMonthValue()-1, to.getDayOfMonth(), fromCalendar.getMaximum(Calendar.HOUR_OF_DAY), fromCalendar.getMaximum(Calendar.MINUTE), fromCalendar.getMaximum(Calendar.SECOND)); // set to end of day
        // ArrayList<Photo> photos = user.getPhotos();
        ArrayList<Photo> photos = new ArrayList<>();
        for (Album album: user.getAlbums())
            photos.addAll(album.getPhotos());
        String resFolder = System.getProperty("user.dir") + File.separator + "src" + File.separator + "resources" + File.separator;
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
