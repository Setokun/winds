package server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import account.Profile;
import addon.JarLevel;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import database.LevelData;
import database.Score;
import database.ThemeData;

public class ServerConnection {
	
	/*OK*/public static Profile downloadProfile(String email, String password){
		
		Profile profile = null;

		try {
			URL monURL = new URL("http://www.winds-game.com/API.php?email="+email+"&password="+password+"&action=downloadProfile");
	        URLConnection yc = monURL.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
	        
	        Gson gson = new Gson();
	        profile = gson.fromJson(in, Profile.class);
	        in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return profile;
	}
	
	/*OK*/public static ArrayList<Score> getScores(String email, String password){
		
		ArrayList<Score> scores = new ArrayList<Score>();

		try {
			URL monURL = new URL("http://www.winds-game.com/API.php?email="+email+"&password="+password+"&action=getScores");
	        URLConnection yc = monURL.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
	        
			JsonArray jArray = new JsonParser().parse(in).getAsJsonArray();
			
			for (int i=0;i<jArray.size();i++) {
			    JsonObject jsonObject = jArray.get(i).getAsJsonObject();
			    Score score = new Score();
			    score.setIdLevel(Integer.valueOf(jsonObject.get("idLevel").toString().replaceAll("\"", "")));
			    score.setTime(Integer.valueOf(jsonObject.get("time").toString().replaceAll("\"", "")));
			    score.setClicks(Integer.valueOf(jsonObject.get("nbClicks").toString().replaceAll("\"", "")));
			    score.setNbItems(Integer.valueOf(jsonObject.get("nbItems").toString().replaceAll("\"", "")));
			    score.setLevelName(jsonObject.get("levelName").toString());
			    scores.add(score);
			}
			
			for (int i = 0; i < scores.size(); i++) {
				System.out.println(scores.get(i).toString());
			}
			
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return scores;
	}
	
	/*OK*/public static ArrayList<ThemeData> getThemesList(String email, String password){
		ArrayList<ThemeData> themes = new ArrayList<ThemeData>();

		try {
			URL monURL = new URL("http://www.winds-game.com/API.php?email="+email+"&password="+password+"&action=getThemes");
	        URLConnection yc = monURL.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
	        
			JsonArray jArray = new JsonParser().parse(in).getAsJsonArray();
			
			for (int i=0;i<jArray.size();i++) {
			    JsonObject jsonObject = jArray.get(i).getAsJsonObject();
			    ThemeData thm = new ThemeData();
			    thm.setIdTheme(Integer.valueOf(jsonObject.get("id").toString().replaceAll("\"", "")));
			    thm.setName(jsonObject.get("name").toString());
			    themes.add(thm);
			}
			
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return themes;
	}
	
	/*OK*/public static ArrayList<LevelData> getBasicLevelsList(String email, String password){
		ArrayList<LevelData> basicLevels = new ArrayList<LevelData>();

		try {
			URL monURL = new URL("http://www.winds-game.com/API.php?email="+email+"&password="+password+"&action=getBasicLevels");
	        URLConnection yc = monURL.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
	        
			JsonArray jArray = new JsonParser().parse(in).getAsJsonArray();
			
			for (int i=0;i<jArray.size();i++) {
			    JsonObject jsonObject = jArray.get(i).getAsJsonObject();
			    LevelData lvl = new LevelData();
			    lvl.setTimeMax(Integer.valueOf(jsonObject.get("timeMax").toString().replaceAll("\"", "")));
			    lvl.setIdTheme(Integer.valueOf(jsonObject.get("idTheme").toString().replaceAll("\"", "")));
			    lvl.setName(jsonObject.get("name").toString());
			    lvl.setDescription(jsonObject.get("description").toString());
			    lvl.setDate(jsonObject.get("creationDate").toString());
			    lvl.setCreator(jsonObject.get("creator").toString());
			    lvl.setIdLevel(Integer.valueOf(jsonObject.get("idLevel").toString().replaceAll("\"", "")));
			    basicLevels.add(lvl);
			}
			
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return basicLevels;
	}
	
	/*OK*/public static ArrayList<LevelData> getCustomLevelsList(String email, String password){
		ArrayList<LevelData> customLevels = new ArrayList<LevelData>();

		try {
			URL monURL = new URL("http://www.winds-game.com/API.php?email="+email+"&password="+password+"&action=getCustomLevels");
	        URLConnection yc = monURL.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
	        
			JsonArray jArray = new JsonParser().parse(in).getAsJsonArray();
			
			for (int i=0;i<jArray.size();i++) {
			    JsonObject jsonObject = jArray.get(i).getAsJsonObject();
			    LevelData lvl = new LevelData();
			    lvl.setTimeMax(Integer.valueOf(jsonObject.get("timeMax").toString().replaceAll("\"", "")));
			    lvl.setIdTheme(Integer.valueOf(jsonObject.get("idTheme").toString().replaceAll("\"", "")));
			    lvl.setName(jsonObject.get("name").toString());
			    lvl.setDescription(jsonObject.get("description").toString());
			    lvl.setDate(jsonObject.get("creationDate").toString());
			    lvl.setCreator(jsonObject.get("creator").toString());
			    lvl.setIdLevel(Integer.valueOf(jsonObject.get("idLevel").toString().replaceAll("\"", "")));
			    customLevels.add(lvl);
			}
			
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customLevels;
	}
	
	/*OK*/public static ArrayList<LevelData> getLevelsToModerateList(String email, String password){
		ArrayList<LevelData> levelsToModerate = new ArrayList<LevelData>();

		try {
			URL monURL = new URL("http://www.winds-game.com/API.php?email="+email+"&password="+password+"&action=getLevelsToModerate");
	        URLConnection yc = monURL.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
	        
			JsonArray jArray = new JsonParser().parse(in).getAsJsonArray();
			
			for (int i=0;i<jArray.size();i++) {
			    JsonObject jsonObject = jArray.get(i).getAsJsonObject();
			    LevelData lvl = new LevelData();
			    lvl.setTimeMax(Integer.valueOf(jsonObject.get("timeMax").toString().replaceAll("\"", "")));
			    lvl.setIdTheme(Integer.valueOf(jsonObject.get("idTheme").toString().replaceAll("\"", "")));
			    lvl.setName(jsonObject.get("name").toString());
			    lvl.setDescription(jsonObject.get("description").toString());
			    lvl.setDate(jsonObject.get("creationDate").toString());
			    lvl.setCreator(jsonObject.get("creator").toString());
			    lvl.setIdLevel(Integer.valueOf(jsonObject.get("idLevel").toString().replaceAll("\"", "")));
			    levelsToModerate.add(lvl);
			}
			
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return levelsToModerate;
	}
	
	/*TODO*/public static void downloadTheme(int idTheme){
		
	}
	
	/*TODO*/public static void downloadLevel(int idLevel){
		
	}
	
	/*TODO*/public static void uploadCustomLevel(JarLevel level){
		
	}

	/*TODO en cours*/public static void uploadScores(String email, String password, ArrayList<Score> scores){
		String infosToUpload = "[{\"idLevel\":1, \"time\":37, \"nbClicks\":76, \"nbItems\":58}]";
		
		String url = "http://www.winds-game.com/API.php?email="+email+"&password="+password+"&action=uploadScores";
        
		
	}
}
