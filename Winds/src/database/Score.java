package database;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.ServerConnection;
import account.Profile;

public class Score {
	private int idLevel;
	private String levelName;
	private int nbClicks;
	private int nbItems;
	private int time;
	
	/**
	 * empty constructor based on a level id
	 * @param idLevel
	 */
	public Score(int idLevel){
		this.idLevel = idLevel;
	}
	/**
	 * constructor based on a level id and score parameters
	 * @param idLevel
	 * @param clicks amount of clicks
	 * @param nbItems amount of collected items
	 * @param time time elapsed during the game
	 */
	public Score(int idLevel, int clicks ,int nbItems, int time){
		this.idLevel = idLevel;
		this.nbClicks = clicks;
		this.nbItems = nbItems;
		this.time = time;
	}
	
	/**
	 * returns the level id of the score
	 * @return int
	 */
	public int getIdLevel() {return idLevel;}
	/**
	 * returns the name of the level of the score
	 * @return String
	 */
	public String getLevelName() {return levelName;}
	/**
	 * returns the score
	 * @return int
	 */
	public int getScore() {
		int score = 10000 - time * 100 + nbItems * 75 - nbClicks * 10;
		return (score > 0)? score : 0 ;
	}
	/**
	 * returns the amount of clicks
	 * @return int
	 */
	public int getClicks() {return nbClicks;}
	/**
	 * returns the elapsed time during the level
	 * @return int
	 */
	public int getTime() {return time;}
	/**
	 * returns the amount of collected items
	 * @return
	 */
	public int getNbItems() {return nbItems;}
	
	/**
	 * sets the name of the level concerned by this score
	 * @param levelName String
	 */
	public void setLevelName(String levelName) {this.levelName = levelName;}
	/**
	 * sets the amount of clicks about this level
	 * @param clicks int
	 */
	public void setClicks(int clicks) {this.nbClicks = clicks;}
	/**
	 * sets the time elapsed during this level
	 * @param time int
	 */
	public void setTime(int time) {this.time = time;}
	/**
	 * sets the amount of collected items during this level
	 * @param nbItems int
	 */
	public void setNbItems(int nbItems) {this.nbItems = nbItems;}
	
	/**
	 * save this current score into the local database
	 * @param idLevel
	 */
	public void saveScore(){
		try {
			Score oldScore = null;
			int counter = 0;	
			DBClass.connect();
			ResultSet r = DBClass.requestQuery("SELECT nbItems, nbClicks, time FROM scores WHERE idLevel="+idLevel);
			while(r.next()) counter++;
			
			if(counter > 0){
				r = DBClass.requestQuery("SELECT nbItems, nbClicks, time FROM scores WHERE idLevel="+idLevel);
				while(r.next()) 
					oldScore = new Score(idLevel, r.getInt("nbClicks"),r.getInt("nbItems"), r.getInt("time"));
				
				if(oldScore != null && this.getScore() > oldScore.getScore())
					DBClass.executeQuery("UPDATE scores SET nbItems = "+this.nbItems+", "
														 + "nbClicks="+this.nbClicks+", "
														 + "time="+this.time+" "
														 + "WHERE idLevel="+idLevel);
			}
			else
				DBClass.executeQuery("INSERT INTO scores (idPlayer, idLevel, time, nbClicks, nbItems) "
						+ "VALUES ('"+Profile.current.getId()+"','"+idLevel+"', '"+time+"', '"+nbClicks+"', '"+nbItems+"')");
			
		} catch (ClassNotFoundException | SQLException e) {
		} finally {
			DBClass.disconnect();
		}
		
	}
	/**
	 * returns an ArrayList of local database scores
	 * @return ArrayList of Score
	 */
	public static ArrayList<Score> getLocalScores(){
		try {
			DBClass.connect();
			ResultSet r = DBClass.requestQuery("SELECT levels.name AS levelName, levels.id AS levelID, nbItems, nbClicks, time "
					+ "FROM scores JOIN levels ON levels.id = scores.idLevel "
					+ "WHERE idPlayer="+Profile.current.getId()+"ORDER BY levels.id");
			
			ArrayList<Score> scores = new ArrayList<Score>();

			while(r.next()) {
				Score s = new Score(r.getInt("levelID"));
				s.setLevelName(r.getString("levelname"));
				s.setNbItems(r.getInt("nbItems"));
				s.setClicks(r.getInt("nbClicks"));
				s.setTime(r.getInt("time"));
				scores.add(s);
			}
			
			return scores;
		} catch (ClassNotFoundException | SQLException e) {
			//TODO
			e.printStackTrace();
		}
		finally{
			DBClass.disconnect();
		}
		return null;
	}
	/**
	 * returns a formatted double dimension table to provide a GUI table
	 * @return Object[][]
	 */
	public static Object[][] getFormattedScores(){
		Object[][] results = null;
		
		ArrayList<Score> r = null;
		try {
			r = ServerConnection.getScores();
		} catch (IOException e) {
			try {
				DBClass.connect();
			} catch (ClassNotFoundException | SQLException e1) {}
			finally{
				DBClass.disconnect();
			}
			r = Score.getLocalScores();
		}
		int nbScores = r.size();
		
		results = new String[nbScores][5];

		for(int i=0; i < r.size();i++){
			results[i][0] =  r.get(i).getLevelName();
			results[i][1] =  String.valueOf(r.get(i).getNbItems());
			results[i][2] =  String.valueOf(r.get(i).getClicks());
			results[i][3] =  Score.transformIntTimeInString(r.get(i).getTime());
			results[i][4] =  String.valueOf(Score.calculateScore(r.get(i).getTime(),r.get(i).getNbItems(),r.get(i).getClicks()));
		}
		return results;
	}
	/**
	 * formats a int time into a viewable String format
	 * @param time
	 * @return String
	 */
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
    /**
     * returns a calculation of the score in function of time, items collected and clicks provided
     * @param time
     * @param nbItems
     * @param nbClicks
     * @return int
     */
	public static int calculateScore(int time, int nbItems, int nbClicks){
		int score = 10000 - time * 100 + nbItems * 75 - nbClicks * 10; 
		return (score > 0)? score : 0;
	}
	/**
	 * returns a String representation used to prepare score sending to the remote server
	 */
	public String toString(){
		return "{%22idLevel%22:"+idLevel+", %22time%22:"+time+", %22nbClicks%22:"+nbClicks+", %22nbItems%22:"+nbItems+"}";
	}
	
}
