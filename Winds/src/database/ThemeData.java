package database;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import server.ServerConnection;
import addon.AddonManager;

public class ThemeData {
	
	private int idTheme;
	private String name;
	private String description;
	private String fileName;
	
	public ThemeData(){
		
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
	public String getFileName() {
		return fileName;
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
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String toString(){
		return "idTheme:"+idTheme+",name:"+name+",description:"+description+",fileName:"+fileName;
	}
	
	public static Object[][] getThemesList() throws IOException{
		
		Object[][] results = null;
		
		ArrayList<ThemeData> r = null;
		
		r = ServerConnection.getThemesList();
		
		int[] ids = AddonManager.getThemesInstalledIds();
		int nbThemes = r.size() - ids.length;
		results = new Object[nbThemes][2];

		int j=0;
		for(int i=0; i < r.size();i++){
			boolean exists = false;
			results[j][0] =  String.valueOf(r.get(i).getName());
			results[j][1] =  r.get(i).getIdTheme();
			for (int k = 0; k < ids.length; k++) {
				if(ids[k] == r.get(i).getIdTheme())
					exists = true;
			}
			if(!exists)
				j++;
		}
		return results;
	}

	public boolean insertDB(){
		try {
			
			DBClass.executeQuery("INSERT INTO themes (id, name, description) "
								+ "VALUES ('"+idTheme+"', '"+name+"','"+description+");");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				DBClass.executeQuery("UPDATE themes SET name='"+name+"', description='"+description+"' WHERE id='"+idTheme+"'");
			} catch (ClassNotFoundException | SQLException e1) {
				return false;
			}
		}
		return true;
	}
}
