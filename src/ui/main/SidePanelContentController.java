package ui.main;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;


public class SidePanelContentController implements Initializable {

    @FXML
    private JFXButton b1;
    @FXML
    private JFXButton b2;
    @FXML
    private JFXButton b3;
    @FXML
    private JFXButton exit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void changeColor(ActionEvent event) {
        JFXButton btn = (JFXButton) event.getSource();
        System.out.println(btn.getText());
        switch(btn.getText())
        {
            case "Island":
                LogInScreenController.image = new Image("/resources/nisi.jpg");
                BackgroundSize backgroundSize = new BackgroundSize(120, 120, true, true, true, true);
                BackgroundImage backgroundImage = new BackgroundImage(LogInScreenController.image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
                Background background = new Background(backgroundImage);
                LogInScreenController.rootAnchorPane.setBackground(background);
                break;
            case "Zakinthos":
                LogInScreenController.image = new Image("/resources/zakinthos.jpg");
                backgroundSize = new BackgroundSize(120, 120, true, true, true, true);
                backgroundImage = new BackgroundImage(LogInScreenController.image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
                background = new Background(backgroundImage);
                LogInScreenController.rootAnchorPane.setBackground(background);
                break;
            case "Santorini":
                LogInScreenController.image = new Image("/resources/santorini.jpg");
                backgroundSize = new BackgroundSize(120, 120, true, true, true, true);
                backgroundImage = new BackgroundImage(LogInScreenController.image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
                background = new Background(backgroundImage);
                LogInScreenController.rootAnchorPane.setBackground(background);
                break;
        }
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }
    
}
