package main.java.login;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.stage.Stage;
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

    @FXML
    private Button quitButton;

    private MainController mc;
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
        System.exit(0);
    }

    public void injectMainController(MainController mc) { // we may not even need a main controller but we'll see
        this.mc = mc;
    }

    public void start() {
        if (scene == null) {
            initScene();
        }
        stage.setScene(scene);
    }

    private void initScene() {
        stage = MainController.getStage();
        scene = MainController.getLoginScene();
    }
}
