package login;

import java.util.ArrayList;

import util.*;

public class LoginModel {

    private ArrayList<User> users;
    private static LoginModel instance;

    private LoginModel() {
        users = new ArrayList<User>();
        // users.add(new Admin()); //admin has index 0
        // users.add(new Stock());
    }

    public static LoginModel getInstance() {
        if (instance == null) {
            return new LoginModel();
        }
        return instance;
    }

    public User login(String username, String password) { //returns 1 if admin, 0 if other, -1 if unsuccessful
        User user = User.getUser(username, password);
        if (user == null)
            System.out.println("Login unsuccessful");
        return user;
        // if (username.equals("admin") && password.equals("admin")) {
        //     return 1;
        // } else if  (users.contains(new User(username, password))) {
        //     System.out.println("Login successful!");
        //     return 0;
        // } else {
        //     System.out.println("Login unsuccessful");
        //     return -1;
        // }
    }
    
}