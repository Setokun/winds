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
	
	public Score(){}
	public Score(int clicks ,int nbItems, int time){
		this.clicks = clicks;
		this.nbItems = nbItems;
		this.time = time;
	}

	public int getId() {return id;}
	public String getLevelName() {return levelName;}
	public int getScore() {return 10000 - time * 100 + nbItems * 75 - clicks * 10;}
	public int getClicks() {return clicks;}
	public int getTime() {return time;}
	public int getNbItems() {return nbItems;}
	
	public void setId(int id) {this.id = id;}
	public void setLevelName(String levelName) {this.levelName = levelName;}
	public void setClicks(int clicks) {this.clicks = clicks;}
	public void setTime(int time) {this.time = time;}
	public void setNbItems(int nbItems) {this.nbItems = nbItems;}
	
	public void setScore(int idLevel){

		try {
			Score oldScore = null;
			int counter = 0;			
			ResultSet r = DBClass.requestQuery("SELECT nbItems, nbClicks, time, rank FROM scores WHERE id="+idLevel);
			while(r.next()){counter++;}
			
			if(counter > 0){
				r = DBClass.requestQuery("SELECT nbItems, nbClicks, time, rank FROM scores WHERE idLevel="+idLevel);
				
				while(r.next()){
					oldScore = new Score(r.getInt("nbClicks"),r.getInt("nbItems"), r.getInt("time"));
				}
				
				if(oldScore != null && this.getScore() > oldScore.getScore()){
					DBClass.executeQuery("UPDATE scores SET nbItems = "+this.nbItems+", nbClicks="+this.clicks+", time="+this.time+" WHERE id="+idLevel);
				}
			}
			else{
				DBClass.executeQuery("INSERT INTO scores (idPlayer, idLevel, time, nbClicks, nbItems, rank) VALUES ('"+Window.profile.getId()+"','"+idLevel+"', '"+time+"', '"+clicks+"', '"+nbItems+"', 0)");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
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
	
	public static  String transformIntTimeInString(int time){
		String result = "";
		int hours = time / 3600;
		int minutes = time / 60;
		int seconds = time % 60;
		if(hours> 0){
			result += hours+":";
		}
		
		result += minutes + ":" + ((seconds > 9)?String.valueOf(seconds):"0"+seconds);
		
		return result;
	}
    
    /*public static String transformPositionInString(int position, int total){
    	
    	if(position == 0 || total == 0){
    		return "---";
    	}
    	
    	return position + "/" + total;
    }*/
	
}
