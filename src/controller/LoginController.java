package controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import model.LoginModel;

public class LoginController {

    @FXML
    private Button butonLogin;

    @FXML
    private TextField tfPassword; //consider making this a password field

    @FXML
    private TextField tfUsername;

    @FXML
    private Text textInvalid;

    private LoginModel loginModel = LoginModel.getInstance();

    // public LoginController() {}

    @FXML
    void login() {
        if (loginModel.login(tfUsername.getText(), tfPassword.getText()) == 1) {
            textInvalid.setVisible(false);
            // System.out.println("Logged in!!");
        } else {
            textInvalid.setVisible(true);
            // System.out.println("Invalid credentials :(");
        }
    }

}
