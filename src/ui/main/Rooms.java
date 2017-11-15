package ui.main;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Rooms{

    private String room_name;
    private int beds;
    private int price;
    private int floor;
    private boolean isSuite;
    private boolean hasBalcony;
    
    
    private int id;
    public Rooms(int id) {
        this.id = id;
        constructor();
    }
    

    public void constructor(){
    	
	    DBconnect db = new DBconnect();
	    
	    try {
			ResultSet rr1 = db.getStatement().executeQuery("SELECT * FROM rooms WHERE id="+id+"");
			while(rr1.next()){
			    this.setRoom_name(room_name); rr1.getString("name");
			    this.setBeds(rr1.getInt("beds"));
			    this.setPrice(rr1.getInt("price"));
			    this.setFloor(rr1.getInt("floor"));
			    if (rr1.getInt("isSuite")==1)
			    	this.setSuite(true);
			    else
			    	this.setSuite(false);
			    if (rr1.getInt("hasBalcony")==1)
			    	this.setHasBalcony(true);
			    else
			    	this.setHasBalcony(false);  
	    	  }
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }



	public String getRoom_name() {
		return room_name;
	}



	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}



	public int getBeds() {
		return beds;
	}



	public void setBeds(int beds) {
		this.beds = beds;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}



	public int getFloor() {
		return floor;
	}



	public void setFloor(int floor) {
		this.floor = floor;
	}



	public boolean isSuite() {
		return isSuite;
	}



	public void setSuite(boolean isSuite) {
		this.isSuite = isSuite;
	}



	public boolean isHasBalcony() {
		return hasBalcony;
	}



	public void setHasBalcony(boolean hasBalcony) {
		this.hasBalcony = hasBalcony;
	}

	public int getId() {
		return id;
	}
}
