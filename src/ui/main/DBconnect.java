package ui.main;

/**
 * @author Kalios
 * 
 */

import java.sql.*;
import java.util.Date;

public class DBconnect {

	private Connection Conn = null;
	private Statement st = null;

	
	public DBconnect(){
		try{
		
			Class.forName("com.mysql.jdbc.Driver");
			
			Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306?autoReconnect=true&useSSL=false&user=root&password=root");
			
			st = Conn.createStatement();

			
		}catch(Exception ex){
			
			System.out.println("Error: "+ex);
		}
		
	}
	public void createDB(){
		try{
	
			st.executeUpdate("DROP DATABASE IF EXISTS HotelMaster");
			st.executeUpdate("CREATE DATABASE HotelMaster");
			st = getStatement();
			st.executeUpdate("CREATE TABLE Access_Levels (accessLevel tinyint(1) NOT NULL,canLoggin tinyint(1) NOT NULL,isStaff tinyint(1) NOT NULL,canChangeAccessLevel tinyint(1) NOT NULL,canEditBooking tinyint(1) NOT NULL,canEditCustomer tinyint(1) NOT NULL,canEditRoom tinyint(1) NOT NULL,PRIMARY KEY (accessLevel))");
			st.executeUpdate("CREATE TABLE Users (id int(10) NOT NULL,email varchar(45) NOT NULL,accUsername varchar(20) NOT NULL,accPassword varchar(45) NOT NULL,accessLevel tinyint(1) NOT NULL,registerDate timestamp NOT NULL,PRIMARY KEY (id),FOREIGN KEY (accessLevel) REFERENCES Access_Levels(accessLevel))");
			st.executeUpdate("CREATE TABLE Rooms (id int(10) NOT NULL,name varchar(45) NOT NULL,beds tinyint(1) NOT NULL,price int(10) NOT NULL,floor tinyint(2) NOT NULL,isSuite tinyint(1) NOT NULL,hasBalcony tinyint(1) NOT NULL,PRIMARY KEY (id))");
			st.executeUpdate("CREATE TABLE Bookings (id int(10) NOT NULL,room_id int(10) NOT NULL,user_id int(10) NOT NULL,startDate date NOT NULL,endDate date NOT NULL,bookedAt timestamp NOT NULL,PRIMARY KEY (id),FOREIGN KEY (room_id) REFERENCES Rooms(id),FOREIGN KEY (user_id) REFERENCES Users(id))");
			st.executeUpdate("CREATE TABLE Customers (user_id int(10) NOT NULL,firstName varchar(45) NOT NULL,lastName varchar(45) NOT NULL,age tinyint(3) NOT NULL,visits tinyint(3) NOT NULL,lastBooking int(10) NOT NULL,currentBooking int(10) NOT NULL,hasDiscount tinyint(1) NOT NULL,discount tinyint(3) NOT NULL,homeCountry varchar(45) NOT NULL,FOREIGN KEY (user_id) REFERENCES Users(id))");
			st.executeUpdate("INSERT INTO Access_Levels VALUES ('-1','0','0','0','0','0','0')");
			st.executeUpdate("INSERT INTO Access_Levels VALUES ('0','1','0','0','0','0','0')");
			st.executeUpdate("INSERT INTO Access_Levels VALUES ('1','1','1','0','1','1','0')");
			st.executeUpdate("INSERT INTO Access_Levels VALUES ('2','1','1','1','1','1','1')");
			st.executeUpdate("INSERT INTO users VALUES ('0','emptymail@gmail.com','admin','admin','2','"+new Timestamp(new Date().getTime())+"')");
			st.executeUpdate("INSERT INTO rooms VALUES ('1', '1', '2', '20', '1', '0', '0')");
			st.executeUpdate("INSERT INTO rooms VALUES ('2', '2', '2', '20', '1', '0', '0')");
			st.executeUpdate("INSERT INTO rooms VALUES ('3', '3', '2', '20', '1', '0', '0')");
			st.executeUpdate("INSERT INTO rooms VALUES ('101', '101', '2', '35', '0', '0', '1')");
			st.executeUpdate("INSERT INTO rooms VALUES ('102', '102', '2', '35', '0', '0', '1')");
			st.executeUpdate("INSERT INTO rooms VALUES ('103', '103', '2', '35', '0', '0', '1')");
			st.executeUpdate("INSERT INTO rooms VALUES ('104', '104', '2', '35', '1', '0', '0')");
			st.executeUpdate("INSERT INTO rooms VALUES ('105', '105', '2', '35', '1', '0', '0')");
			st.executeUpdate("INSERT INTO rooms VALUES ('106', '106', '2', '35', '1', '0', '0')");
			st.executeUpdate("INSERT INTO rooms VALUES ('201', '201', '2', '40', '2', '0', '1')");
			st.executeUpdate("INSERT INTO rooms VALUES ('202', '202', '2', '40', '2', '0', '1')");
			st.executeUpdate("INSERT INTO rooms VALUES ('203', '203', '2', '40', '2', '0', '1')");
			st.executeUpdate("INSERT INTO rooms VALUES ('204', '204', '2', '40', '2', '0', '0')");
			st.executeUpdate("INSERT INTO rooms VALUES ('205', '205', '2', '40', '2', '0', '0')");
			st.executeUpdate("INSERT INTO rooms VALUES ('206', '206', '2', '40', '2', '0', '0')");
			st.executeUpdate("INSERT INTO rooms VALUES ('301', '301', '2', '50', '3', '0', '1')");
			st.executeUpdate("INSERT INTO rooms VALUES ('302', '302', '2', '50', '3', '0', '1')");
			st.executeUpdate("INSERT INTO rooms VALUES ('303', '303', '2', '50', '3', '0', '1')");
			st.executeUpdate("INSERT INTO rooms VALUES ('304', '304', '2', '50', '3', '0', '1')");
			st.executeUpdate("INSERT INTO rooms VALUES ('305', '305', '2', '50', '3', '0', '1')");
			st.executeUpdate("INSERT INTO rooms VALUES ('306', '306', '2', '50', '3', '0', '1')");
			st.executeUpdate("INSERT INTO rooms VALUES ('401', 'Wedding Suite', '2', '250', '4', '1', '1')");
			st.executeUpdate("INSERT INTO rooms VALUES ('402', 'Presidential Suite', '2', '250', '4', '1', '1')");
			
			
		}catch(Exception ex){
			
			System.out.println("Error: "+ex);
		}
	}
	
	public void deleteDB(){
		try{
			st.executeUpdate("DROP DATABASE IF EXISTS HotelMaster");
		}catch(Exception ex){
			
			System.out.println("Error: "+ex);
		}
	}
	
	public Statement getStatement(){
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HotelMaster?autoReconnect=true&useSSL=false","root","root");
			
			st = Conn.createStatement();
			
		}catch(Exception ex){
			
			System.out.println("Error: "+ex);
		}
		
		return st;
	}

	
}