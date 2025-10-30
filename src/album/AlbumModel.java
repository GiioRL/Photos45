package album;

import java.util.ArrayList;
import java.util.Calendar;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import library.LibraryController;
import main.App;
import util.*;

public class AlbumModel {

    private ArrayList<Photo> photos;
    private Stage primaryStage;
    private LibraryController lc;
    private AlbumController ac;

    public AlbumModel() {}

    public AlbumModel(ArrayList<Photo> photos) {
        this.photos = photos;
    }

    public Photo createPhoto(Calendar calendar, ArrayList<Tag> tags, String location, String caption) {
        return new Photo(calendar, tags, location, caption);
    }

    public Photo createPhoto(String location) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND, 0);
        return new Photo(calendar, null, location, "");
    }

    public void start() {
        primaryStage = App.getStage();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/album/AlbumView.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        } catch (Exception e) {
            System.out.println("err there was an exception");
            e.printStackTrace();
        }
    }
}
