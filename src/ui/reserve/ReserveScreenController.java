package ui.reserve;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import ui.alertbox.AlertBox;
import ui.main.DBconnect;
import ui.main.Main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class ReserveScreenController {

    @FXML
    private Button roomDetails;

    @FXML
    private Button submit;

    @FXML
    private DatePicker dateFrom;

    @FXML
    private DatePicker dateTo;

	private int yescounter;

	private int bookings;

	private boolean isFirstBooking = true;

	private boolean haveShowMsg = false;

	private boolean doBooking = true;

    @FXML
    void roomDetailsAction(ActionEvent event) throws IOException {
        Parent logInPageParent = FXMLLoader.load(getClass().getResource(("/ui/roomdetails/RoomDetailsScreen.fxml")));
        Scene logInPageScene = new Scene(logInPageParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(logInPageScene);
        appStage.show();
    }

	public void submitAction(ActionEvent actionEvent) throws IOException{
    	

    	if(dateFrom.getValue() == null ||  dateTo.getValue() == null){
    		AlertBox.display("\t\t Invalid date.\nPlease fill both dates");
    		return;
    	}
    		
    	java.sql.Date from = java.sql.Date.valueOf(dateFrom.getValue());
    	java.sql.Date to = java.sql.Date.valueOf(dateTo.getValue());
    	Date current = new Date();
    	Timestamp timestamp = new Timestamp(current.getTime());

    	final int room_id = Main.floor*100+Main.room;
    	

		if(from.before(current) || to.before(current)){
			AlertBox.display("\tInvalid date.\nCannot reserve for past dates.");
			haveShowMsg = true;
			doBooking  = false;
		}
		else if (from.equals(to)){
			AlertBox.display("\t\tInvalid date.\nCannot reserve for zero days.");
			haveShowMsg = true;
			doBooking = false;
		}
		else if (from.after(to)){
			AlertBox.display("Invalid date.\nStarting date has to be before ending date.");
			haveShowMsg = true;
			doBooking = false;
		}
		else{
	    	DBconnect db = new DBconnect();
	    	try {
	    		ResultSet rs = db.getStatement().executeQuery("SELECT * FROM bookings WHERE room_id="+room_id+"");
	    		while(rs.next()){
	    			if((from.after(rs.getDate("endDate")) || to.before(rs.getDate("startDate"))) || ((from.before(rs.getDate("startDate"))) && to.before(rs.getDate("startDate"))))
	    			{
	    				yescounter++;
	    			}
	    			bookings++;
	    			isFirstBooking = false;
	    		}
	
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	
		}
    	if((yescounter==bookings || isFirstBooking) && doBooking){

    		DBconnect db = new DBconnect();
    		int counter = 0;
    		
    		try {
    			ResultSet rs = db.getStatement().executeQuery("SELECT * FROM bookings");
    			while(rs.next()){
    				counter ++;
    			}
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
    		
    		try {

	  			Connection conn = db.getStatement().getConnection();
	  			PreparedStatement stmt = conn.prepareStatement("INSERT INTO bookings VALUES (?,?,?,?,?,?)");
	  			stmt.setInt(1, counter+1);
	  			stmt.setInt(2, room_id);
	  			stmt.setInt(3, Main.user);
	  			stmt.setDate(4, (java.sql.Date) from);
	  			stmt.setDate(5,(java.sql.Date) to);
	  			stmt.setTimestamp(6, timestamp);
	  			stmt.execute();
	  			stmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

	    	AlertBox.display("You successfully reserve the room number " + Main.room + " of " + Main.floor + " floor");
	    	Parent logInPageParent = FXMLLoader.load(getClass().getResource(("/ui/main/LogInScreen.fxml")));
	    	Scene logInPageScene = new Scene(logInPageParent);
	    	Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
	    	appStage.setScene(logInPageScene);
	    	appStage.show();
    	}
    	else
    		if (!haveShowMsg)
    			AlertBox.display("You must choose another date");
    		bookings=0;
    		yescounter=0;
    	}
   
}