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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import main.java.util.Tag;

public class UserCredentialsDialog extends Dialog<UserCredentialsDialog.CredData> {
    
    private final GridPane grid;
    private final Label usernameLabel, passwordLabel;
    private final TextField usernameField;
    private final PasswordField passwordField;

    public UserCredentialsDialog() {
        super();
        DialogPane pane = this.getDialogPane();

        this.usernameField = new TextField("");
        this.usernameField.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(this.usernameField, Priority.ALWAYS);
        GridPane.setFillWidth(this.usernameField, true);

        this.passwordField = new PasswordField();
        this.passwordField.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(this.passwordField, Priority.ALWAYS);
        GridPane.setFillWidth(this.passwordField, true);

        this.usernameLabel = createContentLabel("Username:");
        this.usernameLabel.setPrefWidth(-1.0);

        this.passwordLabel = createContentLabel("Password:");
        this.passwordLabel.setPrefWidth(-1.0);

        this.grid = new GridPane();
        this.grid.setHgap(10.0);
        this.grid.setMaxWidth(Double.MAX_VALUE);
        this.grid.setAlignment(Pos.CENTER_LEFT);
        pane.contentTextProperty().addListener((var1x) -> {
            this.updateGrid();
        });

        this.setHeaderText("Add User");
        pane.getStyleClass().add("text-input-dialog");
        pane.getButtonTypes().addAll(new ButtonType[]{ButtonType.OK, ButtonType.CANCEL});
        this.updateGrid();

        this.setResultConverter((buttonType) -> {
            ButtonBar.ButtonData buttonData = buttonType == null ? null : buttonType.getButtonData();
            return buttonData == ButtonData.OK_DONE ? new CredData(usernameField.getText(), passwordField.getText()) : null;
        });
    }

    private void updateGrid() {
        this.grid.getChildren().clear();
        this.grid.add(this.usernameLabel, 0, 0);
        this.grid.add(this.usernameField, 1, 0);
        this.grid.add(this.passwordLabel, 0, 1);
        this.grid.add(this.passwordField, 1, 1);
        this.getDialogPane().setContent(this.grid);
    }

    private Label createContentLabel(String label) {
        return new Label(label);
    }

    public class CredData {
        private String username, password;

        public CredData(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() { return username; }
        public String getPassword() { return password; }
    }
}
