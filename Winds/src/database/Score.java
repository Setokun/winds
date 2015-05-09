package database;

public class Score {
	private int id;
	private String levelName;
	private int clicks;
	private int time;
	private String position;
	
	public Score(){
		
	}

	public int getId() {return id;}
	public String getLevelName() {return levelName;}
	public int getScore() {return 10000 - (clicks * 10) - (time * 10);}
	public int getClicks() {return clicks;}
	public int getTime() {return time;}
	public String getPosition() {return position;}
	
	public void setId(int id) {this.id = id;}
	public void setLevelName(String levelName) {this.levelName = levelName;}
	public void setClicks(int clicks) {this.clicks = clicks;}
	public void setTime(int time) {this.time = time;}
	public void setPosition(String position) {this.position = position;}
	
}
