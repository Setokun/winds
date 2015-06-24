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
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	private static final String URL_API_SERVER = "http://localhost/Winds/page/test.php";
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
		try {
			  URLConnection ucon = new URL("http://www.winds-game.com/resources/brambles.jar" ).openConnection();
			  FileOutputStream fos = new FileOutputStream("C:\\Users\\Stephane\\Downloads\\test.jar" );
			  InputStream in = ucon.getInputStream();
			  int b = 0;
			  while ((b = in.read())!= -1)
			    fos.write(b);
			  fos.close();
			  System.out.println("téléchargement terminé !!");
			}
			catch (Exception e){
			  System.out.println(e);
			}
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
	
	
	
	public List<String> sendRequest(Map<String, String> params) throws Exception {
		ServerRequest req = new ServerRequest();
		for(Map.Entry<String, String> entry : params.entrySet()){
			req.addParameter(entry.getKey(), entry.getValue());
		}
		return req.finish();
	}
	public List<String> uploadFile(String filepath) throws Exception {
		File uploadFile = new File(filepath);
		ServerRequest req = new ServerRequest();
		req.addFile("hello1", uploadFile);
		return req.finish();
	}
	public void downloadFile(){
		
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
	    /*OK*/public ServerRequest() throws Exception {
	    	boundary = String.valueOf(System.currentTimeMillis());
	    	URL url = new URL(ServerConnection.URL_API_SERVER);
	    	try {
	    		
		        cnx = (HttpURLConnection) url.openConnection();
		        cnx.setRequestMethod("POST");
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
