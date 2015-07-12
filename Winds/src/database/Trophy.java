package database;

import java.util.ArrayList;

import server.ServerConnection;

public class Trophy {
	private String description;
	private String achieved;
	
	public Trophy(){}

	/**
	 * returns the description of the trophy
	 * @return String
	 */
	public String getDescription() {return description;}
	/**
	 * returns if the Trophy was achieved or not
	 * @return String
	 */
	public String getAchieved() {return achieved;}
	
	
	/**
	 * sets the description of the Trophy
	 * @param description
	 */
	public void setDescription(String description) {this.description = description;}
	/**
	 * sets if the Trophy was achieved or not
	 * @param achieved
	 */
	public void setAchieved(String achieved) {this.achieved = achieved;}
	
	/**
	 * returns a String representation of the Trophy
	 */
	public String toString(){
		return "Trophy : " + description + ", achieved : " + achieved;
	}
	
	/**
	 * returns a double dimension table used to provide a GUI table
	 * @return Object[][]
	 */
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
