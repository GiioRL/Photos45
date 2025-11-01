package main.java.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Calendar;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Photo {

    private Calendar date;
    private ArrayList<Tag> tags;
    private String location;
    private String caption;
    private Image image;
    
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
    }

    private Image createImage() throws IOException {
        return new Image(Photo.class.getResourceAsStream(location));
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

    // private ImageView createImageView() throws IOException {
    //     InputStream stream = new FileInputStream(location);
    //     Image image = new Image(stream);
    //     ImageView imageView = new ImageView();
    //     imageView.setImage(image);
    //     return null;
    // }
}
