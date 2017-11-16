package ui.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    public static Boolean isSplashLoaded = false;
    public static int floor = 0;
    public static int room = 0;
    public static int user = 0;


    @Override
    public void start(Stage stage) throws Exception {

        Parent rootPanel = FXMLLoader.load(getClass().getResource("LogInScreen.fxml"));

        Scene scene = new Scene(rootPanel, 500, 350);


        stage.setScene(scene);
        stage.sizeToScene();
        stage.getIcons().add(new Image("/resources/hotel.png"));
        stage.setTitle("My Hotel");
        stage.centerOnScreen();
        stage.show();
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
       



        launch(args);
    }
}
