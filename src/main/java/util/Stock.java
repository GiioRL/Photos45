package main.java.util;
// 
// import java.util.ArrayList;

import main.java.album.AlbumModel;

public class Stock extends User {

    private AlbumModel albumModel = AlbumModel.getInstance();
    
    public Stock() {
        username = "stock";
        password = "stock";
        photos.add(albumModel.createPhoto("/main/resources/StockPhotos/Monk_with_Munk.jpg"));
        // ArrayList<Photo> stockPhotos = new ArrayList<Photo>();
        albums.add(new Album(photos, "stock"));
    }
    
}
