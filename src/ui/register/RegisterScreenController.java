package ui.register;

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
import javafx.stage.Stage;
import ui.main.DBconnect;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ResourceBundle;

import java.sql.Connection;

public class RegisterScreenController implements Initializable{

    @FXML
    private Button mainScreenButton;

    @FXML
    private TextField email;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    public Button registerButton;

 

    public void registerButtonAction(ActionEvent actionEvent) throws IOException  {

        String username1 = username.getText();
        String email1 = email.getText();
        String password1 = password.getText();
        boolean flag1 = true;
        int count1 = 0;
        DBconnect st1 = new DBconnect();
        Timestamp date1 = new Timestamp(new Date().getTime());
        
      try {
    	  ResultSet rr1 = st1.getStatement().executeQuery("SELECT *  from users");
    	  while(rr1.next()){
		    String accUsernames = rr1.getString("accUsername");
		    String emails = rr1.getString("email");
		          if(username1.equals(accUsernames)){
			  ui.alertbox.AlertBox.display("User already exists.Choose another username");
		          flag1 =false;
		          }
		          else if(username.getText().equals("")) {
		                ui.alertbox.AlertBox.display("You must enter a username");
		          flag1 = false;
		          }
		          if(email1.equals(emails)){
		        	  ui.alertbox.AlertBox.display("Email already exists.Choose another email");
			          flag1 =false;

		          }
		          else if(email.getText().equals("")) {
		                ui.alertbox.AlertBox.display("You must enter an email");
		          flag1=false;
		          }
		          count1++;
    	  }
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
      
      if(password1.length()<6 || password1.length()>45 || password1.equals("")){

    	  ui.alertbox.AlertBox.display("Your password must be between 6 and 45 characters");
          flag1 =false;

      }
     
      
      if(flag1){
    	  try {
  			
  			Connection conn = st1.getStatement().getConnection();
  			PreparedStatement stmt = conn.prepareStatement("INSERT INTO users VALUES (?,?,?,?,?,?)");
  			stmt.setInt(1, count1);
  			stmt.setString(2, email1);
  			stmt.setString(4, password1);
  			stmt.setString(3, username1);
  			stmt.setInt(5,0);
  			stmt.setTimestamp(6, date1);
  			stmt.execute();
  			stmt.close();
  			ui.alertbox.AlertBox.display("Your registration was successfully");
    	  
  			 Parent logInPageParent = FXMLLoader.load(getClass().getResource(("/ui/main/LogInScreen.fxml")));
             Scene logInPageScene = new Scene(logInPageParent);
             Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
             appStage.setScene(logInPageScene);
             appStage.show();
    	  
    	  
    	  
    	  } catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
    	  
    	  
      }

      
      
               
            }
      

    public void mainScreenButtonAction(ActionEvent actionEvent) throws IOException {
        Parent logInPageParent = FXMLLoader.load(getClass().getResource(("/ui/main/LogInScreen.fxml")));
        Scene logInPageScene = new Scene(logInPageParent);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(logInPageScene);
        appStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
