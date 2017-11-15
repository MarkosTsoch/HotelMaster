package ui.roomselection;

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

public class FourthFloorScreenController {

    @FXML
    private Button floorButton;

    @FXML
    private Button room1;

    @FXML
    private Button room2;

    @FXML
    void backToFloorButtonAction(ActionEvent event) throws IOException {
        Parent logInPageParent = FXMLLoader.load(getClass().getResource(("/ui/floorselection/FloorScreen.fxml")));
        Scene logInPageScene = new Scene(logInPageParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(logInPageScene);
        appStage.show();
    }

    @FXML
    void room1Action(ActionEvent event) throws IOException {
        Main.room = 1;
        Parent logInPageParent = FXMLLoader.load(getClass().getResource(("/ui/roomdetails/RoomDetailsScreen.fxml")));
        Scene logInPageScene = new Scene(logInPageParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(logInPageScene);
        appStage.show();
    }

    @FXML
    void room2Action(ActionEvent event) throws IOException {
        Main.room = 2;
        Parent logInPageParent = FXMLLoader.load(getClass().getResource(("/ui/roomdetails/RoomDetailsScreen.fxml")));
        Scene logInPageScene = new Scene(logInPageParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(logInPageScene);
        appStage.show();
    }
}
