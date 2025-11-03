package util;

import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.App;

public class Admin extends User {

    private Stage primaryStage;

    public Admin() {
        setUsername("admin");
        setPassword("admin");
    }

    public ArrayList<User> listUsers() {
        return User.getUsers();
    }

    public int addUser(String username, String password) {
        try {
            if (User.getUsers().add(new User(username, password)))
                return 0;
        }
        catch (Exception e) { return -1; }
        return -1;
    }

    public int deleteUser(String username) {
        ArrayList<User> users = User.getUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                users.remove(i);
                return 0;
            }
        }
        return -1;
    }

    public void start() {
        primaryStage = App.getStage();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/admin/AdminView.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        } catch (Exception e) {
            System.out.println("err there was an exception");
            e.printStackTrace();
        }
    }
}
