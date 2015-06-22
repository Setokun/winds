package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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
	
	/*OK*/public static ArrayList<Score> getScores(String email, String password) throws Exception{
		
		ArrayList<Score> scores = new ArrayList<Score>();

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
		in.close();
		
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

	/*TODO en cours*/
	public static void uploadScores(String email, String password, ArrayList<Score> scores){
		String infosToUpload = "[{\"idLevel\":1, \"time\":32, \"nbClicks\":69, \"nbItems\":100}]";

		int nbModified = 0;
		
		List<String> keys = new ArrayList<String>();
		keys.add("email");
		keys.add("password");
		keys.add("action");
		keys.add("scores");
		
		List<String> values = new ArrayList<String>();
		values.add(email);
		values.add(password);
		values.add("uploadScores");
		values.add(infosToUpload);
		
		try {
			ServerConnection.post("http://www.winds-game.com/API.php", keys, values);
		} catch (IOException e1) {e1.printStackTrace();}
		
		/*String monUrl = "http://www.winds-game.com/API.php?email="+email+"&password="+password+"&action=uploadScores&scores="+infosToUpload;
		URL url;
		try {
			url = new URL(monUrl);
			URLConnection yc = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        
			nbModified = new JsonParser().parse(in).getAsInt();
			System.out.println(monUrl);
			System.out.println(nbModified);
			in.close();
			
		} catch (IOException e) { e.printStackTrace(); }*/
		
	}
	
	public static String post(String adress,List<String> keys,List<String> values) throws IOException{
		String result = "";
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		try {
		//encodage des paramètres de la requête
		String data="";
		for(int i=0;i<keys.size();i++){
			if (i!=0) data += "&amp;";
			data +=URLEncoder.encode(keys.get(i), "UTF-8")+"="+URLEncoder.encode(values.get(i), "UTF-8");
		}
		System.out.println(data);
		//création de la connection
		URL url = new URL(adress);
		URLConnection conn = url.openConnection();
		conn.setDoOutput(true);
		 
		 
		//envoi de la requête
		writer = new OutputStreamWriter(conn.getOutputStream());
		System.out.println(writer);
		writer.write(data);
		writer.flush();
		 
		 
		 
		 
		//lecture de la réponse
		reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String ligne;
		while ((ligne = reader.readLine()) != null) {
		result+=ligne;
		}
		}catch (Exception e) {
		e.printStackTrace();
		}finally{
		try{writer.close();}catch(Exception e){}
		try{reader.close();}catch(Exception e){}
		}
		return result;
		}
	
}
