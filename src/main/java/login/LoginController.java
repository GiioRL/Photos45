package main.java.login;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import main.java.App;
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

    @FXML
    private Button quitButton;

    private Scene scene;
    private Stage stage;

    private static LoginModel loginModel = LoginModel.getInstance();

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

    @FXML
    void quit() {
        App.quit();
    }

    @FXML
    void restore() {
        User.restoreClass();
    }

    @FXML
    void reset() {
        User.resetClass();
    }

    public void start() {
        if (scene == null) {
            initScene();
        }
        stage.setScene(scene);
    }

    private void initScene() {
        stage = App.getStage();
        scene = App.getLoginScene(); //replace this maybe
    }
}
