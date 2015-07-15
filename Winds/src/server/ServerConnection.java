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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import account.Profile;
import addon.AddonManager;
import addon.JarLevel;
import addon.Level;
import addon.level.LevelMode;
import addon.level.LevelStatus;
import addon.level.LevelType;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import database.DBClass;
import database.Score;
import database.ThemeData;
import database.Trophy;

public class ServerConnection {
	private static final String URL_API_SERVER = "http://www.winds-game.com/API.php";
	private static final int TIMEOUT = 6; // seconds
	
	//region Public methods 
	/**
	 * Downloads the user's profile with match the specified parameters.
	 * @param email The user's e-mail address
	 * @param password The user's password
	 * @return
	 */
	public static Profile downloadProfile(String email, String password){
		Profile profile = null;

		try {
			URL monURL = new URL(URL_API_SERVER+"?email="+email+"&password="+ password +"&action=downloadProfile");
	        URLConnection yc = monURL.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
	        
	        Gson gson = new Gson();
	        profile = gson.fromJson(in, Profile.class);
	        in.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Unable to reach distant winds server, please verify your internet connection and try again !");
		}
		
		return profile;
	}
	/**
	 * Get the scores of the current player from the remote server.
	 * @return ArrayList<Score>
	 * @throws IOException
	 */
	public static ArrayList<Score> getScores() throws IOException{
		ArrayList<Score> oldScores = Score.getLocalScoresForUpload();
		uploadScores(oldScores);

		ArrayList<Score> scores = new ArrayList<Score>();
		JsonArray jArray = getJsonArrayOfGetRequest("action=getScores");
		for (int i=0;i<jArray.size();i++) {
		    JsonObject jsonObject = jArray.get(i).getAsJsonObject();
		    Score score = new Score(Integer.valueOf(jsonObject.get("idLevel").toString().replaceAll("\"", "")));
		    score.setTime(Integer.valueOf(jsonObject.get("time").toString().replaceAll("\"", "")));
		    score.setClicks(Integer.valueOf(jsonObject.get("nbClicks").toString().replaceAll("\"", "")));
		    score.setNbItems(Integer.valueOf(jsonObject.get("nbItems").toString().replaceAll("\"", "")));
		    score.setLevelName(jsonObject.get("levelName").toString().replaceAll("\"", ""));
		    scores.add(score);
		}
		
		for (int i = 0; i < scores.size(); i++) {
			Score score = scores.get(i);
			try {
				String s = "INSERT INTO scores (idPlayer, idLevel, time, nbClicks, nbItems) "
						+ "VALUES ("+Profile.current.getId()+","+score.getIdLevel()+","+score.getTime()+","+score.getClicks()+","+score.getNbItems()+");";
				DBClass.connect();
				DBClass.executeQuery(s);
			} catch (ClassNotFoundException e) {
			} catch (SQLException e) {
				try {
					String s =  "UPDATE scores SET time="+score.getTime()+", nbClicks="+score.getClicks()
							+", nbItems="+score.getNbItems()+" WHERE idLevel="+score.getIdLevel()
							+" AND idPlayer="+Profile.current.getId();
					DBClass.connect();
					DBClass.executeQuery(s);
				} catch (ClassNotFoundException e1) {
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Critical database issue, please restart the game.");
					DBClass.createStructures();
					DBClass.createStartData();
					System.exit(1);
				}
			}finally{
				DBClass.disconnect();
			}
		}
		
		return scores;
	}
	/**
	 * Get the themes list from the remote server.
	 * @return ArrayList<ThemeData>
	 * @throws IOException
	 */
	public static ArrayList<ThemeData> getThemesList() throws IOException{
		ArrayList<ThemeData> themes = new ArrayList<ThemeData>();

		JsonArray jArray = getJsonArrayOfGetRequest("action=getThemes");
		for (int i=0;i<jArray.size();i++) {
		    JsonObject jsonObject = jArray.get(i).getAsJsonObject();
		    ThemeData thm = new ThemeData();
		    thm.setIdTheme(Integer.valueOf(jsonObject.get("id").toString().replaceAll("\"", "")));
		    thm.setName(jsonObject.get("name").toString().replaceAll("\"", ""));
		    themes.add(thm);
		}
		
		return themes;
	}
	/**
	 * Get the trophies of the current player from the remote server.
	 * @return ArrayList<Trophy>
	 */
	public static ArrayList<Trophy> getTrophies(){
		ArrayList<Trophy> trophies = null;
		
		try {
			trophies = new ArrayList<Trophy>();
			
			JsonArray jArray = getJsonArrayOfGetRequest("action=getTrophies");
			for (int i=0;i<jArray.size();i++) {
		    JsonObject jsonObject = jArray.get(i).getAsJsonObject();
		    Trophy trophy = new Trophy();
		    trophy.setDescription(jsonObject.get("trophy").toString().replaceAll("\"", ""));
		    trophy.setAchieved(jsonObject.get("ok").toString().replaceAll("\"", "").equals("ok"));
		    trophies.add(trophy);
		}
		} catch (IOException e) {}		
		
		return trophies;
	}
	/**
	 * Get the informations about the theme into the remote server which matches the specified ID.
	 * @param idTheme The ID of the theme to find
	 * @return ThemeData
	 * @throws IOException
	 */
	public static ThemeData getThemeInfos(int idTheme) throws IOException{
		ThemeData themeData = null;

		JsonArray jArray = getJsonArrayOfGetRequest("action=getThemes&idTheme="+idTheme);
		for (int i=0;i<jArray.size();i++) {
		    JsonObject jsonObject = jArray.get(i).getAsJsonObject();
		    themeData = new ThemeData();
		    themeData.setIdTheme(Integer.valueOf(jsonObject.get("id").toString().replaceAll("\"", "")));
		    themeData.setName(jsonObject.get("name").toString().replaceAll("\"", ""));
		    themeData.setDescription(jsonObject.get("description").toString().replaceAll("\"", ""));
		}
		
		return themeData;
	}
	/**
	 * Get the informations about the level into the remote server which matches the specified ID.
	 * @param idLevel The ID of the level to find
	 * @return Level
	 * @throws IOException
	 */
	public static Level getLevelInfos(int idLevel) throws IOException{
		Level level = null;

		JsonArray jArray = getJsonArrayOfGetRequest("action=getLevelInfos&idLevel="+idLevel);
		for (int i=0;i<jArray.size();i++) {
		    JsonObject jsonObject = jArray.get(i).getAsJsonObject();
		    level = new Level();
		    level.setTimeMax(Integer.valueOf(jsonObject.get("timeMax").toString().replaceAll("\"", "")));
		    
		    String type = jsonObject.get("levelType").toString().replaceAll("\"", "");
		    if(type.equals("basic"))			level.setType(LevelType.basic);
		    else if(type.equals("custom"))		level.setType(LevelType.custom);
		    else if(type.equals("tomoderate"))	level.setType(LevelType.tomoderate);

		    String mode = jsonObject.get("levelMode").toString().replaceAll("\"", "");
		    if(mode.equals("standard"))	level.setMode(LevelMode.standard);
		    if(mode.equals("boss"))		level.setMode(LevelMode.boss);
		    
		    level.setStatus(LevelStatus.installed);
		    level.setIdDB(Integer.valueOf(jsonObject.get("idLevel").toString().replaceAll("\"", "")));
		    level.setIdTheme(Integer.valueOf(jsonObject.get("idTheme").toString().replaceAll("\"", "")));
		    level.setName(jsonObject.get("name").toString().replaceAll("\"", ""));
		    level.setDescription(jsonObject.get("description").toString().replaceAll("\"", ""));
		    level.setCreator(jsonObject.get("creator").toString().replaceAll("\"", ""));
		}
		
		return level;
	}
	/**
	 * Get the available basic levels list from the remote server.
	 * @return ArrayList<Level>
	 * @throws IOException
	 */
	public static ArrayList<Level> getBasicLevelsList() throws IOException{
		ArrayList<Level> basicLevels = new ArrayList<Level>();

		JsonArray jArray = getJsonArrayOfGetRequest("action=getBasicLevels");
		for (int i=0;i<jArray.size();i++) {
		    JsonObject jsonObject = jArray.get(i).getAsJsonObject();
		    Level lvl = new Level();
		    lvl.setTimeMax(Integer.valueOf(jsonObject.get("timeMax").toString().replaceAll("\"", "")));
		    lvl.setIdTheme(Integer.valueOf(jsonObject.get("idTheme").toString().replaceAll("\"", "")));
		    lvl.setName(jsonObject.get("name").toString().replaceAll("\"", ""));
		    lvl.setDescription(jsonObject.get("description").toString().replaceAll("\"", ""));
		    lvl.setCreator(jsonObject.get("creator").toString().replaceAll("\"", ""));
		    lvl.setIdDB(Integer.valueOf(jsonObject.get("idLevel").toString().replaceAll("\"", "")));
		    String mode = jsonObject.get("levelMode").toString().replaceAll("\"", "");
		    lvl.setMode((mode.equals("boss"))?LevelMode.boss:LevelMode.standard);
		    basicLevels.add(lvl);
		}
		
		return basicLevels;
	}
	/**
	 * Get the available custom levels list from the remote server.
	 * @return ArrayList<Level>
	 * @throws IOException
	 */
	public static ArrayList<Level> getCustomLevelsList() throws IOException{
		ArrayList<Level> customLevels = new ArrayList<Level>();

		JsonArray jArray = getJsonArrayOfGetRequest("action=getCustomLevels");
		for (int i=0;i<jArray.size();i++) {
		    JsonObject jsonObject = jArray.get(i).getAsJsonObject();
		    Level lvl = new Level();
		    lvl.setTimeMax(Integer.valueOf(jsonObject.get("timeMax").toString().replaceAll("\"", "")));
		    lvl.setIdTheme(Integer.valueOf(jsonObject.get("idTheme").toString().replaceAll("\"", "")));
		    lvl.setName(jsonObject.get("name").toString().replaceAll("\"", ""));
		    lvl.setDescription(jsonObject.get("description").toString().replaceAll("\"", ""));
		    lvl.setCreator(jsonObject.get("creator").toString().replaceAll("\"", ""));
		    lvl.setIdDB(Integer.valueOf(jsonObject.get("idLevel").toString().replaceAll("\"", "")));
		    customLevels.add(lvl);
		}

		return customLevels;
	}
	/**
	 * Get the available to-moderate levels list from the remote server.
	 * @return ArrayList<Level>
	 * @throws IOException
	 */
	public static ArrayList<Level> getLevelsToModerateList() throws IOException{
		ArrayList<Level> levelsToModerate = new ArrayList<Level>();

		JsonArray jArray = getJsonArrayOfGetRequest("action=getLevelsToModerate");
		for (int i=0;i<jArray.size();i++) {
		    JsonObject jsonObject = jArray.get(i).getAsJsonObject();
		    Level lvl = new Level();
		    lvl.setTimeMax(Integer.valueOf(jsonObject.get("timeMax").toString().replaceAll("\"", "")));
		    lvl.setIdTheme(Integer.valueOf(jsonObject.get("idTheme").toString().replaceAll("\"", "")));
		    lvl.setName(jsonObject.get("name").toString().replaceAll("\"", ""));
		    lvl.setDescription(jsonObject.get("description").toString().replaceAll("\"", ""));
		    lvl.setCreator(jsonObject.get("creator").toString().replaceAll("\"", ""));
		    lvl.setIdDB(Integer.valueOf(jsonObject.get("idLevel").toString().replaceAll("\"", "")));
		    levelsToModerate.add(lvl);
		}

		return levelsToModerate;
	}
	/**
	 * Downloads the theme from the remote server which matches the specified ID.
	 * @param idTheme The ID of the theme to download
	 * @return boolean
	 */
	public static boolean downloadTheme(int idTheme){
		try {
			ThemeData theme = getThemeInfos(idTheme);
			if(theme != null){
				URLConnection ucon = new URL(URL_API_SERVER+"?email="+ Profile.current.getEmail()
						+"&password="+ Profile.current.getPassword()
						+"&action=downloadTheme&idTheme="+ idTheme)
					.openConnection();  
				StringBuilder sb = new StringBuilder(AddonManager.getThemesPath());
				File folder = new File(AddonManager.getThemesPath());
				if( !folder.exists()) folder.mkdir();
				sb.append(theme.getName().replaceAll("\"", ""));
				sb.append(".jar");
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
		catch (NullPointerException e){
			JOptionPane.showMessageDialog(null, "Unable to install this theme !");
			return false;
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "Unable to reach distant winds server, please verify your internet connection and try again !");
			return false;
		}
		return true;
	}
	/**
	 * Downloads the level from the remote server which matches the specified ID.
	 * @param idLevel The ID of the level to download
	 * @return boolean
	 */
	@SuppressWarnings("unused")
	public static boolean downloadLevel(int idLevel){
		try {
			Level level = getLevelInfos(idLevel);
			if(level.getType().equals("custom"))
				level.setStatus(LevelStatus.installed);
			
			if(level != null){
				String s = URL_API_SERVER +"?email="+ Profile.current.getEmail().replace("\"", "")
						 + "&password="+ Profile.current.getPassword().replace("\"", "");
				
				if(level.getType() == LevelType.basic) 			 s += "&action=downloadBasicLevel&idBasicLevel="+ idLevel;
				else if(level.getType() == LevelType.custom) 	 s += "&action=downloadCustomLevel&idCustomLevel="+ idLevel;
				else if(level.getType() == LevelType.tomoderate) s += "&action=downloadLevelToModerate&idLevelToModerate="+ idLevel;
				else return false;
				
				URLConnection ucon = new URL(s).openConnection();  
				StringBuilder sb = new StringBuilder(AddonManager.getLevelsPath());
				File folder = new File(AddonManager.getLevelsPath());
				if( !folder.exists()) folder.mkdir();
				sb.append(level.getIdDB());
				sb.append(".jar");

				FileOutputStream fos = new FileOutputStream(sb.toString());
				InputStream in = ucon.getInputStream();
				int b = 0;
				while ((b = in.read())!= -1)
					fos.write(b);
				fos.close();
				
				JarLevel jl = new JarLevel(new File(sb.toString()));
				if(jl.isValid()){
					AddonManager.addJarLevel(new File(sb.toString()));
					return level.insertDB();
				}else return false;
			}
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "Unable to reach distant winds server, please verify your internet connection and try again !");
			return false;
		}
		return true;
	}
	/**
	 * Uploads the specified archive to the remote server and returns the server response.
	 * @param jar The archive which represents a Winds level to upload
	 * @return ArrayList<String>
	 */
	public static ArrayList<String> uploadCustomLevel(JarLevel jar){
		try {
			return uploadFile(jar.getFile());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Unable to upload your level to the remote server");
			return new ArrayList<String>();
		}
	}
	/**
	 * Uploads the specified scores list to the remote server and returns the success statement.
	 * @param scores The scores list to upload
	 * @return ArrayList<String>
	 */
	public static boolean uploadScores(ArrayList<Score> scores){
		List<String> response = new ArrayList<String>();

		if(scores != null && scores.size() > 0){
			StringBuilder infosToUpload = new StringBuilder("[");
			
			for (int i = 0; i < scores.size(); i++)
				infosToUpload.append(scores.get(i).toString()+",");
			
			infosToUpload.deleteCharAt(infosToUpload.length()-1);
			infosToUpload.append("]");
			
			Map<String, String> params = new HashMap<String, String>();
			params.put("action", "uploadScores");
			params.put("email", Profile.current.getEmail());
			params.put("password", Profile.current.getPassword());
			params.put("scores", infosToUpload.toString());
			
			try { response = sendRequest(params); } 
			catch (Exception e) {}
		}
		return response.size() > 0 ? scores.size() == Integer.valueOf(response.get(0)) : false;
	}
	//endregion
	
	//region Private methods 
	/**
	 * Builds a GET request to send to the remote server with the user's
	 * e-mail address and password as default<br>and add the specified parameter
	 * at the end of the request. Then, returns the server response.
	 * @param endURL The text to add at the end of the request
	 * @return JsonArray
	 * @throws IOException
	 */
	private static JsonArray getJsonArrayOfGetRequest(String endURL) throws IOException {
		URL url = new URL(URL_API_SERVER +"?email="+ Profile.current.getEmail()
				+"&password="+ Profile.current.getPassword() +"&"+ endURL);
		
		URLConnection uc = url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
        JsonArray jArray = new JsonParser().parse(br).getAsJsonArray();
		br.close();
		
        return jArray;
	}
	/**
	 * Builds a POST request to send to the remote server with the specified parameters
	 * and returns the server response.
	 * @param params The hashmap which contains the parameters to send
	 * @return List<String>
	 * @throws Exception
	 */
	private static List<String> sendRequest(Map<String, String> params) throws Exception {
		ServerRequest req = new ServerRequest("POST");
		for(Map.Entry<String, String> entry : params.entrySet())
			req.addParameter(entry.getKey(), entry.getValue());
		return req.finish();
	}
	/**
	 * Builds a request to send the specified file to the remote server and returns the server response.
	 * @param f The file to upload
	 * @return ArrayList<String>
	 * @throws Exception
	 */
	private static ArrayList<String> uploadFile(File f) throws Exception {
		ServerRequest req = new ServerRequest("POST");
		req.addParameter("email", Profile.current.getEmail());
		req.addParameter("password", Profile.current.getPassword());
		req.addParameter("action", "uploadCustomLevel");
		req.addFile("level", f);
		return req.finish();
	}
	//endregion
	
	//region Internal class 
	/**
	 * Internal class used to send multipart/form-data request to the remote Winds server.
	 */
	private static class ServerRequest {
	    private final String boundary;
	    private static final String EOL = "\r\n";
	    private HttpURLConnection cnx;
	    private OutputStream outputStream;
	    private PrintWriter writer;
	    
	    /**
	     * Initializes a new HTTP POST request with content-type equals to multipart/form-data.
	     * @throws Exception
	     */
	    public ServerRequest(String method) throws Exception {
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
	        	if(cnx != null)  cnx.disconnect();
	        }	        
	    }
	    
	    /**
	     * Adds a parameter to the request
	     * @param name The field name
	     * @param value The field value
	     */
	    public void addParameter(String name, String value) {
	        writer.append("--" + boundary).append(EOL);
	        writer.append("Content-Disposition: form-data; name=\"" + name + "\"").append(EOL);
	        writer.append("Content-Type: text/plain; charset=UTF-8").append(EOL);
	        writer.append(EOL).append(value).append(EOL);
	        writer.flush();
	    }
	    /**
	     * Adds a upload file section to the request
	     * @param fileKey The key to find in $_FILE on Winds server
	     * @param uploadFile The file to upload
	     * @throws IOException
	     */
	    public void addFile(String fileKey, File uploadFile) throws IOException {
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
	        while ((bytesRead = inputStream.read(buffer)) != -1)
	            outputStream.write(buffer, 0, bytesRead);
	        outputStream.flush();
	        inputStream.close();
	         
	        writer.append(EOL);
	        writer.flush();    
	    }
	    /**
	     * Completes the request and receives response from the server.
	     * @return ArrayList<String> as response in case the server returned status OK, otherwise an exception is thrown.
	     * @throws Exception
	     */
	    public ArrayList<String> finish() throws Exception {
	        ArrayList<String> response = new ArrayList<String>();
	 
	        writer.append(EOL).flush();
	        writer.append("--" + boundary + "--").append(EOL);
	        writer.close();
	 
	        // checks server's status code first
	        int status = cnx.getResponseCode();
	        if (status == HttpURLConnection.HTTP_OK) {
	            BufferedReader reader = new BufferedReader(new InputStreamReader(cnx.getInputStream()));
	            String line = null;
	            while ((line = reader.readLine()) != null)
	                response.add(line);
	            reader.close();
	            cnx.disconnect();
	        } else {
	        	cnx.disconnect();
	            throw new Exception("Winds server returned non-OK status: " + status);
	        }
	 
	        return response;
	    }
	}
	//endregion
	
}
