package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL url=new File("src/main/java/sample/View.fxml").toURI().toURL();
        Parent root= FXMLLoader.load(url);
        primaryStage.setTitle("Sensor Simulator");
        primaryStage.setScene(new Scene(root, 600, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}
