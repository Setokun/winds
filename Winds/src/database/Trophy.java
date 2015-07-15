package database;

import java.util.ArrayList;

import server.ServerConnection;

public class Trophy {
	private String description;
	private boolean achieved;
	
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
	public boolean getAchieved() {return achieved;}
	
	
	/**
	 * sets the description of the Trophy
	 * @param description
	 */
	public void setDescription(String description) {this.description = description;}
	/**
	 * sets if the Trophy was achieved or not
	 * @param achieved
	 */
	public void setAchieved(boolean achieved) {this.achieved = achieved;}
	
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
	public static ArrayList<Trophy> getTrophies(){
		return ServerConnection.getTrophies();
	}
	
}
