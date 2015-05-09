package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import display.Window;

public class Score {
	private int id;
	private String levelName;
	private int clicks;
	private int nbItems;
	private int time;
	private int rank;
	
	public Score(){
		
	}

	public int getId() {return id;}
	public String getLevelName() {return levelName;}
	public int getScore() {return 10000 - (clicks * 10) - (time * 10);}
	public int getClicks() {return clicks;}
	public int getTime() {return time;}
	public int getRank() {return rank;}
	public int getNbItems() {return nbItems;}
	
	public void setId(int id) {this.id = id;}
	public void setLevelName(String levelName) {this.levelName = levelName;}
	public void setClicks(int clicks) {this.clicks = clicks;}
	public void setTime(int time) {this.time = time;}
	public void setRank(int rank) {this.rank = rank;}
	public void setNbItems(int nbItems) {this.nbItems = nbItems;}
	
	public static ArrayList<Score> getScores(){
		try {
			
			ResultSet r = DBClass.requestQuery("SELECT levels.name AS levelName, nbItems, nbClicks, time, rank FROM scores JOIN levels ON levels.id = scores.idLevel WHERE idPlayer="+Window.profile.getId()+"ORDER BY levels.id");
			
			ArrayList<Score> scores = new ArrayList<Score>();

			while(r.next()) {
				Score s = new Score();
				
				s.setLevelName(r.getString("levelname"));
				s.setNbItems(r.getInt("nbItems"));
				s.setClicks(r.getInt("nbClicks"));
				s.setTime(r.getInt("time"));
				s.setRank(r.getInt("rank"));
				
				scores.add(s);
			}
			
			return scores;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
