package main.java.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.java.App;
import main.java.album.customDialogs.UserCredentialsDialog;
import main.java.util.User;

public class AdminController {

    @FXML
    private Button buttonAddUser;

    @FXML
    private Button buttonDeleteUser;

    @FXML
    private Button buttonListUsers;

    @FXML
    private Text display;

    // @FXML
    // private TextField tfPassword;

    // @FXML
    // private TextField tfUsername;

    @FXML
    void listUsers() {
        String list = "Users:\n";
        for (User user: User.getUsers())
            list += user.getUsername() + '\n';
        display.setText(list);
    }

    @FXML
    void addUser() {
        display.setText("");
        new UserCredentialsDialog().showAndWait().ifPresent(credData -> {
            String username = credData.getUsername(), password = credData.getPassword();
            if (username.length() == 0) {
                // display.setText("Enter a username to proceed.");
                Alert warning = new Alert(Alert.AlertType.WARNING, "Enter a username to proceed.");
                warning.setHeaderText("Invalid Username");
                warning.showAndWait();
                return;
            }
            else if (password.length() == 0) {
                // display.setText("Enter a password to proceed.");
                Alert warning = new Alert(Alert.AlertType.WARNING, "Enter a password to proceed.");
                warning.setHeaderText("Invalid Password");
                warning.showAndWait();
                return;
            }
            else {
                try {
                    new User(username, password);
                    // display.setText("User added successsfully!");
                    Alert info = new Alert(Alert.AlertType.INFORMATION, "User added successfully!");
                    info.setHeaderText("User Added");
                    info.showAndWait();
                }
                catch (Exception e) {
                    Alert warning = new Alert(Alert.AlertType.WARNING, e.getMessage());
                    warning.setHeaderText("Unable To Create User");
                    warning.showAndWait();
                    return;
                }
            }
        });
    }

    @FXML
    void deleteUser() {
        // String username = tfUsername.getText();
        display.setText("");
        TextInputDialog usernameDialog = new TextInputDialog();
        usernameDialog.setContentText("Username:");
        usernameDialog.setHeaderText("Delete User");
        usernameDialog.showAndWait().ifPresent(username -> {
            if (username.length() == 0) {
                // display.setText("Enter a username to proceed.");
                Alert warning = new Alert(Alert.AlertType.WARNING, "Enter a username to proceed.");
                warning.setHeaderText("Invalid Username");
                warning.showAndWait();
                return;
            }
            else if (username.equals("admin") || username.equals("stock")) {
                Alert warning = new Alert(Alert.AlertType.WARNING, "Cannot remove " + username + " user.");
                warning.setHeaderText("Cannot Remove User");
                warning.showAndWait();
                return;
            }
            else {
                User oldUser = User.getUser(username);
                if (oldUser == null) {
                    // display.setText("User with that username does not exist.");
                    Alert warning = new Alert(Alert.AlertType.WARNING, "User with that username does not exist.");
                    warning.setHeaderText("User Does Not Exist");
                    warning.showAndWait();
                    return;
                }
                else {
                    User.getUsers().remove(oldUser);
                    // display.setText("User deleted successfully!");
                    Alert info = new Alert(Alert.AlertType.INFORMATION, "User deleted successfully!");
                    info.setHeaderText("User Deleted");
                    info.showAndWait();
                }
            }
        });
    }

    @FXML
    void logout() {
        Stage primaryStage = App.getStage();
        primaryStage.setScene(App.getLoginScene());
    }

    @FXML
    void quit() {
        System.exit(0);
    }
}
