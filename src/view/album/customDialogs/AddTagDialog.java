package view.album.customDialogs;

import java.util.Collection;

import javafx.geometry.Pos;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import model.Tag;

public class AddTagDialog extends Dialog<AddTagDialog.TagData> {
    
    private final GridPane grid;
    private final Label typeLabel, valueLabel;
    private final ComboBox<String> typeBox;
    private final TextField valueField;

    public AddTagDialog(Collection<Tag> tags) {
        super();
        DialogPane pane = this.getDialogPane();

        // this.textField1 = new TextField("");
        // this.textField1.setMaxWidth(Double.MAX_VALUE);
        // GridPane.setHgrow(this.textField1, Priority.ALWAYS);
        // GridPane.setFillWidth(this.textField1, true);
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

        this.valueField = new TextField("");
        this.valueField.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(this.valueField, Priority.ALWAYS);
        GridPane.setFillWidth(this.valueField, true);

        this.typeLabel = createContentLabel("Tag type:");
        this.typeLabel.setPrefWidth(-1.0);
        // this.label1.textProperty().bind(var2.contentTextProperty());

        this.valueLabel = createContentLabel("Tag value:");
        this.valueLabel.setPrefWidth(-1.0);
        // this.label2.textProperty().bind(var2.contentTextProperty());

        this.grid = new GridPane();
        this.grid.setHgap(10.0);
        this.grid.setMaxWidth(Double.MAX_VALUE);
        this.grid.setAlignment(Pos.CENTER_LEFT);
        pane.contentTextProperty().addListener((var1x) -> {
            this.updateGrid();
        });

        // this.setTitle(ControlResources.getString("Dialog.confirm.title"));
        // var2.setHeaderText(ControlResources.getString("Dialog.confirm.header"));
        this.setHeaderText("Add Tag");
        pane.getStyleClass().add("text-input-dialog");
        pane.getButtonTypes().addAll(new ButtonType[]{ButtonType.OK, ButtonType.CANCEL});
        this.updateGrid();

        this.setResultConverter((buttonType) -> {
            ButtonBar.ButtonData buttonData = buttonType == null ? null : buttonType.getButtonData();
            return buttonData == ButtonData.OK_DONE ? new TagData(typeBox.getSelectionModel().getSelectedItem(), valueField.getText()) : null;
        });
    }

    private void updateGrid() {
        this.grid.getChildren().clear();
        this.grid.add(this.typeLabel, 0, 0);
        this.grid.add(this.typeBox, 1, 0);
        this.grid.add(this.valueLabel, 0, 1);
        this.grid.add(this.valueField, 1, 1);
        this.getDialogPane().setContent(this.grid);
    }

    private Label createContentLabel(String label) {
        // Label var1 = new Label(var0);
        // var1.setMaxWidth(Double.MAX_VALUE);
        // var1.setMaxHeight(Double.MAX_VALUE);
        // var1.getStyleClass().add("content");
        // var1.setWrapText(true);
        // var1.setPrefWidth(360.0);
        // return var1;
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
