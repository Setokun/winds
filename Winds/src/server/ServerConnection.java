package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import account.Profile;
import addon.AddonManager;
import addon.JarLevel;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import database.LevelData;
import database.Score;
import database.ThemeData;

public class ServerConnection {
	private static final String URL_API_SERVER = "http://www.winds-game.com/API.php";
	private static final int TIMEOUT = 6; // seconds
	
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
	
	/*OK*/public static ArrayList<ThemeData> getThemesList(String email, String password) throws IOException{
		ArrayList<ThemeData> themes = new ArrayList<ThemeData>();

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
		
		return themes;
	}
	
	/*OK*/public static ThemeData getThemeInfos(String email, String password, int idTheme) throws IOException{
		ThemeData themeData = null;

		URL monURL = new URL("http://www.winds-game.com/API.php?email="+email+"&password="+password+"&action=getThemes&idTheme="+idTheme);
        URLConnection yc = monURL.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        
		JsonArray jArray = new JsonParser().parse(in).getAsJsonArray();
		
		for (int i=0;i<jArray.size();i++) {
		    JsonObject jsonObject = jArray.get(i).getAsJsonObject();
		    themeData = new ThemeData();
		    themeData.setIdTheme(Integer.valueOf(jsonObject.get("id").toString().replaceAll("\"", "")));
		    themeData.setName(jsonObject.get("name").toString());
		    themeData.setDescription(jsonObject.get("description").toString());
		    themeData.setFileName(jsonObject.get("fileName").toString());
		}
		
		in.close();
		
		return themeData;
	}
	
	/*TODO*/public static LevelData getLevelInfos(String email, String password, int idLevel) throws IOException{
		LevelData levelData = null;

		URL monURL = new URL("http://www.winds-game.com/API.php?email="+email+"&password="+password+"&action=getLevelInfos&idLevel="+idLevel);
        URLConnection yc = monURL.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        
		JsonArray jArray = new JsonParser().parse(in).getAsJsonArray();
		
		for (int i=0;i<jArray.size();i++) {
		    JsonObject jsonObject = jArray.get(i).getAsJsonObject();
		    levelData = new LevelData();
		    levelData.setTimeMax(Integer.valueOf(jsonObject.get("timeMax").toString().replaceAll("\"", "")));
		    levelData.setLevelType(jsonObject.get("levelType").toString());
		    levelData.setLevelStatus(jsonObject.get("levelStatus").toString());
		    levelData.setLevelMode(jsonObject.get("levelMode").toString());
		    levelData.setIdLevel(Integer.valueOf(jsonObject.get("id").toString().replaceAll("\"", "")));
		    levelData.setIdTheme(Integer.valueOf(jsonObject.get("idTheme").toString().replaceAll("\"", "")));
		    levelData.setName(jsonObject.get("name").toString());
		    levelData.setDescription(jsonObject.get("description").toString());
		    levelData.setCreator(jsonObject.get("creator").toString());
		}
		in.close();
		
		return levelData;
	}
	
	/*OK*/public static ArrayList<LevelData> getBasicLevelsList(String email, String password) throws IOException{
		ArrayList<LevelData> basicLevels = new ArrayList<LevelData>();

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
		    lvl.setCreator(jsonObject.get("creator").toString());
		    lvl.setIdLevel(Integer.valueOf(jsonObject.get("idLevel").toString().replaceAll("\"", "")));
		    basicLevels.add(lvl);
		}
		
		in.close();
		
		return basicLevels;
	}
	
	/*OK*/public static ArrayList<LevelData> getCustomLevelsList(String email, String password) throws IOException{
		ArrayList<LevelData> customLevels = new ArrayList<LevelData>();

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
		    lvl.setCreator(jsonObject.get("creator").toString());
		    lvl.setIdLevel(Integer.valueOf(jsonObject.get("idLevel").toString().replaceAll("\"", "")));
		    customLevels.add(lvl);
		}
		
		in.close();

		return customLevels;
	}
	
	/*OK*/public static ArrayList<LevelData> getLevelsToModerateList(String email, String password) throws IOException{
		ArrayList<LevelData> levelsToModerate = new ArrayList<LevelData>();

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
		    lvl.setCreator(jsonObject.get("creator").toString());
		    lvl.setIdLevel(Integer.valueOf(jsonObject.get("idLevel").toString().replaceAll("\"", "")));
		    levelsToModerate.add(lvl);
		}
		
		in.close();

		return levelsToModerate;
	}
	
	/*OK*/public static boolean downloadTheme(String email, String password, int idTheme){
		try {
			ThemeData theme = getThemeInfos(email, password, idTheme);
			if(theme != null){
				URLConnection ucon = new URL("http://www.winds-game.com/API.php?email=player1@winds.net&password=player&action=downloadTheme&idTheme="+idTheme).openConnection();  
				StringBuilder sb = new StringBuilder(System.getProperty("user.dir" )+ "\\bin\\resources\\themes\\");
				sb.append(theme.getFileName().replaceAll("\"", ""));
				FileOutputStream fos = new FileOutputStream(sb.toString());
				InputStream in = ucon.getInputStream();
				int b = 0;
				while ((b = in.read())!= -1)
					fos.write(b);
				fos.close();

				AddonManager.addJarTheme(new File(sb.toString()));
				return theme.insertDB();
			}
			
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "Unable to reach distant winds server, please verify your internet connection and try again !");
			return false;
		}
		return true;
	}
	
	/*OK*/public static boolean downloadLevel(String email, String password, int idLevel){
		try {
			LevelData level = getLevelInfos(email, password, idLevel);
			
			if(level != null){
				String s = null;
				
				if(level.getLevelType().equals("\"basic\"")){
					s = "http://www.winds-game.com/API.php?email="+email.replace("\"", "")+"&password="+password.replace("\"", "")+"&action=downloadBasicLevel&idBasicLevel="+idLevel;
				}
				else if(level.getLevelType().equals("custom")){
					s = "http://www.winds-game.com/API.php?email="+email+"&password="+password+"&action=downloadCustomLevel&idCustomLevel="+idLevel;
				}
				else if(level.getLevelType().equals("tomoderate")){
					s = "http://www.winds-game.com/API.php?email="+email+"&password="+password+"&action=downloadLevelToModerate&idLevelToModerate="+idLevel;
				}
				
				URLConnection ucon = new URL(s).openConnection();  
				StringBuilder sb = new StringBuilder(System.getProperty("user.dir" )+ "\\bin\\resources\\levels\\");
				sb.append(level.getIdLevel());
				sb.append(".jar");
				//System.out.println(sb.toString());
				
				FileOutputStream fos = new FileOutputStream(sb.toString());
				InputStream in = ucon.getInputStream();
				int b = 0;
				while ((b = in.read())!= -1)
					fos.write(b);
				fos.close();
				//System.out.println("téléchargement du niveau terminé !!");
				AddonManager.addJarLevel(new File(sb.toString()));

				return level.insertDB();
			}
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "Unable to reach distant winds server, please verify your internet connection and try again !");
			return false;
		}
		return true;
	}
	
	/*TODO*/public static void uploadCustomLevel(String email, String password, JarLevel level){
		
	}

	/*TODO reste à formater les scores passés en paramètre*/
	public void uploadScores(String email, String password, ArrayList<Score> scores){
		String infosToUpload = "[{%22idLevel%22:1, %22time%22:32, %22nbClicks%22:67, %22nbItems%22:102}, {%22idLevel%22:4, %22time%22:37, %22nbClicks%22:68, %22nbItems%22:57}]";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "uploadScores");
		params.put("email", email);
		params.put("password", password);
		params.put("scores", infosToUpload);

		List<String> response = new ArrayList<String>();
		
		try { 	response = sendRequest(params); } 
		catch (Exception e) { e.printStackTrace(); }

		for(int i=0; i<response.size(); i++){
			System.out.println(response.get(i));
		}
		
	}
	
	public List<String> sendRequest(Map<String, String> params) throws Exception {
		ServerRequest req = new ServerRequest("POST");
		for(Map.Entry<String, String> entry : params.entrySet()){
			req.addParameter(entry.getKey(), entry.getValue());
		}
		return req.finish();
	}
	public List<String> uploadFile(String filepath) throws Exception {
		File uploadFile = new File(filepath);
		ServerRequest req = new ServerRequest("POST");
		req.addFile("hello1", uploadFile);
		return req.finish();
	}

	
	private class ServerRequest {
	    private final String boundary;
	    private static final String EOL = "\r\n";
	    private HttpURLConnection cnx;
	    private OutputStream outputStream;
	    private PrintWriter writer;
	    // penser au timeout a la lecture de flux avec un finally cnx.disconnect
	    
	    /**
	     * Initializes a new HTTP POST request with content-type equals to multipart/form-data
	     * @throws Exception
	     */
	    /*OK*/public ServerRequest(String method) throws Exception {
	    	boundary = String.valueOf(System.currentTimeMillis());
	    	URL url = new URL(ServerConnection.URL_API_SERVER);
	    	try {
	    		
		        cnx = (HttpURLConnection) url.openConnection();
		        cnx.setRequestMethod(method);
		        cnx.setConnectTimeout(ServerConnection.TIMEOUT * 1000);
	        	cnx.setUseCaches(false);
	        	cnx.setDoOutput(true);
		        cnx.setDoInput(true);
		        cnx.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
		        cnx.setRequestProperty("User-Agent", "Winds Agent");
		        outputStream = cnx.getOutputStream();
		        writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
		        
	        } catch(ConnectException e) {
	        	throw new Exception("Failure of the connection with Winds server");
	        } catch(UnknownHostException e) {
	        	throw new Exception("Bad Winds server URL");
	        } catch(SocketTimeoutException e) {
	        	throw new Exception("Winds server connection timeout reached");
	        } catch(IllegalStateException e) {
	        	throw new Exception("Already connected to Winds server");
	        } finally{
	        	if(cnx != null){ cnx.disconnect(); }
	        }	        
	    }
	 
	    /**
	     * Adds a parameter to the request
	     * @param name field name
	     * @param value field value
	     */
	    /*OK*/public void addParameter(String name, String value) {
	        writer.append("--" + boundary).append(EOL);
	        writer.append("Content-Disposition: form-data; name=\"" + name + "\"").append(EOL);
	        writer.append("Content-Type: text/plain; charset=UTF-8").append(EOL);
	        writer.append(EOL).append(value).append(EOL);
	        writer.flush();
	    }
	    /**
	     * Adds a upload file section to the request
	     * @param fileKey key to find in $_FILE on Winds server
	     * @param uploadFile a File to be uploaded
	     * @throws IOException
	     */
	    /*OK*/public void addFile(String fileKey, File uploadFile) throws IOException {
	        String fileName = uploadFile.getName();
	        writer.append("--" + boundary).append(EOL);
	        writer.append("Content-Disposition: form-data; name=\""+ fileKey +"\"; filename=\""+ fileName +"\"").append(EOL);
	        writer.append("Content-Type: "+ URLConnection.guessContentTypeFromName(fileName)).append(EOL);
	        writer.append("Content-Transfer-Encoding: binary").append(EOL);
	        writer.append(EOL);
	        writer.flush();
	 
	        FileInputStream inputStream = new FileInputStream(uploadFile);
	        byte[] buffer = new byte[4096];
	        int bytesRead = -1;
	        while ((bytesRead = inputStream.read(buffer)) != -1) {
	            outputStream.write(buffer, 0, bytesRead);
	        }
	        outputStream.flush();
	        inputStream.close();
	         
	        writer.append(EOL);
	        writer.flush();    
	    }
	 
	    /**
	     * Completes the request and receives response from the server.
	     * @return a list of Strings as response in case the server returned status OK, otherwise an exception is thrown.
	     * @throws Exception
	     */
	    public List<String> finish() throws Exception {
	        List<String> response = new ArrayList<String>();
	 
	        writer.append(EOL).flush();
	        writer.append("--" + boundary + "--").append(EOL);
	        writer.close();
	 
	        // checks server's status code first
	        int status = cnx.getResponseCode();
	        if (status == HttpURLConnection.HTTP_OK) {
	            BufferedReader reader = new BufferedReader(new InputStreamReader(
	                    cnx.getInputStream()));
	            String line = null;
	            while ((line = reader.readLine()) != null) {
	                response.add(line);
	            }
	            reader.close();
	            cnx.disconnect();
	        } else {
	        	cnx.disconnect();
	            throw new Exception("Winds server returned non-OK status: " + status);
	        }
	 
	        return response;
	    }
	}
	
}
