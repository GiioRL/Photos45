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
        String resFolder = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "StockPhotos" + File.separator;
        photos.add(albumModel.createPhoto(resFolder + "Monk_with_Munk.jpg"));
        photos.add(albumModel.createPhoto(resFolder + "Monkey_Fall.jpg"));
        photos.add(albumModel.createPhoto(resFolder + "Pumpkin.jpg"));
        photos.add(albumModel.createPhoto(resFolder + "Skill_Issue.jpg"));
        photos.add(albumModel.createPhoto(resFolder + "Costa_Rica_Background.jpg"));
        photos.add(albumModel.createPhoto(resFolder + "Forest_Background.jpg"));
        photos.add(albumModel.createPhoto(resFolder + "Mountain_Background.jpg"));
        photos.add(albumModel.createPhoto(resFolder + "Ocean_Background.jpg"));
        tags.add(new Tag("Test", "Value"));

        // photos.add(albumModel.createPhoto("/main/resources/StockPhotos/Monk_with_Munk.jpg"));
        // photos.add(albumModel.createPhoto("/main/resources/StockPhotos/Monkey_Fall.jpg"));
        // photos.add(albumModel.createPhoto("/main/resources/StockPhotos/Pumpkin.jpg"));
        // photos.add(albumModel.createPhoto("/main/resources/StockPhotos/Skill_Issue.jpg"));
        // photos.add(albumModel.createPhoto("/main/resources/StockPhotos/Costa_Rica_Background.jpg"));
        // photos.add(albumModel.createPhoto("/main/resources/StockPhotos/Forest_Background.jpg"));
        // photos.add(albumModel.createPhoto("/main/resources/StockPhotos/Mountain_Background.jpg"));
        // photos.add(albumModel.createPhoto("/main/resources/StockPhotos/Ocean_Background.jpg"));
        
        // photos.add(albumModel.createPhoto("/main/resources/StockPhotos/Monk_with_Munk.jpg"));
        // photos.add(albumModel.createPhoto("/main/resources/StockPhotos/Monk_with_Munk.jpg"));
        // photos.add(albumModel.createPhoto("/main/resources/StockPhotos/Monk_with_Munk.jpg"));
        // photos.add(albumModel.createPhoto("/main/resources/StockPhotos/Monk_with_Munk.jpg"));
        // photos.add(albumModel.createPhoto("/main/resources/StockPhotos/Monk_with_Munk.jpg"));
        // photos.add(albumModel.createPhoto("/main/resources/StockPhotos/Monk_with_Munk.jpg"));
        albums.add(new Album(photos, "stock", this));
    }
    
}
