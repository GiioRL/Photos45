package main.java.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.java.App;
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

    @FXML
    private TextField tfPassword;

    @FXML
    private TextField tfUsername;

    @FXML
    void listUsers() {
        String list = "";
        for (User user: User.getUsers())
            list += user.getUsername() + '\n';
        display.setText(list);
    }

    @FXML
    void addUser() {
        String username = tfUsername.getText(), password = tfPassword.getText();
        if (username.length() == 0)
            display.setText("Enter a username to proceed.");
        else if (password.length() == 0)
            display.setText("Enter a password to proceed.");
        else {
            try {
                new User(username, password);
                display.setText("User added successsfully!");
            }
            catch (Exception e) {
                display.setText(e.getMessage());
            }
        }
    }

    @FXML
    void deleteUser() {
        String username = tfUsername.getText();
        if (username.length() == 0)
            display.setText("Enter a username to proceed.");
        else if (username.equals("admin"))
            display.setText("Cannot remove admin user.");
        else if (username.equals("stock"))
            display.setText("Cannot remove stock user.");
        else {
            User oldUser = User.getUser(username);
            if (oldUser == null)
                display.setText("User with that username does not exist.");
            else {
                User.getUsers().remove(oldUser);
                display.setText("User deleted successfully!");
            }
        }
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
