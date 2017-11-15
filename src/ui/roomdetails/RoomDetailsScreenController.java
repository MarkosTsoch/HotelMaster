package ui.roomdetails;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ui.main.DBconnect;
import ui.main.Main;
import ui.main.Rooms;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RoomDetailsScreenController implements Initializable {
	
	@FXML
    private Button roomScreen;

    @FXML
    private Button reserve;

    @FXML
    private TextArea utilities;

    @FXML
    private Text price;

    @FXML
    private Text availability;

    @FXML
    private Text stars;

    @FXML
    private Button photosButton;
    
    @FXML
    private Text hostsNumber;

    
    @FXML
    void photosButtonAction(ActionEvent event) {

    }
    
	Date current = new Date();
    

	@Override
    public void initialize(URL url, ResourceBundle rb) {
        
		Rooms room = new Rooms(Main.floor*100+Main.room);
    	DBconnect db = new DBconnect();
    	
    	availability.setText("Available now");
    	availability.setFill(Color.DARKGREEN);
    	try {
    		ResultSet rs = db.getStatement().executeQuery("SELECT * FROM bookings WHERE room_id="+room.getId()+"");
    		while(rs.next()){
    			if(current.after(rs.getDate("startDate"))  &&  current.before(rs.getDate("endDate"))){
    				availability.setText("Not available until "+rs.getDate("endDate"));
    				availability.setFill(Color.RED);
    				break;
    			}
    		}

		} catch (SQLException e) {
			e.printStackTrace();
		}

        price.setText(""+room.getPrice()+"€ /night");
  
        if (Main.floor < 2 ){
        	stars.setText("**");
        	utilities.setText("WC\nTelephone\nMini bar\n");
        }
        else if (Main.floor < 4 ){
        	stars.setText("***");
        	utilities.setText("WC\nTV\nA/C\nTelephone\nMini bar\n");
        }
        else{
        	stars.setText("*****");
        	utilities.setText("WC\nTV\nA/C\nTelephone\nBar\nButler/Chef/Driver\nJacuzzi");
        }
        
        hostsNumber.setText(""+room.getBeds()+" people");

    }
    
    @FXML
    void reserveAction(ActionEvent event) throws IOException  {
        Parent logInPageParent = FXMLLoader.load(getClass().getResource(("/ui/reserve/ReserveScreen.fxml")));
        Scene logInPageScene = new Scene(logInPageParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(logInPageScene);
        appStage.show();
    }

    @FXML
    void roomScreenAction(ActionEvent event) throws IOException {
        if(Main.floor == 4) {
            Parent logInPageParent = FXMLLoader.load(getClass().getResource(("/ui/roomselection/FourthFloorScreen.fxml")));
            Scene logInPageScene = new Scene(logInPageParent);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(logInPageScene);
            appStage.show();
        }
        else if(Main.floor == 0) {
            Parent logInPageParent = FXMLLoader.load(getClass().getResource(("/ui/roomselection/ZeroFloorScreen.fxml")));
            Scene logInPageScene = new Scene(logInPageParent);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(logInPageScene);
            appStage.show();
        }
        else {
            Parent logInPageParent = FXMLLoader.load(getClass().getResource(("/ui/roomselection/RoomsScreen.fxml")));
            Scene logInPageScene = new Scene(logInPageParent);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(logInPageScene);
            appStage.show();
        }
    }

}
