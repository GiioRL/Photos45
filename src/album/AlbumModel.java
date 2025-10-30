package album;

import java.util.ArrayList;
import java.util.Calendar;

import util.*;

public class AlbumModel {
    

    private Photo createPhoto(String location) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND, 0);
        return new Photo(calendar, null, location, "");
    }

    private Album createAlbum(ArrayList<Photo> photos) {
        return new Album(photos);
    }
}
