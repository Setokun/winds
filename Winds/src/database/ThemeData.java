package database;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import server.ServerConnection;
import addon.AddonManager;


/**
 * Class used to represent a Winds theme into local DB.
 */
public class ThemeData {
	
	private int idTheme;
	private String name;
	private String description;
	
	public ThemeData(){}

	/**
	 * returns id of the Theme
	 * @return int
	 */
	public int getIdTheme() { return idTheme; }
	/**
	 * returns name of the Theme
	 * @return String
	 */
	public String getName() { return name; }
	/**
	 * returns description of the Theme
	 * @return String
	 */
	public String getDescription() { return description; }

	/**
	 * sets the id of the Theme
	 * @param idTheme int
	 */
	public void setIdTheme(int idTheme) { this.idTheme = idTheme; }
	/**
	 * sets the name of the Theme
	 * @param name String
	 */
	public void setName(String name) { this.name = name; }
	/**
	 * sets the description of the Theme
	 * @param description String
	 */
	public void setDescription(String description) { this.description = description; }
	
	/**
	 * returns e String representation of this object
	 */
	public String toString(){
		return "idTheme:"+idTheme+",name:"+name+",description:"+description;
	}
	/**
	 * returns a double dimension table used to provide a GUI table
	 * @return Object[][]
	 * @throws IOException
	 */
	public static Object[][] getThemesList() throws IOException{
		Object[][] results = null;
		ArrayList<ThemeData> r = null;
		r = ServerConnection.getThemesList();
		
		if(r != null){
			int[] ids = AddonManager.getThemesInstalledIds();
			int nbThemes = r.size() - ids.length;
			results = new Object[nbThemes][2];
	
			int j=0;
			for(int i=0; i < r.size();i++){
				boolean exists = false;
				for (int k = 0; k < ids.length; k++) {
					if(ids[k] == r.get(i).getIdTheme())
						exists = true;
				}
				if(exists){
					continue;
				}else{
					results[j][0] =  String.valueOf(r.get(i).getName());
					results[j][1] =  r.get(i).getIdTheme();
					j++;
				}
			}
		}
		return results;
	}
	/**
	 * insert or update Theme data into the local database, returns false if it fails
	 * @return boolean
	 */
	public boolean insertDB(){
		try {
			DBClass.connect();
			DBClass.executeQuery("INSERT INTO themes (id, name, description) "
								+ "VALUES ('"+idTheme+"', '"+name+"','"+description+");");
			
		} catch (ClassNotFoundException e) {
		} catch (SQLException e) {
			try {
				DBClass.executeQuery("UPDATE themes SET name='"+name+"', description='"+description+"' WHERE id='"+idTheme+"'");
			} catch (ClassNotFoundException | SQLException e1) {
				return false;
			}
		}finally{
			DBClass.disconnect();
		}
		return true;
	}
}
