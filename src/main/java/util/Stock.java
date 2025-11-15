package main.java.util;
// 
// import java.util.ArrayList;
import java.io.File;

import main.java.album.AlbumModel;

public class Stock extends User {

    private AlbumModel albumModel = AlbumModel.getInstance();
    
    public Stock() {
        username = "stock";
        password = "stock";
        tags.add(new Tag("location", null));
        tags.add(new Tag("location", "home"));
        tags.add(new Tag("location", "Rutgers University"));
        tags.add(new Tag("location", "background"));
        tags.add(new Tag("person", null));
        tags.add(new Tag("person", "Rohit"));
        tags.add(new Tag("pet", null));
        tags.add(new Tag("pet", "Monkey"));
        tags.add(new Tag("pet", "Pumpkin"));
        String resFolder = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "StockPhotos" + File.separator;
        photos.add(albumModel.createPhoto(resFolder + "Monk_with_Munk.jpg", "Monk with Munk"));
        photos.add(albumModel.createPhoto(resFolder + "Monkey_Fall.jpg", "Monkey Fall"));
        photos.add(albumModel.createPhoto(resFolder + "Pumpkin.jpg", "Pumpkin"));
        photos.add(albumModel.createPhoto(resFolder + "Skill_Issue.jpg", "Pool"));
        photos.add(albumModel.createPhoto(resFolder + "Costa_Rica_Background.jpg", "Costa Rica"));
        photos.add(albumModel.createPhoto(resFolder + "Forest_Background.jpg", "Forest"));
        photos.add(albumModel.createPhoto(resFolder + "Mountain_Background.jpg", "Mountain"));
        photos.add(albumModel.createPhoto(resFolder + "Ocean_Background.jpg", "Ocean"));
        albums.add(new Album(photos, "stock", this));
    }
    
}
