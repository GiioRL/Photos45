package main.java.album;

import javafx.geometry.Pos;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class TagInputDialog extends Dialog<TagInputDialog.TagData> {

    private final GridPane grid;
    private final Label label1, label2;
    private final TextField textField1, textField2;

    public TagInputDialog() {
        super();
        DialogPane var2 = this.getDialogPane();
        this.textField1 = new TextField("");
        this.textField1.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(this.textField1, Priority.ALWAYS);
        GridPane.setFillWidth(this.textField1, true);
        this.textField2 = new TextField("");
        this.textField2.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(this.textField2, Priority.ALWAYS);
        GridPane.setFillWidth(this.textField2, true);
        this.label1 = createContentLabel("Tag type:");
        this.label1.setPrefWidth(-1.0);
        // this.label1.textProperty().bind(var2.contentTextProperty());
        this.label2 = createContentLabel("Tag value:");
        this.label2.setPrefWidth(-1.0);
        // this.label2.textProperty().bind(var2.contentTextProperty());
        this.grid = new GridPane();
        this.grid.setHgap(10.0);
        this.grid.setMaxWidth(Double.MAX_VALUE);
        this.grid.setAlignment(Pos.CENTER_LEFT);
        var2.contentTextProperty().addListener((var1x) -> {
            this.updateGrid();
        });
        // this.setTitle(ControlResources.getString("Dialog.confirm.title"));
        // var2.setHeaderText(ControlResources.getString("Dialog.confirm.header"));
        var2.getStyleClass().add("text-input-dialog");
        var2.getButtonTypes().addAll(new ButtonType[]{ButtonType.OK, ButtonType.CANCEL});
        this.updateGrid();
        this.setResultConverter((var1x) -> {
            ButtonBar.ButtonData var3 = var1x == null ? null : var1x.getButtonData();
            return var3 == ButtonData.OK_DONE ? new TagData(textField1.getText(), textField2.getText()) : null;
        });
    }

    private void updateGrid() {
        this.grid.getChildren().clear();
        this.grid.add(this.label1, 0, 0);
        this.grid.add(this.textField1, 1, 0);
        this.grid.add(this.label2, 0, 1);
        this.grid.add(this.textField2, 1, 1);
        this.getDialogPane().setContent(this.grid);
    }

    private Label createContentLabel(String var0) {
        // Label var1 = new Label(var0);
        // var1.setMaxWidth(Double.MAX_VALUE);
        // var1.setMaxHeight(Double.MAX_VALUE);
        // var1.getStyleClass().add("content");
        // var1.setWrapText(true);
        // var1.setPrefWidth(360.0);
        // return var1;
        return new Label(var0);
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
