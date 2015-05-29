package addon;

import java.awt.Point;
import addon.level.Mode;
import addon.level.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Level {
	private String creator, date, name, description;
	private int idTheme;
	private Mode mode;
	private Type type;
	private boolean uploaded;
	
	private Point startPosition;
	private int[][] matrix;
	private int[][] interactions;
	private int timeMax;
	
	//region Public methods 
	public String toJson(){
		Gson gson = new GsonBuilder().serializeNulls().create();
		return gson.toJson(this);
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
