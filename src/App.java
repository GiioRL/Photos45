import javafx.application.Application;
// import javafx.event.ActionEvent;
// import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
    //     Button btn = new Button();
    //     btn.setText("Say 'Hello World'");
    //     btn.setOnAction(new EventHandler<ActionEvent>() {
 
    //         @Override
    //         public void handle(ActionEvent event) {
    //             System.out.println("Hello World!");
    //         }
    //     });
        
    //     StackPane root = new StackPane();
    //     root.getChildren().add(btn);
  
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("view/LoginView.fxml"));
            Scene scene = new Scene(root);

    
        //   Scene scene = new Scene(root, 300, 250);
        
            primaryStage.setTitle("Photos Application");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("err there was an exception");
            e.printStackTrace();
        }
    }
 
 public static void main(String[] args) {
        launch(args);
    }
}