package main.java.album.customDialogs;

import java.util.Collection;

import javafx.geometry.Pos;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.Pair;
import main.java.util.Tag;

public class CreateTagDialog extends Dialog<Pair<String, Boolean>> {
    
    private final GridPane grid;
    private final Label typeLabel, multiValueLabel;
    private final TextField typeField;
    private final CheckBox muitiValueCheck;

    public CreateTagDialog() {
        super();
        DialogPane pane = this.getDialogPane();

        this.typeField = new TextField("");
        this.typeField.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(this.typeField, Priority.ALWAYS);
        GridPane.setFillWidth(this.typeField, true);

        this.muitiValueCheck = new CheckBox();

        this.typeLabel = createContentLabel("Tag name:");
        this.typeLabel.setPrefWidth(-1.0);

        this.multiValueLabel = createContentLabel("Multiple values:");
        this.multiValueLabel.setPrefWidth(-1.0);

        this.grid = new GridPane();
        this.grid.setHgap(10.0);
        this.grid.setMaxWidth(Double.MAX_VALUE);
        this.grid.setAlignment(Pos.CENTER_LEFT);
        pane.contentTextProperty().addListener((var1x) -> {
            this.updateGrid();
        });

        this.setHeaderText("Create Tag");
        pane.getStyleClass().add("text-input-dialog");
        pane.getButtonTypes().addAll(new ButtonType[]{ButtonType.OK, ButtonType.CANCEL});
        this.updateGrid();

        this.setResultConverter((buttonType) -> {
            ButtonBar.ButtonData buttonData = buttonType == null ? null : buttonType.getButtonData();
            return buttonData == ButtonData.OK_DONE ? new Pair<String, Boolean>(typeField.getText(), muitiValueCheck.isSelected()) : null;
        });
    }

    private void updateGrid() {
        this.grid.getChildren().clear();
        this.grid.add(this.typeLabel, 0, 0);
        this.grid.add(this.typeField, 1, 0);
        this.grid.add(this.multiValueLabel, 0, 1);
        this.grid.add(this.muitiValueCheck, 1, 1);
        this.getDialogPane().setContent(this.grid);
    }

    private Label createContentLabel(String label) {
        return new Label(label);
    }
}
