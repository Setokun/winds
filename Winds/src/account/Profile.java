package account;

import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;

import server.ServerConnection;
import database.DBClass;
import display.Window;

public class Profile {
	private int id;
	private String email;
	private String password;
	private String userType;
	private String pseudo;
	private int musicVolume;
	private int soundEffectsVolume;
	private int resolution;
	
	
	public Profile(){
		
	}
	
	public int getId() {return id;}
	public String getEmail() {return email;}
	public String getPassword() {return password;}
	public String getUserType() {return userType;}
	public String getPseudo() {return pseudo;}
	public int getMusicVolume() {return musicVolume;}
	public int getSoundEffectsVolume() {return soundEffectsVolume;}
	public int getResolution() {return resolution;}
	
	public void setId(int id) {this.id = id;}
	public void setEmail(String email) {this.email = email;}
	public void setPassword(String password) {this.password = password;}
	public void setUserType(String userType) {this.userType = userType;}
	public void setPseudo(String pseudo) {this.pseudo = pseudo;}
	public void setMusicVolume(int musicVolume) {this.musicVolume = musicVolume;}
	public void setSoundEffectsVolume(int soundEffectsVolume) {this.soundEffectsVolume = soundEffectsVolume;}
	public void setResolution(int resolution) {this.resolution = resolution;}
	
	public void disconnect(){
		try {
			DBClass.executeQuery("UPDATE users SET current=false WHERE id="+this.id);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Profile connect(String email, String password){
		
		try {
			ResultSet r = DBClass.requestQuery("SELECT * FROM users WHERE email='"+email+"' AND password='"+password+"'");
    		if(r.next()){
    			Profile p = new Profile();
    			int id = r.getInt("id");
    			p.setId(id);
    			p.setEmail(r.getString("email"));
    			p.setPassword(r.getString("password"));
    			p.setUserType(r.getString("userType"));
    			p.setPseudo(r.getString("pseudo"));
    			p.setMusicVolume(r.getInt("music"));
    			p.setSoundEffectsVolume(r.getInt("effects"));
    			p.setResolution(r.getInt("resolution"));
    			DBClass.executeQuery("UPDATE users SET current=true WHERE id="+id);
    			return p;
    		}
			return null;
		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();
		}
		
		return null;
	}
	
	public static Profile connectFromServer(String email, String password){
		return ServerConnection.downloadProfile(email, password);
	}
	
	public static int insertOrUpdateProfile(String email, String password){
		// 0 -> ko
		// 1 -> password updated
		// 2 -> wrong server password
		// 3 -> new profile inserted
			try {
				if(isExistingEmail(email)){
					Profile profile = connectFromServer(email, password);
					if(profile.getEmail() != null){
						DBClass.executeQuery("UPDATE users set password='"+password+"', current=true WHERE email='"+email+"'");
						return 1;
					}else{
						return 2;
					}
				}
				else
				{
					Profile profile = connectFromServer(email, password);
					if(profile == null)return 0;
					if(profile.getEmail() == null)return 2;
					DBClass.executeQuery("INSERT INTO users (id, email, password, pseudo, userType, current, music, effects, resolution) "
							+ "VALUES ('"+profile.getId()+"','"+profile.getEmail()+"','"+password+"','"+profile.getPseudo()+"','"+profile.getUserType()+"',true, 5, 5, 1);");
					return 3;
				}
			} 
			catch (ClassNotFoundException e) {e.printStackTrace();}
			catch (SQLException e) {e.printStackTrace();}
		return 0;
	}
	
	public static boolean isExistingEmail(String email){
		
		try {
    		ResultSet r = DBClass.requestQuery("SELECT * FROM users WHERE email='"+email+"'");
    		if(r.next()){
    			return true;
    		}
		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();
		}
		
		return false;
	}
	
 	public static Profile getCurrentPlayer(){
    	
    	try {
    		ResultSet r = DBClass.requestQuery("SELECT * FROM users WHERE current=true");
    		if(r.next()){
    			Profile p = new Profile();
    			p.setId(r.getInt("id"));
    			p.setEmail(r.getString("email"));
    			p.setPassword(r.getString("password"));
    			p.setUserType(r.getString("userType"));
    			p.setPseudo(r.getString("pseudo"));
    			p.setMusicVolume(r.getInt("music"));
    			p.setSoundEffectsVolume(r.getInt("effects"));
    			p.setResolution(r.getInt("resolution"));
    			return p;
    		}
			return null;
		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();
		}
    	
    	return null;
    }

	public Profile updateConfiguration(int music, int soundEffects, int resolution){
		
		try {
    		DBClass.requestQuery("UPDATE users SET music="+music+", effects="+soundEffects+", resolution="+resolution+" WHERE id="+this.getId());
    		return connect(Window.profile.getEmail(), Window.profile.getPassword());
		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();
		}
		return Window.profile;
	}
	
	public Dimension getScreenDimensions(){
		try {
    		ResultSet r = DBClass.requestQuery("SELECT width, height FROM resolutions WHERE id="+this.resolution);
    		if(r.next()){
    			return new Dimension(r.getInt("width"), r.getInt("height"));
    		}
			return null;
		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();
		}
    	
    	return null;	
    }
	
	public String toString(){
		return "email : " + email + ",password : "+password;
	}
	
}
