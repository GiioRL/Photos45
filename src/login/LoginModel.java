package login;

import java.util.ArrayList;

public class LoginModel {

    private ArrayList<User> users;
    private static LoginModel instance;

    private LoginModel() {
        users = new ArrayList<User>();
        users.add(new User("stock", "stock"));
        users.add(new User("admin", "admin"));
    }

    public static LoginModel getInstance() {
        if (instance == null) {
            return new LoginModel();
        }
        return instance;
    }

    public int login(String username, String password) { //returns 1 if successful, 0 if unsuccessful
        if (users.contains(new User(username, password))) {
            System.out.println("Login successful!");
            return 1;
        } else {
            System.out.println("Login unsuccessful");
            return 0;
        }
    }
    
}