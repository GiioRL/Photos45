package main.java.album.customDialogs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Window;
import main.java.util.Photo;
import main.java.util.Tag;

public class ImageDialog extends Dialog<Object> {
    
    @FXML
    private ImageView imageView;

    @FXML
    private Text tagText;

    public ImageDialog(Photo photo) {
        super();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/main/java/album/customDialogs/ImageDialog.fxml"));
            loader.setController(this);
            DialogPane pane = loader.load();

            this.imageView.setImage(photo.getImage());
            pane.getButtonTypes().add(ButtonType.OK);

            String text = "Tags:\n";
            if (photo.getTags() != null) {
                HashMap<String, String> tagLines = new HashMap<>();
                for (Tag t: photo.getTags()) {
                    if (!tagLines.containsKey(t.getType()))
                        tagLines.put(t.getType(), t.getValue());
                    else
                        tagLines.put(t.getType(), tagLines.get(t.getType()) + ", " + t.getValue()); 
                }
                for (Map.Entry<String, String> line: tagLines.entrySet())
                    text += line.getKey() + ": " + line.getValue() + "\n";
            }
            this.tagText.setText(text);
            this.setDialogPane(pane);
            
            // pane.contentTextProperty().addListener((var1x) -> {
            //     this.updateGrid();
            // });

            String header = photo.getCaption().length() > 0 ? photo.getCaption() : "(Uncaptioned)";
            header += " - " + photo.getDateString();
            this.setHeaderText(header);
            // pane.getStyleClass().add("text-input-dialog");
            // pane.getButtonTypes().addAll(new ButtonType[]{ButtonType.OK, ButtonType.CANCEL});

            this.setResultConverter((buttonType) -> {
                return null;
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
