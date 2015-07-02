package database;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.ServerConnection;
import addon.AddonManager;
import addon.level.Type;

public class LevelData {
	private int timeMax, idTheme, idLevel;
	private String name, description, creator, levelType, levelStatus, levelMode;
	
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
	public String getCreator() {
		return creator;
	}
	public int getIdLevel() {
		return idLevel;
	}
	public String getLevelType() {
		return levelType;
	}
	public String getLevelStatus() {
		return levelStatus;
	}
	public String getLevelMode() {
		return levelMode;
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
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public void setIdLevel(int idLevel) {
		this.idLevel = idLevel;
	}
	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}
	public void setLevelStatus(String levelStatus) {
		this.levelStatus = levelStatus;
	}
	public void setLevelMode(String levelMode) {
		this.levelMode = levelMode;
	}

	public String toString(){
		return "timeMax:"+timeMax+",idTheme:"+idTheme+",name:"+name+",description:"
				+description+",creator:"+creator+",idLevel:"+idLevel
				+",type:"+levelType+",status:"+levelStatus+",mode:"+levelMode;
	}

	public static Object[][] getLevelsList(Type type) throws IOException{
		
		ArrayList<Object[]> intermediary = null;
		Object[][] results = null;
		
		ArrayList<LevelData> r = null;

		if(type == Type.basic){
			r = ServerConnection.getBasicLevelsList();
		}
		else if(type == Type.custom){
			r = ServerConnection.getCustomLevelsList();
		}
		else if(type == Type.toModerate){
			r = ServerConnection.getLevelsToModerateList();
		}

		int[] idLevels = AddonManager.getLevelsInstalledIds(type);
		int[] idThemes = AddonManager.getThemesInstalledIds();
		
		int nbLevels = r.size() - idLevels.length;
		
		if(nbLevels > 0){
			intermediary = new ArrayList<Object[]>();
			results = new Object[nbLevels][2];

			for(int i=0; i < r.size();i++){
				boolean exists = false;
				boolean requiredThemeInstalled = false;
				
				//on test 
				for (int k = 0; k < idLevels.length; k++) {
					if(idLevels[k] == r.get(i).getIdLevel())
						exists = true;
				}
				//on teste si le thème requis est installé
				for (int k2 = 0; k2 < idThemes.length; k2++) {
					if(idThemes[k2] == r.get(i).getIdTheme()){
						requiredThemeInstalled = true;
					}
				}
				if(!exists){
					Object[] result = new Object[2]; 
					result[0] =  String.valueOf(r.get(i).getName());
					result[1] =  r.get(i).getIdLevel();
					if(requiredThemeInstalled)intermediary.add(result);
				}
			}
			
			results = new Object[intermediary.size()][2];
			for (int i = 0; i < intermediary.size(); i++) {
				results[i] = intermediary.get(i);
			}
		}
		
		return results;
	}
	
	public static Object[][] getCustomLevelsList() throws IOException{
		
		Object[][] results = null;
		
		ArrayList<LevelData> r = null;

		r = ServerConnection.getCustomLevelsList();
		
		int nbLevels = r.size();
		
		results = new Object[nbLevels][4];

		for(int i=0; i < r.size();i++){
			results[i][0] =  String.valueOf(r.get(i).getName());
			results[i][1] =  r.get(i).getIdLevel();
			results[i][2] =  getStatus(r.get(i).getIdLevel());
			results[i][3] =  r.get(i).getIdTheme();
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
	
	public static boolean setStatus(int idLevel, LevelStatus status){
		
		try {
			if(status == LevelStatus.installed){
				DBClass.requestQuery("UPDATE levels SET levelStatus='installed' WHERE id="+idLevel+";");
			}
			else if(status == LevelStatus.desactivated){
				DBClass.requestQuery("UPDATE levels SET levelStatus='desactivated' WHERE id="+idLevel+";");
			}
			else if(status == LevelStatus.uninstalled){
				DBClass.requestQuery("UPDATE levels SET levelStatus='uninstalled' WHERE id="+idLevel+";");
			}
		} catch (ClassNotFoundException | SQLException e) {
			return false;
		}
		return true;
	}
	public boolean insertDB(){
		try {
			
			DBClass.executeQuery("INSERT INTO levels (id , name, description, timeMax, "
								+ "levelType, levelStatus, levelMode, creator, idTheme) "
								+ "VALUES ("+getIdLevel()+",'"+getName()+"','"+getDescription()+"','"+getTimeMax()
								+"','"+getLevelType()+"','"+getLevelStatus()+"','"+getLevelMode()+"','"+getCreator()+"','"+getIdTheme()+"');");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				DBClass.executeQuery("UPDATE levels SET name='"+getName()+"', description='"+getDescription()+"', timeMax='"+getTimeMax()+"'"
						+ ", levelType='"+getLevelType()+"', levelStatus='"+getLevelStatus()+"', levelMode='"+getLevelMode()+"',"
						+ " creator='"+getCreator()+"', idTheme='"+getIdTheme()+"' WHERE id="+getIdLevel());
			}catch (ClassNotFoundException | SQLException e1) {
				return false;
			}
		}
		return true;
	}
	
}
