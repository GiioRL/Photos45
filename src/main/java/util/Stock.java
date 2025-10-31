package main.java.util;
// 
// import java.util.ArrayList;

import main.java.album.AlbumModel;

public class Stock extends User {

    private AlbumModel albumModel = new AlbumModel();
    
    public Stock() {
        username = "stock";
        password = "stock";
        photos.add(albumModel.createPhoto("/resources/Monk_with_Munk.jpg"));
    }
    
}
