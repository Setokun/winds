package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import account.Profile;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import database.Score;

public class ServerConnection {
	
	/*OK*/public static Profile downloadProfile(String email, String password){
		
		Profile profile = null;

		try {
			URL monURL = new URL("http://www.winds-game.com/API.php?email="+email+"&password="+password+"&action=downloadProfile");
	        URLConnection yc = monURL.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
	        
	        Gson gson = new Gson();
	        profile = gson.fromJson(in, Profile.class);
	        System.out.println(profile.toString());
	        in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return profile;
	}
	
	public static ArrayList<Score> getScores(String email, String password){
		
		ArrayList<Score> scores = new ArrayList<Score>();

		try {
			URL monURL = new URL("http://www.winds-game.com/API.php?email="+email+"&password="+password+"&action=getScores");
	        URLConnection yc = monURL.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
	        
			JsonArray jArray = new JsonParser().parse(in).getAsJsonArray();
			
			for (int i=0;i<jArray.size();i++) {
			    JsonObject jsonObject = jArray.get(i).getAsJsonObject();
			    //System.out.println(jsonObject);
			    Score score = new Score();
			    score.setIdLevel(Integer.valueOf(jsonObject.get("idLevel").toString().replaceAll("\"", "")));
			    score.setTime(Integer.valueOf(jsonObject.get("time").toString().replaceAll("\"", "")));
			    score.setClicks(Integer.valueOf(jsonObject.get("nbClicks").toString().replaceAll("\"", "")));
			    score.setNbItems(Integer.valueOf(jsonObject.get("nbItems").toString().replaceAll("\"", "")));
			    score.setLevelName((jsonObject.get("levelName").toString()));
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
	
	public static String[] getThemesList(String email, String password){
		return null;
	}
	
	public static String[] getBasicLevelsList(String email, String password){
		return null;
	}
	
	public static String[] getCustomLevelsList(String email, String password){
		return null;
	}
	
	public static String[] getLevelsToModerateList(String email, String password){
		return null;
	}
	
	public static void downloadTheme(int idTheme){
		
	}
	
}
