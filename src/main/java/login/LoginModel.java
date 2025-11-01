package main.java.login;

import java.util.ArrayList;

import main.java.util.*;

public class LoginModel {

    private ArrayList<User> users;
    private static LoginModel instance;

    private LoginModel() {
        users = new ArrayList<User>();
        users.add(new Admin()); //admin has index 0
        users.add(new Stock());
    }

    public static LoginModel getInstance() {
        if (instance == null) {
            return new LoginModel();
        }
        return instance;
    }

    public User login(String username, String password) {
        int index = users.indexOf(new User(username, password));
        if (index == -1) {
            System.out.println("login unsuccessful");
            return null;
        }
        return users.get(index);
    }
    
}