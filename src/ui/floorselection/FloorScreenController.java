package ui.floorselection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ui.main.Main;

import java.io.IOException;

public class FloorScreenController {

    @FXML
    private Button fourthFloor;

    @FXML
    private Button thirdFloor;

    @FXML
    private Button secondFloor;

    @FXML
    private Button firstFloor;

    @FXML
    private Button zeroFloor;

    @FXML
    private Button backToMain;

    @FXML
    void backToMainAction(ActionEvent event) throws IOException {
        Parent logInPageParent = FXMLLoader.load(getClass().getResource(("/ui/main/LogInScreen.fxml")));
        Scene logInPageScene = new Scene(logInPageParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(logInPageScene);
        appStage.show();
    }

    @FXML
    void zeroFloorAction(ActionEvent event) throws IOException {
        Main.floor = 0;
        Parent logInPageParent = FXMLLoader.load(getClass().getResource(("/ui/roomselection/ZeroFloorScreen.fxml")));
        Scene logInPageScene = new Scene(logInPageParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(logInPageScene);
        appStage.show();
    }

    @FXML
    void firstFloorAction(ActionEvent event) throws IOException{
        Main.floor = 1;
        Parent logInPageParent = FXMLLoader.load(getClass().getResource(("/ui/roomselection/RoomsScreen.fxml")));
        Scene logInPageScene = new Scene(logInPageParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(logInPageScene);
        appStage.show();
    }


    @FXML
    void secondFloorAction(ActionEvent event) throws IOException{
        Main.floor = 2;
        Parent logInPageParent = FXMLLoader.load(getClass().getResource(("/ui/roomselection/RoomsScreen.fxml")));
        Scene logInPageScene = new Scene(logInPageParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(logInPageScene);
        appStage.show();
    }

    @FXML
    void thirdFloorAction(ActionEvent event) throws IOException {
        Main.floor = 3;
        Parent logInPageParent = FXMLLoader.load(getClass().getResource(("/ui/roomselection/RoomsScreen.fxml")));
        Scene logInPageScene = new Scene(logInPageParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(logInPageScene);
        appStage.show();
    }
    @FXML
    void fourthFloorAction(ActionEvent event) throws IOException{
        Main.floor = 4;
        Parent logInPageParent = FXMLLoader.load(getClass().getResource(("/ui/roomselection/FourthFloorScreen.fxml")));
        Scene logInPageScene = new Scene(logInPageParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(logInPageScene);
        appStage.show();
    }

}
