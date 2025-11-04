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
        photos.add(albumModel.createPhoto("/main/resources/StockPhotos/Monkey_Fall.jpg"));
        photos.add(albumModel.createPhoto("/main/resources/StockPhotos/Pumpkin.jpg"));
        photos.add(albumModel.createPhoto("/main/resources/StockPhotos/Skill_Issue.jpg"));
        photos.add(albumModel.createPhoto("/main/resources/StockPhotos/Costa_Rica_Background.jpg"));
        photos.add(albumModel.createPhoto("/main/resources/StockPhotos/Forest_Background.jpg"));
        photos.add(albumModel.createPhoto("/main/resources/StockPhotos/Mountain_Background.jpg"));
        photos.add(albumModel.createPhoto("/main/resources/StockPhotos/Ocean_Background.jpg"));
        // photos.add(albumModel.createPhoto("/main/resources/StockPhotos/Monk_with_Munk.jpg"));
        // photos.add(albumModel.createPhoto("/main/resources/StockPhotos/Monk_with_Munk.jpg"));
        // photos.add(albumModel.createPhoto("/main/resources/StockPhotos/Monk_with_Munk.jpg"));
        // photos.add(albumModel.createPhoto("/main/resources/StockPhotos/Monk_with_Munk.jpg"));
        // photos.add(albumModel.createPhoto("/main/resources/StockPhotos/Monk_with_Munk.jpg"));
        // photos.add(albumModel.createPhoto("/main/resources/StockPhotos/Monk_with_Munk.jpg"));
        albums.add(new Album(photos, "stock", this));
    }
    
}
