package addon;

import java.awt.Point;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import leveleditor.EditorGUI;
import account.Profile;
import addon.level.Mode;
import addon.level.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Level {
	private String creator, date, name, description;
	private int idDB, idTheme;
	private Mode mode;
	private Type type;
	private boolean uploaded;
	
	private Point startPosition, endPosition;
	private int[][] matrix, interactions;
	private int timeMax;
	
	public Level(){
		updateDate();
		name = "my new level";
		creator = Profile.getCurrentPlayer().getPseudo();
		mode = Mode.Normal;
		type = Type.my;
		uploaded = false;
		startPosition = new Point(1,1);
		matrix = new int[EditorGUI.NB_TILES_MATRIX][EditorGUI.NB_TILES_MATRIX];
		interactions = new int[EditorGUI.NB_TILES_MATRIX][EditorGUI.NB_TILES_MATRIX];
	}
	public Level(String levelName, int idTheme){
		this();
		this.name = levelName;
		this.idTheme = idTheme;
	}
	
	//region Public methods 
	public String toJson(){
		Gson gson = new GsonBuilder().serializeNulls().create();
		return gson.toJson(this);
	}
	public void updateDate(){
		date = ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
	}
	public String toString(){
		return "Level "+ toJson();
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
}
