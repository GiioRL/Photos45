// package util;

// import java.util.ArrayList;

// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.stage.Stage;

// import album.AlbumController;
// import library.LibraryController;
// import main.App;

// public class Album {

//     private ArrayList<Photo> photos;
//     private Stage primaryStage;
//     private LibraryController lc;
//     private AlbumController ac;

//     public Album(LibraryController lc) {
//         this.lc = lc;
//         ac = new AlbumController(lc);
//     }

//     public Album(ArrayList<Photo> photos) {
//         this.photos = photos;
//         ac = new AlbumController(lc);
//     }

//     public ArrayList<Photo> getPhotos() {
//         return photos;
//     }
    
//     public void start() {
//         primaryStage = App.getStage();
//         Parent root;
//         try {
//             root = FXMLLoader.load(getClass().getResource("/album/AlbumView.fxml"));
//             Scene scene = new Scene(root);
//             primaryStage.setScene(scene);
//         } catch (Exception e) {
//             System.out.println("err there was an exception");
//             e.printStackTrace();
//         }
//     }
// }
