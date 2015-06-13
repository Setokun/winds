package database;

public class LevelData {
	private int timeMax, idTheme, idLevel;
	private String name, description, date, creator;
	
	public LevelData(){
		
	}
	
	//Getters
	public int getTimeMax() {
		return timeMax;
	}
	public int getIdTheme() {
		return idTheme;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public String getDate() {
		return date;
	}
	public String getCreator() {
		return creator;
	}
	public int getIdLevel() {
		return idLevel;
	}
	
	//setters
	public void setTimeMax(int timeMax) {
		this.timeMax = timeMax;
	}
	public void setIdTheme(int idTheme) {
		this.idTheme = idTheme;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public void setIdLevel(int idLevel) {
		this.idLevel = idLevel;
	}
	
	public String toString(){
		return "timeMax:"+timeMax+",idTheme:"+idTheme+",name:"+name+",description:"+description+",date:"+date+",creator:"+creator+",idLevel:"+idLevel;
	}
	
}
