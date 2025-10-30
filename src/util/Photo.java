<<<<<<< HEAD:src/model/Photo.java
package model;
=======
package util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
>>>>>>> dev:src/util/Photo.java

import java.util.ArrayList;
import java.util.Calendar;

<<<<<<< HEAD:src/model/Photo.java
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.io.IOException;

public class Photo {
    Calendar date;
    ArrayList<Tag> tags;
    String location, caption;

    public Photo(String location, String caption) {
        try {
            this.location = location;
            this.caption = caption;
        }
        catch (IOException e) {}
    }
=======
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Photo {

    private Calendar date;
    private ArrayList<Tag> tags;
    private String location;
    private String caption;
    private ImageView imageView;
    
    public Photo(Calendar date, ArrayList<Tag> tags, String location, String caption) {
        this.date = date;
        this.tags = tags;
        this.location = location;
        this.caption = caption;
        try {
            imageView = createImageView();
        } catch (IOException e) {
            System.out.println("oops hehe");
        }
    }

    private ImageView createImageView() throws IOException {
        return new ImageView(new Image(new FileInputStream(location)));
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

    public ImageView getImageView() {
        return imageView;
    }

    // private ImageView createImageView() throws IOException {
    //     InputStream stream = new FileInputStream(location);
    //     Image image = new Image(stream);
    //     ImageView imageView = new ImageView();
    //     imageView.setImage(image);
    //     return null;
    // }
>>>>>>> dev:src/util/Photo.java
}
