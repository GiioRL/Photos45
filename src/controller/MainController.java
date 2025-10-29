package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private TextField titleTF;

    @FXML
    void butonClick(ActionEvent event) {
        System.out.println("CHAT HE CLICK BUTON!!!");
        Stage mainWindow = (Stage) titleTF.getScene().getWindow();
        String title = titleTF.getText();
        mainWindow.setTitle(title);
    }

}
