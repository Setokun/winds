package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.ServerConnection;
import display.Window;

public class Score {
	private int id;
	private int idLevel;
	private String levelName;
	private int nbClicks;
	private int nbItems;
	private int time;
	
	public Score(){}
	public Score(int clicks ,int nbItems, int time){
		this.nbClicks = clicks;
		this.nbItems = nbItems;
		this.time = time;
	}

	public int getId() {return id;}
	public int getIdLevel() {return idLevel;}
	public String getLevelName() {return levelName;}
	public int getScore() {return 10000 - time * 100 + nbItems * 75 - nbClicks * 10;}
	public int getClicks() {return nbClicks;}
	public int getTime() {return time;}
	public int getNbItems() {return nbItems;}
	
	public void setId(int id) {this.id = id;}
	public void setIdLevel(int idLevel) {this.idLevel = idLevel;}
	public void setLevelName(String levelName) {this.levelName = levelName;}
	public void setClicks(int clicks) {this.nbClicks = clicks;}
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
					DBClass.executeQuery("UPDATE scores SET nbItems = "+this.nbItems+", nbClicks="+this.nbClicks+", time="+this.time+" WHERE id="+idLevel);
				}
			}
			else{
				DBClass.executeQuery("INSERT INTO scores (idPlayer, idLevel, time, nbClicks, nbItems, rank) VALUES ('"+Window.profile.getId()+"','"+idLevel+"', '"+time+"', '"+nbClicks+"', '"+nbItems+"', 0)");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static ArrayList<Score> getLocalScores(){
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
	
	public static Object[][] getFormattedScores(){
		Object[][] results = null;
		
		ArrayList<Score> r = null;
		try {
			r = ServerConnection.getScores(Window.profile.getEmail(), Window.profile.getPassword());
		} catch (Exception e) {
			r = Score.getLocalScores();
		}
		int nbScores = r.size();
		
		results = new String[nbScores][5];

		for(int i=0; i < r.size();i++){
			results[i][0] =  r.get(i).getLevelName();
			results[i][1] =  String.valueOf(r.get(i).getNbItems());
			results[i][2] =  String.valueOf(r.get(i).getClicks());
			results[i][3] =  Score.transformIntTimeInString(r.get(i).getTime());
			results[i][4] =  String.valueOf(10000 - r.get(i).getTime() * 100 + r.get(i).getNbItems() * 75 - r.get(i).getClicks() * 10);
		}
		return results;
	}
	
	public static Object[][] getFormattedTrophies(){
		
		Object[][] results = null;
		
		ArrayList<Trophy> t = Trophy.getTrophies();
		int count = t.size();
		
		results = new String[count][2];

		for (int i = 0; i < count; i++) {
			results[i][0] =  t.get(i).getDescription();
			results[i][1] =  t.get(i).getAchieved();
		}
		return results;
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
    
	public String toString(){
		return "id:"+idLevel+",levelName:"+levelName+",clicks:"+nbClicks+",nbItems:"+nbItems+",time:"+time;
	}
	
}
