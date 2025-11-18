package controller;

// import java.util.ArrayList;

// import main.java.util.*;
import model.User;

public class LoginController {

    // private ArrayList<User> users;
    private static LoginController instance;

    private LoginController() {
        // users = new ArrayList<User>();
        // users.add(new Admin()); //admin has index 0
        // users.add(new Stock());
    }

    public static LoginController getInstance() {
        if (instance == null) {
            return new LoginController();
        }
        return instance;
    }

    public User login(String username, String password) { //returns 1 if admin, 0 if other, -1 if unsuccessful
        User user = User.getUser(username, password);
        return user;
    }
    
}