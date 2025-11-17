package main.java.util;

import java.util.ArrayList;
import java.io.File;

import main.java.album.AlbumModel;

public class Stock extends User {

    private AlbumModel albumModel = AlbumModel.getInstance();
    
    public Stock() {
        username = "stock";
        password = "stock";
        tags.add(new Tag("Location", null)); //we need to add these when a photo is added with a tag i think
        tags.add(new Tag("Location", "Home"));
        tags.add(new Tag("Location", "Rutgers University"));
        tags.add(new Tag("Location", "Background"));
        tags.add(new Tag("Person", null));
        tags.add(new Tag("Person", "Rohit"));
        tags.add(new Tag("Pet", null));
        tags.add(new Tag("Pet", "Monkey"));
        tags.add(new Tag("Pet", "Pumpkin"));
        String resFolder = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "StockPhotos" + File.separator;
        ArrayList<Photo> photos = new ArrayList<>();
        photos.add(albumModel.createPhoto(resFolder + "Monk_with_Munk.jpg", "Monk with Munk", new ArrayList<Tag>() {{
            add(new Tag("Location", "Home"));
            add(new Tag("Pet", "Monkey"));
        }}));
        photos.add(albumModel.createPhoto(resFolder + "Monkey_Fall.jpg", "Monkey Fall", new ArrayList<Tag>() {{
            add(new Tag("Location", "Home"));
            add(new Tag("Pet", "Monkey"));
        }}));
        photos.add(albumModel.createPhoto(resFolder + "Pumpkin.jpg", "Pumpkin", new ArrayList<Tag>() {{
            add(new Tag("Location", "Home"));
            add(new Tag("Pet", "Pumpkin"));
        }}));
        photos.add(albumModel.createPhoto(resFolder + "Skill_Issue.jpg", "Pool", new ArrayList<Tag>() {{
            add(new Tag("Location", "Rutgers University"));
            add(new Tag("Person", "Rohit"));
        }}));
        photos.add(albumModel.createPhoto(resFolder + "Costa_Rica_Background.jpg", "Costa Rica", new ArrayList<Tag>() {{
            add(new Tag("Location", "Background"));
        }}));
        photos.add(albumModel.createPhoto(resFolder + "Forest_Background.jpg", "Forest", new ArrayList<Tag>() {{
            add(new Tag("Location", "Background"));
        }}));
        photos.add(albumModel.createPhoto(resFolder + "Mountain_Background.jpg", "Mountain", new ArrayList<Tag>() {{
            add(new Tag("Location", "Background"));
        }}));
        photos.add(albumModel.createPhoto(resFolder + "Ocean_Background.jpg", "Ocean", new ArrayList<Tag>() {{
            add(new Tag("Location", "Background"));
        }}));
        albums.add(new Album(photos, "Stock", this));
    }
    
}
