package database;

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
	
	
}
