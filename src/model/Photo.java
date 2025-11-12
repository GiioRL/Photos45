package model;

import java.util.ArrayList;
import java.util.Calendar;

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
}
