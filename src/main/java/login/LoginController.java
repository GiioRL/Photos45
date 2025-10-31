package main.java.login;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import main.java.MainController;
import main.java.util.User;

public class LoginController {

    @FXML
    private Button butonLogin;

    @FXML
    private TextField tfPassword; //consider making this a password field

    @FXML
    private TextField tfUsername;

    @FXML
    private Text textInvalid;

    private MainController mc;

    private LoginModel loginModel = LoginModel.getInstance();

    // public LoginController() {}

    @FXML
    void login() {
        User user = loginModel.login(tfUsername.getText(), tfPassword.getText());
        if (user == null) { //invalid
            textInvalid.setVisible(true);
        } else {
            textInvalid.setVisible(false);
            user.start();
        }
    }

    public void injectMainController(MainController mc) {
        this.mc = mc;
    }

}
