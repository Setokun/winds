package database;

import java.util.ArrayList;

import server.ServerConnection;

public class Trophy {
	private String description;
	private String achieved;
	
	public Trophy(){}

	public String getDescription() {return description;}
	public String getAchieved() {return achieved;}
	
	public void setDescription(String description) {this.description = description;}
	public void setAchieved(String achieved) {this.achieved = achieved;}
	
	public String toString(){
		return "Trophy : " + description + ", achieved : " + achieved;
	}
	
	public static Object[][] getTrophies(){
		
		Object[][] results = null;
		
		ArrayList<Trophy> t = ServerConnection.getTrophies();
		int count = t.size();
		
		results = new String[count][2];

		for (int i = 0; i < count; i++) {
			results[i][0] =  t.get(i).getDescription();
			results[i][1] =  t.get(i).getAchieved();
		}
		return results;
	}
	
}
