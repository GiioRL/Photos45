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
        ArrayList<User> users = User.getUsers();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getUsername().equals(username)) {
                System.out.println("index: " + i);
                return user;
            }
        }
        System.out.println("login unsuccessful");
        return null;
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