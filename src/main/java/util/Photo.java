package main.java.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Calendar;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import main.java.album.photoThumbnail.PhotoThumbnailController;

public class Photo {

    private Calendar date;
    private ArrayList<Tag> tags;
    private String location;
    private String caption;
    private Image image;
    private Node thumbnail;
    private PhotoThumbnailController tc;
    
    public Photo(Calendar date, ArrayList<Tag> tags, String location, String caption) {
        this.date = date;
        this.tags = tags;
        this.location = location;
        this.caption = caption;
        try {
            image = createImage();
        } catch (IOException e) {
            System.out.println("oops hehe");
        }
        createThumbnail();
    }

    private Image createImage() throws IOException {
        return new Image(Photo.class.getResourceAsStream(location));
    }

    public void createThumbnail() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../album/photoThumbnail/PhotoThumbnailView.fxml"));
            thumbnail = loader.load();
            if (thumbnail == null) {
                System.out.println("why null???");
            }
            tc = loader.getController();
            tc.injectPhoto(this);
        } catch (Exception e) {
            System.out.println("o no bad thumbnail");
            e.printStackTrace();
        }
    }

    public Calendar getDate() {
        return date;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public String getLocation() {
        return location;
    }

    public String getCaption() {
        return caption;
    }

    public Image getImage() {
        return image;
    }

    public Node getThumbnail() {
        return thumbnail;
    }
}
