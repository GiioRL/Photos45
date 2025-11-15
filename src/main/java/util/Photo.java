package main.java.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Calendar;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;

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
        image = createImage();
        createThumbnail();
    }

    private Image createImage() {
        InputStream stream = null;
        try {
            stream = new FileInputStream(new File(location));
        } catch (IOException e) {
            Alert error = new Alert(Alert.AlertType.ERROR, "Photo not found. Please enter a valid photo file path.");
            error.setHeaderText("Photo Not Found");
            error.showAndWait();
        }
        return new Image(stream);
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
            System.out.println("o no bad thumbnail in photo");
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

    // Returns true if photos have same location - perhaps change in future, but this way is useful for preventing duplicate photo insert into album
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Photo))
            return false;
        return location.equals(((Photo) o).location);
    }
}
