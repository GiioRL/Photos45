package main.java.album.customDialogs;

import java.util.Collection;

import javafx.geometry.Pos;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import main.java.util.Tag;

public class RemoveTagDialog extends Dialog<RemoveTagDialog.TagData> {
    
    private final GridPane grid;
    private final Label typeLabel, valueLabel;
    private final ComboBox<String> typeBox, valueBox;

    public RemoveTagDialog(Collection<Tag> tags) {
        super();
        DialogPane pane = this.getDialogPane();

        this.typeBox = new ComboBox<String>();
        this.typeBox.setMinWidth(150.0);
        if (pane != null) {
            for (Tag t: tags)
                if (!this.typeBox.getItems().contains(t.getType()))
                    this.typeBox.getItems().add(t.getType());
        }
        this.typeBox.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(this.typeBox, Priority.ALWAYS);
        GridPane.setFillWidth(this.typeBox, true);

        this.valueBox = new ComboBox<String>();
        this.valueBox.setMinWidth(150.0);
        this.valueBox.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(this.valueBox, Priority.ALWAYS);
        GridPane.setFillWidth(this.valueBox, true);
        this.typeBox.getSelectionModel().selectedItemProperty().addListener((obs, oldType, newType) -> {
            this.valueBox.getItems().clear();
            for (Tag t: tags)
                if (t.getType().equals(newType))
                    this.valueBox.getItems().add(t.getValue());
            this.valueBox.getSelectionModel().clearSelection();
        });

        this.typeLabel = createContentLabel("Tag type:");
        this.typeLabel.setPrefWidth(-1.0);

        this.valueLabel = createContentLabel("Tag value:");
        this.valueLabel.setPrefWidth(-1.0);

        this.grid = new GridPane();
        this.grid.setHgap(10.0);
        this.grid.setMaxWidth(Double.MAX_VALUE);
        this.grid.setAlignment(Pos.CENTER_LEFT);
        pane.contentTextProperty().addListener((var1x) -> {
            this.updateGrid();
        });

        this.setHeaderText("Remove Tag");
        pane.getStyleClass().add("text-input-dialog");
        pane.getButtonTypes().addAll(new ButtonType[]{ButtonType.OK, ButtonType.CANCEL});
        this.updateGrid();
        
        this.setResultConverter((buttonType) -> {
            ButtonBar.ButtonData buttonData = buttonType == null ? null : buttonType.getButtonData();
            return buttonData == ButtonData.OK_DONE ? new TagData(typeBox.getSelectionModel().getSelectedItem(), valueBox.getSelectionModel().getSelectedItem()) : null;
        });
    }

    private void updateGrid() {
        this.grid.getChildren().clear();
        this.grid.add(this.typeLabel, 0, 0);
        this.grid.add(this.typeBox, 1, 0);
        this.grid.add(this.valueLabel, 0, 1);
        this.grid.add(this.valueBox, 1, 1);
        this.getDialogPane().setContent(this.grid);
    }

    private Label createContentLabel(String label) {
        return new Label(label);
    }

    public class TagData {
        private String type, value;

        public TagData(String type, String value) {
            this.type = type;
            this.value = value;
        }

        public String getType() { return type; }
        public String getValue() { return value; }
    }
}
