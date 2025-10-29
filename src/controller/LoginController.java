package controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.LoginModel;

public class LoginController {

    @FXML
    private Button butonLogin;

    @FXML
    private TextField tfPassword;

    @FXML
    private TextField tfUsername;

    private LoginModel loginModel;

    public LoginController() {}

    @FXML
    void login() {
        if (loginModel.login(tfUsername.getText(), tfPassword.getText()) == 1) {
            System.out.println("Logged in!!");
        } else {
            System.out.println("Invalid credentials :(");
        }
    }

}
