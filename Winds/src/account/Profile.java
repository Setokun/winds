package account;

import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;

import server.ServerConnection;
import database.DBClass;

public class Profile {
	private int id;
	private String email;
	private String password;
	private String userType;
	private String pseudo;
	private int resolution;
	
	public static Profile current = null;
	
	public Profile(){
		
	}
	
	/**
	 * returns the profile's ID
	 * @return id int
	 */
	public int getId() {return id;}
	/**
	 * returns profile's email
	 * @return email String
	 */
	public String getEmail() {return email;}
	/**
	 * returns profile's password
	 * @return password String
	 */
	public String getPassword() {return password;}
	/**
	 * returns profile's type
	 * @return userType String
	 */
	public String getUserType() {return userType;}
	/**
	 * returns profile's pseudo
	 * @return pseudo String
	 */
	public String getPseudo() {return pseudo;}
	/**
	 * returns profile's resolution
	 * @return resolution int
	 */
	public int getResolution() {return resolution;}
	
	/**
	 * sets profile's ID
	 * @param id int
	 */
	public void setId(int id) {this.id = id;}
	/**
	 * sets profile's email
	 * @param email String
	 */
	public void setEmail(String email) {this.email = email;}
	/**
	 * sets profile's password
	 * @param password String
	 */
	public void setPassword(String password) {this.password = password;}
	/**
	 * sets profile's user type
	 * @param userType String
	 */
	public void setUserType(String userType) {this.userType = userType;}
	/**
	 * sets profile's pseudo
	 * @param pseudo String
	 */
	public void setPseudo(String pseudo) {this.pseudo = pseudo;}
	/**
	 * sets profile's resolution
	 * @param resolution int
	 */
	public void setResolution(int resolution) {this.resolution = resolution;}
	
	
	/**
	 * sets profile's "current" field to false
	 */
	public void disconnect(){
		try {
			DBClass.connect();
			DBClass.executeQuery("UPDATE users SET current=false WHERE id="+this.id);
		} catch (ClassNotFoundException | SQLException e) {
		} finally {
			DBClass.disconnect();
		}
	}
	
	/**
	 * returns a Profile from the local Database
	 * @param email String
	 * @param password String
	 * @return Profile
	 */
	public static Profile connect(String email, String password){
		
		try {
			DBClass.connect();
			ResultSet r = DBClass.requestQuery("SELECT * FROM users WHERE email='"+email+"' AND password='"+password+"'");
    		if(r.next()){
    			Profile p = new Profile();
    			int id = r.getInt("id");
    			p.setId(id);
    			p.setEmail(r.getString("email"));
    			p.setPassword(r.getString("password"));
    			p.setUserType(r.getString("userType"));
    			p.setPseudo(r.getString("pseudo"));
    			p.setResolution(r.getInt("resolution"));
    			DBClass.executeQuery("UPDATE users SET current=true WHERE id="+id);
    			return p;
    		}
			return null;
		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();
		} finally {
			DBClass.disconnect();
		}
		
		return null;
	}
	/**
	 * returns a Profile from the server
	 * @param email String
	 * @param password String
	 * @return Profile
	 */
	
	public static Profile connectFromServer(String email, String password){
		return ServerConnection.downloadProfile(email, password);
	}
	/**
	 * returns a Profile from the local Database
	 * @param email String
	 * @param password String
	 * @return int : 0 for "ko", 1 for "password updated", 2 for "wrong server password" and 3 for "new profile inserted"
	 */
	
	public static int insertOrUpdateProfile(String email, String password){
			try {
				if(isExistingEmail(email)){
					Profile profile = connectFromServer(email, password);
					if(profile.getEmail() != null){
						DBClass.connect();
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
					DBClass.connect();
					DBClass.executeQuery("INSERT INTO users (id, email, password, pseudo, userType, current, music, effects, resolution) "
							+ "VALUES ('"+profile.getId()+"','"+profile.getEmail()+"','"+password+"','"+profile.getPseudo()+"','"+profile.getUserType()+"',true, 5, 5, 1);");
					return 3;
				}
			} 
			catch (ClassNotFoundException e) {e.printStackTrace();}
			catch (SQLException e) {e.printStackTrace();}
			finally{
				DBClass.disconnect();
			}
		return 0;
	}
	/**
	 * returns if the email already exists in the local database
	 * @param email String
	 * @return boolean
	 */
	
	public static boolean isExistingEmail(String email){
		
		try {
			DBClass.connect();
    		ResultSet r = DBClass.requestQuery("SELECT * FROM users WHERE email='"+email+"'");
    		if(r.next()){
    			return true;
    		}
		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();
		} finally{
			DBClass.disconnect();
		}
		
		return false;
	}
	/**
	 * returns the current Profile or null if there's no one 
	 * @param email String
	 * @param password String
	 * @return Profile
	 */
	
 	public static Profile getCurrentPlayer(){
    	
    	try {
    		DBClass.connect();
    		ResultSet r = DBClass.requestQuery("SELECT * FROM users WHERE current=true");
    		if(r.next()){
    			Profile p = new Profile();
    			p.setId(r.getInt("id"));
    			p.setEmail(r.getString("email"));
    			p.setPassword(r.getString("password"));
    			p.setUserType(r.getString("userType"));
    			p.setPseudo(r.getString("pseudo"));
    			p.setResolution(r.getInt("resolution"));
    			return p;
    		}
			return null;
		} catch (ClassNotFoundException | SQLException e) {
		} finally { DBClass.disconnect(); }
    	
    	return null;
    }
 	/**
	 * returns an updated Profile with the new parameters
	 * @param music int
	 * @param soundEffects int
	 * @param resolution int
	 * @return Profile
	 */

	public Profile updateConfiguration(int resolution){
		
		try {
			DBClass.connect();
    		DBClass.requestQuery("UPDATE users SET resolution="+resolution+" WHERE id="+this.getId());
    		return connect(current.getEmail(), current.getPassword());
		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();
		} finally{
			DBClass.disconnect();
		}
		return current;
	}
	/**
	 * returns the screen Dimension attached to this Profile
	 * @return Dimension
	 */
	
	public Dimension getScreenDimensions(){
		try {
			DBClass.connect();
    		ResultSet r = DBClass.requestQuery("SELECT width, height FROM resolutions WHERE id="+this.resolution);
    		if(r.next()){
    			return new Dimension(r.getInt("width"), r.getInt("height"));
    		}
			return null;
		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();
		} finally{
			DBClass.disconnect();
		}
    	
    	return null;	
    }
	/**
	 * returns a String representation of this Profile
	 * @return String
	 */
	
	public String toString(){
		return "id :" + id + ",email : " + email + ",password : "+password+", user type : " + userType + ", pseudo : " + pseudo + ", id resolution : " + resolution;
	}
	
}
