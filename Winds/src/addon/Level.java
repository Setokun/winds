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
import addon.level.LevelMode;
import addon.level.LevelStatus;
import addon.level.LevelType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import database.DBClass;


/**
 * Class used to represent a Winds level.
 */
public class Level implements Cloneable {
	private String creator, date, name, description;
	private int idDB, idTheme;
	private LevelMode mode;
	private LevelType type;
	private LevelStatus status;
	private boolean uploaded;
	
	private Point startPosition, endPosition;
	private int[][] matrix, interactions;
	private int timeMax;
	
	
	//region Constructors 
	/**
	 * Builds an empty level.
	 */
	public Level(){
		updateDate();
		name = "my new level";
		creator = Profile.getCurrentPlayer().getPseudo();
		mode = LevelMode.standard;
		type = LevelType.my;
		uploaded = false;
		startPosition = new Point(1,1);
		matrix = new int[EditorGUI.NB_TILES_MATRIX][EditorGUI.NB_TILES_MATRIX];
		interactions = new int[EditorGUI.NB_TILES_MATRIX][EditorGUI.NB_TILES_MATRIX];
	}
	/**
	 * Builds a level which used the specified theme.
	 * @param levelName The level's name
	 * @param idTheme The ID of the theme used by the level
	 */
	public Level(String levelName, int idTheme){
		this();
		this.name = levelName;
		this.idTheme = idTheme;
	}
	//endregion
	
	//region Public methods 
	/**
	 * Clones the current level.
	 * @return Level or null
	 */
	public Level clone() {
		try {
			Level lvl = (Level) super.clone();
			lvl.updateDate();
			lvl.uploaded = false;
			return lvl;
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
	/**
	 * Get the JSON-formated string representation of the level.
	 * @return String
	 */
	public String toJson(){
		Gson gson = new GsonBuilder().serializeNulls().create();
		return gson.toJson(this);
	}
	/**
	 * Get the string representation of the level.
	 * @return String
	 */
	public String toString(){
		return "Level "+ toJson();
	}
	/**
	 * Updates the level's creation date to today.
	 */
	public void updateDate(){
		date = ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
	}
	/**
	 * Insert the level into the local DB and returns its success statement.
	 * @return boolean
	 */
	public boolean insertDB(){
		try {
			DBClass.connect();
			DBClass.executeQuery("INSERT INTO levels (id , name, description, timeMax, "
								+ "levelType, levelStatus, levelMode, creator, idTheme) "
								+ "VALUES ("+getIdDB()+",'"+getName()+"','"+getDescription()+"','"+getTimeMax()
								+"','"+getType()+"','"+getStatus()+"','"+getMode()+"','"+getCreator()+"','"+getIdTheme()+"');");
			
		} catch (ClassNotFoundException e) {
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
	//endregion
	
	//region Getters 
	/**
	 * Get the pseudonym of the level's creator.
	 * @return String
	 */
	public String getCreator() {
		return creator;
	}
	/**
	 * Get the level's creation date.
	 * @return String
	 */
	public String getDate() {
		return date;
	}
	/**
	 * Get the level's name.
	 * @return String
	 */
	public String getName() {
		return name;
	}
	/**
	 * Get the level's description.
	 * @return String
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Get the theme ID used by the level.
	 * @return int
	 */
	public int getIdTheme() {
		return idTheme;
	}
	/**
	 * Get the level's ID.
	 * @return int
	 */
	public int getIdDB() {
		return idDB;
	}
	/**
	 * Get the level's mode.
	 * @return LevelMode
	 */
	public LevelMode getMode() {
		return mode;
	}
	/**
	 * Get the level's type.
	 * @return LevelType
	 */
	public LevelType getType() {
		return type;
	}
	/**
	 * Get the level's status.
	 * @return LevelStatus
	 */
	public LevelStatus getStatus(){
		return status;
	}
	/**
	 * Checks if the level has been uploaded.
	 * @return boolean
	 */
	public boolean isUploaded() {
		return uploaded;
	}
	/**
	 * Get the level's departure point.
	 * @return Point
	 */
	public Point getStartPosition(){
		return startPosition;
	}
	/**
	 * Get the level's arrival point.
	 * @return
	 */
	public Point getEndPosition(){
		return endPosition;
	}
	/**
	 * Get the level's matrix.
	 * @return int[][]
	 */
	public int[][] getMatrix() {
		return matrix;
	}
	/**
	 * Get the level's interactions.
	 * @return int[][]
	 */
	public int[][] getInteractions() {
		return interactions;
	}
	/**
	 * Get the level's maximu time.
	 * @return int
	 */
	public int getTimeMax() {
		return timeMax;
	}
	//endregion	
	
	//region Setters 
	/**
	 * Set the level's creator.
	 * @param creator The pseudonym of the user who made the level
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}
	/**
	 * Set the level's creation date.
	 * @param date The new date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * Set the level's name.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Set the level's description.
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Set the theme ID used by the level.
	 * @param idTheme
	 */
	public void setIdTheme(int idTheme) {
		this.idTheme = idTheme;
	}
	/**
	 * Set the level's ID.
	 * @param idLevel
	 */
	public void setIdDB(int idLevel) {
		this.idDB = idLevel;
	}
	/**
	 * Set the level's mode.
	 * @param mode A constant of LevelMode
	 */
	public void setMode(LevelMode mode) {
		this.mode = mode;
	}
	/**
	 * Set the level's type.
	 * @param type A constant of LevelType
	 */
	public void setType(LevelType type) {
		this.type = type;
	}
	/**
	 * Set the level's status.
	 * @param status A constant of LevelStatus
	 */
	public void setStatus(LevelStatus status){
		this.status = status;
	}
	/**
	 * Set the level's upload status.
	 * @param uploaded The new upload's status
	 */
	public void setUploaded(boolean uploaded) {
		this.uploaded = uploaded;
	}
	/**
	 * Set the level's departure point.
	 * @param startPosition The new departure point.
	 */
	public void setStartPosition(Point startPosition){
		this.startPosition = startPosition;
	}
	/**
	 * Set the level's arrival point.
	 * @param endPosition The new arrival point
	 */
	public void setEndPosition(Point endPosition){
		this.endPosition = endPosition;
	}
	/**
	 * Set the level's matrix.
	 * @param matrix The new matrix
	 */
	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}
	/**
	 * Set the level's interactions.
	 * @param interactions The new interactions
	 */
	public void setInteractions(int[][] interactions) {
		this.interactions = interactions;
	}
	/**
	 * Set the level's maximum time.
	 * @param timeMax The new maximum time
	 */
	public void setTimeMax(int timeMax) {
		this.timeMax = timeMax;
	}
	//endregion

	//region Static 
	/**
	 * Get the levels' list on the remote server which have the specified type.
	 * @param type The level's type to find
	 * @return Object[][]
	 * @throws IOException
	 */
	public static Object[][] getLevelsList(LevelType type) throws IOException{
		ArrayList<Object[]> intermediary = null;
		Object[][] results = null;
		ArrayList<Level> r = null;

		if(type == LevelType.basic)				r = ServerConnection.getBasicLevelsList();
		else if(type == LevelType.custom)		r = ServerConnection.getCustomLevelsList();
		else if(type == LevelType.tomoderate)	r = ServerConnection.getLevelsToModerateList();

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
				for (int k = 0; k < idLevels.length; k++)
					if(idLevels[k] == r.get(i).getIdDB())
						exists = true;
				
				//on teste si le thème requis est installé
				for (int k2 = 0; k2 < idThemes.length; k2++)
					if(idThemes[k2] == r.get(i).getIdTheme())
						requiredThemeInstalled = true;
				
				if(!exists){
					Object[] result = new Object[2]; 
					result[0] =  String.valueOf(r.get(i).getName());
					result[1] =  r.get(i).getIdDB();
					if(requiredThemeInstalled)intermediary.add(result);
				}
			}
			
			results = new Object[intermediary.size()][2];
			for (int i = 0; i < intermediary.size(); i++)
				results[i] = intermediary.get(i);
		}
		
		return results;
	}
	/**
	 * Get the custom levels' list on the remote server.
	 * @return Object[][]
	 * @throws IOException
	 */
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
	/**
	 * Get the level's status in local DB which matched the specified ID.
	 * @param idCustomLevel The custom level ID
	 * @return String
	 */
	public static String getStatus(int idCustomLevel){
		String status = null;
		
		try {
			DBClass.connect();
			ResultSet r = DBClass.requestQuery("SELECT levelStatus FROM levels WHERE id="+idCustomLevel+";");
			
			while(r.next()) {
				status = r.getString("levelStatus");
			}
		} catch (ClassNotFoundException | SQLException e) {
		} finally{
			DBClass.disconnect();
		}
		return status;
	}
	/**
	 * Set the level's status into local DB which matched the<br>
	 * parameters and returns its success statement.
	 * @param idLevel The ID of the level to update
	 * @param status The status to apply
	 * @return boolean
	 */
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
	//endregion
	
}
