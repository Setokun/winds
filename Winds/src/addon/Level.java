package addon;

import java.awt.Point;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import leveleditor.EditorGUI;
import server.ServerConnection;
import account.Profile;
import addon.level.Mode;
import addon.level.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import database.DBClass;
import database.LevelStatus;


public class Level implements Cloneable {
	private String creator, date, name, description;
	private int idDB, idTheme;
	private Mode mode;
	private Type type;
	private LevelStatus status;
	private boolean uploaded;
	
	private Point startPosition, endPosition;
	private int[][] matrix, interactions;
	private int timeMax;
	
	
	// region Constructors 
	/*OK*/public Level(){
		updateDate();
		name = "my new level";
		creator = Profile.getCurrentPlayer().getPseudo();
		mode = Mode.STANDARD;
		type = Type.MY;
		uploaded = false;
		startPosition = new Point(1,1);
		matrix = new int[EditorGUI.NB_TILES_MATRIX][EditorGUI.NB_TILES_MATRIX];
		interactions = new int[EditorGUI.NB_TILES_MATRIX][EditorGUI.NB_TILES_MATRIX];
	}
	/*OK*/public Level(String levelName, int idTheme){
		this();
		this.name = levelName;
		this.idTheme = idTheme;
	}
	//endregion
	
	//region Public methods 
	/*OK*/public Level clone() {
		try {
			Level lvl = (Level) super.clone();
			lvl.updateDate();
			lvl.uploaded = false;
			return lvl;
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
	/*OK*/public String toJson(){
		Gson gson = new GsonBuilder().serializeNulls().create();
		return gson.toJson(this);
	}
	/*OK*/public String toString(){
		return "Level "+ toJson();
	}
	
	/*OK*/public void updateDate(){
		date = ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
	}
	//endregion
	
	//region Getters 
	public String getCreator() {
		return creator;
	}
	public String getDate() {
		return date;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public int getIdTheme() {
		return idTheme;
	}
	public int getIdDB() {
		return idDB;
	}
	public Mode getMode() {
		return mode;
	}
	public Type getType() {
		return type;
	}
	public LevelStatus getStatus(){
		return status;
	}
	public boolean isUploaded() {
		return uploaded;
	}
	public Point getStartPosition(){
		return startPosition;
	}
	public Point getEndPosition(){
		return endPosition;
	}
	public int[][] getMatrix() {
		return matrix;
	}
	public int[][] getInteractions() {
		return interactions;
	}
	public int getTimeMax() {
		return timeMax;
	}
	//endregion	
	//region Setters 
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setIdTheme(int idTheme) {
		this.idTheme = idTheme;
	}
	public void setIdDB(int idLevel) {
		this.idDB = idLevel;
	}
	public void setMode(Mode mode) {
		this.mode = mode;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public void setStatus(LevelStatus status){
		this.status = status;
	}
	public void setUploaded(boolean uploaded) {
		this.uploaded = uploaded;
	}
	public void setStartPosition(Point startPosition){
		this.startPosition = startPosition;
	}
	public void setEndPosition(Point endPosition){
		this.endPosition = endPosition;
	}
	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}
	public void setInteractions(int[][] interactions) {
		this.interactions = interactions;
	}
	public void setTimeMax(int timeMax) {
		this.timeMax = timeMax;
	}
	//endregion
	
	public static Object[][] getLevelsList(Type type) throws IOException{
		
		ArrayList<Object[]> intermediary = null;
		Object[][] results = null;
		
		ArrayList<Level> r = null;

		if(type == Type.BASIC){
			r = ServerConnection.getBasicLevelsList();
		}
		else if(type == Type.CUSTOM){
			r = ServerConnection.getCustomLevelsList();
		}
		else if(type == Type.TOMODERATE){
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
					if(idLevels[k] == r.get(i).getIdDB())
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
					result[1] =  r.get(i).getIdDB();
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
		
		ArrayList<Level> r = null;
	
		r = ServerConnection.getCustomLevelsList();
		
		int nbLevels = r.size();
		
		results = new Object[nbLevels][4];
	
		for(int i=0; i < r.size();i++){
			results[i][0] =  String.valueOf(r.get(i).getName());
			results[i][1] =  r.get(i).getIdDB();
			results[i][2] =  getStatus(r.get(i).getIdDB());
			results[i][3] =  r.get(i).getIdTheme();
		}
		return results;
	}
	
	
	public static String getStatus(int idCustomeLevel){
		String status = null;
		
		try {
			DBClass.connect();
			ResultSet r = DBClass.requestQuery("SELECT levelStatus FROM levels WHERE id="+idCustomeLevel+";");
			
			while(r.next()) {
				status = r.getString("levelStatus");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBClass.disconnect();
		}
		return status;
	}
	
	
	public static boolean setStatus(int idLevel, LevelStatus status){
		
		try {
			DBClass.connect();
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
		} finally{
			DBClass.disconnect();
		}
		return true;
	}
	
	public boolean insertDB(){
		try {
			DBClass.connect();
			DBClass.executeQuery("INSERT INTO levels (id , name, description, timeMax, "
								+ "levelType, levelStatus, levelMode, creator, idTheme) "
								+ "VALUES ("+getIdDB()+",'"+getName()+"','"+getDescription()+"','"+getTimeMax()
								+"','"+getType()+"','"+getStatus()+"','"+getMode()+"','"+getCreator()+"','"+getIdTheme()+"');");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				DBClass.executeQuery("UPDATE levels SET name='"+getName()+"', description='"+getDescription()+"', timeMax='"+getTimeMax()+"'"
						+ ", levelType='"+getType()+"', levelStatus='"+getStatus()+"', levelMode='"+getMode()+"',"
						+ " creator='"+getCreator()+"', idTheme='"+getIdTheme()+"' WHERE id="+getIdDB());
			}catch (ClassNotFoundException | SQLException e1) {
				return false;
			}
		}finally{
			DBClass.disconnect();
		}
		return true;
	}
	
}
