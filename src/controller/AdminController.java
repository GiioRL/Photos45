package controller;

import javafx.scene.control.Alert;
import model.User;
import view.album.customDialogs.UserCredentialsDialog;

public class AdminController {

    private static AdminController instance;

    private AdminController() {}

    public static AdminController getInstance() {
        if (instance == null) {
            instance = new AdminController();
        }
        return instance;
    }

    public void addUser(String username, String password) {
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
    }

    public void deleteUser(String username) {
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
    }
}
