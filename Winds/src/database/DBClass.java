package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import com.mysql.jdbc.*;
import java.util.Random;

public class DBClass{    
    public static void executeQuery(String sql) throws ClassNotFoundException, SQLException{       
    	  
    	try {
    		Class.forName("org.hsqldb.jdbcDriver").newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
          //System.out.println("Driver ok !!!");
          Connection connexion = null;
          try{
        	  connexion = DriverManager.getConnection("jdbc:hsqldb:file:winds", "sa",  "");
          }
          catch(Exception e){
        	  e.printStackTrace();
          }
          
          Statement statement = connexion.createStatement() ;
          statement.executeQuery(sql);
          
          statement.executeQuery("SHUTDOWN");
          statement.close();
    }
    
    public static ResultSet requestQuery(String sql) throws ClassNotFoundException, SQLException{       
  	  
    	try {
    		Class.forName("org.hsqldb.jdbcDriver").newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
          Connection connexion = null;
          try{
        	  connexion = DriverManager.getConnection("jdbc:hsqldb:file:winds", "sa",  "");
          }
          catch(Exception e){
        	  e.printStackTrace();
          }
          
          Statement statement = connexion.createStatement() ;
          ResultSet result = statement.executeQuery(sql);
          statement.executeQuery("SHUTDOWN");
          statement.close();
          
          return result;
    }
    
    public static void createStructures(){
    	try {
			DBClass.executeQuery("CREATE TABLE scores (id INT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) PRIMARY KEY, idPlayer INT, idLevel INT, time INT, nbClicks INT, nbItems INT, rank INT)");
			DBClass.executeQuery("CREATE TABLE levels (id INT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) PRIMARY KEY, name VARCHAR, description VARCHAR, creationDate DATETIME, timeMax INT, levelType VARCHAR, levelStatus VARCHAR, levelMode VARCHAR, idCreator INT, idTheme INT)");
			DBClass.executeQuery("CREATE TABLE themes (id INT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) PRIMARY KEY, name VARCHAR, description VARCHAR, creationDate DATETIME, idCreator INT)");
			DBClass.executeQuery("CREATE TABLE users (id INT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) PRIMARY KEY, email VARCHAR, password VARCHAR, pseudo VARCHAR, registrationDate VARCHAR, userType VARCHAR, current BOOLEAN)");
			DBClass.executeQuery("CREATE TABLE trophies (id INT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) PRIMARY KEY, idPlayer INT, description VARCHAR, achieved VARCHAR)");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public static void createTestData(){
		Random rand = new Random();
		try {
			for (int i = 1; i <= 4; i++) {
				executeQuery("INSERT INTO users (email, password, pseudo, registrationDate, userType, current) VALUES ('perso"+i+"@live.fr','pwd"+i+"','pseudo"+i+"','2015-05-0"+i+" 12:05:27','player',false);");
			}
			executeQuery("INSERT INTO users (email, password, pseudo, registrationDate, userType, current) VALUES ('admin@live.fr','admin','admin','2015-04-30 00:05:00','administrator',false);");
			for (int i = 1; i <= 20; i++) {
				DBClass.executeQuery("INSERT INTO scores (idPlayer, idLevel, time, nbClicks, nbItems, rank) "
											   + "VALUES ('"+(rand.nextInt(5)+1)+"','"+(rand.nextInt(20)+1)+"','"+ (rand.nextInt(100)+37) +"','"+ (rand.nextInt(500)+100) +"','"+ (rand.nextInt(50)+1) +"',0);");
			}
			for (int i = 1; i <= 20; i++) {
				DBClass.executeQuery("INSERT INTO levels (name, description, creationDate, timeMax, levelType, levelStatus, levelMode, idCreator, idTheme) VALUES ("
																+ "'level"+i+"',"
																+ "'description"+i+"',"
																+ "'2015-04-"+((i<10)?"0"+i:i)+" 00:05:00',"
																+ "'"+ (rand.nextInt(100)+37) +"',"
																+ "'"+ ((i%2==0)?"basic":"custom") +"',"
																+ "'active',"
																+ "'"+ ((i%4==0)?"standard":"boss") +"',"
																+ "'"+ (rand.nextInt(5)+1) +"',"
																+ "'"+ (rand.nextInt(3)+1) +"');");
			}
			
			DBClass.executeQuery("INSERT INTO themes (name, description, creationDate, idCreator) VALUES ('honey','honey','2015-04-30 00:05:00', 5);");
			DBClass.executeQuery("INSERT INTO themes (name, description, creationDate, idCreator) VALUES ('brambles','brambles','2015-04-30 00:05:00', 5);");
			DBClass.executeQuery("INSERT INTO themes (name, description, creationDate, idCreator) VALUES ('pirate','pirate','2015-04-30 00:05:00' ,5);");
			
			DBClass.executeQuery("INSERT INTO trophies (idPlayer, description, achieved) VALUES (1,'Achieve the first level','ok')");
			DBClass.executeQuery("INSERT INTO trophies (idPlayer, description, achieved) VALUES (1,'Achieve 10 levels','')");
			DBClass.executeQuery("INSERT INTO trophies (idPlayer, description, achieved) VALUES (1,'Achieve a level without being touched','ok')");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    public static void deleteTestData(){
    	try {
			DBClass.executeQuery("DROP TABLE scores;");
			DBClass.executeQuery("DROP TABLE levels;");
			DBClass.executeQuery("DROP TABLE themes;");
			DBClass.executeQuery("DROP TABLE users;");
			DBClass.executeQuery("DROP TABLE trophies;");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
    }
	
    public static  ResultSet getScores(){
		try {
			//return DBClass.requestQuery("SELECT * FROM scores WHERE idPlayer="+1);
			return DBClass.requestQuery("SELECT levels.name AS levelName, nbItems, nbClicks, time, rank FROM scores JOIN levels ON levels.id = scores.idLevel WHERE idPlayer="+1+"ORDER BY levels.id");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
    
    public static ResultSet getTrophies(){
    	try {
			return DBClass.requestQuery("SELECT * FROM trophies WHERE idPlayer="+1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
    }
	
    public static  void deleteScoresFromBDD(){
		try {
			DBClass.executeQuery("DELETE FROM scores");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
    public static  void deleteTableScoresFromBDD(){
		try {
			DBClass.executeQuery("DROP TABLE scores");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
    public static  String transformIntTimeInString(int time){
		String result = "";
		int hours = time / 3600;
		int minutes = time / 60;
		int seconds = time % 60;
		if(hours> 0){
			result += hours+":";
		}
		
		result += minutes + ":" + ((seconds > 9)?String.valueOf(seconds):"0"+seconds);
		
		return result;
	}
    
    public static String transformPositionInString(int position, int total){
    	
    	if(position == 0 || total == 0){
    		return "---";
    	}
    	
    	return position + "/" + total;
    }
    
    
    
}
