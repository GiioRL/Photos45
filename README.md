# Photos-45

This project was developed in VS Code

To run the application, either
1. Configure an IDE such as VS Code to build JavaFX
2. Use the following terminal commands

```javac -d bin src\controller\*.java src\controller\album\*.java .\src\model\*.java src\view\admin\*.java src\view\album\*.java src\view\album\albumThumbnail\*.java src\view\album\customDialogs\*.java src\view\album\photoBox\*.java src\view\album\photoThumbnail\*.java src\view\library\*.java src\view\login\*.java src\main\*.java --module-path $env:PATH_TO_FX --add-modules javafx.controls,javafx.fxml```
 
 ```java -cp bin src.main.App.java```