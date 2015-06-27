package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.ServerConnection;
import addon.AddonManager;
import addon.level.Type;
import display.Window;

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

	public static Object[][] getLevelsList(Type type){
		
		Object[][] results = null;
		
		ArrayList<LevelData> r = null;
		try {
			
			if(type == Type.basic){
				r = ServerConnection.getBasicLevelsList(Window.profile.getEmail(), Window.profile.getPassword());
			}
			else if(type == Type.custom){
				r = ServerConnection.getCustomLevelsList(Window.profile.getEmail(), Window.profile.getPassword());
			}
			else if(type == Type.toModerate){
				r = ServerConnection.getLevelsToModerateList(Window.profile.getEmail(), Window.profile.getPassword());
			}
		} catch (Exception e) {
			//TODO afficher un dialogBox d'informations de serveur indisponible
		}
		
		int[] ids = AddonManager.getLevelsInstalledIds(type);
		
		int nbLevels = r.size() - ids.length;
		/*if(nbLevels < 0)*/ nbLevels = 1;
		
		results = new Object[nbLevels][2];

		int j=0;
		for(int i=0; i < r.size();i++){
			boolean exists = false;
			
			for (int k = 0; k < ids.length; k++) {
				if(ids[k] == r.get(i).getIdLevel())
					exists = true;
			}
			if(!exists){
				results[j][0] =  String.valueOf(r.get(i).getName());
				results[j][1] =  r.get(i).getIdLevel();
				j++;
			}
		}
		return results;
	}
	
	public static Object[][] getCustomLevelsList(){
		
		Object[][] results = null;
		
		ArrayList<LevelData> r = null;
		try {
			
			r = ServerConnection.getCustomLevelsList(Window.profile.getEmail(), Window.profile.getPassword());
			
		} catch (Exception e) {
			//TODO afficher un dialogBox d'informations de serveur indisponible
		}
		
		int nbLevels = r.size();
		
		results = new Object[nbLevels][3];

		for(int i=0; i < r.size();i++){
			results[i][0] =  String.valueOf(r.get(i).getName());
			results[i][1] =  r.get(i).getIdLevel();
			results[i][2] =  getStatus(r.get(i).getIdLevel());
		}
		return results;
	}
	
	public static String getStatus(int idCustomeLevel){
		String status = null;
		
		try {
			
			ResultSet r = DBClass.requestQuery("SELECT levelStatus FROM levels WHERE id="+idCustomeLevel+";");
			
			while(r.next()) {
				status = r.getString("levelStatus");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
}
