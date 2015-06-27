package database;

import java.util.ArrayList;

import addon.AddonManager;
import server.ServerConnection;
import display.Window;

public class ThemeData {
	
	private int idTheme;
	private String name;
	
	public ThemeData(){
		
	}

	public int getIdTheme() {
		return idTheme;
	}
	public String getName() {
		return name;
	}
	public void setIdTheme(int idTheme) {
		this.idTheme = idTheme;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		return "idTheme:"+idTheme+",name:"+name;
	}
	
	public static Object[][] getThemesList(){
		
		Object[][] results = null;
		
		ArrayList<ThemeData> r = null;
		try {
			r = ServerConnection.getThemesList(Window.profile.getEmail(), Window.profile.getPassword());
		} catch (Exception e) {
			//TODO afficher un dialogBox d'informations de serveur indisponible
		}
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
	
}
