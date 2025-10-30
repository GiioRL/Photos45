package util;

import java.util.ArrayList;

public class Album {

    private ArrayList<Photo> photos;

    public Album(ArrayList<Photo> photos) {
        this.photos = photos;
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
    }
    
}
