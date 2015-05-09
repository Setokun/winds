package accounts;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.DBClass;

public class Profile {
	private int id;
	private String email;
	private String password;
	private String userType;
	private String pseudo;
	
	
	public Profile(){
		
	}
	
	public void disconnect(){ // TODO à tester
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
    			DBClass.executeQuery("UPDATE users SET current=true WHERE id="+id);
    			return p;
    		}
			return null;
		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();
		}
		
		return null;
	}
	
	public static Profile getCurrentPlayer(){ // TODO à tester
    	
    	try {
    		ResultSet r = DBClass.requestQuery("SELECT * FROM users WHERE current=true");
    		if(r.next()){
    			Profile p = new Profile();
    			p.setId(r.getInt("id"));
    			p.setEmail(r.getString("email"));
    			p.setPassword(r.getString("password"));
    			p.setUserType(r.getString("userType"));
    			p.setPseudo(r.getString("pseudo"));
    			return p;
    		}
			return null;
		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();
		}
    	
    	return null;
    }

	public int getId() {return id;}
	public String getEmail() {return email;}
	public String getPassword() {return password;}
	public String getUserType() {return userType;}
	public String getPseudo() {return pseudo;}
	
	public void setId(int id) {this.id = id;}
	public void setEmail(String email) {this.email = email;}
	public void setPassword(String password) {this.password = password;}
	public void setUserType(String userType) {this.userType = userType;}
	public void setPseudo(String pseudo) {this.pseudo = pseudo;}
	
	
	
}
