package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import display.Window;

public class Trophy {
	private int id;
	private String description;
	private String achieved;
	
	public Trophy(){
		
	}

	public int getId() {return id;}
	public String getDescription() {return description;}
	public String getAchieved() {return achieved;}
	
	public void setId(int id) {this.id = id;}
	public void setDescription(String description) {this.description = description;}
	public void setAchieved(String achieved) {this.achieved = achieved;}
	
	public static ArrayList<Trophy> getTrophies(){
    	try {
    		
			ResultSet r = DBClass.requestQuery("SELECT * FROM trophies");
			
			ArrayList<Trophy> trophies = new ArrayList<Trophy>();

			while(r.next()) {
				Trophy t = new Trophy();
				
				t.setId(r.getInt("id"));
				t.setDescription(r.getString("description"));
				t.setAchieved("");
				
				trophies.add(t);
			}
			
			r = DBClass.requestQuery("SELECT * FROM trophies_achieved WHERE idPlayer="+Window.profile.getId());
			while(r.next()){
				trophies.get(r.getInt("idTrophy")-1).setAchieved("ok");
			}
			
    		return trophies;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
    }
	
}
