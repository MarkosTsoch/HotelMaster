package ui.main;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogInScreenController implements Initializable {

    public static Image image;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private AnchorPane root;

    @FXML
    private Button logIn;

    @FXML
    private Button register;

    @FXML
    private TextField usernameText;

    @FXML
    private PasswordField passwordText;

    public static AnchorPane rootAnchorPane;

    private boolean flag = false;
    private boolean found = false;

    public void logInAction(ActionEvent actionEvent) throws IOException {
       


        String name  = usernameText.getText();
        String password = passwordText.getText();

       DBconnect ss13 = new DBconnect();
       
       
       
       try {
		ResultSet rr1 = ss13.getStatement().executeQuery("SELECT *  from users");
	    
		while(rr1.next()){
	   
        String accPasswords = rr1.getString("accPassword");
      
        String accUsernames = rr1.getString("accUsername");
        int user_id = rr1.getInt("id");
        
        
        if (name.equals(accUsernames)){
            
        	
           if(password.equals(accPasswords)  ){
        		flag = true;	
        		found = true;
        		Main.user = user_id;
        		break;
        	}
           else{
        	   ui.alertbox.AlertBox.display("Wrong Password");
        	   found = true;
           }
        
        }
        else{
        	
        	found = false;
 
           }
	    
	    
	    }
		   if(!found){
	    	   ui.alertbox.AlertBox.display("Username not found");
	       }
       } catch (SQLException e) {
		e.printStackTrace();
	}

        if(flag) {
            Parent logInPageParent = FXMLLoader.load(getClass().getResource(("/ui/floorselection/FloorScreen.fxml")));
            Scene logInPageScene = new Scene(logInPageParent);
            Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            appStage.setScene(logInPageScene);
            appStage.show();

            
        }
    }
        
        
    

    public void registerAction(ActionEvent actionEvent) throws IOException {
        System.out.println("you clicked log in button");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ui/register/RegisterScreen.fxml"));
        Parent logInPageParent = loader.load();
        Scene logInPageScene = new Scene(logInPageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(logInPageScene);
        appStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (!Main.isSplashLoaded) {
            loadSplashScreen();
        }

        rootAnchorPane = root;

        image = new Image("/resources/santorini.jpg");
        // new BackgroundSize(width, height, widthAsPercentage, heightAsPercentage, contain, cover)
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
        // new BackgroundImage(image, repeatX, repeatY, position, size)
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
        // new Background(images...)
        Background background = new Background(backgroundImage);

        root.setBackground(background);

        try {
            VBox box = FXMLLoader.load(getClass().getResource("/ui/main/SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(LogInScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isShown()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });
    }

    private void loadSplashScreen() {
        try {
            Main.isSplashLoaded = true;

            StackPane pane = FXMLLoader.load(getClass().getResource(("/ui/main/SplashFXML.fxml")));
            root.getChildren().setAll(pane);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.play();


            fadeIn.setOnFinished(e -> fadeOut.play());

            fadeOut.setOnFinished((e) -> {
                try {
                    AnchorPane parentContent = FXMLLoader.load(getClass().getResource(("/ui/main/LogInScreen.fxml")));
                    root.getChildren().setAll(parentContent);

                } catch (IOException ex) {
                    Logger.getLogger(LogInScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } catch (IOException ex) {
            Logger.getLogger(LogInScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


